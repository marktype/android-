package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


/*
 * 静态实现，在manifest中注册
 * */
public class MyBroadCast extends BroadcastReceiver {
	public static final String MY_SMS_BROADCAST = "com.example.broadcast.sms";//自定义广播标记实现，通过此来发送广播
	public static final String SYSTEM_SMS_BROADCAST = "android.provider.Telephony.SMS_RECEIVED"; //系统定义字符标记
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		String action = arg1.getAction();
		if (action.equals(MY_SMS_BROADCAST)) {  //实现过滤
			Toast.makeText(arg0, "处理自定义短信广播", Toast.LENGTH_SHORT).show();
		}else if (action.equals(SYSTEM_SMS_BROADCAST)) {
			Toast.makeText(arg0, "处理系统接收短信", Toast.LENGTH_SHORT).show();
		}
			
		
		
	}

}
