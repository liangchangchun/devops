package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.ChargeOrder;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.TChargeRulesWarpper;
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
import com.stylefeng.guns.common.persistence.model.TChargeRules;
import com.stylefeng.guns.modular.backend.service.ITChargeRulesService;

import java.util.List;
import java.util.Map;

/**
 * 充值规则列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-17 20:12:56
 */
@Controller
@RequestMapping("/tChargeRules")
public class TChargeRulesController extends BaseController {

    private String PREFIX = "/backend/tChargeRules/";

    @Autowired
    private ITChargeRulesService tChargeRulesService;

    /**
     * 跳转到充值规则列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tChargeRules.html";
    }

    /**
     * 跳转到添加充值规则列表
     */
    @RequestMapping("/tChargeRules_add")
    public String tChargeRulesAdd() {
        return PREFIX + "tChargeRules_add.html";
    }

    /**
     * 跳转到修改充值规则列表
     */
     
    @RequestMapping("/tChargeRules_update/{tChargeRulesId}")
    public String tChargeRulesUpdate(@PathVariable Integer tChargeRulesId, Model model) {
        TChargeRules tChargeRules = tChargeRulesService.selectById(tChargeRulesId);
        model.addAttribute("item",tChargeRules);
        LogObjectHolder.me().set(tChargeRules);
        return PREFIX + "tChargeRules_edit.html";
    }

    /**
     * 获取充值规则列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<TChargeRules> page = new PageFactory<TChargeRules>().defaultPage();
        List<Map<String, Object>> result = tChargeRulesService.selectRulesList(page);
        page.setRecords((List<TChargeRules>)new TChargeRulesWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增充值规则列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TChargeRules tChargeRules) {
        tChargeRulesService.insert(tChargeRules);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除充值规则列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tChargeRulesId) {
        tChargeRulesService.deleteById(tChargeRulesId);
        return SUCCESS_TIP;
    }

    /**
     * 修改充值规则列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TChargeRules tChargeRules) {
        tChargeRulesService.updateById(tChargeRules);
        return super.SUCCESS_TIP;
    }

    /**
     * 充值规则列表详情
     */
     
    @RequestMapping(value = "/detail/{tChargeRulesId}")
    @ResponseBody
    public Object detail(@PathVariable("tChargeRulesId") Integer tChargeRulesId) {
        return tChargeRulesService.selectById(tChargeRulesId);
    }
}
