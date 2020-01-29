package com.app.fromindia.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.app.fromindia.fragments.FIFragmentManagerTest;

public class SampleTestActivity extends AppCompatActivity {

    private FragmentActivity mContext;

    private FIFragmentManagerTest mFragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle
            persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}