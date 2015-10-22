package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.layout.R;

public class HandlerMessage extends Activity {
	private TextView mTextView;
	private Button mButtonOne, mButtonTwo;
	private int count = 0;
	private MyThreadHandle threadHandle;
	private Handler mHandleThree;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.handler_get_message);
		mTextView = (TextView) findViewById(R.id.handler_txt);

		mButtonOne = (Button) findViewById(R.id.handler_one_btn);
		mButtonTwo = (Button) findViewById(R.id.handler_two_btn);

		threadHandle = new MyThreadHandle();
		threadHandle.start();

		mButtonOne.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Message mage = Message.obtain();
				mage.arg1 = count++;
				threadHandle.handlerOne.sendMessage(mage);

			}
		});

		mButtonTwo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message mage = Message.obtain();
				mage.arg1 = count++;
				threadHandle.handlerTwo.sendMessage(mage);

			}
		});

		mHandleThree = new Handler(Looper.getMainLooper(), new Callback() {// 主线程looper

					@Override
					public boolean handleMessage(Message msg) {
						int arg1 = msg.arg1;

						mTextView.setText("子线程   消息" + arg1);
						return true;
					}
				});
	}

	public class MyThreadHandle extends Thread {
		public Handler handlerOne, handlerTwo;

		@Override
		public void run() {
			Looper.prepare();   //实例化looper

			handlerOne = new Handler(Looper.getMainLooper(), new Callback() {// 主线程looper

						@Override
						public boolean handleMessage(Message msg) {
							int arg1 = msg.arg1;
							Log.e("tag", "消息" + arg1);
							mTextView.setText("主线程   消息" + arg1);
							return true;
						}
					});
			handlerTwo = new Handler(Looper.myLooper(), new Callback() { // 子线程looper

						@Override
						public boolean handleMessage(Message msg) {
							int arg1 = msg.arg1;
							// 将子线程中的数据返回到主线程中显示
							Message mas = new Message();
							mas.arg1 = arg1;
							mHandleThree.sendMessage(mas);

							return true;
						}
					});

			Looper.loop();  //从looper中循环取数据
		}
	}
}
