package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DivinationImage;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
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
public interface IDivinationImageService extends IService<DivinationImage> {

    List<Map<String, Object>> selectLists(Page<DivinationImage> page, String name);

    DivinationImage selectByDivinationTopicId(Integer condition);

    //批量删除
    Integer deleteAllById(List<String> id);
}
