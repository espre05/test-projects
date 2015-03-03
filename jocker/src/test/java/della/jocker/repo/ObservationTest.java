package della.jocker.repo;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.junit.Test;

import della.jocker.util.BaseRepositoryTest;

public class ObservationTest extends BaseRepositoryTest {

	@Test
	public void testListenChanges() throws Exception {
		RepositoryFacade instance = RepositoryFacade.instance();
		instance.addListener(new EventListener() {

			public void onEvent(EventIterator events) {
				callback(events);
			}
		}, Event.NODE_ADDED);
		Node rootNode = getSession().getRootNode();
		rootNode.addNode("test");
		rootNode.save();
	}

	protected void callback(EventIterator events) {
		try {
			assertEquals("test", events.nextEvent().getPath());
		} catch (RepositoryException e) {
			throw new RuntimeException("test failed");
		}
	}

}
