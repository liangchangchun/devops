package com.zww.learn.thread;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public abstract class TimeTemplate {
	
	public void doSomeThing() {
		/*long startTime=System.currentTimeMillis();   //获取开始时间
		task();
		long endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("程序运行时间： "+(endTime - startTime)+"ms");
		 */
		/*
		long startTime=System.nanoTime();   //获取开始时间

		task(); //测试的代码段

		long endTime=System.nanoTime(); //获取结束时间

		System.out.println("程序运行时间： "+(endTime - startTime)+"ns");
		
			MICROSECONDS    微秒   一百万分之一秒（就是毫秒/1000）
			MILLISECONDS    毫秒   千分之一秒    
			NANOSECONDS   毫微秒  十亿分之一秒（就是微秒/1000）
			SECONDS          秒
			MINUTES     分钟
			HOURS      小时
			DAYS      天
		*/
		Stopwatch stopwatch =Stopwatch.createStarted();
        //do something test

		task(); //测试的代码段
        long nanos = stopwatch.elapsed(TimeUnit.NANOSECONDS );
        System.out.println("程序运行时间: "+nanos+"ns");


	 };
	 
	 
	public abstract void task();
}
