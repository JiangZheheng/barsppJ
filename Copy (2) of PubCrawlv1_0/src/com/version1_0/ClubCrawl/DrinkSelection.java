package com.version1_0.ClubCrawl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import com.version1_0.ClubCrawl.R;

public class DrinkSelection extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drink_selection);
		// setting buttons for DrinkSelection Screen
		Button btnQuick = (Button) findViewById(R.id.btnQuick);
		Button btnPlan = (Button) findViewById(R.id.btnPlan);
		btnQuick.setOnClickListener(this);
		btnPlan.setOnClickListener(this);
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



	// Method for btnQuick layout change
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.btnQuick:
			
			intent.setClass(this, QuickDrink.class);
			break;
		case R.id.btnPlan:
			intent.setAction("android.intent.action.PlanCrawl");
			intent.addCategory(Intent.CATEGORY_DEFAULT);
		}
		startActivity(intent);

	}
}
