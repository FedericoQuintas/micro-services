package de.bonify.notification.domain;

import java.util.List;

public interface NotificationRepository {

	void queue(String message);

	List<String> getMessages(int batchSize);

}
