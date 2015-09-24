package com.example.ui.widget;

import com.example.layout.R;

import android.R.menu;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Logcat_Switch extends Activity{
	private Switch mOnOff;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logcat_switch);
		mOnOff = (Switch) findViewById(R.id.switch_onoff);
		mOnOff.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Switch switch1 = (Switch) buttonView;
				boolean onoff = buttonView.isChecked();
				if (onoff) {
					Log.d("tag", "开");
					Toast.makeText(Logcat_Switch.this,switch1.getTextOn() , Toast.LENGTH_SHORT).show();
				}else {
					Log.d("tag", "关");
					Toast.makeText(Logcat_Switch.this, switch1.getTextOff(), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	public void onClickOnOff(View v){
		ToggleButton tButton = (ToggleButton) v;
		
		if (tButton.isChecked()) {
			Log.d("tag", "开");
			Toast.makeText(this, tButton.getTextOn(), Toast.LENGTH_SHORT).show();
		}else {
			Log.d("tag", "关");
			Toast.makeText(this, tButton.getTextOff(), Toast.LENGTH_SHORT).show();
		}
		
	}
}

