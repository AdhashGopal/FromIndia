package com.app.fromindia.fragments.slideMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity
import com.app.fromindia.adapter.FIHomeProductListAdapter
import com.app.fromindia.adapter.FIMenuProductListAdapter
import com.app.fromindia.model.MenuItem
import com.app.fromindia.utils.MiddleDividerItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialog

class FISlideMenuCategoryFragment : Fragment() {

    private var mItemRC: RecyclerView? = null
    private var mSortLAY: RelativeLayout? = null
    private var mFilterLAY: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val aView: View = inflater.inflate(R.layout.fragment_slide_menu_category, container,
                false)

        classWidgets(aView);

        return aView
    }

    private fun classWidgets(aView: View) {
        mItemRC = aView!!.findViewById(R.id.categoryItemRC) as RecyclerView
        mSortLAY = aView!!.findViewById(R.id.sortLAY) as RelativeLayout
        mFilterLAY = aView!!.findViewById(R.id.filterLAY) as RelativeLayout
        toSetProductValue()
        clickListener()
    }

    private fun clickListener() {
        mSortLAY!!.setOnClickListener { showBottomSheet() }
    }


    private fun toSetProductValue() {

        val aStaticMenu = ArrayList<MenuItem>()

        val aMenu1 = MenuItem()

        aMenu1.menuName = "Traditional Kamachi Vilakku Medium 13 cm - Fine Copper"

        aMenu1.menuUrl = "https://sg.fromindia.com/pub/media/catalog/product/t/r/traditional-kamachi-vilakku.jpg"

        aStaticMenu.add(aMenu1)

        val aMenu2 = MenuItem()

        aMenu2.menuName = "Mani Mark Kadalamittai/Kadali Urundai"

        aMenu2.menuUrl = "https://sg.fromindia.com/pub/media/catalog/product/m/a/manimark-kadalai-mittai.png"

        aStaticMenu.add(aMenu2)

        val aMenu3 = MenuItem()

        aMenu3.menuName = "Gents Jute Sling Bag"

        aMenu3.menuUrl = "https://sg.fromindia.com/pub/media/catalog/product/g/e/gents-cotton-sling-bag.png"

        aStaticMenu.add(aMenu3)

        val aMenu4 = MenuItem()

        aMenu4.menuName = "Creatick Impex Hammered Brass Container"

        aMenu4.menuUrl = "https://sg.fromindia.com/pub/media/catalog/product/h/a/hammered-brass-container_1.jpg"

        aStaticMenu.add(aMenu4)

        val adapter = FIMenuProductListAdapter(aStaticMenu, activity)

        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

        mItemRC!!.addItemDecoration(MiddleDividerItemDecoration(context!!, MiddleDividerItemDecoration.ALL))

        mItemRC!!.layoutManager = manager

        mItemRC!!.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        (activity as FIHomePageActivity).hideBottomTab()
        (activity as FIHomePageActivity).showToolbarHead()

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as FIHomePageActivity).showBottomTab()
        (activity as FIHomePageActivity).hideToolbarHead()

    }

    private fun showBottomSheet() {

        val view = layoutInflater.inflate(R.layout.inflate_sort_sheet, null)
        val dialog = BottomSheetDialog(this!!.activity!!)
        dialog.setContentView(view)
        dialog.show()
    }
}