package com.zww.learn.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountDown extends TimeTemplate {

	private static final CountDownLatch countDown = new CountDownLatch(10);
	
	@Override
	public void task() {
		 //ExecutorService executorService= Executors.newCachedThreadPool();
			ExecutorService executorService= Executors.newFixedThreadPool(3);
			  
			  for (int i=0;i<10;i++) {
				  executorService.execute(new ResultTask(String.valueOf(i),countDown));
			  }
			  try {
				countDown.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			  executorService.shutdown();
			  System.out.println("结束了！");  
			 /*while(true) {
				 if (executorService.isTerminated()) {  
					  System.out.println("结束了！");  
					 break;  
				}  
	            try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  
			} 
			*/
	}

}

class ResultTask implements Runnable {  
	
	private String name;
	private CountDownLatch countDown;
	
	public ResultTask(String name,CountDownLatch countDown) {
		this.name = name;
		this.countDown = countDown;
	}
	  
    @Override  
    public void run() {  
         System.out.println("任务"+this.name+",抓取链接");
         System.out.println("-------------------当前线程数量:"+Thread.activeCount()); 
         countDown.countDown();
    }  
  
}  