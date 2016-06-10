package com.lechance.android.journal.util;

import android.os.AsyncTask;

import java.net.URL;

/**
 * This class was Created by lechance on 20 May 2016 at 12:41 AM.
 */
public class DownloadImageTask extends AsyncTask<URL, Integer, Long> {

    public static final String TAG = "DownloadImageTask";

    public DownloadImageTask() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(URL... params) {

        publishProgress(123);
        return null;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

}
