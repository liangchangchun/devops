package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.HttpClientUtil;

import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.core.util.StringUtils;
import com.stylefeng.guns.modular.backend.service.ITDollService;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.MachinePhysicalProbilityWarpper;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
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
import com.stylefeng.guns.common.persistence.model.MachinePhysicalProbability;
import com.stylefeng.guns.modular.backend.service.IMachinePhysicalProbabilityService;

import java.util.*;

/**
 * 机器物理概率控制器
 *
 * @author fengshuonan
 * @Date 2018-02-11 10:39:08
 */
@Controller
@RequestMapping("/machinePhysicalProbability")
public class MachinePhysicalProbabilityController extends BaseController {

    private String PREFIX = "/backend/machinePhysicalProbability/";

    @Autowired
    private IMachinePhysicalProbabilityService machinePhysicalProbabilityService;

    @Autowired
    private ITDollService dollService;

    //马甲网址
//    private String linkUrl = "http://106.15.202.148:2346";
    //线上网址
    private String linkUrl = "http://47.100.113.59:2346";

    /**
     * 跳转到机器物理概率首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "machinePhysicalProbability.html";
    }

    /**
     * 跳转到添加机器物理概率
     */
    @RequestMapping("/machinePhysicalProbability_add")
    public String machinePhysicalProbabilityAdd() {
        return PREFIX + "machinePhysicalProbability_add.html";
    }

    /**
     * 跳转到修改机器物理概率
     */
    @RequestMapping("/machinePhysicalProbability_update/{machinePhysicalProbabilityId}")
    public String machinePhysicalProbabilityUpdate(@PathVariable Integer machinePhysicalProbabilityId, Model model) {
        MachinePhysicalProbability machinePhysicalProbability = machinePhysicalProbabilityService.selectById(machinePhysicalProbabilityId);
        model.addAttribute("item",machinePhysicalProbability);
        LogObjectHolder.me().set(machinePhysicalProbability);
        return PREFIX + "machinePhysicalProbability_edit.html";
    }

    /**
     * 获取机器物理概率列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer dollId,String machainCode,String name) {
        Page<MachinePhysicalProbability> page = new PageFactory<MachinePhysicalProbability>().defaultPage();
        List<Map<String, Object>> result = machinePhysicalProbabilityService.selectLists(page, dollId, name, machainCode);
        page.setRecords((List<MachinePhysicalProbability>)new MachinePhysicalProbilityWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增机器物理概率
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MachinePhysicalProbability machinePhysicalProbability) {
        MachinePhysicalProbability tDoll = machinePhysicalProbabilityService.selectByDollId(machinePhysicalProbability.getDollId());
        if(tDoll == null){
            machinePhysicalProbability.setCreatedDate(new Date());
            machinePhysicalProbability.setCreatedBy(ShiroKit.getUser().getId());
            machinePhysicalProbability.setModifiedDate(new Date());
            machinePhysicalProbability.setModifiedBy(ShiroKit.getUser().getId());
            try {
                //调用机器
                String result = linkMachine(machinePhysicalProbability);
                if("ok".equals(result)){
                    machinePhysicalProbabilityService.insert(machinePhysicalProbability);
                }else{
                    return "链接机器失败";
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            }
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 删除机器物理概率
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer machinePhysicalProbabilityId) {
        machinePhysicalProbabilityService.deleteById(machinePhysicalProbabilityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改机器物理概率
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MachinePhysicalProbability machinePhysicalProbability) {
        machinePhysicalProbability.setModifiedDate(new Date());
        machinePhysicalProbability.setModifiedBy(ShiroKit.getUser().getId());
        try {
            //调用机器
           String result = linkMachine(machinePhysicalProbability);
            if("ok".equals(result)){
                machinePhysicalProbabilityService.updateById(machinePhysicalProbability);
            } else {
                return "链接机器失败";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        return super.SUCCESS_TIP;
    }


    /**
     * http post调用机器指令**
     */
    public String linkMachine(MachinePhysicalProbability machinePhysicalProbability) throws ClientProtocolException {
        String littleKey = "";
        String machineUrl = "";
        TDoll doll = dollService.selectById(machinePhysicalProbability.getDollId());
        String setting = "{\"strongVoltage\":"+machinePhysicalProbability.getStrongVoltage()+
                ",\"weakOneVoltage\":"+machinePhysicalProbability.getWeakOneVoltage()+
                ",\"weakTwoVoltage\":"+machinePhysicalProbability.getWeakTwoVoltage()+
                ",\"strongTime\":"+machinePhysicalProbability.getStrongTime()+
                ",\"weakTime\":"+machinePhysicalProbability.getWeakTime()+"}"
                ;
        if(doll != null) {
            machineUrl = doll.getMachineUrl();
            //md5
            String keyss = MD5Util.MD5Encode(machineUrl+"|setting|62kq9FlIRF58G8lF", "UTF-8");
            littleKey = keyss.substring(0,10);
        }
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("device",String.valueOf(machineUrl)));
        params.add(new BasicNameValuePair("key",littleKey));
        params.add(new BasicNameValuePair("settings",setting));
        String result = HttpClientUtil.getInstance().executeByPOST(linkUrl, params);
        return result;
    }



    /**
     * 机器物理概率详情
     */
     
    @RequestMapping(value = "/detail/{machinePhysicalProbabilityId}")
    @ResponseBody
    public Object detail(@PathVariable("machinePhysicalProbabilityId") Integer machinePhysicalProbabilityId) {
        return machinePhysicalProbabilityService.selectById(machinePhysicalProbabilityId);
    }
}
