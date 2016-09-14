package de.bonify.news.service;

import java.util.concurrent.ExecutorService;

import de.bonify.news.domain.News;
import de.bonify.news.domain.NewsBuilder;
import de.bonify.news.domain.NewsRepository;
import de.bonify.notification.service.NotificationService;

public class CreationNewsServiceImpl implements CreationNewsService {

	private NewsRepository newsRepository;
	private NotificationService mockedNotificationService;
	private ExecutorService executor;

	public CreationNewsServiceImpl(NewsRepository newsRepository, NotificationService mockedNotificationService, ExecutorService executorService) {
		this.newsRepository = newsRepository;
		this.mockedNotificationService = mockedNotificationService;
		this.executor = executorService;
	}

	@Override
	public News createNews(Long channelId) {

		News news = new NewsBuilder().withChannelId(channelId).build();

		newsRepository.store(news);
		
		sendNotification(channelId, news);

		return news;
	}

	private void sendNotification(Long channelId, News news) {

		executor.submit(() -> {
			mockedNotificationService.newsCreated(news.getId(), channelId);
		});
	}

}
