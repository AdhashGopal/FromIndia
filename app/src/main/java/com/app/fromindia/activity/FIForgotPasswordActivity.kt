package com.app.fromindia.activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.app.fromindia.R
import com.app.fromindia.helper.CommonValues
import com.app.fromindia.helper.CommonValues.ALERT_ENTER_VALID_EMAIL
import com.app.fromindia.helper.CommonValues.ALERT_FOR_NO_NETWORK_CONNECTION
import com.app.fromindia.helper.PreferenceHelper
import com.app.fromindia.helper.PreferenceHelper.hashKey
import com.app.fromindia.helper.PreferenceHelper.userId
import com.app.fromindia.model.Customer
import com.app.fromindia.model.LoginResponse
import com.app.fromindia.model.User
import com.app.fromindia.retrofit.APIClient
import com.app.fromindia.retrofit.APIInterface
import com.app.fromindia.utils.FIHelper
import com.app.fromindia.utils.Utils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_forgot_pwd.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FIForgotPasswordActivity : AppCompatActivity() {
    var mPrefs: SharedPreferences? = null
    var mActivity: Activity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pwd)
        mActivity = this@FIForgotPasswordActivity
        mPrefs = PreferenceHelper.defaultPreference(this)
        FIHelper.editErrorEmpty(forgetEmailEDT, forgetEmailEdtLay)

        ///For Checking the network connection
        if (Utils.isNetworkAvailable(this)) {
        } else {
            Utils.showInterentCheckButtonAlert(mActivity!!, ALERT_FOR_NO_NETWORK_CONNECTION)
        }

        sendBtn.setOnClickListener {
            resetPassword()
        }

        forgetEmailEDT.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                resetPassword()
                true
            } else {
                false
            }
        }
    }

    private fun resetPassword() {
        if (validation()) {
            forgetEmailEdtLay.error = "";
            if (Utils.isNetworkAvailable(this)) {
                forgotPassword(getUserLoginValue())
            } else {
                Utils.showInfoToastAlert(applicationContext, ALERT_FOR_NO_NETWORK_CONNECTION)
            }
        }
    }

    //ToDo To check registration validation
    private fun validation(): Boolean {
        return if (FIHelper.getEditTextValue(forgetEmailEDT) == "") {
            forgetEmailEdtLay.error = CommonValues.ALERT_ENTER_EMAIL
            false
        } else if (!FIHelper.isValidEmail(FIHelper.getEditTextValue(forgetEmailEDT))) {
            forgetEmailEdtLay.error = ""
            forgetEmailEdtLay.error = CommonValues.ALERT_ENTER_VALID_EMAIL
            false
        } else {
            forgetEmailEdtLay.error = ""
            true
        }
    }


    private fun getUserLoginValue(): User? {

        var aUser = User()

        aUser.email = FIHelper.getEditTextValue(forgetEmailEDT)

        return aUser

    }

    public override fun onDestroy() {

        super.onDestroy()
    }

    override fun onBackPressed() {
        moveToLoginPage()
    }


    private fun forgotPassword(aRegisterValue: User?) {

        val aOuterJson = JSONObject()

        val aInnerJson = JSONObject()

        try {
            aInnerJson.put(CommonValues.EMAIL, aRegisterValue!!.email)
            aInnerJson.put(CommonValues.STORE_ID, CommonValues.STORE_ID_VALUE)
        } catch (e: JSONException) { // TODO Auto-generated catch block
            e.printStackTrace()
        }

        aOuterJson.put(CommonValues.PARAMETERS, aInnerJson)

        Log.e("PostReq_Login", aOuterJson.toString())

        forgotProgressLAY.visibility = View.VISIBLE

        var apiServices = APIClient.getClient()!!.create(APIInterface::class.java)

        val aRegCall = apiServices.userForgotPassword(Utils.getJsonObjectValue(aOuterJson.toString()))

        aRegCall.enqueue(object : Callback<ArrayList<LoginResponse>?> {

            override fun onResponse(call: Call<ArrayList<LoginResponse>?>, response: Response<ArrayList<LoginResponse>?>) {

                val aLoginResponse: ArrayList<LoginResponse>? = response.body()

                val res = response.body().toString()

                Log.e("PRINT PREEFER", call.request().url().toString())

                Log.e("ErrorBody", response.message())

                if (response.isSuccessful && aLoginResponse != null) {

                    forgotProgressLAY.visibility = View.GONE

                    val aCustomerValue: List<Customer>? = aLoginResponse[0]!!.data!!.customer

                    for (k in aCustomerValue!!.indices) {

                        //Storing Customer ID and Hash key

                        var aAlertMsg: String? = aCustomerValue[k].message

                        aAlertMsg = aAlertMsg?.replace("\\s+", " ")

                        Utils.showSingleButtonAlert(mActivity!!, aAlertMsg!!)


                        /* if (aCustomerValue[k].status == CommonValues.SUCCESS) {

                             var aAlertMsg: String? = aCustomerValue[k].message

                             Utils.showToastAlert(mActivity!!, aAlertMsg!!)

                         } else {

                             var aAlertMsg: String? = aCustomerValue[k].message

                             Utils.showToastAlert(mActivity!!, aAlertMsg!!)
                         }*/
                    }

                } else {
                }
            }

            override fun onFailure(call: Call<ArrayList<LoginResponse>?>, t: Throwable) {
                forgotProgressLAY.visibility = View.GONE
                Utils.showSingleButtonAlert(mActivity!!, t.toString()!!)

            }
        })

    }


    private fun moveToLoginPage() {
        val aIntent = Intent(this, FILoginActivity::class.java)
        aIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(aIntent)
        finish()
    }
}