package com.version1_0.ClubCrawl;

/**
 * the class represents the set clock activity, the user can set the clock in this activity
 */
import java.util.Calendar;

import com.version1_0.ClubCrawl.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class SetClock extends Activity implements OnClickListener {

	private TextView alarmRecord;
	// private SharedPreferences sharedPreferences;
	private View dialogView;
	private TimePicker timePicker;
	Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_clock);
		Button addAlarm1 = (Button) findViewById(R.id.BsetBySelf_activityMain);
		Button addAlarm2 = (Button) findViewById(R.id.B30minutes_activityMain);
		Button addAlarm3 = (Button) findViewById(R.id.B45minutes_activityMain);
		Button addAlarm4 = (Button) findViewById(R.id.B1hour_activityMain);
		Button addAlarm5 = (Button) findViewById(R.id.B1_5hour_activityMain);
		alarmRecord = (TextView) findViewById(R.id.TVleave_activityMain);
		addAlarm1.setOnClickListener(this);
		addAlarm2.setOnClickListener(this);
		addAlarm3.setOnClickListener(this);
		addAlarm4.setOnClickListener(this);
		addAlarm5.setOnClickListener(this);
		// sharedPreferences = getSharedPreferences("alarm_record",
		// Activity.MODE_PRIVATE);

		AlarmManager aManager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
		intent = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
				intent, 0);
		aManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 60 * 1000,
				pendingIntent);

	}

	@Override
	protected void onResume() {
		super.onResume();
		if(MyApplication.getInstance().getHashMap().get("settingTime")!=null){
		alarmRecord.setText("I will leave at :"
				+ MyApplication.getInstance().getHashMap().get("settingTime"));
		}

	}

	@Override
	protected void onStop() {
		
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		MyApplication.getInstance().getHashMap().clear();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.BsetBySelf_activityMain:
			showAddAlarm(0, 0);
			break;
		case R.id.B30minutes_activityMain:
			showAddAlarm(0, 30);
			break;
		case R.id.B45minutes_activityMain:
			showAddAlarm(0, 45);
			break;
		case R.id.B1hour_activityMain:
			showAddAlarm(1, 0);
			break;
		case R.id.B1_5hour_activityMain:
			showAddAlarm(1, 30);
			break;

		}
	}

	public void showAddAlarm(int extraHours, int extraMinutes) {
		dialogView = getLayoutInflater().inflate(R.layout.add_alarm, null);
		timePicker = (TimePicker) dialogView.findViewById(R.id.TP_settingClock);
		setTimePicker(extraHours, extraMinutes);
		Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Setting");
		dialog.setView(dialogView);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				String timeStr = String.valueOf(timePicker.getCurrentHour())
						+ ":" + String.valueOf(timePicker.getCurrentMinute());
				if (timeStr != null) {
					alarmRecord.setText("I will leave at :" + timeStr);
					MyApplication.getInstance().getHashMap()
							.put("settingTime", timeStr);
				}

			}
		});
		dialog.setNegativeButton("Cancel", null);
		dialog.show();
	}

	public void setTimePicker(int extraHours, int extraMinutes) {

		Calendar calendar = Calendar.getInstance();
		int currentMinute = calendar.get(Calendar.MINUTE) + extraMinutes;
		int currentHour = calendar.get(Calendar.HOUR_OF_DAY) + extraHours;
		Log.i(String.format("%02d",calendar.get(Calendar.MINUTE)),
				String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY)));
		if (currentMinute < 60) {
			timePicker.setCurrentMinute(currentMinute);
		} else {
			timePicker.setCurrentMinute(currentMinute - 60);
			currentHour++;
		}
		if (currentHour < 24) {
			timePicker.setCurrentHour(currentHour);
		} else {
			timePicker.setCurrentHour(currentHour - 24);
		}
		timePicker.setIs24HourView(true);
	}

}
