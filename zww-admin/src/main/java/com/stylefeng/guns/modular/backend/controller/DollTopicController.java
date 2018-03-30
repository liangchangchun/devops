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
import com.stylefeng.guns.common.persistence.dao.TDollMapper;
import com.stylefeng.guns.common.persistence.model.DollTopic;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.common.persistence.model.TDollImage;
import com.stylefeng.guns.modular.backend.service.IDollTopicService;
import com.stylefeng.guns.modular.backend.warpper.DollTopicWarpper;
import com.stylefeng.guns.modular.backend.warpper.TDollImageWarpper;

/**
 * 娃娃机主题列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-24 21:39:55
 */
@Controller
@RequestMapping("/dollTopic")
public class DollTopicController extends BaseController {

    private String PREFIX = "/backend/dollTopic/";

    @Autowired
    private IDollTopicService dollTopicService;
    
    @Autowired
	TDollMapper tDollMapper;

    /**
     * 跳转到娃娃机主题列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dollTopic.html";
    }

    /**
     * 跳转到添加娃娃机主题列表
     */
    @RequestMapping("/dollTopic_add")
    public String dollTopicAdd() {
        return PREFIX + "dollTopic_add.html";
    }

    /**
     * 跳转到修改娃娃机主题列表
     */
     
    @RequestMapping("/dollTopic_update/{dollTopicId}")
    public String dollTopicUpdate(@PathVariable Integer dollTopicId, Model model) {
        DollTopic dollTopic = dollTopicService.selectById(dollTopicId);
        model.addAttribute("item",dollTopic);
        LogObjectHolder.me().set(dollTopic);
        return PREFIX + "dollTopic_edit.html";
    }

    /**
     * 获取娃娃机主题列表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String topicName,Integer machineCode) {
       // return dollTopicService.selectList(null);
    	 Page<DollTopic> page = new PageFactory<DollTopic>().defaultPage();
    	 List<Map<String, Object>> result =  dollTopicService.getDollTopicList(page,topicName,machineCode);
    	 page.setRecords((List<DollTopic>)new DollTopicWarpper(result).warp());
       	 return super.packForBT(page);
    }

    /**
     * 新增娃娃机主题列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DollTopic dollTopic) {
        DollTopic dollTopics = dollTopicService.selectLists(dollTopic);
        if (dollTopics == null){
            TDoll dollmachine = tDollMapper.selectById(dollTopic.getDollId());
            dollTopic.setDollName(dollmachine.getName());
            Integer type = dollTopicService.selectByTopicName(dollTopic.getTopicName());
            //System.out.println("type="+type);
            if (type!=null) {
                dollTopic.setTopicType(type);
            } else {
                Integer maxType = dollTopicService.getMaxTopicType();
                maxType = maxType==null?0:maxType;
                dollTopic.setTopicType(maxType+1);
            }
            dollTopicService.insert(dollTopic);
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除娃娃机主题列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer dollTopicId) {
        dollTopicService.deleteById(dollTopicId);
        return SUCCESS_TIP;
    }

    /**
     * 修改娃娃机主题列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DollTopic dollTopic) {
    	TDoll dollmachine = tDollMapper.selectById(dollTopic.getDollId());
    	dollTopic.setDollName(dollmachine.getName());
    	Integer type = dollTopicService.selectByTopicName(dollTopic.getTopicName());
    	//System.out.println("type="+type);
    	if (type!=null) {
    		dollTopic.setTopicType(type);
    	} else {
    		Integer maxType = dollTopicService.getMaxTopicType();
    		maxType = maxType==null?0:maxType;
    		dollTopic.setTopicType(maxType+1);
    	}
        dollTopicService.updateById(dollTopic);
        return SUCCESS_TIP;
    }

    /**
     * 娃娃机主题列表详情
     */
     
    @RequestMapping(value = "/detail/{dollTopicId}")
    @ResponseBody
    public Object detail(@PathVariable("dollTopicId") Integer dollTopicId) {
        return dollTopicService.selectById(dollTopicId);
    }
}
