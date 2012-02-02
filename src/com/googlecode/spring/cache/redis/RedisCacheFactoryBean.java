package com.googlecode.spring.cache.redis;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

public class RedisCacheFactoryBean implements FactoryBean<RedisCache>, BeanNameAware, InitializingBean{
	private String name = "";
	private RedisCache cache;
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public RedisCache getObject() throws Exception {
		// TODO Auto-generated method stub
				
		return this.cache;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		
		return cache.getClass();
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public void setBeanName(String arg0) {
		// TODO Auto-generated method stub
		if (!StringUtils.hasLength(this.name)) {
			setName(arg0);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub		
		if (cache == null){
			cache = new RedisCache(name);
		}
	}
}
