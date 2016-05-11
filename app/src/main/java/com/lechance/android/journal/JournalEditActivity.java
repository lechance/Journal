package com.lechance.android.journal;

import com.lechance.android.journal.db.DBConstants;
import com.lechance.android.journal.db.DBHelper;
import com.lechance.android.journal.model.Journal;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JournalEditActivity extends Activity {
	Journal journal;
	TextView tTitle;
	EditText eTitle;
	EditText eContent;
	/**
	 * flag is true --edit flag is false -- submit
	 */
	boolean flag = true;
	DBHelper helper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journal_edit_activity);
		Intent intent = getIntent();
		if (intent != null) {
			journal = (Journal) intent.getSerializableExtra("journal");
		}
		tTitle = (TextView) findViewById(R.id.tv_top_title);
		eTitle = (EditText) findViewById(R.id.et_edit_title);
		eContent = (EditText) findViewById(R.id.et_edit_content);

		tTitle.setText(journal.getTitle());
		eTitle.setText(journal.getTitle());
		eContent.setText(journal.getContent());
		eContent.setEnabled(false);
		eTitle.setVisibility(View.GONE);
		helper = new DBHelper(this);
	}

	public void btnOnClick(View view) {
		Log.i("info", "msg--------btnOnClick");
		switch (view.getId()) {
		case R.id.btn_edit_return:
			finish();
			break;
		case R.id.btn_edit:
			if (flag) {
				edit();
			} else {
				submit();
				finish();
			}
			break;
		default:
			break;
		}
	}

	private void edit() {
		eTitle.setVisibility(View.VISIBLE);
		eContent.setEnabled(true);
		flag = false;
	}

	private void submit() {
		db = helper.getReadableDatabase();
		ContentValues cValues = new ContentValues();
		cValues.put(DBConstants.KEY_TITLE, eTitle.getText().toString());
		cValues.put(DBConstants.KEY_CONTENT, eContent.getText().toString());
		db.update(DBConstants.DATABASE_TABLE, cValues, DBConstants.KEY_ROWID
				+ "=?", new String[] { "" + journal.getId() });
		db.close();
	}
}
