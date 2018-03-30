package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.stylefeng.guns.common.persistence.model.Member;
import com.stylefeng.guns.common.persistence.model.TDollImage;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.MemberChannelDeductWarpper;
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
import com.stylefeng.guns.common.persistence.model.MemberChannelDeduct;
import com.stylefeng.guns.modular.backend.service.IMemberChannelDeductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 渠道扣量控制器
 *
 * @author fengshuonan
 * @Date 2018-03-02 16:45:56
 */
@Controller
@RequestMapping("/memberChannelDeduct")
public class MemberChannelDeductController extends BaseController {

    private String PREFIX = "/backend/memberChannelDeduct/";

    @Autowired
    private IMemberChannelDeductService memberChannelDeductService;

    /**
     * 跳转到渠道扣量首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberChannelDeduct.html";
    }



    /**
     * 获取渠道扣量列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String userId,String name, String registeDate, String endTime, String lastLoginFrom,String channelNum) {
        Page<MemberChannelDeduct> page = new PageFactory<MemberChannelDeduct>().defaultPage();
        List<Map<String, Object>> result = memberChannelDeductService.selectLists(page,userId,name,registeDate,endTime,lastLoginFrom,channelNum);
        page.setRecords((List<MemberChannelDeduct>)new MemberChannelDeductWarpper(result).warp());
        return super.packForBT(page);
    }



    /**
     * 删除渠道扣量
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String memberChannelDeductId) {
        String[] ids = null;
        if(memberChannelDeductId != null) {
            ids = memberChannelDeductId.split(",");
        }
        //批量删除id的数据
        memberChannelDeductService.deleteAllById(ids);
        return SUCCESS_TIP;
    }



    /**
     * 渠道扣量详情
     */
     
    @RequestMapping(value = "/detail/{memberChannelDeductId}")
    @ResponseBody
    public Object detail(@PathVariable("memberChannelDeductId") Integer memberChannelDeductId) {
        return memberChannelDeductService.selectById(memberChannelDeductId);
    }
}
