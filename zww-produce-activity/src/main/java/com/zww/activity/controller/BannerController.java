package com.zww.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zww.activity.service.BannerService;
import com.zww.common.ResultMap;


@RestController
public class BannerController {

	@Autowired
    private BannerService bannerService;
	  /**
     * 滚动横幅
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/scrollingBanner", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap scrollingBanner(String head) {
    	return bannerService.selectscrollingBanner();
    }
	
}
