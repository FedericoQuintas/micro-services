package de.bonify.channel.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import de.bonify.channel.domain.ChannelRepository;
import de.bonify.channel.persistence.ChannelRepositoryImpl;
import de.bonify.channel.service.ChannelService;
import de.bonify.channel.service.ChannelServiceImpl;

@Configuration
@ComponentScan
@EntityScan("de.bonify.channel")
public class ChannelConfiguration {

	@Bean
	public ChannelService creationNewsService(
			ChannelRepository channelRepository) {
		return new ChannelServiceImpl(channelRepository);
	}

	@Bean
	private ChannelRepository channelRepository(Jedis jedis) {
		return new ChannelRepositoryImpl(jedis);
	}
}
