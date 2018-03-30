package com.zww.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zww.account.service.AccountService;
import com.zww.api.model.Account;
import com.zww.api.service.RiskManagementService;
import com.zww.api.service.ValidateTokenService;
import com.zww.api.util.StringUtils;
import com.zww.common.Enviroment;
import com.zww.common.ResultMap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by SUN on 2018/1/10.
 * 用户账户控制器
 */
@RestController
@RequestMapping(value = "/account")
@CrossOrigin
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;
    @Autowired
    ValidateTokenService validateTokenService;
    @Autowired
    RiskManagementService riskManagementService;

    /**
     * 查询用户账户
     *
     * @param memberId
     * @param token
     * @return
     */
    @RequestMapping(value = "/accountDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap getAccount(Integer memberId, String token) {
        try {
            logger.info("用户账户接口参数:memberId=" + memberId + ",token=" + token);
            if (memberId == null || StringUtils.isEmpty(token)) {
                logger.info("用户账户接口参数异常=" + Enviroment.RETURN_INVALID_PARA_MESSAGE);
                return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE1, Enviroment.RETURN_INVALID_PARA_MESSAGE);
            }
            //验证token
            if (!validateTokenService.validataToken(token, memberId)) {
                logger.info("用户账户接口参数异常=" + Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
                return new ResultMap(Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
            }
            //return accountService.selectById(memberId);
            Account account = accountService.selectById(memberId);
            return new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE, account);
        } catch (Exception e) {
            logger.error("用户账户接口参数异常=" + e.getMessage());
            e.printStackTrace();
            return new ResultMap(Enviroment.ERROR_CODE, Enviroment.HAVE_ERROR);
        }
    }
    /**
     * 查询用户有效充值金额
     *
     * @param memberId
     * @param token
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap pay(Integer memberId, String token) {
        try {
            logger.info("用户账户接口参数:memberId=" + memberId + ",token=" + token);
            if (memberId == null || StringUtils.isEmpty(token)) {
                logger.info("用户账户接口参数异常=" + Enviroment.RETURN_INVALID_PARA_MESSAGE);
                return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE1, Enviroment.RETURN_INVALID_PARA_MESSAGE);
            }
            //验证token
            if (!validateTokenService.validataToken(token, memberId)) {
                logger.info("用户账户接口参数异常=" + Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
                return new ResultMap(Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
            }
            //return accountService.selectById(memberId);
            Account account = accountService.selectById(memberId);
            return new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE, account);
        } catch (Exception e) {
            logger.error("用户账户接口参数异常=" + e.getMessage());
            e.printStackTrace();
            return new ResultMap(Enviroment.ERROR_CODE, Enviroment.HAVE_ERROR);
        }
    }

    /*@RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Object text(Integer memberId1, Integer memberId2) {
        return new ResultMap("",riskManagementService.isOneIMEI(memberId1, memberId2));
    }*/

}
