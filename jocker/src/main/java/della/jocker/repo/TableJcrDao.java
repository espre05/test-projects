package della.jocker.repo;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

public class TableJcrDao extends JcrDao {

	public static final String PERSISTENT_ID = "id";
	protected final String tableName;
	protected final String typeName;
	protected final String typeScopeQuery;

	public TableJcrDao(String tableName, String typeName) {
		this.tableName = tableName;
		this.typeName = typeName;
		typeScopeQuery = "//" + tableName + "//" + typeName;
	}

	protected Node getMainNode(String pattern) throws DaoException {
		try {
			return rootNode().getNode(pattern);
		} catch (PathNotFoundException e) {
			throw new DaoException(e);
		} catch (RepositoryException e) {
			throw new DaoException(e);
		}
	}

	public Node createMainNode() throws DaoException {
		try {
			if (find("//" + tableName).getSize() >= 1) {
				return null;
			}
			Node rootNode = rootNode();
			Node newNode = rootNode.addNode(tableName);
			rootNode.save();
			return newNode;
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
		}
	}

	public void removeAll() throws DaoException {
		remove(typeScopeQuery);
	}

	public Node getMainNode() throws DaoException {
		return getMainNode(tableName);
	}

	public NodeIterator selectAll() throws DaoException {
		return find(typeScopeQuery);
	}

	public Node find(Long id) throws DaoException {
		NodeIterator results = find(typeScopeQuery + "[" + TableJcrDao.PERSISTENT_ID + "='" + id
				+ "'" + "]");
		if (results.hasNext()) {
			return results.nextNode();
		}
		return null;
	}

	public Node create() throws DaoException {
		Node table = getMainNode();
		try {
			Node newNode = table.addNode(typeName);
			newNode.addMixin("mix:referenceable");
			table.save();
			return newNode;
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
		}
	}

	public long count() throws DaoException {
		return selectAll().getSize();
	}

	public OperationsQueue bind(JcrToListMapper mapper) throws DaoException {
		return super.bind(mapper, tableName);
	}

	public Node lookupByProperty(String property, String searchTerm) throws DaoException {
		return lookupByProperties(property, searchTerm);
	}

	/**
	 * @param strings
	 *            array of string, each sequential couple is "proprertyName ->
	 *            serchTerm"
	 */
	public Node lookupByProperties(String... strings) throws DaoException {
		XPathPropertiesQueryBuilder queryBuilder = getQueryBuilder();
		for (int i = 0; i < strings.length; i = i + 2) {
			queryBuilder.appendCondition(strings[i], strings[i + 1]);
		}
		return super.lookup(queryBuilder.getQuery());
	}

	public Node lookupInScope(String string) throws DaoException {
		return super.lookup(typeScopeQuery + string);
	}

	public NodeIterator findInScope(String query) throws DaoException {
		return super.find(typeScopeQuery + query);
	}

	public NodeIterator findByProperty(String property, Object searchTerm) throws DaoException {
		String scope = typeScopeQuery;
		return findByProperty(scope, property, searchTerm);
	}

	public XPathPropertiesQueryBuilder getQueryBuilder() {
		return new XPathPropertiesQueryBuilder(typeScopeQuery);
	}

}
