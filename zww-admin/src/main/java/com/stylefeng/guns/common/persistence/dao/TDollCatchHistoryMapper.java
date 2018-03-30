package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.ChargeOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.Member;
import com.stylefeng.guns.common.persistence.model.TDollCatchHistory;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-02
 */
public interface TDollCatchHistoryMapper extends BaseMapper<TDollCatchHistory> {

    List<Map<String, Object>> selectDollCatchHistoryList(@Param("page") Page<TDollCatchHistory> page, @Param("userId")Integer userId, @Param("dollId")Integer dollId, @Param("catchStatus")String catchStatus, @Param("beginDate")String beginDate, @Param("endtime")String endtime);

    //抓取次数
    Integer selectCatchNum(@Param("memberId") Integer memberId, @Param("beginDate")String beginDate, @Param("endtime") String endtime);

    //抓取成功次数
    Integer selectCatchSuccessNum(@Param("memberId") Integer memberId, @Param("beginDate")String beginDate, @Param("endtime") String endtime);

}