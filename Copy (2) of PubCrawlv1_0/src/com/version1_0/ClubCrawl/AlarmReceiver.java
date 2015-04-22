package com.version1_0.ClubCrawl;

/**
 * the class represents receiver, the system will continuously receive the alarm of the system,
 * then get the current time and compare it to the setting time, if the current time equal the 
 * setting time, it call the music service
 * @author Jiang Zhe Heng
 */
import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
	/**
 * 
 */
	@Override
	public void onReceive(Context context, Intent intent) {

		// SharedPreferences
		// sharedPreferences=context.getSharedPreferences("alarm_record",
		// Activity.MODE_PRIVATE);
		String hour = String.valueOf(Calendar.getInstance().get(
				Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(Calendar.getInstance().get(
				Calendar.MINUTE));
		String currentTime = hour + ":" + minute;
		Log.i((String) MyApplication.getInstance().getHashMap()
				.get("settingTime"), "Receive");
		String settingTime = (String) MyApplication.getInstance().getHashMap()
				.get("settingTime");
		Log.i("Receive", "Receive");
		if (currentTime.equals(settingTime)) {

			context.startService(new Intent(context, MusicService.class));
		}

	}

}
