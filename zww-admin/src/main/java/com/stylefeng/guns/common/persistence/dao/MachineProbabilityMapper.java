package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MachinePhysicalProbability;
import com.stylefeng.guns.common.persistence.model.MachineProbability;
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
 * @since 2018-01-29
 */
public interface MachineProbabilityMapper extends BaseMapper<MachineProbability> {

    List<Map<String, Object>> selectMachineProbability(@Param("page") Page<MachineProbability> page, @Param("dollId") Integer dollId, @Param("machainCode") String machainCode, @Param("name") String name);

    MachineProbability selectByDollId(@Param("dollId") Integer dollId);

}