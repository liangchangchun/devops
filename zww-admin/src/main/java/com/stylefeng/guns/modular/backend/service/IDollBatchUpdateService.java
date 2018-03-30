package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DollBatchUpdate;
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
 * @since 2018-03-16
 */
public interface IDollBatchUpdateService extends IService<DollBatchUpdate> {

    List<Map<String, Object>> selectLists(Page<DollBatchUpdate> page, String name,String machineCode);

}
