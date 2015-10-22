package com.example.ui.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.layout.R;

public class PopupWindowActivity extends Activity {
	private Button mPopWinBtn;
	private PopupWindow mPopWin;
	private boolean hasMeasured;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.popwindow_view);

		mPopWinBtn = (Button) findViewById(R.id.pop_window_btn);
		mPopWin = new PopupWindow(setPopWindowData());
		mPopWinBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (mPopWin.isShowing()) {
					mPopWin.dismiss();
				} else {
					mPopWin.showAsDropDown(v);
				}

			}
		});

		/*
		 * 让下拉框显示出来，无这部分则不会显示
		 */
		ViewTreeObserver vto = mPopWinBtn.getViewTreeObserver();
		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			public boolean onPreDraw() {
				if (hasMeasured == false) {
					int btWidth = mPopWinBtn.getMeasuredWidth(); // 得到按钮宽度
					int btHeight = mPopWinBtn.getMeasuredHeight(); // 得到按钮高度

					mPopWin.setWidth(btWidth); // 设置宽高属性，两属性缺一不可
					mPopWin.setHeight(500);
					hasMeasured = true;
				}
				return true;
			}
		});

	}

	public View setPopWindowData() {

		// 直接使用适配器控件布局,若加入外部布局则会报错
		View v = LayoutInflater.from(this).inflate(
				R.layout.pop_window_listview, null);
		ListView content = (ListView) v;

		String[] popData = { "添加", "删除", "修改", "插入" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, popData);
		content.setAdapter(adapter);

		return content;
	}

}
