package com.example.ui.widget;

import com.example.layout.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/*
 * 按钮触发事件，设置、获取文本信息
 * */
public class EventButton extends Activity implements OnClickListener{

	private TextView mMassageTxt;
	private Button mImpBtn,mInnerBtn,mXmlBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_event_botton);
	
	mMassageTxt = (TextView) findViewById(R.id.test_txt);
	mImpBtn = (Button) findViewById(R.id.imp_button_btn);
	mInnerBtn = (Button) findViewById(R.id.inner_button_btn);
	mXmlBtn = (Button) findViewById(R.id.xml_button_btn);
	mImpBtn.setOnClickListener(this);
	mInnerBtn.setOnClickListener(new onButtonClick());
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mMassageTxt.setText("接口实现方式");
	}
	
	
	
	class onButtonClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mMassageTxt.setText("内部类实现方式");
		}
		
	}
	//自定义属性方法，xml中调用onclick
	public void onClickXml(View v){
		mMassageTxt.setText("xml实现方式");
	}
}
