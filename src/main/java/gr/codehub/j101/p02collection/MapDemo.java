package gr.codehub.j101.p02collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapDemo {
	private static final Logger logger = LoggerFactory.getLogger(MapDemo.class);

	public static void main(String... args) {
		createMap();
		otherMapActions();
		simpleMapDictionary();
		countWordsInText();
	}

	private static void createMap() {
		logger.info("### CREATE MAP ###");

		// empty map which is read-only (cannot be changed)
		Map<String, String> map = Map.of();

		// read-only map (cannot be changed)
		Map<String, String> map2 = Map.of("key1", "value1", "key2", "value2");

		// read-only
		Map<String, String> map3 = Map.ofEntries(Map.entry("key1", "value1"), Map.entry("key2", "value2"),
												 Map.entry("key100", "value100"));
		logger.info("{}", map3);

		Map<String, String> map4 = getNatureColors();
		// Remove an entry from a map
		map4.remove("mountain");

		// Process the map
		map4.forEach((k, v) -> logger.info("{}:{}", k, v));
	}

	private static Map<String, String> getNatureColors() {
		Map<String, String> map = new HashMap<>();
		map.put("sky", "blue");
		map.put("sea", "blue");
		map.put("mountain", "gray");
		map.put("desert", "brown");
		map.put("sky", "gray");
		return map;
	}

	private static void simpleMapDictionary() {
		logger.info("");
		logger.info("### SIMPLE MAP DICTIONARY ###");
		Map<String, String> myMap = new HashMap<>();
		myMap.put("hello", "geia sas");
		myMap.put("today", "simera");
		myMap.put("tomorrow", "avrio");

		String englishWord = "today";
		String translation = myMap.get(englishWord);
		logger.info("{} is translated to {}", englishWord, translation);

		logger.info("Iterate with lambda");
		myMap.forEach((k, v) -> logger.info("{}, {}", k, v));

		logger.info("Iterate with iterator");
		//for (Map.Entry<String, String> entry : myMap.entrySet()) {
		for (var entry : myMap.entrySet()) {
			logger.info("Key {} has value {}", entry.getKey(), entry.getValue());
		}

	}

	private static void otherMapActions() {
		logger.info("");
		logger.info("### MAP OTHER ACTIONS ###");

		Map<String, String> map = getNatureColors();
		// Convert keys to array
		String[] keysArray = map.keySet().toArray(new String[map.keySet().size()]);
		for (String key : keysArray) {
			logger.info("{}.", key);
		}
		logger.info("");

		// Convert keys to list
		List<String> keysList = new ArrayList<>(map.keySet());
		keysList.forEach(logger::info);
		logger.info("");

		// Get a default value if not found
		map.forEach((k, v) -> logger.info("{}:{}", k, v));
		logger.info("");
		logger.info("The sky color is {}", map.getOrDefault("sky", "black"));
		logger.info("The city color is {}", map.getOrDefault("city", "black"));
	}

	private static void countWordsInText() {
		String text = getTextFromSomeSource();
		String[] words = text.split("\\s+");
		Map<String, Integer> counter = new LinkedHashMap<>();
		for (String word : words) {
			word = word.toLowerCase();
			counter.put(word, counter.getOrDefault(word, 0) + 1);
		}
		for (var entry : counter.entrySet()) {
			logger.info("{} = {}", entry.getKey(), entry.getValue());
		}
		logger.info("================================================");
		logger.info("Reorder:");
		List<Map.Entry<String, Integer>> list = new ArrayList<>(counter.entrySet());
		list.sort(Map.Entry.comparingByValue());
		Collections.reverse(list);
		list.stream().forEach(e -> logger.info("{}={}", e.getKey(), e.getValue()));
	}

	private static String getTextFromSomeSource() {
		return String.join("\n", //
						   "To      be or not to be", //
						   "that is the old question", //
						   "and it is a fair question", //
						   "but to tweet or not to tweet", //
						   "that is the modern question", //
						   "to ask and to answer" //
						  );
	}

	//		private static String getTextFromSomeSourceVersion15() {
	//			return """
	//					My text
	//					is written
	//					in multiple lines.
	//
	//					iyfgiy
	//					     oihoh y
	//
	//					It is good""";
	//		}

}
