package com.example.http;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.basicinfo.HttpConnetUtil;
import com.example.basicinfo.Logs;
import com.example.basicinfo.HttpConnetUtil.HttpConnectCallBack;
import com.example.flash.view.XListView;
import com.example.flash.view.XListView.IXListViewListener;
import com.example.layout.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AnotherParseHttpData extends Activity implements IXListViewListener{
	private XListView mContentList;
	private ProgressBar mProgress;
	private	LruCache<String, Bitmap> mMemoryCache;
	private String url = "http://192.168.1.129:8080/qw/around";
	private PractiveAdapter adapter;
	private Boolean isUp = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.http_another_parse_data_layout);
		initLruCache();//初始化内存
		mContentList = (XListView) findViewById(R.id.http_listview);
		mProgress = (ProgressBar) findViewById(R.id.flash_progress);
		mContentList.setPullLoadEnable(true);  
		mContentList.setEmptyView(mProgress);    //listview没有加载上时启动
		mContentList.setXListViewListener(this);    //注册
		adapter = new PractiveAdapter(this);

		connetHttpGetStr(url);

		mContentList.setAdapter(adapter);

	}

	/*
	 * 链接网络，获取json字符串
	 */
	public void connetHttpGetStr(String url) {
		HttpConnetUtil connetUtil = new HttpConnetUtil();
		connetUtil.httpConnet(url, null, "GET", new HttpConnectCallBack() {

			@Override
			public void backMessage(String message) {
				ArrayList<HashMap<String, Object>> list = parseHttpData(message);
				if (isUp) {
					adapter.setList(list);
					isUp = false;
				}else{
					adapter.addList(list);
				}
			}
		});

	}

	/*
	 * 解析数据信息,获取数据源
	 */
	public ArrayList<HashMap<String, Object>> parseHttpData(String str) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			JSONObject object = new JSONObject(str);

			JSONObject json = object.getJSONObject("info");
			JSONArray array = json.getJSONArray("merchantKey");
			for (int i = 0; i < array.length(); i++) {
				final HashMap<String, Object> item = new HashMap<String, Object>();
				JSONObject student = array.getJSONObject(i);
				String name = student.getString("name");
				String coupon = student.getString("coupon");
				String location = student.getString("location");
				String distance = student.getString("distance");
				String picUrl = student.getString("picUrl");

				String couponType = student.getString("couponType");
				String cardType = student.getString("cardType");
				String groupType = student.getString("groupType");
				Logs.v("name:" + name + "--" + "pic:" + picUrl);

				item.put("picUrl", picUrl);
				item.put("name", name);
				item.put("coupon", coupon);
				item.put("location", location);
				item.put("distance", distance);

				item.put("couponType", couponType);
				item.put("cardType", cardType);
				item.put("groupType", groupType);

				list.add(item);
			}

			// Logs.e("list111111-----------------------"+list);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 下载图片
	 */
	public Bitmap downLoadImage(String httpUrl) {
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			InputStream is = connection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			return bitmap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * 自定义适配器
	 */
	public class PractiveAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		private LayoutInflater inflater;

		public PractiveAdapter(Context context) {

			inflater = LayoutInflater.from(context);
		}

		public void setList(ArrayList<HashMap<String, Object>> list) {
			this.list = list;
			notifyDataSetChanged();// 更新数据
		}

		public void addList(ArrayList<HashMap<String, Object>> list) {
			this.list.addAll(list);
			notifyDataSetChanged();// 更新数据
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			final ViewHolder viewHolder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.http_list_item_layout,
						null);
				viewHolder = new ViewHolder();
				viewHolder.iconImageitem = (ImageView) convertView
						.findViewById(R.id.http_item_image);

				viewHolder.name = (TextView) convertView
						.findViewById(R.id.http_name_txt);
				viewHolder.coupon = (TextView) convertView
						.findViewById(R.id.http_content_txt);
				viewHolder.location = (TextView) convertView
						.findViewById(R.id.http_address_txt);
				viewHolder.distance = (TextView) convertView
						.findViewById(R.id.http_distance_txt);

				viewHolder.iconImageCard = (ImageView) convertView
						.findViewById(R.id.http_right_card_image);
				viewHolder.iconQuan = (ImageView) convertView
						.findViewById(R.id.http_right_quan_image);
				viewHolder.iconTuan = (ImageView) convertView
						.findViewById(R.id.http_right_tuan_image);
				convertView.setTag(viewHolder);
			} else {

				viewHolder = (ViewHolder) convertView.getTag();
			}

			HashMap<String, Object> item = (HashMap<String, Object>) getItem(position);
			String picUrl = (String) item.get("picUrl");
			String couponType = (String) item.get("couponType");
			String cardType = (String) item.get("cardType");
			String groupType = (String) item.get("groupType");
			Logs.i("pic--" + picUrl);
			String name = (String) item.get("name");
			String coupon = (String) item.get("coupon");
			String location = (String) item.get("location");
			String distance = (String) item.get("distance");

//			new AsyncTask<String, Void, Bitmap>() {
//
//				@Override
//				protected Bitmap doInBackground(String... params) {
//					Bitmap bitmap = downLoadImage(params[0]);
//					return bitmap;
//				}
//
//				protected void onPostExecute(Bitmap result) {
//					if (result != null) {
//						viewHolder.iconImageitem.setImageBitmap(result);
//					}
//				};
//			}.execute(picUrl);

			loadBitmap(getResources(), picUrl, viewHolder.iconImageitem,
					R.drawable.ic_launcher);
			
			viewHolder.name.setText(name);
			viewHolder.coupon.setText(coupon);
			viewHolder.location.setText(location);
			viewHolder.distance.setText(distance);

			//此处必须加if，else加载显示与隐藏，若只有一个则会出现图片显示错乱的情况
			if (groupType.equalsIgnoreCase("NO")) {
				viewHolder.iconTuan.setVisibility(View.GONE);
			}else {
				viewHolder.iconTuan.setVisibility(View.VISIBLE);
			}
			if (couponType.equalsIgnoreCase("NO")) {
				viewHolder.iconQuan.setVisibility(View.GONE);
			}else{
				viewHolder.iconQuan.setVisibility(View.VISIBLE);
			}
			if (cardType.equalsIgnoreCase("NO")) {
				viewHolder.iconImageCard.setVisibility(View.GONE);
			}else {
				viewHolder.iconImageCard.setVisibility(View.VISIBLE);
			}
			

			return convertView;
		}

		class ViewHolder {
			ImageView iconImageitem, iconImageCard, iconQuan, iconTuan;
			TextView name, coupon, location, distance;
		}
	}
	
	/**
	 * 初始化LRUCache
	 */
	public void initLruCache(){
		// 获取应用程序最大可用内存
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 8;
		// 设置图片缓存大小为程序最大可用内存的1/8
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount();
			}
		};
	}
	/**
	 * 添加图片到内存缓存
	 */
	public void addBitmapToCache(String url,Bitmap bitmap){
		if(getCacheBitmap(url) == null){
			mMemoryCache.put(url, bitmap);
		}
	}
	/**
	 * 从缓存中获取图片
	 * @param url
	 * @return
	 */
	public Bitmap getCacheBitmap(String url){
		Bitmap bitmap = mMemoryCache.get(url);
		return bitmap;
	}
	
	/**
	 * 通常类似 ListView 与 GridView 等视图组件在使用上面演示的AsyncTask 方法时会同时带来另外一个问题。
	 * 为了更有效的处理内存，那些视图的子组件会在用户滑动屏幕时被循环使用。如果每一个子视图都触发一个AsyncTask ，
	 * 那么就无法确保当前视图在结束task时，分配的视图已经进入循环队列中给另外一个子视图进行重用。 而且, 无法确保所有的
	 * 异步任务能够按顺序执行完毕。
	 * 
	 * @param imageUrl
	 * @param imageView
	 * @param resId
	 *            默认图片资源
	 */
	public void loadBitmap(Resources res, String imageUrl, ImageView imageView,
			int resId) {

		// 第一步：根据图片地址，判断图片是否被缓存在内存
				Bitmap bitmap = getCacheBitmap(imageUrl);
				if(bitmap != null){
					imageView.setImageBitmap(bitmap);
					Logs.v("从内存获取图片");
					return;
				}
		if (cancelPotentialWork(imageUrl, imageView)) {
			BitmapWorkerTask task = new BitmapWorkerTask(imageView);

			AsyncDrawable asyncDrawable = new AsyncDrawable(res,
					BitmapFactory.decodeResource(res, resId), task);
			imageView.setImageDrawable(asyncDrawable);

			task.execute(imageUrl);
		}
	}

	public static boolean cancelPotentialWork(String imageUrl,
			ImageView imageView) {
		final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

		if (bitmapWorkerTask != null) {
			final String bitmapData = bitmapWorkerTask.data;
			if (!bitmapData.equals(imageUrl)) {
				// Cancel previous task
				bitmapWorkerTask.cancel(true);
			} else {
				// The same work is already in progress
				return false;
			}
		}
		// No task associated with the ImageView, or an existing task was
		// cancelled
		return true;
	}

	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
		if (imageView != null) {
			final Drawable drawable = imageView.getDrawable();
			if (drawable instanceof AsyncDrawable) {
				final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
				return asyncDrawable.getBitmapWorkerTask();
			}
		}
		return null;
	}

	/**
	 * 创建一个专用的Drawable的子类来储存返回工作任务的引用。在这种情况下，当任务完成时BitmapDrawable会被使用
	 * 
	 */
	static class AsyncDrawable extends BitmapDrawable {
		private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

		public AsyncDrawable(Resources res, Bitmap bitmap,
				BitmapWorkerTask bitmapWorkerTask) {
			super(res, bitmap);
			bitmapWorkerTaskReference = new WeakReference<BitmapWorkerTask>(
					bitmapWorkerTask);
		}

		public BitmapWorkerTask getBitmapWorkerTask() {
			return (BitmapWorkerTask) bitmapWorkerTaskReference.get();
		}
	}

	class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
		private final WeakReference<ImageView> imageViewReference;// 弱引用
																	// ，特点当内存不够时，自动回收
		private String data = "";

		public BitmapWorkerTask(ImageView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			imageViewReference = new WeakReference<ImageView>(imageView);
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(String... params) {
			data = params[0];
			return downLoadImage(data);
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (isCancelled()) {
				bitmap = null;
			}

			if (imageViewReference != null && bitmap != null) {
				final ImageView imageView = (ImageView) imageViewReference
						.get();
				final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
				if (this == bitmapWorkerTask && imageView != null) {
					imageView.setImageBitmap(bitmap);
					addBitmapToCache(data, bitmap);   //添加图片到缓存
				}
			}
		}
	}

	private void onLoad() {
		mContentList.stopRefresh();
		mContentList.stopLoadMore();
	}
	@Override
	public void onRefresh() {
		new AsyncTask<Void, Void, Void>(){

			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				isUp = true;
				connetHttpGetStr(url);
				return null;
			}
			
			protected void onPostExecute(Void result) {
				onLoad();
			};
		}.execute();

	}

	@Override
	public void onLoadMore() {
		new AsyncTask<Void, Void, Void>(){

			@Override
			protected Void doInBackground(Void... params) {
				
				
				return null;
			}
			
			protected void onPostExecute(Void result) {
				connetHttpGetStr(url);
				onLoad();
			};
		}.execute();
	}
}
