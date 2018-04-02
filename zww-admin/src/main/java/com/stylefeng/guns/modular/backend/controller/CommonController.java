package com.stylefeng.guns.modular.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.dao.*;
import com.stylefeng.guns.common.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.backend.service.ITChargeRulesService;

@RestController
@RequestMapping("/common")
public class CommonController extends BaseController{

	@Autowired
	TChargeRulesMapper tChargeRulesMapper;
	
	@Autowired
	DollAddressMapper dollAddressMapper;
	
	@Autowired
	TDollMapper tDollMapper;

	@Autowired
	DollTopicMapper dollTopicMapper;

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	DivinationTopicMapper divinationTopicMapper;

	@Autowired
	MemberVipMapper memberVipMapper;

	/**
	 * 获取充值 规则	
	 * @return
	 */
	@RequestMapping(value = "getChargeRules",method = RequestMethod.POST)
	public Object getChargeRules(){
		/*List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map map = new HashMap();
		map.put("label", "java");
		map.put("value", "Java");
		result.add(map);
		Map map1 = new HashMap();
		map1.put("label", "perl");
		map1.put("value", "Perl");
		result.add(map1);*/
		List<TChargeRules> result = tChargeRulesMapper.selectList(null);
		return result;
	 //"[{label: 'java',value: 'Java'},{label: 'perl',value: 'Perl'},{label: 'ruby',value: 'Ruby'}]";
	}	
	/**
	 * 获取娃娃机地址列表
	 */
	@RequestMapping(value = "getDollAddress",method = RequestMethod.POST)
	public Object getDollAddress(){
		List<DollAddress> result = dollAddressMapper.selectList(null);
		String addr = "";
		for (DollAddress address:result) {
			addr = address.getProvince()+address.getCity()+address.getCounty()+address.getStreet();
			address.setAddress(addr);
		}
		return result;
	}	
	/**
	 * 获取娃娃机列表
	 * @return
	 */
	@RequestMapping(value = "getDollList",method = RequestMethod.POST)
	public Object getDollList(){
		List<TDoll> result = tDollMapper.selectList(null);
		return result;
	}

	/**
	 * 获取娃娃机列表排序
	 * @return
	 */
	@RequestMapping(value = "getDollListOrderByMachineCode",method = RequestMethod.POST)
	public Object getDollListOrderByMachineCode(){
		List<TDoll> result = tDollMapper.getDollListOrderByMachineCode();
		return result;
	}
	/**
	 * 获取娃娃机概率列表
	 * @return
	 */
	@RequestMapping(value = "getDollListProbability",method = RequestMethod.POST)
	public Object getDollListProbability(){
		List<TDoll> result = tDollMapper.getDollListProbability();
		return result;
	}

	/**
	 * 获取娃娃机占卜列表
	 * @return
	 */
	@RequestMapping(value = "getDollListDivination",method = RequestMethod.POST)
	public Object getDollListDivination(){
		List<TDoll> result = tDollMapper.getDollListDivination();
		return result;
	}



	/**
	 * 获取主题名称
	 * @return
	 */
	@RequestMapping(value = "getTopicList",method = RequestMethod.POST)
	public Object getTopicList(){
		List<DollTopic> result = dollTopicMapper.getTopicList();
		return result;
	}

	/**
	 * 获取渠道号
	 * @return
	 */
	@RequestMapping(value = "getRegisterChannel",method = RequestMethod.POST)
	public Object getRegisterChannel(){
		List<Member> result = memberMapper.getRegisterChannel();
		List<Member> results = new ArrayList<>();
		for (Member m : result){
			if("".equals(m.getRegisterChannel())){
				m.setRegisterChannel("---");
			}
			results.add(m);
		}
		return results;
	}

	/**
	 * 获取占卜列表
	 * @return
	 */
	@RequestMapping(value = "getdivinationTopicList",method = RequestMethod.POST)
	public Object getdivinationTopicList(){
		List<DivinationTopic> result = divinationTopicMapper.selectList(null);
		return result;
	}

	/**
	 * 获取vip等级
	 * @return
	 */
	@RequestMapping(value = "getVipList",method = RequestMethod.POST)
	public Object getVipList(){
		List<MemberVip> result = memberVipMapper.selectList(null);
		return result;
	}
}
