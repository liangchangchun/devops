package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.TDoll;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-02-02
 */
public interface IDivinationTopicService extends IService<DivinationTopic> {

    List<Map<String, Object>> selectLists(Page<DivinationTopic> page, String name);

    DivinationTopic selectByDollId(Integer dollId);


}
