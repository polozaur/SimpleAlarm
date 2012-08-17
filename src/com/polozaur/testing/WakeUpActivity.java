package com.polozaur.testing;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class WakeUpActivity extends Activity {

	TextView wakeUpMessageView;
	Button dismissBtn;
	static boolean dismissed;
	static String alarmName;
	static MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN 
		| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
		| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON );
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wake_up_layout);
		//start alarm sound
		mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
		mediaPlayer.setLooping(true);
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mediaPlayer.release();
			}
		});
		mediaPlayer.start();
		this.wakeUpMessageView = (TextView) findViewById(R.id.wakeUpMessageView);
		this.dismissBtn = (Button) findViewById(R.id.dismissAlarmBtn);
		alarmName = getIntent().getStringExtra("ALARM_NAME") + '!';
		this.wakeUpMessageView.setText(alarmName);
		//start media player sound
		this.dismissBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//stop sound  & destroy
				dismissed = true;
				mediaPlayer.stop();
				mediaPlayer = null;
			    WakeUpActivity.this.finish();	
			}
		});
	}
	//only clicking dismiss button would the activity go away ???
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(!dismissed) {
			Intent respawnIntent = new Intent(WakeUpActivity.this, WakeUpActivity.class);
			respawnIntent.putExtra("ALARM_NAME", alarmName);
		}
	}
}
