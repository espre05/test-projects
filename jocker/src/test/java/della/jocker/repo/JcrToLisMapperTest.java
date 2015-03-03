package della.jocker.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;

import org.junit.Before;
import org.junit.Test;

import della.jocker.util.BaseRepositoryTest;

public class JcrToLisMapperTest extends BaseRepositoryTest {

	public static final NullApplicationAdapter NULL_APPLICATION_ADAPTER = new NullApplicationAdapter();

	static final class NullApplicationAdapter implements ApplicationObjectAdapter {
		public Object toApplicationObject(Node node) {
			return node;
		}
	}

	private Node productsOnRepo;
	public JcrDao jcrDao;
	private List<Node> productsInMemory;
	private JcrToListMapper jcrToListMapper;
	private String pattern;

	@Before
	public void clear() throws Exception {
		pattern = "products";
		new JcrDao().remove(pattern);
		jcrDao = new JcrDao();
		productsOnRepo = jcrDao.rootNode().addNode("products");
		jcrDao.rootNode().save();
		assertEquals(0, productsOnRepo.getNodes().getSize());
		productsInMemory = new ArrayList();
		jcrToListMapper = new JcrToListMapper(JcrToLisMapperTest.NULL_APPLICATION_ADAPTER, productsInMemory);

	}

	@Test
	public void shouldDetectAddAndRemoveEvents() throws Exception {
		OperationsQueue eventQueue = jcrDao.bind(jcrToListMapper, pattern);

		productsOnRepo.addNode("product").setProperty("id", "001");
		Node p2 = productsOnRepo.addNode("product");
		p2.setProperty("id", "002");
		productsOnRepo.save();

		waitObservationThreadNotification();

		assertEquals(2, eventQueue.size());
		assertEquals(0, productsInMemory.size());
		eventQueue.processNext();
		eventQueue.processNext();
		assertEquals(2, productsInMemory.size());

		p2.remove();
		productsOnRepo.save();
		assertEquals(1, productsOnRepo.getNodes().getSize());

		waitObservationThreadNotification();

		assertEquals(1, eventQueue.size());
		eventQueue.processNext();
		assertEquals(1, productsInMemory.size());
		assertEquals("001", productsInMemory.get(0).getProperty("id").getString());
	}

	@Test
	public void testThatListIsInitializedIfElementsAreAlreadyInRepo() throws Exception {
		productsOnRepo.addNode("product").setProperty("id", "001");
		Node p2 = productsOnRepo.addNode("product");
		p2.setProperty("id", "002");
		productsOnRepo.save();

		OperationsQueue eventQueue = jcrDao.bind(jcrToListMapper, pattern);
		assertEquals(2, productsInMemory.size());

		p2.remove();
		productsOnRepo.save();
		assertEquals(1, productsOnRepo.getNodes().getSize());

		waitObservationThreadNotification();

		eventQueue.processNext();
		assertEquals(1, productsInMemory.size());
		assertEquals("001", productsInMemory.get(0).getProperty("id").getString());
	}

	private void waitObservationThreadNotification() throws InterruptedException {
		Thread.sleep(500);
	}

	@Test
	public void shouldProcessOnlyMonitoredEvents() throws Exception {
		OperationsQueue eventQueue = jcrDao.bind(jcrToListMapper, pattern);
		productsOnRepo.addNode("product").setProperty("id", "001");
		productsOnRepo.save();
		Node customersOnRepo = jcrDao.rootNode().addNode("customers");
		jcrDao.rootNode().save();
		customersOnRepo.addNode("customer.1");
		customersOnRepo.save();

		waitObservationThreadNotification();

		assertEquals(1, eventQueue.size());
		assertEquals(0, productsInMemory.size());
		eventQueue.processNext();
		eventQueue.processNext();
		assertEquals(1, productsInMemory.size());
	}

	@Test
	public void shouldCreateReferenceableNode() throws Exception {
		Node node = jcrDao.create("foo");
		node.addMixin("mix:referenceable");

		Property uuid = node.getProperty("jcr:uuid");
		assertNotNull(uuid);
	}

}
