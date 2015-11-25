package com.example.xmlparse;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.example.basicinfo.Logs;

import android.test.AndroidTestCase;
import android.util.Xml;

public class XmlParseTest extends AndroidTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	//将xml文件转换为数据
	public void xmlParseTest() throws IOException, XmlPullParserException{
		XmlPullParser pullParser = Xml.newPullParser();
		InputStream is = getContext().getAssets().open("parse.xml");   //解析文本
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
		
	}
	
	//将数据转换为xml字符串
	public void serialize() throws IllegalArgumentException, IllegalStateException, IOException{
		XmlSerializer serializer = Xml.newSerializer();
		ArrayList<Books> bookList = new ArrayList<Books>();
		Books books = new Books();
		books.setId(12);
		books.setName("android解析xml学习1");
		books.setPrice(23.3);
		bookList.add(books);
		
		books = new Books();
		books.setId(13);
		books.setName("android解析xml学习2");
		books.setPrice(24.3);
		bookList.add(books);
		
		books = new Books();
		books.setId(14);
		books.setName("android解析xml学习3");
		books.setPrice(25.3);
		bookList.add(books);
		
		StringWriter os = new StringWriter();
		
		serializer.setOutput(os);
		serializer.startDocument("UTF-8", true);
		serializer.startTag("", "books");
		
		for (Books books2 : bookList) {
			serializer.startTag("", "book");
			
			serializer.startTag("", "id");
			serializer.text(books2.getId()+"");
			serializer.endTag("", "id");
			
			serializer.startTag("", "name");
			serializer.text(books2.getName());
			serializer.endTag("", "name");
			
			serializer.startTag("", "price");
			serializer.text(books2.getPrice()+"");
			serializer.endTag("", "price");
			
			serializer.endTag("", "book");
		}
		serializer.endTag("", "books");
		serializer.endDocument();
		
		Logs.d(""+os.toString());
	}
}
