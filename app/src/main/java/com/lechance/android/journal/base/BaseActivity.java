package com.lechance.android.journal.base;

import android.app.ProgressDialog;
import android.database.AbstractCursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * This class was Created by lechance on 16 May 2016 at 3:32 AM.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String INTENT_ACTION_EXIT_APP = "INTENT_ACTION_EXIT_APP";

    private boolean mIsVisible;
    private ProgressDialog mWaitDialog;

    private LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault();
    }

    @Override
    public void onClick(View v) {

        EventBus d;

        AbstractCursor abstractCursor = null;
        int columnIndex = abstractCursor.getColumnIndex("");
    }


}
