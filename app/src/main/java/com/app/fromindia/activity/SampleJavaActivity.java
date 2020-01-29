package com.app.fromindia.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.fromindia.R;
import com.app.fromindia.fragments.FIFragmentManager;
import com.app.fromindia.fragments.FIFragmentManagerTest;
import com.app.fromindia.model.DynamicMenu;
import com.app.fromindia.model.User;
import com.app.fromindia.retrofit.APIClient;
import com.app.fromindia.retrofit.APIService;

public class SampleJavaActivity extends AppCompatActivity {

    private FragmentActivity mContext;

    private FIFragmentManagerTest mFragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        DynamicMenu aMenu = new DynamicMenu();
        aMenu.setMenuName("Hey");
        mContext = SampleJavaActivity.this;
        mFragmentManager = new FIFragmentManagerTest(mContext);
        //  mFragmentManager = new FIFragmentManager(mContext);

    }

    private void updateFragment(Fragment aFragment, Context aContext) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_container, aFragment);
        transaction.commit();
        //APIClient aCli = new APIClient();

    }

    private User getValue() {
        User a = new User();
        return a;
    }
}
