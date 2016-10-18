package de.claudiopoll.poll.domain;


public class Poll {

	private Long channelId;
	private Long id;

	public Poll(Long channelId, Long id) {
		this.channelId = channelId;
		this.id = id;
	}

	public Long getChannelId() {
		return this.channelId;
	}

	public Long getId() {
		return this.id;
	}

}
