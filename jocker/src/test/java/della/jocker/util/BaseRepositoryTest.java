package della.jocker.util;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;

import net.della.stuff.generic.util.LogUtils;

import org.junit.After;
import org.junit.Before;

import della.jocker.repo.DaoException;
import della.jocker.repo.RepositoryFacade;

public abstract class BaseRepositoryTest {

	@Before
	public void setUp() throws Exception {
		LogUtils.out("setUp");
		// SessionContext.newInstance();
		RepositoryFacade.currentSession();
	}

	@After
	public void tearDown() throws Exception {
		LogUtils.out("tearDown");
		// SessionContext.getInstance().close();
	}

	protected void cleanUpAllRepo() throws RepositoryException, DaoException {
		Node rootNode = getSession().getRootNode();
		NodeIterator nodes = rootNode.getNodes();
		while (nodes.hasNext()) {
			Node node = nodes.nextNode();
			if (!"jcr:system".equals(node.getName())) {
				node.remove();
			}
		}
		rootNode.save();
	}

	protected static Node rootNode() throws RepositoryException, DaoException {
		return getSession().getRootNode();
	}

	protected static Session getSession() throws DaoException {
		return RepositoryFacade.currentSession();
	}

	protected static int countFirstLevelChilds(String namePattern) throws RepositoryException,
			DaoException {
		return (int) rootNode().getNodes(namePattern).getSize();
	}

	protected static NodeIterator executeQuery(String string) throws RepositoryException,
			DaoException {
		return queryManager().createQuery(string, Query.XPATH).execute().getNodes();
	}

	protected static QueryManager queryManager() throws RepositoryException, DaoException {
		return getSession().getWorkspace().getQueryManager();
	}

}
