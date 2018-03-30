package com.stylefeng.guns.modular.backend.service;

import com.stylefeng.guns.common.persistence.model.DollTopic;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-24
 */
public interface IDollTopicService extends IService<DollTopic> {

	List<Map<String, Object>> getDollTopicList(Page<DollTopic> page, String topicName, Integer machineCode);

	Integer selectByTopicName(String topicName);

	Integer getMaxTopicType();

	Integer updateByDollId(DollTopic dollTopic);

	DollTopic selectLists(DollTopic dollTopic);
}
