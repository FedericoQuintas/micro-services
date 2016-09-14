package de.bonify.news.domain;

import de.bonify.news.exception.InvalidNewsCreationException;

public class NewsBuilder {

	private Long channelId;
	private Long id;

	public NewsBuilder withChannelId(Long channelId) {
		this.channelId = channelId;
		return this;
	}

	public News build() {
		validate();
		return new News(channelId, id);
	}

	private void validate() {
		validateField(channelId);
		validateField(id);
	}

	private void validateField(Long field) {
		if (field == null){
			throw new InvalidNewsCreationException("Invalid news creation");
		}
	}

	public NewsBuilder withId(Long id) {
		this.id = id;
		return this;
	}

}
