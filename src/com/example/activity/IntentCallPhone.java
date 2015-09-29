package com.example.activity;

import com.example.layout.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class IntentCallPhone extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.intent_uri);
	}
	
	public void OnClickCallPhone(View v){
		//隐式跳转
		Uri uri = Uri.parse("tel:13056789023");
		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		startActivity(intent);
	}
	
	public void OnClickSeeDetil(View v){
//		Intent intent = new Intent(this, IntentDetil.class);//常用手法
		
		Intent intent = new Intent();
		
//		ComponentName component = new ComponentName(this, IntentDetil.class);
//		intent.setComponent(component);
		
		//自定义类型一
		intent.setAction(Intent.ACTION_VIEW);
		intent.setClass(this, IntentDetil.class);
		
		//自定义类型二
//		intent.setAction("com.example.TestAction");

		
		startActivity(intent);
	}
}
