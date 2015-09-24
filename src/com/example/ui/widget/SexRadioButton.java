package com.example.ui.widget;

import com.example.layout.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class SexRadioButton extends Activity{
	private RadioGroup mSexRadio,mSexRadio2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_sex_radio_button);
	mSexRadio = (RadioGroup) findViewById(R.id.sex_radio);
	mSexRadio2 = (RadioGroup) findViewById(R.id.sex_2_radio);
	//设置默认选项
	//((RadioButton) mSexRadio.getChildAt(0)).toggle();
	
	mSexRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.man_radio:
				//方式一
				RadioButton radioGroup = (RadioButton) group.getChildAt(0);
				//方法一    在系统中输出
				System.out.println(radioGroup.getText());
				//方法二    在界面上打印输出
				Toast.makeText(SexRadioButton.this, radioGroup.getText(), Toast.LENGTH_SHORT).show();
				
				break;
			case R.id.woman_radio:
				//方式二
				RadioButton radioButton = (RadioButton) findViewById(checkedId);
				System.out.println(radioButton.getText());
				Toast.makeText(SexRadioButton.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	});
	
	
	
	}
}
