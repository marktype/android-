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
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.basicinfo.HttpConnetUtil;
import com.example.basicinfo.HttpConnetUtil.HttpConnectCallBack;
import com.example.layout.R;

public class HttpActivity extends Activity implements OnClickListener {
	private Button mSendBtn, mclientBtn, mUtilBtn;
	private TextView mGetTxt;
	 private String url = "http://192.168.1.156/app/print";
//	private String url = "http://192.168.1.172:8080/app/text";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.http_getserver_layout);

		mSendBtn = (Button) findViewById(R.id.http_send_server_btn);
		mclientBtn = (Button) findViewById(R.id.http_send_client_btn);
		mUtilBtn = (Button) findViewById(R.id.http_util_btn);
		mGetTxt = (TextView) findViewById(R.id.http_getback_txt);

		mSendBtn.setOnClickListener(this);
		mclientBtn.setOnClickListener(this);
		mUtilBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.http_send_server_btn:
			// AsyncTaskByGET(); //get传参
			AsyncTaskByPOST(); // post传参
			break;
		case R.id.http_send_client_btn:
			AsyncTaskByHttpClientByPost(); // post传参
			// AsyncTaskByHttpClientByGet(); // get传参
			break;
		case R.id.http_util_btn:
			UserLogin();
			break;
		}

	}

	public void UserLogin() {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "小张");
//		map.put("password", "213");

		HttpConnetUtil connet = new HttpConnetUtil();
		connet.httpConnet(url, null, "POST", new HttpConnectCallBack() {
			public void backMessage(String message) {
				if (message != null) {
					mGetTxt.setText(message);
				}
			}
		});
	}

	public void AsyncTaskByGET() {
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

	public void AsyncTaskByPOST() {
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

	public void AsyncTaskByHttpClientByGet() {
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				String result = httpConnetByGet(params[0]);

				return result;
			}

			protected void onPostExecute(String result) {
				mGetTxt.setText(result);
			};

		}.execute(url);
	}

	public void AsyncTaskByHttpClientByPost() {
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				String result = httpConnetByPost(params[0]);

				return result;
			}

			protected void onPostExecute(String result) {
				mGetTxt.setText(result);
			};

		}.execute(url);
	}

	/*
	 * 通过get传参
	 */
	public String getConnetByGET(String httpUrl) {

		try {
			String encoder = URLEncoder.encode("张三", "UTF-8"); // 解决中文乱码问题
			httpUrl = httpUrl + "?name=" + encoder + "&password=123";
			URL url = new URL(httpUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(10000);// 设置连接超时时间
			con.setRequestMethod("GET"); // 设置请求方法 此处需大写
			con.setReadTimeout(10000); // 设置读取超时时间
			con.connect();// 连接网络

			InputStream is = con.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));

			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
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
	 */
	public String getConnetByPOST(String httpUrl) {

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(10000);// 设置连接超时时间
			con.setRequestMethod("POST"); // 设置请求方法 此处需大写
			con.setReadTimeout(10000); // 设置读取超时时间
			con.connect();// 连接网络

			// servlet中转换了格式，此处不同再转
			OutputStream os = con.getOutputStream();
			PrintWriter writer = new PrintWriter(os);
			writer.print("name=张三&password=123");
			writer.flush();

			InputStream is = con.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
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

	public String httpConnetByGet(String url) {

		HttpClient client = new DefaultHttpClient();
		// get传递参数
		url = url + "?name=张三&password=123";
		HttpGet get = new HttpGet(url);

		// HttpPost post = new HttpPost(url);
		try {
			HttpResponse res = client.execute(get);
			// HttpResponse res = client.execute(post);

			String str = EntityUtils.toString(res.getEntity()); // 获取从服务器端发送的值

			return str;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String httpConnetByPost(String url) {

		HttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);
		BasicNameValuePair namePair = new BasicNameValuePair("name", "李四");
		BasicNameValuePair passwordPair = new BasicNameValuePair("password",
				"123");
		ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(namePair);
		list.add(passwordPair);
		try {
			// 乱码问题解决方法一
			HttpEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8); // 加上utf-8解决乱码问题，服务端也应加上req.setCharacterEncoding("UTF-8");
			// 乱码问题解决方法二，服务端去掉req.setCharacterEncoding("UTF-8")时
			// post.setHeader("Content-Type",
			// "application/x-www-form-urlencoded; charset=utf-8");

			post.setEntity(entity);
			
			HttpResponse res = client.execute(post);

			String str = EntityUtils.toString(res.getEntity()); // 获取从服务器端发送的值

			return str;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
