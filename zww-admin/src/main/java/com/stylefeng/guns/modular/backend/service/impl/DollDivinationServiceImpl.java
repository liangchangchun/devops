package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DollDivination;
import com.stylefeng.guns.common.persistence.dao.DollDivinationMapper;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.modular.backend.service.IDollDivinationService;
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
 * @since 2018-02-09
 */
@Service
public class DollDivinationServiceImpl extends ServiceImpl<DollDivinationMapper, DollDivination> implements IDollDivinationService {

    @Autowired
    private DollDivinationMapper dollDivinationMapper;

    @Override
    public boolean insert(DollDivination dollDivination) {
        return retBool(dollDivinationMapper.insert(dollDivination));
    }

    @Override
    public List<Map<String, Object>> selectLists(Page<DollDivination> page, String name) {
        return dollDivinationMapper.selectLists(page,name);
    }

    @Override
    public DollDivination selectByDollId(Integer dollId) {
        return dollDivinationMapper.selectByDollId(dollId);
    }
}
