package de.claudiopoll.poll.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import redis.clients.jedis.Jedis;
import de.claudiopoll.poll.domain.PollRepository;
import de.claudiopoll.poll.domain.Poll;

public class PollRepositoryImpl implements PollRepository {

	private SessionFactory sessionFactory;
	private Jedis jedis;

	public PollRepositoryImpl(SessionFactory sessionFactory, Jedis jedis) {
		this.sessionFactory = sessionFactory;
		this.jedis = jedis;
	}

	@Override
	public void store(Poll news) {
		
		NewsPersistenceModel newsPersistenceModel = createPersistentModel(news);
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();

		session.save(newsPersistenceModel);
		
		session.getTransaction().commit();

	}

	private NewsPersistenceModel createPersistentModel(Poll news) {
		return new NewsPersistenceModel(news.getChannelId(), news.getId());
	}

	@Override
	public Long getNextId() {
		Long nextId = jedis.incr("n:co");
		return nextId;
	}

}
