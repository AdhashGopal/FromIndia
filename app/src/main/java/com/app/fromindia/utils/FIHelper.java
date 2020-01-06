package com.app.fromindia.utils;

import android.app.Activity;
import android.graphics.Paint;

import androidx.appcompat.widget.AppCompatTextView;

import com.app.fromindia.R;

public class FIHelper {

    public static void changeTextColor(Activity aContext, AppCompatTextView aRegTxt, AppCompatTextView aLoginTxt) {
        aRegTxt.setTextColor(aContext.getResources().getColor(R.color.black));
        aLoginTxt.setTextColor(aContext.getResources().getColor(R.color.red));
        aLoginTxt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

    }
}
