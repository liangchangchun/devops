package com.stylefeng.guns.modular.backend.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.mutidatasource.DSEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.DollAddress;
import com.stylefeng.guns.modular.backend.service.IDollAddressService;

/**
 * 娃娃机地址列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-24 21:41:02
 */
@Controller
@RequestMapping("/dollAddress")
public class DollAddressController extends BaseController {

    private String PREFIX = "/backend/dollAddress/";

    @Autowired
    private IDollAddressService dollAddressService;

    /**
     * 跳转到娃娃机地址列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dollAddress.html";
    }

    /**
     * 跳转到添加娃娃机地址列表
     */
    @RequestMapping("/dollAddress_add")
    public String dollAddressAdd() {
        return PREFIX + "dollAddress_add.html";
    }

    /**
     * 跳转到修改娃娃机地址列表
     */
     
    @RequestMapping("/dollAddress_update/{dollAddressId}")
    public String dollAddressUpdate(@PathVariable Integer dollAddressId, Model model) {
        DollAddress dollAddress = dollAddressService.selectById(dollAddressId);
        model.addAttribute("item",dollAddress);
        LogObjectHolder.me().set(dollAddress);
        return PREFIX + "dollAddress_edit.html";
    }

    /**
     * 获取娃娃机地址列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return dollAddressService.selectList(null);
    }

    /**
     * 新增娃娃机地址列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DollAddress dollAddress) {
    	dollAddress.setDefaultFlg(true);
    	dollAddress.setCreatedDate(new Date());
   	 	dollAddress.setModifiedDate(new Date());
        dollAddressService.insert(dollAddress);
        return SUCCESS_TIP;
    }

    /**
     * 删除娃娃机地址列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer dollAddressId) {
       // dollAddressService.deleteById(dollAddressId);
    	 DollAddress dollAddress = dollAddressService.selectById(dollAddressId);
    	 dollAddress.setDefaultFlg(false);
    	 dollAddress.setModifiedDate(new Date());
    	dollAddressService.insertOrUpdate(dollAddress);
        return SUCCESS_TIP;
    }

    /**
     * 修改娃娃机地址列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DollAddress dollAddress) {
    	 DollAddress old = dollAddressService.selectById(dollAddress.getId());
    	 dollAddress.setCreatedDate(old.getCreatedDate());
    	 dollAddress.setModifiedDate(new Date());
        dollAddressService.updateById(dollAddress);
        return SUCCESS_TIP;
    }

    /**
     * 娃娃机地址列表详情
     */
     
    @RequestMapping(value = "/detail/{dollAddressId}")
    @ResponseBody
    public Object detail(@PathVariable("dollAddressId") Integer dollAddressId) {
        return dollAddressService.selectById(dollAddressId);
    }
}
