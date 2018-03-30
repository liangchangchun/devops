package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.dao.DollDivinationMapper;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.backend.warpper.DivinationTopicWarpper;
import com.stylefeng.guns.modular.backend.warpper.DollDivinationWarpper;
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
import com.stylefeng.guns.common.persistence.model.DollDivination;
import com.stylefeng.guns.modular.backend.service.IDollDivinationService;

import java.util.List;
import java.util.Map;

/**
 * 机器占卜主题控制器
 *
 * @author fengshuonan
 * @Date 2018-02-09 11:03:23
 */
@Controller
@RequestMapping("/dollDivination")
public class DollDivinationController extends BaseController {

    private String PREFIX = "/backend/dollDivination/";

    @Autowired
    private IDollDivinationService dollDivinationService;
    @Autowired
    private DollDivinationMapper dollDivinationMapper;
    /**
     * 跳转到机器占卜主题首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dollDivination.html";
    }

    /**
     * 跳转到添加机器占卜主题
     */
    @RequestMapping("/dollDivination_add")
    public String dollDivinationAdd() {
        return PREFIX + "dollDivination_add.html";
    }

    /**
     * 跳转到修改机器占卜主题
     */
     
    @RequestMapping("/dollDivination_update/{dollDivinationId}")
    public String dollDivinationUpdate(@PathVariable Integer dollDivinationId, Model model) {
        DollDivination dollDivination = dollDivinationMapper.selectById(dollDivinationId);
        model.addAttribute("item",dollDivination);
        LogObjectHolder.me().set(dollDivination);
        return PREFIX + "dollDivination_edit.html";
    }

    /**
     * 获取机器占卜主题列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<DollDivination> page = new PageFactory<DollDivination>().defaultPage();
        List<Map<String, Object>> result = dollDivinationService.selectLists(page,condition);
        page.setRecords((List<DollDivination>)new DollDivinationWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增机器占卜主题
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DollDivination dollDivination) {
        DollDivination divination = dollDivinationService.selectByDollId(dollDivination.getDollId());
        if(divination == null){
            dollDivinationService.insert(dollDivination);
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 删除机器占卜主题
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer dollDivinationId) {
        dollDivinationService.deleteById(dollDivinationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改机器占卜主题
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DollDivination dollDivination) {
        dollDivinationService.updateById(dollDivination);
        return super.SUCCESS_TIP;
    }

    /**
     * 机器占卜主题详情
     */
     
    @RequestMapping(value = "/detail/{dollDivinationId}")
    @ResponseBody
    public Object detail(@PathVariable("dollDivinationId") Integer dollDivinationId) {
        return dollDivinationService.selectById(dollDivinationId);
    }
}
