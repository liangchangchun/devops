package com.zww.test;

import java.util.HashMap;
import java.util.Map;

public class MapTester {
	public static void main(String[] args) {
		Student s1 =new Student("1","张三");
		Student s2 =new Student("2","李四");
		
	   Map<Student,String> map = new HashMap<Student,String>();
	   map.put(s1, "s1");
	   map.put(s2, "s2");
	   
	   
	   System.out.println(map);
	}
}
