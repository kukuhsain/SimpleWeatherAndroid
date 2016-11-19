package com.kukuhsain.simple.weather;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by kukuh on 08/10/16.
 */

public class SimpleApp extends Application {
    private static SimpleApp INSTANCE;

    public static SimpleApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.d("Application onCreate...");
    }
}
