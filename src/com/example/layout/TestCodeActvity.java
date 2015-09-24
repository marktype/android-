package com.example.layout;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
/*
 * 代码写界面
 * 
 * 
 * */
public class TestCodeActvity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	LinearLayout lay = new LinearLayout(this);
	lay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT
			, LayoutParams.MATCH_PARENT));
	Resources res = getResources();
	lay.setOrientation(lay.HORIZONTAL);//设置排列方向
	lay.setBackgroundColor(res.getColor(android.R.color.holo_green_dark));
	
	TextView tv = new TextView(this);
	tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
			LayoutParams.WRAP_CONTENT));
	tv.setBackgroundColor(res.getColor(android.R.color.holo_red_dark));
	tv.setText(res.getString(R.string.second));//修改名字
	tv.setTextSize(res.getDimension(R.dimen.dimen_size_20));//设置字体大小
	
	lay.addView(tv);
	setContentView(lay);
	}
}
