package de.bonify.news;

import de.bonify.news.domain.News;
import de.bonify.news.domain.NewsBuilder;
import de.bonify.news.domain.NewsRepository;
import de.bonify.news.service.CreationNewsService;

public class CreationNewsServiceImpl implements CreationNewsService {

	private NewsRepository newsRepository;

	public CreationNewsServiceImpl(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	@Override
	public News createNews(Long channelId) {

		News news = new NewsBuilder().withChannelId(channelId).build();

		newsRepository.store(news);

		return news;
	}

}
