package com.lechance.android.journal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

/**
 * This class was Created by lechance on 19 May 2016 at 11:33 PM.
 */
public class AppManager extends Application {

    public static final String TAG = "AppManager";

    private static Context _context;
    private static Resources _resources;
    private static SharedPreferences _sharedPreference;

    public static PackageInfo getPackageInfo() {
        try {
            return _context.getPackageManager().getPackageInfo(_context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        _resources = getResources();
        _sharedPreference = _context.getSharedPreferences(_context.getPackageName(), MODE_PRIVATE);
    }
}
