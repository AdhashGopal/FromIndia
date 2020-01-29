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
import com.app.fromindia.adapter.FICustomAdapter
import com.app.fromindia.adapter.FIMyAccountAdapter
import com.app.fromindia.helper.CommonValues.*
import com.app.fromindia.model.DynamicMenu
import com.asksira.loopingviewpager.LoopingViewPager
import kotlinx.android.synthetic.main.activity_main2.*

class FIAddAddressFragment : Fragment() {

    private var TAG = "FIAddAddressFragment"

    private var mFragmentManager: FIFragmentManager? = null

    private var mBackIM: AppCompatImageView? = null

    private var mTitleTxt: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment

        val aView: View = inflater.inflate(R.layout.fragment_add_address, container,
                false)

        classWidgets(aView);

        return aView
    }

    private fun classWidgets(aView: View) {

        mFragmentManager = FIFragmentManager(activity)

        mBackIM = aView!!.findViewById(R.id.backIm) as AppCompatImageView

        mTitleTxt = aView!!.findViewById(R.id.headerTXT) as TextView

        mTitleTxt!!.text = getString(R.string.title_add_new_address)

        clickListener()
    }

    private fun clickListener() {
        mBackIM!!.setOnClickListener {

            mFragmentManager!!.backPress()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as FIHomePageActivity).hideToolbarHead()

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as FIHomePageActivity).showToolbarHead()

    }
}