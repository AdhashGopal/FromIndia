package com.app.fromindia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.fragments.FIFragmentManager
import com.app.fromindia.fragments.FIProductDetailFragment
import com.app.fromindia.model.DynamicMenu
import com.bumptech.glide.Glide

class FIMyWishListAdapter internal constructor(val aStaticMenuList: ArrayList<DynamicMenu>,
                                               aContext: FragmentActivity?) :
        RecyclerView.Adapter<FIMyWishListAdapter.ViewHolder>() {

    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null

    private var mItemList: ArrayList<DynamicMenu>? = null

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FIMyWishListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inflate_my_wishlist_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FIMyWishListAdapter.ViewHolder, position: Int) {

        // Glide.with(mContext).load(mItemList!![position].menuImage).into(holder.aProductIM)

        //   holder.aProductDescTXT!!.text = mItemList!![position].menuName

        holder.aRootLAY!!.setOnClickListener {
            mFragmentManager!!.updateContent(FIProductDetailFragment(), "", null)
        }
        holder.aProductRemoveXT!!.setOnClickListener {

            mItemList!!.removeAt(position)

            updateList(mItemList!!)
        }

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return mItemList!!.size
    }

    init {
        mContext = aContext
        mFragmentManager = FIFragmentManager(mContext)
        mItemList = aStaticMenuList

    }

    //the class is holding  the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var aProductDescTXT: TextView? = null
        var aProductRemoveXT: TextView? = null
        var aWishListPrizeTXT: TextView? = null
        var aProductIM: ImageView? = null
        var aRootLAY: ConstraintLayout? = null

        init {
            aProductDescTXT = itemView.findViewById(R.id.wishListProductDescTXT) as TextView
            aProductRemoveXT = itemView.findViewById(R.id.wishListRemoveTXT) as TextView
            aProductIM = itemView.findViewById(R.id.wishListProductIM) as ImageView
            aWishListPrizeTXT = itemView.findViewById(R.id.wishListProductPriceTXT) as TextView
            aRootLAY = itemView.findViewById(R.id.wishListMainLAY) as ConstraintLayout
        }

    }

    private fun updateList(aItem: ArrayList<DynamicMenu>) {
        mItemList = aItem
        notifyDataSetChanged()
    }
}



