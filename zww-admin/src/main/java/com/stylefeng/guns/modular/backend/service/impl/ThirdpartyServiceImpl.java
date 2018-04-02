package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.Thirdparty;
import com.stylefeng.guns.common.persistence.dao.ThirdpartyMapper;
import com.stylefeng.guns.modular.backend.service.IThirdpartyService;
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
 * @since 2018-03-26
 */
@Service
public class ThirdpartyServiceImpl extends ServiceImpl<ThirdpartyMapper, Thirdparty> implements IThirdpartyService {

    @Autowired
    private ThirdpartyMapper thirdpartyMapper;

    @Override
    public List<Map<String, Object>> selectLists(Page<Thirdparty> page, String name) {
        return thirdpartyMapper.selectLists(page,name);
    }
}
