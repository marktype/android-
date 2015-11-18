package com.example.asynctask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.basicinfo.Logs;
import com.example.layout.R;
import com.example.ui.widget.Logcat_Switch;

public class DownLoadActivity extends Activity {
	private Button mDownLoadBtn;
	private GridView mDownLoadView;
	MyDownAsyncTask myAsyncTask;
	ImageAdapter adapter;
	private Executor exec;
	private ArrayList<Bitmap> list = new ArrayList<Bitmap>();
	String[] image = {"http://img3.imgtn.bdimg.com/it/u=3564124530,3069531013&fm=21&gp=0.jpg",
				"http://img2.imgtn.bdimg.com/it/u=4114593003,3380074209&fm=21&gp=0.jpg",
				"http://img4.imgtn.bdimg.com/it/u=1769854738,4294318444&fm=21&gp=0.jpg",
				"http://img0.imgtn.bdimg.com/it/u=2116741773,4287423763&fm=21&gp=0.jpg",
				"http://img3.imgtn.bdimg.com/it/u=3981721939,3831880878&fm=21&gp=0.jpg",
				"http://img4.imgtn.bdimg.com/it/u=817539089,3446358281&fm=21&gp=0.jpg",
				"http://img1.imgtn.bdimg.com/it/u=2070436659,3906009764&fm=21&gp=0.jpg",
				"http://img4.imgtn.bdimg.com/it/u=1423086322,1916377740&fm=21&gp=0.jpg",
				"http://img2.imgtn.bdimg.com/it/u=3342608294,529148826&fm=21&gp=0.jpg"}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.asynctask_download_layout);
		mDownLoadView = (GridView) findViewById(R.id.async_gridview);
		mDownLoadBtn = (Button) findViewById(R.id.async_btn);
		exec = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		
//		adapter = new ImageAdapter(this);
		
		mDownLoadBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				for(int i = 0;i<image.length;i++){
//					myAsyncTask = new MyDownAsyncTask();
//					myAsyncTask.execute(image[i]);
//				}
//				mDownLoadView.setAdapter(adapter);
				
				ImageAdapterTwo adapterTwo = new ImageAdapterTwo(DownLoadActivity.this);
				mDownLoadView.setAdapter(adapterTwo);
			}
		});
		
		
		
		
		
	}
	/*
	 * 访问网络时需要在manifest加上网络权限
	 * */
	public Bitmap downLoadImage(String httpUrl){
		try {
			URL url = new URL(httpUrl);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			InputStream is = connection.getInputStream();
			
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			return bitmap;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public class MyDownAsyncTask extends AsyncTask<String, Void, Bitmap>{
		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = downLoadImage(params[0]);
			return bitmap;
		}
	
		
		
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			
			list.add(result);
			adapter.setData(list);
		}
	}
	
	
	
	public class ImageAdapter extends BaseAdapter{
		
		private LayoutInflater inflater;
		ArrayList<Bitmap> list = new ArrayList<Bitmap>();
		public ImageAdapter(Context context){
			inflater = LayoutInflater.from(context);
		}
		
		public void setData(ArrayList<Bitmap> list){
			this.list = list;
			notifyDataSetChanged();
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView gridview;
			
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.asynctask_gridview_layout, null);
				 gridview = (ImageView) convertView.findViewById(R.id.async_item_img);
				
				convertView.setTag(gridview);
			}else{
				gridview = (ImageView) convertView.getTag();
			}
			
			 
			gridview.setImageBitmap(list.get(position));
			
			
			return convertView;
		}
		
	}
	public class ImageAdapterTwo extends BaseAdapter{
		
		private LayoutInflater inflater;
//		private String[] image;
		public ImageAdapterTwo(Context context){
			inflater = LayoutInflater.from(context);
		}
		
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return image.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return image[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ImageView gridview;
			
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.asynctask_gridview_layout, null);
				 gridview = (ImageView) convertView.findViewById(R.id.async_item_img);
				
				convertView.setTag(gridview);
			}else{
				gridview = (ImageView) convertView.getTag();
			}
			
			new AsyncTask<String, Void, Bitmap>(){

				@Override
				protected Bitmap doInBackground(String... params) {
					// TODO Auto-generated method stub
					return downLoadImage(image[position]);
				}
				@Override
				protected void onPostExecute(Bitmap result) {
					super.onPostExecute(result);
					gridview.setImageBitmap(result);
				}
				
//			}.execute(image);    //单任务执行
		}.executeOnExecutor(exec, image) ;  //多任务执行
			
			
			
			return convertView;
		}
		
	}
}


