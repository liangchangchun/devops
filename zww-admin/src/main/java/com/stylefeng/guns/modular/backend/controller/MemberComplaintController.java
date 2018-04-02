package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.DollRepairs;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.backend.warpper.DollRepairsWarpper;
import com.stylefeng.guns.modular.backend.warpper.MemberComplainWarpper;
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
import com.stylefeng.guns.common.persistence.model.MemberComplaint;
import com.stylefeng.guns.modular.backend.service.IMemberComplaintService;

import java.util.List;
import java.util.Map;

/**
 * 待处理申诉控制器
 *
 * @author fengshuonan
 * @Date 2018-01-25 11:53:34
 */
@Controller
@RequestMapping("/memberComplaint")
public class MemberComplaintController extends BaseController {

    private String PREFIX = "/backend/memberComplaint/";

    @Autowired
    private IMemberComplaintService memberComplaintService;

    /**
     * 跳转到待处理申诉首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberComplaint.html";
    }


    /**
     * 跳转到已处理申诉首页
     */
    @RequestMapping("/done")
    public String indexDone() {
        return PREFIX + "memberComplaintDone.html";
    }


    /**
     * 跳转到视频
     */
    @RequestMapping("/video")
    public String video(String id, Model model) {
        model.addAttribute("videoUrl",id);
        return PREFIX + "video.html";
    }


    /**
     * 跳转到待处理申诉-通过
     */
    @RequestMapping("/memberComplaint_add/{memberComplaintId}")
    public String memberComplaintAdd(@PathVariable Integer memberComplaintId, Model model) {
        model.addAttribute("item",memberComplaintId);
        return PREFIX + "memberComplaint_pass.html";
    }

    /**
     * 跳转到待处理申诉-驳回
     */
    @RequestMapping("/memberComplaint_update/{memberComplaintId}")
    public String memberComplaintUpdate(@PathVariable Integer memberComplaintId, Model model) {
        model.addAttribute("item",memberComplaintId);
        return PREFIX + "memberComplaint_nopass.html";
    }

    /**
     * 获取待处理申诉列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String memberID, String channelNum,String catchStates,Double vipGroups) {
        Page<MemberComplaint> page = new PageFactory<MemberComplaint>().defaultPage();
        List<Map<String, Object>> result = memberComplaintService.selectMemberComplain(page,memberID,channelNum,catchStates,vipGroups);
        page.setRecords((List<MemberComplaint>)new MemberComplainWarpper(result).warp());
        return super.packForBT(page);
    }


    /**
     * 获取已处理申诉列表
     */

    @RequestMapping(value = "/doneList")
    @ResponseBody
    public Object doneList(String memberID, String channelNum,String catchStates,Integer checkState) {
        Page<MemberComplaint> page = new PageFactory<MemberComplaint>().defaultPage();
        List<Map<String, Object>> result = memberComplaintService.selectMemberComplainDone(page,memberID,channelNum,catchStates,checkState);
        page.setRecords((List<MemberComplaint>)new MemberComplainWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 待处理申诉
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MemberComplaint memberComplaint, Integer backStatus) {
        memberComplaintService.complaintBack(memberComplaint, backStatus);
        return super.SUCCESS_TIP;
    }



    /**
     * 驳回待处理申诉
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MemberComplaint memberComplaint, Integer backStatus) {
        memberComplaintService.complaintBack(memberComplaint,backStatus);
        return super.SUCCESS_TIP;
    }

    /**
     * 待处理申诉详情
     */
     
    @RequestMapping(value = "/detail/{memberComplaintId}")
    @ResponseBody
    public Object detail(@PathVariable("memberComplaintId") Integer memberComplaintId) {
        return memberComplaintService.selectById(memberComplaintId);
    }
}
