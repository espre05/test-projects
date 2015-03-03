package della.jocker.spike;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.Session;

import org.junit.Before;
import org.junit.Test;

import della.jocker.repo.DaoException;
import della.jocker.repo.RepositoryFacade;
import della.jocker.util.BaseRepositoryTest;

public class RepositorySpike extends BaseRepositoryTest {

	@Before
	public void cleanup() throws DaoException {
		try {
			Session session = RepositoryFacade.currentSession();

			Node rootNode = session.getRootNode();
			NodeIterator allEntries = rootNode.getNodes();

			while (allEntries.hasNext()) {
				Node element = (Node) allEntries.next();
				if (!"jcr:system".equals(element.getName()))
					element.remove();
			}
			rootNode.save();
		} catch (Throwable e) {
			throw new DaoException(e);
		}
	}

	@Test
	public void emptyRepoShouldHaveOneNodeUnderRoot() throws Exception {
		NodeIterator nodes = rootNode().getNodes("*");
		assertEquals(1, nodes.getSize());
	}

	@Test
	public void addChildsAndGetChildNodes() throws Exception {
		Node rootNode = rootNode();
		Node firstLevel = rootNode.addNode("1");
		firstLevel.addNode("2");
		rootNode.save();

		assertEquals(2, countFirstLevelChilds("*"));
		assertEquals(1, countFirstLevelChilds("1"));
		assertEquals(0, countFirstLevelChilds("2"));
	}

	@Test
	public void testQueries() throws Exception {

		Node rootNode = rootNode();
		Node a = rootNode.addNode("a");
		a.setProperty("p1", "v1");
		Node aa = a.addNode("aa");
		aa.setProperty("p11", "v11");
		Node ab = a.addNode("ab");
		ab.setProperty("p12", "v12");
		rootNode.save();

		NodeIterator nodes;

		nodes = executeQuery("a");
		assertEquals(1, nodes.getSize());

		nodes = executeQuery("//a");
		Node node = nodes.nextNode();
		Property property = node.getProperty("p1");
		assertEquals("v1", property.getString());

		nodes = executeQuery("//b");
		assertEquals(0, nodes.getSize());

		nodes = executeQuery("//a//aa");
		assertEquals(1, nodes.getSize());
		nodes = executeQuery("//a//*");
		assertEquals(2, nodes.getSize());
		nodes = executeQuery("//a//dasda");
		assertEquals(0, nodes.getSize());

		nodes = executeQuery("//a//*b");
		Node firstResult = nodes.nextNode();
		assertEquals("v11", firstResult.getProperty("p11").getString());
		Node secondResult = nodes.nextNode();
		assertEquals("v12", secondResult.getProperty("p12").getString());

		nodes = executeQuery("//a//*[p11='v11']");
		Node exactMatch = nodes.nextNode();
		assertEquals("v11", exactMatch.getProperty("p11").getString());

		nodes = executeQuery("//a//aa[p11='v11']");
		exactMatch = nodes.nextNode();
		assertEquals("v11", exactMatch.getProperty("p11").getString());

		// NodeIterator nodes2 = queryManager().createQuery("select * from a",
		// Query.SQL).execute().getNodes();
		// assertEquals(1, count(nodes2));
	}

}
