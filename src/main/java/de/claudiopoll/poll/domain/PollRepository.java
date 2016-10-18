package de.claudiopoll.poll.domain;

public interface PollRepository {

	void store(Poll news);

	Long getNextId();

}
