package com.zww.api.util.wx;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil {

	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
				Map<String, String> map = new HashMap<String, String>();

		InputStream inputStream = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();

		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		inputStream.close();
		inputStream = null;

		return map;
	}

	public static SortedMap<String, String> dom4jXMLParse(HttpServletRequest request) throws DocumentException, IOException {
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		/** 获取微信调用notify_url的返回XML信息 */
		String result = new String(outSteam.toByteArray(), "utf-8");
		SortedMap<String, String> smap = new TreeMap<String, String>();
		Document doc = DocumentHelper.parseText(result);
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			smap.put(e.getName(), e.getText());
		}
		return smap;
	}
}
