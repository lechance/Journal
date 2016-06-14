package com.lechance.android.journal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lechance.android.journal.R;
import com.lechance.android.journal.data.Journal;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    public static final String TAG = "ListViewAdapter";
    Context context;
    List<Journal> list;

    public ListViewAdapter(Context context, List<Journal> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else
            return 0;
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Journal j = list.get(position);
        if (convertView == null) {
            holder=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.view_item_listview, null);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.icon = (ImageView) convertView.findViewById(R.id.tv_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(j.getTitle());
        holder.date.setText(j.getDate());
        holder.icon.setImageResource(R.drawable.ic_content_paste_black_24dp);
        return convertView;
    }

    private static final class ViewHolder {
        TextView title;
        TextView date;
        ImageView icon;
    }

}
