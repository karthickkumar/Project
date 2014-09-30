package com.ocms.punchlist;

import java.util.ArrayList;
import java.util.List;

import com.example.pick_photo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchSelection extends Activity implements OnClickListener {

	private Spinner status, discipline, system, subSystem, category;
	private Button btnSubmit;
	ImageView add, search;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.search_selection);

		status = (Spinner) findViewById(R.id.status);
		discipline = (Spinner) findViewById(R.id.discipline);
		system = (Spinner) findViewById(R.id.system);
		subSystem = (Spinner) findViewById(R.id.sub_system);
		category = (Spinner) findViewById(R.id.category);
		add = (ImageView) findViewById(R.id.add);
		search = (ImageView) findViewById(R.id.search);

		add.setOnClickListener(this);
		search.setOnClickListener(this);

		List<String> list = new ArrayList<String>();
		list.add("Status 1");
		list.add("Status 2");
		list.add("Status 3");
		list.add("Status 4");
		list.add("Status 5");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		status.setAdapter(dataAdapter);

		List<String> list1 = new ArrayList<String>();
		list1.add("discipline 1");
		list1.add("discipline 2");
		list1.add("discipline 3");
		list1.add("discipline 4");
		list1.add("discipline 5");

		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list1);

		dataAdapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		discipline.setAdapter(dataAdapter1);

		List<String> list2 = new ArrayList<String>();
		list2.add("System 1");
		list2.add("System 2");
		list2.add("System 3");
		list2.add("System 4");
		list2.add("System 5");

		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list2);

		dataAdapter2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		system.setAdapter(dataAdapter2);

		List<String> list3 = new ArrayList<String>();
		list3.add("Sub System 1");
		list3.add("Sub System 2");
		list3.add("Sub System 3");
		list3.add("Sub System 4");
		list3.add("Sub System 5");

		ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list3);

		dataAdapter3
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		subSystem.setAdapter(dataAdapter3);

		List<String> list4 = new ArrayList<String>();
		list4.add("Category 1");
		list4.add("Category 2");
		list4.add("Category 3");
		list4.add("Category 4");
		list4.add("Category 5");

		ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list4);

		dataAdapter4
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		category.setAdapter(dataAdapter4);

		// Spinner item selection Listener
		addListenerOnSpinnerItemSelection();

	}

	// Add spinner data

	public void addListenerOnSpinnerItemSelection() {

		status.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		discipline
				.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	// get the selected dropdown list value

	public class CustomOnItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.add) {
			startActivity(new Intent(this, TabLayoutActivity.class));
			finish();
			overridePendingTransition(0, 0);
		}

	}
}