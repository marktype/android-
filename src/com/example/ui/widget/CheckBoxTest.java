package com.example.ui.widget;

import com.example.layout.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class CheckBoxTest extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_checkbox);

		
	}

	public void onChickClick(View v) {
		CheckBox cBox = (CheckBox) v;
		switch (v.getId()) {
		case R.id.one:
			if(cBox.isChecked()){
				Toast.makeText(this, "第一个", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "第一个取消", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.two:
			if(cBox.isChecked()){
				Toast.makeText(this, "第二个", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "第二个取消", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.three3:
			if(cBox.isChecked()){
				Toast.makeText(this, "第三个", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "第三个取消", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}
	public void onOwnChickClick(View v) {
		CheckBox cBox = (CheckBox) v;
		switch (v.getId()) {
		case R.id.first:
			if(cBox.isChecked()){
				Toast.makeText(this, "第一个", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "第一个取消", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.second:
			if(cBox.isChecked()){
				Toast.makeText(this, "第二个", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "第二个取消", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.three:
			if(cBox.isChecked()){
				Toast.makeText(this, "第三个", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "第三个取消", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}
}
