package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.common.persistence.model.TopicInfo;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-01-30
 */
public interface ITopicInfoService extends IService<TopicInfo> {

    boolean deleteById(Integer id);

    List<Map<String, Object>> selectTopicInfo(Page<TopicInfo> page, String name);



}
