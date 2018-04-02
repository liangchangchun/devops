package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.ThirdpartyWarpper;
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
import com.stylefeng.guns.common.persistence.model.Thirdparty;
import com.stylefeng.guns.modular.backend.service.IThirdpartyService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 第三方接入控制器
 *
 * @author fengshuonan
 * @Date 2018-03-26 10:58:24
 */
@Controller
@RequestMapping("/thirdparty")
public class ThirdpartyController extends BaseController {

    private String PREFIX = "/backend/thirdparty/";

    @Autowired
    private IThirdpartyService thirdpartyService;

    /**
     * 跳转到第三方接入首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "thirdparty.html";
    }

    /**
     * 跳转到添加第三方接入
     */
    @RequestMapping("/thirdparty_add")
    public String thirdpartyAdd() {
        return PREFIX + "thirdparty_add.html";
    }

    /**
     * 跳转到修改第三方接入
     */
     
    @RequestMapping("/thirdparty_update/{thirdpartyId}")
    public String thirdpartyUpdate(@PathVariable Integer thirdpartyId, Model model) {
        Thirdparty thirdparty = thirdpartyService.selectById(thirdpartyId);
        model.addAttribute("item",thirdparty);
        LogObjectHolder.me().set(thirdparty);
        return PREFIX + "thirdparty_edit.html";
    }

    /**
     * 获取第三方接入列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<Thirdparty> page = new PageFactory<Thirdparty>().defaultPage();
        List<Map<String, Object>> result = thirdpartyService.selectLists(page,condition);
        page.setRecords((List<Thirdparty>)new ThirdpartyWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增第三方接入
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Thirdparty thirdparty) {
        thirdparty.setCreatedDate(new Date());
        thirdparty.setModifiedDate(new Date());
        thirdpartyService.insert(thirdparty);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除第三方接入
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer thirdpartyId) {
        thirdpartyService.deleteById(thirdpartyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改第三方接入
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Thirdparty thirdparty) {
        thirdparty.setCreatedDate(new Date());
        thirdparty.setModifiedDate(new Date());
        thirdpartyService.updateById(thirdparty);
        return super.SUCCESS_TIP;
    }

    /**
     * 第三方接入详情
     */
     
    @RequestMapping(value = "/detail/{thirdpartyId}")
    @ResponseBody
    public Object detail(@PathVariable("thirdpartyId") Integer thirdpartyId) {
        return thirdpartyService.selectById(thirdpartyId);
    }
}
