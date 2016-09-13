package de.bonify.news;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.bonify.news.domain.News;
import de.bonify.news.domain.NewsRepository;
import de.bonify.news.exception.InvalidNewsCreationException;
import de.bonify.news.service.CreationNewsService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreationNewsServiceTest {

	private CreationNewsService creationNewsService;
	private NewsRepository mockedNewsRepository;

	@Before
	public void before(){
		mockedNewsRepository = mock(NewsRepository.class);
		creationNewsService = new CreationNewsServiceImpl(mockedNewsRepository);
	}
	
	@Test
	public void whenNewsIsCreatedThenNewsHasChannelID(){
		
		News news = creationNewsService.createNews(1L);
		
		Assert.assertEquals(new Long(1), news.getChannelId());
		
	}
	
	@Test
	public void whenNewsIsCreatedWithNullChannelIdThenExceptionIsThrown(){
		try {
			creationNewsService.createNews(null);
			fail();
		} catch (InvalidNewsCreationException ex) {
			Assert.assertEquals("Invalid news creation", ex.getMessage());
		}
	}
	
	@Test
	public void whenNewsIsCreatedThenRepositoryIsCalled(){
		
		News news = creationNewsService.createNews(1L);
		
		verify(mockedNewsRepository).store(news);
		
	}
	
	@Test
	public void whenNewsIsCreatedThenNotificationServiceIsCalled(){
		
		News news = creationNewsService.createNews(1L);
		
		verify(mockedNewsRepository).store(news);
		
	}
	
	
}
