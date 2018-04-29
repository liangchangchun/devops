package com.zww.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.stylefeng.guns.core.redis.RedisService;

public class OrderNoUtil {
	//Map<Integer,Integer> orderEnd =new HashMap<Integer,Integer>();
	private volatile String orderprefix = "";
	private volatile String now = "";
	private volatile static OrderNoUtil intance =null;
	private RedisService redisService;
	public static OrderNoUtil getInstance(RedisService redisTemplate) {
		if (intance==null) {
			synchronized (OrderNoUtil.class) {
				if (intance==null) {
					intance = new OrderNoUtil(redisTemplate);
				}
			}
		}
		return intance;
	}
	private OrderNoUtil(RedisService redisTemplate) {
		this.redisService = redisTemplate;
	}
	/**
	 * test
	 */
	private volatile Map<String,Integer> map = new ConcurrentHashMap<String,Integer>();
	public void addNo(String No) {
		if (map.containsKey(No)) {
			Integer num = map.get(No);
			map.put(No, num++);
		} else {
			map.put(No, 1);
		}
	}
	public Integer getOrderNoSize() {
		return map.size();
	}
	public void clearTestMap() {
		map.clear();
	}
	
	public synchronized String getOrder(int k) {
		String orderNo=null;
        String kk="";
        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
        String dateStr=sdf.format(new Date());
        orderprefix = dateStr;
        if(k<10){
               kk="0"+k;
         }else{
               kk=k+"";
        }
        int number = getOrderRNum();
        //orderEnd = (Map<Integer, Integer>) redisService.hashGet(orderprefix);
        //if (orderEnd.containsKey(number)) {
        //if (redisService.hashGet(orderprefix).containsKey(number)) {
        	  now=sdf.format(new Date());
        	if (now.equals(orderprefix)) {
        		number = getOrderRNum();
        		redisService.addHashSet(orderprefix, number, 1);	
        	} else {
        		redisService.delKey(orderprefix);
        		redisService.addHashSet(now, number, 1);
        	}
       // }
         //orderEnd.put(number, 1);
        	orderNo=dateStr+number+kk;
        return orderNo;
	}
	
	public int getOrderRNum() {
		int number = getOrderRd();
		//while (orderEnd.containsKey(number)) {
		//while (redisService.hashGet(orderprefix).containsKey(number)) {
			number = getOrderRd();
		//}
		return number;
	}
	
	public  int getOrderRd() {
		return (int)((Math.random()*9+1)*100000);
	}
	

	public RedisService getRedisService() {
		return redisService;
	}

	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}
	
	
}
