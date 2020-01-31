package com.app.fromindia.fragments

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity
import com.app.fromindia.adapter.FIProductListAdapter
import com.app.fromindia.model.MenuItem
import com.asksira.loopingviewpager.LoopingViewPager
import com.rd.PageIndicatorView
import kotlinx.android.synthetic.main.fragment_product_details.*


class FIProductDetailFragment : Fragment() {

    private var TAG = "FIProductDetailFragment"

    private var mFragmentManager: FIFragmentManager? = null

    private var mBackIM: AppCompatImageView? = null

    private var mProductList: LoopingViewPager? = null

    private var mPageIndicator: PageIndicatorView? = null

    private var mPRoductDetailIM: AppCompatImageView? = null

    private var mProductDetailTxt: AppCompatTextView? = null

    private var mProductDetailLAY: LinearLayout? = null

    private var mWishIM: AppCompatImageView? = null

    private var productMainDetailLAY: RelativeLayout? = null


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

        mPRoductDetailIM = aView!!.findViewById(R.id.productDetailIM) as AppCompatImageView

        mProductDetailTxt = aView!!.findViewById(R.id.productDetailTXT) as AppCompatTextView

        mProductDetailLAY = aView!!.findViewById(R.id.productDetailLAY) as LinearLayout

        productMainDetailLAY = aView!!.findViewById(R.id.productMainDetailLAY) as RelativeLayout


        mWishIM = aView!!.findViewById(R.id.myWishListIM) as AppCompatImageView

        mWishIM!!.visibility = View.GONE

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

        productMainDetailLAY!!.setOnClickListener {
            if (mProductDetailLAY!!.visibility == View.VISIBLE) {
                collapse(mProductDetailLAY)

            } else {
                expand(mProductDetailLAY!!)
            }
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


    fun expand(view: View) {
        mPRoductDetailIM!!.animate().rotation(180f).start();

        view.visibility = View.VISIBLE
        val widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(widthSpec, heightSpec)
        val mAnimator: ValueAnimator = this!!.slideAnimator(view, 0, view.measuredHeight)!!
        mAnimator.start()
    }

    private fun slideAnimator(v: View, start: Int, end: Int): ValueAnimator? {
        val animator = ValueAnimator.ofInt(start, end)
        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = v.layoutParams
            layoutParams.height = value
            v.layoutParams = layoutParams
        }
        return animator
    }

    fun collapse(view: LinearLayout?) {
        mPRoductDetailIM!!.animate().rotation(360f).start();

        val finalHeight = view!!.height
        val mAnimator = slideAnimator(view, finalHeight, 0)
        mAnimator!!.addListener(object : AnimatorListener {
            override fun onAnimationEnd(animator: Animator) {
                view.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        mAnimator.start()
    }
}