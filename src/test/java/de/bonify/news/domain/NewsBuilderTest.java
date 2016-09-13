package de.bonify.news.domain;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import de.bonify.news.exception.InvalidNewsCreationException;

public class NewsBuilderTest {

	@Test
	public void whenNewsIsCreatedWithChannelIdThenNewsHasChannelId() {

		News news = new NewsBuilder().withChannelId(1L).build();
		Assert.assertEquals(new Long(1), news.getChannelId());
	}

	@Test
	public void whenNewsAreCreatedWithoutChannelIdThenExceptionIsThrown() {

		try {
			new NewsBuilder().build();
			fail();
		} catch (InvalidNewsCreationException ex) {
			Assert.assertEquals("Invalid news creation", ex.getMessage());
		}
	}

}
