package com.zww.activity.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.core.util.StringUtils;
import com.zww.activity.dao.BannerDao;
import com.zww.api.model.Banner;
import com.zww.api.model.ScrollingBanner;
import com.zww.activity.service.BannerService;
import com.zww.common.Enviroment;
import com.zww.common.ResultMap;



/**
 * @author liangchangchun Description: 用户Service接口实现类.

 */
@Service
public class BannerServiceImpl implements BannerService {
	
    @Autowired
    private BannerDao bannerDao;
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> redisUtil;

    @Override
    public List<Banner> selectActiveBanner() {
        String[] str = {Enviroment.BANNER0, Enviroment.BANNER1};
        return bannerDao.selectActiveBannerByType(str);
    }

    /**
     * 获取滚动banner
     *
     * @return
     */
    @Override
    public ResultMap selectscrollingBanner() {
        //RedisUtil redisUtil = new RedisUtil();
        //查询redis中的锁是否还在
        List<String> list = new ArrayList<>();
        //if (StringUtils.isEmpty(redisUtil.getString("scrollingBanner"))) {
        if (StringUtils.isEmpty(redisUtil.get("scrollingBanner"))) {
            //重新加锁
            //redisUtil.setString("scrollingBanner", "1", 120);
        	redisUtil.set("scrollingBanner", "1", 120);
            //没锁就从数据库中查询10条返回并插入redis
            List<ScrollingBanner> scrollingBanner = bannerDao.selectscrollingBanner();
            for (int i = 0; i < scrollingBanner.size(); i++) {
                list.add(scrollingBanner.get(i).toString());
                redisUtil.set("scrollingBanner" + i, list.get(i), 36000);
            }
            if (20 - scrollingBanner.size() > 0) {
                for (int i = scrollingBanner.size(); i < 20; i++) {
                    list.add(redisUtil.get("scrollingBanner" + i));
                }
            }
            //logger.info("从数据库中获取滚动横幅成功");
            return new ResultMap(Enviroment.SCROLLINGBANNER_JDBC, list);
        } else {
            //有锁就从redis中读取
            for (int i = 0; i < 20; i++) {
                String s = redisUtil.get("scrollingBanner" + i);
                list.add(s == null ? "" : s);
            }
            //logger.info("滚动横幅成功");
            return new ResultMap(Enviroment.SCROLLINGBANNER_REDIS, list);
        }
    }

    /**
     * 查询启动页
     *
     * @return
     */
    @Override
    public ResultMap selectStartPage() {
        String[] str = {Enviroment.STARTPAGE};
        List<Banner> startPage = bannerDao.selectActiveBannerByType(str);
        if (startPage.size() > 0) {
            //logger.info("查询启动页成功");
            return new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE, startPage);
        }
        return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.NO_STARTPAGE);
    }

    /**
     * 查询弹窗广告
     *
     * @return
     */
    @Override
    public ResultMap selectPopUpAd() {
        String[] str = {Enviroment.POPUPAD};
        List<Banner> popUpAd = bannerDao.selectActiveBannerByType(str);
        if (popUpAd.size() > 0) {
            //logger.info("查询弹窗广告成功");
            return new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE, popUpAd);
        }
        return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.NO_POPUPAD);
    }
}
