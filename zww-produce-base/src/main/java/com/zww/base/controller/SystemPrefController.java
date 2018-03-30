package com.zww.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zww.api.model.SystemPref;
import com.zww.api.service.SystemPrefService;
import com.zww.base.dao.SystemPrefDao;

/**
 * 系统参数服务
 * @author lcc
 *
 */
@RefreshScope
@RestController
public class SystemPrefController implements SystemPrefService {
	
	@Autowired
	private SystemPrefDao systemPrefDao;

	
	//@RequestMapping(value = "/selectByPrimaryKey" ,method = RequestMethod.POST)
	@Override
	public SystemPref selectByPrimaryKey(@RequestParam String code) {
		return systemPrefDao.selectByPrimaryKey(code);
	}
	
	
	
	//@RequestMapping(value = "/hello")
	@Override
	public String hello(@RequestParam String code) {
		return "hello "+code+"，this is back messge";
	}



	@Override
	public List<SystemPref> selectChannel() {
		// TODO Auto-generated method stub
		return systemPrefDao.selectChannel();
	}


}
