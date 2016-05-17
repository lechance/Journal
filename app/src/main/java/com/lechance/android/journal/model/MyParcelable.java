package com.lechance.android.journal.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class was Created by lechance on 16 May 2016 at 5:22 PM.
 */
public class MyParcelable implements Parcelable {

    private int mData;


    private MyParcelable(Parcel in){
        mData=in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mData);
    }

    public static final Parcelable.Creator<MyParcelable> CREATOR = new Parcelable.Creator<MyParcelable>(){
        @Override
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };

}
