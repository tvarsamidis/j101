package gr.codehub.j101.p02collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {
	private final static String LINE_DELIMITER = "------------------------------------------------------------";
	private static final Logger logger = LoggerFactory.getLogger(ListDemo.class);

	public static void main(String[] args) {
		createLists();
		otherListActions();
		conventionListActions();
		createLinkedLists();
	}

	private static void createLists() {
		logger.info("### LIST CREATE ACTIONS ###");

		List<String> stringList = Arrays.asList("Lars", "Simon");
		stringList.set(0, "John");

		var explicitStringList = new ArrayList<String>();
		explicitStringList.add("Lars");
		explicitStringList.add("Simon");
		explicitStringList.add("Mary");

		explicitStringList.forEach(i -> logger.info(i));
		logger.info(LINE_DELIMITER);

		List<String> newWayStringList = List.of("Lars", "Simon");
		newWayStringList.forEach(i -> logger.info(i));
		logger.info(LINE_DELIMITER);

	}

	private static void otherListActions() {
		logger.info("");
		logger.info("### LIST OTHER ACTIONS ###");

		List<String> l1 = createList();  // decoupling
		l1.sort(null);
		l1.forEach(logger::info);
		logger.info(LINE_DELIMITER);

		l1.sort((c1, c2) -> c2.compareTo(c1));
		l1.forEach(logger::info);
		logger.info(LINE_DELIMITER);

		//l1.sort((c1, c2) -> c1.compareToIgnoreCase(c2));
		l1.sort(String::compareToIgnoreCase);
		l1.forEach(logger::info);
		logger.info(LINE_DELIMITER);

		List<String> l4 = createList2();
		logger.info("Demo of remove");
		l4.removeIf(s -> s.toLowerCase().contains("x"));  // streams, lambda expression, functional programming
		l4.forEach(logger::info);

		logger.info(LINE_DELIMITER);
		l4.remove("Windows");
		String removed = l4.remove(0);
		l4.forEach(logger::info);
	}

	private static void conventionListActions() {
		logger.info("");
		logger.info("### LIST OTHER ACTIONS ###");
		List<String> list = createList2();
		Collections.sort(list);
		list.forEach(logger::info);

		logger.info(LINE_DELIMITER);
		Collections.sort(list);
		list.sort(null);
		list.forEach(logger::info);

		List<String> anotherList = Collections.emptyList();
		logger.info("List: {}.", anotherList);
		// Will generate an error as this is immutable
		// anotherList.add("Item 1");
		//logger.info("List: " + anotherList);
	}

	private static void createLinkedLists() {
		logger.info("");
		logger.info("### LINKEDLIST ACTIONS ###");

		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add("A");
		linkedList.add("B");
		linkedList.addLast("D");
		linkedList.addFirst("E");
		linkedList.add(2, "C"); // E, A, C, B, D, Y
		linkedList.add("Z");
		String itemAtPosition5 = linkedList.get(5);
		String itemRemoved = linkedList.set(5, "Y");
		logger.info("Filled linked list: {}", linkedList);

		linkedList.remove("B");
		linkedList.remove(3);
		linkedList.removeFirst();
		// linkedList.remove(0);
		linkedList.removeLast();
		// linkedList.remove(linkedList.size() - 1);
		linkedList.removeFirstOccurrence("E");
		linkedList.removeLastOccurrence("E");
		logger.info("Filled linked list after removals: {}", linkedList);

		linkedList.clear();
		ArrayList<String> a = new ArrayList<>(List.of("six", "seven"));
		linkedList.addAll(List.of("one", "two", "three", "four", "five", "six"));
		linkedList.removeAll(a);
		logger.info("Number Linked List: {}", linkedList);
		logger.info("Peek (returns but does not remove): {}", linkedList.peek());
		logger.info("Peek (returns but does not remove): {}", linkedList.get(0));
		logger.info("Poll (returns and removes, with null if not found) : {}", linkedList.poll());
		logger.info("Poll (returns and removes, with null if not found) : {}", linkedList.poll());
		logger.info("Pop (returns with Exception if not found): {}", linkedList.pop());
		logger.info("Add (returns boolean): {}",  linkedList.offer("twelve"));
	}

	private static List<String> createList() {
		return Arrays.asList("Windows", "Linux", "iOS", "Android", "Mac OS X");
	}

	private static List<String> createList2() {
		List<String> anotherList = new ArrayList<>();
		anotherList.addAll(Arrays.asList("Windows", "Linux", "iOS", "Android", "Mac OS X"));
		return anotherList;
	}
}
