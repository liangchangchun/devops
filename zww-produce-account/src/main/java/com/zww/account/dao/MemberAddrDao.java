package com.zww.account.dao;

import java.util.List;

import com.zww.api.model.MemberAddr;


public interface MemberAddrDao {
    /**
     * 添加地址
     */
    int insertAddr(MemberAddr addr);

    /**
     * 删除地址
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据id查询收货地址
     */
    MemberAddr selectByPrimaryKey(Integer id);

    /**
     * 根据memberId查询收货地址
     */
    List<MemberAddr> findByMemberId(Integer memberId);

    /**
     * 修改收货地址
     */
    int updateByPrimaryKeySelective(MemberAddr addr);

    /**
     * 清除默认收货地址
     */
    int updateDefaultAddr(Integer memberId);

    /**
     * 设置默认收货地址
     *
     * @param id 新默认地址id
     * @return
     */
    int updateDafultAddrById(Integer id);

    /**
     * 获取默认地址
     */
    MemberAddr selectDefaultAddr(Integer memberId);
}
