package com.example.mediaplay;

import java.io.File;
import java.util.ArrayList;

import com.example.layout.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Mp3ListItemActivity extends Activity {

	private ListView mListView;
	private ArrayList<File> mFileList = new ArrayList<File>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mp3_list_item);
		
		mListView = (ListView) findViewById(R.id.mp3_listview);
	}
	
	
	/*
	 * 查找音乐文件
	 * */
	public void showFile(File file) {
		
		File[] listFile = file.listFiles();

		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				if (listFile[i].isDirectory()) {
					showFile(listFile[i]);
					
				} else {
					if (listFile[i].getName().endsWith("mp3")) {
						mFileList.add(listFile[i]);
					}
				}

			}
		}
	}
}
