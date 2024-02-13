package gr.codehub.j101.p02collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDemo {
	private static final Logger logger = LoggerFactory.getLogger(QueueDemo.class);

	public static void main(String[] args) {
		createQueues();
	}

	private static void createQueues() {
		logger.info("### QUEUES ###");

		Queue<String> testQueue1 = new LinkedList<>();
		PriorityQueue<String> testQueue2 = new PriorityQueue<>();
		//cannot do the following
		//PriorityQueue<String> testQueue3 = new LinkedList<String>();

		Queue<String> sampleQueue = new PriorityQueue<>();
		List<String> newList = createList();
		Collections.sort(newList);
		sampleQueue.addAll(newList);

		logger.info("Head: {}", sampleQueue.peek());
		logger.info("Head: {}", sampleQueue.element());
		logger.info("");

		if (sampleQueue.contains("Helen")) {
			logger.info("Helen is in the queue");
		}

		sampleQueue.remove();
		sampleQueue.poll();
		sampleQueue.offer("Bill");

		logger.info("After removing two elements and offering one:");
		for (String item : sampleQueue) {
			logger.info("{}", item);
		}

		logger.info("");
		logger.info("Queue contents:");
		Iterator itr = sampleQueue.iterator();
		while (itr.hasNext()) {
			logger.info("{}", itr.next());
		}
		logger.info("--- now repeat with removal ---");
		String head;
		// will extract all items one by one
		while ((head = sampleQueue.poll()) != null) {
			logger.info("{}", head);
		}
		logger.info("sampleQueue size is {}", sampleQueue.size());
	}

	private static List<String> createList() {
		return Arrays.asList("George", "Constantinos", "John", "Mary", "Helen", "Paul", "David", "Amelie", "Kate");
	}
}
