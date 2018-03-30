package com.stylefeng.guns.modular.backend.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.stylefeng.guns.common.persistence.model.DollTopic;
import com.stylefeng.guns.modular.backend.service.IDollTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.TipType;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.RedisKeyGenerator;
import com.stylefeng.guns.core.util.StringUtils;
import com.stylefeng.guns.game.MachineStatus;
import com.stylefeng.guns.modular.backend.service.ITDollService;
import com.stylefeng.guns.modular.backend.warpper.TDollWarpper;

/**
 * 娃娃机列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-24 15:58:48
 */
@Controller
@RequestMapping("/tDoll")
public class TDollController extends BaseController {

    private String PREFIX = "/backend/tDoll/";

    @Autowired
    private ITDollService tDollService;

    @Autowired
    private IDollTopicService dollTopicService;
    
    @Autowired
    AliyunService aliyunService;
    
     @Autowired
     StringRedisTemplate stringRedisTemplate;

     @Resource(name = "stringRedisTemplate")
     ValueOperations<String, String> valOpsStr;

    /**
     * 跳转到娃娃机列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tDoll.html";
    }

    /**
     * 跳转到添加娃娃机列表
     */
    @RequestMapping("/tDoll_add")
    public String tDollAdd() {
        return PREFIX + "tDoll_add.html";
    }

    /**
     * 跳转到添加娃娃机主题
     */
    @RequestMapping("/dollTopic_add/{tDollId}")
    public String dollTopicAdd(@PathVariable Integer tDollId, Model model) {
        TDoll tDoll = tDollService.selectById(tDollId);
        model.addAttribute("item",tDoll);
        LogObjectHolder.me().set(tDoll);
        return PREFIX + "tDollTopic_add.html";
    }

    /**
     * 娃娃封面图片上传
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toUpload")
    public String toUpload(Integer id, Model model) {
    	TDoll doll = tDollService.selectById(id);
    	 model.addAttribute("item",doll);
        return PREFIX + "tDoll_upload.html";
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
			 TDoll dollmachine = tDollService.selectById(tBannerId);
			 //logger.info("广告参数:{}",d.toString());
			 if(dollmachine.getTbimgRealPath()!=null&&dollmachine.getTbimgRealPath().equals("")==false&&dollmachine.getTbimgRealPath().length()>10)
			 {
			 String oldFileKey=dollmachine.getTbimgRealPath().substring(dollmachine.getTbimgRealPath().lastIndexOf("/")+1, dollmachine.getTbimgRealPath().lastIndexOf("."));
			 //如果有则删除原来的头像
			 if(!"".equals(oldFileKey) && oldFileKey!=null) {
			// logger.info("删除广告: "+d.getId()+"原来的头像从阿里云OSS:"+ bukectName+"文件名:"+oldFileKey);
				 aliyunService.deleteFileFromOSS(oldFileKey);
			 }
			 }
			 String newFileUrl = aliyunService.generatePresignedUrl(NewFileKey,1000000).toString();
			 dollmachine.setTbimgRealPath(newFileUrl);
			 dollmachine.setModifiedBy(ShiroKit.getUser().getId());
			 dollmachine.setModifiedDate(new Date());
			 //logger.info("更新广告头像结果:{}",newFileUrl);
			 boolean result = tDollService.insertOrUpdate(dollmachine);
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
     * 跳转到修改娃娃机列表
     */
     
    @RequestMapping("/tDoll_update/{tDollId}")
    public String tDollUpdate(@PathVariable Integer tDollId, Model model) {
        TDoll tDoll = tDollService.selectById(tDollId);
        model.addAttribute("item",tDoll);
        LogObjectHolder.me().set(tDoll);
        return PREFIX + "tDoll_edit.html";
    }


    /**
     * 跳转到添加背景图
     */

    @RequestMapping("/addBackgroung/{tDollId}")
    public String addBackgroung(@PathVariable Integer tDollId, Model model) {
        TDoll tDoll = tDollService.selectById(tDollId);
        model.addAttribute("item",tDoll);
        LogObjectHolder.me().set(tDoll);
        return PREFIX + "tDollImage_t.html";
    }

    /**
     * 获取娃娃机列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer dollId, String name, String machineCode, String machineStates,Integer machineType,Integer modifiedBy) {
         Page<TDoll> page = new PageFactory<TDoll>().defaultPage();
         List<Map<String, Object>> result = tDollService.selectDolls(page,dollId,name,machineCode,machineStates,machineType,modifiedBy);
         page.setRecords((List<TDoll>)new TDollWarpper(result).warp());
         return super.packForBT(page);
    }

    /**
     * 新增娃娃机列表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TDoll tDoll) {
    	tDoll.setMachineStatus(MachineStatus.MACHINE_NOT_ONLINE.getMessage());
    	tDoll.setQuantity(99);
    	tDoll.setTbimgRealPath("#");
    	tDoll.setDeleteStatus(1);
    	tDoll.setCreatedDate(new Date());
    	tDoll.setCreatedBy(ShiroKit.getUser().getId());
    	tDoll.setModifiedDate(new Date());
    	tDoll.setModifiedBy(ShiroKit.getUser().getId());
        tDollService.insert(tDoll);
        return SUCCESS_TIP;
    }

    /**
     * 删除娃娃机列表
     */
     
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tDollId) {
       // tDollService.deleteById(tDollId);
    	 TDoll tDoll = tDollService.selectById(tDollId);
    	 tDoll.setDeleteStatus(0);
    	 tDoll.setModifiedDate(new Date());
    	 tDoll.setModifiedBy(ShiroKit.getUser().getId());
    	 tDollService.insertOrUpdate(tDoll);
        return SUCCESS_TIP;
    }

    /**
     * 修改娃娃机列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TDoll tDoll) {
    	TDoll oldtDoll = tDollService.selectById(tDoll.getId());
    	tDoll.setCreatedBy(oldtDoll.getCreatedBy());
    	tDoll.setCreatedDate(oldtDoll.getCreatedDate());
    	tDoll.setModifiedBy(ShiroKit.getUser().getId());
    	tDoll.setModifiedDate(new Date());
    	tDoll.setQuantity(99);
    	tDoll.setDeleteStatus(1);
    	tDoll.setTbimgRealPath(oldtDoll.getTbimgRealPath());
    	boolean result = tDollService.updateById(tDoll);
        DollTopic dollTopic = new DollTopic();
        dollTopic.setDollId(tDoll.getId());
        dollTopic.setDollName(tDoll.getName());
        if(result) {
            //修改主题机器名称
            dollTopicService.updateByDollId(dollTopic);
            //修改redis状态
			valOpsStr.set(RedisKeyGenerator.getRoomStatusKey(tDoll.getId()), tDoll.getMachineStatus());

        }
        return SUCCESS_TIP;
    }


    /**
     * 娃娃机列表详情
     */
     
    @RequestMapping(value = "/detail/{tDollId}")
    @ResponseBody
    public Object detail(@PathVariable("tDollId") Integer tDollId) {
        return tDollService.selectById(tDollId);
    }
}
