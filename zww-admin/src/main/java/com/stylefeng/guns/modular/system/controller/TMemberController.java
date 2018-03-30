package com.stylefeng.guns.modular.system.controller;

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
import com.stylefeng.guns.common.persistence.model.TMember;
import com.stylefeng.guns.modular.system.service.ITMemberService;

/**
 * member控制器
 *
 * @author fengshuonan
 * @Date 2018-01-02 19:41:27
 */
@Controller
@RequestMapping("/tMember")
public class TMemberController extends BaseController {

    private String PREFIX = "/system/tMember/";

    @Autowired
    private ITMemberService tMemberService;

    /**
     * 跳转到member首页
     */
     
    @RequestMapping("")
    public String index() {
        return PREFIX + "tMember.html";
    }

    /**
     * 跳转到添加member
     */
     
    @RequestMapping("/tMember_add")
    public String tMemberAdd() {
        return PREFIX + "tMember_add.html";
    }

    /**
     * 跳转到修改member
     */
     
    @RequestMapping("/tMember_update/{tMemberId}")
    public String tMemberUpdate(@PathVariable Integer tMemberId, Model model) {
        TMember tMember = tMemberService.selectById(tMemberId);
        model.addAttribute("item",tMember);
        LogObjectHolder.me().set(tMember);
        return PREFIX + "tMember_edit.html";
    }

    /**
     * 获取member列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return tMemberService.selectList(null);
    }

    /**
     * 新增member
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TMember tMember) {
        tMemberService.insert(tMember);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除member
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tMemberId) {
        tMemberService.deleteById(tMemberId);
        return SUCCESS_TIP;
    }

    /**
     * 修改member
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TMember tMember) {
        tMemberService.updateById(tMember);
        return super.SUCCESS_TIP;
    }

    /**
     * member详情
     */
     
    @RequestMapping(value = "/detail/{tMemberId}")
    @ResponseBody
    public Object detail(@PathVariable("tMemberId") Integer tMemberId) {
        return tMemberService.selectById(tMemberId);
    }
}
