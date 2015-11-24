package com.example.basicinfo;

import java.io.IOException;
import java.net.HttpURLConnection;
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



import android.os.AsyncTask;

/*
 * 使用httpclient链接方式，封装服务端处理(回调方式)
 * */
public class HttpConnetUtil {

	public static final String GET = "GET";
	public static final String POST = "POST";

	public HttpConnectCallBack message;

	public interface HttpConnectCallBack {
		public void backMessage(String message);
	}

	/*
	 * get解析用户数据
	 */
	public String parseUserInfoByGet(String url, HashMap<String, Object> map) {
		if (map != null) {
		StringBuilder builder = new StringBuilder();
		url = url + "?";
		for (String key : map.keySet()) {
			builder.append(key);
			builder.append("=");
			builder.append(map.get(key));
			builder.append("&");
		}
		url = url + builder.substring(0, builder.length() - 1);
		}
		return url;
	}

	/*
	 * post解析用户数据
	 */
	public ArrayList<BasicNameValuePair> parseUserInfoByPost(
			HashMap<String, Object> map) {
		ArrayList<BasicNameValuePair> list = null;
	
		if (map != null) {
		list = new ArrayList<BasicNameValuePair>();
		for (String key : map.keySet()) {

			BasicNameValuePair namePair = new BasicNameValuePair(key,
					(String) map.get(key));
			list.add(namePair);
		}
		}
		return list;
		
	}

	public void httpConnet(final String url, final HashMap<String, Object> map,
			String method, final HttpConnectCallBack message) {
		this.message = message;
		if (method.equals(GET)) {
			
				new AsyncTask<String, Void, String>() {

					@Override
					protected String doInBackground(String... params) {
						String httpUrl = parseUserInfoByGet(params[0], map);
						String result = HttpGetCon(httpUrl);
						return result;
					}
					protected void onPostExecute(String result) {
						message.backMessage(result);
					};
				}.execute(url);
			
		} else {
			
				new AsyncTask<String, Void, String>() {

					@Override
					protected String doInBackground(String... params) {
						ArrayList<BasicNameValuePair> list = parseUserInfoByPost(map);
						String result = HttpPostCon(params[0], list);
						return result;
					}
					protected void onPostExecute(String result) {
						message.backMessage(result);
					};
				}.execute(url);

			
		}
	}

	/*
	 * post读取服务端返回的值
	 */
	public String HttpPostCon(String url, ArrayList<BasicNameValuePair> list) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		String result = null;
		try {
			if (list != null) {
				// 乱码问题解决方法一
				HttpEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8); // 加上utf-8解决乱码问题，服务端也应加上req.setCharacterEncoding("UTF-8");
				// 乱码问题解决方法二，服务端去掉req.setCharacterEncoding("UTF-8")时
				// post.setHeader("Content-Type",
				// "application/x-www-form-urlencoded; charset=utf-8");
				
				post.setEntity(entity);
			}
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
				result = EntityUtils.toString(res.getEntity(),HTTP.UTF_8); // 获取从服务器端发送的值
				
			}else {
				result = "网络出错";
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * get读取服务端返回的值
	 */
	public String HttpGetCon(String httpUrl) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(httpUrl);
		String result = null;
		try {
			HttpResponse res = client.execute(get);
			Logs.e("code---"+res.getStatusLine().getStatusCode());
			if (res.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
				result = EntityUtils.toString(res.getEntity(),HTTP.UTF_8); // 获取从服务器端发送的值
				
			}else {
				result = "网络出错";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
