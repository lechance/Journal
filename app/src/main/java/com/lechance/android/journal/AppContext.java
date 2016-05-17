package com.lechance.android.journal;

import com.lechance.android.journal.base.BaseApplication;

import java.util.UUID;

/**
 * This class was Created by lechance on 16 May 2016 at 12:17 AM.
 */
public class AppContext extends BaseApplication {

    private static AppContext instance;

    public static AppContext getInstance() {
        if (instance == null) {
            instance = (AppContext) context();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getAppId() {
        //String uniqueId=getProperty(AppConfig.CONF_APP_UNIQUEID);
        String uniqueID = UUID.randomUUID().toString();
        //set property for uniqueID
        return uniqueID;
    }


}
