package com.app.fromindia.fragments.slideMenu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity
import com.app.fromindia.activity.FILoginActivity
import com.app.fromindia.adapter.FICustomAdapter
import com.app.fromindia.adapter.FIMyAccountAdapter
import com.app.fromindia.fragments.FIEditAccountFragment
import com.app.fromindia.fragments.FIFragmentManager
import com.app.fromindia.helper.CommonValues
import com.app.fromindia.helper.CommonValues.*
import com.app.fromindia.helper.PreferenceHelper
import com.app.fromindia.helper.PreferenceHelper.loginSuccess
import com.app.fromindia.model.DynamicMenu
import kotlinx.android.synthetic.main.activity_main2.*

class FIMyAccountFragment : Fragment(), CommonValues {

    private var TAG = "FIMyAccountFragment"

    private var mProfileMenuList: RecyclerView? = null

    private var mLogOutLAY: RelativeLayout? = null

    private var mEditAccountIM: AppCompatImageView? = null

    private var mFragmentManager: FIFragmentManager? = null

    var mPrefs: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment


        val aView: View = inflater.inflate(R.layout.fragment_my_account, container,
                false)

        classWidgets(aView);

        return aView
    }

    private fun classWidgets(aView: View) {

        mPrefs = PreferenceHelper.defaultPreference(activity!!)

        mFragmentManager = FIFragmentManager(activity)

        mProfileMenuList = aView.findViewById(R.id.profileMenuRV) as RecyclerView

        mLogOutLAY = aView.findViewById(R.id.logoutLAY) as RelativeLayout

        mEditAccountIM = aView.findViewById(R.id.editProfileIM) as AppCompatImageView

        toLoadDynamicValue()

        clickListener()
    }

    private fun clickListener() {
        mEditAccountIM!!.setOnClickListener {
            mFragmentManager!!.updateContent(FIEditAccountFragment(), "", null)
        }


        mLogOutLAY!!.setOnClickListener {


            moveToLoginPage()
        }
    }

    private fun moveToLoginPage() {
        mPrefs!!.loginSuccess = false
        val aIntent = Intent(activity, FILoginActivity::class.java)
        aIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(aIntent)
        activity!!.finish()
    }

    private fun toLoadDynamicValue() {

        val aStaticMenu = ArrayList<DynamicMenu>()

        val aMenu1 = DynamicMenu()

        aMenu1.menuName = MENU1_ORDER

        aMenu1.menuImage = R.drawable.ic_my_orders

        aStaticMenu.add(aMenu1)

        val aMenu2 = DynamicMenu()

        aMenu2.menuName = MENU6_TRACK_MY_ORDER

        aMenu2.menuImage = R.drawable.ic_track_my_order

        aStaticMenu.add(aMenu2)

        val aMenu3 = DynamicMenu()

        aMenu3.menuName = MENU2_WISH_LIST

        aMenu3.menuImage = R.drawable.ic_my_wish_list

        aStaticMenu.add(aMenu3)

        val aMenu4 = DynamicMenu()

        aMenu4.menuName = MENU7_MY_ADDRESS

        aMenu4.menuImage = R.drawable.ic_my_address

        aStaticMenu.add(aMenu4)

        val aMenu5 = DynamicMenu()

        aMenu5.menuName = MENU8_ACCOUNT_INFORMATION

        aMenu5.menuImage = R.drawable.ic_my_account

        aStaticMenu.add(aMenu5)

        val aMenu6 = DynamicMenu()

        aMenu6.menuName = MENU9_STORED_PAYMENT

        aMenu6.menuImage = R.drawable.ic_stored_payment

        aStaticMenu.add(aMenu6)

        val aMenu7 = DynamicMenu()

        aMenu7.menuName = MENU10_BILLING_AGREEMENT

        aMenu7.menuImage = R.drawable.ic_billing_agreements

        aStaticMenu.add(aMenu7)

        val aMenu8 = DynamicMenu()

        aMenu8.menuName = MENU11_NEWS_LETTER_SUB

        aMenu8.menuImage = R.drawable.ic_newsletter

        aStaticMenu.add(aMenu8)

        val aMenu9 = DynamicMenu()

        aMenu9.menuName = MENU5_POINTS_REWARDS

        aMenu9.menuImage = R.drawable.ic_menu_rewards

        aStaticMenu.add(aMenu9)

        val adapter = FIMyAccountAdapter(aStaticMenu, activity)

        mProfileMenuList!!.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        mProfileMenuList!!.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        (activity as FIHomePageActivity).hideBottomTab()
        (activity as FIHomePageActivity).showToolbarHead()

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as FIHomePageActivity).showBottomTab()
        (activity as FIHomePageActivity).hideToolbarHead()

    }

}