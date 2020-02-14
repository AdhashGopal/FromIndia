package com.app.fromindia.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

object PreferenceHelper {

    val USER_ID = "CUSTOMER_ID"

    val CU_HASH_KEY = "HASHKEY"

    val SPLASH_KEY = "SPLASH"

    fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    inline fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val key = pair.first
        val value = pair.second
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in SharedPreferences")
        }
    }

    var SharedPreferences.userId
        get() = getString(USER_ID, "")
        set(value) {
            editMe {
                it.putString(USER_ID, value)
            }
        }

    var SharedPreferences.hashKey
        get() = getString(CU_HASH_KEY, "")
        set(value) {
            editMe {
                //it.put(USER_PASSWORD to value)
                it.putString(CU_HASH_KEY, value)
            }
        }

    var SharedPreferences.loginSuccess
        get() = getBoolean(SPLASH_KEY, false)
        set(value) {
            editMe {
                //it.put(USER_PASSWORD to value)
                it.putBoolean(SPLASH_KEY, value)
            }
        }


    var SharedPreferences.saveValue
        get() = getString(CU_HASH_KEY, "")
        set(value) {
            editMe {
                //it.put(USER_PASSWORD to value)
                it.putString(CU_HASH_KEY, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                /*it.remove(USER_ID)
                it.remove(USER_PASSWORD)*/
                it.clear()
            }
        }
}