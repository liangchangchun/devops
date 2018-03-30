package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.dao.DivinationTopicMapper;
import com.stylefeng.guns.common.persistence.model.DivinationImage;
import com.stylefeng.guns.common.persistence.dao.DivinationImageMapper;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.modular.backend.service.IDivinationImageService;
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
public class DivinationImageServiceImpl extends ServiceImpl<DivinationImageMapper, DivinationImage> implements IDivinationImageService {

    @Autowired
    private DivinationImageMapper divinationImageMapper;

    @Override
    public List<Map<String, Object>> selectLists(Page<DivinationImage> page, String name) {
        return divinationImageMapper.selectLists(page,name);
    }

    @Override
    public DivinationImage selectByDivinationTopicId(Integer condition) {
        return divinationImageMapper.selectByDivinationTopicId(condition);
    }

    @Override
    public Integer deleteAllById(List<String> id) {
        return divinationImageMapper.deleteAllById(id);
    }
}
