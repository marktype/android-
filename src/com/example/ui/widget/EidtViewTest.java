package com.example.ui.widget;

import com.example.layout.R;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class EidtViewTest extends Activity implements OnClickListener {
	private Button mDelUserName, mDelPassword;
	private EditText mUserName, mPassword;
	private AutoCompleteTextView mAutoTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editview);
		mDelUserName = (Button) findViewById(R.id.del_btn_1);
		mDelPassword = (Button) findViewById(R.id.del_btn_2);
		mUserName = (EditText) findViewById(R.id.name_edit_text);
		mPassword = (EditText) findViewById(R.id.password_edit_text);

		mDelPassword.setOnClickListener(this);
		mDelUserName.setOnClickListener(this);
		
		String[] strName = {"abfkdk@qq.com","afdgk@163.com","cdsfjfdsjk@qq.com","boajfsk@goolge.com"}; 
		mAutoTxt = (AutoCompleteTextView) findViewById(R.id.auto_Txt);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, strName);
		mAutoTxt.setAdapter(adapter);
		mAutoTxt.setThreshold(1);//设置从第几个数开始联想
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.del_btn_1:
			mUserName.setText("");
			break;
		case R.id.del_btn_2:
			mPassword.setText("");
			break;
		default:
			break;
		}
	}
}
