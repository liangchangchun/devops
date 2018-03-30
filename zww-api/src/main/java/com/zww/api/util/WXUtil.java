package com.zww.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zww.api.util.wx.WxConfig;

import net.sf.json.JSONObject;

/**
 * Created by SUN on 2017/12/27.
 */
public class WXUtil {
    private static final Logger logger = LoggerFactory.getLogger(WXUtil.class);
    //private RedisUtil redisUtil = new RedisUtil();
    public static String TOKEN;
    public static String APPID;
    public static String SECRET;
    public static String URLBody;

    static {
        try {
            TOKEN = WxConfig.GZHTOKEN;
            APPID = WxConfig.GZHAPPID;
            SECRET = WxConfig.GZHSECRET;
            URLBody = WxConfig.GZHURLBODY;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //获取基础ACCESSTOKEN的URL
    public static final String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //获取用户信息的URL(需要关注公众号)
    public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN ";
    //创建自定义菜单的URL
    public static final String CREATEMENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //发送模板消息的URL
    public static final String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    //获取网页版的ACCESSTOKEN的URL
    public static final String GET_WEB_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    //通过网页获取用户信息的URL(不需要关注公众号)
    public static final String GET_WEB_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //获取jssdk使用的ticket
    public static final String GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    //获得网络授权
    public static final String GET_NET_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";

    public static String accessToken;
    //accessToken的失效时间
    public static Long expiresTime = 0L;
    //获得jssdk需要的ticket
    public static String ticket;
    //ticket的失效时间
    public static Long expiresTime_1 = 0L;


    public static HttpResponse getOauth2(String code, String head) {
        String wxUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxConfig.APPID + "&secret="
                + WxConfig.SECRET + "&code=" + code + "&grant_type=authorization_code";
        //logger.info("微信登录请求微信服务器:url={}", wxUrl);
        if (StringUtils.isNotEmpty(head)) {
            if ("老子是H5".equals(head)) {
                wxUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx42ac1f22ae0225f3&secret=6382ef530107642121fa2743b110aaa4&code=" + code + "&grant_type=authorization_code";
            } else if ("老子是小程序".equals(head)) {
                wxUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=wxb3f7e984afdab336&secret=9f6de9dccf211877422f20c0a6b55204&js_code=" + code + "&grant_type=authorization_code";
            } else if ("xiaoyaojing".equals(head)) {
                wxUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx956f01bcced1c6aa&secret=0b50759f7ad1e4a47eac349813d9e0a0&code=" + code + "&grant_type=authorization_code";
            }
        }
        //logger.info("微信登录请求微信服务器:url={}", wxUrl);
        URI url = URI.create(wxUrl);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;


    }

    /**
     * 获得  JSONObject 对象信息 accessToken unionid
     *
     * @return
     */
    public static String getOauthInfo(String code, String head) {
        //根据code请求用户openid unionid
        HttpResponse response = null;
        try {
            response = getOauth2(code, head);
            //响应参数
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("请求微信服务器响应结果(200表示成功):{}", statusCode);
            //请求微信服务器响应成功
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                    sb.append(temp);
                }
                //JSONObject object = JSONObject.fromObject(sb.toString().trim());
                logger.info("微信登录返回的json:{}", sb);
                return sb.toString().trim();
            }
        } catch (Exception e) {
            logger.error("微信登录异常:获取openid失败");
            // e.printStackTrace();
            return null;
        }
        return null;
    }

    public static HttpResponse getSns(String accessToken, String openId) {
        String getUserInfoUri = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken
                + "&openid=" + openId;
        //logger.info("获取微信用户信息Url:{}", getUserInfoUri);
        HttpGet getUserInfo = new HttpGet(URI.create(getUserInfoUri));
        URI url = URI.create(getUserInfoUri);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /***
     * 模拟get请求
     * @param url
     * @param charset
     * @param timeout
     * @return
     */
    public static String sendGet(String url, String charset, int timeout) {
        String result = "";
        try {
            URL u = new URL(url);
            try {
                URLConnection conn = u.openConnection();
                conn.connect();
                conn.setConnectTimeout(timeout);
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
                String line = "";
                while ((line = in.readLine()) != null) {

                    result = result + line;
                }
                in.close();
            } catch (IOException e) {
                return result;
            }
        } catch (MalformedURLException e) {
            return result;
        }

        return result;
    }


    /**
     * 获取AccessToken
     *
     * @return
     */
    public static String getAccessToken() {
        String result = null;
        try {
            //如果accessToken为null或者accessToken已经失效就去重新获取(提前10秒)
            if (System.currentTimeMillis() >= expiresTime) {
                //发送http请求
                result = HttpUtil.get(GET_ACCESSTOKEN_URL.replace("APPID", APPID).replace("APPSECRET", SECRET));
                //转成json对象
                JSONObject json = JSONObject.fromObject(result);
                if (!json.has("access_token")) {
                    return json.toString();
                }
                accessToken = json.getString("access_token");
                Integer expires_in = json.getInt("expires_in");
                //失效时间=当前时间(毫秒)+7200
                expiresTime = System.currentTimeMillis() + ((expires_in - 60) * 1000);
            }
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public static String getJSApiTicket() {
        String result = null;
        try {
            if (System.currentTimeMillis() >= expiresTime_1) {
                //发送http请求
                result = HttpUtil.get(GET_TICKET_URL.replace("ACCESS_TOKEN", getAccessToken()));
                //转成json对象
                JSONObject json = JSONObject.fromObject(result);
                if (!json.has("ticket")) {
                    return json.toString();
                }
                ticket = json.getString("ticket");
                Integer expires_in = json.getInt("expires_in");
                //失效时间=当前时间(毫秒)+7200
                expiresTime_1 = System.currentTimeMillis() + ((expires_in - 60) * 1000);
            }
            return ticket;
        } catch (Exception e) {
            return result;
        }
    }
}
