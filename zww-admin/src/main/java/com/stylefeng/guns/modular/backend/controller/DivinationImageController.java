package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.persistence.dao.DivinationTopicMapper;
import com.stylefeng.guns.common.persistence.model.DivinationTopic;
import com.stylefeng.guns.common.persistence.model.TDollImage;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.TipType;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.StringUtils;
import com.stylefeng.guns.modular.backend.service.IDivinationTopicService;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.DivinationImageWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.mutidatasource.DSEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.common.persistence.model.DivinationImage;
import com.stylefeng.guns.modular.backend.service.IDivinationImageService;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 占卜图片控制器
 *
 * @author fengshuonan
 * @Date 2018-02-02 14:52:28
 */
@Controller
@RequestMapping("/divinationImage")
public class DivinationImageController extends BaseController {

    private String PREFIX = "/backend/divinationImage/";
    private String PREFIXS = "/backend/divinationTopic/";


    @Autowired
    private IDivinationImageService divinationImageService;

    @Autowired
    private IDivinationTopicService divinationTopicService;

    @Autowired
    AliyunService aliyunService;


    /**
     * 跳转到占卜图片首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "divinationImage.html";
    }

    /**
     * 跳转到添加占卜图片
     */
    @RequestMapping("/divinationImage_add")
    public String divinationImageAdd() {
        return PREFIX + "divinationImage_add.html";
    }

    /**
     * 跳转到添加占卜图片
     */
    @RequestMapping("/divinationImage_adds/{divinationTopicId}")
    public String divinationImageAdds(@PathVariable Integer divinationTopicId, Model model) {
        model.addAttribute("item",divinationTopicId);
        return PREFIXS + "divinationImage_add_topic.html";
    }


    //跳转到上传图片
    @RequestMapping("/toUpload")
    public String toUpload(Integer id, Model model) {
        DivinationImage divinationImage = divinationImageService.selectById(id);
        model.addAttribute("item",divinationImage);
        return PREFIX + "divinationImage_upload.html";
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
            DivinationImage divinationImage = divinationImageService.selectById(id);
            //logger.info("广告参数:{}",d.toString());
            if(divinationImage.getDivinationTopicImg()!=null&&divinationImage.getDivinationTopicImg().equals("")==false&&divinationImage.getDivinationTopicImg().length()>10)
            {
                String oldFileKey=divinationImage.getDivinationTopicImg().substring(divinationImage.getDivinationTopicImg().lastIndexOf("/")+1, divinationImage.getDivinationTopicImg().lastIndexOf("."));
                //如果有则删除原来的头像
                if(!"".equals(oldFileKey) && oldFileKey!=null) {
                    aliyunService.deleteFileFromOSS(oldFileKey);
                }
            }

            divinationImage.setDivinationTopicImg(newFileUrl);
            divinationImage.setModifiedBy(ShiroKit.getUser().getId());
            divinationImage.setModifiedDate(new Date());
            boolean result = divinationImageService.insertOrUpdate(divinationImage);
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

        DivinationTopic divinationTopic = divinationTopicService.selectById(id);
        DivinationImage divinationImage = new DivinationImage();
        divinationImage.setDivinationTopicId(id);
        divinationImage.setDivinationName(divinationTopic.getDivinationName());
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
                    divinationImage.setDivinationTopicImg(newFileUrl);
                    divinationImage.setCreatedDate(new Date());
                    divinationImage.setCreatedBy(ShiroKit.getUser().getId());
                    divinationImage.setDivinationName(divinationTopic.getDivinationName());
                    divinationImage.setModifiedBy(ShiroKit.getUser().getId());
                    divinationImage.setModifiedDate(new Date());
                    boolean result = divinationImageService.insertOrUpdate(divinationImage);
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
     * 获取占卜图片列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<DivinationImage> page = new PageFactory<DivinationImage>().defaultPage();
        List<Map<String, Object>> result = divinationImageService.selectLists(page,condition);
        page.setRecords((List<DivinationImage>)new DivinationImageWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增占卜图片
     */
     
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DivinationImage divinationImage,Integer temp) {
        if(temp == 1){
            return super.SUCCESS_TIP;
        }else {
            DivinationTopic divinationTopic = new DivinationTopic();
            if(divinationImage !=null){
                divinationTopic = divinationTopicService.selectById(divinationImage.getDivinationTopicId());
            }
            divinationImage.setDivinationName(divinationTopic.getDivinationName());
            divinationImage.setCreatedDate(new Date());
            divinationImage.setCreatedBy(ShiroKit.getUser().getId());
            divinationImage.setModifiedDate(new Date());
            divinationImage.setModifiedBy(ShiroKit.getUser().getId());
            if(divinationImage.getDivinationTopicImg()=="") {divinationImage.setDivinationTopicImg("#");}
            divinationImageService.insert(divinationImage);
            return super.SUCCESS_TIP;
        }

    }

    /**
     * 删除占卜图片
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String divinationImageId) {
        String[] ids = null;
        //存放id集合
        List<String> idsString = new ArrayList<>();
        if(divinationImageId != null) {
            ids = divinationImageId.split(",");
        }
        Integer id = null;
        //图片路径集合
        List<DivinationImage> images = new ArrayList<>();
        for (String imgId : ids) {
            idsString.add(imgId);
            if (imgId != null){
                id = Integer.valueOf(imgId);
                DivinationImage image = divinationImageService.selectById(id);
                images.add(image);
            }
        }
        //批量删除id的数据
        int result = divinationImageService.deleteAllById(idsString);
        if(result>0) {
            for (DivinationImage image : images) {
                if(image.getDivinationTopicImg()!=null&&image.getDivinationTopicImg().equals("")==false&&image.getDivinationTopicImg().length()>10) {
                    String oldFileKey=image.getDivinationTopicImg().substring(image.getDivinationTopicImg().lastIndexOf("/")+1, image.getDivinationTopicImg().lastIndexOf("."));
                    //如果有则删除原来的头像
                    if(!"".equals(oldFileKey) && oldFileKey!=null) {
                        aliyunService.deleteFileFromOSS(oldFileKey);
                    }
                }
            }
        }
        return SUCCESS_TIP;
    }

//    /**
//     * 修改占卜图片
//     */
//
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public Object update(DivinationImage divinationImage) {
//        divinationImageService.updateById(divinationImage);
//        return super.SUCCESS_TIP;
//    }

    /**
     * 占卜图片详情
     */
     
    @RequestMapping(value = "/detail/{divinationImageId}")
    @ResponseBody
    public Object detail(@PathVariable("divinationImageId") Integer divinationImageId) {
        return divinationImageService.selectById(divinationImageId);
    }
}
