package com.zww.base.dao;

import java.util.List;

import com.zww.api.model.SystemPref;

/**
 * Author: mwan
 * Version: 1.1
 * Date: 2018/03/23
 * Description: 系统参数表DAO层.
 * Copyright (c) 2018 zww365网络. All rights reserved.
 */
public interface SystemPrefDao {
    int deleteByPrimaryKey(String code);

    int insert(SystemPref record);

    int insertSelective(SystemPref record);

    SystemPref selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SystemPref record);

    int updateByPrimaryKey(SystemPref record);
    
    List<SystemPref> selectAll();

    List<SystemPref> selectChannel();
}