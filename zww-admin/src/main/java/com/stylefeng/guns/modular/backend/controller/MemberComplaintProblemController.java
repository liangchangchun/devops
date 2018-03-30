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
import com.stylefeng.guns.common.persistence.model.MemberComplaintProblem;
import com.stylefeng.guns.modular.backend.service.IMemberComplaintProblemService;

/**
 * 设置申诉问题列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-23 20:43:36
 */
@Controller
@RequestMapping("/memberComplaintProblem")
public class MemberComplaintProblemController extends BaseController {

    private String PREFIX = "/backend/memberComplaintProblem/";

    @Autowired
    private IMemberComplaintProblemService memberComplaintProblemService;

    /**
     * 跳转到设置申诉问题列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberComplaintProblem.html";
    }

    /**
     * 跳转到添加设置申诉问题列表
     */
    @RequestMapping("/memberComplaintProblem_add")
    public String memberComplaintProblemAdd() {
        return PREFIX + "memberComplaintProblem_add.html";
    }

    /**
     * 跳转到修改设置申诉问题列表
     */
     
    @RequestMapping("/memberComplaintProblem_update/{memberComplaintProblemId}")
    public String memberComplaintProblemUpdate(@PathVariable Integer memberComplaintProblemId, Model model) {
        MemberComplaintProblem memberComplaintProblem = memberComplaintProblemService.selectById(memberComplaintProblemId);
        model.addAttribute("item",memberComplaintProblem);
        LogObjectHolder.me().set(memberComplaintProblem);
        return PREFIX + "memberComplaintProblem_edit.html";
    }

    /**
     * 获取设置申诉问题列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return memberComplaintProblemService.selectList(null);
    }

    /**
     * 新增设置申诉问题列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MemberComplaintProblem memberComplaintProblem) {
        memberComplaintProblemService.insert(memberComplaintProblem);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除设置申诉问题列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberComplaintProblemId) {
        memberComplaintProblemService.deleteById(memberComplaintProblemId);
        return SUCCESS_TIP;
    }

    /**
     * 修改设置申诉问题列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MemberComplaintProblem memberComplaintProblem) {
        memberComplaintProblemService.updateById(memberComplaintProblem);
        return super.SUCCESS_TIP;
    }

    /**
     * 设置申诉问题列表详情
     */
     
    @RequestMapping(value = "/detail/{memberComplaintProblemId}")
    @ResponseBody
    public Object detail(@PathVariable("memberComplaintProblemId") Integer memberComplaintProblemId) {
        return memberComplaintProblemService.selectById(memberComplaintProblemId);
    }
}
