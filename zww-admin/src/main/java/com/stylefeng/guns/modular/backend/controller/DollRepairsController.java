package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.ChargeOrder;
import com.stylefeng.guns.common.persistence.model.Member;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.DollRepairsWarpper;
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
import com.stylefeng.guns.common.persistence.model.DollRepairs;
import com.stylefeng.guns.modular.backend.service.IDollRepairsService;

import java.util.List;
import java.util.Map;

/**
 * 报修列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-24 22:24:59
 */
@Controller
@RequestMapping("/dollRepairs")
public class DollRepairsController extends BaseController {

    private String PREFIX = "/backend/dollRepairs/";

    @Autowired
    private IDollRepairsService dollRepairsService;

    /**
     * 跳转到报修列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dollRepairs.html";
    }

    /**
     * 跳转到添加报修列表
     */
    @RequestMapping("/dollRepairs_add")
    public String dollRepairsAdd() {
        return PREFIX + "dollRepairs_add.html";
    }

    /**
     * 跳转到修改报修列表
     */
     
    @RequestMapping("/dollRepairs_update/{dollRepairsId}")
    public String dollRepairsUpdate(@PathVariable Integer dollRepairsId, Model model) {
        DollRepairs dollRepairs = dollRepairsService.selectById(dollRepairsId);
        model.addAttribute("item",dollRepairs);
        LogObjectHolder.me().set(dollRepairs);
        return PREFIX + "dollRepairs_edit.html";
    }

    /**
     * 获取报修列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
//        Integer userId = 0;
//        if(memberId != null){
//            Member member = memberService.selectIdByMemberId(memberId);
//            if(member != null){
//                userId = member.getId();
//            }
//        }
        Page<DollRepairs> page = new PageFactory<DollRepairs>().defaultPage();
        List<Map<String, Object>> result = dollRepairsService.selectDollRepairs(page);
        page.setRecords((List<DollRepairs>)new DollRepairsWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增报修列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DollRepairs dollRepairs) {
        dollRepairsService.insert(dollRepairs);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除报修列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer dollRepairsId) {
        dollRepairsService.deleteById(dollRepairsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改报修列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DollRepairs dollRepairs) {
        dollRepairsService.updateById(dollRepairs);
        return super.SUCCESS_TIP;
    }

    /**
     * 报修列表详情
     */
     
    @RequestMapping(value = "/detail/{dollRepairsId}")
    @ResponseBody
    public Object detail(@PathVariable("dollRepairsId") Integer dollRepairsId) {
        return dollRepairsService.selectById(dollRepairsId);
    }
}
