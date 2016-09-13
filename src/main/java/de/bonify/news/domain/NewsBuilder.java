package de.bonify.news.domain;

import de.bonify.news.exception.InvalidNewsCreationException;

public class NewsBuilder {

	private Long channelId;

	public NewsBuilder withChannelId(Long channelId) {
		this.channelId = channelId;
		return this;
	}

	public News build() {
		validate();
		return new News(channelId);
	}

	private void validate() {
		if (channelId == null){
			throw new InvalidNewsCreationException("Invalid news creation");
		}
	}

}
