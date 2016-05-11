package com.lechance.android.journal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	// journal _id,_title ,_content,_date
	String create_table ="create table if not exists "+DBConstants.DATABASE_TABLE
			+"("+DBConstants.KEY_ROWID+" integer primary key autoincrement, "
			+DBConstants.KEY_TITLE+" text, "
			+DBConstants.KEY_CONTENT +" text, "
			+DBConstants.KEY_DATE+" text )";
	// drop table
	String drop_table_journal = "drop table " + DBConstants.DATABASE_TABLE;

	public DBHelper(Context context) {
		super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// db.execSQL("CREATE TABLE Journal (_id INTEGER KEY AUTOINCREMENT,_title TEXT,_content TEXT)");
		db.execSQL(create_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(drop_table_journal);
		onCreate(db);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}

}
