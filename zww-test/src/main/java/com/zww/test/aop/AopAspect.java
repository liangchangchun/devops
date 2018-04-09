package com.zww.test.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAspect {

	@Around("execution (java.util.Map com.zww.test.controller.MainController.*( .. ) ) ")
    public Object invoke(ProceedingJoinPoint point) {
        Map<String, Object> map = null;
        try {
            Object[] args = point.getArgs();
            Object val = point.proceed(args);
            map = (Map<String, Object>) val;
        } catch (Throwable e) {
            e.printStackTrace();
            map = new HashMap<>();
            map.put("msg", e.toString());
        }
        map.put("aop", "拦截了一个请求");
        return map;
    }
}
