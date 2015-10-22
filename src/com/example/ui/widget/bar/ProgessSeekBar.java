package com.example.ui.widget.bar;

import java.util.logging.MemoryHandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.layout.R;
import com.example.ui.meituan.MainMassageActivity;

public class ProgessSeekBar extends Activity {

	private ProgressBar mProgessBar;
	private SeekBar mSeekBar, mSeekOwnBar;
	private Button mProgessBtn, mSeekBtn, mSeekOwnBtn;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 2:
				String mess = (String) msg.obj;
				Toast.makeText(ProgessSeekBar.this, mess, Toast.LENGTH_SHORT)
						.show();
				break;

			default:
				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.progressbar_widget);
		mProgessBar = (ProgressBar) findViewById(R.id.progess_bar);
		mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
		mSeekOwnBar = (SeekBar) findViewById(R.id.seek_own_bar);

		mProgessBtn = (Button) findViewById(R.id.progess_button);
		mSeekBtn = (Button) findViewById(R.id.seek_button);
		mSeekOwnBtn = (Button) findViewById(R.id.seek_own_button);

		mProgessBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 1; i <= mProgessBar.getMax(); i++) {
							mProgessBar.setProgress(i);
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						Message msg = Message.obtain();
						msg.what = 2;
						msg.obj = "加载完成";
						mHandler.sendMessage(msg);
					}
				}).start();
			}
		});

		mSeekBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 1; i <= mSeekBar.getMax(); i++) {
							mSeekBar.setProgress(i);
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						mHandler.post(new Runnable() {

							@Override
							public void run() {

								Toast.makeText(ProgessSeekBar.this, "成功加载",
										Toast.LENGTH_SHORT).show();
							}
						});
					}
				}).start();
			}
		});

		mSeekOwnBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 1; i <= mSeekOwnBar.getMax(); i++) {
							mSeekOwnBar.setProgress(i);
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				}).start();
			}
		});
	}
}
