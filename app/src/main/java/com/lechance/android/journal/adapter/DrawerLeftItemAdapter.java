package com.lechance.android.journal.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lechance.android.journal.R;

import java.util.ArrayList;

/**
 * This class was Created by lechance on 19 May 2016 at 3:19 AM.
 */
public class DrawerLeftItemAdapter extends BaseAdapter {

    public static final String TAG = "DrawerLeftItemAdapter";

    private Context context;
    private ArrayList<String> lists;

    public DrawerLeftItemAdapter(Context context, ArrayList<String> lists){
        this.context=context;
        this.lists=lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public String getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.journal_listview_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.drawer_listview_item_image);
            holder.item = (TextView) convertView.findViewById(R.id.drawer_listview_item_content);
            convertView.setTag(holder);

            Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<getView:: holder ->>>>>>>>>>>>>>>>>>>>>>>>>> " + holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        AsyncTask asyncTask=new AsyncTask<ViewHolder, Void, Bitmap>() {
            ViewHolder v;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Bitmap doInBackground(ViewHolder... params) {
                v = params[0];
                return BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
//                v.icon.setImageBitmap(bitmap);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.execute(holder);

        Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<getView:: holder ->>>>>>>>>>>>>>>>>>>>>>>>>> " + convertView);

        return convertView;
    }


    private static class ViewHolder {
        private ImageView icon;
        private TextView item;
    }
}
