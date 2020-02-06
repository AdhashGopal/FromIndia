package com.app.fromindia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity
import com.app.fromindia.fragments.*
import com.app.fromindia.fragments.slideMenu.*
import com.app.fromindia.model.DynamicMenu

class FIStaticMenuAdapter internal constructor(val aStaticMenuList: ArrayList<DynamicMenu>, aContext: FragmentActivity?) : RecyclerView.Adapter<FIStaticMenuAdapter.ViewHolder>() {

    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FIStaticMenuAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FIStaticMenuAdapter.ViewHolder, position: Int) {
        holder.bindItems(aStaticMenuList[position], mFragmentManager, mContext)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return aStaticMenuList.size
    }

    init {
        mContext = aContext
        mFragmentManager = FIFragmentManager(mContext)
    }

    //the class is holding  the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(aStaticMenuItem: DynamicMenu, mFragmentManager: FIFragmentManager?, aContext: FragmentActivity?) {
            val aMenuNameTXT = itemView.findViewById(R.id.menuItemNameTXT) as TextView
            val aMenuIM = itemView.findViewById(R.id.menuItemIM) as AppCompatImageView
            val aMainCV = itemView.findViewById(R.id.menuMainCV) as CardView
            aMenuNameTXT.text = aStaticMenuItem.menuName
            aMenuIM.setImageResource(aStaticMenuItem.menuImage)
            aMainCV.setOnClickListener {
                (aContext as FIHomePageActivity).closeDrawerSetTitle(aStaticMenuItem.menuName)  // To close the drawer
                mFragmentManager!!.clearAllFragments()  // To clear all the Back stack
                when (aStaticMenuItem.menuID) {
                    1 -> mFragmentManager!!.updateContent(FIMyOrderFragment(), "", null)
                    2 -> mFragmentManager!!.updateContent(FIMyWishListFragment(), "", null)
                    3 -> mFragmentManager!!.updateContent(FIMyAccountFragment(), "", null)
                    4 -> mFragmentManager!!.updateContent(FIMyNotificationFragment(), "", null)
                    //  5 -> mFragmentManager!!.updateContent(FIMyPointsRewardsFragment(), "", null)
                    5 -> mFragmentManager!!.updateContent(FIMyCartFragment(), "", null)
                }
            }
        }
    }


}