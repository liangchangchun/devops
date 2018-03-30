package com.zww.api.util.wx;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


public class GetWxOrderno {

    static {
    }


    public static Map getPayNo(String url, String xmlParam) {
        HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);
        String prepay_id = "";
        Map<String, Object> dataMap = new HashMap<>();
        try {
            HttpClient http_client = new DefaultHttpClient();
            httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
            HttpResponse response = http_client.execute(httpost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            File logfile = new File("/tmp/wx.log");
            BufferedWriter writer = new BufferedWriter(new FileWriter(logfile));
            writer.write("jsonStr=" + jsonStr);
            writer.flush();
            writer.close();
            //logger.info("微信预支付信息: jsonStr=" + jsonStr);
            if (jsonStr.indexOf("FAIL") != -1) {
                return dataMap;
            }
            Map map = doXMLParse(jsonStr);
            String return_code = (String) map.get("return_code");
            prepay_id = (String) map.get("prepay_id");
            String mweb_url = (String) map.get("mweb_url");
            String result_code = (String) map.get("result_code");
            dataMap.put("prepay_id", prepay_id);
            dataMap.put("mweb_url", mweb_url);
            dataMap.put("return_code", return_code);
            dataMap.put("result_code", result_code);
            dataMap.put("nonce_str", (String) map.get("nonce_str"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dataMap;
    }

    public static Map doXMLParse(String strxml) throws Exception {
        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        in.close();

        return m;
    }

    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

}