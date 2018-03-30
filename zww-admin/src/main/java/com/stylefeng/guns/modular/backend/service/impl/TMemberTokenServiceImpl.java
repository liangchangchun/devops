package com.stylefeng.guns.modular.backend.service.impl;

import com.stylefeng.guns.common.persistence.model.TMemberToken;
import com.stylefeng.guns.common.persistence.dao.TMemberTokenMapper;
import com.stylefeng.guns.modular.backend.service.ITMemberTokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-02-24
 */
@Service
public class TMemberTokenServiceImpl extends ServiceImpl<TMemberTokenMapper, TMemberToken> implements ITMemberTokenService {
	
}
