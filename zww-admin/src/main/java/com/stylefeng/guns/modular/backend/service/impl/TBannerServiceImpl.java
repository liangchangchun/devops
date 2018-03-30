package com.stylefeng.guns.modular.backend.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.persistence.dao.TBannerMapper;
import com.stylefeng.guns.common.persistence.model.TBanner;
import com.stylefeng.guns.modular.backend.service.ITBannerService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-18
 */
@Service
public class TBannerServiceImpl extends ServiceImpl<TBannerMapper, TBanner> implements ITBannerService {
	
}
