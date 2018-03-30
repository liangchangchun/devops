package com.zww.activity.service;

import java.util.List;

import com.zww.api.model.Banner;
import com.zww.common.ResultMap;


/**
 * @author lgq Version: 1.0 Date: 2017/9/25 Description: 用户Service接口类. Copyright
 *         (c) 2017 lgq. All rights reserved.
 */
public interface BannerService {

    /**
     * 获取主页banner
     *
     * @return
     */
    List<Banner> selectActiveBanner();

    /**
     * 获取滚动横幅
     *
     * @return
     */
    ResultMap selectscrollingBanner();

    /**
     * 获取启动页
     *
     * @return
     */
    ResultMap selectStartPage();

    /**
     * 获取弹窗广告
     *
     * @return
     */
    ResultMap selectPopUpAd();

}
