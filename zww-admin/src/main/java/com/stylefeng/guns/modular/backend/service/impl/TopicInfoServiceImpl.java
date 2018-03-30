package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.common.persistence.model.TopicInfo;
import com.stylefeng.guns.common.persistence.dao.TopicInfoMapper;
import com.stylefeng.guns.modular.backend.service.ITopicInfoService;
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
 * @author konghuanhuan
 * @since 2018-01-30
 */
@Service
public class TopicInfoServiceImpl extends ServiceImpl<TopicInfoMapper, TopicInfo> implements ITopicInfoService {

    @Autowired
    private TopicInfoMapper topicInfoMapper;

    @Override
    public boolean deleteById(Integer id) {
        return retBool(topicInfoMapper.deleteById(id));
    }

    @Override
    public List<Map<String, Object>> selectTopicInfo(Page<TopicInfo> page, String name) {
        return topicInfoMapper.selectTopicInfo(page,name);
    }
}
