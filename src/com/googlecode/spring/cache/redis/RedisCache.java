package com.googlecode.spring.cache.redis;

import java.io.Serializable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

import redis.clients.jedis.Jedis;
public class RedisCache implements Cache{
	private Jedis cache;
	private final String name;
	
	public RedisCache(String name){
		this(null,name);
	}
	
	public RedisCache(Jedis cache,String name){
		this.cache = cache;
		this.name = name;
	}
	
	public void setCache(Jedis cache){
		this.cache = cache;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		cache.flushDB();
	}

	@Override
	public void evict(Object arg0) {
		System.out.println("evict - " + arg0.toString());
		// TODO Auto-generated method stub
		Item key = new Item(arg0);
		byte[] k = serializeObject(key);
		
		cache.del(k);
	}

	@Override
	public ValueWrapper get(Object arg0) {
		System.out.println("get - " + arg0.toString());
		
		// TODO Auto-generated method stub
		if ( arg0 == null ) {
			return null;
		}
		Item key = new Item(arg0);
		byte[] k = serializeObject(key);
		byte[] result = cache.get( k );
		if ( result != null ) {
			Item vi = (Item)deserializeObject(result);
			ValueWrapper v = new SimpleValueWrapper(vi.getValue());
			
			return v;
		}
		
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Object getNativeCache() {
		// TODO Auto-generated method stub
		return cache;
	}

	@Override
	public void put(Object arg0, Object arg1) {
		System.out.println("put - " + arg0.toString() + ":" + (arg1 == null ? null : arg1.getClass().getName()));
		// TODO Auto-generated method stub
		Item key = new Item(arg0);
		Item val = new Item(arg1);
		byte[] k = serializeObject(key);
		byte[] v = serializeObject(val);
		
		cache.set( k, v );
	}
	
	protected byte[] serializeObject(Object obj){
		SerializingConverter sc = new SerializingConverter();
		return sc.convert(obj);
 	}
	
	protected Object deserializeObject(byte[] b){
 		DeserializingConverter dc = new DeserializingConverter();
		return dc.convert(b);
	}
	
	protected final static class Item implements Serializable{
		private static final long serialVersionUID = 1L;
		private final Object value;
		//private final long timestamp;
		
		Item(Object value) {
			this.value = value;
			//this.timestamp = Timestamper.next();;
		}
		
		public Object getValue() {
			return value;
		}
	}
}
