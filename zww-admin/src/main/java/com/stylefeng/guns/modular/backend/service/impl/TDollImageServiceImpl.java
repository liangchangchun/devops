package com.stylefeng.guns.modular.backend.service.impl;

import com.stylefeng.guns.common.persistence.model.TDollImage;
import com.stylefeng.guns.common.persistence.dao.TDollImageMapper;
import com.stylefeng.guns.modular.backend.service.ITDollImageService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-24
 */
@Service
public class TDollImageServiceImpl extends ServiceImpl<TDollImageMapper, TDollImage> implements ITDollImageService {
	@Autowired
	TDollImageMapper tDollImageMapper;
	
	@Override
	public List<Map<String, Object>> getImageList(Page<TDollImage> page, String name) {
		return tDollImageMapper.getImageList(page,name);
	}

	@Override
	public int deleteAllById(List<String> id) {
		return tDollImageMapper.deleteAllById(id);
	}
}
