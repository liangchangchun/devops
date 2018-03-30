package com.zww.account.service;

import java.util.List;

import com.zww.api.model.MemberAddr;


public interface MemberAddrService {

    MemberAddr insertAddr(MemberAddr addr);

    Integer deleteByPrimaryKey(Integer id);

    List<MemberAddr> findByMemberId(Integer memberId);

    MemberAddr selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(MemberAddr addr);

    Integer updateDefaultAddr(Integer memberId);

    /**
     * 修改默认地址
     *
     * @param id 新默认地址id
     * @return
     */
    Integer updateDafultAddrById(Integer id);
}
