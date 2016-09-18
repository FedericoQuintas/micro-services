package de.bonify.news;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.bonify.news.domain.News;
import de.bonify.news.domain.NewsRepository;
import de.bonify.news.exception.InvalidNewsCreationException;
import de.bonify.news.service.CreationNewsService;
import de.bonify.news.service.CreationNewsServiceImpl;
import de.bonify.notification.queue.NotificationQueue;

public class CreationNewsServiceTest {

	private CreationNewsService creationNewsService;
	private NewsRepository mockedNewsRepository;
	private NotificationQueue mockedNotificationQueue;

	@Before
	public void before(){
		mockedNewsRepository = mock(NewsRepository.class);
		when(mockedNewsRepository.getNextId()).thenReturn(new Long(1));
		mockedNotificationQueue = mock(NotificationQueue.class);
		creationNewsService = new CreationNewsServiceImpl(mockedNewsRepository, mockedNotificationQueue);
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
		when(mockedNewsRepository.getNextId()).thenReturn(null);
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
	public void whenNewsIsCreatedThenNotificationQueueIsCalled(){
		
		creationNewsService.createNews(1L);
		
		verify(mockedNotificationQueue).notify(Long.valueOf(1).toString());
		
	}
	
	@Test
	public void whenNewsIsCreatedThenRepositoryRetrivesNextId(){
		
		creationNewsService.createNews(1L);
		
		verify(mockedNewsRepository).getNextId();
		
	}
	
	
}
