package com.stylefeng.guns.modular.backend.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.persistence.model.TBanner;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.TipType;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.StringUtils;
import com.stylefeng.guns.modular.backend.service.ITBannerService;
import com.stylefeng.guns.modular.backend.warpper.MemberWarpper;
import com.stylefeng.guns.modular.backend.warpper.TBannerWarpper;

/**
 * banner控制器
 *
 * @author fengshuonan
 * @Date 2018-01-18 20:54:24
 */
@Controller
@RequestMapping("/tBanner")
public class TBannerController extends BaseController {

    private String PREFIX = "/backend/tBanner/";

    @Autowired
    ITBannerService tBannerService;
    
    @Resource
    GunsProperties gunsProperties;
    
    @Autowired
    AliyunService aliyunService;

    /**
     * 跳转到banner首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tBanner.html";
    }

    /**
     * 跳转到添加banner
     */
    @RequestMapping("/tBanner_add")
    public String tBannerAdd() {
        return PREFIX + "tBanner_add.html";
    }
    
    @RequestMapping("/toUpload")
    public String toUpload(Integer id, Model model) {
    	TBanner banner = tBannerService.selectById(id);
    	 model.addAttribute("item",banner);
        return PREFIX + "tBanner_upload.html";
    }
    
    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload/{tBannerId}")
    @ResponseBody
    public Object upload(@RequestPart("file") MultipartFile picture,@PathVariable Integer tBannerId) {
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
			 TBanner banner = tBannerService.selectById(tBannerId);
			 //logger.info("广告参数:{}",d.toString());
			 if(banner.getImageUrl()!=null&&banner.getImageUrl().equals("")==false&&banner.getImageUrl().length()>10)
			 {
			 String oldFileKey=banner.getImageUrl().substring(banner.getImageUrl().lastIndexOf("/")+1, banner.getImageUrl().lastIndexOf("."));
			 //如果有则删除原来的头像
			 if(!"".equals(oldFileKey) && oldFileKey!=null) {
			// logger.info("删除广告: "+d.getId()+"原来的头像从阿里云OSS:"+ bukectName+"文件名:"+oldFileKey);
				 aliyunService.deleteFileFromOSS(oldFileKey);
			 }
			 }
			 String newFileUrl = aliyunService.generatePresignedUrl(NewFileKey,1000000).toString();
			 banner.setImageUrl(newFileUrl);
			 banner.setModifiedBy(ShiroKit.getUser().getId());
			 banner.setModifiedDate(new Date());
			 //logger.info("更新广告头像结果:{}",newFileUrl);
			 boolean result = tBannerService.insertOrUpdate(banner);
			// logger.info("更新广告头像结果:{}",result>0?"success":"fail");
			 if(result) {
				 return SUCCESS_TIP;
			 }else {
				 return new ErrorTip(TipType.UPLOAD_ERROR.getCode(),TipType.UPLOAD_ERROR.getMessage());
			 }
		} catch (Exception e) {
			throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
		}
    	 
       /* String pictureName = UUID.randomUUID().toString() + ".jpg";
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return pictureName;*/
    	// return "";
    }
    /**
     * 跳转到修改banner
     */
     
    @RequestMapping("/editUrl")
    @ResponseBody
    public Object tBannerEditUrl(Integer id,String bannerUrl, Model model) {
        TBanner tBanner = tBannerService.selectById(id);
        tBanner.setImageUrl(bannerUrl);
        tBannerService.insertOrUpdate(tBanner);
        return SUCCESS_TIP;
    }
    

    /**
     * 跳转到修改banner
     */
     
    @RequestMapping("/tBanner_update/{tBannerId}")
    public String tBannerUpdate(@PathVariable Integer tBannerId, Model model) {
        TBanner tBanner = tBannerService.selectById(tBannerId);
        model.addAttribute("item",tBanner);
        LogObjectHolder.me().set(tBanner);
        return PREFIX + "tBanner_edit.html";
    }

    /**
     * 获取banner列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<TBanner> result = tBannerService.selectList(null);
        return new TBannerWarpper(result).warp();
    }

    /**
     * 新增banner
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TBanner tBanner) {
    	//System.out.println(tBanner.getHyperlink());
    	tBanner.setCreatedBy(ShiroKit.getUser().getId());
    	tBanner.setCreatedDate(new Date());
    	tBanner.setModifiedDate(new Date());
    	tBanner.setModifiedBy(ShiroKit.getUser().getId());
    	tBanner.setImageUrl("#");
        tBannerService.insert(tBanner);
        return SUCCESS_TIP;
    }

    /**
     * 删除banner
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tBannerId) {
        tBannerService.deleteById(tBannerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改banner
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TBanner tBanner) {
    	TBanner oldtBanner = tBannerService.selectById(tBanner.getId());
    	tBanner.setModifiedBy(ShiroKit.getUser().getId());
    	tBanner.setModifiedDate(new Date());
    	tBanner.setCreatedDate(oldtBanner.getCreatedDate());
        tBannerService.insertOrUpdate(tBanner);
        return SUCCESS_TIP;
    }

    /**
     * banner详情
     */
     
    @RequestMapping(value = "/detail/{tBannerId}")
    @ResponseBody
    public Object detail(@PathVariable("tBannerId") Integer tBannerId) {
        return tBannerService.selectById(tBannerId);
    }
}
