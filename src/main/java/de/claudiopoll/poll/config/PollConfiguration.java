package de.claudiopoll.poll.config;

import java.util.concurrent.Executors;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import redis.clients.jedis.Jedis;
import de.claudiopoll.common.HibernateUtil;
import de.claudiopoll.common.JedisConfig;
import de.claudiopoll.notification.domain.NotificationRepository;
import de.claudiopoll.notification.persistence.NotificationRepositoryImpl;
import de.claudiopoll.notification.queue.NotificationQueue;
import de.claudiopoll.notification.queue.NotificationQueueImpl;
import de.claudiopoll.notification.sender.NotificationSenderJob;
import de.claudiopoll.notification.service.NotificationSenderJobImpl;
import de.claudiopoll.poll.domain.PollRepository;
import de.claudiopoll.poll.persistence.PollRepositoryImpl;
import de.claudiopoll.poll.resource.PollResource;
import de.claudiopoll.poll.service.CreationPollService;
import de.claudiopoll.poll.service.CreationPollServiceImpl;

@Configuration
@ComponentScan
@EntityScan("de.claudiopoll.poll")
@Import(JedisConfig.class)
public class PollConfiguration {

	@Bean
	public CreationPollService creationPolService(
			NotificationQueue notificationQueue, PollRepository pollRepository) {
		return new CreationPollServiceImpl(pollRepository, notificationQueue);
	}

	@Bean
	public PollRepository pollRepository(Jedis jedis) {
		return new PollRepositoryImpl(
				HibernateUtil.getSessionJavaConfigFactory(), jedis);
	}

	@Bean
	NotificationQueue notificationQueue(NotificationRepository notificationRepository) {
		return new NotificationQueueImpl(notificationRepository, Executors.newSingleThreadExecutor());
	}
	
	@Bean
	NotificationRepository notificationRepository(Jedis jedis){
		return new NotificationRepositoryImpl(jedis); 
	}

	@Bean
	public PollResource pollResource(CreationPollService creationPollService) {
		return new PollResource(creationPollService);
	}
	
	@Bean
	public NotificationSenderJob notificationSenderJob(NotificationRepository notificationRepository){
		return new NotificationSenderJobImpl(notificationRepository);
	}

}
