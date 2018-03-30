package com.stylefeng.guns.modular.backend.controller;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.Member;
import com.stylefeng.guns.modular.backend.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.MemberChargeHistory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.backend.service.IMemberChargeHistoryService;
import com.stylefeng.guns.modular.backend.warpper.MemberChargeHistoryWarpper;

/**
 * 用户交易列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-03 13:59:04
 */
@Controller
@RequestMapping("/memberChargeHistory")
public class MemberChargeHistoryController extends BaseController {

    private String PREFIX = "/backend/memberChargeHistory/";

    @Autowired
    private IMemberChargeHistoryService memberChargeHistoryService;

    @Autowired
    private IMemberService iMemberService;
    /**
     * 跳转到用户交易列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberChargeHistory.html";
    }

    /**
     * 跳转到添加用户交易列表
     */
    @RequestMapping("/memberChargeHistory_add")
    public String memberChargeHistoryAdd() {
        return PREFIX + "memberChargeHistory_add.html";
    }

    /**
     * 跳转到修改用户交易列表
     */
     
    @RequestMapping("/memberChargeHistory_update/{memberChargeHistoryId}")
    public String memberChargeHistoryUpdate(@PathVariable Integer memberChargeHistoryId, Model model) {
        MemberChargeHistory memberChargeHistory = memberChargeHistoryService.selectById(memberChargeHistoryId);
        model.addAttribute("item",memberChargeHistory);
        LogObjectHolder.me().set(memberChargeHistory);
        return PREFIX + "memberChargeHistory_edit.html";
    }

    /**
     * 获取用户交易列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String memberId) {
        Integer userId = 0;
        if(memberId != null && !("".equals(memberId))){
            Member member = iMemberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
    	Page<MemberChargeHistory> page = new PageFactory<MemberChargeHistory>().defaultPage();
    	List<Map<String, Object>> result = memberChargeHistoryService.selectList(page,userId);
    	page.setRecords((List<MemberChargeHistory>)new MemberChargeHistoryWarpper(result).warp());
   	 	return super.packForBT(page);
    }

    /**
     * 新增用户交易列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MemberChargeHistory memberChargeHistory) {
        memberChargeHistoryService.insert(memberChargeHistory);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除用户交易列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberChargeHistoryId) {
        memberChargeHistoryService.deleteById(memberChargeHistoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户交易列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MemberChargeHistory memberChargeHistory) {
        memberChargeHistoryService.updateById(memberChargeHistory);
        return super.SUCCESS_TIP;
    }

    /**
     * 用户交易列表详情
     */
     
    @RequestMapping(value = "/detail/{memberChargeHistoryId}")
    @ResponseBody
    public Object detail(@PathVariable("memberChargeHistoryId") Integer memberChargeHistoryId) {
        return memberChargeHistoryService.selectById(memberChargeHistoryId);
    }
}
