package com.lechance.android.journal.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.lechance.android.journal.data.bean.Diary;
import com.lechance.android.journal.data.source.DiaryDataSource;

import java.util.ArrayList;
import java.util.List;


/**
 * This class was Created by lechance .
 */
public class DiaryLocalDataSource implements DiaryDataSource {

    public static final String TAG = "DiaryLocalDataSource";

    private static DiaryLocalDataSource INSTANCE;
    private DiaryDbHelper mDbHelper;

    public DiaryLocalDataSource(@NonNull Context context) {
        mDbHelper = new DiaryDbHelper(context);
    }

    public static DiaryLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DiaryLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getDiarys(@NonNull LoadDiarysCallback callback) {
        List<Diary> diaries = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DiaryDbContract.DiaryEntry.COLUMN_NAME_ENTRY_ID,
                DiaryDbContract.DiaryEntry.COLUMN_NAME_DATE,
                DiaryDbContract.DiaryEntry.COLUMN_NAME_TITLE,
                DiaryDbContract.DiaryEntry.COLUMN_NAME_SUBTITLE,
                DiaryDbContract.DiaryEntry.COLUMN_NAME_CONTENT
        };

        Cursor c = db.query(
                DiaryDbContract.DiaryEntry.TABLE_NAME, projection, null, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String mId = c.getString(c.getColumnIndex(DiaryDbContract.DiaryEntry.COLUMN_NAME_ENTRY_ID));
                String mDate = c.getString(c.getColumnIndexOrThrow(DiaryDbContract.DiaryEntry.COLUMN_NAME_DATE));
                String mTitle = c.getString(c.getColumnIndexOrThrow(DiaryDbContract.DiaryEntry.COLUMN_NAME_TITLE));
                String mSubTitle = c.getString(c.getColumnIndexOrThrow(DiaryDbContract.DiaryEntry.COLUMN_NAME_SUBTITLE));
                String mContent = c.getString(c.getColumnIndexOrThrow(DiaryDbContract.DiaryEntry.COLUMN_NAME_CONTENT));
                Diary diary = new Diary(mId, mTitle, mSubTitle, mContent, mDate);
                diaries.add(diary);
            }
        }
        if (c != null) {
            db.close();
        }
        db.close();
        if (diaries.isEmpty()) {
            //when table is new or just empty
            callback.onDataNotAvailable();
        } else {
            callback.onDiaryLoad(diaries);
        }
    }

    @Override
    public void getDiary(@NonNull String diaryId, @NonNull GetDiaryCallback callback) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                DiaryDbContract.DiaryEntry.COLUMN_NAME_ENTRY_ID,
                DiaryDbContract.DiaryEntry.COLUMN_NAME_DATE,
                DiaryDbContract.DiaryEntry.COLUMN_NAME_TITLE,
                DiaryDbContract.DiaryEntry.COLUMN_NAME_SUBTITLE,
                DiaryDbContract.DiaryEntry.COLUMN_NAME_CONTENT
        };

        String selection = DiaryDbContract.DiaryEntry.COLUMN_NAME_ENTRY_ID + " Like ?";
        String[] selectionarg = {diaryId};

        Cursor c = db.query(
                DiaryDbContract.DiaryEntry.TABLE_NAME, projection, selection, selectionarg, null, null, null);
        Diary diary = null;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            String mId = c.getString(c.getColumnIndex(DiaryDbContract.DiaryEntry.COLUMN_NAME_ENTRY_ID));
            String mDate = c.getString(c.getColumnIndexOrThrow(DiaryDbContract.DiaryEntry.COLUMN_NAME_DATE));
            String mTitle = c.getString(c.getColumnIndexOrThrow(DiaryDbContract.DiaryEntry.COLUMN_NAME_TITLE));
            String mSubTitle = c.getString(c.getColumnIndexOrThrow(DiaryDbContract.DiaryEntry.COLUMN_NAME_SUBTITLE));
            String mContent = c.getString(c.getColumnIndexOrThrow(DiaryDbContract.DiaryEntry.COLUMN_NAME_CONTENT));
            diary = new Diary(mId, mTitle, mSubTitle, mContent, mDate);
        }
        if (c != null) {
            c.close();
        }
        db.close();
        if (diary == null) {
            callback.onDataNotAvailable();
        } else {
            callback.onDiaryLoaded(diary);
        }
    }

    @Override
    public void saveDiary(@NonNull Diary diary) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DiaryDbContract.DiaryEntry.COLUMN_NAME_ENTRY_ID, diary.getmId());
        values.put(DiaryDbContract.DiaryEntry.COLUMN_NAME_DATE, diary.getmDate());
        values.put(DiaryDbContract.DiaryEntry.COLUMN_NAME_TITLE, diary.getmTitle());
        values.put(DiaryDbContract.DiaryEntry.COLUMN_NAME_SUBTITLE, diary.getmSubTitle());
        values.put(DiaryDbContract.DiaryEntry.COLUMN_NAME_CONTENT, diary.getmContent());

        db.insert(DiaryDbContract.DiaryEntry.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void refreshDiarys() {

    }

    @Override
    public void deleteAllDiarys() {
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        db.delete(DiaryDbContract.DiaryEntry.TABLE_NAME,null,null);
        db.close();
    }

    @Override
    public void deleteDiary(@NonNull String diaryId) {
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        String selection=DiaryDbContract.DiaryEntry.COLUMN_NAME_ENTRY_ID+" LIKE ?";
        String[] args={diaryId};
        db.delete(DiaryDbContract.DiaryEntry.TABLE_NAME,selection,args);
        db.close();
    }
}
