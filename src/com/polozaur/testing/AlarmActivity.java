package com.polozaur.testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlarmActivity extends Activity {
	
	Button newTaskBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        newTaskBtn = (Button) findViewById(R.id.newTaskBtn);
        newTaskBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AlarmActivity.this, NewAlarmActivity.class));
			}
		});
    }
}
