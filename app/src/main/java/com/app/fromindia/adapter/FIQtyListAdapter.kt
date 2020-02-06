package com.app.fromindia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.fragments.FIFragmentManager
import com.app.fromindia.fragments.FIProductDetailFragment
import com.app.fromindia.model.QtyItem


class FIQtyListAdapter internal constructor(val aStaticMenuList: ArrayList<QtyItem>, aContext: FragmentActivity?, aListener: FIMyCartItemAdapter) : RecyclerView.Adapter<FIQtyListAdapter.ViewHolder>() {

    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null
    private var mListener: RecyclerViewClickListener? = null

    interface RecyclerViewClickListener {
        fun onClick(view: View?, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FIQtyListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inflate_qty_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FIQtyListAdapter.ViewHolder, position: Int) {
        // holder.bindItems(aStaticMenuList[position], mFragmentManager, mContext, mListener, position)
        holder.aQtyTxt!!.text = aStaticMenuList[position].name
        holder.aMainLAY!!.setOnClickListener {
            mListener!!.onClick(holder.aQtyTxt!!, position)
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return aStaticMenuList.size
    }

    init {
        mContext = aContext
        mFragmentManager = FIFragmentManager(mContext)
        mListener = aListener;
    }

    //the class is holding  the list view
    class ViewHolder(aView: View) : RecyclerView.ViewHolder(aView) {

        var aQtyTxt: TextView? = null
        var aMainLAY: RelativeLayout? = null

        init {
            aQtyTxt = itemView.findViewById(R.id.qtyTXT) as TextView
            aMainLAY = itemView.findViewById(R.id.mainLAY) as RelativeLayout


        }
    }


}