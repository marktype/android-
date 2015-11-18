package com.example.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Style;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.basicinfo.Logs;
import com.example.layout.R;
import com.example.ui.widget.Marquee_Test;

@SuppressLint("NewApi")
public class NotifyActivity extends Activity {
	private Button mSendBtn, mNavBtn, mBigViewBtn,mOwnNotifyBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.notify_send_layout);
		mSendBtn = (Button) findViewById(R.id.notify_send_btn);
		mNavBtn = (Button) findViewById(R.id.notify_navigation_btn);
		mBigViewBtn = (Button) findViewById(R.id.notify_bigview_btn);
		mOwnNotifyBtn = (Button) findViewById(R.id.notify_own_notify_btn);
		
		/*
		 * 发送通知
		 * */
		mSendBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 构造通知界面
				NotificationCompat.Builder builder = new NotificationCompat.Builder(
						NotifyActivity.this);
				builder.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle(getString(R.string.hello_world))
						.setContentText(getString(R.string.hint_text))
						.setAutoCancel(true) // 点击跳转后自动销毁
						.setTicker(getString(R.string.hello_world)); // 设置滚动文本

				// 通知行为
				Intent intent = new Intent(NotifyActivity.this,
						Marquee_Test.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(
						NotifyActivity.this, 0, intent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				builder.setContentIntent(pendingIntent);

				// 发送通知
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				manager.notify(1, builder.build()); // id不变则只显示一个，若id变化则可以重复显示多个
			}
		});

		/*
		 * 通知导航功能，返回应用主页
		 * */
		mNavBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NotificationCompat.Builder builder = new NotificationCompat.Builder(
						NotifyActivity.this);
				builder.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle(getString(R.string.hello_world))
						.setContentText(getString(R.string.hint_text))
						.setAutoCancel(true) // 点击跳转后自动销毁
						.setTicker(getString(R.string.hello_world)); // 设置滚动文本

				Intent intent = new Intent(NotifyActivity.this,
						Marquee_Test.class);
				TaskStackBuilder taskStackBuilder = TaskStackBuilder
						.create(NotifyActivity.this);
				
				taskStackBuilder.addParentStack(Marquee_Test.class);
				taskStackBuilder.addNextIntent(intent);
				PendingIntent pendingIntent = taskStackBuilder
						.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
				builder.setContentIntent(pendingIntent);

				// 发送通知
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				manager.notify(2, builder.build()); // id不变则只显示一个，若id变化则可以重复显示多个
			}
		});

		/*
		 * bigview 添加系统按钮监听
		 * */
		mBigViewBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NotificationCompat.Builder builder = new NotificationCompat.Builder(
						NotifyActivity.this);
				builder.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle(getString(R.string.hello_world))
						.setContentText(getString(R.string.hint_text))
						.setAutoCancel(true) // 点击跳转后自动销毁
						.setTicker(getString(R.string.hello_world)); // 设置滚动文本
				Intent playIntent = new Intent(NotifyActivity.this,
						PlayMusicService.class);
				playIntent.setAction("1");
				playIntent.putExtra(
						"PATH","file://"+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
								+ "/wy.mp3");
				playIntent.putExtra("FLAG", 1);
				
				PendingIntent playPending = PendingIntent.getService(
						NotifyActivity.this, 0, playIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);

				

				Intent pauseIntent = new Intent(NotifyActivity.this,
						PlayMusicService.class);
				pauseIntent.putExtra("PATH","file://"+ Environment
										.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+ "/wy.mp3");
				pauseIntent.setAction("2");   //此时必须设置action，不然不能播放，单个控件时可以不用设置
				pauseIntent.putExtra("FLAG", 2);
				PendingIntent pausePending = PendingIntent.getService(
						NotifyActivity.this, 0, pauseIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				builder.addAction(R.drawable.bg_actionbar, "播放", playPending);
				builder.addAction(R.drawable.bg_actionbar, "暂停", pausePending);

				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				manager.notify(3, builder.build()); // id不变则只显示一个，若id变化则可以重复显示多个
			}
		});
		
		/*
		 * 自定义通知界面
		 * */
		mOwnNotifyBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NotificationCompat.Builder builder = new NotificationCompat.Builder(
						NotifyActivity.this);
				builder.setSmallIcon(R.drawable.ic_launcher);  //此条件必须设置，不然不能显示
				builder.setTicker("开始播放音乐");
				RemoteViews view = new RemoteViews(getPackageName(), R.layout.notify_play_music_layout);
				builder.setContent(view);
				
				Intent playIntent = new Intent(NotifyActivity.this,
						PlayMusicService.class);
				playIntent.setAction("1");
				playIntent.putExtra(
						"PATH","file://"+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
								+ "/wy.mp3");
				playIntent.putExtra("FLAG", 1);
				
				PendingIntent playPending = PendingIntent.getService(
						NotifyActivity.this, 0, playIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				view.setOnClickPendingIntent(R.id.music_play_img, playPending);
				
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				manager.notify(4, builder.build());
			}
		});
	}
}
