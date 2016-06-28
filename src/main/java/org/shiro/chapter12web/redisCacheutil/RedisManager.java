package org.shiro.chapter12web.redisCacheutil;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {
	
	private String host = "123.101.0.89";
//	private String host = "192.168.3.250";
	
	private int port = 6379;
	
	// 0 - never expire (unit:second)
	private int expire = 180;
	
	//timeout for jedis try to connect to redis server, not expire time! In milliseconds
	private int timeout = 1000;
	
	private String password = "abc123";
//	private String password = "Redis.qingfu2015**";
	
	private static JedisPool jedisPool = null;
	
	public RedisManager(){
		
	}
	
	/**
	 * 初始化方法
	 */
	public void init(){
		if(jedisPool == null){
			if(password != null && !"".equals(password)){
				
		        
				jedisPool = new JedisPool(getJedisPoolConfig(), host, port, timeout, password);
				
			}else if(timeout != 0){
				
				jedisPool = new JedisPool(getJedisPoolConfig(), host, port,timeout);
				
			}else{
				jedisPool = new JedisPool(getJedisPoolConfig(), host, port);
			}
			
		}
	}
	
	public JedisPoolConfig getJedisPoolConfig(){
		
		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxTotal(6);//设置最大连接数  
//		config.setmax
        config.setMaxIdle(10); //设置最大空闲数 
        config.setMaxWait(30);
        config.setTestOnBorrow(true);
        config.setMaxActive(50);
        
        return config;
	}
	
	/**
	 * get value from redis
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key){
		
		byte[] value = null;
		Jedis jedis = jedisPool.getResource();
		try{
			value = jedis.get(key);
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	public static void main(String[] args) {
		
		RedisManager redis = new RedisManager();
		redis.init();
//		redis.set("a".getBytes(), "abc".getBytes(), 0);
		System.out.println(new String(redis.get("a".getBytes())));
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(byte[] key,byte[] value){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.set(key,value);
			if(this.expire != 0){
				jedis.expire(key, this.expire);
		 	}
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] set(byte[] key,byte[] value,int expire){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.set(key,value);
			if(expire != 0){
				jedis.expire(key, expire);
		 	}
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * del
	 * @param key
	 */
	public void del(byte[] key){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.del(key);
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * flush
	 */
	public void flushDB(){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.flushDB();
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * size
	 */
	public Long dbSize(){
		Long dbSize = 0L;
		Jedis jedis = jedisPool.getResource();
		try{
			dbSize = jedis.dbSize();
		}finally{
			jedisPool.returnResource(jedis);
		}
		return dbSize;   
	}

	/**
	 * keys
	 * @param regex aaaaaaaaaaaaaaaaaaaaaaaaaaa
	 * @return
	 */
	public Set<byte[]> keys(String pattern){
		Set<byte[]> keys = null;
		Jedis jedis = jedisPool.getResource();
		try{
			keys = jedis.keys(pattern.getBytes());
		}finally{
			jedisPool.returnResource(jedis);
		}
		return keys; 
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
