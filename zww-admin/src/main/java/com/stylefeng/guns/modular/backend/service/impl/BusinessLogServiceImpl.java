package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.BusinessLog;
import com.stylefeng.guns.common.persistence.dao.BusinessLogMapper;
import com.stylefeng.guns.modular.backend.service.IBusinessLogService;
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
 * @since 2018-03-20
 */
@Service
public class BusinessLogServiceImpl extends ServiceImpl<BusinessLogMapper, BusinessLog> implements IBusinessLogService {

    @Autowired
    private BusinessLogMapper businessLogMapper;

    @Override
    public List<Map<String, Object>> selectLists(Page<BusinessLog> page, String logName, String logType, String beginTime, String endTime) {
        return businessLogMapper.selectLists(page, logName, logType, beginTime, endTime);
    }
}
