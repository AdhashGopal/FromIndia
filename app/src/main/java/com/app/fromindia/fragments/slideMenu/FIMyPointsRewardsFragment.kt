package com.app.fromindia.fragments.slideMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.fromindia.R
import com.app.fromindia.activity.FIHomePageActivity

class FIMyPointsRewardsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        // classWidgets(aView);
        return inflater.inflate(R.layout.fragment_my_rewards, container, false)
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

}