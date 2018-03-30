package com.stylefeng.guns.modular.backend.service;

import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.common.persistence.model.TDollImage;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-24
 */
public interface ITDollImageService extends IService<TDollImage> {

	List<Map<String, Object>> getImageList(Page<TDollImage> page, String name);

	//批量删除图片
	int deleteAllById(List<String> id);
}
