package com.zww.order.service;

import java.util.List;
import java.util.Set;

import com.zww.api.model.Doll;
import com.zww.api.model.DollTopic;
import com.zww.common.ResultMap;


/**
 * Author: mwan
 * Version: 1.1
 * Date: 2018/03/27
 * Description: 娃娃机管理业务接口.
 * Copyright (c) 2018 365zww网络. All rights reserved.
 */
public interface DollService {

    /**
     * 获取房间主题(旧)
     *
     * @return
     */
    List<DollTopic> getDollTopics();

    /**
     * 获取房间主题
     *
     * @return
     */
    ResultMap getTopic();

    /**
     * 获取娃娃机列表
     */
    List<Doll> getDollList(int offset, int limit);

    List<Doll> getDollList(Integer offset, Integer limit, Integer dollTopic, boolean worker);

    List<Doll> getDollListPage(Integer offset, Integer limit, Integer dollTopic, boolean worker);

    List<Doll> getH5DollList(boolean worker);

    List<Doll> selectDollHistory(Integer memberId);

    Doll selectByPrimaryKey(Integer id);

    int getTotalCount();

    //获取娃娃机列表PageBean分页
    //PageBean<DollAndAddress> dollList(int page, int pageSize, String name, String machineCode, String machineStates);

    //新增娃娃机
    int insertSelective(Doll record);

    /**
     * 删除娃娃机
     */
    int dollDel(Doll doll);

    /**
     * 根据id查询娃娃机
     */
    Doll selectById(Doll doll);

    /**
     * 更新娃娃机
     */
    int updateByPrimaryKeySelective(Doll record);

    int totalCount(String name, String machineCode, String machineStates);

    List<Doll> allDollList();

    Integer updateDeleteStatus(Integer id);

    ResultMap DollList(Integer offset, Integer limit, Integer dollTopic, boolean worker);

    Integer selectTypeById(Integer dollId);

    List<Integer> selectDollId();

    Doll getDollByDollCode(String dollCode);

    Doll spareRoom();

}
