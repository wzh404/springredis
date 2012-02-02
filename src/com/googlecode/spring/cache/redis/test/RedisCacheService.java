package com.googlecode.spring.cache.redis.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {
	@Cacheable(value="testCache",key="#userId + 'RedisCacheService.printUserId'")
	public void printUserId(String userId){
		System.out.println("save cache |" + userId + "| RedisCacheService.printUserId");
	}
	
	@CacheEvict(value="testCache",key="#userId + 'RedisCacheService.printUserId'")
	public void flushUserId(String userId){
		System.out.println("flush cache |" +userId +"| RedisCacheService.printUserId");
	}
	
	@Cacheable(value="testCache",key="#userId + 'RedisCacheService.printUserId2'")
	public List<String> printUserId2(String userId){
		System.out.println("save cache |" + userId + "| RedisCacheService.printUserId2");
		List<String> l = new ArrayList<String>();
		l.add("wangqi");
		l.add("songwei");
		
		return l;
	}
	
	@CacheEvict(value="testCache",key="#userId + 'RedisCacheService.printUserId2'")
	public void flushUserId2(String userId){
		System.out.println("flush cache |" +userId +"| RedisCacheService.printUserId2");
	}
}
