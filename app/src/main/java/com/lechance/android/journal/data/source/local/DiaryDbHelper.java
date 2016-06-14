package com.lechance.android.journal.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class was Created by lechance .
 */

public class DiaryDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Journal.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE" + DiaryDbContract.DiaryEntry.TABLE_NAME + "(" +
                    DiaryDbContract.DiaryEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + "PRIMARY KEY+" +
                    DiaryDbContract.DiaryEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    DiaryDbContract.DiaryEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + COMMA_SEP +
                    DiaryDbContract.DiaryEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    DiaryDbContract.DiaryEntry.COLUMN_NAME_CONTENT + TEXT_TYPE + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DiaryDbContract.DiaryEntry.TABLE_NAME;

    public DiaryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for note data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}
