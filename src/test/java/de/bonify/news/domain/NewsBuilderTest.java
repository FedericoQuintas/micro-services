package de.bonify.news.domain;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import de.bonify.news.exception.InvalidNewsCreationException;

public class NewsBuilderTest {

	@Test
	public void whenNewsIsCreatedWithChannelIdThenNewsHasChannelId() {

		News news = new NewsBuilder().withChannelId(1L).withId(100L).build();
		Assert.assertEquals(new Long(1), news.getChannelId());
	}
	
	@Test
	public void whenNewsIsCreatedWithIdThenNewsHasId() {

		News news = new NewsBuilder().withChannelId(1L).withId(100L).build();
		Assert.assertEquals(new Long(100), news.getId());
	}

	@Test
	public void whenNewsAreCreatedWithoutChannelIdThenExceptionIsThrown() {

		try {
			new NewsBuilder().withId(1L).build();
			fail();
		} catch (InvalidNewsCreationException ex) {
			Assert.assertEquals("Invalid news creation", ex.getMessage());
		}
	}
	
	@Test
	public void whenNewsAreCreatedWithoutIdThenExceptionIsThrown() {

		try {
			new NewsBuilder().withChannelId(1L).build();
			fail();
		} catch (InvalidNewsCreationException ex) {
			Assert.assertEquals("Invalid news creation", ex.getMessage());
		}
	}

}
