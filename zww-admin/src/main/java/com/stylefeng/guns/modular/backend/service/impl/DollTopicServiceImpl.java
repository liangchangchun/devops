package com.stylefeng.guns.modular.backend.service.impl;

import com.stylefeng.guns.common.persistence.model.DollTopic;
import com.stylefeng.guns.common.persistence.dao.DollTopicMapper;
import com.stylefeng.guns.modular.backend.service.IDollTopicService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-24
 */
@Service
public class DollTopicServiceImpl extends ServiceImpl<DollTopicMapper, DollTopic> implements IDollTopicService {

	@Autowired
	DollTopicMapper dollTopicMapper;
	
	@Override
	public List<Map<String, Object>> getDollTopicList(Page<DollTopic> page, String topicName, Integer machineCode) {
		return dollTopicMapper.getDollTopicList(page,machineCode,topicName);
	}

	@Override
	public Integer selectByTopicName(String topicName) {
		return dollTopicMapper.selectByTopicName(topicName);
	}

	@Override
	public Integer getMaxTopicType() {
		return dollTopicMapper.getMaxTopicType();
	}

	@Override
	public Integer updateByDollId(DollTopic dollTopic) {
		return dollTopicMapper.updateByDollId(dollTopic);
	}

	@Override
	public DollTopic selectLists(DollTopic dollTopic) {
		return dollTopicMapper.selectLists(dollTopic);
	}
}
