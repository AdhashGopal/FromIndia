package com.app.fromindia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.fromindia.R
import com.app.fromindia.helper.CommonValues.*
import com.app.fromindia.utils.FIHelper
import com.app.fromindia.utils.Utils
import kotlinx.android.synthetic.main.activity_login.*


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

        login_btn.setOnClickListener {
            if (validation()) {
                pwdLay.error = "";
                // Toast.makeText(applicationContext, "Heylogin_btn", Toast.LENGTH_SHORT).show();

                //FIHomePageActivity

                val intent = Intent(applicationContext, FIHomePageActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        pwd_edt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (signUpValidation()) {
                    pwdLay.error = "";
                    Toast.makeText(applicationContext, "pwd_edt", Toast.LENGTH_SHORT).show();
                }
                true
            } else {
                false
            }
        }

        registerBtn.setOnClickListener {
            if (signUpValidation()) {
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


}