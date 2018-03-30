package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DollDivination;
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
 * @author 孔欢欢
 * @since 2018-02-09
 */
public interface DollDivinationMapper extends BaseMapper<DollDivination> {

    Integer insert(DollDivination dollDivination);

    List<Map<String, Object>> selectLists(@Param("page") Page<DollDivination> page, @Param("name") String name);

    DollDivination selectById(@Param("id") Integer id);

    DollDivination selectByDollId(@Param("dollId") Integer dollId);
}