package gr.codehub.j101.p02collection;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
	private static final Logger logger = LoggerFactory.getLogger(SetDemo.class);
	private static final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) {
		createSets();
		createTreeSet();
		createLinkedHashSet();
		conventionOperations();
	}

	private static void createSets() {
		logger.info("");
		logger.info("### SET ACTIONS ###");
		// TreeSet will show set elements in sorted order
		// Set<String> sampleSet = new TreeSet<>();
		// LinkedHashSet will show set elements in inclusion order
		// Set<String> sampleSet = new LinkedHashSet<>();
		Set<String> sampleSet = new HashSet<>();
		sampleSet.add("one");
		sampleSet.add("one");
		sampleSet.add("two");
		sampleSet.add("three");
		sampleSet.add("one");
		sampleSet.add("two");
		logger.info("Unique set elements : {}", sampleSet.size());

		sampleSet.addAll(generateRandomStrings(10));

		logger.info("'three' does {}exist", sampleSet.contains("three") ? "" : "not ");
		logger.info("'four' does {}exist", sampleSet.contains("four") ? "" : "not ");
		logger.info("sampleSet: {}", sampleSet);

		sampleSet.remove("two"); // removes object with value "two"
		// sampleSet.remove(0); // NOTE: removes object with value 0

		var emptyReadOnlySet = Set.<String>of();
		Set<String> readOnlySet = getCitiesReadOnly();
		logger.info("Physical order");
		logger.info("--------------------");
		for (String item : readOnlySet) {
			logger.info("{}", item);
		}

	}

	private static void conventionOperations() {
		// remove duplicate objects from List
		logger.info("");
		logger.info("### conventionOperations ###");
		List<String> myList = new ArrayList<>();
		myList.add("Nick");
		myList.add("Nick");
		myList.add("Maria");
		myList.add("Nick");
		myList.add("John");
		myList.add("Maria");
		Set<String> mySet = new HashSet<>();
		mySet.addAll(myList);
		logger.info("Original list size is {}", myList.size());
		logger.info("Set size is {}", mySet.size());
		myList.clear();
		myList.addAll(mySet);
		logger.info("New list size is {}", myList.size());
	}

	private static void createTreeSet() {
		logger.info("");
		logger.info("### USE TREESET ###");

		TreeSet<String> sampleTreeSet = new TreeSet<>();
		sampleTreeSet.addAll(getCitiesReadOnly());
		sampleTreeSet.add("Item to be deleted");

		logger.info("Sorted order");
		logger.info("--------------------");
		for (String item : sampleTreeSet) {
			logger.info("{}", item);
		}
		logger.info("First value is {}", sampleTreeSet.first());
		logger.info("Last value is {}", sampleTreeSet.last());

		sampleTreeSet.remove("Item to be deleted");
		logger.info("After remove: {}", sampleTreeSet);

		sampleTreeSet.pollFirst();
		logger.info("After pollFirst: {}", sampleTreeSet);

		sampleTreeSet.pollLast();
		logger.info("After pollLast: {}", sampleTreeSet);
		logger.info("");
	}

	private static void createLinkedHashSet() {
		logger.info("");
		logger.info("### LINKEDHASHSET ###");

		LinkedHashSet<String> sampleLinkedHashSet = new LinkedHashSet<>(getCitiesReadOnly());
		logger.info("Set order: {}", sampleLinkedHashSet);
		logger.info("");

		LinkedHashSet<String> linkedset = new LinkedHashSet<>();
		linkedset.add("A");
		linkedset.add("B");
		linkedset.add("C");
		linkedset.add("D");
		linkedset.add("A");
		linkedset.add("E");
		linkedset.add("A");
		linkedset.add("B");
		linkedset.add("C");

		logger.info("Size of linkedset: {}.", linkedset.size());
		logger.info("Original linkedset: {}.", linkedset);
		logger.info("Remove 'D': {}.", linkedset.remove("D"));
		logger.info("Remove 'Z': {}.", linkedset.remove("Z"));
		logger.info("Contains 'A': {}.", linkedset.contains("A"));
		logger.info("Final linkedset: {}.", linkedset);
	}

	private static Set<String> getCitiesReadOnly() {
		return Set.of("Athens", "Thessaloniki", "Patra", "Herakleion", "Ioannina", "Larisa", "Volos", "Rodos");
	}

	private static Set<String> generateRandomStrings(int howMany) {
		Set<String> names = new HashSet<>();
		for (int i = 0; i < howMany; i++) {
			names.add(generator.getFirstName());
		}
		return names;
	}
}
