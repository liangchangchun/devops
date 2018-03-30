package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.BusinessLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-20
 */
public interface BusinessLogMapper extends BaseMapper<BusinessLog> {

   List<Map<String, Object>> selectLists(@Param("page") Page<BusinessLog> page, @Param("logName") String logName, @Param("logType") String logType, @Param("beginTime") String beginTime, @Param("endTime")String endTime);
}