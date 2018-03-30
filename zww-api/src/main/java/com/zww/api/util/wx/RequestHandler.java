package com.zww.api.util.wx;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zww.api.util.StringUtils;

public class RequestHandler {
    private String tokenUrl;
    private String gateUrl;
    private String notifyUrl;
    private String appid;
    private String appkey;
    private String partnerkey;
    private String appsecret;
    private String key;
    private SortedMap parameters;
    /**
     * Token
     */
    private String Token;
    private String charset;
    private String debugInfo;
    private String last_errcode;

    private HttpServletRequest request;

    private HttpServletResponse response;

    public RequestHandler(HttpServletRequest request,
                          HttpServletResponse response) {
        this.last_errcode = "0";
        this.request = request;
        this.response = null;
        //this.charset = "GBK";
        this.charset = "UTF-8";
        this.parameters = new TreeMap();
        notifyUrl = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";

    }

    public void init(String app_id, String app_secret, String partner_key) {
        this.last_errcode = "0";
        this.Token = "token_";
        this.debugInfo = "";
        this.appid = app_id;
        this.partnerkey = partner_key;
        this.appsecret = app_secret;
        this.key = partner_key;
    }

    public void init() {
    }

    public String getLasterrCode() {
        return last_errcode;
    }

    public String getGateUrl() {
        return gateUrl;
    }


    public String getParameter(String parameter) {
        String s = (String) this.parameters.get(parameter);
        return (null == s) ? "" : s;
    }

    public void setKey(String key) {
        this.partnerkey = key;
    }

    public void setAppKey(String key) {
        this.appkey = key;
    }

    public String UrlEncode(String src) throws UnsupportedEncodingException {
        return URLEncoder.encode(src, this.charset).replace("+", "%20");
    }

    public String genPackage(SortedMap<String, String> packageParams)
            throws UnsupportedEncodingException {
        String sign = createSign(packageParams);

        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append(k + "=" + UrlEncode(v) + "&");
        }

        String packageValue = sb.append("sign=" + sign).toString();
        return packageValue;
    }

    public String createSign(SortedMap<String, String> packageParams) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + this.getKey());
        String sign = MD5Util.MD5Encode(sb.toString(), this.charset).toUpperCase();
        return sign;

    }


    public String createSha1Sign(SortedMap<String, String> packageParams) {
        String sign = null;
        try {
            sign = Sha1Util.createSHA1Sign(packageParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    public boolean isWechatSign(SortedMap<String, String> smap) {
        StringBuffer sb = new StringBuffer();
        Set es = smap.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && !"key".equals(k) && StringUtils.isNotEmpty(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=svM34iakaVkbLEMjPNn4bbsMzR1fllIQ");
        /** 验证的签名 */
        String sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
        /** 微信端返回的合法签名 */
        String validSign = ((String) smap.get("sign")).toUpperCase();
        //System.out.println("sign=" + sign + ",validSign=" + validSign);
        return validSign.equals(sign);
    }

    public boolean createMd5Sign(String signParams) {
        StringBuffer sb = new StringBuffer();
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }

        String enc = TenpayUtil.getCharacterEncoding(this.request,
                this.response);
        String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

        String tenpaySign = this.getParameter("sign").toLowerCase();

        this.setDebugInfo(sb.toString() + " => sign:" + sign + " tenpaySign:"
                + tenpaySign);

        return tenpaySign.equals(sign);
    }

    public String parseXML() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {

                sb.append("<" + k + ">" + getParameter(k) + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    protected void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    public void setPartnerkey(String partnerkey) {
        this.partnerkey = partnerkey;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public String getKey() {
        return partnerkey;
    }

}
