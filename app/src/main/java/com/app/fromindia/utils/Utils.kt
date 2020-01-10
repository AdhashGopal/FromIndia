package com.app.fromindia.utils

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText


public object Utils {

    fun setFontStyle(aContext: Context): Typeface? {
        return Typeface.createFromAsset(aContext.assets, "fonts/Roboto-Medium.ttf")
    }

    fun isNetworkAvailable(aContext: Context): Boolean {
        val connectivityManager = aContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


    fun hideKeyboard(activity: Activity) {

        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)


    }

    fun getEditTextValue(aEditText: TextInputEditText): String? {
        return aEditText.text.toString().trim { it <= ' ' }
    }
}