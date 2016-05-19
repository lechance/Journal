package com.lechance.android.journal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lechance.android.journal.R;

import java.util.List;
import java.util.Map;

/**
 * This class was Created by lechance on 19 May 2016 at 3:19 AM.
 */
public class DrawerLeftItemAdapter extends ArrayAdapter {

    private Context context;
    private ViewHolder holder;

    public DrawerLeftItemAdapter(Context context){
        this(context,0);
    }
    public DrawerLeftItemAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (holder == null) {
            if (holder.layout ==null) {
                holder.layout=LayoutInflater.from(context).inflate(R.layout.listview_item_drawer, parent);
            }
            holder = new ViewHolder();
            holder.icon = (ImageView) holder.layout.findViewById(R.id.drawer_listview_item_image);
            holder.item = (TextView) holder.layout.findViewById(R.id.drawer_listview_item_content);
            convertView.setTag(holder);
        }
        holder= (ViewHolder) convertView.getTag();
        holder.icon.setImageResource(R.drawable.ic_launcher);
        holder.item.setText(getItem(position).toString());

        return holder.layout;
    }

    private static class ViewHolder {
        private View layout;
        private ImageView icon;
        private TextView item;
    }
}
