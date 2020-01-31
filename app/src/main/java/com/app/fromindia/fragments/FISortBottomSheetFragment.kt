package com.app.fromindia.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.adapter.FICategoryFilterAdapter
import com.app.fromindia.adapter.FICategoryFilterItemAdapter
import com.app.fromindia.helper.CommonValues
import com.app.fromindia.model.DynamicMenu
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FISortBottomSheetFragment : BottomSheetDialogFragment(), FICategoryFilterAdapter.MyInterface, FICategoryFilterItemAdapter.MyInterface {

    private var mUpdateTXT: TextView? = null

    private var mHeaderTXT: TextView? = null

    private var mCategoryRC: RecyclerView? = null

    private var mCategoryItemRC: RecyclerView? = null

    private var mApplyBTN: AppCompatButton? = null

    private var mBackIM: AppCompatImageView? = null

    private var mFragmentManager: FIFragmentManager? = null


    private var mFilterItemAdapter: FICategoryFilterItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment

        val aView: View = inflater.inflate(R.layout.fragment_filter, container,
                false)

        classWidgets(aView);

        return aView
    }

    private fun classWidgets(aView: View) {

        mFragmentManager = FIFragmentManager(activity)

        mUpdateTXT = aView!!.findViewById(R.id.updateTXT) as TextView

        mHeaderTXT = aView!!.findViewById(R.id.headerTXT) as TextView

        mCategoryRC = aView!!.findViewById(R.id.categoryRC) as RecyclerView

        mCategoryItemRC = aView!!.findViewById(R.id.categoryItemRC) as RecyclerView

        mApplyBTN = aView!!.findViewById(R.id.applyBTN) as AppCompatButton

        mBackIM = aView!!.findViewById(R.id.backIm) as AppCompatImageView

        mUpdateTXT!!.text = getString(R.string.lbl_clear_filter)

        mHeaderTXT!!.text = getString(R.string.header_filter)

        toSetStaticValue()

        clickListener()

    }

    private fun clickListener() {
        mApplyBTN!!.setOnClickListener {

            Log.e("VALUE", mFilterItemAdapter!!.getSelectedValue())
        }

        mBackIM!!.setOnClickListener { mFragmentManager!!.backPress() }
    }

    private fun toSetStaticValue() {

        val aStaticMenu = ArrayList<DynamicMenu>()

        val aMenu1 = DynamicMenu()

        aMenu1.menuName = "Category"

        aMenu1.menuImage = R.drawable.ic_my_orders

        aMenu1.menuID = CommonValues.MENU1_ID

        aStaticMenu.add(aMenu1)

        val aMenu2 = DynamicMenu()

        aMenu2.menuName = "Size"

        aMenu2.menuImage = R.drawable.ic_my_wish_list

        aMenu2.menuID = CommonValues.MENU2_ID

        aStaticMenu.add(aMenu2)

        val aMenu3 = DynamicMenu()

        aMenu3.menuName = "Brand"

        aMenu3.menuImage = R.drawable.ic_my_account

        aMenu3.menuID = CommonValues.MENU3_ID

        aStaticMenu.add(aMenu3)

        val aMenu4 = DynamicMenu()

        aMenu4.menuName = "Price"

        aMenu4.menuImage = R.drawable.ic_menu_notification

        aMenu4.menuID = CommonValues.MENU4_ID

        aStaticMenu.add(aMenu4)

        val aMenu5 = DynamicMenu()

        aMenu5.menuName = "Offers"

        aMenu5.menuImage = R.drawable.ic_menu_rewards

        aMenu5.menuID = CommonValues.MENU5_ID

        aStaticMenu.add(aMenu5)

        val adapter = FICategoryFilterAdapter(aStaticMenu, activity, this)

        mCategoryRC!!.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        mCategoryRC!!.adapter = adapter
    }


    private fun toFilterItem() {

        val aStaticMenu = ArrayList<DynamicMenu>()

        val aMenu1 = DynamicMenu()

        aMenu1.menuName = "Category"

        aMenu1.menuImage = R.drawable.ic_my_orders

        aMenu1.menuID = CommonValues.MENU1_ID

        aStaticMenu.add(aMenu1)

        val aMenu2 = DynamicMenu()

        aMenu2.menuName = "Size"

        aMenu2.menuImage = R.drawable.ic_my_wish_list

        aMenu2.menuID = CommonValues.MENU2_ID

        aStaticMenu.add(aMenu2)

        val aMenu3 = DynamicMenu()

        aMenu3.menuName = "Brand"

        aMenu3.menuImage = R.drawable.ic_my_account

        aMenu3.menuID = CommonValues.MENU3_ID

        aStaticMenu.add(aMenu3)

        val aMenu4 = DynamicMenu()

        aMenu4.menuName = "Price"

        aMenu4.menuImage = R.drawable.ic_menu_notification

        aMenu4.menuID = CommonValues.MENU4_ID

        aStaticMenu.add(aMenu4)

        val aMenu5 = DynamicMenu()

        aMenu5.menuName = "Offers"

        aMenu5.menuImage = R.drawable.ic_menu_rewards

        aMenu5.menuID = CommonValues.MENU5_ID

        aStaticMenu.add(aMenu5)

        mFilterItemAdapter = FICategoryFilterItemAdapter(aStaticMenu, activity, this)

        mCategoryItemRC!!.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        mCategoryItemRC!!.adapter = mFilterItemAdapter
    }

    private fun toFilterItem2() {

        val aStaticMenu = ArrayList<DynamicMenu>()

        val aMenu1 = DynamicMenu()

        aMenu1.menuName = "Category"

        aMenu1.menuImage = R.drawable.ic_my_orders

        aMenu1.menuID = CommonValues.MENU1_ID

        aStaticMenu.add(aMenu1)

        val aMenu2 = DynamicMenu()

        aMenu2.menuName = "Size"

        aMenu2.menuImage = R.drawable.ic_my_wish_list

        aMenu2.menuID = CommonValues.MENU2_ID

        aStaticMenu.add(aMenu2)

        val aMenu3 = DynamicMenu()

        aMenu3.menuName = "Brand"

        aMenu3.menuImage = R.drawable.ic_my_account

        aMenu3.menuID = CommonValues.MENU3_ID

        aStaticMenu.add(aMenu3)


        mFilterItemAdapter = FICategoryFilterItemAdapter(aStaticMenu, activity, this)

        mCategoryItemRC!!.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        mCategoryItemRC!!.adapter = mFilterItemAdapter
    }

    override fun clickEvent(aMenu: DynamicMenu) {

        when (aMenu.menuName) {

            "Category" -> toFilterItem()
            "Size" -> toFilterItem2()
            "Brand" -> toFilterItem()
            "Price" -> toFilterItem2()
            "Offers" -> toFilterItem()

        }
        Toast.makeText(activity, "clickEvent", Toast.LENGTH_SHORT).show()
    }

    override fun filterItem() {
        Toast.makeText(activity, "filterItem", Toast.LENGTH_SHORT).show()
    }

}