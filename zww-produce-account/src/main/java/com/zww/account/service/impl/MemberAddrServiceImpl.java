package com.zww.account.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zww.account.dao.MemberAddrDao;
import com.zww.account.service.MemberAddrService;
import com.zww.api.model.MemberAddr;
import com.zww.common.Enviroment;


@Service("MemberAddrService")
public class MemberAddrServiceImpl implements MemberAddrService {
    private static final Logger logger = LoggerFactory.getLogger(MemberAddrServiceImpl.class);
    @Autowired
    private MemberAddrDao memberAddrDao;

    @Override
    public MemberAddr insertAddr(MemberAddr addr) {
        logger.info("insertAddr 参数addr:{}", addr);
        addr.setCreatedDate(new Date());
        addr.setModifiedDate(new Date());
        List<MemberAddr> list = memberAddrDao.findByMemberId(addr.getMemberId());
        if (list != null && list.size() > 0) {
            addr.setDefaultFlg1(Enviroment.NOTDEFAULTFLG);
        } else {
            addr.setDefaultFlg1(Enviroment.ISDEFAULTFLG);
        }
        memberAddrDao.insertAddr(addr);
        return addr;
    }

    @Transactional
    public Integer deleteByPrimaryKey(Integer id) {
        logger.info("deleteByPrimaryKey 参数id:{}", id);
        return memberAddrDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<MemberAddr> findByMemberId(Integer memberId) {
        logger.info("findByMemberId 参数memberId:{}", memberId);
        org.springframework.http.converter.json.MappingJackson2HttpMessageConverter cd = null;
        return memberAddrDao.findByMemberId(memberId);
    }

    @Override
    public MemberAddr selectByPrimaryKey(Integer id) {
        logger.info("selectByPrimaryKey 参数id:{}", id);
        return memberAddrDao.selectByPrimaryKey(id);
    }

    @Transactional
    public Integer updateByPrimaryKeySelective(MemberAddr addr) {
        logger.info("updateByPrimaryKeySelective 参数addr:{}", addr);
        addr.setModifiedDate(new Date());
        return memberAddrDao.updateByPrimaryKeySelective(addr);
    }

    @Transactional
    public Integer updateDefaultAddr(Integer memberId) {
        logger.info("updateDefaultAddr 参数memberId:{}", memberId);
        return memberAddrDao.updateDefaultAddr(memberId);
    }

    @Override
    public Integer updateDafultAddrById(Integer id) {
        logger.info("updateDafultAddrById 参数id:{}", id);
        return memberAddrDao.updateDafultAddrById(id);
    }

}
