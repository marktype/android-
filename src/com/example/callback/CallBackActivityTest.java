package com.example.callback;

import com.example.basicinfo.Logs;

import android.app.Activity;
import android.os.Bundle;

public class CallBackActivityTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		CallBackThread callBackThread = new CallBackThread();
		callBackThread.start();
	}
	
	public class CallBackThread extends Thread{
		private CallBackInterface callBackInterface;
		int i;
		public void setCallBackInterface(CallBackInterface callBackInterface){
			this.callBackInterface = callBackInterface;
		}
		
		@Override
		public void run() {
			for(i=0;i<10;i++){
				Logs.d("正在执行"+i);
			}
			
//			callBackInterface.excute(message);
		}
	}
	
	
	
	public interface CallBackInterface{
		public void excute(String message);
	}
}
