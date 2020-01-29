package com.app.fromindia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.fragments.FIFragmentManager
import com.app.fromindia.model.MenuItem
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class FIHomeProductListAdapter internal constructor(val aStaticMenuList: ArrayList<MenuItem>, aContext: FragmentActivity?) : RecyclerView.Adapter<FIHomeProductListAdapter.ViewHolder>() {

    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FIHomeProductListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inflate_product_list_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FIHomeProductListAdapter.ViewHolder, position: Int) {
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

        fun bindItems(aStaticMenuItem: MenuItem, mFragmentManager: FIFragmentManager?, aContext: FragmentActivity?) {
            val aCityIM = itemView.findViewById(R.id.productIM) as ImageView
            val aCityNameTxt = itemView.findViewById(R.id.productDescTxt) as TextView
            Glide.with(aContext).load(aStaticMenuItem.menuUrl).into(aCityIM)
            aCityNameTxt!!.text = aStaticMenuItem.menuName
        }
    }


}