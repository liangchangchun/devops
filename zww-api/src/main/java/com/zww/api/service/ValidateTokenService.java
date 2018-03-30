package com.zww.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.hystrix.ValidateTokenServiceHystrix;
import com.zww.api.model.MemberToken;

/**
 * Author: lgq Version: 1.1 Date: 2018/03/26 Description: 验证token. Copyright
 */
@FeignClient(value = "base-service" , fallback = ValidateTokenServiceHystrix.class)
public interface ValidateTokenService {

    /**
     * 轻验证token
     */
	@RequestMapping(value = "/validataToken" ,method = RequestMethod.POST)
    boolean validataToken(@RequestParam(value = "token") String token);

	@RequestMapping(value = "/validataToken2" ,method = RequestMethod.POST)
    boolean validataToken(@RequestParam(value = "token") String token,@RequestParam(value = "memberId") Integer memberId);

	@RequestMapping(value = "/selectByMemberId" ,method = RequestMethod.POST)
    MemberToken selectByMemberId(@RequestParam(value = "memberId") Integer memberId);

	@RequestMapping(value = "/selectByMemberId" ,method = RequestMethod.POST)
	Integer updateToken(@RequestParam(value = "mtoken") MemberToken mtoken);

}
