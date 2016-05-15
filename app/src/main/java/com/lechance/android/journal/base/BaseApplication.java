package com.lechance.android.journal.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class was Created by lechance on 16 May 2016 at 2:14 AM.
 */
public class BaseApplication extends Application {

    private final static String PREF_NAME = "journal_preference.pref";
    private final static String LAST_USED_TIME = "last_used_time.pref";
    private static String lastToast = "";
    private static long lastToastTime;
    private static Context mContext;
    private static Resources mResources;

    public static synchronized BaseApplication context() {
        return (BaseApplication) mContext;
    }

    public static Resources resources() {
        return mResources;
    }

    public static void putTotalNoteNumbers(String prefName, String key, int numbers) {
        SharedPreferences preferences = context().getSharedPreferences(prefName, Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, numbers);
        editor.apply();
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    public static void showToast(String message, int duration) {
        showToast(message, duration, 0);
    }

    public static void showToast(String message, int duration, int icon) {
        showToast(message, duration, icon, Gravity.BOTTOM);
    }

    public static void showToast(String message, int duration, int icon, int gravity, Object... args) {
        showToast(message, duration, icon, gravity);
    }


    public static void showToast(String message, int duration, int icon, int gravity) {
        if (message != null && !message.equals("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast) || Math.abs(time - lastToastTime) > 2000) {
                LinearLayout layout = new LinearLayout(context());
                layout.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView = new ImageView(context());
                TextView textView = new TextView(context());
                textView.setText(message);
                layout.addView(imageView);
                layout.addView(textView);

                if (icon != 0) {
                    imageView.setImageResource(icon);
                } else {
                    imageView.setVisibility(View.INVISIBLE);
                }

                Toast toast = new Toast(context());
                toast.setView(layout);
                toast.setDuration(duration);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 25);
                }
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mResources = mContext.getResources();
    }
}
