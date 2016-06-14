package com.lechance.android.journal.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.lechance.android.journal.R;
import com.lechance.android.journal.data.source.local.DiaryDbConstants;
import com.lechance.android.journal.data.source.local.DiaryDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JournalPublishActivity extends Activity {
    SQLiteDatabase db;
    DiaryDbHelper helper;
    EditText eTitle, eContent;
    SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_diary);
        helper = new DiaryDbHelper(this);
        eTitle = (EditText) findViewById(R.id.et_title);
        eContent = (EditText) findViewById(R.id.et_content);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    }

    public void btnOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_return:
                Log.i("info", "--------btn_return");
                finish();
                break;
            case R.id.btn_publish_edit:
                addData();
                finish();
                break;
            default:
                break;
        }
    }

    private void addData() {
        db = helper.getReadableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(DiaryDbConstants.KEY_TITLE, eTitle.getText().toString());
        cValues.put(DiaryDbConstants.KEY_CONTENT, eContent.getText().toString());
        Date date = new Date();
        String value = format.format(date);
        cValues.put(DiaryDbConstants.KEY_DATE, value);

        db.insert(DiaryDbConstants.DATABASE_TABLE, null, cValues);
        db.close();
    }
}
