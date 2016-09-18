package de.bonify.channel.persistence;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import redis.clients.jedis.Jedis;

import com.google.common.collect.Maps;

import de.bonify.channel.domain.ChannelRepository;

public class ChannelRepositoryImpl implements ChannelRepository {

	private static final String CHANNEL_SUBSCRIBERS = "c:s";
	private Jedis jedis;

	public ChannelRepositoryImpl(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public Map<Long, Set<Long>> getSuscribersByChannel(List<String> channelIds) {

		Map<Long, Set<Long>> subscribersByChannel = Maps.newHashMap();

		// search for bulk implementation
		for (String channelId : channelIds) {

			String key = new StringBuilder().append(CHANNEL_SUBSCRIBERS)
					.append(":").append(channelId).toString();

			Set<String> smembers = jedis.smembers(key);

			Set<Long> members = convertSet(smembers, s -> Long.valueOf(s));

			subscribersByChannel.put(Long.valueOf(channelId), members);
		}

		return subscribersByChannel;
	}

	public static <T, U> Set<U> convertSet(Set<T> from, Function<T, U> func) {
		return from.stream().map(func).collect(Collectors.toSet());
	}
}
