package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MachinePhysicalProbability;
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
 * @since 2018-02-11
 */
public interface MachinePhysicalProbabilityMapper extends BaseMapper<MachinePhysicalProbability> {

    List<Map<String, Object>> selectLists(@Param("page") Page<MachinePhysicalProbability> page,@Param("dollId") Integer dollId, @Param("name") String name, @Param("machineCode") String machineCode);

    MachinePhysicalProbability selectByDollId(@Param("dollId") Integer dollId);
}