package com.zhangzhiqiang.android.journal.adapter;

import java.util.List;

import com.zhangzhiqiang.android.journal.R;
import com.zhangzhiqiang.android.journal.model.Journal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DetailAdapter extends BaseAdapter {
	Context context;
	List<Journal> list;
	LayoutInflater inflater;

	public DetailAdapter(Context context, List<Journal> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub

		return list.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = inflater.inflate(R.layout.journal_listview_item, null);
		}
		TextView title = (TextView) view.findViewById(R.id.tv_title);
		TextView date = (TextView) view.findViewById(R.id.tv_date);
		Journal journal = list.get(position);
		title.setText(journal.getTitle());
		date.setText(journal.getDate());
		return view;
	}

}
