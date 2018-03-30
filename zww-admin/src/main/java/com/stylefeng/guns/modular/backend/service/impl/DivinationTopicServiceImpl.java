package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.stylefeng.guns.common.persistence.dao.DivinationTopicMapper;
import com.stylefeng.guns.modular.backend.service.IDivinationTopicService;
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
 * @since 2018-02-02
 */
@Service
public class DivinationTopicServiceImpl extends ServiceImpl<DivinationTopicMapper, DivinationTopic> implements IDivinationTopicService {

    @Autowired
    private DivinationTopicMapper divinationTopicMapper;

    @Override
    public List<Map<String, Object>> selectLists(Page<DivinationTopic> page, String name) {
        return divinationTopicMapper.selectLists(page,name);
    }

    @Override
    public DivinationTopic selectByDollId(Integer dollId) {
        return divinationTopicMapper.selectByDollId(dollId);
    }
}
