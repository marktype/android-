package com.example.server;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.layout.R;
import com.example.server.MyFirstServer.IBCount;

public class ServerActivity extends Activity {
	private Button mServerBtn, mStartBtn, mStopBtn;
	private SeekBar mSeekBar;
	private MediaPlayer mMediaPlayer ;
	private IBCount mCount;
	
	private ServiceConnection mServerCount = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mCount = null;

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mCount = (IBCount) service;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.server_button);
		mServerBtn = (Button) findViewById(R.id.server_btn);
		mStartBtn = (Button) findViewById(R.id.server_start_btn);
		mStopBtn = (Button) findViewById(R.id.server_pause_btn);
		mSeekBar = (SeekBar) findViewById(R.id.server_seekbar);

		Intent intent = new Intent(this, MyFirstServer.class);
		bindService(intent, mServerCount, BIND_AUTO_CREATE);

		
		// mServerBtn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// startService(new Intent(ServerActivity.this,
		// MyFirstServer.class));//跳转到服务
		// }
		// });
		mStartBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mMediaPlayer = mCount.getCount();
				
				mMediaPlayer.start();
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						while (true) {
							if (mMediaPlayer.isPlaying()) {

								mSeekBar.setMax(mMediaPlayer.getDuration());
								mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
							}
						}
						
					}
				}).start();
				

			}
		});

		mStopBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMediaPlayer.pause();
			}
		});
	}

	
}
