package com.zww.learn.zookeeper.fakelimite;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.CloseableUtils;

public class InterProcessMutexExample {  
	   private static final int QTY = 5;  
	   private static final int REPETITIONS = QTY * 10;  
	   private static final String PATH = "/examples/locks";  
	   private static final String CONNECT_ADDR = "127.0.0.1:2181";  
	 
	   public static void main(String[] args) throws Exception {  
	       final FakeLimitedResource resource = new FakeLimitedResource();  
	       ExecutorService executor = Executors.newFixedThreadPool(QTY);  
	        try {  
	            for(int i=0; i<QTY; i++) {  
	                final int index = i;  
	                Callable<Void> task = () -> {  
	                    CuratorFramework curator = CuratorFrameworkFactory.newClient(CONNECT_ADDR, new RetryNTimes(3, 1000));  
	                    curator.start();  
	                    try {  
	                        final ExampleClientThatLocks example = new ExampleClientThatLocks(curator, PATH, resource, "Client " + index);  
	                        for(int j=0; j<REPETITIONS; j++) {  
	                            example.doWork(10, TimeUnit.SECONDS);  
	                        }  
	                    } catch (Exception e) {  
	                        e.printStackTrace();  
	                    } finally {  
	                        CloseableUtils.closeQuietly(curator);  
	                    }  
	                    return null;  
	                };  
	                executor.submit(task);  
	            }  
	            executor.shutdown();  
	            executor.awaitTermination(10, TimeUnit.MINUTES);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	}  
