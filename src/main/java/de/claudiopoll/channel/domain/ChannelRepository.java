package de.claudiopoll.channel.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ChannelRepository {

	public Map<Long, Set<Long>> getSuscribersByChannel(List<String> channelIds);

}
