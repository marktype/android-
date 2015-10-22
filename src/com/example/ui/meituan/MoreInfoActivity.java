package com.example.ui.meituan;

import com.example.layout.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MoreInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.meituan_more_pager);
	}
}
