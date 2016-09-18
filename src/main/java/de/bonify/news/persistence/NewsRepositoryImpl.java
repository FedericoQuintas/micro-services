package de.bonify.news.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import redis.clients.jedis.Jedis;
import de.bonify.news.domain.News;
import de.bonify.news.domain.NewsRepository;

public class NewsRepositoryImpl implements NewsRepository {

	private SessionFactory sessionFactory;
	private Jedis jedis;

	public NewsRepositoryImpl(SessionFactory sessionFactory, Jedis jedis) {
		this.sessionFactory = sessionFactory;
		this.jedis = jedis;
	}

	@Override
	public void store(News news) {
		
		NewsPersistenceModel newsPersistenceModel = createPersistentModel(news);
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();

		session.save(newsPersistenceModel);
		
		session.getTransaction().commit();

	}

	private NewsPersistenceModel createPersistentModel(News news) {
		return new NewsPersistenceModel(news.getChannelId(), news.getId());
	}

	@Override
	public Long getNextId() {
		Long nextId = jedis.incr("n:co");
		return nextId;
	}

}
