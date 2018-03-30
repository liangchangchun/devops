package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.common.persistence.model.TMember;
import com.stylefeng.guns.common.persistence.dao.TMemberMapper;
import com.stylefeng.guns.modular.system.service.ITMemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-02
 */
@Service
public class TMemberServiceImpl extends ServiceImpl<TMemberMapper, TMember> implements ITMemberService {
	
}
