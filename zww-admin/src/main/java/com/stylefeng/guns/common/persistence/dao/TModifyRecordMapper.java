package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.Account;
import com.stylefeng.guns.common.persistence.model.Member;
import com.stylefeng.guns.common.persistence.model.TModifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-02
 */
public interface TModifyRecordMapper extends BaseMapper<TModifyRecord> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(TModifyRecord record);

    TModifyRecord selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(TModifyRecord record);

    int updateByPrimaryKey(TModifyRecord record);

}