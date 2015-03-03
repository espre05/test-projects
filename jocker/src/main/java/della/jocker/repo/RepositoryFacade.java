package della.jocker.repo;

import java.io.IOException;
import java.util.Hashtable;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;
import javax.jcr.version.VersionException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.core.jndi.RegistryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepositoryFacade {

	public static Session session;

	/** code from artima article* */

	// The repostory config file needed to initialize the Jackrabbit
	// repository. If the repository is obtained via a naming service,
	// for instance, then this information is not required for a client program.
	private static final String CONFIG_FILE = "repository.xml";

	// The directory for the repository files.
	public static String REPO_HOME_DIR = "repotest";

	// The repository's JNDI name
	private static final String REPO_NAME = "repo";

	// User ID and password to log into the repository
	private static final String USERID = "userid";

	private static final char[] PASSWORD = "".toCharArray();

	private static Context ctx = null;

	private final Session sess;

	private static Logger logger = LoggerFactory.getLogger(RepositoryFacade.class);

	public RepositoryFacade(Session session) {
		this.sess = session;
	}

	static Repository getRepository() throws DaoException {
		// if (ctx == null) {
		// try {
		// bindRepository();
		// } catch (NamingException e) {
		// throw new DAOException(e);
		// } catch (RepositoryException e) {
		// throw new DAOException(e);
		// }
		// }
		// try {
		// return (Repository) ctx.lookup(REPO_NAME);
		// } catch (NamingException e) {
		// throw new DAOException(e);
		// }

		try {
			return new TransientRepository(CONFIG_FILE, REPO_HOME_DIR);
		} catch (IOException e) {
			throw new DaoException("error during Repository init: " + e);
		}
	}

	/**
	 * Bind the repository in a JNDI context so that other clients can access
	 * the repository via a JNDI lookup. This code would likely appear in a Web
	 * application, and not in a standalone program. It is shown here to provide
	 * an example how one might look up a repository instance from a naming
	 * registry.
	 */
	private static void bindRepository() throws NamingException, RepositoryException {
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.jackrabbit.core.jndi.provider.DummyInitialContextFactory");
		environment.put(Context.PROVIDER_URL, "localhost");
		ctx = new InitialContext(environment);
		RegistryHelper.registerRepository(ctx, REPO_NAME, CONFIG_FILE, REPO_HOME_DIR, true);
	}

	public static Session currentSession() throws DaoException {
		if (session != null)
			return session;
		try {
			Repository repository = getRepository();
			session = repository.login(new SimpleCredentials(USERID, PASSWORD));
		} catch (LoginException e) {
			throw new DaoException(e);
		} catch (RepositoryException e) {
			throw new DaoException(e);
		}
		return session;
	}

	public static void saveSession() throws DaoException {
		try {
			session.save();
		} catch (AccessDeniedException e) {
			throw new DaoException(e);
		} catch (ItemExistsException e) {
			throw new DaoException(e);
		} catch (ConstraintViolationException e) {
			throw new DaoException(e);
		} catch (InvalidItemStateException e) {
			throw new DaoException(e);
		} catch (VersionException e) {
			throw new DaoException(e);
		} catch (LockException e) {
			throw new DaoException(e);
		} catch (NoSuchNodeTypeException e) {
			throw new DaoException(e);
		} catch (RepositoryException e) {
			throw new DaoException(e);
		}
	}

	public void addListener(EventListener nodeEventListener, int eventType) throws DaoException {
		addListener(nodeEventListener, eventType, "/");
	}

	public void addListener(EventListener nodeEventListener, int eventType, String pattern)
			throws DaoException {
		try {
			Workspace workspace = sess.getWorkspace();
			ObservationManager observationManager = workspace.getObservationManager();
			observationManager.addEventListener(nodeEventListener, eventType, pattern, true, null,
					null, false);
		} catch (UnsupportedRepositoryOperationException e) {
			throw new DaoException(e);
		} catch (RepositoryException e) {
			throw new DaoException(e);
		}
	}

	public static RepositoryFacade instance() throws DaoException {
		return new RepositoryFacade(currentSession());
	}

	public static void closeSession() {
		if (session != null) {
			session.logout();
			session = null;
		} else {
			logger.warn("trying to close a Repository Session but there is no open one");
		}
	}

}
