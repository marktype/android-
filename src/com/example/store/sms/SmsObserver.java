package com.example.store.sms;

import com.example.basicinfo.Logs;
import com.example.db.DataBaseUtil;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class SmsObserver extends ContentObserver {

	private Context context;
	private information mInfo = new information();
	private Handler mHandler;
	public SmsObserver(Context context, Handler handler) {
		super(handler);
		this.context = context;
		mHandler = handler;
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);

		Logs.d("收到——————————————————");
		String[] projection = new String[] { DataBaseUtil.SmsColumn.ADDRESS,
				DataBaseUtil.SmsColumn.BODY };

		Cursor cursor = context.getContentResolver().query(DataBaseUtil.SmsColumn.SMS_URI,
				projection, "address = ?", new String[]{"111"}, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();    //监听刚收到的一条信息,不用循环发送
				String address = cursor.getString(cursor.getColumnIndex("address"));
				String body = cursor.getString(cursor.getColumnIndex("body"));
				mInfo.setAddress(address);
				mInfo.setBody(body);
				
				Message msg = Message.obtain();
				msg.what = 111;//信息标记
				msg.obj = mInfo;
				mHandler.sendMessage(msg);
			
		}
		
	}
	
	public class information{
		String address,body;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}
	}
}
