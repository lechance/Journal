package com.lechance.android.journal.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * This class was Created by lechance on 16 May 2016 at 5:20 PM.
 */
public class MyService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
