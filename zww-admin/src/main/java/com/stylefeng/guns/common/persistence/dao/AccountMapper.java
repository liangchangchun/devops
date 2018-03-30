package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.Account;
import com.stylefeng.guns.common.persistence.model.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-02
 */
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 根据条件查询用户金币
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectAccount(@Param("page") Page<Member> page, @Param("userId") String userId, @Param("userName") String userName, @Param("beginTime") String beginTime, @Param("lastLoginFrom") String lastLoginFrom);

    //跟据memberid查id
    Account selectById(@Param("memberId") Integer memberId);

    //修改用户金币
    Integer updateByKeyId(Account account);


}