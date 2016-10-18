package de.claudiopoll.notification.persistence;

import java.util.List;

import redis.clients.jedis.Jedis;
import de.claudiopoll.notification.domain.NotificationRepository;

public class NotificationRepositoryImpl implements NotificationRepository {

	private Jedis jedis;
	
	private static final String NEWS_QUEUE = "n:q";

	public NotificationRepositoryImpl(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public void queue(String message) {
		jedis.rpush(NEWS_QUEUE, message);
	}

	@Override
	public List<String> getMessages(int batchSize) {
		return jedis.lrange(NEWS_QUEUE, 0, batchSize);
	}

}
