package com.example.flash;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.basicinfo.HttpConnetUtil;
import com.example.basicinfo.Logs;
import com.example.basicinfo.HttpConnetUtil.HttpConnectCallBack;
import com.example.layout.R;

public class FlashDwonActivity extends Activity implements OnScrollListener{
	private ListView mFlashListView;
	private ProgressBar mProgress;
	private FlashAdapter adapter;
	private Boolean isFlash = false;
	private int currentPage = 1;
	private int totalPage;
	private String url = "http://192.168.1.129:8080/app/ttt";
//	private String url = "http://192.168.1.145/app/page?pageNo=1&pageSize=20";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.flash_dwon_listview_layout);
		mFlashListView = (ListView) findViewById(R.id.flash_listview);
		mProgress = (ProgressBar) findViewById(R.id.flash_progress);

		mFlashListView.setEmptyView(mProgress);
		adapter = new FlashAdapter(this);

		getDataByHttp(currentPage);
		mFlashListView.setAdapter(adapter);
		
		mFlashListView.setOnScrollListener(this);
	}

	public void getDataByHttp(int pageNo) {
		HttpConnetUtil connetUtil = new HttpConnetUtil();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pagerNo", pageNo);
		map.put("count", 20);

		connetUtil.httpConnet(url, map, "GET", new HttpConnectCallBack() {

			@Override
			public void backMessage(String message) {
				ArrayList<String> list = new ArrayList<String>();

				try {
					JSONObject json = new JSONObject(message);
					JSONArray array = json.getJSONArray("content");
					totalPage = json.getInt("pagecount");
					
					int num = array.length();
					for (int i = 0; i < num; i++) {
						JSONObject object = array.getJSONObject(i);
						String msg = object.getString("msg");
						list.add(msg);
					}
					adapter.addlist(list);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	public class FlashAdapter extends BaseAdapter {
		ArrayList<String> list = new ArrayList<String>();
		private Context context;
		private LayoutInflater inflater;

		public FlashAdapter(Context context) {
			this.context = context;

			inflater = LayoutInflater.from(context);
		}

		public void setlist(ArrayList<String> list) {
			this.list = list;
			notifyDataSetChanged();   //网络取数据时必须加上，不然无法获取数据
		}

		public void addlist(ArrayList<String> list) {
			this.list.addAll(list);
			notifyDataSetChanged();
		}
		@Override
		public int getCount() {// 目录数量
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = null;
			if (convertView == null) {
				// 解析xml
				v = inflater.inflate(
						android.R.layout.simple_list_item_1, null);

			} else {
				v = convertView;
			}

			String msg = (String) getItem(position);
			TextView msgTxt = (TextView) v;
			msgTxt.setText(msg);
			return v;
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (((firstVisibleItem+visibleItemCount) == totalItemCount) && totalItemCount>0) {
			isFlash = true;
		}
	}

	/*
	 * scrollState状态有1、开始触摸，2、放开触摸还在滚动，0、停止滚动
	 * */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (isFlash && scrollState == SCROLL_STATE_IDLE) {
			if (totalPage >= ++currentPage) {
				getDataByHttp(currentPage);
				
				isFlash = false;
			}
			
		}
		
	}
}
