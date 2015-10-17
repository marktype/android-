package com.example.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.layout.R;

public class AlertDialogBasic extends Activity implements OnClickListener {
	private Button mProgressBtn, mDatePickerbtn, mOwnDialog, mAlertDialog,
			mToastOwn;
	private ProgressDialog mProgressDialog;
	private DatePickerDialog mDatePickerDialog;
	private AlertDialog mAlertDialog2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_dialog);

		mProgressBtn = (Button) findViewById(R.id.dialog_progress_btn);
		mDatePickerbtn = (Button) findViewById(R.id.dialog_datepicker_btn);
		mOwnDialog = (Button) findViewById(R.id.dialog_own_btn);
		mAlertDialog = (Button) findViewById(R.id.dialog_own_system_btn);
		mToastOwn = (Button) findViewById(R.id.toast_own_btn);

		
		
		mToastOwn.setOnClickListener(this);
		mAlertDialog.setOnClickListener(this);
		mOwnDialog.setOnClickListener(this);
		mDatePickerbtn.setOnClickListener(this);
		mProgressBtn.setOnClickListener(this);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1:
			new AlertDialog.Builder(this).setIcon(R.drawable.a1e).setTitle("标题").
			setMessage("测试信息").setNeutralButton("退出", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(AlertDialogBasic.this, "退出成功", Toast.LENGTH_SHORT).show();
				}
			}).create().show();
			
			break;

		default:
			break;
		}
		
		
		
		return null;

	}
	public void onOwnWayDialog(View v){
		showDialog(1);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_progress_btn:
			setProgresssDialog();
			break;
		case R.id.dialog_datepicker_btn:
			setDatePickerDialog();
			break;
		case R.id.dialog_own_btn:
			OwnDialogActivity myDialog = new OwnDialogActivity(this);//自定义对话框
			myDialog.show();
			break;
		case R.id.dialog_own_system_btn:
			setAlertDialog();
			break;
		case R.id.toast_own_btn:
			setMyToast();
			break;
		default:
			break;
		}
	}

	/*
	 * 进度条对话框————自定义
	 */
	public void setProgresssDialog() {

		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setIndeterminate(false);// 设置为确定型
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置为横条形式
		mProgressDialog.setProgressDrawable(getResources().getDrawable(
				R.drawable.seekbar_progess));
		mProgressDialog.setTitle("加载中......");
		mProgressDialog.show();
		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i < mProgressDialog.getMax(); i++) {
					mProgressDialog.setProgress(i);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				mProgressDialog.dismiss();// 循环结束后关闭对话框
			}
		}).start();
	}

	/*
	 * 显示时间对话框
	 */
	public void setDatePickerDialog() {
		mDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {

			}
		}, 2015, 10, 14);
		mDatePickerDialog.show();
	}

	/*
	 * 自定义布局，在不继承AlertDialog的情况下
	 */
	public void setAlertDialog() {
		View myView = LayoutInflater.from(this).inflate(
				R.layout.dialog_own_back, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		Button mNoExit = (Button) myView.findViewById(R.id.dialog_no_btn);
		Button mYesExit = (Button) myView.findViewById(R.id.dialog_yes_btn);
		builder.setView(myView);
		mAlertDialog2 = builder.create();
		mYesExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Toast.makeText(AlertDialogBasic.this, "退出成功",
						Toast.LENGTH_SHORT).show();// 此处应用context，通过类构造传参
				mAlertDialog2.dismiss();
			}
		});
		mNoExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Toast.makeText(AlertDialogBasic.this, "不退出成功",
						Toast.LENGTH_SHORT).show();// 此处应用context，通过类构造传参
				mAlertDialog2.dismiss();
			}

		});

		mAlertDialog2.show();
	}

	/*
	 * 自定义toast布局
	 */
	public void setMyToast() {
		View toastView = LayoutInflater.from(this).inflate(R.layout.toast_own,
				null);
		Toast toast = new Toast(this);
		toast.setGravity(Gravity.CENTER, 0, 0);// 设置显示位置
		toast.setDuration(Toast.LENGTH_SHORT);// 设置显示时间
		toast.setView(toastView);
		toast.show();
	}

}
