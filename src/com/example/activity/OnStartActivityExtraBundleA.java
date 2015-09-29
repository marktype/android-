package com.example.activity;

import com.example.basicinfo.ActivityExitAll;
import com.example.basicinfo.ConstantInfo;
import com.example.basicinfo.Student;
import com.example.basicinfo.UserInfo;
import com.example.layout.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class OnStartActivityExtraBundleA extends Activity {
	private TextView mGetIdTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityExitAll main = ActivityExitAll.getInstance();
		main.addActivity(this);
		
		setContentView(R.layout.activity_intent_extra_bundle_a);
		mGetIdTxt = (TextView) findViewById(R.id.get_id_a_txt);
		// mGetIdTxt.setText("aaaa------>"+this.getTaskId()); //获取任务栈id

		mGetIdTxt.setText("welcome!");
	}

	public void OnStartActivityB(View v) {

		Intent intent = new Intent(this, OnStartActivityExtraBundleB.class);
		intent.putExtra("TYPE", ConstantInfo.TYPE_BASIC);
		intent.putExtra("KEY_BUTTON_B", "hello world!");
		intent.putExtra("boolean", true);
		startActivity(intent);
	}

	public void OnStartActivitySerializableB(View v) {
		Intent intent = new Intent(this, OnStartActivityExtraBundleB.class);
		intent.putExtra("TYPE", ConstantInfo.TYPE_RESERLIZED);
		Student student = new Student();
		student.setName("张三");
		student.setNumber("123");
		student.setSex("男");
		intent.putExtra("student", student);
		startActivity(intent);
	}

	public void OnStartActivityParcelableB(View v) {
		Intent intent = new Intent(this, OnStartActivityExtraBundleB.class);
		intent.putExtra("TYPE", ConstantInfo.TYPE_PARCLABLE);
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("李四");
		userInfo.setPassword("456");
		userInfo.setSex("男");
		intent.putExtra("userInfo", userInfo);
		startActivity(intent);
	}
	
	public void OnStartActivityBackValue(View v){
		Intent intent = new Intent(this,BackValueActivity.class);
		startActivityForResult(intent, ConstantInfo.NUM_BACK);
		
	}
	
	/*
	 * 多处返回值传递时resultCode作为标记符号，一处则默认返回
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
//		Log.v("tag", "+-----------------------+");
		switch (resultCode) {
		case ConstantInfo.NUM_BACK_FIVE:
			String getData = data.getStringExtra("BACKVALUE");
			mGetIdTxt.setText(getData==null?"":getData);
			break;
		case ConstantInfo.NUM_BACK_SIX:
			String getDataTwo = data.getStringExtra("BACKVALUETWO");
			mGetIdTxt.setText(getDataTwo==null?"":getDataTwo);
			break;
		default:
			break;
		}
		
	}
}
