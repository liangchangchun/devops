package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DollBatchUpdate;
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
 * @since 2018-03-16
 */
public interface DollBatchUpdateMapper extends BaseMapper<DollBatchUpdate> {

    Integer inserts(DollBatchUpdate dollBatchUpdate);

    List<Map<String, Object>> selectLists(@Param("page") Page<DollBatchUpdate> page, @Param("name") String name, @Param("machineCode") String machineCode);

    DollBatchUpdate selectByDollId(@Param("dollId") Integer dollId);

    Integer deleteAllById(@Param("ids") List<String> dollIds);

    //批量空闲
    Integer updateStatus(@Param("ids") List<String> dollIds);
    //批量未上线
    Integer updateStatusW(@Param("ids") List<String> dollIds);
}