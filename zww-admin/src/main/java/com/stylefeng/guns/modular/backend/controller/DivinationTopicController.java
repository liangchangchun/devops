package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.persistence.model.*;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.TipType;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.StringUtils;
import com.stylefeng.guns.modular.backend.service.ITDollService;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.DivinationTopicWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.mutidatasource.DSEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.modular.backend.service.IDivinationTopicService;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 占卜主题控制器
 *
 * @author fengshuonan
 * @Date 2018-02-02 11:32:58
 */
@Controller
@RequestMapping("/divinationTopic")
public class DivinationTopicController extends BaseController {

    private String PREFIX = "/backend/divinationTopic/";

    @Autowired
    private IDivinationTopicService divinationTopicService;

    @Autowired
    private ITDollService dollService;

    @Autowired
    AliyunService aliyunService;

    /**
     * 跳转到占卜主题首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "divinationTopic.html";
    }

    /**
     * 跳转到添加占卜主题
     */
    @RequestMapping("/divinationTopic_add")
    public String divinationTopicAdd() {
        return PREFIX + "divinationTopic_add.html";
    }

    /**
     * 跳转到修改占卜主题
     */
     
    @RequestMapping("/divinationTopic_update/{divinationTopicId}")
    public String divinationTopicUpdate(@PathVariable Integer divinationTopicId, Model model) {
        DivinationTopic divinationTopic = divinationTopicService.selectById(divinationTopicId);
        model.addAttribute("item",divinationTopic);
        LogObjectHolder.me().set(divinationTopic);
        return PREFIX + "divinationTopic_edit.html";
    }


    /**
     * 跳转到添加占卜图片
     */

    @RequestMapping("/addImg/{divinationTopicId}")
    public String addImg(@PathVariable Integer divinationTopicId, Model model) {
        DivinationTopic divinationTopic = divinationTopicService.selectById(divinationTopicId);
        model.addAttribute("item",divinationTopic);
        LogObjectHolder.me().set(divinationTopic);
        return PREFIX + "divinationImage_topic.html";
    }

    //跳转到上传图片
    @RequestMapping("/toUpload")
    public String toUpload(Integer id, Model model) {
        DivinationTopic divinationTopic = divinationTopicService.selectById(id);
        model.addAttribute("item",divinationTopic);
        return PREFIX + "divinationTopic_upload.html";
    }

    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload/{id}")
    @ResponseBody
    public Object upload(@RequestPart("file") MultipartFile picture, @PathVariable Integer id) {
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
            if (id==-1) {
                return newFileUrl;
            }
            DivinationTopic divinationTopic = divinationTopicService.selectById(id);
            //logger.info("广告参数:{}",d.toString());
            if(divinationTopic.getModeUrl()!=null&&divinationTopic.getModeUrl().equals("")==false&&divinationTopic.getModeUrl().length()>10)
            {
                String oldFileKey=divinationTopic.getModeUrl().substring(divinationTopic.getModeUrl().lastIndexOf("/")+1, divinationTopic.getModeUrl().lastIndexOf("."));
                //如果有则删除原来的头像
                if(!"".equals(oldFileKey) && oldFileKey!=null) {
                    // logger.info("删除广告: "+d.getId()+"原来的头像从阿里云OSS:"+ bukectName+"文件名:"+oldFileKey);
                    aliyunService.deleteFileFromOSS(oldFileKey);
                }
            }
            divinationTopic.setModeUrl(newFileUrl);
            divinationTopic.setModifiedBy(ShiroKit.getUser().getId());
            divinationTopic.setModifiedDate(new Date());
            //logger.info("更新头像结果:{}",newFileUrl);
            boolean result = divinationTopicService.insertOrUpdate(divinationTopic);
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
     * 获取占卜主题列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<DivinationTopic> page = new PageFactory<DivinationTopic>().defaultPage();
        List<Map<String, Object>> result = divinationTopicService.selectLists(page,condition);
        page.setRecords((List<DivinationTopic>)new DivinationTopicWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增占卜主题
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DivinationTopic divinationTopic) {

        if(divinationTopic.getModeUrl()==""||divinationTopic.getModeUrl()==null) {divinationTopic.setModeUrl("#");}
        divinationTopic.setCreatedDate(new Date());
        divinationTopicService.insert(divinationTopic);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除占卜主题
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer divinationTopicId) {
        divinationTopicService.deleteById(divinationTopicId);
        return SUCCESS_TIP;
    }

    /**
     * 修改占卜主题
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DivinationTopic divinationTopic) {
        divinationTopic.setModifiedDate(new Date());
        divinationTopicService.updateById(divinationTopic);
        return super.SUCCESS_TIP;
    }

    /**
     * 占卜主题详情
     */
     
    @RequestMapping(value = "/detail/{divinationTopicId}")
    @ResponseBody
    public Object detail(@PathVariable("divinationTopicId") Integer divinationTopicId) {
        return divinationTopicService.selectById(divinationTopicId);
    }
}
