package com.stylefeng.guns.modular.backend.service.impl;

import com.stylefeng.guns.common.persistence.model.AlertInfo;
import com.stylefeng.guns.common.persistence.dao.AlertInfoMapper;
import com.stylefeng.guns.modular.backend.service.IAlertInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-02-06
 */
@Service
public class AlertInfoServiceImpl extends ServiceImpl<AlertInfoMapper, AlertInfo> implements IAlertInfoService {
	
}
