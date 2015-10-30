package com.example.store.sms;

import com.example.basicinfo.Logs;

import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

public class SmsCase extends AndroidTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		
	}
	/*
	 * 读取手机内短信信息
	 * "address"发送短信到手机的号码
	 * "body"短信内容
	 * */
	public void testGetMassage(){
		Uri uri = Uri.parse("content://sms");
		
		Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
	
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				String address = cursor.getString(cursor.getColumnIndex("address"));
				String body = cursor.getString(cursor.getColumnIndex("body"));
				
				Logs.e(address + "      " + body);
			}
		}
	}
}
