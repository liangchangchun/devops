package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.dao.DollTopicMapper;
import com.stylefeng.guns.common.persistence.model.DollTopic;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.backend.warpper.TDollWarpper;
import com.stylefeng.guns.modular.backend.warpper.TopicInfoWarpper;
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
import com.stylefeng.guns.common.persistence.model.TopicInfo;
import com.stylefeng.guns.modular.backend.service.ITopicInfoService;

import java.util.List;
import java.util.Map;

/**
 * 娃娃机主题详情列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-30 11:20:11
 */
@Controller
@RequestMapping("/topicInfo")
public class TopicInfoController extends BaseController {

    @Autowired
    private DollTopicMapper dollTopicMapper;

    private String PREFIX = "/backend/topicInfo/";

    @Autowired
    private ITopicInfoService topicInfoService;

    /**
     * 跳转到娃娃机主题详情列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "topicInfo.html";
    }

    /**
     * 跳转到添加娃娃机主题详情列表
     */
    @RequestMapping("/topicInfo_add")
    public String topicInfoAdd() {
        return PREFIX + "topicInfo_add.html";
    }

    /**
     * 跳转到修改娃娃机主题详情列表
     */
     
    @RequestMapping("/topicInfo_update/{topicInfoId}")
    public String topicInfoUpdate(@PathVariable Integer topicInfoId, Model model) {
        TopicInfo topicInfo = topicInfoService.selectById(topicInfoId);
        model.addAttribute("item",topicInfo);
        LogObjectHolder.me().set(topicInfo);
        return PREFIX + "topicInfo_edit.html";
    }

    /**
     * 获取娃娃机主题详情列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<TopicInfo> page = new PageFactory<TopicInfo>().defaultPage();
        List<Map<String, Object>> result = topicInfoService.selectTopicInfo(page,condition);
        page.setRecords((List<TopicInfo>)new TopicInfoWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增娃娃机主题详情列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TopicInfo topicInfo) {
        String result = dollTopicMapper.selectByTopicType(topicInfo.getTopicType());
        topicInfo.setTopicName(result);
        topicInfoService.insert(topicInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除娃娃机主题详情列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer topicInfoId) {
        topicInfoService.deleteById(topicInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改娃娃机主题详情列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TopicInfo topicInfo) {
        String result = dollTopicMapper.selectByTopicType(topicInfo.getTopicType());
        topicInfo.setTopicName(result);
        topicInfoService.updateById(topicInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 娃娃机主题详情列表详情
     */
     
    @RequestMapping(value = "/detail/{topicInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("topicInfoId") Integer topicInfoId) {
        return topicInfoService.selectById(topicInfoId);
    }
}
