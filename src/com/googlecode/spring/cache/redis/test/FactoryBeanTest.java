package com.googlecode.spring.cache.redis.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class FactoryBeanTest implements FactoryBean<ConcurrentMap>, BeanNameAware, InitializingBean{
	private ConcurrentMap map;
	
	@Override
	public ConcurrentMap getObject() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("getObject - ");
				
		return this.map;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		System.out.println("getObjectType - ");
		
		return map.getClass();
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		System.out.println("isSingleton - ");
		
		return false;
	}

	@Override
	public void setBeanName(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("setBeanName - " + arg0);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterPropertiesSet - ");
		
		if (map == null){
			map = new ConcurrentHashMap();
		}
	}
}
