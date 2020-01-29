package com.app.fromindia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.fromindia.R
import com.app.fromindia.helper.CommonValues.*
import com.app.fromindia.model.User
import com.app.fromindia.retrofit.APIClient
import com.app.fromindia.retrofit.APIInterface
import com.app.fromindia.utils.FIHelper
import com.app.fromindia.utils.Utils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FILoginActivity : AppCompatActivity() {

    var mScreenCheck: Boolean = false
    var mActivity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mActivity = this@FILoginActivity
        defaultLoadLogin()
        clickListener()
        focusChangeListener()
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
                // Toast.makeText(applicationContext, "Heylogin_btn", Toast.LENGTH_SHORT).show();

                //FIHomePageActivity

                /* val intent = Intent(applicationContext, FIHomePageActivity::class.java)
                 startActivity(intent)
                 finish()*/
                if (Utils.isNetworkAvailable(this)) {
                    userLogin(getUserLoginValue())

                } else {
                    Toast.makeText(applicationContext, "Pls check ur nw", Toast.LENGTH_SHORT).show();

                }
            }
        }
        pwd_edt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (signUpValidation()) {
                    pwdLay.error = "";
                    // Toast.makeText(applicationContext, "pwd_edt", Toast.LENGTH_SHORT).show();
                }
                true
            } else {
                false
            }
        }

        registerBtn.setOnClickListener {
            if (signUpValidation()) {

                callRegistration(getRegisterValues())
                pwdLay.error = "";
                Toast.makeText(applicationContext, "Signup", Toast.LENGTH_SHORT).show();
            }
        }
        signUpConfirmPwdEDT.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (signUpValidation()) {
                    pwdLay.error = "";
                    Toast.makeText(applicationContext, "signUpConfirmPwdEDT", Toast.LENGTH_SHORT).show();
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

        aUser.password = FIHelper.getEditTextValue(pwd_edt)

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
        } else if (FIHelper.getEditTextValue(pwd_edt) == "") {
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
        FIHelper.editErrorEmpty(pwd_edt, pwdLay)
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

        //...............................................................//

        val aInnerJson = JSONObject()
        val aCustomerJson = JSONObject()
        aCustomerJson.put(EMAIL, aRegisterValue!!.email)
        aCustomerJson.put(FIRST_NAME, aRegisterValue!!.firstName)
        aCustomerJson.put(LAST_NAME, aRegisterValue!!.lastName)
        aInnerJson.put(CUSTOMER, aCustomerJson)
        aInnerJson.put(PASSWORD, aRegisterValue!!.password)
        Log.e("SIGNUP", aInnerJson.toString())

        //...............................................................//

        var apiServices = APIClient.getClient()!!.create(APIInterface::class.java)

        val aRegCall = apiServices.createUser(Utils.getJsonObjectValue(aInnerJson.toString()))

        aRegCall.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                val aConversationBody: ResponseBody? = response.body()
                Log.e("PRINT PREEFER", call.request().url().toString())
                Log.e("ErrorBody", response.message())
                if (response.isSuccessful && aConversationBody != null) {

                } else {
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
            }
        })
    }

    /**
     * User Login
     */
    private fun userLogin(aRegisterValue: User?) {
        //To Json Body
        //...............................................................//
        val aJsonObject = JSONObject()
        aJsonObject.put(USERNAME, aRegisterValue!!.username)
        aJsonObject.put(PASSWORD, aRegisterValue!!.password)
        //...............................................................//

        progressLAY.visibility = View.VISIBLE

        Log.e("LOGIN JSON PARAM", aJsonObject.toString())
        var apiServices = APIClient.getClient()!!.create(APIInterface::class.java)
        val aRegCall = apiServices.userLogin(Utils.getJsonObjectValue(aJsonObject.toString()))
        aRegCall.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                val aConversationBody: ResponseBody? = response.body()

                val res = response.body().toString()

                Log.e("PRINT PREEFER", call.request().url().toString())
                Log.e("ErrorBody", response.message())
                if (response.isSuccessful && aConversationBody != null) {

                    Log.e("responseString", Gson().toJson(res))

                    progressLAY.visibility = View.GONE

                } else {
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
            }
        })

    }
}