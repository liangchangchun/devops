package com.zww.activity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zww.api.model.Banner;
import com.zww.api.model.ScrollingBanner;


/**
 * @author liangchangchun  2018.03.16
 */
public interface BannerDao extends BaseMapper<Banner> {

    List<Banner> selectBannerList(@Param("begin") int begin, @Param("pageSize") int pageSize);

    int totalCount();

    int insertSelective(Banner record);

    int bannerDel(Integer id);

    Banner selectBannerById(Integer id);

    int updateByPrimaryKeySelective(Banner record);

    Banner selectById(Banner record);

    List<ScrollingBanner> selectscrollingBanner();

    /**
     * 根据类型查询有效banner
     *
     * @return
     */
    List<Banner> selectActiveBannerByType(String[] type);

}
