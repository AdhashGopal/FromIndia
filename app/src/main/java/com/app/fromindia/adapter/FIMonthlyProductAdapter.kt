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

class FIMonthlyProductAdapter internal constructor(val aStaticMenuList: ArrayList<MenuItem>, aContext: FragmentActivity?) : RecyclerView.Adapter<FIMonthlyProductAdapter.ViewHolder>() {

    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FIMonthlyProductAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.infalte_monthly_product_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FIMonthlyProductAdapter.ViewHolder, position: Int) {
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
            val aProductIM = itemView.findViewById(R.id.monthlyProductIM) as ImageView
            val aProductPrizeTxt = itemView.findViewById(R.id.monthlyProductPriceTxt) as TextView
            Glide.with(aContext).load(aStaticMenuItem.menuUrl).into(aProductIM)
            // aProductPrizeTxt!!.text = aStaticMenuItem.menuName
        }
    }


}