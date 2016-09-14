package de.bonify.news;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.bonify.news.domain.News;
import de.bonify.news.domain.NewsRepository;
import de.bonify.news.exception.InvalidNewsCreationException;
import de.bonify.news.service.CreationNewsService;
import de.bonify.news.service.CreationNewsServiceImpl;
import de.bonify.notification.service.NotificationService;

public class CreationNewsServiceTest {

	private CreationNewsService creationNewsService;
	private NewsRepository mockedNewsRepository;
	private NotificationService mockedNotificationService;

	@Before
	public void before(){
		mockedNewsRepository = mock(NewsRepository.class);
		mockedNotificationService = mock(NotificationService.class);
		creationNewsService = new CreationNewsServiceImpl(mockedNewsRepository, mockedNotificationService, Executors.newSingleThreadExecutor());
	}
	
	@Test
	public void whenNewsIsCreatedThenNewsHasChannelID(){
		
		News news = creationNewsService.createNews(1L);
		
		Assert.assertEquals(new Long(1), news.getChannelId());
		
	}
	
	@Test
	public void whenNewsIsCreatedThenNewsHasID(){
		
		News news = creationNewsService.createNews(1L);
		
		Assert.assertEquals(new Long(1), news.getId());
		
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
		
		verify(mockedNotificationService).newsCreated(news.getId(), news.getChannelId());
		
	}
	
	
}
