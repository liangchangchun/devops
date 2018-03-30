package com.stylefeng.guns.modular.backend.controller;

import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.persistence.model.TBanner;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.TipType;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.mutidatasource.DSEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.common.persistence.model.AlertInfo;
import com.stylefeng.guns.modular.backend.service.IAlertInfoService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

/**
 * 抓取弹窗列表控制器
 *
 * @author fengshuonan
 * @Date 2018-02-06 12:03:19
 */
@Controller
@RequestMapping("/alertInfo")
public class AlertInfoController extends BaseController {

    private String PREFIX = "/backend/alertInfo/";

    @Autowired
    private IAlertInfoService alertInfoService;

    @Resource
    GunsProperties gunsProperties;

    @Autowired
    AliyunService aliyunService;


    /**
     * 跳转到抓取弹窗列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "alertInfo.html";
    }

    /**
     * 跳转到添加抓取弹窗列表
     */
    @RequestMapping("/alertInfo_add")
    public String alertInfoAdd() {
        return PREFIX + "alertInfo_add.html";
    }


    /**
     * 跳转上传图片
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toUpload")
    public String toUpload(Integer id, Model model) {
        AlertInfo alertInfo = alertInfoService.selectById(id);
        model.addAttribute("item",alertInfo);
        return PREFIX + "alertInfo_upload.html";
    }


    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload/{alertInfoId}")
    @ResponseBody
    public Object upload(@RequestPart("file") MultipartFile picture, @PathVariable Integer alertInfoId) {
        String originalFileName = picture.getOriginalFilename();
        // 获取后缀
        String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")
                + 1);
        // 修改后完整的文件名称
        String fileKey = StringUtils.getRandomUUID();
        String NewFileKey = fileKey + "." + suffix;
        byte[] bytes;
        try {
            bytes = picture.getBytes();
            InputStream fileInputStream = new ByteArrayInputStream(bytes);
            if(!aliyunService.putFileStreamToOSS(NewFileKey, fileInputStream)) {
                return "0";
            }
            AlertInfo alertInfo = alertInfoService.selectById(alertInfoId);
            if(alertInfo.getAlertImgUrl()!=null&&alertInfo.getAlertImgUrl().equals("")==false&&alertInfo.getAlertImgUrl().length()>10)
            {
                String oldFileKey=alertInfo.getAlertImgUrl().substring(alertInfo.getAlertImgUrl().lastIndexOf("/")+1, alertInfo.getAlertImgUrl().lastIndexOf("."));
                //如果有则删除原来的头像
                if(!"".equals(oldFileKey) && oldFileKey!=null) {
                    // logger.info("删除广告: "+d.getId()+"原来的头像从阿里云OSS:"+ bukectName+"文件名:"+oldFileKey);
                    aliyunService.deleteFileFromOSS(oldFileKey);
                }
            }
            String newFileUrl = aliyunService.generatePresignedUrl(NewFileKey,1000000).toString();
            alertInfo.setAlertImgUrl(newFileUrl);
            alertInfo.setModifiedBy(ShiroKit.getUser().getId());
            alertInfo.setModifiedDate(new Date());
            //logger.info("更新广告头像结果:{}",newFileUrl);
            boolean result = alertInfoService.insertOrUpdate(alertInfo);
            // logger.info("更新广告头像结果:{}",result>0?"success":"fail");
            if(result) {
                return SUCCESS_TIP;
            }else {
                return new ErrorTip(TipType.UPLOAD_ERROR.getCode(),TipType.UPLOAD_ERROR.getMessage());
            }
        } catch (Exception e) {
            throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
        }
    }

    /**
     * 跳转到修改banner
     */
    @RequestMapping("/editUrl")
    @ResponseBody
    public Object EditUrl(Integer id,String alertUrl, Model model) {
        AlertInfo alertInfo = alertInfoService.selectById(id);
        alertInfo.setAlertImgUrl(alertUrl);
        alertInfoService.insertOrUpdate(alertInfo);
        return SUCCESS_TIP;
    }


    /**
     * 跳转到修改抓取弹窗列表
     */
     
    @RequestMapping("/alertInfo_update/{alertInfoId}")
    public String alertInfoUpdate(@PathVariable Integer alertInfoId, Model model) {
        AlertInfo alertInfo = alertInfoService.selectById(alertInfoId);
        model.addAttribute("item",alertInfo);
        LogObjectHolder.me().set(alertInfo);
        return PREFIX + "alertInfo_edit.html";
    }

    /**
     * 获取抓取弹窗列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return alertInfoService.selectList(null);
    }

    /**
     * 新增抓取弹窗列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AlertInfo alertInfo) {
        alertInfo.setCreatedBy(ShiroKit.getUser().getId());
        alertInfo.setCreatedDate(new Date());
        alertInfo.setModifiedDate(new Date());
        alertInfo.setModifiedBy(ShiroKit.getUser().getId());
        alertInfo.setAlertImgUrl("#");
        alertInfoService.insert(alertInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除抓取弹窗列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer alertInfoId) {
        alertInfoService.deleteById(alertInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改抓取弹窗列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AlertInfo alertInfo) {
        alertInfo.setModifiedDate(new Date());
        alertInfo.setModifiedBy(ShiroKit.getUser().getId());
        alertInfoService.updateById(alertInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 抓取弹窗列表详情
     */
     
    @RequestMapping(value = "/detail/{alertInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("alertInfoId") Integer alertInfoId) {
        return alertInfoService.selectById(alertInfoId);
    }
}
