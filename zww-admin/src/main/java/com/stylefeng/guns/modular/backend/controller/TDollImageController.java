package com.stylefeng.guns.modular.backend.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.aliyun.impl.AliyunServiceImpl;
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

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.persistence.model.TBanner;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.common.persistence.model.TDollImage;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.TipType;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.StringUtils;
import com.stylefeng.guns.modular.backend.service.ITDollImageService;
import com.stylefeng.guns.modular.backend.warpper.TDollImageWarpper;
import com.stylefeng.guns.modular.backend.warpper.TDollWarpper;

/**
 * 娃娃机背景图片列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-24 21:38:53
 */
@Controller
@RequestMapping("/tDollImage")
public class TDollImageController extends BaseController {

    private String PREFIX = "/backend/tDollImage/";
    private String PREFIXS = "/backend/tDoll/";

    @Autowired
    private ITDollImageService tDollImageService;
    
    @Autowired
    AliyunService aliyunService;

    /**
     * 跳转到娃娃机背景图片列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tDollImage.html";
    }

    /**
     * 跳转到添加娃娃机背景图片列表
     */
    @RequestMapping("/tDollImage_add")
    public String tDollImageAdd() {
        return PREFIX + "tDollImage_add.html";
    }


    /**
     * 跳转到添加娃娃机背景图片列表
     */
    @RequestMapping("/tDollImage_adds/{dollId}")
    public String tDollImageAdds(@PathVariable Integer dollId, Model model) {
        model.addAttribute("item",dollId);
        return PREFIXS + "tDollImage_add_t.html";
    }
    
    @RequestMapping("/toUpload")
    public String toUpload(Integer id, Model model) {
    	TDollImage tDollImage = tDollImageService.selectById(id);
    	 model.addAttribute("item",tDollImage);
        return PREFIX + "tDollImage_upload.html";
    }
    
    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload/{id}")
    @ResponseBody
    public Object upload(@RequestPart("file") MultipartFile picture,@PathVariable Integer id) {
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
			 TDollImage tDollImage = tDollImageService.selectById(id);
			 //logger.info("广告参数:{}",d.toString());
			 if(tDollImage.getImgRealPath()!=null&&tDollImage.getImgRealPath().equals("")==false&&tDollImage.getImgRealPath().length()>10)
			 {
			 String oldFileKey=tDollImage.getImgRealPath().substring(tDollImage.getImgRealPath().lastIndexOf("/")+1, tDollImage.getImgRealPath().lastIndexOf("."));
			 //如果有则删除原来的头像
			 if(!"".equals(oldFileKey) && oldFileKey!=null) {
			// logger.info("删除广告: "+d.getId()+"原来的头像从阿里云OSS:"+ bukectName+"文件名:"+oldFileKey);
				 aliyunService.deleteFileFromOSS(oldFileKey);
			 }
			 }

			 tDollImage.setImgRealPath(newFileUrl);
			 tDollImage.setModifiedBy(ShiroKit.getUser().getId());
			 tDollImage.setModifiedDate(new Date());
			 //logger.info("更新头像结果:{}",newFileUrl);
			 boolean result = tDollImageService.insertOrUpdate(tDollImage);
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
     * 上传多图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/uploads/{id}")
    @ResponseBody
    public Object upload(@RequestPart("file") MultipartFile[] picture,@PathVariable Integer id) {

        TDollImage tDollImage = new TDollImage();
        tDollImage.setDollId(id);
        boolean temp = false;
        if(picture != null){
            for (MultipartFile onePicture:picture){
                String originalFileName = onePicture.getOriginalFilename();
                // 获取后缀
                String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")
                        + 1);
                // 修改后完整的文件名称
                String fileKey = StringUtils.getRandomUUID();
                String NewFileKey = fileKey + "." + suffix;
                byte[] bytes;
                try {
                    bytes = onePicture.getBytes();
                    InputStream fileInputStream = new ByteArrayInputStream(bytes);
                    if(!aliyunService.putFileStreamToOSS(NewFileKey, fileInputStream)) {
                        return "0";
                    }
                    String newFileUrl = aliyunService.generatePresignedUrl(NewFileKey,1000000).toString();
                    tDollImage.setImgRealPath(newFileUrl);
                    tDollImage.setModifiedBy(ShiroKit.getUser().getId());
                    tDollImage.setModifiedDate(new Date());
                    boolean result = tDollImageService.insertOrUpdate(tDollImage);
                    if(result) {
                        temp = true;
                    }else {
                        temp = false;
                    }
                } catch (Exception e) {
                    throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
                }
            }
        }
        if (temp){
            return SUCCESS_TIP;
        }else{
            return new ErrorTip(TipType.UPLOAD_ERROR.getCode(),TipType.UPLOAD_ERROR.getMessage());
        }
    }





    /**
     * 跳转到修改娃娃机背景图片列表
     */
     
    @RequestMapping("/tDollImage_update/{tDollImageId}")
    public String tDollImageUpdate(@PathVariable Integer tDollImageId, Model model) {
        TDollImage tDollImage = tDollImageService.selectById(tDollImageId);
        model.addAttribute("item",tDollImage);
        LogObjectHolder.me().set(tDollImage);
        return PREFIX + "tDollImage_edit.html";
    }

    /**
     * 获取娃娃机背景图片列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String name) {
    	 Page<TDollImage> page = new PageFactory<TDollImage>().defaultPage();
    	 List<Map<String, Object>> result =  tDollImageService.getImageList(page,name);
    	 page.setRecords((List<TDollImage>)new TDollImageWarpper(result).warp());
       	 return super.packForBT(page);
    }

    /**
     * 新增娃娃机背景图片列表
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TDollImage tDollImage,Integer temp) {
        if(temp == 1){
        }else {
            tDollImage.setCreatedDate(new Date());
            tDollImage.setCreatedBy(ShiroKit.getUser().getId());
            tDollImage.setModifiedDate(new Date());
            tDollImage.setModifiedBy(ShiroKit.getUser().getId());
            if(tDollImage.getImgRealPath()=="") {tDollImage.setImgRealPath("#");}
            tDollImageService.insert(tDollImage);
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除娃娃机背景图片列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String tDollImageIds) {
        String[] ids = null;
        //存放id集合
        List<String> idsString = new ArrayList<>();
        if(tDollImageIds != null) {
            ids = tDollImageIds.split(",");
        }
        Integer id = null;
        //图片路径集合
        List<TDollImage> images = new ArrayList<>();
        for (String imgId : ids) {
            idsString.add(imgId);
            if (imgId != null){
                id = Integer.valueOf(imgId);
                TDollImage image = tDollImageService.selectById(id);
                images.add(image);
            }
        }
        //批量删除id的数据
        int result = tDollImageService.deleteAllById(idsString);
        if(result>0) {
            for (TDollImage image : images) {
                if(image.getImgRealPath()!=null&&image.getImgRealPath().equals("")==false&&image.getImgRealPath().length()>10) {
                    String oldFileKey=image.getImgRealPath().substring(image.getImgRealPath().lastIndexOf("/")+1, image.getImgRealPath().lastIndexOf("."));
                    //如果有则删除原来的头像
                    if(!"".equals(oldFileKey) && oldFileKey!=null) {
                        aliyunService.deleteFileFromOSS(oldFileKey);
                    }
                }
            }
        }
        return SUCCESS_TIP;
    }

    /**
     * 修改娃娃机背景图片列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TDollImage tDollImage) {
        tDollImageService.updateById(tDollImage);
        return super.SUCCESS_TIP;
    }

    /**
     * 娃娃机背景图片列表详情
     */
     
    @RequestMapping(value = "/detail/{tDollImageId}")
    @ResponseBody
    public Object detail(@PathVariable("tDollImageId") Integer tDollImageId) {
        return tDollImageService.selectById(tDollImageId);
    }
}
