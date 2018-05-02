package com.zww.learn.zookeeper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.junit.Test;

public class AclServer {
	static final String CONNECT_ADDR = "127.0.0.1:2181";  
	 @Test  
	 public void testAclServer() {  
	    List<ACL> acls = new ArrayList<ACL>(2);  
	    try {  
	        Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest("fish:fishpw"));  
	        ACL acl1 = new ACL(ZooDefs.Perms.WRITE, id1);  
	  
	        Id id2 = new Id("digest", DigestAuthenticationProvider.generateDigest("qsd:qsdpw"));  
	        ACL acl2 = new ACL(ZooDefs.Perms.READ, id2);  
	   
	         acls.add(acl1);  
	         acls.add(acl2);  
	   
	         // 所有用户都有权限  
	         // Id world = new Id("world", "anyone");  
	         // ACL worldAcl = new ACL(ZooDefs.Perms.READ, world);  
	         // acls.add(worldAcl);  
	   
	         // 10.0.2.76是本机IP  
	         // Id id3 = new Id("ip", "10.0.2.76");  
	         // ACL acl3 = new ACL(ZooDefs.Perms.WRITE, id3);  
	         // acls.add(acl3);  
	     } catch (NoSuchAlgorithmException e1) {  
	         e1.printStackTrace();  
	     }  
	     ZooKeeper zk = null;  
	     try {  
	         zk = new ZooKeeper(CONNECT_ADDR, 300000, new Watcher() {  
	             // 监控所有被触发的事件  
	             public void process(WatchedEvent event) {  
	                 System.out.println("已经触发了" + event.getType() + "事件！");  
	             }  
	         });  
	         if (zk.exists("/test", true) == null) {  
	             System.out.println(zk.create("/test", "ACL测试".getBytes(), acls, CreateMode.PERSISTENT));  
	         }  
	     } catch (IOException e) {  
	         e.printStackTrace();  
	     } catch (KeeperException e1) {  
	         e1.printStackTrace();  
	     } catch (InterruptedException e1) {  
	         e1.printStackTrace();  
	     }  
	 }  
	   
	 @Test  
	 public void testAclClient() {  
	     try {  
	         ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, 300000, new Watcher() {  
	             // 监控所有被触发的事件  
	             public void process(WatchedEvent event) {  
	                 System.out.println("已经触发了" + event.getType() + "事件！");  
	             }  
	         });  
	         // 只有写权限  
	         zk.addAuthInfo("digest", "fish:fishpw".getBytes());  
	         // 只有读权限  
	         zk.addAuthInfo("digest", "qsd:qsdpw".getBytes());  
	         System.out.println(new String(zk.getData("/test", null, null)));  
	         zk.setData("/test", "I change！".getBytes(), -1);  
	     } catch (KeeperException e) {  
	         e.printStackTrace();  
	     } catch (InterruptedException e) {  
	         e.printStackTrace();  
	     } catch (IOException e) {  
	         e.printStackTrace();  
	     }  
	 }  

}
