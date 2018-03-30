package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.constant.factory.ZwwContentFactory;
import com.stylefeng.guns.common.persistence.dao.ChargeOrderMapper;
import com.stylefeng.guns.common.persistence.model.Member;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.backend.service.IMemberService;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.MemberWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.mutidatasource.DSEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.common.persistence.model.ChargeOrder;
import com.stylefeng.guns.modular.backend.service.IChargeOrderService;

import java.util.List;
import java.util.Map;

/**
 * orderManage控制器
 *
 * @author fengshuonan
 * @Date 2018-01-02 21:36:29
 */
@Controller
@RequestMapping("/chargeOrder")
public class ChargeOrderController extends BaseController {

    private String PREFIX = "/backend/chargeOrder/";
    private String PREFIXS = "/backend/channel/";

    @Autowired
    private IChargeOrderService chargeOrderService;

    @Autowired
    private IMemberService iMemberService;
    /**
     * 跳转到orderManage首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "chargeOrder.html";
    }

    @RequestMapping("/channel")
    public String channel() {
        return PREFIXS + "chargeOrderChannel.html";
    }

    @RequestMapping("/xiaoyaojingChannel")
    public String xiaoyaojingChannel() {
        return PREFIXS + "xiaoyaojingChargeOrderChannel.html";
    }

    /**
     * 跳转到添加orderManage
     */
    @RequestMapping("/chargeOrder_add")
    public String chargeOrderAdd() {
        return PREFIX + "chargeOrder_add.html";
    }

    /**
     * 跳转到修改orderManage
     */
   
    @RequestMapping("/chargeOrder_update/{chargeOrderId}")
    public String chargeOrderUpdate(@PathVariable Integer chargeOrderId, Model model) {
        ChargeOrder chargeOrder = chargeOrderService.selectById(chargeOrderId);
        model.addAttribute("item",chargeOrder);
        LogObjectHolder.me().set(chargeOrder);
        return PREFIX + "chargeOrder_edit.html";
    }

    /**
     * 获取orderManage列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String memberName,String memberId,Integer chargeruleid,Integer chargeState,String registeDate,String endtime) {
        Integer userId = null;
        if(memberId != null && !"".equals(memberId)){
            Member member = iMemberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
        Page<ChargeOrder> page = new PageFactory<ChargeOrder>().defaultPage();
        List<Map<String, Object>> result = chargeOrderService.selectList(page,memberName,userId,chargeruleid,chargeState,registeDate,endtime);
        page.setRecords((List<ChargeOrder>)new ChargeOrderWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 获取渠道列表
     */
    @RequestMapping(value = "/channelList")
    @ResponseBody
    public Object channelList(String channelNum, String lastLoginFrom,String memberName,String memberId,Integer chargeruleid,Integer chargeState,String registeDate,String endtime) {
        Integer userId = null;
        if(memberId != null && !"".equals(memberId)){
            Member member = iMemberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
        Page<ChargeOrder> page = new PageFactory<ChargeOrder>().defaultPage();
        List<Map<String, Object>> result = chargeOrderService.selectListChannel(page,channelNum,lastLoginFrom,memberName,userId,chargeruleid,chargeState,registeDate,endtime);
        page.setRecords((List<ChargeOrder>)new ChargeOrderWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 获取小妖精渠道列表
     */
    @RequestMapping(value = "/xiaoyaojingChannelList")
    @ResponseBody
    public Object xiaoyaojingChannelList(String channelNum, String lastLoginFrom,String memberName,String memberId,Integer chargeruleid,Integer chargeState,String registeDate,String endtime) {
        Integer userId = null;
        if(memberId != null && !"".equals(memberId)){
            Member member = iMemberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
        Page<ChargeOrder> page = new PageFactory<ChargeOrder>().defaultPage();
        List<Map<String, Object>> result = chargeOrderService.xiaoyaojingSelectListChannel(page,channelNum,lastLoginFrom,memberName,userId,chargeruleid,chargeState,registeDate,endtime);
        page.setRecords((List<ChargeOrder>)new ChargeOrderWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 小妖精渠道总金额orderManage
     */
    @RequestMapping(value = "/xiaoyaojignAllMoneyChannel")
    @ResponseBody
    public Object xiaoyaojignAllMoneyChannel(String channelNum, String lastLoginFrom, String memberName,String memberId,String comboNames,String chargeState,String registeDate,String endtime) {
        Double allMoney = chargeOrderService.xiaoyaojingSelectAllMoneyChannel(channelNum,lastLoginFrom,memberName,memberId,comboNames,chargeState,registeDate,endtime);
        if(allMoney == null){
            allMoney = 0.0;
        }
        return allMoney;
    }

    /**
     * 小妖精渠道总人数orderManage
     */
    @RequestMapping(value = "/xiaoyaojignAllPersonChannel")
    @ResponseBody
    public Object xiaoyaojignAllPersonChannel(String channelNum, String lastLoginFrom,String memberName,String memberId,String comboNames,String chargeState,String registeDate,String endtime) {
        Integer allPerson = chargeOrderService.xiaoyaojingSelectAllPersonChannel(channelNum,lastLoginFrom,memberName,memberId,comboNames,chargeState,registeDate,endtime);
        if(allPerson == null){
            allPerson = 0;
        }
        return allPerson;
    }

    /**
     * 渠道总金额orderManage
     */
    @RequestMapping(value = "/allMoneyChannel")
    @ResponseBody
    public Object allMoneyChannel(String channelNum, String lastLoginFrom, String memberName,String memberId,String comboNames,String chargeState,String registeDate,String endtime) {
        Double allMoney = chargeOrderService.selectAllMoneyChannel(channelNum,lastLoginFrom,memberName,memberId,comboNames,chargeState,registeDate,endtime);
        if(allMoney == null){
            allMoney = 0.0;
        }
        return allMoney;
    }

    /**
     * 渠道总人数orderManage
     */
    @RequestMapping(value = "/allPersonChannel")
    @ResponseBody
    public Object allPersonChannel(String channelNum, String lastLoginFrom,String memberName,String memberId,String comboNames,String chargeState,String registeDate,String endtime) {
        Integer allPerson = chargeOrderService.selectAllPersonChannel(channelNum,lastLoginFrom,memberName,memberId,comboNames,chargeState,registeDate,endtime);
        if(allPerson == null){
            allPerson = 0;
        }
        return allPerson;
    }


    /**
     * 新增orderManage
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ChargeOrder chargeOrder) {
        chargeOrderService.insert(chargeOrder);
        return super.SUCCESS_TIP;
    }

    /**
     * 总金额orderManage
     */
    @RequestMapping(value = "/allMoney")
    @ResponseBody
    public Object allMoney(String memberName,String memberId,String comboNames,String chargeState,String registeDate,String endtime) {
        Integer userId = null;
        if(memberId != null && (!"".equals(memberId))){
            Member member = iMemberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
        Double allMoney = chargeOrderService.selectAllMoney(memberName,userId,comboNames,chargeState,registeDate,endtime);
        if(allMoney == null){
            allMoney = 0.0;
        }
        return allMoney;
    }

    /**
     * 总人数orderManage
     */
    @RequestMapping(value = "/allPerson")
    @ResponseBody
    public Object allPerson(String memberName,String memberId,String comboNames,String chargeState,String registeDate,String endtime) {

        Integer userId = null;
        if(!"".equals(memberId) && memberId != null){
            Member member = iMemberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
        Integer allPerson = chargeOrderService.selectAllPerson(memberName,userId,comboNames,chargeState,registeDate,endtime);
        if(allPerson == null){
            allPerson = 0;
        }
        return allPerson;
    }

    /**
     * 删除orderManage
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer chargeOrderId) {
        chargeOrderService.deleteById(chargeOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改orderManage
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ChargeOrder chargeOrder) {
        chargeOrderService.updateById(chargeOrder);
        return super.SUCCESS_TIP;
    }

    /**
     * orderManage详情
     */
    @RequestMapping(value = "/detail/{chargeOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("chargeOrderId") Integer chargeOrderId) {
        return chargeOrderService.selectById(chargeOrderId);
    }
}
