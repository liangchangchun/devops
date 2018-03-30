package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DollBatchUpdate;
import com.stylefeng.guns.common.persistence.dao.DollBatchUpdateMapper;
import com.stylefeng.guns.modular.backend.service.IDollBatchUpdateService;
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
 * @since 2018-03-16
 */
@Service
public class DollBatchUpdateServiceImpl extends ServiceImpl<DollBatchUpdateMapper, DollBatchUpdate> implements IDollBatchUpdateService {

    @Autowired
    private DollBatchUpdateMapper dollBatchUpdateMapper;

    @Override
    public List<Map<String, Object>> selectLists(Page<DollBatchUpdate> page, String name,String machineCode) {
        return dollBatchUpdateMapper.selectLists(page,name,machineCode);
    }
}
