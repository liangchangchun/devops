package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.BusinessLog;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.TDoll;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-20
 */
public interface IBusinessLogService extends IService<BusinessLog> {

    List<Map<String, Object>> selectLists(Page<BusinessLog> page, String logName, String logType, String beginTime, String endTime);
}
