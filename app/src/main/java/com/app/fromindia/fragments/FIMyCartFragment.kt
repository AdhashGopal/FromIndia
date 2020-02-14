package com.app.fromindia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
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

    private var mAmtBtn: AppCompatButton? = null


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

        mAmtBtn = aView!!.findViewById(R.id.addToCatBtn) as AppCompatButton

        normalFunction()

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

        aMenu1.price = "40"


        aStaticMenu.add(aMenu1)

        val aMenu2 = DynamicMenu()

        aMenu2.menuName = CommonValues.MENU2_WISH_LIST

        aMenu2.menuImage = R.drawable.ic_my_wish_list

        aMenu2.menuID = CommonValues.MENU2_ID

        aMenu2.price = "30"


        aStaticMenu.add(aMenu2)

        val aMenu3 = DynamicMenu()

        aMenu3.menuName = CommonValues.MENU3_MY_ACCOUNT

        aMenu3.menuImage = R.drawable.ic_my_account

        aMenu3.menuID = CommonValues.MENU3_ID

        aMenu3.price = "20"

        aStaticMenu.add(aMenu3)

        val aMenu4 = DynamicMenu()

        aMenu4.menuName = CommonValues.MENU4_NOTIFICATION

        aMenu4.menuImage = R.drawable.ic_menu_notification

        aMenu4.menuID = CommonValues.MENU4_ID

        aMenu4.price = "10"

        aStaticMenu.add(aMenu4)

        val aMenu5 = DynamicMenu()

        aMenu5.menuName = CommonValues.MENU5_POINTS_REWARDS

        aMenu5.menuImage = R.drawable.ic_menu_rewards

        aMenu5.menuID = CommonValues.MENU5_ID

        aMenu5.price = "50"

        aStaticMenu.add(aMenu5)

        val aMenu7 = DynamicMenu()

        aMenu7.menuName = CommonValues.MENU3_MY_ACCOUNT

        aMenu7.menuImage = R.drawable.ic_my_account

        aMenu7.menuID = CommonValues.MENU3_ID

        aMenu7.price = "10"

        aStaticMenu.add(aMenu7)


        val aMenu8 = DynamicMenu()

        aMenu8.menuName = CommonValues.MENU3_MY_ACCOUNT

        aMenu8.menuImage = R.drawable.ic_my_account

        aMenu8.menuID = CommonValues.MENU3_ID

        aMenu8.price = "10"

        aStaticMenu.add(aMenu8)

        val aMenu9 = DynamicMenu()

        aMenu9.menuName = CommonValues.MENU3_MY_ACCOUNT

        aMenu9.menuImage = R.drawable.ic_my_account

        aMenu9.menuID = CommonValues.MENU3_ID

        aMenu9.price = "10"

        aStaticMenu.add(aMenu8)


        val aMenu6 = DynamicMenu()

        aMenu6.menuName = "PAYMENT"

        aStaticMenu.add(aMenu6)

        val adapter = FIMyCartItemAdapter(aStaticMenu, activity)

        mCartItemRC!!.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        mCartItemRC!!.adapter = adapter

        mAmtBtn!!.text = adapter.getTotalAmtValue()

    }

    override fun onResume() {
        super.onResume()
        (activity as FIHomePageActivity).hideBottomTab()
        (activity as FIHomePageActivity).hideToolbarHead()

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as FIHomePageActivity).showBottomTab()
        (activity as FIHomePageActivity).hideToolbarHead()

    }

    fun normalFunction() {
        println("This is normal function.")
        inlineFunctionExample({ println("Inlined Functions") }, { println("Instead of object creation it copies the code.") })
    }

    inline fun inlineFunctionExample(myFunction: () -> Unit, another: () -> Unit) {
        myFunction()
        another()
        println("Finally it's working fine!")
    }
}