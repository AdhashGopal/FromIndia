package com.app.fromindia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity
import com.app.fromindia.adapter.FICustomAdapter
import com.app.fromindia.adapter.FIMyAccountAdapter
import com.app.fromindia.adapter.FIMyCartItemAdapter
import com.app.fromindia.adapter.FIMyOrderAdapter
import com.app.fromindia.helper.CommonValues
import com.app.fromindia.helper.CommonValues.*
import com.app.fromindia.model.DynamicMenu
import com.asksira.loopingviewpager.LoopingViewPager
import kotlinx.android.synthetic.main.activity_main2.*

class FIMyCartFragment : Fragment() {

    private var TAG = "FIMyCartFragment"

    private var mFragmentManager: FIFragmentManager? = null

    private var mBackIM: AppCompatImageView? = null

    private var mCartItemRC: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment

        val aView: View = inflater.inflate(R.layout.fragment_my_cart, container,
                false)

        classWidgets(aView);

        return aView
    }

    private fun classWidgets(aView: View) {

        mFragmentManager = FIFragmentManager(activity)

        mBackIM = aView!!.findViewById(R.id.backIm) as AppCompatImageView

        mCartItemRC = aView!!.findViewById(R.id.fragmentMyCartRV) as RecyclerView

        clickListener()

        toSetStaticValue()
    }


    private fun clickListener() {
        mBackIM!!.setOnClickListener {

            mFragmentManager!!.backPress()
        }
    }


    private fun toSetStaticValue() {

        val aStaticMenu = ArrayList<DynamicMenu>()

        val aMenu1 = DynamicMenu()

        aMenu1.menuName = CommonValues.MENU1_ORDER

        aMenu1.menuImage = R.drawable.ic_my_orders

        aMenu1.menuID = CommonValues.MENU1_ID

        aStaticMenu.add(aMenu1)

        val aMenu2 = DynamicMenu()

        aMenu2.menuName = CommonValues.MENU2_WISH_LIST

        aMenu2.menuImage = R.drawable.ic_my_wish_list

        aMenu2.menuID = CommonValues.MENU2_ID

        aStaticMenu.add(aMenu2)

        val aMenu3 = DynamicMenu()

        aMenu3.menuName = CommonValues.MENU3_MY_ACCOUNT

        aMenu3.menuImage = R.drawable.ic_my_account

        aMenu3.menuID = CommonValues.MENU3_ID

        aStaticMenu.add(aMenu3)

        val aMenu4 = DynamicMenu()

        aMenu4.menuName = CommonValues.MENU4_NOTIFICATION

        aMenu4.menuImage = R.drawable.ic_menu_notification

        aMenu4.menuID = CommonValues.MENU4_ID

        aStaticMenu.add(aMenu4)

        val aMenu5 = DynamicMenu()

        aMenu5.menuName = CommonValues.MENU5_POINTS_REWARDS

        aMenu5.menuImage = R.drawable.ic_menu_rewards

        aMenu5.menuID = CommonValues.MENU5_ID

        aStaticMenu.add(aMenu5)

        val adapter = FIMyCartItemAdapter(aStaticMenu, activity)

        mCartItemRC!!.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        mCartItemRC!!.adapter = adapter
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