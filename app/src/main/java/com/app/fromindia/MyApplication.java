package com.app.fromindia;

import android.app.Application;

import com.app.fromindia.utils.TypefaceUtil;

public class MyApplication extends Application {
    //private AppModule mApiComponent;
    //  private D2EComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        //FontsOverride.setDefaultFont(this, "DEFAULT", "raleway_regular.ttf");
        //  This FontsOverride comes from the example I posted above

        // TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Bold.ttf");


    }
}
