package com.lechance.android.journal.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * This class was Created by lechance on 20 May 2016 at 12:39 AM.
 */
public class MyServices extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
