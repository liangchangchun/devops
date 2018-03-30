package com.zww.account.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zww.account.service.MemberAddrService;
import com.zww.api.model.MemberAddr;
import com.zww.api.service.ValidateTokenService;
import com.zww.api.util.IcraneResult;
import com.zww.common.Enviroment;


/**
 * Author: perry
 * Version: 1.0
 * Date: 2017/09/25
 * Description: 收货地址控制层.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
@RestController
@RequestMapping("/addr")
@CrossOrigin
public class MemberAddrController {
    private static final Logger logger = LoggerFactory.getLogger(MemberAddrController.class);
    @Autowired
    private ValidateTokenService validateTokenService;
    @Autowired
    private MemberAddrService memberAddrService;

    /**
     * 新增地址
     *
     * @param addr
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertAddr", method = RequestMethod.POST)
    @ResponseBody
    public IcraneResult insertAddr(MemberAddr addr, String token) throws Exception {
        logger.info("新增地址接口参数addr=" + addr + "," + "token=" + token);
        try {
            boolean isToken = validateTokenService.validataToken(token, addr.getMemberId());
            if (isToken) {
                MemberAddr memberAddr = memberAddrService.insertAddr(addr);
                //logger.info("新增地址result=" + memberAddr);
                if (memberAddr.getId() != null) {
                    return IcraneResult.ok(memberAddr);
                } else {
                    return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_FAILE_MESSAGE);
                }
            } else {
                return IcraneResult.build(Enviroment.RETURN_FAILE, "400", "token已失效");
            }

        } catch (Exception e) {
            logger.error("新增地址出错", e);
            throw e;
        }
    }

    @RequestMapping(value = "/deleteAddr", method = RequestMethod.POST)
    @ResponseBody
    public IcraneResult deleteAddr(int id, Integer memberId, String token) throws Exception {
        logger.info("删除地址接口参数id=" + id + "," + "token=" + token);
        try {
            boolean isToken = validateTokenService.validataToken(token, memberId);
            if (isToken) {
                Integer result = memberAddrService.deleteByPrimaryKey(id);
                logger.info("删除地址result=" + result);
                if (result > 0) {
                    return IcraneResult.ok();
                } else {
                    return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_FAILE_MESSAGE);
                }
            } else {
                return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_UNAUTHORIZED_CODE1, Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
            }
        } catch (Exception e) {
            logger.error("删除地址出错", e);
            throw e;
        }
    }

    @RequestMapping(value = "/findAddr", method = RequestMethod.POST)
    @ResponseBody
    public IcraneResult findAddr(int memberId, String token) throws Exception {
        logger.info("获取地址接口参数memberId=" + memberId + "," + "token=" + token);
        try {
            boolean isToken = validateTokenService.validataToken(token, memberId);
            if (isToken) {
                List<MemberAddr> list = memberAddrService.findByMemberId(memberId);
                if (list != null && list.size() > 0) {
                    return IcraneResult.ok(list);
                } else {
                    return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_FAILE_MESSAGE);
                }
            } else {
                return IcraneResult.build(Enviroment.RETURN_FAILE, "400", "token已失效");
            }
        } catch (Exception e) {
            logger.error("根据用户id获取地址出错", e);
            throw e;
        }
    }

    @RequestMapping(value = "/findAddrById", method = RequestMethod.POST)
    @ResponseBody
    public IcraneResult findAddrById(int id, Integer memberId, String token) throws Exception {
        logger.info("获取地址byId接口参数id=" + id + "," + "token=" + token);
        try {
            boolean isToken = validateTokenService.validataToken(token, memberId);
            if (isToken) {
                MemberAddr result = memberAddrService.selectByPrimaryKey(id);
                logger.info("获取地址result=" + result);
                if (result != null) {
                    return IcraneResult.ok(result);
                } else {
                    return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_FAILE_MESSAGE);
                }
            } else {
                return IcraneResult.build(Enviroment.RETURN_FAILE, "400", "token已失效");
            }
        } catch (Exception e) {
            logger.error("获取地址出错", e);
            throw e;
        }
    }

    @RequestMapping(value = "/updateAddr", method = RequestMethod.POST)
    @ResponseBody
    public IcraneResult updateAddr(MemberAddr addr, String token) throws Exception {
        logger.info("修改地址接口参数addr=" + addr + "," + "token=" + token);
        try {
            boolean isToken = validateTokenService.validataToken(token, addr.getMemberId());
            if (isToken) {
                Integer result = memberAddrService.updateByPrimaryKeySelective(addr);
                logger.info("修改地址result=" + result);
                if (result > 0) {
                    return IcraneResult.ok();
                } else {
                    return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_FAILE_MESSAGE);
                }
            } else {
                return IcraneResult.build(Enviroment.RETURN_FAILE, "400", "token已失效");
            }
        } catch (Exception e) {
            logger.error("修改地址出错", e);
            throw e;
        }
    }

    @RequestMapping(value = "/setDafultAddr", method = RequestMethod.POST)
    @ResponseBody
    public IcraneResult setDafultAddr(MemberAddr addr, String token) throws Exception {
        logger.info("修改默认地址接口参数addr=" + addr + "," + "token=" + token);
        try {
            boolean isToken = validateTokenService.validataToken(token, addr.getMemberId());
            if (isToken) {
                Integer result = memberAddrService.updateDefaultAddr(addr.getMemberId());
                logger.info("updateDefaultAddr结果=" + result);
                if (result > 0) {
                    Integer update = memberAddrService.updateDafultAddrById(addr.getId());
                    logger.info("updateDafultAddrById结果=" + update);
                    if (update > 0) {
                        return IcraneResult.ok();
                    } else {
                        return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_FAILE_MESSAGE);
                    }
                } else {
                    return IcraneResult.build(Enviroment.RETURN_FAILE, Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_FAILE_MESSAGE);
                }
            } else {
                return IcraneResult.build(Enviroment.RETURN_FAILE, "400", "token已失效");
            }
        } catch (Exception e) {
            logger.error("设置默认地址出错", e);
            throw e;
        }
    }
}
