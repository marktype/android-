package com.example.store.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.basicinfo.Logs;
import com.example.layout.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SendImageStore extends Activity {
	private ImageView mImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.file_store_image);
		mImageView = (ImageView) findViewById(R.id.file_store_img);
		
	}

	public void onClickInputFile(View v) {
		/*保存在外部公共区域*/
		if (isExternalStorageWritable()) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.ddd);
			
			File pathName = new File(
					Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+
					"ddd.jpg");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(pathName);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
				Toast.makeText(this, "读取完成", Toast.LENGTH_SHORT).show();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			Logs.v("外部存储不可用");
		}
	}

	/*
	 * 检测外部储存是否可用，true为可用
	 * */
	private boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}

	}

	public void onClickOutputFile(View v) {
		if (isExternalStorageWritable()) {
		File pathName = new File(
				Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+
				"ddd.jpg");
		Bitmap bitmap = BitmapFactory.decodeFile(pathName.getAbsolutePath());
		mImageView.setImageBitmap(bitmap);
		}else{
			Logs.v("外部存储不可用");
		}
	}
}
