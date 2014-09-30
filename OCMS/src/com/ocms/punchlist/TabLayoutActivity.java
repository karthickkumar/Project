package com.ocms.punchlist;

import com.example.pick_photo.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class TabLayoutActivity extends TabActivity implements OnClickListener{

    // Divide 1.0 by # of tabs needed
    // In this case: 1.0/2 => 0.5
    private static final LayoutParams params = new LinearLayout.LayoutParams(
            LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 0.5f);

    private static TabHost tabHost;
    private static TabHost.TabSpec spec;
    private static Intent intent;
    private static LayoutInflater inflater;
    ImageView add, search;
    private View tab;
    private TextView label;
    private TextView divider;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      //Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.tabview);
        
        add = (ImageView) findViewById(R.id.add);
		search = (ImageView) findViewById(R.id.search);
		
		add.setOnClickListener(this);
		search.setOnClickListener(this);
        
        // Get inflator so we can start creating the custom view for tab
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        // Get tab manager
        tabHost = getTabHost();
        
        // This converts the custom tab view we created and injects it into the tab widget
        tab = inflater.inflate(R.layout.tab, getTabWidget(), false);
        // Mainly used to set the weight on the tab so each is equally wide
        tab.setLayoutParams(params);
        // Add some text to the tab
        label = (TextView) tab.findViewById(R.id.tabLabel);
        label.setText("ITEM");
        // Show a thick line under the selected tab (there are many ways to show
        // which tab is selected, I chose this)
        divider = (TextView) tab.findViewById(R.id.tabSelectedDivider);
        divider.setVisibility(View.VISIBLE);
        // Intent whose generated content will be added to the tab content area
        intent = new Intent(this, ItemSectionListActivity.class);
        // Just some data for the tab content activity to use (just for demonstrating changing content)
        intent.putExtra("content", "Content for HOME");
        // Finalize the tabs specification
        spec = tabHost.newTabSpec("item").setIndicator(tab).setContent(intent);
        // Add the tab to the tab manager
        tabHost.addTab(spec);
        
        
        // Add another tab
        tab = inflater.inflate(R.layout.tab, getTabWidget(), false);
        tab.setLayoutParams(params);
        label = (TextView) tab.findViewById(R.id.tabLabel);
        label.setText("ATTACH");
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("content", "Content for USERS");
        spec = tabHost.newTabSpec("attach").setIndicator(tab).setContent(intent);
        tabHost.addTab(spec);
        
        
        // Listener to detect when a tab has changed. I added this just to show 
        // how you can change UI to emphasize the selected tab
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(String tag) {
                // reset some styles
                clearTabStyles();
                View tabView = null;
                // Use the "tag" for the tab spec to determine which tab is selected
                if (tag.equals("item")) {
                    tabView = getTabWidget().getChildAt(0);
                }
                else if (tag.equals("attach")) {
                    tabView = getTabWidget().getChildAt(1);
                }
                tabView.findViewById(R.id.tabSelectedDivider).setVisibility(View.VISIBLE);
            }       
        });
    }
    
    private void clearTabStyles() {
        for (int i = 0; i < getTabWidget().getChildCount(); i++) {
            tab = getTabWidget().getChildAt(i);
            tab.findViewById(R.id.tabSelectedDivider).setVisibility(View.GONE);
        }
    }
    
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.search) {
			startActivity(new Intent(this, SearchSelection.class));
			finish();
			overridePendingTransition(0, 0);
		} 

	}
}