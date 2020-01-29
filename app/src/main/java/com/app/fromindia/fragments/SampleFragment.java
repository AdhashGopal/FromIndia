package com.app.fromindia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fromindia.R;
import com.app.fromindia.activity.FIHomePageActivity;

public class SampleFragment extends Fragment {
    FragmentActivity mContex;

    private RecyclerView mNRC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View aView = inflater.inflate(R.layout.fragment_my_notification, container, false);
        classWidgets(aView);
        return aView;
    }

    private void classWidgets(View aView) {

        mNRC = (RecyclerView) aView.findViewById(R.id.notificationRC);

    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
