package com.example.store.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.basicinfo.Logs;
import com.example.layout.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.test.AndroidTestCase;
import android.widget.Toast;

public class FileAndroidTest extends AndroidTestCase {

	private Context context;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	this.context = getContext();
	}
	
	/*
	 * 写文件到固定的文件夹下
	 * */
	public void testWriteFile() throws IOException{
		String name = "android.txt";
		String msg  = "hello world!";
		File path = context.getFilesDir();
		File file = new File(path+"/"+name);
		FileOutputStream fos = null;
		
		try {
			
			fos = new FileOutputStream(file);
			fos.write(msg.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (fos !=null) {
				fos.close();
			}
		}
	}
	
	public void testStoreFile(){
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_category_1);
		File pathName = new File(
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"ic_category_1.png");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(pathName);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			Logs.e("读取完成");
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
