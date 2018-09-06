package com.zww.test.service.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.core.redis.RedisService;
import com.zww.test.service.OrderNoService;
import com.zww.test.util.OrderNoUtil;

@Service
public class OrderNoServiceImpl implements OrderNoService {
	@Autowired
	RedisService redisService;
	private static AtomicInteger count = new AtomicInteger(0);
	
	@Override
	public String getOrderNoInfo()  {
		count = new AtomicInteger(0);
		OrderNoUtil.getInstance(redisService).clearTestMap();
		int threadNumber = 90000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
		for (int i=0; i<3000 ; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j=0; j < 30 ; j++) {
						final int person = j;
						String orderNo = OrderNoUtil.getInstance(redisService).getOrder(person);
						OrderNoUtil.getInstance(redisService).addNo(orderNo);
						System.out.println(Thread.currentThread().getName()+" ---- 订单编号:"+orderNo + ",order数量:" + OrderNoUtil.getInstance(redisService).getOrderNoSize());
						countDownLatch.countDown();
						count.incrementAndGet();
					}
					
				}
			}).start();
		}
		 try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String result = "xxxxxxxxxxx最终 order数量:" + OrderNoUtil.getInstance(redisService).getOrderNoSize() + ",count:" +count;
		
		/*redisService.hashSet("t1", "123456", "1");
		redisService.hashSet("t1", "123457", "2");
		String result = redisService.getHashSet("t1", "123457");
		redisService.delKey("t1");
		*/
		return result;
	}

}
