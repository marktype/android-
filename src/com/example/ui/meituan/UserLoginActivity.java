package com.example.ui.meituan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.layout.R;
import com.example.ui.adapter.ViewPagerAdapter;

public class UserLoginActivity extends Activity {
	private EditText mUserName, mPassword;
	private Button mLoginBtn;
	private long mExitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.meituan_login_layout);

		mLoginBtn = (Button) findViewById(R.id.meituan_enter_btn);
		mUserName = (EditText) findViewById(R.id.meituan_user_name_edt_txt);
		mPassword = (EditText) findViewById(R.id.meituan_password_edt_txt);

		mLoginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mUserName.getText().toString().equals("a")
						&& mPassword.getText().toString().equals("1")) {
					startActivity(new Intent(UserLoginActivity.this,
							ViewPagerAdapter.class));
					finish();
				} else {
					new AlertDialog.Builder(UserLoginActivity.this)
							.setTitle("提示信息")
							.setMessage("输入错误，请填写正确的用户和密码！")
							.setNeutralButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {

										}
									}).create().show();
				}

			}
		});
	}

	/*
	 * 点击返回弹出对话框
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			dialog();
		}

		return true;

	}

	public void dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("确认要退出？");
		builder.setNegativeButton("否", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		builder.setPositiveButton("是", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		builder.create().show();
	}
}
