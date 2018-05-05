package com.zww.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.zww.mybatis.model.User;

public class TestCase {
	  private static SqlSessionFactory sqlSessionFactory;
	  
	@Test
	public void test() {
		   //读取mybatis-config.xml文件
        InputStream resourceAsStream;
        SqlSession sqlSession = null;
        SqlSession sqlSession2  = null;
		try {
			resourceAsStream = Resources.getResourceAsStream("com/zww/mybatis/test/mybatis-config.xml");
        //初始化mybatis,创建SqlSessionFactory类的实例
			sqlSessionFactory =  new SqlSessionFactoryBuilder().build(resourceAsStream);
			
			sqlSession = sqlSessionFactory.openSession();
	        User user = (User)sqlSession.selectOne("com.zww.mybatis.dao.UserMapper.selectByPrimaryKey", 1000);
	       // System.out.println(user);
	        //user.setPhone("111");
	       // sqlSession.update("com.zww.mybatis.dao.UserMapper.updateByPrimaryKeySelective", user);
	        sqlSession.commit();
	        sqlSession2 = sqlSessionFactory.openSession();
	        User user2 = (User)sqlSession2.selectOne("com.zww.mybatis.dao.UserMapper.selectByPrimaryKey", 1000);
	        System.out.println(user);
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        sqlSession.close();
	        sqlSession2.close();
	    }
	}
}
