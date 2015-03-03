package net.della.stuff.generic.concurrent;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class OperationsQueue {

	protected final LinkedBlockingQueue queue;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected OperationsQueue targetQueue;

	public OperationsQueue() {
		queue = new LinkedBlockingQueue();
	}

	public OperationsQueue(OperationsQueue targetQueue) {
		this();
		this.targetQueue = targetQueue;
	}

	public void schedule() {
		int delay = 150;
		schedule(delay);
	}

	/**
	 * 
	 * @param delay
	 *            : in milliseconds
	 */
	public void schedule(int delay) {
		ScheduledExecutorService scheduledExecutor = Executors
				.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleWithFixedDelay(runner(), 1000, delay,
				TimeUnit.MILLISECONDS);
	}

	public Runnable runner() {
		return new Runnable() {
			public void run() {
				try {
					processNext();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		};
	}

	public int size() {
		return queue.size();
	}

	public void enqueue(Iterator events) {
		while (events.hasNext()) {
			queue.add(events.next());
		}
	}

	public void enqueue(Object element) {
		queue.add(element);
	}

	protected Object poll() {
		return queue.poll();
	}

	protected boolean isEmpty() {
		return queue.isEmpty();
	}

	public void processNext() throws Exception {
		if (!isEmpty()) {
			process();
		}
	}

	protected abstract void process() throws Exception;

	public void addAll(List childs) {
		queue.addAll(childs);
	}

}
