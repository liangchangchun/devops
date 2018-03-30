package com.zww.pay.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zww.api.model.SystemPref;
import com.zww.api.service.SystemPrefService;


@RefreshScope
@RestController
public class PayController {

    private final Logger logger = Logger.getLogger(getClass());
    
    @Autowired
    private SystemPrefService systemPrefService;
    
    @Value("${from}")
    private String from;

    @RequestMapping("/from")
    public String from() {

        return this.from;
    }

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        //ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        //logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        logger.info("config param:"+this.from);
        SystemPref systemPref = systemPrefService.selectByPrimaryKey("NEW_BONUS");
        if(systemPref!=null) {
        	logger.info("systemPref name=" + systemPref.getName() + ",value=" + systemPref.getValue());
        }
        logger.info("systemPref code=" + systemPrefService.hello("NEW_BONUS"));
        return r;
    }

}