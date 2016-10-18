package de.claudiopoll.poll.domain;

import de.claudiopoll.poll.exception.InvalidPollCreationException;

public class PollBuilder {

	private Long channelId;
	private Long id;

	public PollBuilder withChannelId(Long channelId) {
		this.channelId = channelId;
		return this;
	}

	public Poll build() {
		validate();
		return new Poll(channelId, id);
	}

	private void validate() {
		validateField(channelId);
		validateField(id);
	}

	private void validateField(Long field) {
		if (field == null){
			throw new InvalidPollCreationException("Invalid poll creation");
		}
	}

	public PollBuilder withId(Long id) {
		this.id = id;
		return this;
	}

}
