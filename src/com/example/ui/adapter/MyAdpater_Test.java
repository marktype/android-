package com.example.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.layout.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MyAdpater_Test extends Activity {
	private ListView mMyListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_adapter);
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
	setdata(list);
	mMyListView = (ListView) findViewById(R.id.simple_listview);
	
	
	Event_MyBaseAdapter myAdapter = new Event_MyBaseAdapter(this);
	myAdapter.setlist(list);
	mMyListView.setAdapter(myAdapter);
	
	
	
	
	
	
	}
	public void setdata(ArrayList<HashMap<String, Object>> list) {
		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("icon", R.drawable.team_buy_pic17);
		item.put("title", "1简州大饭店");
		item.put("content", "好吃你就多吃点");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.team_buy_pic17);
		item.put("title", "2简州大饭店");
		item.put("content", "好吃你就多吃点");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.team_buy_pic17);
		item.put("title", "3简州大饭店");
		item.put("content", "好吃你就多吃点");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.team_buy_pic17);
		item.put("title", "4简州大饭店");
		item.put("content", "好吃你就多吃点");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.team_buy_pic17);
		item.put("title", "5简州大饭店");
		item.put("content", "好吃你就多吃点");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("icon", R.drawable.team_buy_pic17);
		item.put("title", "6简州大饭店");
		item.put("content", "好吃你就多吃点");
		list.add(item);
	}
}
