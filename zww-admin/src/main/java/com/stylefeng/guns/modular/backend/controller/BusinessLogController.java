package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.stylefeng.guns.common.persistence.model.OperationLog;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.system.warpper.LogWarpper;
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
import com.stylefeng.guns.common.persistence.model.BusinessLog;
import com.stylefeng.guns.modular.backend.service.IBusinessLogService;

import java.util.List;
import java.util.Map;

/**
 * 日志管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-20 11:38:30
 */
@Controller
@RequestMapping("/businessLog")
public class BusinessLogController extends BaseController {

    private String PREFIX = "/backend/businessLog/";

    @Autowired
    private IBusinessLogService businessLogService;

    /**
     * 跳转到日志管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "businessLog.html";
    }

    /**
     * 跳转到添加日志管理
     */
    @RequestMapping("/businessLog_add")
    public String businessLogAdd() {
        return PREFIX + "businessLog_add.html";
    }

    /**
     * 跳转到修改日志管理
     */
     
    @RequestMapping("/businessLog_update/{businessLogId}")
    public String businessLogUpdate(@PathVariable Integer businessLogId, Model model) {
        BusinessLog businessLog = businessLogService.selectById(businessLogId);
        model.addAttribute("item",businessLog);
        LogObjectHolder.me().set(businessLog);
        return PREFIX + "businessLog_edit.html";
    }

    /**
     * 查询操作日志详情
     */

    @RequestMapping("/detail/{id}")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object detail(@PathVariable Integer id) {
        BusinessLog businessLog = businessLogService.selectById(id);
        Map<String, Object> stringObjectMap = BeanKit.beanToMap(businessLog);
        return super.warpObject(new LogWarpper(stringObjectMap));
    }

    /**
     * 获取日志管理列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String logName,String beginTime, String endTime,String logType) {
        Page<BusinessLog> page = new PageFactory<BusinessLog>().defaultPage();
        List<Map<String, Object>> result = businessLogService.selectLists(page,logName,logType,beginTime,endTime);
        page.setRecords((List<BusinessLog>)new ChargeOrderWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增日志管理
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusinessLog businessLog) {
        businessLogService.insert(businessLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除日志管理
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer businessLogId) {
        businessLogService.deleteById(businessLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改日志管理
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusinessLog businessLog) {
        businessLogService.updateById(businessLog);
        return super.SUCCESS_TIP;
    }


}
