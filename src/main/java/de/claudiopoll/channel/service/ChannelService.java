package de.claudiopoll.channel.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ChannelService {

	public Map<Long, Set<Long>> getSubscribersByChannel(List<String> channelId);

}
