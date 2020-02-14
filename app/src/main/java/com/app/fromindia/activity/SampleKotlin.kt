package com.app.fromindia.activity

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.helper.PreferenceHelper
import com.app.fromindia.helper.PreferenceHelper.userId
import com.app.fromindia.helper.PreferenceHelper.put


class SampleKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.constrainlayout_sample)
        //    setContentView(R.layout.inflate_notification_lay)
        // dummyItem()
        val defaultPrefs = PreferenceHelper.defaultPreference(this)
        // defaultPrefs.userId = 1234
        PreferenceHelper.defaultPreference(this)

    }

    /*private fun dummyItem() {
        val users = ArrayList<MenuData>()
        //adding some dummy data to the list
        users.add(MenuData("My Orders", "1"))
        users.add(MenuData("My Wish list", "2"))
        users.add(MenuData("My Account", "3"))
        users.add(MenuData("Notifications", "4"))
        users.add(MenuData("My Points and Rewards", "5"))
        val adapter = FICustomAdapter(users)
        sampleRC.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        sampleRC.adapter = adapter
    }*/
}