package com.zww.api.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.model.SystemPref;
import com.zww.api.service.SystemPrefService;

@Component
public class SystemPrefServiceHystrix  implements SystemPrefService{
	//获取系统参数服务
	public SystemPref selectByPrimaryKey(@RequestParam(value = "code") String code) {
		return null;
	}
	
	
	public String hello(@RequestParam(value = "code")  String code) {
		return code+" is fail";
	}

	@Override
	public List<SystemPref> selectChannel() {
		return null;
	}
}
