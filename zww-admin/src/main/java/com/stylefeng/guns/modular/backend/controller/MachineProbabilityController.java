package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.ChargeOrder;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.backend.service.ITDollService;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.MachineProbablityWarpper;
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
import com.stylefeng.guns.common.persistence.model.MachineProbability;
import com.stylefeng.guns.modular.backend.service.IMachineProbabilityService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 娃娃机概率列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-29 15:44:08
 */
@Controller
@RequestMapping("/machineProbability")
public class MachineProbabilityController extends BaseController {

    private String PREFIX = "/backend/machineProbability/";

    @Autowired
    private IMachineProbabilityService machineProbabilityService;

    @Autowired
    private ITDollService dollService;

    /**
     * 跳转到娃娃机概率列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "machineProbability.html";
    }

    /**
     * 跳转到添加娃娃机概率列表
     */
    @RequestMapping("/machineProbability_add")
    public String machineProbabilityAdd() {
        return PREFIX + "machineProbability_add.html";
    }

    /**
     * 跳转到修改娃娃机概率列表
     */
     
    @RequestMapping("/machineProbability_update/{machineProbabilityId}")
    public String machineProbabilityUpdate(@PathVariable Integer machineProbabilityId, Model model) {
        MachineProbability machineProbability = machineProbabilityService.selectById(machineProbabilityId);
        model.addAttribute("item",machineProbability);
        LogObjectHolder.me().set(machineProbability);
        return PREFIX + "machineProbability_edit.html";
    }

    /**
     * 获取娃娃机概率列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer dollId, String machainCode, String name) {
        Page<MachineProbability> page = new PageFactory<MachineProbability>().defaultPage();
        List<Map<String, Object>> result = machineProbabilityService.selectMachineProbability(page,dollId,machainCode,name);
        page.setRecords((List<MachineProbability>)new MachineProbablityWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增娃娃机概率列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MachineProbability machineProbability) {
        MachineProbability tDoll = machineProbabilityService.selectByDollId(machineProbability.getDollId());
        if(tDoll == null){
            machineProbability.setCreatedDate(new Date());
            machineProbability.setCreatedBy(ShiroKit.getUser().getId());
            machineProbability.setModifiedBy(ShiroKit.getUser().getId());
            machineProbability.setModifiedDate(new Date());
            machineProbabilityService.insert(machineProbability);
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 删除娃娃机概率列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer machineProbabilityId) {
        machineProbabilityService.deleteById(machineProbabilityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改娃娃机概率列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MachineProbability machineProbability) {
        machineProbability.setModifiedBy(ShiroKit.getUser().getId());
        machineProbability.setModifiedDate(new Date());
        machineProbabilityService.updateById(machineProbability);
        return super.SUCCESS_TIP;
    }

    /**
     * 娃娃机概率列表详情
     */
     
    @RequestMapping(value = "/detail/{machineProbabilityId}")
    @ResponseBody
    public Object detail(@PathVariable("machineProbabilityId") Integer machineProbabilityId) {
        return machineProbabilityService.selectById(machineProbabilityId);
    }
}
