package com.example.mediaplay;

import java.io.IOException;

import com.example.layout.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Mp3PlayActivity extends Activity implements OnClickListener {

	private Button mOneBtn, mTwoBtn, mThreeBtn,mFourBtn, mFiveBtn;
	private MediaPlayer mediaPlayer = new MediaPlayer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mp3_play_item_button);

		mOneBtn = (Button) findViewById(R.id.mp3_one_btn);
		mTwoBtn = (Button) findViewById(R.id.mp3_two_btn);
		mThreeBtn = (Button) findViewById(R.id.mp3_three_btn);
		mFourBtn = (Button) findViewById(R.id.mp3_four_btn);
		mFiveBtn = (Button) findViewById(R.id.mp3_five_btn);
		
		mOneBtn.setOnClickListener(this);
		mTwoBtn.setOnClickListener(this);
		mThreeBtn.setOnClickListener(this);
		mFourBtn.setOnClickListener(this);
		mFiveBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mp3_one_btn:
			mediaPlayOne();
			break;
		case R.id.mp3_two_btn:
			mediaPlayTwo();
			break;
		case R.id.mp3_three_btn:
			mediaPlayOwn();
			break;
		case R.id.mp3_four_btn:
			start();
			break;
		case R.id.mp3_five_btn:
			pause();
			break;
		default:
			break;
		}

	}

	public void mediaPlayOne() {
		Uri uri = Uri.parse("file://"
				+ Environment.getExternalStorageDirectory()
				+ "/music/blmzm.mp3");
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "audio/mp3");
		startActivity(intent);
	}

	public void mediaPlayOwn() {
		
		try {
			mediaPlayer.setDataSource("file://"
					+ Environment.getExternalStorageDirectory()
					+ "/music/blmzm.mp3");
			
			mediaPlayer.prepare();
			Toast.makeText(this, "准备好了，可以开始", Toast.LENGTH_SHORT).show();
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
	
	public void mediaPlayTwo(){
		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.ykzzldygx);
			
			mediaPlayer.start();
	}
	
	public void start(){
		mediaPlayer.start();
	}
	public void pause(){
		mediaPlayer.pause();
	}
}
