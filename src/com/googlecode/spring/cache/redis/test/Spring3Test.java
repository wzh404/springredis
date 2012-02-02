package com.googlecode.spring.cache.redis.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring3Test {
	 public static void main(String[] args) {
		 ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
		 RedisCacheService service = (RedisCacheService)context.getBean("redisCacheService");
		 service.printUserId("2");
		 service.flushUserId("2");
		 
		 List l = service.printUserId2("2");
		 
		 System.out.println("------------ok---------" + l.size());
	}

}
