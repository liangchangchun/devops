package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MemberWhiteList;
import com.stylefeng.guns.common.persistence.dao.MemberWhiteListMapper;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.modular.backend.service.IMemberWhiteListService;
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
 * @author liangchangchun
 * @since 2018-01-03
 */
@Service
public class MemberWhiteListServiceImpl extends ServiceImpl<MemberWhiteListMapper, MemberWhiteList> implements IMemberWhiteListService {

    @Autowired
    private MemberWhiteListMapper memberWhiteListMapper;

    @Override
    public MemberWhiteList selectIdByMemberId(String memberId) {
        return memberWhiteListMapper.selectIdByMemberId(memberId);
    }

    @Override
    public List<Map<String, Object>> selectLists(Page<MemberWhiteList> page, String memberId, String userName) {
        return memberWhiteListMapper.selectLists(page,memberId,userName);
    }
}
