package della.jocker.repo;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import jin.collection.core.Iter;
import jin.collection.core.Operation;

public class JcrToListEventsQueue extends OperationsQueue {

	private final JcrToListMapper mapper;

	public JcrToListEventsQueue(final JcrToListMapper mapper) {
		this.mapper = mapper;
	}

	public void processNext() {
		if (queue.size() == 0) {
			return;
		}
		Event event = (Event) queue.poll();
		if (Event.NODE_ADDED == event.getType()) {
			mapper.processInsertEvent(event);
		} else if (Event.NODE_REMOVED == event.getType()) {
			mapper.processRemoveEvent(event);
		}
	}

	public void bind(NodeIterator actualNodes, String pattern) throws DaoException {
		Iter.forEach(actualNodes, new Operation() {
			public void execute(Object element) {
				try {
					Node node = (Node) element;
					mapper.insert(node);
				} catch (RuntimeDaoException e) {
					logger.warn("", e);
					throw e;
				}
			}
		});

		RepositoryFacade repo = RepositoryFacade.instance();

		repo.addListener(new EventListener() {
			public void onEvent(EventIterator events) {
				enqueue(events);
			}
		}, Event.NODE_ADDED, pattern);

		repo.addListener(new EventListener() {
			public void onEvent(EventIterator events) {
				enqueue(events);
			}
		}, Event.NODE_REMOVED);
	}

	public void process() {
	}

}
