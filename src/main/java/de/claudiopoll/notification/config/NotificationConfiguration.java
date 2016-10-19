package de.claudiopoll.notification.config;

import java.util.concurrent.Executors;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import de.claudiopoll.notification.domain.NotificationRepository;
import de.claudiopoll.notification.persistence.NotificationRepositoryImpl;
import de.claudiopoll.notification.queue.NotificationQueue;
import de.claudiopoll.notification.queue.NotificationQueueImpl;
import de.claudiopoll.notification.sender.NotificationSenderJob;
import de.claudiopoll.notification.sender.NotificationSenderJobImpl;

@Configuration
@ComponentScan
@EntityScan("de.claudiopoll.notification")
public class NotificationConfiguration {

	
	@Bean
	NotificationQueue notificationQueue(NotificationRepository notificationRepository) {
		return new NotificationQueueImpl(notificationRepository, Executors.newSingleThreadExecutor());
	}
	
	@Bean
	NotificationRepository notificationRepository(Jedis jedis){
		return new NotificationRepositoryImpl(jedis); 
	}
	
	@Bean
	public NotificationSenderJob notificationSenderJob(NotificationRepository notificationRepository){
		return new NotificationSenderJobImpl(notificationRepository);
	}
}
