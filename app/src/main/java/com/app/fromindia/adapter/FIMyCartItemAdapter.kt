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

    private var mTotalPrice: String? = "0"

    private var mTotalValue: Int? = 0

    var mAmtValue: Int? = 0

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FIMyCartItemAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.infalte_my_cart_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FIMyCartItemAdapter.ViewHolder, position: Int) {
        var aItem = mStaticMenuList[position]
        if (aItem.menuName == "PAYMENT") {
            holder.aCartItemLAY!!.visibility = View.GONE
            holder.aPriceLAY!!.visibility = View.VISIBLE
            holder.mItemCountTxt!!.text = "Price (" + (itemCount - 1).toString() + " Items)"
            holder.mPriceTXT!!.text = getItemTotalValue()
            holder.mTaxTXT!!.text = "12"
            holder.mAmtTXT!!.text = getTotalAmtValue(holder.mTaxTXT!!.text.toString())

        } else {
            holder.aPriceLAY!!.visibility = View.GONE
            holder.aCartItemLAY!!.visibility = View.VISIBLE
            holder.mQtyTXT!!.text = "Qty :" + mStaticMenuList[position]!!.qty
            holder.mQtyTXT!!.tag = position
            holder.mProductAmtTxt!!.text = aItem.price
            holder.mQtyTXT!!.setOnClickListener { showPopUpWindow(holder.mQtyTXT!!, position) }
            toCalculatePriceValue(aItem!!.qty, aItem!!.price, aItem, position)

        }

    }

    private fun toCalculatePriceValue(qty: String, price: String, aItem: DynamicMenu, position: Int) {

        var aPriceCal: Int? = 0

        if (aItem.checkAddValue == true) {

            aPriceCal = price.toInt()


        } else {

            aPriceCal = qty.toInt() * price.toInt()

        }

        mTotalValue = mTotalValue!! + aPriceCal!!;

        Log.e("PRICE", "POS" + position + aPriceCal)

        // mTotalPrice = mTotalValue.toString()

        Log.e("AMTVALUE", "" + mTotalValue)
    }

    private fun getItemTotalValue(): String {
        return "" + mTotalValue!!
    }

    private fun getTotalAmtValue(aTax: String): String {


        mAmtValue = getItemTotalValue().toInt() + aTax.toInt()

        return "" + mAmtValue!!
    }

    public fun getTotalAmtValue(): String {
        return "" + mAmtValue!!
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return mStaticMenuList.size
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
        var aPriceLAY: RelativeLayout? = null
        var aCartItemLAY: RelativeLayout? = null
        var mProductAmtTxt: AppCompatTextView? = null
        var mItemCountTxt: AppCompatTextView? = null
        var mPriceTXT: AppCompatTextView? = null
        var mTaxTXT: AppCompatTextView? = null
        var mAmtTXT: AppCompatTextView? = null

        init {
            mQtyTXT = itemView.findViewById(R.id.qtyTXT) as TextView
            mSpinnerQty = itemView.findViewById(R.id.qtySpinnerLAY) as AppCompatSpinner
            aPriceLAY = itemView.findViewById(R.id.bottomPriceLAY) as RelativeLayout
            aCartItemLAY = itemView.findViewById(R.id.cartItemMainLAY) as RelativeLayout
            mProductAmtTxt = itemView.findViewById(R.id.productAmtTxt) as AppCompatTextView

            //Price LAY
            mItemCountTxt = itemView.findViewById(R.id.priceLblTXT) as AppCompatTextView
            mPriceTXT = itemView.findViewById(R.id.priceTXT) as AppCompatTextView
            mTaxTXT = itemView.findViewById(R.id.taxTXT) as AppCompatTextView
            mAmtTXT = itemView.findViewById(R.id.amtTXT) as AppCompatTextView


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

        Log.e("VALUEPOS", "" + aPos)

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

            mStaticMenuList[mPos!!].qty = aStaticMenu[position].name

            mTotalValue = mTotalValue!! - mStaticMenuList[mPos!!].price.toInt()

            mStaticMenuList[mPos!!].price = toCalculateQtyPriceValue(aStaticMenu[position].name, mStaticMenuList[mPos!!].price)

            mStaticMenuList[mPos!!].checkAddValue = true

            //  mTotalValue = 0 //For again calculating after change the QTY

            //mTotalPrice = "0"

            //  mAmtValue = 0

            updateList(mStaticMenuList)
        }


    }

    private fun updateList(aItem: ArrayList<DynamicMenu>) {
        mStaticMenuList = aItem
        notifyItemChanged(mPos!!);
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
                mStaticMenuList[mPos!!].qty = aQtyEDT!!.text.toString()

                // mStaticMenuList[mPos!!].price = toCalculateQtyPriceValue(aQtyEDT!!.text.toString(), mStaticMenuList[mPos!!].price)

                mTotalValue = 0 //For again calculating after change the QTY

                mTotalPrice = "0"

                mAmtValue = 0

                updateList(mStaticMenuList)

                dialog.dismiss()
            }

        }
        dialog.show()

    }

    /**
     * QTY and PRICE Manipulation for individual product
     */
    private fun toCalculateQtyPriceValue(qty: String, price: String): String {

        var aPriceCal: Int? = 0

        aPriceCal = qty.toInt() * price.toInt()

        return "" + aPriceCal

        Log.e("QTY*PRICE VALUE", "" + aPriceCal)
    }

}