package com.app.fromindia;

import android.app.Application;


public class MyApplication extends Application {
    // public static AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //FontsOverride.setDefaultFont(this, "DEFAULT", "raleway_regular.ttf");
        //  This FontsOverride comes from the example I posted above

        // TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Bold.ttf");
/*
        mComponent = DaggerAppComponent
                .builder()
                .build();*/
    }
}
