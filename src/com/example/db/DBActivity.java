package com.example.db;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.layout.R;

public class DBActivity extends Activity {

	private Button mAddBtn,mQueryBtn;
	private TextView mNameTxt,mPasswordTxt;
	private MyDataBase mMyBase;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_activity);
		mMyBase = MyDataBase.getInstance(this);
		
		mNameTxt = (TextView) findViewById(R.id.db_name_txt);
		mPasswordTxt = (TextView) findViewById(R.id.db_password_txt);
		
		mAddBtn = (Button) findViewById(R.id.db_add_btn);
		mQueryBtn = (Button) findViewById(R.id.db_query_btn);
		
		mAddBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = (String) mNameTxt.getText().toString();
				String password = (String) mPasswordTxt.getText().toString();
				mMyBase.add(name, password);
				Toast.makeText(DBActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		mQueryBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//使用数据库循环遍历查找所有信息
//				startActivity(new Intent(DBActivity.this, QueryInfoActivity.class));
				
				//使用cousorAdapter查找所有信息，注意此处必须要有个命名为"_id"的列
				startActivity(new Intent(DBActivity.this, CousorActivity.class));
			}
		});
	}
	
	
}
