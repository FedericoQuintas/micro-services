package de.bonify.news.domain;


public class News {

	private Long channelId;
	private Long id;

	public News(Long channelId, Long id) {
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
