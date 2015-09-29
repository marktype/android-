package com.example.ui.widget;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.layout.R;

public class Event_SimpleAdapter extends Activity implements
		OnItemClickListener {
	private ListView mListViewSimple;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_adapter);
		mListViewSimple = (ListView) findViewById(R.id.simple_listview);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		setdata(list);

		// from Hashmap中的key值
		// to value值取值地址
		String[] from = { "icon", "title", "content" };
		int[] to = { R.id.simple_adapter_img, R.id.simple_title_txt,
				R.id.simple_content_txt };

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.simple_adapter_item_list, from, to);
		mListViewSimple.setAdapter(adapter);
		mListViewSimple.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		// RelativeLayout view = (RelativeLayout) arg1;
		// TextView text = (TextView) view.findViewById(R.id.simple_title_txt);
		// Toast.makeText(this, text.getText(), Toast.LENGTH_SHORT).show();

		SimpleAdapter data = (SimpleAdapter) arg0.getAdapter();
		HashMap<String, Object> item1 = (HashMap<String, Object>) data
				.getItem(arg2);
		String str = (String) item1.get("content");
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
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
