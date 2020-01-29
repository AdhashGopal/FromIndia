package com.app.fromindia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity
import com.app.fromindia.adapter.*
import com.app.fromindia.helper.CommonValues
import com.app.fromindia.model.MenuItem
import com.app.fromindia.utils.MiddleDividerItemDecoration
import com.app.fromindia.utils.Utils
import com.asksira.loopingviewpager.LoopingViewPager
import com.asksira.loopingviewpager.LoopingViewPager.IndicatorPageChangeListener
import com.rd.PageIndicatorView


class FIHomePageFragment : Fragment() {


    private var mAutoScrollVP: LoopingViewPager? = null

    private var mPageIndicator: PageIndicatorView? = null

    private var mCityRC: RecyclerView? = null

    private var mProductListRC: RecyclerView? = null

    private var mMonthlyProductRC: RecyclerView? = null

    private var mProductAllTxt: TextView? = null

    private var mMonthlyDealAll: TextView? = null


    private var mContext: FragmentActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val aView: View = inflater.inflate(R.layout.fragment_home_page, container,
                false)

        classWidgets(aView);

        return aView
    }

    private fun classWidgets(aView: View) {
        mContext = activity
        mAutoScrollVP = aView!!.findViewById(R.id.autoScrollVP) as LoopingViewPager
        mPageIndicator = aView!!.findViewById(R.id.pageIndicatorView) as PageIndicatorView
        mCityRC = aView!!.findViewById(R.id.cityListRV) as RecyclerView
        mProductListRC = aView!!.findViewById(R.id.productListRV) as RecyclerView
        mMonthlyProductRC = aView!!.findViewById(R.id.monthlyProductListRV) as RecyclerView

        mProductAllTxt = aView!!.findViewById(R.id.productListViewAllTxt) as TextView
        mMonthlyDealAll = aView!!.findViewById(R.id.monthlyDealViewAllTxt) as TextView


        toSetStaticValue()
        toSetCityValue()
        toSetProductValue()
        toSetMonthlyItem()

        clickListener()
    }

    private fun clickListener() {
        mProductAllTxt!!.setOnClickListener { }
        mMonthlyDealAll!!.setOnClickListener { }

    }

    private fun toSetStaticValue() {

        val aStaticMenu = ArrayList<MenuItem>()

        val aMenu1 = MenuItem()

        aMenu1.menuName = CommonValues.MENU1_ORDER


        aMenu1.menuUrl = "https://sg.fromindia.com/pub/media/banners/images/24Hr-Delivery-2.png"


        aStaticMenu.add(aMenu1)

        val aMenu2 = MenuItem()

        aMenu2.menuUrl = "https://sg.fromindia.com/pub/media/banners/images/IPIP-1.png"

        aStaticMenu.add(aMenu2)

        val aMenu3 = MenuItem()

        aMenu3.menuUrl = "https://sg.fromindia.com/pub/media/banners/images/Pongal-Mobile-Banner.png"

        aStaticMenu.add(aMenu3)

        val aMenu4 = MenuItem()

        aMenu4.menuUrl = "https://sg.fromindia.com/pub/media/banners/images/24Hr-Delivery-1.png"

        aStaticMenu.add(aMenu4)

        var adapter1 = FIAutoScrollAdapter(activity, aStaticMenu, true)

        mAutoScrollVP!!.adapter = adapter1

        //Custom bind indicator
        mPageIndicator!!.count = mAutoScrollVP!!.indicatorCount

        mPageIndicator!!.scaleFactor = 10.0F

        mAutoScrollVP!!.setIndicatorPageChangeListener(object : IndicatorPageChangeListener {
            override fun onIndicatorProgress(selectingPosition: Int, progress: Float) {
                mPageIndicator!!.setProgress(selectingPosition, progress)
            }

            override fun onIndicatorPageChange(newIndicatorPosition: Int) { //                indicatorView.setSelection(newIndicatorPosition);
            }
        })


    }


    private fun toSetCityValue() {

        val aStaticMenu = ArrayList<MenuItem>()

        val aMenu1 = MenuItem()

        aMenu1.menuName = "Coimbatore"

        aMenu1.menuUrl = "https://www.colive.in/blog/wp-content/uploads/2018/04/wdwd.jpg"

        aStaticMenu.add(aMenu1)

        val aMenu2 = MenuItem()

        aMenu2.menuName = "Namakkal"

        aMenu2.menuUrl = "https://cdn.s3waas.gov.in/s3b9228e0962a78b84f3d5d92f4faa000b/uploads/2018/05/2018050331.jpg"

        aStaticMenu.add(aMenu2)

        val aMenu3 = MenuItem()

        aMenu3.menuName = "Kerala"

        aMenu3.menuUrl = "https://imagevars.gulfnews.com/2019/02/13/Keralabuildings_resources1_16a4a15ff94_large.jpg"

        aStaticMenu.add(aMenu3)

        val aMenu4 = MenuItem()

        aMenu4.menuName = "Chennai"

        aMenu4.menuUrl = "https://www.track2realty.track2media.com/wp-content/uploads/2019/04/Chennai.jpg"

        aStaticMenu.add(aMenu4)

        val aMenu5 = MenuItem()

        aMenu5.menuName = "Thrissur"

        aMenu5.menuUrl = "https://www.holidify.com/images/cmsuploads/compressed/Shoba_City_Mall,_Thrissur,_Kerala_20190530041833.jpeg"

        aStaticMenu.add(aMenu5)

        val aMenu6 = MenuItem()

        aMenu6.menuName = "Chennai"

        aMenu6.menuUrl = "https://www.track2realty.track2media.com/wp-content/uploads/2019/04/Chennai.jpg"

        aStaticMenu.add(aMenu6)

        val aMenu7 = MenuItem()

        aMenu7.menuName = "Dubai"

        aMenu7.menuUrl = "https://www.holidify.com/images/cmsuploads/compressed/Shoba_City_Mall,_Thrissur,_Kerala_20190530041833.jpeg"

        aStaticMenu.add(aMenu7)

        val aMenu8 = MenuItem()

        aMenu8.menuName = "Kochin"

        aMenu8.menuUrl = "https://imagevars.gulfnews.com/2019/02/13/Keralabuildings_resources1_16a4a15ff94_large.jpg"

        aStaticMenu.add(aMenu8)

        val adapter = FICityListAdapter(aStaticMenu, activity)

        mCityRC!!.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        mCityRC!!.adapter = adapter

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

        val adapter = FIHomeProductListAdapter(aStaticMenu, activity)

        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

        mProductListRC!!.addItemDecoration(MiddleDividerItemDecoration(context!!, MiddleDividerItemDecoration.ALL))

        mProductListRC!!.layoutManager = manager

        mProductListRC!!.adapter = adapter


    }

    private fun toSetMonthlyItem() {

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

        val adapter = FIMonthlyProductAdapter(aStaticMenu, activity)

        mMonthlyProductRC!!.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        mMonthlyProductRC!!.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        (mContext as FIHomePageActivity).hideNormalHeader()
        mAutoScrollVP!!.resumeAutoScroll()
        activity?.let { Utils.hideKeyboard(it) }
    }

    override fun onPause() {
        mAutoScrollVP!!.pauseAutoScroll()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        (mContext as FIHomePageActivity).hideSearchHeader()
    }
}