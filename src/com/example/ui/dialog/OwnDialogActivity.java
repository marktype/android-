package com.example.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.layout.R;

public class OwnDialogActivity extends AlertDialog implements OnClickListener{

	private Button mYesExit,mNoExit;
	private Context context;
	protected OwnDialogActivity(Context context) {
		super(context);
		this.context = context;
	
	}

	/*
	 * 
	 * 自定义对话框，应写oncreat方法，其余方式同activity中
	 * 
	 * *
	 */
	  
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_own_back);
		
		mNoExit = (Button) findViewById(R.id.dialog_no_btn);
		mYesExit = (Button) findViewById(R.id.dialog_yes_btn);
		
		mNoExit.setOnClickListener(this);
		mYesExit.setOnClickListener(this);
	}

	

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_yes_btn:
			Toast.makeText(context, "退出成功", Toast.LENGTH_SHORT).show();//此处应用context，通过类构造传参
			dismiss();
			break;
		case R.id.dialog_no_btn:
			Toast.makeText(context, "不退出成功", Toast.LENGTH_SHORT).show();
			dismiss();
			break;
		default:
			break;
		}
		
	}
}
