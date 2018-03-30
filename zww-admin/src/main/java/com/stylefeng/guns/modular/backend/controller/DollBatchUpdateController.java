package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.dao.DollBatchUpdateMapper;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.RedisKeyGenerator;
import com.stylefeng.guns.modular.backend.service.ITDollService;
import com.stylefeng.guns.modular.backend.service.impl.TDollServiceImpl;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.DollBatchUpdateWarpper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
import com.stylefeng.guns.common.persistence.model.DollBatchUpdate;
import com.stylefeng.guns.modular.backend.service.IDollBatchUpdateService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 娃娃机批量上下线控制器
 *
 * @author fengshuonan
 * @Date 2018-03-16 11:17:37
 */
@Controller
@RequestMapping("/dollBatchUpdate")
public class DollBatchUpdateController extends BaseController {

    private String PREFIX = "/backend/dollBatchUpdate/";

    @Autowired
    private IDollBatchUpdateService dollBatchUpdateService;

    @Autowired
    private DollBatchUpdateMapper dollBatchUpdateMapper;

    @Autowired
    private ITDollService dollService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;


    /**
     * 跳转到娃娃机批量上下线首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dollBatchUpdate.html";
    }

    /**
     * 跳转到添加娃娃机批量上下线
     */
    @RequestMapping("/dollBatchUpdate_add")
    public String dollBatchUpdateAdd() {
        return PREFIX + "dollBatchUpdate_add.html";
    }

    /**
     * 跳转到修改娃娃机批量上下线
     */
     
    @RequestMapping("/dollBatchUpdate_update/{dollBatchUpdateId}")
    public String dollBatchUpdateUpdate(@PathVariable Integer dollBatchUpdateId, Model model) {
        DollBatchUpdate dollBatchUpdate = dollBatchUpdateService.selectById(dollBatchUpdateId);
        model.addAttribute("item",dollBatchUpdate);
        LogObjectHolder.me().set(dollBatchUpdate);
        return PREFIX + "dollBatchUpdate_edit.html";
    }

    /**
     * 获取娃娃机批量上下线列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,String machineCode) {
        Page<DollBatchUpdate> page = new PageFactory<DollBatchUpdate>().defaultPage();
        List<Map<String, Object>> result = dollBatchUpdateService.selectLists(page,condition,machineCode);
        page.setRecords((List<DollBatchUpdate>)new DollBatchUpdateWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增娃娃机批量上下线
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DollBatchUpdate dollBatchUpdate) {
        DollBatchUpdate dollBatchUpdate1 = dollBatchUpdateMapper.selectByDollId(dollBatchUpdate.getDollId());
        if(dollBatchUpdate1 == null){
            dollBatchUpdateService.insert(dollBatchUpdate);
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 删除娃娃机批量上下线
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String dollBatchUpdateId) {
        String[] ids = null;
        //存放id集合
        List<String> idsString = new ArrayList<>();
        if(dollBatchUpdateId != null) {
            ids = dollBatchUpdateId.split(",");
        }
        for (String imgId : ids) {
            idsString.add(imgId);
        }
        dollBatchUpdateMapper.deleteAllById(idsString);
        return SUCCESS_TIP;
    }

    /**
     * 批量设置空闲
     */
    @RequestMapping(value = "/kongXian")
    @ResponseBody
    public Object kongXian(@RequestParam String dollBatchUpdateId) {
        String[] ids = null;
        //存放id集合
        List<String> idsString = new ArrayList<>();
        if(dollBatchUpdateId != null) {
            ids = dollBatchUpdateId.split(",");
        }
        for (String imgId : ids) {
            DollBatchUpdate dollBatchUpdate = dollBatchUpdateMapper.selectById(imgId);
            idsString.add(dollBatchUpdate.getDollId().toString());
        }
        Integer result = dollBatchUpdateMapper.updateStatus(idsString);
        if(result > 0){
            for (String dollId : idsString){
                TDoll tDoll = dollService.selectById(dollId);
                //修改redis状态
                valOpsStr.set(RedisKeyGenerator.getRoomStatusKey(tDoll.getId()), tDoll.getMachineStatus());
            }
        }
        return SUCCESS_TIP;
    }

    /**
     * 批量设置未上线
     */
    @RequestMapping(value = "/weiShangXian")
    @ResponseBody
    public Object weiShangXian(@RequestParam String dollBatchUpdateId) {
        String[] ids = null;
        //存放id集合
        List<String> idsString = new ArrayList<>();
        if(dollBatchUpdateId != null) {
            ids = dollBatchUpdateId.split(",");
        }
        for (String imgId : ids) {
            DollBatchUpdate dollBatchUpdate = dollBatchUpdateMapper.selectById(imgId);
            idsString.add(dollBatchUpdate.getDollId().toString());
        }
        Integer result = dollBatchUpdateMapper.updateStatusW(idsString);
        if(result > 0){
            for (String dollId : idsString){
                TDoll tDoll = dollService.selectById(dollId);
                //修改redis状态
                valOpsStr.set(RedisKeyGenerator.getRoomStatusKey(tDoll.getId()), tDoll.getMachineStatus());
            }
        }
        return SUCCESS_TIP;
    }

//    /**
//     * 修改娃娃机批量上下线
//     */
//
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public Object update(DollBatchUpdate dollBatchUpdate) {
//        dollBatchUpdateService.updateById(dollBatchUpdate);
//        return super.SUCCESS_TIP;
//    }

//    /**
//     * 娃娃机批量上下线详情
//     */
//
//    @RequestMapping(value = "/detail/{dollBatchUpdateId}")
//    @ResponseBody
//    public Object detail(@PathVariable("dollBatchUpdateId") Integer dollBatchUpdateId) {
//        return dollBatchUpdateService.selectById(dollBatchUpdateId);
//    }
}
