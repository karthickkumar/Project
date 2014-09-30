package com.ocms.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pick_photo.R;

public class ProjectAdapter extends BaseAdapter {

		String[] dataText;
		public Context mContext;


		public ProjectAdapter(Context mCtx, String[] text) {
			dataText = text;
			mContext = mCtx; 
		}

		public ProjectAdapter(Context mCtx,ArrayList<String> text) {
			
			mContext = mCtx;
			
			dataText = new String[text.size()];

			for (int i = 0; i < text.size(); i++) {
				dataText[i] = text.get(i);
			}

		}


		public int getCount() {
			return dataText.length;
		}

		public String getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			View row;

			row = inflater.inflate(R.layout.project_adapter, parent, false);

			TextView textview = (TextView) row.findViewById(R.id.project);
			ImageView imageview = (ImageView) row.findViewById(R.id.icon);

			textview.setText(dataText[position]);
			imageview.setImageResource(R.drawable.ic_launcher);

			return (row);

		}
	}

	