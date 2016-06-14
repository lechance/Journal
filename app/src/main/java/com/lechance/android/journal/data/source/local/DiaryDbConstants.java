package com.lechance.android.journal.data.source.local;

public final class DiaryDbConstants {

	/**
	 * Database Name
	 */
	public static final String DATABASE_NAME = "journal.db";
	/**
	 * Database Version
	 */
	public static final int DATABASE_VERSION = 1;
	/**
	 * Table Name
	 */
	public static final String DATABASE_TABLE = "tb_journal";

	/**
	 * Table columns
	 */
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "_title";
	public static final String KEY_CONTENT = "_content";
	public static final String KEY_DATE = "_date";
	/**
	 * Database creation sql statement
	 */
	
	//create table journal.db (_id integer primary key autoincrement,
	//							_title text not null,
	//							_content text not null,
	//							_date text not null);
	
	public static final String CREATE_JOURNAL_TABLE = "create table"
			+ DATABASE_NAME + "(" + KEY_ROWID
			+ "integer primary key autoincrement," + KEY_TITLE
			+ "text not null," + KEY_CONTENT + "text not null," + KEY_DATE
			+ "text not null);";
}
