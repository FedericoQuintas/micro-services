package de.claudiopoll.poll.domain;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import de.claudiopoll.poll.domain.Poll;
import de.claudiopoll.poll.domain.PollBuilder;
import de.claudiopoll.poll.exception.InvalidPollCreationException;

public class PollBuilderTest {

	@Test
	public void whenPollIsCreatedWithChannelIdThenPollHasChannelId() {

		Poll poll = new PollBuilder().withChannelId(1L).withId(100L).build();
		Assert.assertEquals(new Long(1), poll.getChannelId());
	}
	
	@Test
	public void whenPollIsCreatedWithIdThenPollHasId() {

		Poll poll = new PollBuilder().withChannelId(1L).withId(100L).build();
		Assert.assertEquals(new Long(100), poll.getId());
	}

	@Test
	public void whenPollAreCreatedWithoutChannelIdThenExceptionIsThrown() {

		try {
			new PollBuilder().withId(1L).build();
			fail();
		} catch (InvalidPollCreationException ex) {
			Assert.assertEquals("Invalid poll creation", ex.getMessage());
		}
	}
	
	@Test
	public void whenPollsAreCreatedWithoutIdThenExceptionIsThrown() {

		try {
			new PollBuilder().withChannelId(1L).build();
			fail();
		} catch (InvalidPollCreationException ex) {
			Assert.assertEquals("Invalid poll creation", ex.getMessage());
		}
	}

}
