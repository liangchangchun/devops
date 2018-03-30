package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.DollTopic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-24
 */
public interface DollTopicMapper extends BaseMapper<DollTopic> {
	/**
	 * 分页查询机器类型列表
	 */
	List<Map<String, Object>> getDollTopicList(@Param("page") Page<DollTopic> page, @Param("machineCode")  Integer machineCode,@Param("topicName") String topicName);

	Integer selectByTopicName(@Param("topicName") String topicName);

	String selectByTopicType(@Param("topicType") Integer topicType);
	
	Integer getMaxTopicType();

	List<DollTopic> getTopicList();

	//修改机器名
	Integer updateByDollId(DollTopic dollTopic);

	DollTopic selectLists(DollTopic dollTopic);
}