package de.claudiopoll.notification.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;

import de.claudiopoll.channel.service.ChannelService;
import de.claudiopoll.notification.domain.NotificationRepository;
import de.claudiopoll.notification.sender.NotificationSenderJob;

public class NotificationSenderJobImpl implements NotificationSenderJob {

	private static final int BATCH_SIZE = 1000;
	private NotificationRepository notificationRepository;
	private ChannelService channelService;

	public NotificationSenderJobImpl(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@Scheduled(fixedRate = 50000)
	public void sendNotifications() {
		
		List<String> channelIds = notificationRepository.getMessages(BATCH_SIZE);
		
		Map<Long, Set<Long>> subscribers = channelService.getSubscribersByChannel(channelIds);
		
		//Enviar Notis (there are news in your channels)
		
	}
}
