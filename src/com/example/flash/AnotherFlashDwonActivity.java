package com.example.flash;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.basicinfo.HttpConnetUtil;
import com.example.basicinfo.Logs;
import com.example.basicinfo.HttpConnetUtil.HttpConnectCallBack;
import com.example.flash.view.XListView;
import com.example.flash.view.XListView.IXListViewListener;
import com.example.layout.R;

public class AnotherFlashDwonActivity extends Activity implements IXListViewListener{
	private XListView mFlashListView;
	private ProgressBar mProgress;
	private FlashAdapter adapter;
	private int currentPage = 1;
	private Handler mHandler;
	private int totalPage;
	private String url = "http://192.168.1.129:8080/app/ttt";
//	private String url = "http://192.168.1.145/app/page?pageNo=1&pageSize=20";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		mFlashListView =  (XListView) findViewById(R.id.xListView);
		mProgress = (ProgressBar) findViewById(R.id.flash_progress);
		mFlashListView.setPullLoadEnable(true);    //控制progress是否存在
		
		mFlashListView.setEmptyView(mProgress);    //listview没有加载上时启动
		
		adapter = new FlashAdapter(this);
		getDataByHttp(currentPage);   //第一次进入时加载
		mFlashListView.setXListViewListener(this);    //注册
		mHandler = new Handler();
		mFlashListView.setAdapter(adapter);
	}

	public void getDataByHttp(final int pageNo) {
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
					Logs.d("list--"+list);
					if (pageNo == 1) {
						adapter.setlist(list);
					}else{
						adapter.addlist(list);   //添加数据到适配器
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	private void onLoad() {
		mFlashListView.stopRefresh();
		mFlashListView.stopLoadMore();
	}
	
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				currentPage = 1;
				getDataByHttp(currentPage);
				
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				
				
				if (totalPage >= ++currentPage) {
					getDataByHttp(currentPage);
					
				}else {
					mFlashListView.setPullLoadEnable(false);
				}
				
				onLoad();
			}
		}, 2000);
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

	
}
