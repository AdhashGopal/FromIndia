package com.app.fromindia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.fragments.FIFragmentManager
import com.app.fromindia.fragments.FISortBottomSheetFragment
import com.app.fromindia.model.DynamicMenu


class FICategoryFilterAdapter internal constructor(val aStaticMenuList: ArrayList<DynamicMenu>,
                                                   aContext: FragmentActivity?,
                                                   fiSortBottomSheetFragment: FISortBottomSheetFragment) :
        RecyclerView.Adapter<FICategoryFilterAdapter.ViewHolder>() {

    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null

    private var mItemList: ArrayList<DynamicMenu>? = null

    private var mItemInterface: MyInterface? = null

    interface MyInterface {
        fun clickEvent(dynamicMenu: DynamicMenu)

    }

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FICategoryFilterAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inflate_single_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FICategoryFilterAdapter.ViewHolder, position: Int) {
        // Glide.with(mContext).load(mItemList!![position].menuImage).into(holder.aProductIM)
        holder.aSingleItemTXT!!.text = mItemList!![position].menuName
        /* holder.aSingleItemTXT!!.setOnClickListener {
             holder.aSingleItemTXT!!.setBackgroundColor(R.drawable.bg_cardview)
             holder.aSingleItemTXT!!.setTextColor(ContextCompat.getColor(mContext!!, R.color.app_secondary_color));
             //  setClickListener(mItemInterface!!)
         }*/

        if (mItemList!![position].isCheck) {

            holder.aSingleItemTXT!!.setBackgroundColor(ContextCompat.getColor(mContext!!, R.color.white))

            holder.aSingleItemTXT!!.setTextColor(ContextCompat.getColor(mContext!!, R.color.app_secondary_color))
        } else {
            holder.aSingleItemTXT!!.setBackgroundColor(ContextCompat.getColor(mContext!!, android.R.color.transparent))

            holder.aSingleItemTXT!!.setTextColor(ContextCompat.getColor(mContext!!, R.color.black))
        }

        holder.aSingleItemTXT!!.setOnClickListener {
            updateSelection(position)
            mItemInterface!!.clickEvent(mItemList!![position])
        }


    }

    private fun updateSelection(position: Int) {

        for (k in mItemList!!.indices) {
            mItemList!![k].isCheck = k == position
        }

        updateList(mItemList!!)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return mItemList!!.size
    }

    init {
        mContext = aContext
        mFragmentManager = FIFragmentManager(mContext)
        mItemList = aStaticMenuList
        mItemInterface = fiSortBottomSheetFragment

    }

    //the class is holding  the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var aSingleItemTXT: RadioButton? = null
        var aMainLAY: RelativeLayout? = null

        var aRadioGroup: RadioGroup? = null

        init {
            aSingleItemTXT = itemView.findViewById(R.id.singletItemTXT) as RadioButton
            aRadioGroup = itemView.findViewById(R.id.radioItemGroup) as RadioGroup

            aMainLAY = itemView.findViewById(R.id.singleMainLAY) as RelativeLayout

        }

    }

    private fun updateList(aItem: ArrayList<DynamicMenu>) {
        mItemList = aItem
        notifyDataSetChanged()
    }


}



