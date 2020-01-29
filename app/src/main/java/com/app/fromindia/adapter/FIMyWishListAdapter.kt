package com.app.fromindia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity
import com.app.fromindia.fragments.*
import com.app.fromindia.fragments.slideMenu.*
import com.app.fromindia.model.DynamicMenu

class FIMyWishListAdapter internal constructor(val aStaticMenuList: ArrayList<DynamicMenu>, aContext: FragmentActivity?) : RecyclerView.Adapter<FIMyWishListAdapter.ViewHolder>() {

    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FIMyWishListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inflate_my_wishlist_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FIMyWishListAdapter.ViewHolder, position: Int) {
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
            val aProductDescTXT = itemView.findViewById(R.id.wishListProductDescTXT) as TextView
            val aProductRemoveXT = itemView.findViewById(R.id.wishListRemoveTXT) as TextView
            val aProductIM = itemView.findViewById(R.id.wishListProductIM) as ImageView
            val aWishListPrizeTXT = itemView.findViewById(R.id.wishListProductPriceTXT) as TextView
            val aRootLAY = itemView.findViewById(R.id.wishListMainLAY) as ConstraintLayout
            aRootLAY.setOnClickListener {
                // (aContext as FIHomePageActivity).closeDrawerSetTitle(aStaticMenuItem.menuName)  // To close the drawer
                mFragmentManager!!.updateContent(FIProductDetailFragment(), "", null)
            }
        }
    }


}