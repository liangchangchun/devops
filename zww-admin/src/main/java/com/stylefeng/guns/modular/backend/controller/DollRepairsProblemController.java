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
import com.stylefeng.guns.common.persistence.model.DollRepairsProblem;
import com.stylefeng.guns.modular.backend.service.IDollRepairsProblemService;

/**
 * 设置申诉问题列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-23 20:24:49
 */
@Controller
@RequestMapping("/dollRepairsProblem")
public class DollRepairsProblemController extends BaseController {

    private String PREFIX = "/backend/dollRepairsProblem/";

    @Autowired
    private IDollRepairsProblemService dollRepairsProblemService;

    /**
     * 跳转到设置申诉问题列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dollRepairsProblem.html";
    }

    /**
     * 跳转到添加设置申诉问题列表
     */
    @RequestMapping("/dollRepairsProblem_add")
    public String dollRepairsProblemAdd() {
        return PREFIX + "dollRepairsProblem_add.html";
    }

    /**
     * 跳转到修改设置申诉问题列表
     */
     
    @RequestMapping("/dollRepairsProblem_update/{dollRepairsProblemId}")
    public String dollRepairsProblemUpdate(@PathVariable Integer dollRepairsProblemId, Model model) {
        DollRepairsProblem dollRepairsProblem = dollRepairsProblemService.selectById(dollRepairsProblemId);
        model.addAttribute("item",dollRepairsProblem);
        LogObjectHolder.me().set(dollRepairsProblem);
        return PREFIX + "dollRepairsProblem_edit.html";
    }

    /**
     * 获取设置申诉问题列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return dollRepairsProblemService.selectList(null);
    }

    /**
     * 新增设置申诉问题列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DollRepairsProblem dollRepairsProblem) {
        dollRepairsProblemService.insert(dollRepairsProblem);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除设置申诉问题列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer dollRepairsProblemId) {
        dollRepairsProblemService.deleteById(dollRepairsProblemId);
        return SUCCESS_TIP;
    }

    /**
     * 修改设置申诉问题列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DollRepairsProblem dollRepairsProblem) {
        dollRepairsProblemService.updateById(dollRepairsProblem);
        return super.SUCCESS_TIP;
    }

    /**
     * 设置申诉问题列表详情
     */
     
    @RequestMapping(value = "/detail/{dollRepairsProblemId}")
    @ResponseBody
    public Object detail(@PathVariable("dollRepairsProblemId") Integer dollRepairsProblemId) {
        return dollRepairsProblemService.selectById(dollRepairsProblemId);
    }
}
