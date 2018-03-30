package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.TDoll;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-02-02
 */
public interface DivinationTopicMapper extends BaseMapper<DivinationTopic> {

    List<Map<String, Object>> selectLists(@Param("page") Page<DivinationTopic> page, @Param("name") String name);

    DivinationTopic selectByDollId(@Param("dollId") Integer dollId);
}