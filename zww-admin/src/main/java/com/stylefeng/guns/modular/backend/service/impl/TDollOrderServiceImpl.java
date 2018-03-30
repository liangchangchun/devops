package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.TDollOrder;
import com.stylefeng.guns.common.persistence.dao.TDollOrderMapper;
import com.stylefeng.guns.modular.backend.service.ITDollOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-01-30
 */
@Service
public class TDollOrderServiceImpl extends ServiceImpl<TDollOrderMapper, TDollOrder> implements ITDollOrderService {

    @Autowired
    private TDollOrderMapper tDollOrderMapper;

    @Override
    public List<Map<String, Object>> selectTDollOrder(Page<TDollOrder> page, String memberId, String phone) {
        return tDollOrderMapper.selectTDollOrder(page,memberId,phone);
    }

    @Override
    public List<Map<String, Object>> selectTDollOrderOut(Page<TDollOrder> page, String memberId, String phone) {
        return tDollOrderMapper.selectTDollOrderOut(page,memberId,phone);
    }

    @Override
    public TDollOrder selectByorderNum(String orderNum) {
        return tDollOrderMapper.selectByOrderNum(orderNum);
    }
}
