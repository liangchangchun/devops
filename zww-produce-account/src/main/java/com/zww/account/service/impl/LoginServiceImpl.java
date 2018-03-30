package com.zww.account.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang.ArrayUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.redis.RedisService;
import com.zww.account.dao.MemberDao;
import com.zww.account.service.LoginService;
import com.zww.account.service.MemberService;
import com.zww.api.model.InitializeHeads;
import com.zww.api.model.Member;
import com.zww.api.model.MemberInfo;
import com.zww.api.model.MemberToken;
import com.zww.api.model.PrefSet;
import com.zww.api.model.SystemPref;
import com.zww.api.service.ChargeService;
import com.zww.api.service.RiskManagementService;
import com.zww.api.service.SystemPrefService;
import com.zww.api.util.HttpClientUtil;
import com.zww.api.util.IcraneResult;
import com.zww.api.util.StringUtils;
import com.zww.api.util.TimeUtil;
import com.zww.api.util.WXUtil;
import com.zww.common.Enviroment;
import com.zww.common.RedisKeyGenerator;
import com.zww.common.ResultMap;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SUN on 2017/12/27.
 */
@Service("LoginService")
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Value("${aliyun.smsModelCode.reg}")
    private String reg;
	
	@Value("${qcloudsms.AppID}")
    private String appID;
	
	 @Value("${qcloudsms.AppKEY}")
	 private String appKEY;
	 
	 @Value("${qcloudsms.nationCode}")
	 private String nationCode;
	 
	 @Value("${qcloudsms.templId}")
	 private String templId;

    //private static final Logger logger = LoggerFactory.getLogger(WxPayController.class);
    @Autowired
    private MemberService memberService;
    @Autowired
    private SystemPrefService systemPrefService;
    @Autowired
    private RiskManagementService riskManagementService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ChargeService chargeService;
    @Autowired
    private RedisService redisUtil;
    @Autowired
    private AliyunService aliyunService;
    @Autowired
    private MemberDao memberDao;

    @Override
    public IcraneResult wxLogin(Member member, String lastLoginFrom, String channel, String phoneModel) {
        //账号封禁检测
        if (member.isActiveFlg() == false) {
            //logger.info("微信登录异常:账号已禁用");
            return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE,
                    Enviroment.ACCOUNT_DISABLED);
        }
        redisUtil.addHashSet(RedisKeyGenerator.getMemberInfoKey(member.getId()), "name", member.getName());
        redisUtil.addHashSet(RedisKeyGenerator.getMemberInfoKey(member.getId()), "gender", member.getGender());
        if (member.getIconRealPath() == null) {
            member.setIconRealPath("");
        }
        redisUtil.addHashSet(RedisKeyGenerator.getMemberInfoKey(member.getId()), "iconRealPath", member.getIconRealPath());
        redisUtil.addHashSet(RedisKeyGenerator.getMemberInfoKey(member.getId()), "memberID", member.getMemberID());
        member.setOnlineFlg(true);
        if (lastLoginFrom != null) {
            member.setLastLoginFrom(lastLoginFrom);
        }
        String token = StringUtils.getWxToken();
        //销毁老token
        memberService.destructionToken(member.getId());
        //redis 缓存设置
        redisUtil.setString(token, String.valueOf(member.getId()), 3600 * 24L);
        redisUtil.setString(String.valueOf(member.getId()), token, 3600 * 24L);
        MemberToken mtoken = new MemberToken();
        mtoken.setToken(token);
        mtoken.setMemberId(member.getId());
        member.setLoginChannel(channel);
        member.setPhoneModel(phoneModel);
        // 更新token
        Integer result = memberService.updateMember(member, mtoken);
        //logger.info("已存在用户微信登录更新token结果:{}", result > 0 ? "success" : "fail");
        if (result == 0) {
            return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE,
                    Enviroment.RETURN_FAILE_MESSAGE);
        }
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMember(member);
        memberInfo.setToken(token);
        return IcraneResult.ok(memberInfo);


    }

    @Override
    public Member wxRegistered(String openId, String channel, String phoneModel, String accessToken, String lastLoginFrom, String unionId) throws IOException, NoSuchAlgorithmException {
        try {
            //新建一个用户对象
            Member member = new Member();
            //新建一个用户参数对象
            PrefSet prefSet = new PrefSet();
            prefSet.setMusicFlg(1);
            prefSet.setSoundFlg(1);
            member.setPrefset(prefSet);
            //设置用户openId
            member.setWeixinId(openId);
            member.setLastLoginDate(TimeUtil.getTime());
            member.setRegisterDate(TimeUtil.getTime());
            member.setOnlineFlg(true);
            //设置渠道
            member.setRegisterChannel(channel);
            //设置手机
            member.setPhoneModel(phoneModel);
            //设置MemberID
            member.setMemberID(getMemberCode());
            //设置密码
            member.setPassword(StringUtils.EncoderByMd5(new Random().nextInt(999999) + ""));
            //设置环信
            /*RegisterUsers users = new RegisterUsers();
            User user = new User().username(member.getMemberID()).password(member.getPassword());
            users.add(user);
            EasemobIMUsersController easemobIMU = new EasemobIMUsersController();
            Object oResult = easemobIMU.createNewIMUserSingle(users);
            JSONObject json = JSONObject.fromObject(oResult);
            logger.info("登录环信返回的结果:{}", json);
            try {
                if (json != null) {
                    JSONArray jsonArray = (JSONArray) json.get("entities");
                    JSONObject getJson = jsonArray.getJSONObject(0);
                    String uuid = getJson.getString("uuid");
                    member.setEasemobUuid(uuid);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            // 获得用户微信信息
            HttpResponse response = WXUtil.getSns(accessToken, openId);

            //logger.info("请求微信服务器响应结果(200表示成功):{}", response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader readerInfo = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                StringBuilder builder = new StringBuilder();
                for (String temp = readerInfo.readLine(); temp != null; temp = readerInfo.readLine()) {
                    builder.append(temp);
                }
                JSONObject oj = JSONObject.fromObject(builder.toString().trim());
                //logger.info("获取微信用户信息返回结果:{}", oj);
                String nikeName = oj.getString("nickname");
                String gender = oj.getString("sex");
                String headimgurl = oj.getString("headimgurl");
                member.setName(nikeName);
                member.setIconRealPath(headimgurl);
                if (gender.equals("1")) {
                    member.setGender("m");
                } else if (gender.equals("2")) {
                    member.setGender("f");
                } else {
                    member.setGender("n");
                }
            }
            //设置初始娃娃币
            //member.setCoins(0);
            member.setModifiedBy(member.getId());
            member.setModifiedDate(TimeUtil.getTime());
            member.setCatchNumber(0);
            member.setActiveFlg(true);
            member.setInviteFlg(false);
            member.setFirstCharge(0);
            member.setFirstLogin(0);
            member.setLastLoginFrom(lastLoginFrom);
            member.setRegisterFrom(lastLoginFrom);

            //储存用户信息
            int result = memberDao.insertMemberBywx(member);
            //储存渠道信息
            int result2 = memberDao.insertChannel(member.getId(), channel, null, null);
            //储存微信信息
            //memberDao.insertUnionId(member.getId(), openId, unionId);
            //logger.info("插入新用户结果Result{}:{}", result, result > 0 ? "success" : "fail");
            //注册初始化娃娃币
            int id = 0;
            id = memberService.insertMemberBywx(member);
            //logger.info("微信新用户登录结果:{}", id > 0 ? "success" : "fail");
            if (id == 0) {
                return null;

            }
            String token = StringUtils.getWxToken();

            MemberToken mtoken = new MemberToken();
            mtoken.setToken(token);
            mtoken.setMemberId(member.getId());
            int insertToken = memberService.insertToken(mtoken);
            if (insertToken == 0) {
                return null;
            }
            //redis 缓存设置
            redisUtil.setString(token, String.valueOf(member.getId()), 3600 * 24L);
            //首次签到显示用
            SystemPref systemPref = systemPrefService.selectByPrimaryKey("NEW_BONUS");
            member.setCoins(Integer.parseInt(systemPref.getValue()));
            //关联unionId信息
            memberService.insertUnionId(member.getId(), openId, unionId);
            return member;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public class MyThread extends Thread {
        public void run() {
            System.out.println("MyThread running");
        }
    }


    /**
     * 短信验证码快速登录服务
     *
     * @param mobile     手机号码
     * @param smsCode    短信验证码
     * @param from       登录类型
     * @param channel    渠道信息
     * @param phoneModel 手机型号
     * @return
     */
    @Override
    public ResultMap smsCodeLogin(String mobile, String smsCode, String from, String channel, String phoneModel, String IMEI, String IP) {
        //验证短信验证码是否正确
        String codeLoginKey = RedisKeyGenerator.getCodeLoginKey(mobile);
        String trueCode = redisUtil.getString(codeLoginKey);
        String[] superusers = memberService.getSuperUsers();
        if (!ArrayUtils.contains(superusers, mobile) && trueCode == null) {
            //logger.info("短信验证码登录失败:" + Enviroment.SMSCODE_IS_OVER);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.SMSCODE_IS_OVER);
        }
        if (!ArrayUtils.contains(superusers, mobile) && !trueCode.equals(smsCode)) {
            //logger.info("短信验证码登录失败:" + Enviroment.SMSCODE_IS_FALSE);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.SMSCODE_IS_FALSE);
        }
        //验证手机是否存在
        Member member = memberService.selectByMobile(mobile);
        //如果不存在就注册（取消）
        if (member == null) {
            if (ArrayUtils.contains(superusers, mobile)) {
                InitializeHeads initializeHeads = memberService.selectInitializeHeads();
                member = new Member();
                PrefSet ps = new PrefSet();
                member.setName(initializeHeads.getNikeName());
                member.setIconRealPath(initializeHeads.getHeadimgurl());
                member.setMobile(mobile);
                member.setPassword(smsCode);
                member.setGender("f");
                String memberID = StringUtils.getMemberCode();
                while (memberService.selectByMemberID(memberID) != null) {
                    memberID = StringUtils.getMemberCode();
                }
                member.setMemberID(memberID);
                if (from != null) {
                    member.setRegisterFrom(from);
                }
                if (channel != null) {
                    member.setRegisterChannel(channel);
                }
                member.setPhoneModel(phoneModel);
                member.setCoins(888888);
                member.setFirstCharge(0);
                member.setFirstLogin(0);
                member.setActiveFlg(true);

                //设置抓取次数
                member.setCatchNumber(0);
                memberService.insertRegister(member, ps);
                //储存渠道信息
                int result2 = memberDao.insertChannel(member.getId(), channel, null, null);
                //首次签到显示用
                SystemPref systemPref = systemPrefService.selectByPrimaryKey("NEW_BONUS");
                member.setCoins(Integer.parseInt(systemPref.getValue()));
            } else {
                //除了超级用户都不让注册
                //logger.info("短信验证码登录失败:手机号未注册");
                return new ResultMap(Enviroment.FAILE_CODE, Enviroment.TO_WX);
            }
        }
        //账号封禁检测
        if (member.isActiveFlg() == false) {
            //logger.info("短信验证码登录失败:账号已禁用");
            return new ResultMap(Enviroment.RETURN_FAILE_CODE, Enviroment.ACCOUNT_DISABLED);
        }
        //logger.info("用户信息存入redis入参getMemberInfoKey:{},name:{},gender:{},iconRealPath:{}",RedisKeyGenerator.getMemberInfoKey(member.getId()), member.getName(), member.getGender(), member.getIconRealPath());
        if (member.getIconRealPath() == null) {
            member.setIconRealPath("");
        }
        if (member.getName() == null) {
            member.setName(mobile);
        }
        redisUtil.addHashSet(RedisKeyGenerator.getMemberInfoKey(member.getId()), "name", member.getName());
        redisUtil.addHashSet(RedisKeyGenerator.getMemberInfoKey(member.getId()), "gender", member.getGender());
        redisUtil.addHashSet(RedisKeyGenerator.getMemberInfoKey(member.getId()), "iconRealPath", member.getIconRealPath());
        redisUtil.addHashSet(RedisKeyGenerator.getMemberInfoKey(member.getId()), "memberID", member.getMemberID());
        member.setOnlineFlg(true);
        if (from != null) {
            member.setLastLoginFrom(from);
        }
        String token = StringUtils.getPhoneToken();
        //销毁老tokne
        memberService.destructionToken(member.getId());
        //保存风控信息
        if (IMEI != null && IMEI.length() > 40) {
            return new ResultMap(Enviroment.ERROR_CODE, Enviroment.IMEI_TO_LONG);
        }
        int register = riskManagementService.register(member.getId(), IMEI, IP);
        if (register != 1) {
            return new ResultMap(Enviroment.ERROR_CODE, Enviroment.RISK_CONTROL_ABNORMAL);
        }
        //redis 缓存设置
        redisUtil.setString(token, String.valueOf(member.getId()), 3600 * 24L);
        redisUtil.setString(String.valueOf(member.getId()), token, 3600 * 24L);
        MemberToken mtoken = new MemberToken();
        mtoken.setToken(token);
        mtoken.setMemberId(member.getId());
        member.setLoginChannel(channel);
        member.setPhoneModel(phoneModel);
        // 更新token
        Integer result = memberService.updateMember(member, mtoken);
        //logger.info("已存在用户微信登录更新token结果:{}", result > 0 ? "success" : "fail");
        if (result == 0) {
            //logger.info("短信验证码登录失败,MemberID=" + member.getId());
            return new ResultMap(Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_FAILE_MESSAGE);
        }
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMember(member);
        memberInfo.setToken(token);
        redisUtil.delKey(codeLoginKey);
        //logger.info("短信验证码登录成功,MemberID=" + member.getId());
        return new ResultMap(Enviroment.LOGIN_SUCCESS_MESSAGE, memberInfo);
    }

    /**
     * 发送快速登录验证码短信
     *
     * @param mobile 手机号码
     * @return
     */
    @Override
    public ResultMap getSmsCodeLogin(String mobile) {
        //限制17开头的手机号码登录
        if ("17".equals(mobile.substring(0, 2))) {
            //logger.info("发送快速登录验证码短信失败：17开头不支持");
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE1, Enviroment.BZC);
        }
        if (memberService.selectByMobile(mobile) == null) {
            //logger.info("发送快速登录验证码短信失败：该手机号未注册");
            return new ResultMap(Enviroment.FAILE_CODE, Enviroment.TO_WX);
        }
        // 生成短信验证码
        String smsCode = StringUtils.getSmsCode();
        //获取配置文件
       // PropFileManager propFileMgr = new PropFileManager("interface.properties");
        try {
            // 发送短信
            if (aliyunService.sendSMSForCode(mobile, "365抓娃娃",
            		reg, smsCode)) {
                //验证码信息存入redis
                redisUtil.setString(RedisKeyGenerator.getCodeLoginKey(mobile), smsCode, Enviroment.SMS_ENDTIME * 1L);
                //logger.info("阿里云发送快速登录验证码短信成功=" + Enviroment.TEXT_MESSAGING_SUCCESS);
                return new ResultMap(Enviroment.TEXT_MESSAGING_SUCCESS);
            } else {
                SmsSingleSender sender = new SmsSingleSender(Integer.valueOf(appID), appKEY);
                ArrayList<String> params = new ArrayList<String>();
                params.add(smsCode);
                params.add("5");
                SmsSingleSenderResult result = sender.sendWithParam(nationCode, mobile, Integer.valueOf(templId), params, "", "", "");
                if ("OK".equals(result.errMsg)) {
                    redisUtil.setString(RedisKeyGenerator.getCodeLoginKey(mobile), smsCode, Enviroment.SMS_ENDTIME * 1L);
                    //logger.info("腾讯云发送快速登录验证码短信成功=" + Enviroment.TEXT_MESSAGING_SUCCESS);
                    return new ResultMap(Enviroment.TEXT_MESSAGING_SUCCESS);
                } else {
                    //logger.info("发送快速登录验证码短信异常=腾讯云:" + result.errMsg);
                    return new ResultMap(Enviroment.RETURN_FAILE_CODE, Enviroment.TEXT_MESSAGING_FAILURE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMap(Enviroment.RETURN_FAILE_CODE, Enviroment.TEXT_MESSAGING_FAILURE);
        }
    }

    /**
     * 登出服务
     *
     * @param memberId 用户userID
     * @param token    用户token
     */
    @Override
    public ResultMap logout(Integer memberId
                            // , String token
    ) {
        // Member member = new Member();
        // MemberToken mtoken = new MemberToken();
        try {
            // 验证token有效性
            // if (!validateTokenService.validataToken(token, memberId)) {
            //     logger.info("用户登出失败:memberId=" + memberId + Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
            //     return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
            // }
            memberService.destructionToken(memberId);
            memberService.destructionTokenByRedis(memberId);
            // member.setLastLoginDate(TimeUtil.getTime());
            // member.setOnlineFlg(false);
            // member.setId(memberId);
            // mtoken.setToken(token);
            // redisUtil.delKey(RedisKeyGenerator.getMemberInfoKey(member.getId()));
            // redisUtil.delKey(token);
            // logger.info("登出接口参数" + "member=" + member + "mtoken=" + mtoken);
            // memberService.logoff(member, mtoken);
            memberService.logoff(memberId);
            // logger.info("登出结果result" + result);
            //logger.info("登出结果resultMap" + Enviroment.LOGIN_OUT_SUCCESS);
            return new ResultMap(Enviroment.LOGIN_OUT_SUCCESS);
        } catch (Exception e) {
            //logger.error("登出出错", e);
            throw e;
        }
    }

    @Override
    public ResultMap weChatLogin(HttpServletRequest request, String code, String memberId, String lastLoginFrom, String IMEI, String phoneModel, String channel) {
        try {
            String ipAdrress = HttpClientUtil.getIpAdrress(request);
            //logger.info("多级渠道注册 code=" + code + ",IP=" + ipAdrress + ",memberId=" + memberId + ",lastLoginFrom=" + lastLoginFrom + ",channel=" + channel);
            //获取渠道信息
            Member inviter = memberService.selectByMemberID(memberId);
            if (inviter != null) {
                channel = inviter.getRegisterChannel();
            }

            Member member = null;

            //根据redis中的缓存判断是否注册是否要重新登录
            String openIdUnionid = redisUtil.getString(code);
            if (StringUtils.isEmpty(openIdUnionid)) {
                //新用户或者未登录H5的APP用户(redis中不存在,那么表示code是新的)
                String result = WXUtil.getOauthInfo(code, "老子是H5");
                JSONObject object = JSONObject.fromObject(result);
                if (object.has("access_token")) {
                    String accessToken = object.getString("access_token");
                    String unionId = object.getString("unionid");
                    String wopenid = object.getString("openid");
                    //检查add表如果没有就存入
                    if (StringUtils.isEmpty(memberService.selectGzhopenIdByUnionId(unionId))) {
                        memberService.insertmember_add(wopenid, unionId);
                    }
                    redisUtil.setString(code, wopenid + unionId, 2592000L);
                    //unionId登录兼容问题
                    String openId = memberService.selectOpenIdByUnionId(unionId);
                    if (StringUtils.isEmpty(openId)) {
                        openId = wopenid;
                    }
                    member = memberService.selectByOpenId(openId);
                    //根据openId获取登录信息
                    if (member == null) {
                        //新用户先注册
                        member = loginService.wxRegistered(openId, channel, null, accessToken, lastLoginFrom, unionId);
                        if (member == null) {
                            IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE, Enviroment.REGISTRATION_FAILED);
                        }
                        if (StringUtils.isEmpty(memberId)) {
                            int i = chargeService.inviteOne(member.getId(), channel);
                            if (i == 2) {
                                IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE, "链接错误");
                            }
                        } else {
                            //绑定邀请
                            ResultMap resultMap = chargeService.invite(member.getId(), memberId);
                        }
                        member = memberService.selectById(member.getId());
                    } else {
                        //APP老客户
                        //检查是否绑定如果没有绑定就去绑定
                        if (!member.isInviteFlgWeb()) {
                            chargeService.invite(member.getId(), memberId);
                        }
                    }
                }
            } else {
                //使用openIdUnionid登录
                member = memberService.selectByOpenId(openIdUnionid.substring(openIdUnionid.indexOf("+"), openIdUnionid.length()));
            }

            //老用户直接登录
            //登录前记录IMEI和IP]
            if (IMEI != null && IMEI.length() > 40) {
                return new ResultMap(Enviroment.ERROR_CODE, Enviroment.IMEI_TO_LONG);
            }
            //风控信息
            int register = riskManagementService.register(member.getId(), IMEI, HttpClientUtil.getIpAdrress(request));
            if (register != 1) {
                return new ResultMap(Enviroment.ERROR_CODE, Enviroment.RISK_CONTROL_ABNORMAL);
            }
            IcraneResult icraneResult = loginService.wxLogin(member, lastLoginFrom, channel, phoneModel);
            return new ResultMap(icraneResult.getMessage(), icraneResult.getResultData());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成不重复的MemberId
     *
     * @return
     */
    public String getMemberCode() {
        String memberID = StringUtils.getMemberCode();
        while (memberService.selectByMemberID(memberID) != null) {
            memberID = StringUtils.getMemberCode();
        }
        return memberID;
    }
}
