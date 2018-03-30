package com.zww.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.stylefeng.guns.core.redis.RedisService;
import com.stylefeng.guns.game.GameProcessEnum;
import com.stylefeng.guns.game.GameProcessUtil;
import com.zww.api.model.DollOrder;
import com.zww.api.model.DollOrderItem;
import com.zww.api.service.BaseService;
import com.zww.api.service.ValidateTokenService;
import com.zww.api.util.StringUtils;
import com.zww.common.Enviroment;
import com.zww.common.ResultMap;
import com.zww.order.service.DollOrderService;


/**
 * Author: mwan Version: 1.1 Date: 2017/09/27 Description: 娃娃订单控制层. Copyright
 * (c) 2017 伴飞网络. All rights reserved.
 */
@RefreshScope
@RestController
@RequestMapping(value = "/doll/order")
@CrossOrigin
public class DollOrderController {

    private static final Logger logger = LoggerFactory.getLogger(DollOrderController.class);

    @Autowired
    ValidateTokenService validateTokenService;
    @Autowired
    DollOrderService dollOrderService;
    //@Autowired
    //MemberService memberService;
    @Autowired
    BaseService baseService;
    @Autowired
    RedisService redisUtil;

    // 获取娃娃订单详情
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getOrderById(HttpServletRequest request) throws Exception {
        logger.info("获取娃娃订单详情接口传入参数orderId=" + request.getParameter("orderId"));
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            //验证token有效性
            if (request.getParameter("token") == null ||
                    "".equals(request.getParameter("token")) ||
                    !validateTokenService.validataToken(request.getParameter("token"))) {
                resultMap.put("success", Enviroment.RETURN_FAILE);
                resultMap.put("statusCode", Enviroment.RETURN_UNAUTHORIZED_CODE);
                resultMap.put("message", Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
                return resultMap;
            }
            if (request.getParameter("orderId") == null || "".equals(request.getParameter("orderId"))) {
                resultMap.put("success", Enviroment.RETURN_FAILE);
                resultMap.put("statusCode", Enviroment.RETURN_FAILE_CODE);
                resultMap.put("message", Enviroment.RETURN_INVALID_PARA_MESSAGE);
                return resultMap;
            }

            DollOrder dollOrder = dollOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("orderId")));
            if (dollOrder != null) {
                resultMap.put("resultData", dollOrder);
                resultMap.put("success", Enviroment.RETURN_SUCCESS);
                resultMap.put("statusCode", Enviroment.RETURN_SUCCESS_CODE);
                resultMap.put("message", Enviroment.RETURN_SUCCESS_MESSAGE);
            } else if (dollOrder == null) {
                resultMap.put("success", Enviroment.RETURN_FAILE);
                resultMap.put("statusCode", Enviroment.RETURN_FAILE_CODE);
                resultMap.put("message", Enviroment.RETURN_FAILE_MESSAGE);
            }
            logger.info("获取娃娃订单详情resultMap=" + resultMap);
            return resultMap;
        } catch (Exception e) {
            logger.error("获取娃娃订单出错", e);
            throw e;
        }

    }

    /**
     * 抓取历史列表
     *
     * @param token       身份令牌
     * @param memberId    账号ID
     * @param orderStatus 历史类型
     * @return 抓取历史列表
     * @throws Exception
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap getOrderItemsByMemberId(String token, String memberId, String orderStatus) throws Exception {
        try {
            logger.info("获取娃娃订单详情token=" + token + ",memberId=" + memberId + ",orderStatus=" + orderStatus);
            if (memberId == null || "".equals(memberId) || token == null || "".equals(token)) {
                logger.info("获取娃娃订单详情异常=" + Enviroment.RETURN_INVALID_PARA_MESSAGE);
                return new ResultMap(Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_INVALID_PARA_MESSAGE);
            }
            //验证token有效性
            if (!validateTokenService.validataToken(token, Integer.parseInt(memberId))) {
                logger.info("获取娃娃订单详情异常=" + Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
                return new ResultMap(Enviroment.RETURN_FAILE_CODE, Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
            }
            List<DollOrderItem> dollOrderItems;
            if (StringUtils.isEmpty(orderStatus)) {
                dollOrderItems = dollOrderService.selectItemsByMemberId(Integer.parseInt(memberId));
            } else {
                dollOrderItems = dollOrderService.selectItemsByMemberIdOrderStatus(Integer.parseInt(memberId), orderStatus);
            }
            ResultMap resultMap = new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE);
            resultMap.setResultData(dollOrderItems);
            //logger.info("获取娃娃订单详情resultMap=" + resultMap);
            return resultMap;
        } catch (Exception e) {
            logger.error("获取娃娃订单详情出错", e);
            throw e;
        }

    }

    // 创建娃娃订单
    @RequestMapping(value = "/createDollOrder", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createDollOrder(Integer memberId, Integer dollId, Integer dollNum) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            //Integer num = 0;
            //num = Integer.parseInt(redisUtil.getString(RedisKeyGenerator.getMemberSetter(memberId))) + 1;
            //redisUtil.setString(RedisKeyGenerator.getMemberSetter(memberId), String.valueOf(num), 3600 * 2);
            //优化订单 下单 计数控制   控制代码集中
            Integer num = GameProcessUtil.getInstance(redisUtil).addCountGameLock(memberId, dollId, GameProcessEnum.GAME_SETTER);
            //结算计数
            Integer dollOrder = 0;
            if (num <= 1) {//只结算一次
                dollOrder = dollOrderService.insertOrder(memberId, dollId, dollNum);
            } else {
                dollOrder = 1;//不重复创建 寄存订单
            }
            if (dollOrder == 1) {
                resultMap.put("success", Enviroment.RETURN_SUCCESS);
                resultMap.put("statusCode", Enviroment.RETURN_SUCCESS_CODE);
                resultMap.put("message", Enviroment.RETURN_SUCCESS_MESSAGE);
            } else {
                resultMap.put("success", Enviroment.RETURN_FAILE);
                resultMap.put("statusCode", Enviroment.RETURN_FAILE_CODE);
                resultMap.put("message", Enviroment.RETURN_FAILE_MESSAGE);
            }
            return resultMap;
        } catch (Exception e) {
            logger.error("创建娃娃订单出错", e);
            throw e;
        }

    }

    /**
     * 申请发货
     *
     * @param memberId
     * @param orderIds
     * @param addrId
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendDoll", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap sendDoll(Integer memberId, Integer[] orderIds, Integer addrId, String token, String note) throws Exception {
        try {
            //验证参数
            if (memberId == null || StringUtils.isEmpty(token) || orderIds == null || orderIds.length < 1 || addrId == null || (note != null && note.length() > 255)) {
                logger.info("申请发货接口参数异常=" + Enviroment.RETURN_INVALID_PARA_MESSAGE);
                return new ResultMap(Enviroment.FAILE_CODE, Enviroment.RETURN_INVALID_PARA_MESSAGE);
            }
            //看看是不是VIP
            if (!baseService.isVIP(memberId)) {
                logger.info("申请发货接口参数异常=" + Enviroment.RETURN_INVALID_PARA_MESSAGE);
                return new ResultMap(Enviroment.NO_VIP_CODE, "系统提示：马上完成首充，就能发货哦！");
            }
            //看看是不是白名单
            if (baseService.isWorker(memberId) || memberId == 127051) {
                logger.info("申请发货接口参数异常=" + Enviroment.RETURN_INVALID_PARA_MESSAGE);
                return new ResultMap(Enviroment.FAILE_CODE, "白名单用户禁止发货");
            }
            //访问间隔限制
            //RedisUtil redisUtil = new RedisUtil();
            if (redisUtil.getString("sendDoll" + memberId) != null) {
                logger.info("分享得币失败=" + Enviroment.PLEASE_SLOW_DOWN);
                return new ResultMap(Enviroment.FAILE_CODE, Enviroment.PLEASE_SLOW_DOWN);
            }
            redisUtil.setString("sendDoll" + memberId, "", Enviroment.ACCESS_SENDDOLL_TIME *1L);
            // 验证token有效性
            if (!validateTokenService.validataToken(token, memberId)) {//官方账号不发货
                logger.info("申请发货失败:没有授权");
                return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.RETURN_UNAUTHORIZED_MESSAGE);
            }
            return dollOrderService.sendOrder(memberId, orderIds, addrId, note);
        } catch (Exception e) {
            logger.error("发货出错", e);
            throw e;
        }

    }

}
