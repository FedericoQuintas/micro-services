package de.bonify.news.service;

import de.bonify.news.domain.News;
import de.bonify.news.domain.NewsBuilder;
import de.bonify.news.domain.NewsRepository;
import de.bonify.notification.queue.NotificationQueue;

public class CreationNewsServiceImpl implements CreationNewsService {

	private NewsRepository newsRepository;
	private NotificationQueue notificationQueue;

	public CreationNewsServiceImpl(NewsRepository newsRepository, NotificationQueue notificationQueue) {
		this.newsRepository = newsRepository;
		this.notificationQueue = notificationQueue;
	}

	@Override
	public News createNews(Long channelId) {
		
		Long nextId = newsRepository.getNextId();

		News news = new NewsBuilder().withId(nextId).withChannelId(channelId).build();

		newsRepository.store(news);
		
		addNotification(channelId);

		return news;
	}

	private void addNotification(Long channelId) {

		notificationQueue.notify(channelId.toString());
	}

}
