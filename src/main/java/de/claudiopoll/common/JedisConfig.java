package de.claudiopoll.common;

import org.springframework.context.annotation.Bean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConfig {

	private static final String LOCALHOST = "localhost";

	@SuppressWarnings("resource")
	@Bean
	public Jedis jedis() {
		return new JedisPool(new JedisPoolConfig(), LOCALHOST, 6379)
				.getResource();
	}
}
