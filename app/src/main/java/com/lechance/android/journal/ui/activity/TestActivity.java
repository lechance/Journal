package com.lechance.android.journal.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lechance.android.journal.R;
import com.lechance.android.journal.ui.fragment.TestFragment;

/**
 * This class was Created by lechance on 21 May 2016 at 12:23 AM.
 */

public class TestActivity extends Activity {
    static int stackNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");
        setContentView(R.layout.journal_detail_activity);
        stackNum++;
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            TestFragment fragment=TestFragment.newInstance(stackNum);
            fragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().replace(R.id.layout_header, fragment).commit();
        }

        StackTraceElement[] s=Thread.currentThread().getStackTrace();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        log("onAttachFragment");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("onDestroy");
    }

    private void log(String method) {
        Log.i(this.getClass().toString(), "----------- " + method + " ---------");
    }
}
