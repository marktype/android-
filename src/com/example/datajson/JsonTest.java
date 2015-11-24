package com.example.datajson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.basicinfo.HttpConnetUtil;
import com.example.basicinfo.HttpConnetUtil.HttpConnectCallBack;
import com.example.basicinfo.Logs;

import android.test.AndroidTestCase;
import android.util.Log;

public class JsonTest extends AndroidTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void myJson(){
		String str = "{\"student\":{\"name\":\"小胡\",\"age\":12,\"id\":\"123\",\"sex\":\"男\"}}";
		
		try {
			JSONObject object = new JSONObject(str);
			
			JSONObject student = object.getJSONObject("student");
			String name = student.getString("name");
			int age = student.getInt("age");
			String id = student.getString("id");
			String sex = student.getString("sex");
			Logs.d("name--"+name+"age--"+age+"id--"+id+"sex--"+sex);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fileJson(){
		
		StringBuffer stringBuffer = new StringBuffer();
		String line = null ;
		try {
		BufferedReader br = new BufferedReader(new FileReader(new File("json.txt")));
		while( (line = br.readLine())!= null ){
		stringBuffer.append(line);
		} 
		Logs.d(stringBuffer.toString());
		} catch (FileNotFoundException e) {
		       e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}finally{
			
		}
	}
	
	public void TestAssetsList(){
		try {
			String[] list = getContext().getAssets().list("");
			for (String string : list) {
				Logs.d(string+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readAssetsFile() throws IOException{
		InputStream is = getContext().getAssets().open("user");
		byte[] by = new byte[1024];
		int num ;
		while((num = is.read(by))!=-1){
			String str = new String(by, 0, num);
			Logs.d(str);
		}
		
	}

	/*
	 * 服务不能在测试中使用，此段代码无效
	 * */
	public void readHttpConnet(){
		HttpConnetUtil connetUtil = new HttpConnetUtil();
		connetUtil.httpConnet("http://192.168.1.172:8080/qw/1.txt", null, "GET", new HttpConnectCallBack() {
			
			@Override
			public void backMessage(String message) {
				Logs.d(message);
			}
		});
	}
	
	public void buildJson() throws JSONException{
		JSONObject object = new JSONObject();
		object.put("name", "张三");
		object.put("password", "123");
		JSONObject object2 = new JSONObject();
		object2.put("name", "张三");
		object2.put("password", "123");
		
		JSONArray array = new JSONArray();
		array.put(0, object);
		array.put(1, object2);
	}
}
