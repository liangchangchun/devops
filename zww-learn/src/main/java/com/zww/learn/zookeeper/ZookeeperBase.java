package com.zww.learn.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperBase {
	 /** zookeeper地址
	  *   - "2183:2181"
         - "2883:2888"
         - "3883:3888"
	  *  */  
	   static final String CONNECT_ADDR = "192.168.99.100:2183,192.168.99.100:2883,192.168.99.100:3883";  
	//static final String CONNECT_ADDR = "127.0.0.1:2181";  
	   /** session超时时间 */  
	   static final int SESSION_OUTTIME = 2000;//ms   
	   /** 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号 */  
	   static final CountDownLatch connectedSemaphore = new CountDownLatch(1);  
	     
	    public static void main(String[] args) throws Exception{  
	          
	        ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher(){  
	            @Override  
	            public void process(WatchedEvent event) {  
	                //获取事件的状态  
	                KeeperState keeperState = event.getState();  
	                EventType eventType = event.getType();  
	                //如果是建立连接  
	                if(KeeperState.SyncConnected == keeperState){  
	                    if(EventType.None == eventType){  
	                        //如果建立连接成功，则发送信号量，让后续阻塞程序向下执行  
	                        connectedSemaphore.countDown();  
	                        System.out.println("zk 建立连接");  
	                    }  
	                }  
	            }  
	        });  
	  
	        //进行阻塞  
	        connectedSemaphore.await();  
	          
	        System.out.println("..");  
	        if (zk.exists("/testRoot", false) != null) {
	        	  zk.delete("/testRoot", -1);  
	        	  System.out.println("testRoot 删除根节点");  
	        }
	        //创建父节点  
	      zk.create("/testRoot", "testRoot".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);  
	          
	        //创建子节点，使用EPHEMERAL，主程序执行完成后该节点被删除，只在本次会话内有效，可以用作分布式锁。  
	      zk.create("/testRoot/children", "children data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);  
	          
	        //获取节点信息  
	      byte[] data = zk.getData("/testRoot", false, null);  
	      System.out.println(new String(data));  
	      System.out.println(zk.getChildren("/testRoot", false));  
	          
	        //修改节点的值，-1表示跳过版本检查，其他正数表示如果传入的版本号与当前版本号不一致，则修改不成功，删除是同样的道理。  
	      //zk.setData("/testRoot", "modify data root".getBytes(), -1);  
	      //byte[] data = zk.getData("/testRoot", false, null);  
	      //System.out.println(new String(data));         
	          
	        //判断节点是否存在  
	      System.out.println("判断节点是否存在"+zk.exists("/testRoot/children", false));  
	        //删除节点  
	      zk.delete("/testRoot/children", -1);  
	      System.out.println("删除节点  "+zk.exists("/testRoot/children", false));  
	          
	        zk.close();  
	          
	          
	          
	    }  

}
