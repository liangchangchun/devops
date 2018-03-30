package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MemberChannelDeduct;
import com.stylefeng.guns.common.persistence.dao.MemberChannelDeductMapper;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.modular.backend.service.IMemberChannelDeductService;
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
 * @author 孔欢欢
 * @since 2018-03-02
 */
@Service
public class MemberChannelDeductServiceImpl extends ServiceImpl<MemberChannelDeductMapper, MemberChannelDeduct> implements IMemberChannelDeductService {
    @Autowired
    private MemberChannelDeductMapper memberChannelDeductMapper;

    @Override
    public Boolean deleteAllById(String[] id) {
        return retBool(memberChannelDeductMapper.deleteAllById(id));
    }

    @Override
    public List<Map<String, Object>> selectLists(Page<MemberChannelDeduct> page, String useId, String name, String registeDate, String endTime, String lastLoginFrom,String channelNum) {
        return memberChannelDeductMapper.selectLists(page,useId,name, registeDate,endTime, lastLoginFrom,channelNum);
    }
}
