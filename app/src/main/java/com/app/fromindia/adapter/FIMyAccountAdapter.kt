package com.app.fromindia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity
import com.app.fromindia.fragments.FIAddAddressFragment
import com.app.fromindia.fragments.FIFragmentManager
import com.app.fromindia.fragments.slideMenu.FIMyOrderFragment
import com.app.fromindia.fragments.slideMenu.FIMyPointsRewardsFragment
import com.app.fromindia.fragments.slideMenu.FIMyWishListFragment
import com.app.fromindia.fragments.slideMenu.FISlideMenuCategoryFragment
import com.app.fromindia.helper.CommonValues
import com.app.fromindia.helper.CommonValues.*
import com.app.fromindia.model.DynamicMenu

class FIMyAccountAdapter internal constructor(val userList: ArrayList<DynamicMenu>, aContext: FragmentActivity?) : RecyclerView.Adapter<FIMyAccountAdapter.ViewHolder>() {
    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FIMyAccountAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inflate_profile_list_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FIMyAccountAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position], mContext, mFragmentManager)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    init {
        mContext = aContext
        mFragmentManager = FIFragmentManager(mContext)
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(aMenu: DynamicMenu, mContext: FragmentActivity?, mFragmentManager: FIFragmentManager?) {
            val aMenuNameTXT = itemView.findViewById(R.id.menuItemNameTXT) as TextView
            val aMenuIM = itemView.findViewById(R.id.menuItemIM) as AppCompatImageView
            val aMainLay = itemView.findViewById(R.id.mainItemLAY) as RelativeLayout
            aMenuNameTXT.text = aMenu.menuName
            aMenuIM.setImageResource(aMenu.menuImage)
            aMainLay.setOnClickListener {
                (mContext as FIHomePageActivity).closeDrawerSetTitle(aMenu.menuName)  // To close the drawer
                when (aMenu.menuName) {
                    MENU1_ORDER -> mFragmentManager!!.updateContent(FIMyOrderFragment(), "", null)
                    MENU2_WISH_LIST -> mFragmentManager!!.updateContent(FIMyWishListFragment(), "", null)
                    MENU7_MY_ADDRESS -> mFragmentManager!!.updateContent(FIAddAddressFragment(), "", null)
                    MENU5_POINTS_REWARDS -> mFragmentManager!!.updateContent(FIMyPointsRewardsFragment(), "", null)
                }
            }

        }
    }


}