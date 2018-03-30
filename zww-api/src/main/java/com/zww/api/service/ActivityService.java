package com.zww.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.zww.api.hystrix.ActivityServiceHystrix;
import com.zww.common.ResultMap;

/**
 * 活动微服务
 * @author Administrator
 *
 */
@FeignClient(value = "activity-service", fallback = ActivityServiceHystrix.class)
public interface ActivityService {
	ResultMap selectscrollingBanner();
}
