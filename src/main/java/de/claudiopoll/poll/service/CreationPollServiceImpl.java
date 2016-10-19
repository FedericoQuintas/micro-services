package de.claudiopoll.poll.service;

import de.claudiopoll.poll.domain.Poll;
import de.claudiopoll.poll.domain.PollBuilder;
import de.claudiopoll.poll.domain.PollRepository;

public class CreationPollServiceImpl implements CreationPollService {

	private PollRepository pollRepository;

	public CreationPollServiceImpl(PollRepository newsRepository) {
		this.pollRepository = newsRepository;
	}

	@Override
	public Poll createNews(Long channelId) {
		
		Long nextId = pollRepository.getNextId();

		Poll news = new PollBuilder().withId(nextId).withChannelId(channelId).build();

		pollRepository.store(news);
		
		addNotification(channelId);

		return news;
	}

	private void addNotification(Long channelId) {

//		notificationQueue.notify(channelId.toString());
	}

}
