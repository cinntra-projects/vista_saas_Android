package com.cinntra.vista.EasyPrefs;

import android.app.Application;

import java.io.File;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new Prefs.Builder()
                .setContext(this)
                .setMode(MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        Prefs.initPrefs(this);

        File dexOutputDir = getCodeCacheDir();
        dexOutputDir.setReadOnly();
    }
}
