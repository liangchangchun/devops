package com.stylefeng.guns.core.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

/**
 * Created by SUN on 2018/1/11.
 */
@Repository
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtil.applicationContext = context;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 成功获取到ApplicationContext对象时返回true；反之则返回false
     */
    public static Boolean isReady() {
        return SpringUtil.applicationContext != null;
    }
}