package de.claudiopoll.poll.service;

import de.claudiopoll.notification.queue.NotificationQueue;
import de.claudiopoll.poll.domain.PollRepository;
import de.claudiopoll.poll.domain.Poll;
import de.claudiopoll.poll.domain.PollBuilder;

public class CreationPollServiceImpl implements CreationPollService {

	private PollRepository newsRepository;
	private NotificationQueue notificationQueue;

	public CreationPollServiceImpl(PollRepository newsRepository, NotificationQueue notificationQueue) {
		this.newsRepository = newsRepository;
		this.notificationQueue = notificationQueue;
	}

	@Override
	public Poll createNews(Long channelId) {
		
		Long nextId = newsRepository.getNextId();

		Poll news = new PollBuilder().withId(nextId).withChannelId(channelId).build();

		newsRepository.store(news);
		
		addNotification(channelId);

		return news;
	}

	private void addNotification(Long channelId) {

		notificationQueue.notify(channelId.toString());
	}

}
