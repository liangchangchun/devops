package com.stylefeng.guns.modular.backend.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
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
import com.stylefeng.guns.common.persistence.model.MemberVip;
import com.stylefeng.guns.modular.backend.service.IMemberVipService;

/**
 * 设置vip等级权限控制器
 *
 * @author fengshuonan
 * @Date 2018-03-28 11:42:27
 */
@Controller
@RequestMapping("/memberVip")
public class MemberVipController extends BaseController {

    private String PREFIX = "/backend/memberVip/";

    @Autowired
    private IMemberVipService memberVipService;

    /**
     * 跳转到设置vip等级权限首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberVip.html";
    }

    /**
     * 跳转到添加设置vip等级权限
     */
    @RequestMapping("/memberVip_add")
    public String memberVipAdd() {
        return PREFIX + "memberVip_add.html";
    }

    /**
     * 跳转到修改设置vip等级权限
     */
     
    @RequestMapping("/memberVip_update/{memberVipId}")
    public String memberVipUpdate(@PathVariable Integer memberVipId, Model model) {
        MemberVip memberVip = memberVipService.selectById(memberVipId);
        model.addAttribute("item",memberVip);
        LogObjectHolder.me().set(memberVip);
        return PREFIX + "memberVip_edit.html";
    }

    /**
     * 获取设置vip等级权限列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return memberVipService.selectList(null);
    }

    /**
     * 新增设置vip等级权限
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MemberVip memberVip) {
        memberVipService.insert(memberVip);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除设置vip等级权限
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberVipId) {
        memberVipService.deleteByMemberVipId(memberVipId);
        return SUCCESS_TIP;
    }

    /**
     * 修改设置vip等级权限
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MemberVip memberVip) {
        memberVipService.updateById(memberVip);
        return super.SUCCESS_TIP;
    }

    /**
     * 设置vip等级权限详情
     */
     
    @RequestMapping(value = "/detail/{memberVipId}")
    @ResponseBody
    public Object detail(@PathVariable("memberVipId") Integer memberVipId) {
        return memberVipService.selectById(memberVipId);
    }
}
