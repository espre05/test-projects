package della.jocker.repo;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.query.InvalidQueryException;
import javax.jcr.query.Query;
import javax.jcr.version.VersionException;

public class JcrDao {

	private String defaultQueryLanguage = Query.XPATH;
	private boolean versionable;

	protected void removeAll(NodeIterator iterator) throws DaoException {
		while (iterator.hasNext()) {
			Node node = (Node) iterator.next();
			try {
				node.remove();
			} catch (VersionException e) {
				throw new DaoException(e);
			} catch (LockException e) {
				throw new DaoException(e);
			} catch (ConstraintViolationException e) {
				throw new DaoException(e);
			} catch (RepositoryException e) {
				throw new DaoException(e);
			}
		}
	}

	/**
	 * Quick method to obtain a Query object from repository. Query uses the
	 * defined {@link #getDefaultQueryLanguage()}
	 * 
	 * @param queryString
	 * @return
	 * @throws DaoException
	 */
	public Query query(String queryString) throws DaoException {
		try {
			return getSession().getWorkspace().getQueryManager().createQuery(queryString,
					defaultQueryLanguage);
		} catch (InvalidQueryException e) {
			throw new DaoException("The query is malformed: " + queryString, e);
		} catch (RepositoryException e) {
			throw new DaoException(e);
		}
	}

	public String getDefaultQueryLanguage() {
		return defaultQueryLanguage;
	}

	/**
	 * 
	 * @param defaultQueryLanguage:
	 *            should be Query.XPATH or Query.SQL
	 */
	public void setDefaultQueryLanguage(String defaultQueryLanguage) {
		this.defaultQueryLanguage = defaultQueryLanguage;
	}

	public void print(NodeIterator nodes) throws RepositoryException {
		while (nodes.hasNext()) {
			Node node = (Node) nodes.next();
			System.out.println(node.getName());
		}
	}

	/**
	 * Quick method to obtain NodeIterator as result of a query execution
	 * 
	 */
	public NodeIterator find(String queryString) throws DaoException {
		try {
			return query(queryString).execute().getNodes();
		} catch (RepositoryException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * Quick method to obtain a single Node as result of a query execution
	 * 
	 */
	public Node lookup(String queryString) throws DaoException {
		try {
			NodeIterator nodes = query(queryString).execute().getNodes();
			return nodes.hasNext() ? nodes.nextNode() : null;
		} catch (RepositoryException e) {
			throw new DaoException(e);
		}
	}

	public void remove(String string) throws DaoException {
		NodeIterator iterator = find(string);
		removeAll(iterator);
		try {
			getSession().save();
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

	protected void setVersionable(boolean b) {
		versionable = b;
	}

	public boolean isVersionable() {
		return versionable;
	}

	protected Node rootNode() throws DaoException {
		try {
			return getSession().getRootNode();
		} catch (RepositoryException e) {
			throw new DaoException(e);
		}
	}

	private Session getSession() throws DaoException {
		return RepositoryFacade.currentSession();
	}

	public Node findByPath(String path) throws DaoException {
		try {
			return rootNode().getNode(path);
		} catch (PathNotFoundException e) {
			throw new DaoException(e);
		} catch (RepositoryException e) {
			throw new DaoException(e);
		} catch (DaoException e) {
			throw new DaoException(e);
		}
	}

	public OperationsQueue bind(JcrToListMapper mapper, String pattern) throws DaoException {
		JcrToListEventsQueue eventQueue = new JcrToListEventsQueue(mapper);
		eventQueue.bind(find(pattern + "/*"), "/" + pattern);
		return eventQueue;
	}

	public Node create(String relPath) throws DaoException {
		try {
			Node addNode = rootNode().addNode(relPath);
			rootNode().save();
			return addNode;
		} catch (ItemExistsException e) {
			throw new DaoException(e);
		} catch (PathNotFoundException e) {
			throw new DaoException(e);
		} catch (VersionException e) {
			throw new DaoException(e);
		} catch (ConstraintViolationException e) {
			throw new DaoException(e);
		} catch (LockException e) {
			throw new DaoException(e);
		} catch (RepositoryException e) {
			throw new DaoException(e);
		} catch (DaoException e) {
			throw new DaoException(e);
		}
	}

	public NodeIterator findByProperty(String scope, String property, Object searchTerm)
			throws DaoException {
		StringBuffer sb = new StringBuffer(scope);
		sb.append("[");
		sb.append(property);
		sb.append("='");
		sb.append(searchTerm);
		sb.append("'");
		sb.append("]");
		return find(sb.toString());
	}

}
