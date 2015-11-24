package com.example.xmlparse;

import java.io.InputStream;
import java.util.ArrayList;

public interface XmlParse {

	public String XmlParseToXml(String xmlFile);
	public ArrayList<Books> XmlParseToString(InputStream is);
}
