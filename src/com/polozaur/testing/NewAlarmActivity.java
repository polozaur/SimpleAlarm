package com.polozaur.testing;

import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class NewAlarmActivity extends Activity {

	EditText taskNameView;
	DatePicker datePicker;
	TimePicker timePicker;
	Button setTaskBtn;
	PendingIntent pendingIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_alarm_layout);

		this.taskNameView = (EditText) findViewById(R.id.taskNameView);
		this.datePicker = (DatePicker) findViewById(R.id.datePicker);
		this.timePicker = (TimePicker) findViewById(R.id.timePicker);
		this.setTaskBtn = (Button) findViewById(R.id.setTaskBtn);
		
		timePicker.setCurrentMinute(timePicker.getCurrentMinute() + 1);

		this.setTaskBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if (taskNameView.getText().toString().trim().length() < 1) {
					// create alert show no task name given
					AlertDialog alert = new Builder(NewAlarmActivity.this)
							.setTitle("Ups!").setMessage("Task name is empty")
							.setNeutralButton("OK", null).create();
					alert.show();
				} else {
					// get name of the task new alarm , or something else ...
					Intent intent = new Intent(NewAlarmActivity.this,
							NewAlarmReciever.class);
					intent.putExtra("ALARM_NAME", taskNameView.getText()
							.toString().trim());
					PendingIntent toReciever = PendingIntent.getBroadcast (
							NewAlarmActivity.this, new Random().nextInt(65000),
							intent, PendingIntent.FLAG_ONE_SHOT);

					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(System.currentTimeMillis());
					calendar.clear();
					calendar.set (
							datePicker.getYear(), 
							datePicker.getMonth(),
							datePicker.getDayOfMonth(),
							timePicker.getCurrentHour(),
							timePicker.getCurrentMinute()
					);
					AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
					alarmManager.set(AlarmManager.RTC_WAKEUP,
							calendar.getTimeInMillis(), toReciever);
					
					Toast.makeText(getApplicationContext(), "Alarm set", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}
}
