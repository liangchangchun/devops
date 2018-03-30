package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.dao.MemberWhiteListMapper;
import com.stylefeng.guns.common.persistence.model.Member;
import com.stylefeng.guns.common.persistence.model.TDollCatchHistory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.backend.service.IMemberService;
import com.stylefeng.guns.modular.backend.warpper.DollCatchHistoryWarpper;
import com.stylefeng.guns.modular.backend.warpper.MemberWhiteListWarpper;
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
import com.stylefeng.guns.common.persistence.model.MemberWhiteList;
import com.stylefeng.guns.modular.backend.service.IMemberWhiteListService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户白名单列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-03 16:01:34
 */
@Controller
@RequestMapping("/memberWhiteList")
public class MemberWhiteListController extends BaseController {

    private String PREFIX = "/backend/memberWhiteList/";

    @Autowired
    private IMemberWhiteListService memberWhiteListService;

    @Autowired
    private IMemberService memberService;

    /**
     * 跳转到用户白名单列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberWhiteList.html";
    }

    /**
     * 跳转到添加用户白名单列表
     */
    @RequestMapping("/memberWhiteList_add")
    public String memberWhiteListAdd() {
        return PREFIX + "memberWhiteList_add.html";
    }

    /**
     * 跳转到修改用户白名单列表
     */
     
    @RequestMapping("/memberWhiteList_update/{memberWhiteListId}")
    public String memberWhiteListUpdate(@PathVariable Integer memberWhiteListId, Model model) {
        MemberWhiteList memberWhiteList = memberWhiteListService.selectById(memberWhiteListId);
        model.addAttribute("item",memberWhiteList);
        LogObjectHolder.me().set(memberWhiteList);
        return PREFIX + "memberWhiteList_edit.html";
    }

    /**
     * 获取用户白名单列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String memberId, String userName) {
        Page<MemberWhiteList> page = new PageFactory<MemberWhiteList>().defaultPage();
        List<Map<String, Object>> result = memberWhiteListService.selectLists(page, memberId, userName);
        page.setRecords((List<MemberWhiteList>)new MemberWhiteListWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增用户白名单列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MemberWhiteList memberWhiteList) {
        String memberNum = memberWhiteList.getMemberId();
        MemberWhiteList memberWhite = memberWhiteListService.selectIdByMemberId(memberNum);
        if(memberWhite == null){
            Member member = memberService.selectIdByMemberId(memberNum);
            memberWhiteList.setCreatedDate(new Date());
            memberWhiteList.setModifiedDate(new Date());
            memberWhiteList.setUserId(member.getId());
            memberWhiteList.setUserName(member.getName());
            memberWhiteListService.insert(memberWhiteList);
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 删除用户白名单列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberWhiteListId) {
        memberWhiteListService.deleteById(memberWhiteListId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户白名单列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MemberWhiteList memberWhiteList) {
        String memberNum = memberWhiteList.getMemberId();
        Member member = memberService.selectIdByMemberId(memberNum);
        memberWhiteList.setModifiedDate(new Date());
        memberWhiteList.setUserId(member.getId());
        memberWhiteList.setUserName(member.getName());
        memberWhiteListService.updateById(memberWhiteList);
        return super.SUCCESS_TIP;
    }

    /**
     * 用户白名单列表详情
     */
     
    @RequestMapping(value = "/detail/{memberWhiteListId}")
    @ResponseBody
    public Object detail(@PathVariable("memberWhiteListId") Integer memberWhiteListId) {
        return memberWhiteListService.selectById(memberWhiteListId);
    }
}
