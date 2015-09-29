package com.example.activity;

import com.example.basicinfo.ConstantInfo;
import com.example.layout.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class BackValueActivity extends Activity {
	private TextView mBackValue,mBackValueTwo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getback_value);
		mBackValue = (TextView) findViewById(R.id.back_value);
		mBackValueTwo = (TextView) findViewById(R.id.back_value_two);
		mBackValue.setText("返回值传递");
		mBackValueTwo.setText("另一个返回值传递");
	}
	/*
	 * 回传值传递一
	 * 
	 * */
	public void onClickBackValue(View v){
		Intent intent = new Intent(this, OnStartActivityExtraBundleA.class);
		intent.putExtra("BACKVALUE", mBackValue.getText());
//		Log.v("tag", (String) mBackValue.getText());
		
		setResult(ConstantInfo.NUM_BACK_FIVE, intent);
		finish();
	}
	/*
	 * 回传值传递二
	 *
	 * 
	 * */
	public void onClickBackValueTwo(View v){
		Intent intent = new Intent(this, OnStartActivityExtraBundleA.class);
		intent.putExtra("BACKVALUETWO", mBackValueTwo.getText());
		setResult(ConstantInfo.NUM_BACK_SIX, intent);
		finish();
	}
}
