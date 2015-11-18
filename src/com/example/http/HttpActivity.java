package com.example.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


import com.example.layout.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HttpActivity extends Activity implements OnClickListener{
	private Button mSendBtn;
	private TextView mGetTxt;
//	private String url = "http://192.168.1.156/app/print";
	private String url = "http://192.168.1.172:8080/app/text";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.http_getserver_layout);
		
		mSendBtn = (Button) findViewById(R.id.http_send_server_btn);
		mGetTxt  = (TextView) findViewById(R.id.http_getback_txt);
		
		mSendBtn.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.http_send_server_btn:
//			AsyncTaskByGET();    //get传参
			AsyncTaskByPOST();		//post传参
			
			break;
			
		}
		
	}
	
	public void AsyncTaskByGET(){
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				String result = getConnetByGET(params[0]);
				
				return result;
			}
			protected void onPostExecute(String result) {
				mGetTxt.setText(result);
			};
			
		}.execute(url);
	}
	
	public void AsyncTaskByPOST(){
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				String result = getConnetByPOST(params[0]);
				
				return result;
			}
			protected void onPostExecute(String result) {
				mGetTxt.setText(result);
			};
			
		}.execute(url);
	}
	
	/*
	 * 通过get传参
	 * */
	public String getConnetByGET(String httpUrl){
		
		try {
			String encoder = URLEncoder.encode("张三", "UTF-8");  //解决中文乱码问题
			httpUrl  = httpUrl +"?name="+encoder+"&password=123";
			URL url = new URL(httpUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(10000);//设置连接超时时间
			con.setRequestMethod("GET"); //设置请求方法  此处需大写
			con.setReadTimeout(10000); //设置读取超时时间
			con.connect();//连接网络
			
			InputStream is = con.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			StringBuilder builder = new StringBuilder();
			String line=null;
			while ((line = reader.readLine())!= null) {
				builder.append(line);
			}
			
			
			return builder.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 通过post传参
	 * */
	public String getConnetByPOST(String httpUrl){
		
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(10000);//设置连接超时时间
			con.setRequestMethod("POST"); //设置请求方法  此处需大写
			con.setReadTimeout(10000); //设置读取超时时间
			con.connect();//连接网络
			
			OutputStream os = con.getOutputStream();
			PrintWriter writer = new PrintWriter(os);
			writer.print("name=张三&password=123");
			writer.flush();
			
			InputStream is = con.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder builder = new StringBuilder();
			String line=null;
			while ((line = reader.readLine())!= null) {
				builder.append(line);
			}
			
			
			return builder.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
