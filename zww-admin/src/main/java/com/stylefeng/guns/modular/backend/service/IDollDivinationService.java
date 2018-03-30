package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DollDivination;
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
 * @since 2018-02-09
 */
public interface IDollDivinationService extends IService<DollDivination> {

    boolean insert(DollDivination dollDivination);

    List<Map<String, Object>> selectLists(Page<DollDivination> page, String name);

    DollDivination selectByDollId(Integer dollId);
}
