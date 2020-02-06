package com.app.fromindia.adapter

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.fragments.FIFragmentManager
import com.app.fromindia.model.DynamicMenu
import com.app.fromindia.model.QtyItem
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*


class FIMyCartItemAdapter internal constructor(val aStaticMenuList: ArrayList<DynamicMenu>, aContext: FragmentActivity?) : RecyclerView.Adapter<FIMyCartItemAdapter.ViewHolder>(), FIQtyListAdapter.RecyclerViewClickListener {

    private var mContext: FragmentActivity? = null
    // private val mFragmentManager: FIFragmentManager
    private var mFragmentManager: FIFragmentManager? = null

    private val mQty = arrayOf("1", "2", "3", "More")

    private var mPopupWindow: PopupWindow? = null

    private var aStaticMenu = ArrayList<QtyItem>()

    private var aQtyAdapter: FIQtyListAdapter? = null

    private var mStaticMenuList = ArrayList<DynamicMenu>()

    private var mPos: Int? = 0

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FIMyCartItemAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.infalte_my_cart_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FIMyCartItemAdapter.ViewHolder, position: Int) {
        // holder.bindItems(aStaticMenuList[position], mFragmentManager, mContext)
        holder.mQtyTXT!!.text = "Qty :" + mStaticMenuList[position]!!.qty
        holder.mQtyTXT!!.tag = position
        holder.mQtyTXT!!.setOnClickListener { showPopUpWindow(holder.mQtyTXT!!, position) }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return aStaticMenuList.size
    }

    init {
        mContext = aContext
        mFragmentManager = FIFragmentManager(mContext)
        mStaticMenuList = aStaticMenuList
    }

    //the class is holding  the list view


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var mQtyTXT: TextView? = null
        var mSpinnerQty: AppCompatSpinner? = null
        var aWishListPrizeTXT: TextView? = null
        var aProductIM: ImageView? = null
        var aRootLAY: ConstraintLayout? = null

        init {
            mQtyTXT = itemView.findViewById(R.id.qtyTXT) as TextView
            mSpinnerQty = itemView.findViewById(R.id.qtySpinnerLAY) as AppCompatSpinner


        }
    }


    private fun showPopUpWindow(aView: View, aPos: Int) {

        Log.e("POS", "" + aPos)

        val aTextView = aView as TextView

        var aVal: Int? = 0

        aVal = aTextView.tag as Int?

        val inflater = mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val aCustomView: View = inflater.inflate(R.layout.mode_selection, null)

        mPopupWindow = PopupWindow(
                aCustomView,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        )

        mPopupWindow!!.isOutsideTouchable = true

        val aQtyRV = aCustomView.findViewById<View>(R.id.qtyRV) as RecyclerView

        aVal?.let { toSetQtyValues(aQtyRV, it) }

        mPopupWindow!!.showAsDropDown(aTextView, 0, 0)


    }

    private fun toSetQtyValues(aRec: RecyclerView, aPos: Int) {

        mPos = aPos

        aStaticMenu = ArrayList<QtyItem>()

        val aMenu1 = QtyItem()

        aMenu1.name = "1"

        aStaticMenu.add(aMenu1)

        val aMenu2 = QtyItem()

        aMenu2.name = "2"

        aStaticMenu.add(aMenu2)

        val aMenu3 = QtyItem()

        aMenu3.name = "3"

        aStaticMenu.add(aMenu3)

        val aMenu4 = QtyItem()

        aMenu4.name = "More"

        aStaticMenu.add(aMenu4)


        aQtyAdapter = FIQtyListAdapter(aStaticMenu, mContext, this!!)

        aRec!!.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)

        aRec!!.adapter = aQtyAdapter

        // aRec!!.setRecyclerListener { }


    }

    override fun onClick(aView: View?, position: Int) {
        //Toast.makeText(mContext, aStaticMenu[position].name, Toast.LENGTH_SHORT).show()
        if (mPopupWindow!!.isShowing) {
            mPopupWindow!!.dismiss()
        }

        if (aStaticMenu[position].name == "More") {
            showDialog()
        } else {

            mStaticMenuList[mPos!!].qty = aStaticMenu[position].name.toInt()

            updateList(mStaticMenuList)
        }


    }

    private fun updateList(aItem: ArrayList<DynamicMenu>) {
        mStaticMenuList = aItem
        notifyDataSetChanged()
    }

    private fun showDialog() {

        val dialog = Dialog(mContext!!)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setCancelable(false)

        dialog.setContentView(R.layout.alert_dialog_qty)

        val aCancelBTN = dialog.findViewById(R.id.cancelBtn) as AppCompatTextView

        val aQtyEDT = dialog.findViewById(R.id.edtLAY) as TextInputEditText

        val aQtyTL = dialog.findViewById(R.id.edtTL) as TextInputLayout

        val aApplyBTN = dialog.findViewById(R.id.applyBtn) as AppCompatTextView

        aCancelBTN.setOnClickListener {
            dialog.dismiss()
        }
        aApplyBTN.setOnClickListener {

            if (aQtyEDT!!.text.toString().trim() == "") {

                aQtyTL!!.error = mContext!!.getString(R.string.lbl_enter_qty)

            } else {
                mStaticMenuList[mPos!!].qty = aQtyEDT!!.text.toString().toInt()

                updateList(mStaticMenuList)

                dialog.dismiss()
            }

        }
        dialog.show()

    }
}