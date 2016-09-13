package de.bonify.news.domain;

public class News {

	private Long channelId;

	public News(Long channelId) {
		this.channelId = channelId;
	}

	public Long getChannelId() {
		return this.channelId;
	}

}
