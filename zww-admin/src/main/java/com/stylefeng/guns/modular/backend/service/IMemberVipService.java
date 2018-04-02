package com.stylefeng.guns.modular.backend.service;

import com.stylefeng.guns.common.persistence.model.MemberVip;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-28
 */
public interface IMemberVipService extends IService<MemberVip> {

   boolean deleteByMemberVipId(Integer id);

}
