package com.app.fromindia.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.adapter.FICustomAdapter
import com.app.fromindia.model.MenuData
import kotlinx.android.synthetic.main.samplerclay.*

class SampleKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.samplerclay)

        dummyItem()

    }

    private fun dummyItem() {
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
    }
}