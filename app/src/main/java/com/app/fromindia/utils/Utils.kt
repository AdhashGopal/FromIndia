package com.app.fromindia.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.os.Build
import android.text.Html
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.app.fromindia.R
import com.app.fromindia.helper.CommonValues.APP_NAME
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import com.google.gson.JsonParser


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

    /**
     * Get Json object value
     */
    fun getJsonObjectValue(aValue: String): JsonObject? {
        val aJsonParser = JsonParser()
        return aJsonParser.parse(aValue) as JsonObject
    }

    fun setTextValue(aTextView: TextView) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            aTextView.text = Html.fromHtml("<h4><span style=\"text-decoration: underline;\"><strong>Features:</strong></span></h4><ol>\r\n <li>Brand: Himalaya</li>\r\n <li>Product Type: Baby Gift(Osl) 1 Box</li>\r\n <li>Box contains: Baby gift box (Osl) 1 of baby products</li>\r\n <li>Package Quantity: 1</li>\r\n <li>Benefits: This baby gift set is a perfect present for baby showers or naming ceremonies</li>\r\n </ol>", Html.FROM_HTML_MODE_COMPACT);
        } else {
            aTextView.text = Html.fromHtml("<h4><span style=\"text-decoration: underline;\"><strong>Features:</strong></span></h4><ol>\r\n <li>Brand: Himalaya</li>\r\n <li>Product Type: Baby Gift(Osl) 1 Box</li>\r\n <li>Box contains: Baby gift box (Osl) 1 of baby products</li>\r\n <li>Package Quantity: 1</li>\r\n <li>Benefits: This baby gift set is a perfect present for baby showers or naming ceremonies</li>\r\n </ol>");
        }
    }


    fun showSuccessToastAlert(aContext: Context, aMsg: String) {
        Toast.makeText(aContext, aMsg, Toast.LENGTH_SHORT).show()
    }

    fun showInfoToastAlert(aContext: Context, aMsg: String) {
        Toast.makeText(aContext, aMsg, Toast.LENGTH_SHORT).show()

    }

    fun showSingleButtonAlert(aContext: Activity, aMsg: String) {


        val aDialogBuilder = AlertDialog.Builder(aContext)
        // set message of alert dialog
        aDialogBuilder.setMessage(aMsg)
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                })
        // create dialog box
        val alert = aDialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(APP_NAME)
//show dialog
        if (!aContext.isFinishing) {
            alert.show()
        }
    }

    fun showInterentCheckButtonAlert(aContext: Activity, aMsg: String) {
        val aDialogBuilder = AlertDialog.Builder(aContext)
        // set message of alert dialog
        aDialogBuilder.setMessage(aMsg)
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Goto Settings ", DialogInterface.OnClickListener { dialog, id ->
                    val aIntent = Intent(android.provider.Settings.ACTION_SETTINGS)
                    aContext.startActivity(aIntent)

                })

        /*   .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
               dialog.dismiss()
           })*/
        // create dialog box
        val alert = aDialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(APP_NAME)
        // show alert dialog
        alert.show()

        val aNegativeBTN: Button = alert.getButton(DialogInterface.BUTTON_NEGATIVE)
        val aPositiveBTN: Button = alert.getButton(DialogInterface.BUTTON_POSITIVE)
        aPositiveBTN.setTextColor(ContextCompat.getColor(aContext, R.color.app_color))
        aNegativeBTN.setTextColor(ContextCompat.getColor(aContext, R.color.app_secondary_color))
    }
}