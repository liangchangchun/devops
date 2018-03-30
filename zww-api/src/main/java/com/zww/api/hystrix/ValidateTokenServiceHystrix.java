package com.zww.api.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.model.MemberToken;
import com.zww.api.service.ValidateTokenService;

@Component
public class ValidateTokenServiceHystrix implements ValidateTokenService {

	@Override
	public boolean validataToken(@RequestParam(value = "token") String token) {
		return false;
	}

	@Override
	public boolean validataToken(@RequestParam(value = "token") String token,@RequestParam(value = "memberId") Integer memberId) {
		return false;
	}

	@Override
	public MemberToken selectByMemberId(@RequestParam(value = "memberId") Integer memberId) {
		return null;
	}

	@Override
	public Integer updateToken(@RequestParam(value = "mtoken") MemberToken mtoken) {
		return -1;
	}

}
