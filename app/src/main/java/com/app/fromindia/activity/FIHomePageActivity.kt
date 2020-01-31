package com.app.fromindia.activity

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.adapter.FICustomAdapter
import com.app.fromindia.adapter.FIStaticMenuAdapter
import com.app.fromindia.fragments.FIFragmentManager
import com.app.fromindia.fragments.FIHomePageFragment
import com.app.fromindia.fragments.SampleFragment
import com.app.fromindia.fragments.bottomMenu.FIApparelsFragment
import com.app.fromindia.fragments.bottomMenu.FIIndianCraftFragment
import com.app.fromindia.fragments.bottomMenu.FINativeSpecialFragment
import com.app.fromindia.fragments.bottomMenu.FISuperComboFragment
import com.app.fromindia.helper.CommonValues
import com.app.fromindia.helper.CommonValues.*
import com.app.fromindia.model.DynamicMenu
import com.app.fromindia.utils.FIHelper
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main2.*
import kotlinx.android.synthetic.main.content_main2.*
import kotlinx.android.synthetic.main.homeheader.*
import kotlinx.android.synthetic.main.inflate_bottom_lay.*


class FIHomePageActivity : CommonValues, AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mContext: FragmentActivity? = null

    private var mFragementManager: FIFragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mContext = this@FIHomePageActivity
        mFragementManager = FIFragmentManager(mContext)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.isDrawerIndicatorEnabled = false
        drawer_layout!!.addDrawerListener(toggle)
        toggle.syncState()

        // ToDo To set...Full width for navigation view
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val params = nav_view.layoutParams as DrawerLayout.LayoutParams
        params.width = metrics.widthPixels
        nav_view.layoutParams = params
        //.........................................................................//

        nav_view.setNavigationItemSelectedListener(this)
        clickListener()
        toLoadDynamicValue()
        toSetStaticValue()
        toClickBottomMenus()
        defaultFragment()

        //sampleLogTest()
        //dummyItem()
        //  DynamicMenu();


        // homeSearch.se(Color.BLUE); // set the hint color


    }

    private fun defaultFragment() {
        mFragementManager!!.clearAllFragments()
        mFragementManager!!.updateContent(FIHomePageFragment(), "", null)
    }

    private fun sampleLogTest() {
        val a: String? = null
        // Log.e("!!:", "" + a!!.length)
        //  print("!!:" + a!!.length) //
        val a1: String? = null
        // print("?:" + a1?.length)
        Log.e("?:", "" + a1?.length)
    }

    private fun toClickBottomMenus() {
        superComboLAY.setOnClickListener {
            mFragementManager!!.clearAllFragments()
            mFragementManager!!.updateContentAnimation(FISuperComboFragment(), "", null)
            FIHelper.changeTextImageColor(mContext, superComboIM, superComboTXT)
            FIHelper.changeTextImageRestColor(mContext, nativeSpecialIM, nativeSpecialTXT)
            FIHelper.changeTextImageRestColor(mContext, apparelsIM, apparelsTXT)
            FIHelper.changeTextImageRestColor(mContext, indianCraftIM, indianCraftTXT)

        }
        nativeSpecialLAY.setOnClickListener {
            mFragementManager!!.clearAllFragments()
            mFragementManager!!.updateContentAnimation(FINativeSpecialFragment(), "", null)
            FIHelper.changeTextImageColor(mContext, nativeSpecialIM, nativeSpecialTXT)
            FIHelper.changeTextImageRestColor(mContext, superComboIM, superComboTXT)
            FIHelper.changeTextImageRestColor(mContext, apparelsIM, apparelsTXT)
            FIHelper.changeTextImageRestColor(mContext, indianCraftIM, indianCraftTXT)
        }

        apparelsLAY.setOnClickListener {
            mFragementManager!!.clearAllFragments()
            mFragementManager!!.updateContentAnimation(FIApparelsFragment(), "", null)
            FIHelper.changeTextImageColor(mContext, apparelsIM, apparelsTXT)
            FIHelper.changeTextImageRestColor(mContext, superComboIM, superComboTXT)
            FIHelper.changeTextImageRestColor(mContext, nativeSpecialIM, nativeSpecialTXT)
            FIHelper.changeTextImageRestColor(mContext, indianCraftIM, indianCraftTXT)
        }
        indianCraftLAY.setOnClickListener {
            mFragementManager!!.clearAllFragments()
            mFragementManager!!.updateContentAnimation(FIIndianCraftFragment(), "", null)
            FIHelper.changeTextImageColor(mContext, indianCraftIM, indianCraftTXT)
            FIHelper.changeTextImageRestColor(mContext, superComboIM, superComboTXT)
            FIHelper.changeTextImageRestColor(mContext, nativeSpecialIM, nativeSpecialTXT)
            FIHelper.changeTextImageRestColor(mContext, apparelsIM, apparelsTXT)
        }
    }

    private fun toLoadDynamicValue() {

        val aStaticMenu = ArrayList<DynamicMenu>()

        val aMenu1 = DynamicMenu()

        aMenu1.menuName = "Groceries"

        aMenu1.menuImage = R.drawable.ic_groceries

        aStaticMenu.add(aMenu1)

        val aMenu2 = DynamicMenu()

        aMenu2.menuName = "Super Combo"

        aMenu2.menuImage = R.drawable.ic_super_combo

        aStaticMenu.add(aMenu2)

        val aMenu3 = DynamicMenu()

        aMenu3.menuName = "Beverage"

        aMenu3.menuImage = R.drawable.ic_beverages

        aStaticMenu.add(aMenu3)

        val aMenu4 = DynamicMenu()

        aMenu4.menuName = "Home Appliance"

        aMenu4.menuImage = R.drawable.ic_home_appliances

        aStaticMenu.add(aMenu4)

        val aMenu5 = DynamicMenu()

        aMenu5.menuName = "Sweets&Snacks"

        aMenu5.menuImage = R.drawable.ic_sweets_snacks

        aStaticMenu.add(aMenu5)

        val aMenu6 = DynamicMenu()

        aMenu6.menuName = "Organic&natural"

        aMenu6.menuImage = R.drawable.ic_organic_natural

        aStaticMenu.add(aMenu6)

        val aMenu7 = DynamicMenu()

        aMenu7.menuName = "Self care& Cosmetics"

        aMenu7.menuImage = R.drawable.ic_self_care_cosmetics

        aStaticMenu.add(aMenu7)


        val aMenu8 = DynamicMenu()

        aMenu8.menuName = "Spiritual Products "

        aMenu8.menuImage = R.drawable.ic_spiritual_products

        aStaticMenu.add(aMenu8)

        val aMenu9 = DynamicMenu()

        aMenu9.menuName = "Baby Care"

        aMenu9.menuImage = R.drawable.ic_baby_care

        aStaticMenu.add(aMenu9)

        val aMenu10 = DynamicMenu()

        aMenu10.menuName = "Cleaning&House Hold"

        aMenu10.menuImage = R.drawable.ic_cleaning_house_hold

        aStaticMenu.add(aMenu10)

        val aMenu11 = DynamicMenu()

        aMenu11.menuName = "Fitness Equipment"

        aMenu11.menuImage = R.drawable.ic_fitness_equipments

        aStaticMenu.add(aMenu11)

        val adapter = FICustomAdapter(aStaticMenu, mContext)

        navItemRC.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        navItemRC.adapter = adapter
    }

    private fun toSetStaticValue() {

        val aStaticMenu = ArrayList<DynamicMenu>()

        val aMenu1 = DynamicMenu()

        aMenu1.menuName = MENU1_ORDER

        aMenu1.menuImage = R.drawable.ic_my_orders

        aMenu1.menuID = MENU1_ID

        aStaticMenu.add(aMenu1)

        val aMenu2 = DynamicMenu()

        aMenu2.menuName = MENU2_WISH_LIST

        aMenu2.menuImage = R.drawable.ic_my_wish_list

        aMenu2.menuID = MENU2_ID

        aStaticMenu.add(aMenu2)

        val aMenu3 = DynamicMenu()

        aMenu3.menuName = MENU3_MY_ACCOUNT

        aMenu3.menuImage = R.drawable.ic_my_account

        aMenu3.menuID = MENU3_ID

        aStaticMenu.add(aMenu3)

        val aMenu4 = DynamicMenu()

        aMenu4.menuName = MENU4_NOTIFICATION

        aMenu4.menuImage = R.drawable.ic_menu_notification

        aMenu4.menuID = MENU4_ID

        aStaticMenu.add(aMenu4)

        val aMenu5 = DynamicMenu()

        aMenu5.menuName = MENU5_POINTS_REWARDS

        aMenu5.menuImage = R.drawable.ic_menu_rewards

        aMenu5.menuID = MENU5_ID

        aStaticMenu.add(aMenu5)

        val adapter = FIStaticMenuAdapter(aStaticMenu, mContext)

        navStaticRC.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        navStaticRC.adapter = adapter
    }

    private fun clickListener() {
        burgerMenu.setOnClickListener {
            openDrawer()
        }
        closeMenuIM.setOnClickListener {
            closeDrawer()
        }
        searchBurgerMenu.setOnClickListener {
            openDrawer()
        }
        homeIM.setOnClickListener { defaultFragment() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        val shareItem = menu.findItem(R.id.action_settings)
        shareItem.isVisible = false
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val aId = item.itemId
        displayScreen(aId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun displayScreen(id: Int) {
        /*  when (id) {
              R.id.nav_home -> { // Handle the camera action
                  //  Toast.makeText(applicationContext, "Home is clicked", Toast.LENGTH_SHORT).show()
                  supportFragmentManager.beginTransaction().replace(R.id.container, SampleFragment()).commit()
              }
              R.id.nav_gallery -> {
                  supportFragmentManager.beginTransaction().replace(R.id.container, SampleFragment()).commit()
              }
              R.id.nav_slideshow -> {
                  supportFragmentManager.beginTransaction().replace(R.id.container, SampleFragment()).commit()
              }
              R.id.nav_share -> {
                  supportFragmentManager.beginTransaction().replace(R.id.container, SampleFragment()).commit()
              }
              R.id.nav_share -> {
                  supportFragmentManager.beginTransaction().replace(R.id.container, SampleFragment()).commit()
              }
              R.id.nav_send -> {
                  supportFragmentManager.beginTransaction().replace(R.id.container, SampleFragment()).commit()
              }
          }*/
    }

    override fun onBackPressed() {
        Log.e("BACKCOUNT", "" + mFragementManager!!.getBackStackCount())
        if (mFragementManager!!.getBackStackCount() > 0) {
            if (mFragementManager!!.getBackStackCount() == 1) {
                if (!drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
                    mFragementManager!!.clearAllFragments()
                    openDrawer()
                } else {
                    super.onBackPressed();
                }
            } else {
                mFragementManager!!.backPress()

            }

        } else {
            if (drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
                //super.onBackPressed();
                closeDrawer()

            } else {
                //showAlertDialog()

                super.onBackPressed();

            }
        }

    }

    private fun openDrawer() {
        drawer_layout!!.openDrawer(GravityCompat.START)
    }

    private fun closeDrawer() {
        drawer_layout!!.closeDrawer(GravityCompat.START)
    }

    fun closeDrawerSetTitle(aTitle: String) {
        drawer_layout!!.closeDrawer(GravityCompat.START)
        toolbarTitle.text = aTitle
        burgerMenu.setImageResource(R.drawable.ic_keyboard_backspace)

        hideSearchHeader()
    }

    fun hideSearchHeader() {
        searchHeaderLAY.visibility = View.GONE
        homeSearch.visibility = View.GONE
        normalHeaderLAY.visibility = View.VISIBLE

    }

    fun hideNormalHeader() {
        searchHeaderLAY.visibility = View.VISIBLE
        homeSearch.visibility = View.VISIBLE
        normalHeaderLAY.visibility = View.GONE

    }

    fun hideToolbarHead() {
        mainHeaderLAY.visibility = View.GONE
    }

    fun showToolbarHead() {
        mainHeaderLAY.visibility = View.VISIBLE
    }

    fun hideBottomTab() {
        bottomTabLAY.visibility = View.GONE
    }

    fun showBottomTab() {
        bottomTabLAY.visibility = View.VISIBLE
    }


    private fun showAlertDialog() {

        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.app_name)
        //set message for alert dialog
        builder.setMessage(getString(R.string.lbl_exit_alert))
        //  builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { _, _ ->
            super.onBackPressed();

        }
        //performing negative action
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(applicationContext, "clicked No", Toast.LENGTH_LONG).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


}
