package com.example.notification;

import java.io.IOException;

import com.example.basicinfo.Logs;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayMusicService extends Service {

	private MediaPlayer mMediaPlay;
	@Override
	public void onCreate() {
		super.onCreate();
		if (mMediaPlay == null) {
			mMediaPlay = new MediaPlayer();
		}
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String path = intent.getStringExtra("PATH");
		int flag = intent.getIntExtra("FLAG", 0);
		
		if (flag == 2) {
			if (mMediaPlay.isPlaying()) 
				mMediaPlay.pause();
			
		}else{
			mMediaPlay.reset();
		try {
			mMediaPlay.setDataSource(path);
			mMediaPlay.prepare();
			mMediaPlay.start();
			
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return super.onStartCommand(intent, flags, startId);

		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
