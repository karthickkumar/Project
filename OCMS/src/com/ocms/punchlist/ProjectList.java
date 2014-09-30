package com.ocms.punchlist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import com.ocms.adapter.*;
import com.example.pick_photo.R;

public class ProjectList extends Activity implements OnClickListener {
	EditText edittext;
	ListView listview;
	ImageView add, search;
	String[] text = { "One", "Two", "Three", "Four", "Five", "Six", "Seven",
			"Eight", "Nine", "Ten" };

	int textlength = 0;
	ArrayList<String> textSort = new ArrayList<String>();

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.project_list);

		add = (ImageView) findViewById(R.id.add);
		search = (ImageView) findViewById(R.id.search);
		edittext = (EditText) findViewById(R.id.filter_project);
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(new ProjectAdapter(this,text));

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				// startActivity(new
				// Intent(ProjectList.this,SearchSelection.class));
			}
		});

		edittext.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				textlength = edittext.getText().length();
				textSort.clear();

				for (int i = 0; i < text.length; i++) {
					if (textlength <= text[i].length()) {
						if (edittext
								.getText()
								.toString()
								.equalsIgnoreCase(
										(String) text[i].subSequence(0,
												textlength))) {
							textSort.add(text[i]);
						}
					}
				}

				listview.setAdapter(new ProjectAdapter(ProjectList.this,textSort));

			}
		});
		add.setOnClickListener(this);
		search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.search) {
			startActivity(new Intent(this, SearchSelection.class));
			//finish();
			overridePendingTransition(0,0);
		}else if(v.getId() == R.id.add){
			startActivity(new Intent(this,TabLayoutActivity.class));
			//finish();
			overridePendingTransition(0,0);
		}

	}
}