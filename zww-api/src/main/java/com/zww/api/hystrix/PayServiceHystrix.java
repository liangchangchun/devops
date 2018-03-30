package com.zww.api.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.service.PayService;

/**
 * 熔断器 熔断异常
 * @author Administrator
 *
 */
@Component
public class PayServiceHystrix implements PayService {

	@Override
	public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
		return -9999;
	}


}