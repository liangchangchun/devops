package com.stylefeng.guns.modular.backend.controller;

import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.persistence.model.TDollImage;
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
import com.stylefeng.guns.common.persistence.model.TAppVersion;
import com.stylefeng.guns.modular.backend.service.ITAppVersionService;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

/**
 * 版本管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-15 11:49:03
 */
@Controller
@RequestMapping("/tAppVersion")
public class TAppVersionController extends BaseController {

    private String PREFIX = "/backend/tAppVersion/";

    @Autowired
    private ITAppVersionService tAppVersionService;

    @Autowired
    AliyunService aliyunService;

    /**
     * 跳转到版本管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tAppVersion.html";
    }

    /**
     * 跳转到添加版本管理
     */
    @RequestMapping("/tAppVersion_add")
    public String tAppVersionAdd() {
        return PREFIX + "tAppVersion_add.html";
    }

    /**
     * 跳转到修改版本管理
     */
     
    @RequestMapping("/tAppVersion_update/{tAppVersionId}")
    public String tAppVersionUpdate(@PathVariable String tAppVersionId, Model model) {
        TAppVersion tAppVersion = tAppVersionService.selectByAppKey(tAppVersionId);
        model.addAttribute("item",tAppVersion);
        LogObjectHolder.me().set(tAppVersion);
        return PREFIX + "tAppVersion_edit.html";
    }

    //跳转到上传apk
    @RequestMapping("/uploadAPK/{sys}")
    public String toUpload(@PathVariable("sys") String sys, Model model) {
        model.addAttribute("sys",sys);
        return PREFIX + "tAppVersion_upload.html";
    }

    //上传apk
    @RequestMapping(method = RequestMethod.POST, path = "/upload/{sys}")
    @ResponseBody
    public Object upload(@RequestPart("file") MultipartFile picture, @PathVariable String sys) {
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
            String newFileUrl = aliyunService.generatePresignedUrl(NewFileKey,1000000).toString();
            TAppVersion tAppVersion = tAppVersionService.selectByAppKey(sys);
            if(tAppVersion.getUpgradeUrl()!=null&&tAppVersion.getUpgradeUrl().equals("")==false&&tAppVersion.getUpgradeUrl().length()>10)
            {
                String oldFileKey=tAppVersion.getUpgradeUrl().substring(tAppVersion.getUpgradeUrl().lastIndexOf("/")+1, tAppVersion.getUpgradeUrl().lastIndexOf("."));
                //如果有则删除原来的文件
                if(!"".equals(oldFileKey) && oldFileKey!=null) {
                    aliyunService.deleteFileFromOSS(oldFileKey);
                }
            }

            tAppVersion.setUpgradeUrl(newFileUrl);
            boolean result = tAppVersionService.updates(tAppVersion);
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
     * 修改版本管理
     */

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TAppVersion tAppVersion) {
        tAppVersionService.updateByAppKey(tAppVersion);
        return super.SUCCESS_TIP;
    }


    /**
     * 获取版本管理列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return tAppVersionService.selectList(null);
    }

//    /**
//     * 新增版本管理
//     */
//
//    @RequestMapping(value = "/add")
//    @ResponseBody
//    public Object add(TAppVersion tAppVersion) {
//        tAppVersionService.insert(tAppVersion);
//        return super.SUCCESS_TIP;
//    }



//    /**
//     * 删除版本管理
//     */
//
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public Object delete(@RequestParam Integer tAppVersionId) {
//        tAppVersionService.deleteById(tAppVersionId);
//        return SUCCESS_TIP;
//    }



//    /**
//     * 版本管理详情
//     */
//
//    @RequestMapping(value = "/detail/{tAppVersionId}")
//    @ResponseBody
//    public Object detail(@PathVariable("tAppVersionId") Integer tAppVersionId) {
//        return tAppVersionService.selectById(tAppVersionId);
//    }
}
