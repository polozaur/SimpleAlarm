package com.polozaur.testing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NewAlarmReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("RECIEVER", "WAKE UP SPAWNING");
		Intent wakeUpIntent = new Intent(context, WakeUpActivity.class);
		wakeUpIntent.putExtra("ALARM_NAME",intent.getStringExtra("ALARM_NAME"));
		wakeUpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//call the alarm activity
		context.startActivity(wakeUpIntent);	
	}

}
