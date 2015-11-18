package com.example.ui.webview;



import com.example.layout.R;
import com.example.layout.TestRelative;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class MyWebViewActivity extends Activity {
	private WebView mWebView;
	private String url = "http://192.168.1.132:8080/test/getmarry.html";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.webview_layout);
		
		mWebView = (WebView) findViewById(R.id.webview_one);
		//第一步，设置webview可操作
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		//第三步，添加接口
		mWebView.addJavascriptInterface(new MyWebView(), "musicServiceInterfaceName");
		
		mWebView.loadUrl(url);
	}
	
	/* 第二步
	 * 自定义一个activity与HTML交互类“musicServiceInterfaceName”和所使用方法
	 * */
	public class MyWebView{
		/*
		 * 界面交互，此方法与HTML中调用方法保持一致
		 * */
		public void playMusic(){
			Toast.makeText(MyWebViewActivity.this, "播放成功", Toast.LENGTH_SHORT).show();
		}
		
		/*
		 * 界面交互，此方法与HTML中调用方法保持一致
		 * */
		public void startGridViewHttp(){
			startActivity(new Intent(MyWebViewActivity.this, TestRelative.class));
		}
	}
}
