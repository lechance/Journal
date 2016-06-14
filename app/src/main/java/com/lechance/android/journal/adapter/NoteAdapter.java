package com.lechance.android.journal.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lechance.android.journal.R;

/**
 * This class was Created by lechance on 17 May 2016 at 12:59 PM.
 */
public class NoteAdapter extends CursorAdapter {

    public NoteAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_note_item, null);
        ViewHolder holder = new ViewHolder();
        holder.noteImage = (ImageView) view.findViewById(R.id.note_image);
        holder.noteAbstract = (TextView) view.findViewById(R.id.note_abstract);
        holder.imgIndex = cursor.getColumnIndex("_preImg");
        holder.abstractIndex = cursor.getColumnIndexOrThrow("_title");
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder holder=(ViewHolder)view.getTag();
        holder.noteImage.setImageResource(R.drawable.ic_launcher);
        holder.noteAbstract.setText(cursor.getString(holder.abstractIndex));
    }

    private static class ViewHolder {
        int imgIndex;
        int abstractIndex;
        ImageView noteImage;
        TextView noteAbstract;
    }
}
