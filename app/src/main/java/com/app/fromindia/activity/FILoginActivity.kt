package com.app.fromindia.activity

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.fromindia.R
import com.app.fromindia.helper.CommonValues.*
import com.app.fromindia.helper.PreferenceHelper

import com.app.fromindia.model.Customer
import com.app.fromindia.model.LoginResponse
import com.app.fromindia.model.User
import com.app.fromindia.retrofit.APIClient
import com.app.fromindia.retrofit.APIInterface
import com.app.fromindia.utils.FIHelper
import com.app.fromindia.utils.Utils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.app.fromindia.helper.PreferenceHelper.userId
import com.app.fromindia.helper.PreferenceHelper.put
import com.app.fromindia.helper.PreferenceHelper.hashKey
import com.app.fromindia.helper.PreferenceHelper.loginSuccess
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class FILoginActivity : AppCompatActivity() {

    var mScreenCheck: Boolean = false
    var mActivity: Activity? = null
    var mPrefs: SharedPreferences? = null
    var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mActivity = this@FILoginActivity
        mContext = this
        mPrefs = PreferenceHelper.defaultPreference(this)
        defaultLoadLogin()
        clickListener()
        focusChangeListener()

        ///For Checking the network connection
        if (Utils.isNetworkAvailable(this)) {
        } else {
            Utils.showInterentCheckButtonAlert(mActivity!!, ALERT_FOR_NO_NETWORK_CONNECTION)
        }
    }


    private fun clickListener() {
        loginTxt.setOnClickListener {
            mScreenCheck = true;
            loginPage()
        }
        register_txt.setOnClickListener {
            mScreenCheck = false;
            signUpPage()
        }
        forgotTxt.setOnClickListener {
            mScreenCheck = true;
            moveToForgotPage()
        }
        login_btn.setOnClickListener {
            if (validation()) {
                pwdLay.error = "";
                if (Utils.isNetworkAvailable(this)) {
                    userLogin(getUserLoginValue())
                } else {
                    Utils.showInterentCheckButtonAlert(mActivity!!, ALERT_FOR_NO_NETWORK_CONNECTION)
                }
            }
        }
        loginPwd.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (validation()) {
                    pwdLay.error = "";
                    if (Utils.isNetworkAvailable(this)) {
                        userLogin(getUserLoginValue())
                    } else {
                        Utils.showInterentCheckButtonAlert(mActivity!!, ALERT_FOR_NO_NETWORK_CONNECTION)
                    }
                }
                true
            } else {
                false
            }
        }

        registerBtn.setOnClickListener {
            if (signUpValidation()) {
                signUpConfirmPwdLAY.error = ""
                if (Utils.isNetworkAvailable(this)) {
                    callRegistration(getRegisterValues())
                } else {
                    Utils.showInfoToastAlert(applicationContext, ALERT_FOR_NO_NETWORK_CONNECTION)
                }
            }
        }
        signUpConfirmPwdEDT.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (signUpValidation()) {
                    pwdLay.error = "";
                    // Toast.makeText(applicationContext, "signUpConfirmPwdEDT", Toast.LENGTH_SHORT).show();
                }
                true
            } else {
                false
            }
        }

        bottomLoginbtn.setOnClickListener {
            if (mScreenCheck) {
                signUpPage()
            } else {
                loginPage()
            }
        }
    }

    private fun moveToForgotPage() {
        val intent = Intent(applicationContext, FIForgotPasswordActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToHomePage() {
        mPrefs!!.loginSuccess = true
        val intent = Intent(applicationContext, FIHomePageActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getRegisterValues(): User? {

        var aUser = User()

        aUser.firstName = FIHelper.getEditTextValue(firstNameEDT)

        aUser.lastName = FIHelper.getEditTextValue(lastNameEDT)

        aUser.email = FIHelper.getEditTextValue(signUpEmailEDT)

        aUser.password = FIHelper.getEditTextValue(signUpPwdEDT)

        aUser.confirmPassword = FIHelper.getEditTextValue(signUpConfirmPwdEDT)

        return aUser

    }

    private fun getUserLoginValue(): User? {

        var aUser = User()

        aUser.username = FIHelper.getEditTextValue(email_edt)

        aUser.password = FIHelper.getEditTextValue(loginPwd)

        return aUser

    }

    private fun getValue(): User? {
        return User()
    }

    //ToDo Go to SignUp page
    private fun signUpPage() {
        mScreenCheck = false
        emptyLoginError()
        Utils.hideKeyboard(this)
        FIHelper.changeTextColor(this, loginTxt, register_txt);
        login_lay.visibility = View.GONE;
        signup_lay.visibility = View.VISIBLE;
        loginIM.visibility = View.GONE;
        bottomLoginbtn.text = this.resources.getString(R.string.login);
        loginInfoTxt.text = this.resources.getString(R.string.already_have_acc);
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_left_side)
        signup_lay.startAnimation(animation)
    }

    //ToDo Go to Login page
    private fun loginPage() {


        mScreenCheck = true;
        emptySignUpError()
        Utils.hideKeyboard(this)
        FIHelper.changeTextColor(this, register_txt, loginTxt);
        login_lay.visibility = View.VISIBLE;
        signup_lay.visibility = View.GONE;
        loginIM.visibility = View.VISIBLE;
        bottomLoginbtn.text = this.resources.getString(R.string.register);
        loginInfoTxt.text = this.resources.getString(R.string.new_user);
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_right_side)
        login_lay.startAnimation(animation)
    }


    private fun defaultLoadLogin() {
        mScreenCheck = true;
        FIHelper.changeTextColor(this, register_txt, loginTxt)
        login_lay.visibility = View.VISIBLE
        signup_lay.visibility = View.GONE
        bottomLoginbtn.text = this.resources.getString(R.string.register)
        loginInfoTxt.text = this.resources.getString(R.string.new_user)
    }

    //ToDo To check login validation
    private fun validation(): Boolean {
        return if (FIHelper.getEditTextValue(email_edt) == "") {
            emailEdtLay.error = ALERT_ENTER_EMAIL
            false;
        } else if (!FIHelper.isValidEmail(FIHelper.getEditTextValue(email_edt))) {
            emailEdtLay.error = ALERT_ENTER_VALID_EMAIL
            false;
        } else if (FIHelper.getEditTextValue(loginPwd) == "") {
            emailEdtLay.error = ""
            pwdLay.error = ALERT_ENTER_PWD
            false;
        } else {
            true
        }
    }

    //ToDo To check registration validation
    private fun signUpValidation(): Boolean {
        return if (FIHelper.getEditTextValue(firstNameEDT) == "") {
            firstNameLAY.error = ALERT_ENTER_FIRST_NAME
            false
        } else if (FIHelper.getEditTextValue(lastNameEDT) == "") {
            firstNameLAY.error = ""
            lastNameLAY.error = ALERT_ENTER_LAST_NAME
            false
        } else if (FIHelper.getEditTextValue(signUpEmailEDT) == "") {
            lastNameLAY.error = ""
            signUpEmailLAY.error = ALERT_ENTER_EMAIL
            false
        } else if (!FIHelper.isValidEmail(FIHelper.getEditTextValue(signUpEmailEDT))) {
            signUpEmailLAY.error = ""
            signUpEmailLAY.error = ALERT_ENTER_VALID_EMAIL
            false
        } else if (FIHelper.getEditTextValue(signUpPwdEDT) == "") {
            signUpEmailLAY.error = ""
            signUpPwdLAY.error = ALERT_ENTER_PWD
            false
        } else if (FIHelper.getEditTextValue(signUpConfirmPwdEDT) == "") {
            signUpPwdLAY.error = ""
            signUpConfirmPwdLAY.error = ALERT_ENTER_CONFIRM
            false
        } else if (!FIHelper.getEditTextValue(signUpPwdEDT).equals(FIHelper.getEditTextValue(signUpConfirmPwdEDT))) {
            signUpPwdLAY.error = ""
            pwdLay.error = ALERT_ENTER_CONFIRM_MATCH
            false
        } else {
            signUpConfirmPwdLAY.error = ""
            true
        }
    }

    //Todo Set Empty error
    private fun emptySignUpError() {
        firstNameLAY.error = ""
        lastNameLAY.error = ""
        lastNameLAY.error = ""
        signUpEmailLAY.error = ""
        signUpPwdLAY.error = ""
        signUpConfirmPwdLAY.error = ""

    }

    private fun emptyLoginError() {
        emailEdtLay.error = ""
        pwdLay.error = ""

    }

    // Todo Set Focus changeListener
    private fun focusChangeListener() {
        //For Login page
        FIHelper.editErrorEmpty(email_edt, emailEdtLay)
        FIHelper.editErrorEmpty(loginPwd, pwdLay)
        //For Registration  page
        FIHelper.editErrorEmpty(firstNameEDT, firstNameLAY)
        FIHelper.editErrorEmpty(lastNameEDT, lastNameLAY)
        FIHelper.editErrorEmpty(signUpEmailEDT, signUpEmailLAY)
        FIHelper.editErrorEmpty(signUpPwdEDT, signUpPwdLAY)
        FIHelper.editErrorEmpty(signUpConfirmPwdEDT, signUpConfirmPwdLAY)

    }

    public override fun onDestroy() {
        super.onDestroy()
    }


    private fun callRegistration(aRegisterValue: User?) {

        progressLAY.visibility = View.VISIBLE

        //...............................................................//

        val aOuterJson = JSONObject()
        val aInnerJson = JSONObject()
        aInnerJson.put(FIRST_NAME, aRegisterValue!!.firstName)
        aInnerJson.put(LAST_NAME, aRegisterValue!!.lastName)
        aInnerJson.put(EMAIL, aRegisterValue!!.email)
        aInnerJson.put(PASSWORD, aRegisterValue!!.password)
        aInnerJson.put(STORE_ID, STORE_ID_VALUE)
        aInnerJson.put(IS_SUBSCRIBED, IS_SUB_SCRIBED_VALUE) // Static Value
        aInnerJson.put(GENDER, "1") // Static value set --Right we don't have any option for selecting Gender
        aOuterJson.put(PARAMETERS, aInnerJson)
        Log.e("SIGNUP", aOuterJson.toString())

        //...............................................................//

        var apiServices = APIClient.getClient()!!.create(APIInterface::class.java)

        val aRegCall = apiServices.userRegistration(Utils.getJsonObjectValue(aOuterJson.toString()))

        aRegCall.enqueue(object : Callback<ArrayList<LoginResponse>?> {
            override fun onResponse(call: Call<ArrayList<LoginResponse>?>, response: Response<ArrayList<LoginResponse>?>) {
                val aLoginResponse: ArrayList<LoginResponse>? = response.body()

                Log.e("PRINT PREEFER", call.request().url().toString())

                Log.e("ErrorBody", response.message())

                if (response.isSuccessful && aLoginResponse != null) {

                    progressLAY.visibility = View.GONE

                    val aCustomerValue: List<Customer>? = aLoginResponse[0]!!.data!!.customer

                    for (k in aCustomerValue!!.indices) {

                        if (aCustomerValue[k].status == SUCCESS) {

                            var aAlertMsg: String? = aCustomerValue[k].message

                            Utils.showSuccessToastAlert(mActivity!!, aAlertMsg!!)

                            moveToHomePage()

                        } else {

                            var aAlertMsg: String? = aCustomerValue[k].message

                            // Utils.showToastAlert(mActivity!!, aAlertMsg!!)

                            Utils.showSingleButtonAlert(mActivity!!, aAlertMsg!!)

                        }
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<ArrayList<LoginResponse>??>, t: Throwable) {
                progressLAY.visibility = View.GONE
                Utils.showSingleButtonAlert(mActivity!!, t.toString()!!)

            }
        })
    }

    /**
     * User Login
     */
    private fun userLogin(aRegisterValue: User?) {


        //...............................................................//

        val aLoginJson = JSONObject()

        val aInnerJson = JSONObject()

        try {
            aInnerJson.put(EMAIL, aRegisterValue!!.username)
            aInnerJson.put(PASSWORD, aRegisterValue!!.password)
            aInnerJson.put(STORE_ID, STORE_ID_VALUE)
        } catch (e: JSONException) { // TODO Auto-generated catch block
            e.printStackTrace()
        }

        aLoginJson.put(PARAMETERS, aInnerJson)

        Log.e("PostReq_Login", aLoginJson.toString())

        //...............................................................//

        progressLAY.visibility = View.VISIBLE

        var apiServices = APIClient.getClient()!!.create(APIInterface::class.java)

        val aRegCall = apiServices.userLogin(Utils.getJsonObjectValue(aLoginJson.toString()))

        aRegCall.enqueue(object : Callback<ArrayList<LoginResponse>?> {

            override fun onResponse(call: Call<ArrayList<LoginResponse>?>, response: Response<ArrayList<LoginResponse>?>) {

                val aLoginResponse: ArrayList<LoginResponse>? = response.body()

                val res = response.body().toString()

                Log.e("PRINT PREEFER", call.request().url().toString())

                Log.e("ErrorBody", response.message())

                progressLAY.visibility = View.GONE

                if (response.isSuccessful && aLoginResponse != null) {

                    val aCustomerValue: List<Customer>? = aLoginResponse[0]!!.data!!.customer

                    for (k in aCustomerValue!!.indices) {

                        //Storing Customer ID and Hash key

                        mPrefs!!.userId = aCustomerValue[k].customerId

                        mPrefs!!.hashKey = aCustomerValue[k].hash

                        var aAlertMsg: String? = aCustomerValue[k].message


                        if (aCustomerValue[k].status == SUCCESS) {

                            moveToHomePage()

                        } else {

                            //    var aAlertMsg: String? = aCustomerValue[k].message

                            Utils.showSingleButtonAlert(mActivity!!, aAlertMsg!!)
                        }
                    }

                    Log.e("responseString", Gson().toJson(res))


                } else {
                }
            }

            override fun onFailure(call: Call<ArrayList<LoginResponse>?>, t: Throwable) {
                Utils.showSingleButtonAlert(mActivity!!, t.toString()!!)
                progressLAY.visibility = View.GONE


            }
        })

    }

    fun showAlert() {
    }
}