package com.stylefeng.guns.modular.backend.service.impl;

import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.common.persistence.dao.TDollMapper;
import com.stylefeng.guns.modular.backend.service.ITDollService;
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
public class TDollServiceImpl extends ServiceImpl<TDollMapper, TDoll> implements ITDollService {
	@Autowired
	TDollMapper tDollMapper;
	
	@Override
	public List<Map<String, Object>> selectDolls(Page<TDoll> page,Integer orderId, String name, String machineCode, String machineStates,Integer machineType,Integer modifiedBy) {
		// TODO Auto-generated method stub
		return tDollMapper.selectDolls(page,orderId,name, machineCode, machineStates,machineType,modifiedBy);
	}


}
