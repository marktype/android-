package com.example.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.layout.R;
import com.example.layout.TestLinearlayout;

public class BroadCastActivity extends Activity {
	private Button mSMSBtn,mDynamicBtn;
	DynamicBroadCast receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reciver_broadcast_layout);
		
		mSMSBtn = (Button) findViewById(R.id.reciver_my_sms_btn);
		mDynamicBtn = (Button) findViewById(R.id.reciver_dynamic_btn);
		mSMSBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyBroadCast.MY_SMS_BROADCAST);   //注册自定义字符串标记
				sendBroadcast(intent);
			}
		});
		
		mDynamicBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("com.example.broadcast");   //发送到标记位置下
				sendBroadcast(intent);
			}
		});
	}
	
	/*
	 * 与activity生命周期绑定  也有建议从onResume()开始，两者都行
	 * */
	@Override
	protected void onStart() {
		super.onStart();
		reciverBroadCast();   //注册
	}
	/*
	 * 与activity生命周期绑定  也有建议从onpause()结束，两者都行
	 * */
	@Override
	protected void onStop() {
		super.onStop();
		if (receiver !=null) {
			unregisterReceiver(receiver);  //取消注册
		}
	}
	
	/*
	 * 动态注册广播
	 * */
	public void reciverBroadCast(){
		receiver = new DynamicBroadCast();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.example.broadcast");  //过滤标志
		registerReceiver(receiver, filter);   //动态注册
	}
	
	public class DynamicBroadCast extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("com.example.broadcast")) {   //当有多个标志时起作用，此处可省略
				
				startActivity(new Intent(context, TestLinearlayout.class));
			}
		}
		
	}
	
}
