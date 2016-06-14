package com.lechance.android.journal.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lechance.android.journal.R;

/**
 * This class was Created by lechance on 21 May 2016 at 12:15 AM.
 */
public class TestFragment extends Fragment {

    public static final String TAG = "TestFragment";
    private int num;
    private boolean mDualPane;
    private int mCurCheckPosition = 0;

    public static TestFragment newInstance(int num) {
        TestFragment fragment = new TestFragment();
        Bundle arg = new Bundle();
        arg.putInt("num", num);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        log("onAttach");
        getActivity();
        new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,new String[]{});
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");
        num = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        log("onCreateView");
        View view = inflater.inflate(R.layout.view_item_listview, null);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        title.setText("Fragment# " + num);
        title.setBackgroundResource(android.R.drawable.screen_background_dark);
//        return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        log("onViewCreated");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log("onActivityCreated");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        log("onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        log("onStart");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        log("onCreateOptionMenu");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        log("onPrepareOptionMenu");
    }

    @Override
    public void onPause() {
        super.onPause();
        log("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        log("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log("onDetach");
    }

    private void log(String method) {
        Log.i(TAG, "----------- " + method + " ---------");
    }
}
