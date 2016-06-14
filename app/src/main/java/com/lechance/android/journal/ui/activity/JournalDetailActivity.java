package com.lechance.android.journal.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.lechance.android.journal.R;
import com.lechance.android.journal.adapter.DrawerLeftItemAdapter;
import com.lechance.android.journal.adapter.ListViewAdapter;
import com.lechance.android.journal.data.Journal;
import com.lechance.android.journal.data.source.local.DiaryDbContract;
import com.lechance.android.journal.data.source.local.DiaryDbHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JournalDetailActivity extends Activity {

    Button btnPublish;
    Intent intent;
    ListView listView;
    SQLiteDatabase db;
    DiaryDbHelper helper;
    // itemListener
    OnItemClickListener itemListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // TODO Auto-generated method stub
            Journal journal = (Journal) parent.getItemAtPosition(position);
            intent = new Intent(JournalDetailActivity.this,
                    JournalEditActivity.class);
            intent.putExtra("journal", journal);
            startActivity(intent);
        }
    };
    // clicklistener events
    OnClickListener listener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
//                case R.id.btn_pulblish:
//                    startActivity(new Intent(JournalDetailActivity.this, JournalPublishActivity.class));
//                    break;
//                case R.id.btn_other_preview:
//                    startActivity(new Intent(JournalDetailActivity.this, OtherPreviewJournalActivity.class));
//                    break;
                default:
                    break;
            }
        }
    };
    private boolean doubleBackTOExitPressedOnce = false;
    @BindView(R.id.drawer_layout)
    private DrawerLayout drawerLayout;
    private ListView left_drawer;

    @Override
    public void onBackPressed() {
/*        if (doubleBackTOExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackTOExitPressedOnce = true;
        BaseApplication.showToast("Please click Back again to exit", 2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackTOExitPressedOnce = false;
            }
        }, 2000);*/

        new AlertDialog.Builder(getApplicationContext())
                .setTitle("Do you ")
                .setIcon(R.drawable.ic_launcher)
                .setMessage("Ensure to exit app")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("journal", "which: " + which);
                    }
                })
                .create().show();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        db = helper.getReadableDatabase();
        queryJournal();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        db.close();
    }

    // query
    private void queryJournal() {
        Cursor cursor = db.query(DiaryDbContract.DiaryEntry.TABLE_NAME, null, null, null,
                null, null, null, null);
        List<Journal> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            Journal journal = new Journal();
            int index = 0;
            journal.setId(cursor.getInt(index++));
            journal.setTitle(cursor.getString(index++));
            journal.setContent(cursor.getString(index++));
            journal.setDate(cursor.getString(index++));
            list.add(journal);
        }
        ListViewAdapter adapter = new ListViewAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail);
//        btnPublish = (Button) findViewById(R.id.btn_pulblish);
        listView = (ListView) findViewById(R.id.lv_display);
        listView.setOnItemClickListener(itemListener);
        intent = new Intent();
//        btnPublish.setOnClickListener(listener);
//        Button other = (Button) findViewById(R.id.btn_other_preview);
//        other.setOnClickListener(listener);
        ButterKnife.bind(this);
        helper = new DiaryDbHelper(this);
        left_drawer = (ListView) findViewById(R.id.left_drawer);
        left_drawer.setAdapter(new DrawerLeftItemAdapter(this, getLists()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private ArrayList<String> getLists() {
        ArrayList<String> lists = new ArrayList<>();
        for (int i = 0, len = 20; i < len; i++)
            lists.add("Item " + i);
        return lists;
    }

    public static class meDialog extends android.support.v4.app.DialogFragment {

        public meDialog() {
            super();
        }


    }
}
