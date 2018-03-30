package com.zww.api.hystrix;

import org.springframework.stereotype.Component;

import com.zww.api.service.ActivityService;
import com.zww.common.ResultMap;

/**
 * 熔断异常 提示
 * @author Administrator
 *
 */
@Component
public class ActivityServiceHystrix implements ActivityService {

	@Override
	public ResultMap selectscrollingBanner() {
		return null;
	}

}
