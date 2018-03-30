package com.zww.order.dao;

import org.apache.ibatis.annotations.Param;

import com.zww.api.model.DollTopic;

import java.util.List;

/**
 * @author lcc Version: 1.0 Date: 2018/3/26 Description: 用户Dao接口类. Copyright
 *         (c) 2018 365zww网络. All rights reserved.
 */
public interface DollTopicDao {


	/**
	 * 更新主题
	 */
	int updateByPrimaryKeySelective(DollTopic dollTopic);
	int insertSelective(DollTopic dollTopic);
	/**
	 * 分页查询机器类型列表
	 */
	List<DollTopic> getDollTopicList(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("dollId")  Integer dollId,@Param("topicName") String topicName);
	/**
	 * 总记录数
	 */
	int totalCount(@Param("dollId") Integer dollId,@Param("topicName") String topicName);

	DollTopic getDollTopicById(@Param("id") Integer id);

	int dollTopicDel(@Param("id") Integer id);

	DollTopic getDollTopicByName(@Param("name")String name);

	Integer maxType();

	//更新机器同时更新主题机器名字
	Integer updateByDollIdSelective(DollTopic dollTopic);
	//删除机器同时删除主题
	Integer deleteByDollId(@Param("dollId")Integer dollId);
}




