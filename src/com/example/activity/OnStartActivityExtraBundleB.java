package com.example.activity;

import com.example.basicinfo.ActivityExitAll;
import com.example.basicinfo.ConstantInfo;
import com.example.basicinfo.Student;
import com.example.basicinfo.UserInfo;
import com.example.layout.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OnStartActivityExtraBundleB extends Activity {
	private TextView mGetIdTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ActivityExitAll main = ActivityExitAll.getInstance();
		main.addActivity(this);
		
		setContentView(R.layout.activity_intent_extra_bundle_b);
		mGetIdTxt = (TextView) findViewById(R.id.get_id_b_txt);
		// mGetIdTxt.setText("bbbb--------->"+this.getTaskId());

		Intent intent = getIntent();
		int massage = intent.getIntExtra("TYPE", 1);
		switch (massage) {
		case ConstantInfo.TYPE_BASIC:
			setBasic(intent);
			break;
		case ConstantInfo.TYPE_RESERLIZED:
			setSerializable(intent);
			break;
		case ConstantInfo.TYPE_PARCLABLE:
			setParclable(intent);
			break;
		default:
			break;
		}

	}

	public void OnStartActivityA(View v) {
		startActivity(new Intent(this, OnStartActivityExtraBundleA.class));
	}

	public void OnStartActivityExit(View v){
		ActivityExitAll exit = ActivityExitAll.getInstance();
		exit.finishAll();
	}
	
	public void setBasic(Intent intent) {
		String mass = intent.getStringExtra("KEY_BUTTON_B");
		boolean flag = intent.getBooleanExtra("boolean", false);
		mGetIdTxt.setText(mass + "  " + flag);
	}

	public void setSerializable(Intent intent) {
		Student student = (Student) intent.getSerializableExtra("student");
		mGetIdTxt.setText("姓名：" + student.getName() + "  学号："
				+ student.getNumber() + "  性别：" + student.getSex());

	}

	public void setParclable(Intent intent) {
		UserInfo userInfo = intent.getParcelableExtra("userInfo");
		mGetIdTxt.setText("用户名：" + userInfo.getUserName() + "  密码："
				+ userInfo.getPassword() + "  性别：" + userInfo.getSex());
	}
}
