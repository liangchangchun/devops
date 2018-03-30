package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.common.persistence.model.TopicInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-01-30
 */
public interface TopicInfoMapper extends BaseMapper<TopicInfo> {

   Integer deleteById(@Param("id") Integer id);

   List<Map<String, Object>> selectTopicInfo(@Param("page") Page<TopicInfo> page, @Param("name") String name);

}