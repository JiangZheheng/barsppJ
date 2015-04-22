package com.version1_0.ClubCrawl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.version1_0.ClubCrawl.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * This page requires a basic menu Functionality should include Load crawl
 * button placed at the top right Save and start Crawl button placed at top left
 * (deactivated unless two or more bars are in the list) - Once clicked a
 * message pops up to allow input of a name with save and cancel buttons, once
 * submitted then the user is taken to the Crawl page
 * 
 * A list of current bars which can be reordered by drag and drop, removed by a
 * double click
 * 
 * Swipe left to reveal all bars ordered by distance away from current location
 * - contains a filter at the top with categories - to be defined - from here
 * they can be added to the crawl by double click - long press reveals
 * additional information in a pop-up with a add to crawl button and back button
 * - potential 'bar not here? Add it' option
 * 
 * Swipe right to see currently selected bars and route displayed on a map -
 * numbered place holders?
 * 
 * @author Matt
 * 
 */
public class PlanCrawl extends Activity {
	List<CheckBox> clubs = new ArrayList<CheckBox>();
	LinkedList<Club> clubList = new LinkedList<Club>();

	/**
	 * add the component of the view
	 * @author Jiang Zhe Heng
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_crawl);
		Button start = (Button) findViewById(R.id.start);
		clubs.add((CheckBox) findViewById(R.id.club1));
		clubs.add((CheckBox) findViewById(R.id.club2));
		clubs.add((CheckBox) findViewById(R.id.club3));
		// automatically set the club names into the checkbox
		for (int loop = 0; loop < clubs.size(); loop++) {
			clubs.get(loop).setText(Club.values()[loop].getName());
		}

		//set OnClickListener for start button
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//check if the checkbox is checked
				for (int loop = 0; loop < clubs.size(); loop++) {
					if (clubs.get(loop).isChecked()) {
						clubList.add(Club.values()[loop]);
						Log.i(clubList.peek().getName(), "ewfefef");
					}
				}
				//if there are some clubs been checked,store them into hash map
				if (!clubList.isEmpty()) {
					MyApplication.getInstance().getHashMap()
							.put("clubList", clubList);
					Intent intent = new Intent();
					intent.setClassName("com.version1_0.ClubCrawl",
							"com.version1_0.GoogleMap.GoogleMaps");
					startActivity(intent);
				}
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plan_crawl, menu);
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
