package com.googlecode.spring.cache.redis;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCacheManager extends AbstractCacheManager{
	private Collection<? extends Cache> caches;
	
	public void setCaches(Collection<? extends Cache> caches) {
		this.caches = caches;
	}
	
	@Override
	protected Collection<? extends Cache> loadCaches() {
		// TODO Auto-generated method stub
		int i = 0;
		
		JedisPoolConfig config =  new  JedisPoolConfig();
		config.setMaxActive(100);
		config.setMaxIdle(20);
		config.setMaxWait(1000);
		config.setTestOnBorrow(false);
	 	          
		JedisPool pool = new JedisPool(config,  "localhost" );
		for (Cache cache : caches){
			Jedis jedis = pool.getResource();
			jedis.auth("wangqi");
			jedis.select(i++);
			
			RedisCache rc = (RedisCache)cache;
			rc.setCache(jedis);
		}
		
		return this.caches;
	}
}
