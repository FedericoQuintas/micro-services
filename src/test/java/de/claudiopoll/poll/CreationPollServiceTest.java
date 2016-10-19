package de.claudiopoll.poll;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.claudiopoll.notification.queue.NotificationQueue;
import de.claudiopoll.poll.domain.PollRepository;
import de.claudiopoll.poll.domain.Poll;
import de.claudiopoll.poll.exception.InvalidPollCreationException;
import de.claudiopoll.poll.service.CreationPollService;
import de.claudiopoll.poll.service.CreationPollServiceImpl;

public class CreationPollServiceTest {

	private CreationPollService creationNewsService;
	private PollRepository mockedNewsRepository;
	private NotificationQueue mockedNotificationQueue;

	@Before
	public void before(){
		mockedNewsRepository = mock(PollRepository.class);
		when(mockedNewsRepository.getNextId()).thenReturn(new Long(1));
		mockedNotificationQueue = mock(NotificationQueue.class);
		creationNewsService = new CreationPollServiceImpl(mockedNewsRepository);
	}
	
	@Test
	public void whenNewsIsCreatedThenNewsHasChannelID(){
		
		Poll news = creationNewsService.createNews(1L);
		
		Assert.assertEquals(new Long(1), news.getChannelId());
		
	}
	
	@Test
	public void whenNewsIsCreatedThenNewsHasID(){
		
		Poll news = creationNewsService.createNews(1L);
		
		Assert.assertEquals(new Long(1), news.getId());
		
	}
	
	@Test
	public void whenNewsIsCreatedWithNullChannelIdThenExceptionIsThrown(){
		when(mockedNewsRepository.getNextId()).thenReturn(null);
		try {
			creationNewsService.createNews(null);
			fail();
		} catch (InvalidPollCreationException ex) {
			Assert.assertEquals("Invalid poll creation", ex.getMessage());
		}
	}
	
	@Test
	public void whenNewsIsCreatedThenRepositoryIsCalled(){
		
		Poll news = creationNewsService.createNews(1L);
		
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
