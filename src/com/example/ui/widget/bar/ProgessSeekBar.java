package com.example.ui.widget.bar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.example.layout.R;

public class ProgessSeekBar extends Activity {

	private ProgressBar mProgessBar;
	private SeekBar mSeekBar,mSeekOwnBar;
	private Button mProgessBtn,mSeekBtn,mSeekOwnBtn;
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
						for(int i = 1;i<=mProgessBar.getMax();i++){
							mProgessBar.setProgress(i);
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
		
		mSeekBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for(int i = 1;i<=mSeekBar.getMax();i++){
							mSeekBar.setProgress(i);
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
		
		mSeekOwnBtn.setOnClickListener(new OnClickListener() {
			
			
				@Override
				public void onClick(View v) {
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							for(int i = 1;i<=mSeekOwnBar.getMax();i++){
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
