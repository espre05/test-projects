package della.jocker.repo;

import javax.jcr.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionContext {

	private final static ThreadLocal<SessionContext> threadLocal = new ThreadLocal<SessionContext>();
	private final Session session;
	private static Logger logger;

	public SessionContext(Session session) {
		logger = LoggerFactory.getLogger(getClass());
		this.session = session;
	}

	public static void newInstance() throws DaoException {
		if (threadLocal.get() != null) {
			throw new IllegalStateException("there is already an open session in this thread");
		}
		threadLocal.set(new SessionContext(createSession()));
	}

	private static Session createSession() throws DaoException {
		try {
			return RepositoryFacade.currentSession();
		} catch (DaoException e) {
			logger.error("", e);
			throw e;
		}
	}

	public static SessionContext getInstance() {
		if (threadLocal.get() == null) {
			throw new IllegalStateException("there is no open session in this thread");
		}
		return threadLocal.get();
	}

	public void commit() throws DaoException {
		try {
			session.save();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void close() throws DaoException {
		try {
			threadLocal.set(null);
			session.logout();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Session getSession() {
		return session;
	}

}
