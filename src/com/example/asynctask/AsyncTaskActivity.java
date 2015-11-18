package com.example.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.layout.R;

public class AsyncTaskActivity extends Activity {
	private Button mProgressBtn;
	private ProgressBar mPregressBar;
	private TextView mProgressTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	setContentView(R.layout.asynctask_layout);
	
	mPregressBar = (ProgressBar) findViewById(R.id.asynctask_progess_bar);
	mProgressBtn = (Button) findViewById(R.id.asynctask_progess_btn);
	mProgressTxt = (TextView) findViewById(R.id.asynctask_Txt);
	
	mProgressBtn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			MyAsynctask myAsynctask = new MyAsynctask();
			myAsynctask.execute();   //运行
			
		}
	});
	}
	
	public class MyAsynctask extends AsyncTask<Void, Integer, String>{

		@Override
		protected String doInBackground(Void... params) {
			
			
					for(int i = 1; i<=100;i++){
						
						publishProgress(i);   //将参数传递给onProgressUpdate中
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					String progressSuccess = "加载完成";
			return progressSuccess;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			
			int progress = values[0];
			mPregressBar.setProgress(progress);
			mProgressTxt.setText(""+progress);
			
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			mProgressTxt.setText(result);
		}
		
	}
}
