package com.example.server;

import java.io.IOException;

import com.example.layout.R;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class MyFirstServer extends Service {
	private MediaPlayer mMediaPlayer = new MediaPlayer();
	private ServiceBindler mServiceBindler;
	
	public interface IBCount{
		void setCount(MediaPlayer MediaPlayer);
		public MediaPlayer getCount();
	}
	
	public class ServiceBindler extends Binder implements IBCount{

		@Override
		public void setCount(MediaPlayer MediaPlayer) {
//			Message msg = Message.obtain();
//			msg.arg1 =mMediaPlayer.getDuration();
//			msg.arg2 = mMediaPlayer.getCurrentPosition();
			
			mMediaPlayer = MediaPlayer;
		}

		@Override
		public MediaPlayer getCount() {
			return mMediaPlayer;
		}
		
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
//		mediaPlayOne();
		
		mServiceBindler = new ServiceBindler();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				mediaPlayOwn();
				
			}
		}).start();
		
		
		
		
		
	}

	public void mediaPlayOwn() {

		try {
			mMediaPlayer.setDataSource("file://"
					+ Environment.getExternalStorageDirectory()
					+ "/music/blmzm.mp3");

			mMediaPlayer.prepare();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 此处隐式调用会报错
	 * */
	public void mediaPlayOne() {
		Uri uri = Uri.parse("file://"
				+ Environment.getExternalStorageDirectory()
				+ "/music/blmzm.mp3");
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "audio/mp3");
		startActivity(intent);
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mServiceBindler;
	}

	@Override
	public void unbindService(ServiceConnection conn) {

		super.unbindService(conn);
	
	}
}
