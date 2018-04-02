package com.stylefeng.guns.modular.backend.service.impl;

import com.stylefeng.guns.common.persistence.model.MemberVip;
import com.stylefeng.guns.common.persistence.dao.MemberVipMapper;
import com.stylefeng.guns.modular.backend.service.IMemberVipService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-28
 */
@Service
public class MemberVipServiceImpl extends ServiceImpl<MemberVipMapper, MemberVip> implements IMemberVipService {

    @Autowired
    private MemberVipMapper memberVipMapper;

    @Override
    public boolean deleteByMemberVipId(Integer id) {
        return  retBool(memberVipMapper.deleteByMemberVipId(id));
    }
}
