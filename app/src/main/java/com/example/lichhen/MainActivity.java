package com.example.lichhen;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	@Override

	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
   
	    final TabHost tabHost = getTabHost();


	    TabSpec themlichhen = tabHost.newTabSpec("Thêm Lịch Hẹn");

	    themlichhen.setIndicator("Thêm Lịch Hẹn", getResources().getDrawable(R.drawable.note));
	    Intent o = new Intent(this, ThemLichHenActivity.class);
	    themlichhen.setContent(o);

	    TabSpec xemlichhen = tabHost.newTabSpec("Xem Lịch Hẹn");
	    xemlichhen.setIndicator("Xem Lịch Hẹn", getResources().getDrawable(R.drawable.note));
	    Intent p = new Intent(this, ListLichHenMainActivity.class);
	    xemlichhen.setContent(p);

	    tabHost.addTab(themlichhen);
	    tabHost.addTab(xemlichhen);
	   tabHost.setCurrentTab(0);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		  switch (item.getItemId()) {
		    case R.id.thoat:
		        about();
		        return true;
		    default:
		        return super.onOptionsItemSelected(item);
		    }
		 
	}
	 public void about(){
		 System.exit(0);
	  }
}
