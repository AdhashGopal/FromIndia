package com.app.fromindia.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fromindia.fragments.FIFragmentManager;
import com.app.fromindia.fragments.FIFragmentManagerTest;
import com.app.fromindia.fragments.SampleFragment;

public class SampleAdapter extends RecyclerView.Adapter<FIStaticMenuAdapter.ViewHolder> {

    private FIFragmentManager mFg;

    SampleAdapter(FragmentActivity aContext) {
        mFg = new FIFragmentManager(aContext);
    }

    @NonNull
    @Override
    public FIStaticMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FIStaticMenuAdapter.ViewHolder holder, int position) {
        mFg.updateContent(new SampleFragment(), "", null);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
