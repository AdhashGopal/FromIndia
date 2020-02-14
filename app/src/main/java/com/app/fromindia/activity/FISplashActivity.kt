package com.app.fromindia.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.fromindia.R
import com.app.fromindia.helper.PreferenceHelper
import com.app.fromindia.helper.PreferenceHelper.loginSuccess

class FISplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds
    var mPrefs: SharedPreferences? = null

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            if (mPrefs!!.loginSuccess) {
                moveToHomePage()
            } else {
                moveToLoginPage()
            }
        }
    }

    private fun moveToLoginPage() {
        val intent = Intent(applicationContext, FILoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToHomePage() {
        val intent = Intent(applicationContext, FIHomePageActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mPrefs = PreferenceHelper.defaultPreference(this)

        //Log.e("DENSITY", "" + this.resources.displayMetrics.density)

        //Initialize the Handler
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}