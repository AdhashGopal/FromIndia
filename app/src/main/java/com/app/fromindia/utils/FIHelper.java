package com.app.fromindia.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.AppCompatTextView;

import com.app.fromindia.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FIHelper {

    public static void changeTextColor(Activity aContext, AppCompatTextView aRegTxt, AppCompatTextView aLoginTxt) {
        aRegTxt.setTextColor(aContext.getResources().getColor(R.color.black));
        aLoginTxt.setTextColor(aContext.getResources().getColor(R.color.red));
        aLoginTxt.setTypeface(Typeface.DEFAULT_BOLD);
        // aLoginTxt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    public static String getEditTextValue(TextInputEditText aEditText) {
        return aEditText.getText().toString().trim();
    }

    public static boolean isValidEmail(String email) {
        String expression = "^[\\w\\.]+@([\\w]+\\.)+[A-Z]{2,7}$";
        CharSequence inputString = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static void editErrorEmpty(TextInputEditText aEditText, final TextInputLayout aInputLayout) {
        aEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    aInputLayout.setError("");
                }

            }
        });
    }


    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private static void showKeyboard(Activity activity) {
        View v = activity.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null && v != null;
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }
}
