package com.app.fromindia.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.app.fromindia.R

class FIFragmentManager(val aContext: FragmentActivity?) {


    var myLastTag = ""


    fun updateContent(aFragment: Fragment, tag: String, aBundle: Bundle?) {
        try {
            Log.e("TAG Screen name", tag)
            // Initialise Fragment Manager
            val aFragmentManager: FragmentManager = aContext!!
                    .supportFragmentManager
            // Initialise Fragment Transaction
            val aTransaction = aFragmentManager
                    .beginTransaction()
            aFragment.arguments = aBundle
            aTransaction.add(R.id.container, aFragment, tag)
            // add the tag to the backstack
            aTransaction.addToBackStack(tag)
            // Commit the Fragment transaction
            aTransaction.commit()
            myLastTag = tag
            Log.i("LastTag", myLastTag)
        } catch (aError: Exception) {
            aError.printStackTrace()
        }
    }

    fun updateContentAnimation(aFragment: Fragment, tag: String, aBundle: Bundle?) {
        try {
            Log.e("TAG Screen name", tag)
            // Initialise Fragment Manager
            val aFragmentManager: FragmentManager = aContext!!
                    .supportFragmentManager
            // Initialise Fragment Transaction
            val aTransaction = aFragmentManager
                    .beginTransaction()

            /* aTransaction.setCustomAnimations(R.anim.anim_enter_from_right, R.anim.anim_exit_to_right,
                     R.anim.anim_enter_from_left, R.anim.anim_exit_to_left);*/

            aFragment.arguments = aBundle

            aTransaction.add(R.id.container, aFragment, tag)

            // add the tag to the backstack
            aTransaction.addToBackStack(tag)
            // Commit the Fragment transaction
            aTransaction.commit()

            myLastTag = tag
            Log.i("LastTag", myLastTag)
        } catch (aError: Exception) {
            aError.printStackTrace()
        }
    }

    fun backPress() {
        try {
            val aFragmentManager = aContext!!
                    .supportFragmentManager
            if (aFragmentManager.backStackEntryCount > 1) {
                aFragmentManager.popBackStack()
                aFragmentManager.executePendingTransactions()
                Log.d("TAG", "CURRENT FRAGMENT BACK STACK CLASS "
                        + aFragmentManager
                        .getBackStackEntryAt(
                                aFragmentManager
                                        .backStackEntryCount - 1)
                        .name)
                Log.e("CURRENTTAGFRAGMENT", getActiveFragmentTAG())
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
    //Get the Current TAG

    //Get the Current TAG
    private fun getActiveFragmentTAG(): String? {
        return if (aContext!!.supportFragmentManager.backStackEntryCount == 0) {
            null
        } else aContext!!.supportFragmentManager.getBackStackEntryAt(aContext!!.supportFragmentManager.backStackEntryCount - 1).name
    }

    fun clearAllFragments() {
        try {
            val aFragmentManager: FragmentManager = aContext!!
                    .supportFragmentManager
            aFragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } catch (e: java.lang.Exception) { // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    fun getBackStackCount(): Int {
        val aFragmentManager: FragmentManager = aContext!!
                .getSupportFragmentManager()
        return aFragmentManager.backStackEntryCount
    }
}