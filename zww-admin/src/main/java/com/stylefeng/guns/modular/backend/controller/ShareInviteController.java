package com.stylefeng.guns.modular.backend.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.mutidatasource.DSEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.Member;
import com.stylefeng.guns.common.persistence.model.ShareInvite;
import com.stylefeng.guns.modular.backend.service.IShareInviteService;
import com.stylefeng.guns.modular.backend.warpper.MemberWarpper;
import com.stylefeng.guns.modular.backend.warpper.ShareInviteWarpper;

/**
 * 邀请人列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-26 09:50:48
 */
@Controller
@RequestMapping("/shareInvite")
public class ShareInviteController extends BaseController {

    private String PREFIX = "/backend/shareInvite/";

    @Autowired
    private IShareInviteService shareInviteService;

    /**
     * 跳转到邀请人列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "shareInvite.html";
    }

    /**
     * 获取邀请人列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer inviteType, String startDate, String endDate, String memberid, String name) {
    	
   	 Page<ShareInvite> page = new PageFactory<ShareInvite>().defaultPage();
	 List<Map<String, Object>> result = shareInviteService.selectMember(page,inviteType,startDate, endDate, memberid, name);
	 page.setRecords((List<ShareInvite>)new ShareInviteWarpper(result).warp());
	 return super.packForBT(page);
    }

    /**
     * 邀请人列表详情
     */
     
    @RequestMapping(value = "/detail/{shareInviteId}")
    @ResponseBody
    public Object detail(@PathVariable("shareInviteId") Integer shareInviteId) {
        return shareInviteService.selectById(shareInviteId);
    }
}
