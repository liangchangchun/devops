package com.zww.account.service;

/**
 * Created by SUN on 2017/12/27.
 */


import javax.servlet.http.HttpServletRequest;

import com.zww.api.model.*;
import com.zww.api.util.IcraneResult;
import com.zww.common.ResultMap;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 登录相关服务类
 */
public interface LoginService {

    /**
     * 老用户微信登录
     *
     * @return
     */
    IcraneResult wxLogin(Member member, String lastLoginFrom, String channel, String phoneModel);

    /**
     * 微信新用户注册
     */
    Member wxRegistered(String openId, String channel, String phoneModel, String accessToken, String lastLoginFrom, String unionId) throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException;

    /**
     * 短信验证码快速登录
     *
     * @param mobile     手机号码
     * @param smsCode    短信验证码
     * @param from       手机系统类型
     * @param channel    渠道信息
     * @param phoneModel 手机型号
     * @return
     * @throws Exception 登录结果
     */
    ResultMap smsCodeLogin(String mobile, String smsCode, String from, String channel, String phoneModel, String IMEI, String IP);

    /**
     * 发送快速登录验证码短信
     *
     * @param mobile 手机号码
     * @return 验证码发送结果
     */
    ResultMap getSmsCodeLogin(String mobile);

    /**
     * 登出服务
     *
     * @param memberId 用户userID
     */
    ResultMap logout(Integer memberId
                     // , String token
    );

    ResultMap weChatLogin(HttpServletRequest request, String code, String memberId, String lastLoginFrom, String IMEI, String phoneModel, String channel);

}
