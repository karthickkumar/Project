package com.ocms.punchlist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.pick_photo.R;
import com.ocms.adapter.*;

public class ItemSectionListActivity extends Activity {

	ArrayList<String> headerName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.item_view);

		headerName = new ArrayList<String>();
		headerName.add("Search Criteria");
		headerName.add("Selection");
		headerName.add("Item");

	}

}