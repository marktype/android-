package com.example.store.sms;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.layout.R;
import com.example.store.sms.SmsObserver.information;

public class SmsActivity extends Activity {

	private TextView mSmsMessageTxt;
	private Button mSmsListenBtn;
	private information mInfo;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {     //handler处理信息（两个activity之间传递）
			switch(msg.what){
			case 111:
				mInfo = (information) msg.obj;
				mSmsMessageTxt.setText("号码："+mInfo.getAddress()+"\t"+"信息："+mInfo.getBody());
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sms_message_listener);
		mSmsListenBtn = (Button) findViewById(R.id.sms_get_message_btn);
		mSmsMessageTxt = (TextView) findViewById(R.id.sms_message_txt);
		
		
		mSmsListenBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSmsMessageTxt.setText("正在监听...");
				Uri uri = Uri.parse("content://sms");  //解析短信
				//短信观察者注册监听
				SmsObserver observer = new SmsObserver(SmsActivity.this,mHandler);
				getContentResolver().registerContentObserver(uri, true, observer);
			}
		});
		
		
	}
}
