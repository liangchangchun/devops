package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.TDollImage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-24
 */
public interface TDollImageMapper extends BaseMapper<TDollImage> {

	List<Map<String, Object>> getImageList(@Param("page") Page<TDollImage> page,@Param("name") String name);

	//批量删除图片
	int deleteAllById(@Param("ids")List<String> ids);
}