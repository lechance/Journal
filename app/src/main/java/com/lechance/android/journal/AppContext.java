package com.lechance.android.journal;

import android.content.Context;

import com.lechance.android.journal.base.BaseApplication;

/**
 * This class was Created by lechance on 16 May 2016 at 12:17 AM.
 */
public class AppContext extends BaseApplication{

    private static Context context;

    private AppContext(){}

    public static AppContext getInstance(){
        if(context==null){
            context=BaseApplication.context();
        }
        return (AppContext) context;
    }
}
