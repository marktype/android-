package com.example.layout;

import com.example.layout.R;
import com.example.ui.widget.ImageViewTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TestLinearlayout extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_linaerlayout_1);
	
	}

	public void onClickSwitchActivity(View v){
		startActivity(new Intent(this, ImageViewTest.class));
	}
}
