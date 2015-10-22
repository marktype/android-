package com.example.ui.menu;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.layout.R;

public class ContextMenuActivityTest extends Activity {
	private ListView mListView;
	private String[] data = { "上下文菜单", "设置" };
	private ArrayAdapter<String> adapter;
	private Button mMenuBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_content_iem);

		mListView = (ListView) findViewById(R.id.menu_content_list);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		mListView.setAdapter(adapter);
		registerForContextMenu(mListView);

		// 添加导航图标
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		/*
		 * 按钮显示菜单，下面两层监听嵌套
		 */
		mMenuBtn = (Button) findViewById(R.id.menu_btn);
		mMenuBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupMenu pop = new PopupMenu(ContextMenuActivityTest.this, v);

				// 原始解析方法
				// MenuInflater menuInflater = getMenuInflater();
				// menuInflater.inflate(R.menu.context_menu, pop.getMenu());
				// 打包后直接解析
				pop.inflate(R.menu.context_menu);
				pop.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						switch (item.getItemId()) {
						case R.id.menu_context_one:
							Toast.makeText(ContextMenuActivityTest.this, "添加",
									Toast.LENGTH_SHORT).show();
							break;
						case R.id.menu_context_two:
							Toast.makeText(ContextMenuActivityTest.this, "删除",
									Toast.LENGTH_SHORT).show();
							break;
						default:
							break;
						}
						return false;
					}
				});
				pop.show(); // 在窗口上显示
			}
		});

	}

	@Override
	public void onCreateContextMenu(android.view.ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater content = getMenuInflater();
		content.inflate(R.menu.context_menu, menu);

	}

	/*
	 * 长按会弹出菜单选项
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();//

		String itemName;
		switch (item.getItemId()) {
		case R.id.menu_context_one:
			itemName = adapter.getItem(info.position); // 获取选项信息
			Toast.makeText(this, "添加成功" + itemName, Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_context_two:
			itemName = adapter.getItem(info.position);
			Toast.makeText(this, "删除成功" + itemName, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

		return super.onContextItemSelected(item);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar_view, menu);

		// actionView需要设置属性时执行下代码
		// MenuItem searchItem = menu.findItem(R.id.action_bar_overflow_search);
		// SearchView searchView = (SearchView) searchItem.getActionView();

		MenuItem shareItem = menu.findItem(R.id.action_bar_overflow_share);
		ShareActionProvider provider = (ShareActionProvider) shareItem
				.getActionProvider();
		provider.setShareIntent(getDefaultIntent());

		return super.onCreateOptionsMenu(menu);
	}

	private Intent getDefaultIntent() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		return intent;

	}

	
	
	

	/*
	 * 菜单点击事件
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);

	}
}
