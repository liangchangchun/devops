package com.zww.account.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.redis.RedisService;
import com.zww.account.dao.MemberDao;
import com.zww.account.dao.ValidateTokenDao;
import com.zww.account.service.MemberService;
import com.zww.api.model.*;
import com.zww.api.service.ChargeService;
import com.zww.api.service.SystemPrefService;
import com.zww.api.util.*;
import com.zww.common.Enviroment;
import com.zww.common.RedisKeyGenerator;
import com.zww.common.ResultMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import static com.zww.api.util.InitializeHeadsUtil.getRandom;

/**
 * @author lgq Version: 1.0 Date: 2017年9月19日date Description: 用户Service接口实现类.
 *         Copyright (c) 2017 伴飞网络. All rights reserved.
 */
@Service("MemberService")
public class MemberServiceImpl implements MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private ValidateTokenDao validateTokenDao;
    //@Autowired
   // private SystemPrefDao systemPrefDao;
    @Autowired
    private SystemPrefService systemPrefService;
   // @Autowired
   // private ChargeDao chargeDao;
    @Autowired
    private ChargeService chargeService;

    @Autowired
    private RedisService redisUtil;
    @Autowired
    private AliyunService aliyunService;
    
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

    public Member login(String mobile, String password) {
        logger.info("login 参数mobile:{},password:{}", mobile, password);
        return memberDao.login(mobile, password);
    }

    @Transactional
    public Integer updateLogin(Member member, MemberToken mtoken, String lastLoginFrom) {
        logger.info("updateLogin 参数member:{},mtoken:{}", member, mtoken);
        MemberToken token = validateTokenDao.selectByMemberId(member.getId());
        logger.info("token:{}", token);
        if (token == null) {
            memberDao.insertToken(mtoken);
        } else {
            validateTokenDao.updateToken(mtoken);
        }
        //更新token 同步redis
        redisUtil.setString(mtoken.getToken(), String.valueOf(member.getId()), 3600 * 24L);
        //SystemPref systemPref = systemPrefDao.selectByPrimaryKey("FIRST_LOGIN_COINS");
        SystemPref newBouns = systemPrefService.selectByPrimaryKey("NEW_BONUS");
        if (member.getLastLoginDate() == null) {
            member.setRReward(newBouns.getValue());
        }
        /*
        if (member.getLastLoginDate() != null && member.getLastLoginDate().before(TimeUtil.getDayBeginTimestamp())) {
			member.setCoins(member.getCoins() + Integer.parseInt(systemPref.getValue()));
			Charge charge = new Charge();
			charge.setMemberId(member.getId());
			charge.setCoins(Integer.parseInt(systemPref.getValue()));
			charge.setChargeMethod("签到奖励");
			charge.setType("income");
			charge.setChargeDate(TimeUtil.getTime());
			chargeDao.insertChargeHistory(charge);
			member.setlReward(systemPref.getValue());
		}*/
        if (lastLoginFrom != null) {
            member.setLastLoginFrom(lastLoginFrom);
        }
        member.setLastLoginDate(TimeUtil.getTime());
        return memberDao.updateLogin(member);

    }

    @Transactional
    public String generateToken(int memberId) {
        logger.info("generateToken 参数memberId:{}", memberId);
        validateTokenDao.deleteByMemberId(memberId);
        MemberToken mtoken = new MemberToken();
        String token = StringUtils.getRandomUUID();
        mtoken.setToken(token);
        mtoken.setMemberId(memberId);
        memberDao.insertToken(mtoken);
        //更新token 同步redis
        redisUtil.setString(mtoken.getToken(), String.valueOf(memberId), 3600 * 24L);
        return token;
    }

    public Integer logoff(Member member, MemberToken mtoken) {
        memberDao.updateLogoff(member);
        return memberDao.deleteToken(mtoken);
    }

    public void logoff(Integer memberId) {
        memberDao.updateLogoffBymemberId(memberId);
    }

    public ResultMap linkMobile(Integer memberId, String mobile, String smsCode) {
        //检测是否已经绑定
        if (StringUtils.isNotEmpty(selectById(memberId).getMobile())) {
            logger.info("绑定手机失败:" + Enviroment.LINK_NO_SUCCESS2);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.LINK_NO_SUCCESS2);
        }
        //正确验证码
        String trueCode = redisUtil.getString(RedisKeyGenerator.getLinkMobileCodeKey(mobile));
        if (trueCode == null) {
            logger.info("绑定手机失败:" + Enviroment.SMSCODE_IS_OVER);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.SMSCODE_IS_OVER);
        }
        if (!trueCode.equals(smsCode)) {
            logger.info("绑定手机失败:" + Enviroment.SMSCODE_IS_FALSE);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.SMSCODE_IS_FALSE);
        }
        Member member = new Member();
        member.setId(memberId);
        member.setMobile(mobile);
        Integer result = updateByPrimaryKeySelective(member);
        logger.info("绑定手机result=" + result);
        if (result != 0) {
            logger.info("绑定手机成功:" + Enviroment.LINK_SUCCESS);
            return new ResultMap(Enviroment.LINK_SUCCESS);
        } else {
            logger.info("绑定手机失败:" + Enviroment.LINK_NO_SUCCESS);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.LINK_NO_SUCCESS);
        }
    }

    @Transactional
    public int insertMemberBywx(Member mb) {
        logger.info("insertMemberBywx 参数mb:{}", mb);

        Charge charge = new Charge();
        charge.setCode("NEW_BONUS");
        SystemPref systemPref = systemPrefService.selectByPrimaryKey("NEW_BONUS");
        charge.setCoins(0);
        charge.setCoinsSum(Integer.parseInt(systemPref.getValue()));
        charge.setChargeMethod("新注册用户的奖励");
        charge.setMemberId(mb.getId());
        charge.setType("income");
        charge.setChargeDate(TimeUtil.getTime());
        //logger.info("charge:{}", charge.toString());
        Integer updateMemberCount = chargeService.updateMemberCount(charge);
        //logger.info("updateMemberCount：{}", updateMemberCount > 0 ? "success" : "fail");
        Integer insertChargeHistory = chargeService.insertChargeHistory(charge);
        //logger.info("insertChargeHistory：{}", insertChargeHistory > 0 ? "success" : "fail");
        PrefSet ps = new PrefSet();
        ps.setMemberId(mb.getId());
        ps.setMusicFlg(0);
        ps.setSoundFlg(0);
        Integer insertPref = memberDao.insertPref(ps);
        logger.info("insertPref：{}", insertPref > 0 ? "success" : "fail");
        return insertPref;
    }

    @Transactional
    public Integer insertRegister(Member member, PrefSet ps) {
        logger.info("insertRegister 参数member:{},ps:{}", member, ps);
        RegisterUsers users = new RegisterUsers();
        User user = new User().username(member.getMemberID()).password(member.getPassword());
        users.add(user);
        //环信
        /*EasemobIMUsersController easemobIMU = new EasemobIMUsersController();
        Object result = easemobIMU.createNewIMUserSingle(users);
        JSONObject json = JSONObject.fromObject(result);
        JSONArray jsonArray = (JSONArray) json.get("entities");
        JSONObject getJson = jsonArray.getJSONObject(0);
        String uuid = getJson.getString("uuid");
        if (uuid != null) {
            member.setEasemobUuid(uuid);
        }*/
        member.setRegisterDate(TimeUtil.getTime());
        memberDao.insertRegister(member);
        // Member mber = memberDao.selectById(member.getId());
        Member getMemb = memberDao.selectByMobile(member.getMobile());
        logger.info("getMemb:{}", getMemb);
        if (getMemb.getCoins() == 0) {
            Charge charge = new Charge();
            charge.setCode("NEW_BONUS");
            SystemPref systemPref = systemPrefService.selectByPrimaryKey(charge.getCode());
            charge.setCoins(0);
            charge.setCoinsSum(Integer.parseInt(systemPref.getValue()));
            charge.setChargeMethod("新注册用户的奖励");
            charge.setMemberId(getMemb.getId());
            charge.setType("income");
            charge.setChargeDate(TimeUtil.getTime());
            chargeService.updateMemberCount(charge);
            chargeService.insertChargeHistory(charge);
        }
        ps.setMemberId(getMemb.getId());
        ps.setMusicFlg(1);
        ps.setSoundFlg(1);
        ps.setShockFlg(1);
        memberDao.insertPref(ps);
        return 1;
    }

    public Member selectByMobile(String mobile) {
        //logger.info("selectByMobile 参数mobile:{}", mobile);
        return memberDao.selectByMobile(mobile);
    }

    public Member selectByMemberID(String memberID) {
        //logger.info("selectByMemberID 参数memberID:{}", memberID);
        return memberDao.selectByMemberID(memberID);
    }

    public Integer insertSmsCode(MemberSmscode member) {
        // TODO Auto-generated method stub
        //logger.info("insertSmsCode 参数member:{}", member);
        return memberDao.insertSmsCode(member);
    }

    public MemberSmscode validateSmsCodeByCode(String smscode) {
        // TODO Auto-generated method stub
        //logger.info("validateSmsCodeByCode 参数smscode:{}", smscode);
        return memberDao.validateSmsCodeByCode(smscode);
    }

    public Integer deleteSmscode(String mobile) {
        // TODO Auto-generated method stub
        //logger.info("deleteSmscode 参数mobile:{}", mobile);
        return memberDao.deleteSmscode(mobile);
    }

    public MemberSmscode validateSmsCodeByMobile(String mobile) {
        // TODO Auto-generated method stub
        //logger.info("validateSmsCodeByMobile 参数mobile:{}", mobile);
        return memberDao.validateSmsCodeByMobile(mobile);
    }

    public Member selectById(Integer Id) {
        // TODO Auto-generated method stub
        logger.info("selectById 参数Id:{}", Id);
        return memberDao.selectById(Id);
    }

    @Transactional
    public int updatePwd(Member mb) {
        logger.info("updatePwd 参数mb:{}", mb.toString());
        mb.setModifiedBy(mb.getId());
        mb.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return memberDao.updatePwd(mb);
    }

    @Transactional
    public Integer updateByPrimaryKeySelective(Member member) {
        //
        //memberDao.deleteSmscode(member.getMobile());
        member.setModifiedDate(TimeUtil.getTime());
        member.setModifiedBy(member.getId());
        return memberDao.updateByPrimaryKeySelective(member);
    }

    @Override
    public Member selectByOpenId(String openId) {
        logger.info("selectByOpenId 参数openId:{}", openId);
        try {
            return memberDao.selectByOpenId(openId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public int updateToken(MemberToken mt) {
        logger.info("updateToken 参数mt:{}", mt);
        return memberDao.updateToken(mt);
    }

    @Transactional
    public Integer updateMember(Member mb, MemberToken mt) {
        logger.info("updateMember mt:{}", mt);
        try {
            if (memberDao.selectToken(mt) == null) {
                memberDao.insertToken(mt);
            } else {
                memberDao.updateToken(mt);
            }
            mb.setLastLoginDate(TimeUtil.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberDao.updateLogin(mb);
    }


    @Override
    public int insertToken(MemberToken mt) {
        logger.info("insertToken 参数mt:{}", mt);
        return memberDao.insertToken(mt);
    }

    @Override
    public Member getUserInfo(Integer memberId) {
        logger.info("getUserInfo 参数memberId:{}", memberId);
        return memberDao.getUserInfoById(memberId);
    }

    @Override
    public SystemPref getSystemPref(String code) {
        logger.info("getSystemPref 参数code:{}", code);
        return systemPrefService.selectByPrimaryKey(code);
    }

    @Override
    public Member getCodeBymobile(String mobile) {
        return memberDao.getCodeByMobile(mobile);
    }

    @Override
    public void insertUnionId(int userId, String openId, String unionId) {
        memberDao.insertUnionId(userId, openId, unionId);
    }

    @Override
    public void destructionToken(Integer memberId) {
        List<String> tokens = memberDao.selectTokenById(memberId);
        String rtoken = redisUtil.getString(String.valueOf(memberId));
        if (StringUtils.isNotEmpty(rtoken)) {
            tokens.add(rtoken);
        }
        if (tokens != null && tokens.size() > 0) {
            redisUtil.delKey(RedisKeyGenerator.getMemberInfoKey(memberId));
            memberDao.deleteTokenByIdToken(String.valueOf(memberId));
            for (String token : tokens) {
                redisUtil.delKey(token);
            }
        }
        redisUtil.delKey(memberId.toString());
    }

    @Override
    public void destructionTokenByRedis(Integer memberId) {
        String token = redisUtil.getString(String.valueOf(memberId));
        if (StringUtils.isNotEmpty(token)) {
            redisUtil.delKey(token);
            redisUtil.delKey(memberId.toString());
        }
    }

    @Override
    public InitializeHeads selectInitializeHeads() {
        List<InitializeHeads> list = memberDao.selectInitializeHeads();
        InitializeHeads initializeHeads = list.get(getRandom().nextInt(list.size()));
        if (initializeHeads == null) {
            return new InitializeHeads("兔先生", "http://zww-image-dev.oss-cn-shanghai.aliyuncs.com/dolls/1456561438145638.jpg");
        }
        return initializeHeads;
    }

    @Override
    public ResultMap sendLinkMobileSMS(String mobile, String token) {
        try {
            //被绑用户没绑过
            if (selectByMobile(mobile) != null) {
                //logger.info("发送绑定手机验证码失败=" + Enviroment.LINK_NO_SUCCESS2);
                return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.LINK_NO_SUCCESS2);
            }
            //被绑手机没注册过
            Member member = selectByMobile(mobile);
            if (member == null) {
               // PropFileManager propFileMgr = new PropFileManager("interface.properties");
                // 生成短信验证码
                String smsCode = StringUtils.getSmsCode();
                // 获取配置文件
                // 发送短信
                if (aliyunService.sendSMSForCode(mobile, "三六五抓娃娃",
                		reg, smsCode)) {
                    //验证码信息存入redis
                    redisUtil.setString(RedisKeyGenerator.getLinkMobileCodeKey(mobile), smsCode, Enviroment.SMS_ENDTIME * 1L);
                    logger.info("发送绑定手机验证码成功=" + Enviroment.TEXT_MESSAGING_SUCCESS);
                    return new ResultMap(Enviroment.TEXT_MESSAGING_SUCCESS);
                } else {
                    SmsSingleSender sender = new SmsSingleSender(Integer.valueOf(appID), appKEY);
                    ArrayList<String> params = new ArrayList<String>();
                    params.add(smsCode);
                    params.add("5");
                    SmsSingleSenderResult result = sender.sendWithParam(nationCode, mobile, Integer.valueOf(templId), params, "", "", "");
                    if ("OK".equals(result.errMsg)) {
                        redisUtil.setString(RedisKeyGenerator.getLinkMobileCodeKey(mobile), smsCode, Enviroment.SMS_ENDTIME * 1L);
                        logger.info("发送绑定手机验证码成功=" + Enviroment.TEXT_MESSAGING_SUCCESS);
                        return new ResultMap(Enviroment.TEXT_MESSAGING_SUCCESS);
                    } else {
                        logger.info("发送绑定手机验证码失败=" + Enviroment.TEXT_MESSAGING_FAILURE);
                        return new ResultMap(Enviroment.RETURN_FAILE_CODE, Enviroment.TEXT_MESSAGING_FAILURE);
                    }
                }
            } else {
                // 手机号码已重复
                logger.info("发送绑定手机验证码失败=" + Enviroment.LINKMOBILE_ERROR);
                return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.LINKMOBILE_ERROR);
            }
        } catch (Exception e) {
            logger.error("发送绑定手机验证码出错", e);
            e.printStackTrace();
            return new ResultMap(Enviroment.ERROR_CODE, Enviroment.HAVE_ERROR);
        }
    }

    @Override
    public String selectOpenIdByUnionId(String unionId) {
        return memberDao.selectOpenIdByUnionId(unionId);
    }

    @Override
    public String selectGzhopenId(int memberId) {
        return memberDao.selectGzhopenId(memberId);
    }

    @Override
    public String selectGzhopenIdByUnionId(String unionId) {
        return memberDao.selectGzhopenIdByUnionId(unionId);
    }

    @Override
    public Boolean isWorker(Integer memberId) {
        if (memberId == null) {
            return false;
        }
        Boolean worker = memberDao.isWorker(memberId);
        if (worker == null) {
            return false;
        }
        return worker;
    }

    @Override
    public Boolean isVIP(Integer memberId) {
        if (memberId == null) {
            return false;
        }
        Integer payNumber = memberDao.selectPayNumberByMemberId(memberId);
        if (payNumber != null && payNumber > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String[] getSuperUsers() {
        return memberDao.getSuperUsers();
    }

    @Override
    public List<Integer> selectOutTokenUserId() {
        return memberDao.selectOutTokenUserId();
    }

    @Override
    public Integer selectRank(int id) {
        Integer rank = memberDao.selectRank(id);
        if (rank == null) {
            return 0;
        }
        return rank;
    }

    @Override
    public void insertmember_add(String openid, String unionId) {
        memberDao.insertmember_add(openid, unionId);
    }

    @Override
    public String verifyChannel(String channel) {
        List<SystemPref> systemPref = systemPrefService.selectChannel();
        for (SystemPref pref : systemPref) {
            if (pref.getValue().equals(channel)) {
                return pref.getName();
            }
        }
        return null;
    }
}