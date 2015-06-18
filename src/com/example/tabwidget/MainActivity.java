package com.example.tabwidget;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("TAG", "------test");
		TabHost tabHost = (TabHost) this.findViewById(android.R.id.tabhost);
		 
        // tabhost如果是以findViewById()这个方法获取的，必须调用setup()方法
        tabHost.setup();
 
        View view1 = this.getLayoutInflater().inflate(R.layout.custom, null);
        TextView tv1 = (TextView) view1.findViewById(R.id.tv);
 
        tv1.setText("tab1");
 
        View view2 = this.getLayoutInflater().inflate(R.layout.custom, null);
        
        TextView tv2 = (TextView) view2.findViewById(R.id.tv);
 
        tv2.setText("tab2");
 
        TabSpec spec1 = tabHost.newTabSpec("tab1").setIndicator(view1)
                .setContent(R.id.tabs1);
 
        TabSpec spec2 = tabHost.newTabSpec("tab2").setIndicator(view2)
                .setContent(R.id.tabs2);
 
        tabHost.addTab(spec1);
 
        tabHost.addTab(spec2);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
