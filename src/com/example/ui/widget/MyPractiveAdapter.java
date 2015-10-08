package com.example.ui.widget;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.layout.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MyPractiveAdapter extends Activity {

	private ListView mPtListView;
	private Spinner mSpinnerlist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		setContentView(R.layout.adapter_practive);
		mPtListView = (ListView) findViewById(R.id.practive_item_list);
		setData(list);
		PractiveAdapter adapter = new PractiveAdapter(this);
		adapter.setList(list);
		mPtListView.setAdapter(adapter);
		//=================================
		String[] str = {"美食","1","2","3","4"};
		mSpinnerlist = (Spinner) findViewById(R.id.spinner_1_item);
		ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
		mSpinnerlist.setAdapter(adapter2);
		
		String[] str2 = {"全城","1","2","3","4"};
		mSpinnerlist = (Spinner) findViewById(R.id.spinner_2_item);
		ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str2);
		mSpinnerlist.setAdapter(adapter3);
		
		String[] str3 = {"智能排序","1","2","3","4"};
		mSpinnerlist = (Spinner) findViewById(R.id.spinner_3_item);
		
		ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str3);
		mSpinnerlist.setAdapter(adapter4);
		
		String[] str4 = {"筛选","1","2","3","4"};
		mSpinnerlist = (Spinner) findViewById(R.id.spinner_4_item);
		ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str4);
		mSpinnerlist.setAdapter(adapter5);
	}
	
	public void setData(ArrayList<HashMap<String, Object>> list){
		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("iconImage1", R.drawable.meituan_image1);
		item.put("iconImage2", R.drawable.ic_label_nobooking);
		item.put("title", "天府美食坊");
		item.put("descripe", "代金卷全场可用");
		item.put("price", "9.9");
		item.put("RMB", "元");
		item.put("deletprice", "20元");
//		item.put("priceImage", R.drawable.ic_clear_search_holo_light);
		item.put("grade", "4.1分（1200）");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("iconImage1", R.drawable.meituan_image3);
		item.put("iconImage2", R.drawable.ic_label_nobooking);
		item.put("title", "339旋转餐厅");
		item.put("descripe", "100元代金券1张，全场通用，可叠加使用");
		item.put("price", "19.9");
		item.put("RMB", "元");
		item.put("deletprice", "30元");
//		item.put("priceImage", R.drawable.ic_clear_search_holo_light);
		item.put("grade", "4.2分（2200）");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("iconImage1", R.drawable.meituan_image8);
		item.put("iconImage2", R.drawable.ic_label_nobooking);
		item.put("title", "码头故事（二号桥店）");
		item.put("descripe", "铁板饭2选1，有赠品，提供免费WiFi");
		item.put("price", "29.9");
		item.put("RMB", "元");
		item.put("deletprice", "40元");
//		item.put("priceImage", R.drawable.ic_clear_search_holo_light);
		item.put("grade", "4.3分（3200）");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("iconImage1", R.drawable.meituan_image1);
		item.put("iconImage2", R.drawable.ic_label_nobooking);
		item.put("title", "爱客得（航空港店）");
		item.put("descripe", "饮品8选1，提供免费WiFi，美味不停歇");
		item.put("price", "39.9");
		item.put("RMB", "元");
		item.put("deletprice", "50元");
//		item.put("priceImage", R.drawable.ic_clear_search_holo_light);
		item.put("grade", "4.4分（4200）");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("iconImage1", R.drawable.meituan_image3);
		item.put("iconImage2", R.drawable.ic_label_nobooking);
		item.put("title", "一洋刺身（建设路店）");
		item.put("descripe", "美味蒸蛋1份，提供免费WiFi，美味不停歇");
		item.put("price", "49.9");
		item.put("RMB", "元");
		item.put("deletprice", "60元");
//		item.put("priceImage", R.drawable.ic_clear_search_holo_light);
		item.put("grade", "4.5分（5200）");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("iconImage1", R.drawable.meituan_image8);
		item.put("iconImage2", R.drawable.ic_label_nobooking);
		item.put("title", "澳洲肥牛捞捞锅（盐市口店）");
		item.put("descripe", "单人自助，提供免费WiFi");
		item.put("price", "59.9");
		item.put("RMB", "元");
		item.put("deletprice", "70元");
//		item.put("priceImage", R.drawable.ic_clear_search_holo_light);
		item.put("grade", "4.6分（6200）");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("iconImage1", R.drawable.meituan_image1);
		item.put("iconImage2", R.drawable.ic_label_nobooking);
		item.put("title", "吉布鲁牛排●海鲜自助");
		item.put("descripe", "精品黄焖兔，建议2-3人使用，提供免费WiFi");
		item.put("price", "69.9");
		item.put("RMB", "元");
		item.put("deletprice", "89元");
//		item.put("priceImage", R.drawable.ic_clear_search_holo_light);
		item.put("grade", "4.7分（7200）");
		list.add(item);
	}
	
}
