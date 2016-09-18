package de.bonify.news.config;

import java.util.concurrent.Executors;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import redis.clients.jedis.Jedis;
import de.bonify.common.HibernateUtil;
import de.bonify.common.JedisConfig;
import de.bonify.news.domain.NewsRepository;
import de.bonify.news.persistence.NewsRepositoryImpl;
import de.bonify.news.resource.NewsResource;
import de.bonify.news.service.CreationNewsService;
import de.bonify.news.service.CreationNewsServiceImpl;
import de.bonify.notification.domain.NotificationRepository;
import de.bonify.notification.queue.NotificationQueue;
import de.bonify.notification.queue.NotificationQueueImpl;
import de.bonify.notification.sender.NotificationSenderJob;
import de.bonify.notification.service.NotificationSenderJobImpl;

@Configuration
@ComponentScan
@EntityScan("de.bonify.news")
@Import(JedisConfig.class)
public class NewsConfiguration {

	@Bean
	public CreationNewsService creationNewsService(
			NotificationQueue notificationQueue, NewsRepository newsRepository) {
		return new CreationNewsServiceImpl(newsRepository, notificationQueue);
	}

	@Bean
	public NewsRepository newsRepository(Jedis jedis) {
		return new NewsRepositoryImpl(
				HibernateUtil.getSessionJavaConfigFactory(), jedis);
	}

	@Bean
	NotificationQueue notificationQueue(NotificationRepository notificationRepository) {
		return new NotificationQueueImpl(notificationRepository, Executors.newSingleThreadExecutor());
	}

	@Bean
	public NewsResource newsResource(CreationNewsService creationNewsService) {
		return new NewsResource(creationNewsService);
	}
	
	@Bean
	public NotificationSenderJob notificationSenderJob(NotificationRepository notificationRepository){
		return new NotificationSenderJobImpl(notificationRepository);
	}

}
