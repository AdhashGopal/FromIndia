package com.app.fromindia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.fragments.FIFragmentManager
import com.app.fromindia.fragments.FISortBottomSheetFragment
import com.app.fromindia.model.DynamicMenu


class FICategoryFilterItemAdapter internal constructor(val aStaticMenuList: ArrayList<DynamicMenu>,
                                                       aContext: FragmentActivity?,
                                                       fiSortBottomSheetFragment: FISortBottomSheetFragment) :
        RecyclerView.Adapter<FICategoryFilterItemAdapter.ViewHolder>() {

    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null

    private var mItemList: ArrayList<DynamicMenu>? = null

    private var mItemInterface: MyInterface? = null

    interface MyInterface {
        fun filterItem()
    }

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inflate_filter_cat_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Glide.with(mContext).load(mItemList!![position].menuImage).into(holder.aProductIM)

        holder.aSingleItemTXT!!.text = mItemList!![position].menuName

        // holder.aSingleItemTXT!!.isChecked = true

        if (mItemList!![position].isCheck) {

            holder.aSingleItemTXT!!.setChecked(true)
        } else {
            // holder.aSingleItemTXT!!.isChecked = false
            holder.aSingleItemTXT!!.setChecked(false)
        }

        /*   holder.aSingleItemTXT!!.setOnClickListener {
               updateSelection(position, isChecked)
               mItemInterface!!.filterItem()
           }*/
        holder.aSingleItemTXT!!.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                updateSelection(position, true)

            } else {
                updateSelection(position, false)

            }
        }
        )


    }

    private fun updateSelection(position: Int, checked: Boolean) {
        for (k in mItemList!!.indices) {
            if (k == position) {
                mItemList!![k].isCheck = checked
            }
            //mItemList!![k].isCheck = k == position
        }

        updateList(mItemList!!, position)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return mItemList!!.size
    }

    //this method is giving the size of the list
    fun getSelectedValue(): String {
        var aVal: String
        val aValBuf = StringBuffer()
        for (k in mItemList!!.indices) {
            if (mItemList!![k].isCheck) {
                aVal = mItemList!![k].menuName
                aValBuf!!.append(aVal)
                aValBuf!!.append(",")

            }
            //mItemList!![k].isCheck = k == position
        }
        return aValBuf!!.toString()
    }

    init {
        mContext = aContext
        mFragmentManager = FIFragmentManager(mContext)
        mItemList = aStaticMenuList
        mItemInterface = fiSortBottomSheetFragment
    }

    //the class is holding  the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var aSingleItemTXT: CheckBox? = null


        init {
            aSingleItemTXT = itemView.findViewById(R.id.itemCH) as CheckBox

        }

    }

    private fun updateList(aItem: ArrayList<DynamicMenu>, aPos: Int) {
        mItemList = aItem
        try {
            mContext!!.runOnUiThread(Runnable {
                notifyItemChanged(aPos)
            })
        } catch (e: Exception) {
        }
    }


}



