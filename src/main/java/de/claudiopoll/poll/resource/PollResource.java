package de.claudiopoll.poll.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.claudiopoll.poll.service.CreationPollService;

public class PollResource {
	
	private CreationPollService creationNewsService;

	public PollResource(CreationPollService creationNewsService) {
		this.creationNewsService = creationNewsService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/channels/{channel_id}/news", consumes = { "application/json" })
	@ResponseBody
	public void create(@PathVariable("channel_id") Long channelId) {
		creationNewsService.createNews(channelId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/news", consumes = { "application/json" })
	@ResponseBody
	public void getNews(@PathVariable("channel_id") Long channelId) {
		creationNewsService.createNews(channelId);
	}
}
