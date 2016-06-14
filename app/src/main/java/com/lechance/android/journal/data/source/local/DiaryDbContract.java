package com.lechance.android.journal.data.source.local;

import android.provider.BaseColumns;

/**
 * This class was Created by lechance
 */
public final class DiaryDbContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DiaryDbContract() {}

    /* Inner class that defines the table contents */
    public static abstract class DiaryEntry implements BaseColumns {
        public static final String TABLE_NAME = "diary";

        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_DATE = "date";
    }

}
