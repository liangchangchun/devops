package com.zww.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zww.api.model.*;
import com.zww.common.Enviroment;
import com.zww.common.ResultMap;
import com.zww.order.dao.DollDao;
import com.zww.order.dao.DollTopicDao;
import com.zww.order.service.DollService;


/**
 * Author: lcc
 * Version: 1.1
 * Date: 2018/03/26
 * Description: 娃娃机管理业务接口实现类.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
@Service("DollService")
public class DollServiceImpl implements DollService {
    //private static final Logger logger = LoggerFactory.getLogger(DollServiceImpl.class);
    @Autowired
    private DollDao dollDao;
    @Autowired
    private DollTopicDao dollTopicDao;

    @Override
    public List<DollTopic> getDollTopics() {
        return dollDao.getDollTopics();
    }

    /**
     * 获取主题列表
     *
     * @return
     */
    @Override
    public ResultMap getTopic() {
        List<TopicInfo> topicInfolist = dollDao.getTopicInfo();
        if (topicInfolist != null && topicInfolist.size() > 0) {
            //logger.info("主题列表查询成功");
            return new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE, topicInfolist);
        }
        return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.RETURN_FAILE_MESSAGE);
    }

    public List<Doll> getDollList(int offset, int limit) {
        return dollDao.getDollList(offset, limit);
    }

    public List<Doll> getH5DollList(int offset, int limit) {
        return dollDao.getH5DollList(offset, limit);
    }

    /**
     * 加载主题列表
     */
    @Override
    @Cacheable(value = "dollList", key = "#worker+'-'+#dollTopic")
    public List<Doll> getDollList(Integer offset, Integer limit, Integer dollTopic, boolean worker) {
        List<Doll> dollTopicList = dollDao.getDollTopicList(offset, limit, dollTopic);
        /*if (worker) {
            for (Doll doll : dollTopicList) {
                int endIndex = doll.getName().indexOf("-");
                if (endIndex > 0) {
                    doll.setName(doll.getMachineCode() + "-" + doll.getName().substring(0, doll.getName().indexOf("-")));
                } else {
                    doll.setName(doll.getMachineCode() + "-" + doll.getName());
                }
            }*/
        worker = true;
        if (worker) {
            for (Doll doll : dollTopicList) {
                int endIndex = doll.getName().indexOf("-");
                if (endIndex > 0) {
                    doll.setName(doll.getName().substring(0, doll.getName().indexOf("-")) + "-" + doll.getMachineCode());
                } else {
                    doll.setName(doll.getName() + "-" + doll.getMachineCode());
                }
            }
        } else {
            for (Doll doll : dollTopicList) {
                int endIndex = doll.getName().indexOf("-");
                if (endIndex > 0) {
                    doll.setName(doll.getName().substring(0, endIndex));
                }
            }
        }
        return dollTopicList;
    }

    public List<Doll> getDollListPage(Integer offset, Integer limit, Integer dollTopic, boolean worker) {
        List<Doll> dollList = getDollList(0, 1000, dollTopic, worker);
        if (dollList == null) {
            return null;
        }
        int size = dollList.size();
        if (offset >= size) {
            return null;
        }
        int i = offset + limit;
        List<Doll> dolls = dollList.subList(offset, i <= size ? i : size);
        return dolls;
    }

    @Override
    @Cacheable(value = "h5DollList", key = "#worker+''")
    public List<Doll> getH5DollList(boolean worker) {
        List<Doll> dollTopicList = dollDao.getH5DollTopicList();
        worker = true;
        if (worker) {
            for (Doll doll : dollTopicList) {
                int endIndex = doll.getName().indexOf("-");
                if (endIndex > 0) {
                    doll.setName(doll.getName().substring(0, doll.getName().indexOf("-")) + "-" + doll.getMachineCode());
                } else {
                    doll.setName(doll.getName() + "-" + doll.getMachineCode());
                }
            }
        } else {
            for (Doll doll : dollTopicList) {
                int endIndex = doll.getName().indexOf("-");
                if (endIndex > 0) {
                    doll.setName(doll.getName().substring(0, endIndex));
                }
            }
        }
        return dollTopicList;
    }

    public List<Doll> selectDollHistory(Integer memberId) {
        return dollDao.selectDollHistory(memberId);
    }

    public Doll selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return dollDao.selectByPrimaryKey(id);
    }

    public int getTotalCount() {
        // TODO Auto-generated method stub
        return dollDao.getTotalCount();
    }
/*
    @Override
    public PageBean<DollAndAddress> dollList(int page, int pageSize, String name, String machineCode, String machineStates) {
        PageBean<DollAndAddress> pageBean = new PageBean<DollAndAddress>();
        pageBean.setPage(page);
        pageBean.setPageSize(pageSize);
        int totalCount = 0;
        totalCount = dollDao.totalCount(name, machineCode, machineStates);
        pageBean.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        pageBean.setTotalPage(totalPage);
        int begin = (page - 1) * pageSize;
        List<DollAndAddress> list = dollDao.dollList(begin, pageSize, name, machineCode, machineStates);
        pageBean.setList(list);
        int start = page % 10 == 0 ? (page - 1) / 10 * 10 + 1 : page / 10 * 10 + 1;
//		int end=page/10*10+10>totalPage?totalPage:(page%10==0?(page-1)/10*10+10:page/10*10+10);
        int end = page % 10 == 0 ? ((page - 1) / 10 * 10 + 10 > totalPage ? totalPage : (page - 1) / 10 * 10 + 10) : (page / 10 * 10 + 10 > totalPage ? totalPage : page / 10 * 10 + 10);
        pageBean.setStart(start);
        pageBean.setEnd(end);
        return pageBean;
    }
*/
    @Override
    public int insertSelective(Doll record) {
        record.setMachineStatus("未上线");
        record.setCreatedDate(new Date());
        record.setModifiedDate(new Date());
        record.setTbimgRealPath("1");
        return dollDao.insertSelective(record);
    }

    /**
     * 删除娃娃机
     */
    @Override
    public int dollDel(Doll doll) {
        return dollDao.dollDel(doll);
    }

    /**
     * 根据id查询娃娃机
     */
    @Override
    public Doll selectById(Doll doll) {
        return dollDao.selectById(doll);
    }

    /**
     * 更新娃娃机
     */
    @Override
    public int updateByPrimaryKeySelective(Doll record) {

        //修改主题
        DollTopic dollTopic = new DollTopic();
        dollTopic.setDollId(record.getId());
        dollTopic.setDollName(record.getName());
        dollTopicDao.updateByDollIdSelective(dollTopic);
        //修改时间
        record.setModifiedDate(new Date());
        return dollDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int totalCount(String name, String machineCode, String machineStates) {
        // TODO Auto-generated method stub
        return dollDao.totalCount(name, machineCode, machineCode);
    }

    @Override
    public List<Doll> allDollList() {
        return dollDao.allDollList();
    }

    //删除状态
    @Override
    public Integer updateDeleteStatus(Integer id) {
        dollTopicDao.deleteByDollId(id);
        return dollDao.updateDeleteStatus(id);
    }

    @Override
    public ResultMap DollList(Integer offset, Integer limit, Integer dollTopic, boolean worker) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null) {
            limit = Enviroment.MAX_TABLE_LIMIT;
        }
        if (dollTopic == null) {
            dollTopic = 0;
        }
        List<Doll> dollList = getDollList(offset, limit, dollTopic, worker);
        return new ResultMap(Enviroment.RETURN_SUCCESS_CODE, dollList);
    }

    @Override
    public Integer selectTypeById(Integer dollId) {
        return dollDao.selectTypeById(dollId);
    }

    @Override
    public List<Integer> selectDollId() {
        return dollDao.selectDollId();
    }

    @Override
    public Doll getDollByDollCode(String dollCode) {
        return dollDao.getDollByDollCode(dollCode);
    }

    @Override
    public Doll spareRoom() {
        List<Doll> dolls = dollDao.selectSpareRoom();
        if (dolls.size() < 1) {
            return null;

        }
        for (Doll doll : dolls) {
            if ("空闲中".equals(doll.getMachineStatus())) {
                return doll;
            }
        }
        return dolls.get(1);
    }

}
