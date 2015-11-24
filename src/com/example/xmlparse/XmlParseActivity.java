package com.example.xmlparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.basicinfo.Logs;
import com.example.layout.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class XmlParseActivity extends Activity {
	private ListView mXmlView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.xml_parse_layout);
		mXmlView = (ListView) findViewById(R.id.xml_parse_listview);
		XmlParseAdapter adapter = new XmlParseAdapter(this);
		
		try {
			adapter.setlist(parseXml());
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mXmlView.setAdapter(adapter);
		
	}
	
	
	public ArrayList<Books> parseXml() throws XmlPullParserException, IOException {

		XmlPullParser pullParser = Xml.newPullParser();
		InputStream is = getAssets().open("parse.xml");   //解析文本
		ArrayList<Books> books = null ;
		Books book = null;
		pullParser.setInput(is, "UTF-8");
		int type = pullParser.getEventType();    //获取事件类型
		while (type != pullParser.END_DOCUMENT) {   //结束文本</books>
			switch(type){
			case XmlPullParser.START_DOCUMENT:    //开始文本<books>
				books = new ArrayList<Books>();
				break;
			case XmlPullParser.START_TAG:    //开始标记   <book>
				if (pullParser.getName().equals("book")) {
					book = new Books();
				}else if (pullParser.getName().equals("id")) {
					type = pullParser.next();    //指向下一个位置，不然无法获取数据
					book.setId(Integer.parseInt(pullParser.getText()));
				}else if (pullParser.getName().equals("name")) {
					type = pullParser.next();      
					book.setName(pullParser.getText());
				}else if (pullParser.getName().equals("price")) {
					type = pullParser.next();
					book.setPrice(Double.valueOf(pullParser.getText()));
				}
				
				break;
			case XmlPullParser.END_TAG:   //结束标记      </books>
				if (pullParser.getName().equals("book")) {
					books.add(book);
					book = null;    //置为空释放资源
				}
				break;
			}
			type = pullParser.next();    //指向下一个标记
			
		}
		for (Books books2 : books) {
			Logs.d("books-----id"+books2.getId()+",name--"+books2.getName());
		}
		return books;
	
	}
	
	
	public class XmlParseAdapter extends BaseAdapter{
		ArrayList<Books> list = new ArrayList<Books>();
		private Context context;
		public XmlParseAdapter(Context context) {
			this.context = context;
			
		
		}
		
		public void setlist(ArrayList<Books> list){
			this.list = list;
		}
		
		@Override
		public int getCount() {//目录数量
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
			LayoutInflater inflater = LayoutInflater.from(context);
			ViewHolder viewHolder;
			if (convertView == null) {
				//解析xml
				convertView = inflater.inflate(R.layout.xml_parse_item_layout, null);
				viewHolder = new ViewHolder();
				//获取格式
//				viewHolder.image = (ImageView) convertView.findViewById(R.id.simple_adapter_img);
				viewHolder.title = (TextView) convertView.findViewById(R.id.xml_item_name_txt);
				viewHolder.content = (TextView) convertView.findViewById(R.id.xml_price_txt);
				
				convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			//获取单项目录
			Books item = (Books) getItem(position);
			//分别获取单项中的数据
//			int img = (Integer) item.get("icon");
			String titleTxt = (String) item.getName();
			Double conTxt = (Double) item.getPrice();
			//设置数据
//			viewHolder.image.setImageResource(img);
			viewHolder.title.setText(titleTxt);
			viewHolder.content.setText(""+conTxt);
			
			
			return convertView;
		}
		//自定义存取对象
		class ViewHolder{
			ImageView image ;
			TextView title ;
			TextView content ;
		}
	}
}
