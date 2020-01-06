package com.app.fromindia.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;

import com.app.fromindia.R;
import com.app.fromindia.databinding.ActivityLoginBinding;
import com.app.fromindia.helper.CommonValues;
import com.app.fromindia.utils.FIHelper;
import com.google.android.material.textfield.TextInputEditText;

public class FILoginActivity extends AppCompatActivity implements CommonValues {

    private TextInputEditText mEmailEDT, mPwdEDT, mFirstNameEDT, mLastNameEDT, mSignUpEmailEDT, mSignUpPwdEDT, mSignUpConfirmPwdEDT;
    private AppCompatTextView mLoginTxt, mRegTxt;
    private RelativeLayout mLoginLay, mSignUpLay;
    private Activity mContext;
    private ActivityLoginBinding mActivityBinding;
    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login);
        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //  mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //mView = mActivityBinding.getRoot();
        mContext = FILoginActivity.this;
        // classInitialization();
    }

    private void classInitialization() {
       /* mEmailEDT = (TextInputEditText) findViewById(R.id.email_edt);
        mPwdEDT = (TextInputEditText) findViewById(R.id.pwd_edt);
        mFirstNameEDT = (TextInputEditText) findViewById(R.id.firstName_EDT);
        mLastNameEDT = (TextInputEditText) findViewById(R.id.lastName_EDT);
        mSignUpEmailEDT = (TextInputEditText) findViewById(R.id.signUp_email_EDT);
        mSignUpPwdEDT = (TextInputEditText) findViewById(R.id.signUp_pwd_EDT);
        mSignUpConfirmPwdEDT = (TextInputEditText) findViewById(R.id.signUp_confirm_pwd_EDT);
        mLoginTxt = (AppCompatTextView) findViewById(R.id.login_txt);
        mRegTxt = (AppCompatTextView) findViewById(R.id.register_txt);
        mLoginLay = (RelativeLayout) findViewById(R.id.login_lay);
        mSignUpLay = (RelativeLayout) findViewById(R.id.signup_lay);*/
        clickListener();
    }

    private void clickListener() {
        mActivityBinding.loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FIHelper.changeTextColor(mContext, mRegTxt, mLoginTxt);
                mLoginLay.setVisibility(View.VISIBLE);
                mSignUpLay.setVisibility(View.GONE);
                Animation RightSwipe = AnimationUtils.loadAnimation(mContext, R.anim.anim_right_side);
                mLoginLay.startAnimation(RightSwipe);
            }
        });
        mActivityBinding.registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FIHelper.changeTextColor(mContext, mLoginTxt, mRegTxt);
                mLoginLay.setVisibility(View.GONE);
                mSignUpLay.setVisibility(View.VISIBLE);
                Animation RightSwipe = AnimationUtils.loadAnimation(mContext, R.anim.anim_left_side);
                mSignUpLay.startAnimation(RightSwipe);
            }
        });


    }

}
