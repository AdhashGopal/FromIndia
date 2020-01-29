package com.app.fromindia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity
import com.app.fromindia.adapter.FIAutoScrollAdapter
import com.app.fromindia.adapter.FICustomAdapter
import com.app.fromindia.adapter.FIMyAccountAdapter
import com.app.fromindia.adapter.FIProductListAdapter
import com.app.fromindia.helper.CommonValues
import com.app.fromindia.helper.CommonValues.*
import com.app.fromindia.model.DynamicMenu
import com.app.fromindia.model.MenuItem
import com.asksira.loopingviewpager.LoopingViewPager
import com.rd.PageIndicatorView
import kotlinx.android.synthetic.main.activity_main2.*

class FIProductDetailFragment : Fragment() {

    private var TAG = "FIProductDetailFragment"

    private var mFragmentManager: FIFragmentManager? = null

    private var mBackIM: AppCompatImageView? = null

    private var mProductList: LoopingViewPager? = null

    private var mPageIndicator: PageIndicatorView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment

        val aView: View = inflater.inflate(R.layout.fragment_product_details, container,
                false)

        classWidgets(aView);

        return aView
    }

    private fun classWidgets(aView: View) {

        mFragmentManager = FIFragmentManager(activity)

        mBackIM = aView!!.findViewById(R.id.backIm) as AppCompatImageView

        mProductList = aView!!.findViewById(R.id.productScrollVP) as LoopingViewPager

        mPageIndicator = aView!!.findViewById(R.id.productPageIndicatorView) as PageIndicatorView

        toSetStaticValue()

        clickListener()
    }


    private fun toSetStaticValue() {

        val aStaticMenu = ArrayList<MenuItem>()

        val aMenu1 = MenuItem()

        aMenu1.menuUrl = "https://sg.fromindia.com/pub/media/catalog/product/t/r/traditional-kamachi-vilakku.jpg"

        aStaticMenu.add(aMenu1)

        val aMenu2 = MenuItem()

        aMenu2.menuUrl = "https://sg.fromindia.com/pub/media/catalog/product/m/a/manimark-kadalai-mittai.png"

        aStaticMenu.add(aMenu2)

        val aMenu3 = MenuItem()

        aMenu3.menuUrl = "https://sg.fromindia.com/pub/media/catalog/product/g/e/gents-cotton-sling-bag.png"

        aStaticMenu.add(aMenu3)

        val aMenu4 = MenuItem()

        aMenu4.menuUrl = "https://sg.fromindia.com/pub/media/catalog/product/h/a/hammered-brass-container_1.jpg"

        aStaticMenu.add(aMenu4)

        var adapter1 = FIProductListAdapter(activity, aStaticMenu, true)

        mProductList!!.adapter = adapter1

        //Custom bind indicator

        mPageIndicator!!.count = mProductList!!.indicatorCount

        mPageIndicator!!.scaleFactor = 10.0F

        mProductList!!.setIndicatorPageChangeListener(object : LoopingViewPager.IndicatorPageChangeListener {
            override fun onIndicatorProgress(selectingPosition: Int, progress: Float) {
                mPageIndicator!!.setProgress(selectingPosition, progress)
            }

            override fun onIndicatorPageChange(newIndicatorPosition: Int) { //                indicatorView.setSelection(newIndicatorPosition);
            }
        })


    }


    private fun clickListener() {
        mBackIM!!.setOnClickListener {
            mFragmentManager!!.backPress()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as FIHomePageActivity).hideBottomTab()
        (activity as FIHomePageActivity).hideToolbarHead()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as FIHomePageActivity).hideToolbarHead()
        (activity as FIHomePageActivity).showToolbarHead()

    }
}