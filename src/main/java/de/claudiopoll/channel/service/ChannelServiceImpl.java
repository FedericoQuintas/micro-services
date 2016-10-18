package de.claudiopoll.channel.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import de.claudiopoll.channel.domain.ChannelRepository;

public class ChannelServiceImpl implements ChannelService {

	private ChannelRepository channelRepository;

	public ChannelServiceImpl(ChannelRepository channelRepository) {
		this.channelRepository = channelRepository;
	}

	@Override
	public Map<Long, Set<Long>> getSubscribersByChannel(List<String> channelIds) {
		return channelRepository.getSuscribersByChannel(channelIds);
	}

}
