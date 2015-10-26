package com.example.callback;

import com.example.basicinfo.Logs;
import com.example.layout.R;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class CallBackActivityTest extends Activity {

	private TextView mTextView;
	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.callback_interface_view);
		mTextView = (TextView) findViewById(R.id.call_back_txt);
		
		CallBackThread callBackThread = new CallBackThread();
		callBackThread.start();
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
			String str = (String) msg.obj;
			mTextView.setText(str);
			}
		};
		//写法一
		CallBackInterface callBack = new CallBackInterface() {
			
			@Override
			public void excute(String message) {
				Logs.d(message);
			}
		};
		callBackThread.setCallBackInterface(callBack);
		
		//写法二
		callBackThread.setCallBackInterface(new CallBackInterface() {
			
			@Override
			public void excute(String message) {
				Logs.i(message);
			}
		});
	}
	
	public class CallBackThread extends Thread{
		private CallBackInterface callBackInterface;
		
		public void setCallBackInterface(CallBackInterface callBackInterface){
			this.callBackInterface = callBackInterface;
		}
		
		@Override
		public void run() {
			for(int i=0;i<10;i++){
				Logs.d("正在执行"+i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//用handler将传递消息，在UI主线程中刷新
			Message msg = Message.obtain();
			msg.obj = "操作完成";
			mHandler.sendMessage(msg);
			
			//接口方法传递数据，实现回调
			if (callBackInterface != null)
				callBackInterface.excute("操作完成");
		}
	}
	
	
	//定义一个内部类接口
	public interface CallBackInterface{
		public void excute(String message);
	}
}
