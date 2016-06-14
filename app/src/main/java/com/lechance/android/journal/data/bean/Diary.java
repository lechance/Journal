package com.lechance.android.journal.data.bean;

import android.support.annotation.Nullable;

import java.util.Arrays;
import java.util.UUID;

/**
 * Immutable model class for a Diary
 */

public final class Diary {

    private final String mId;

    private final String mTitle;

    private final String mSubTitle;

    private final String mContent;

    private final String mDate;

    /**
     * Use this constructor to create a new Diary
     */
    public Diary(String title, String subTitle,String content, String date) {
        mId = UUID.randomUUID().toString();
        this.mTitle = title;
        this.mSubTitle=subTitle;
        this.mContent = content;
        this.mDate = date;
    }

    public Diary(String id, String title,String subTitle, String content, String date) {
        this.mId = id;
        this.mTitle = title;
        this.mSubTitle=subTitle;
        this.mContent = content;
        this.mDate = date;
    }

    public String getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSubTitle() {
        return mSubTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public String getmDate() {
        return mDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Diary other = (Diary) o;
        return equal(mId, other.mId) &&
                equal(mTitle, other.mTitle) &&
                equal(mContent, other.mContent) &&
                equal(mDate, other.mDate);
    }

    private boolean equal(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    @Override
    public int hashCode() {
        return hashCode(mId, mTitle, mContent, mDate);
    }

    private int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }

    @Override
    public String toString() {
        return String.format("Diary:[ID:%s Title:%s]", mId, mTitle);
    }
}
