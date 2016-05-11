package com.lechance.android.journal;

import java.util.ArrayList;
import java.util.List;

import com.lechance.android.journal.adapter.ListViewAdapter;
import com.lechance.android.journal.db.DBConstants;
import com.lechance.android.journal.db.DBHelper;
import com.lechance.android.journal.model.Journal;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class JournalDetailActivity extends Activity {
	Button btnPublish;
	Intent intent;
	ListView listView;
	SQLiteDatabase db;
	DBHelper helper;

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
		Cursor cursor = db.query(DBConstants.DATABASE_TABLE, null, null, null,
				null, null, null, null);
		List<Journal> list = new ArrayList<Journal>();

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
		setContentView(R.layout.journal_detail_activity);
		btnPublish = (Button) findViewById(R.id.btn_pulblish);
		listView = (ListView) findViewById(R.id.lv_display);
		listView.setOnItemClickListener(itemListener);
		intent = new Intent();
		btnPublish.setOnClickListener(listener);

		helper = new DBHelper(this);
	}

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
			case R.id.btn_pulblish:
			startActivity(new Intent(JournalDetailActivity.this,JournalPublishActivity.class));
				break;

			default:
				break;
			}
		}
	};
}
