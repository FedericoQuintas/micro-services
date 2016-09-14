package de.bonify.news.service;

import de.bonify.news.domain.News;
import de.bonify.news.domain.NewsBuilder;
import de.bonify.news.domain.NewsRepository;
import de.bonify.notification.service.NotificationService;

public class CreationNewsServiceImpl implements CreationNewsService {

	private NewsRepository newsRepository;
	private NotificationService mockedNotificationService;

	public CreationNewsServiceImpl(NewsRepository newsRepository, NotificationService mockedNotificationService) {
		this.newsRepository = newsRepository;
		this.mockedNotificationService = mockedNotificationService;
	}

	@Override
	public News createNews(Long channelId) {

		News news = new NewsBuilder().withChannelId(channelId).build();

		newsRepository.store(news);
		
		sendNotification(channelId, news);

		return news;
	}

	private void sendNotification(Long channelId, News news) {

		Runnable task = () -> {
		    mockedNotificationService.newsCreated(news.getId(), channelId);
		};

		Thread thread = new Thread(task);
		thread.start();
	}

}
