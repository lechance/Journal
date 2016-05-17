package com.lechance.android.journal.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.lechance.android.journal.R;
import com.lechance.android.journal.adapter.NoteAdapter;
import com.lechance.android.journal.db.DBConstants;
import com.lechance.android.journal.db.DBHelper;

/**
 * This class was Created by lechance on 17 May 2016 at 1:39 PM.
 */
public class OtherPreviewJournalActivity extends AppCompatActivity {

    private ListView journal_preview;
    private NoteAdapter noteAdapter;
    private DBHelper helper;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_main_preview_activity);
        init();
    }

    private void init() {
        journal_preview = (ListView) findViewById(R.id.pre_journal);
        helper = new DBHelper(this);
        db = helper.getReadableDatabase();
        cursor = db.query(DBConstants.DATABASE_TABLE, null, null, null, null, null, null);
        noteAdapter = new NoteAdapter(this, cursor, 0);
        journal_preview.setAdapter(noteAdapter);
    }
}
