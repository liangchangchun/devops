package com.zww.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stylefeng.guns.core.redis.RedisService;
import com.zww.api.model.Member;
import com.zww.api.model.MemberToken;
import com.zww.api.service.ValidateTokenService;
import com.zww.base.dao.ValidateTokenDao;

@RefreshScope
@RestController
public class ValidateTokenController implements ValidateTokenService {
	
	@Autowired
	 ValidateTokenDao validateTokenDao;
	
	@Autowired
	RedisService redisUtil;

	@Override
	public boolean validataToken(@RequestParam(value = "token") String token) {
		 if (redisUtil.existsKey(token)) {
	            String memberId = redisUtil.getString(token);
	            redisUtil.setString(token, memberId, 3600 * 24L);
	            return true;
	        }
	        MemberToken member = validateTokenDao.selectByToken(token);
	        if(member!=null) {
	        	redisUtil.setString(token, String.valueOf(member.getMemberId()),3600 * 24L);
	        	return true;
	        }else {
	        	redisUtil.delKey(token);
	        	return false;
	        }
	}

	@Override
	public boolean validataToken(@RequestParam(value = "token") String token,@RequestParam(value = "memberId") Integer memberId) {
		if (redisUtil.existsKey(token)) {
            String vMemberId = redisUtil.getString(token);
            if (vMemberId.equals(String.valueOf(memberId))) {
                redisUtil.setString(token, String.valueOf(memberId), 3600 * 24L);
                return true;
            } else {
                //Member currmember = memberDao.selectById(Integer.parseInt(vMemberId));
            	Member currmember = validateTokenDao.querryMemberById(Integer.parseInt(vMemberId));
            	
                if (currmember != null && currmember.getMemberID().equals(String.valueOf(memberId))) {
                    redisUtil.setString(token, String.valueOf(memberId), 3600 * 24L);
                    return true;
                }
                redisUtil.delKey(token);
                return false;
            }
        }
        MemberToken member = validateTokenDao.selectByToken(token);
        if (member != null && String.valueOf(member.getMemberId()).equals(String.valueOf(memberId))) {
            redisUtil.setString(token, String.valueOf(memberId), 3600 * 24L);
            return true;
        }
        validateTokenDao.deleteByMemberId(memberId);
        return false;
	}

	@Override
	public MemberToken selectByMemberId(@RequestParam(value = "memberId") Integer memberId) {
		return validateTokenDao.selectByMemberId(memberId);
	}

	@Override
	public Integer updateToken(@RequestParam(value = "mtoken") MemberToken mtoken) {
		 redisUtil.setString(mtoken.getToken(), String.valueOf(mtoken.getMemberId()), 3600 * 24L);
	     return validateTokenDao.updateToken(mtoken);
	}

}
