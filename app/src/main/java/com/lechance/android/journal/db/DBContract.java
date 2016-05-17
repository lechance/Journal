package com.lechance.android.journal.db;

import android.provider.BaseColumns;

/**
 * This class was Created by lechance on 16 May 2016 at 1:57 AM.
 */
public final class DBContract {

    public static final String DATABASE_NAME="journal_database.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NOTES = "tb_notes";


    public static abstract class noteEntry implements BaseColumns{

        public static final String TABLE_NAME="person";
        public static final String PERSON_COLUMN_ID = "_id";
        public static final String PERSON_COLUMN_NAME = "name";
        public static final String PERSON_COLUMN_GENDER = "gender";
        public static final String PERSON_COLUMN_AGE = "age";
    }
}
