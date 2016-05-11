package com.zhangzhiqiang.android.journal;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zhangzhiqiang.android.journal.db.DBConstants;
import com.zhangzhiqiang.android.journal.db.DBHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class JournalPublishActivity extends Activity {
	SQLiteDatabase db;
	DBHelper helper;
	EditText eTitle, eContent;
	SimpleDateFormat format;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journal_publish_activity);
		helper = new DBHelper(this);
		eTitle = (EditText) findViewById(R.id.et_title);
		eContent = (EditText) findViewById(R.id.et_content);
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}

	public void btnOnClick(View v) {
		switch (v.getId()) {
		case R.id.btn_return:
			Log.i("info","--------btn_return");
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
		cValues.put(DBConstants.KEY_TITLE, eTitle.getText().toString());
		cValues.put(DBConstants.KEY_CONTENT, eContent.getText().toString());
		Date date = new Date();
		String value = format.format(date);
		cValues.put(DBConstants.KEY_DATE, value);

		db.insert(DBConstants.DATABASE_TABLE, null, cValues);
		db.close();
	}
}
