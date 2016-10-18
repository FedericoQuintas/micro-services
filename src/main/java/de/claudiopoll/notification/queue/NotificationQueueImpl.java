package de.claudiopoll.notification.queue;

import java.util.concurrent.ExecutorService;

import de.claudiopoll.notification.domain.NotificationRepository;

public class NotificationQueueImpl implements NotificationQueue {

	private NotificationRepository notificationRepository;

	private ExecutorService executorService;

	public NotificationQueueImpl(NotificationRepository notificationRepository,
			ExecutorService executorService) {
		this.notificationRepository = notificationRepository;
		this.executorService = executorService;
	}

	@Override
	public void notify(String channelId) {
		executorService.submit(() -> {
			notificationRepository.queue(channelId);
		});
	}

}
