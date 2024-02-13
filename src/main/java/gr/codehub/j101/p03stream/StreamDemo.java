package gr.codehub.j101.p03stream;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamDemo {
	private static final String LINE_DELIMITER = "---------------------------------------";
	private static final Logger logger = LoggerFactory.getLogger(StreamDemo.class);
	private static final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) {
		streamCreateActions();
		streamBasicActions();
		streamIntermediateActions();
		streamFinalActions();
	}

	private static void streamCreateActions() {
		logger.info("");
		logger.info("STREAM CREATE ACTIONS");
		logger.info(LINE_DELIMITER);

		// Create an empty stream
		Stream<String> emptyStream = Stream.empty();

		// Get list of generated words as stream
		Stream<String> sampleStringStream1 = createSampleStringList();

		// Get a stream by splitting a String
		Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");

		// Stream can also be created of any type of Collection
		Collection<String> collection = Arrays.asList("a", "b", "c");
		Stream<String> streamOfCollection = collection.stream();

		// Array can also be a source of a Stream
		Stream<String> streamOfArray = Stream.of("a", "b", "c");

		// They can  be created out of an existing array or of a part of an array:
		String[] arr = new String[]{"a", "b", "c", "d", "e"};
		Stream<String> streamOfArrayFull = Arrays.stream(arr);
		Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

		// Stream Builder (trickier)
		Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

		// Creates a sequence of ten strings with the value “element”.
		Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);

		// Creates 20 elements starting from 40 then 42, 44, 46
		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

		// Stream of primitives
		IntStream intStream = IntStream.range(1, 3);
		LongStream longStream = LongStream.rangeClosed(1, 3);
	}

	private static void streamBasicActions() {
		logger.info("");
		logger.info("STREAM BASIC ACTIONS");
		logger.info(LINE_DELIMITER);

		// Get list of generated words as stream
		Stream<String> sampleStringStream1 = createSampleStringList();

		// Print content in its natural order
		logger.info("Print in natural order");
		logger.info(LINE_DELIMITER);
		sampleStringStream1.forEach(logger::info);

		// Get list of generated words as stream
		Stream<String> sampleStringStream2 = createSampleNameList(20).stream();
		logger.info("");

		// Print content in a sorted order
		logger.info("Print in a sorted order");
		logger.info(LINE_DELIMITER);
		sampleStringStream2.sorted().forEach(logger::info);
		logger.info("");

		// Concatenate Strings
		logger.info("Concatenate streams");
		logger.info(LINE_DELIMITER);
		Stream<String> sampleConcatStringStream1 = createSampleNameList(3).stream();
		Stream<String> sampleConcatStringStream2 = createSampleNameList(3).stream();
		Stream.concat(sampleConcatStringStream1, sampleConcatStringStream2).sorted().forEach(logger::info);
	}

	public static void streamIntermediateActions() {
		logger.info("");
		logger.info("STREAM INTERMEDIATE ACTIONS");
		logger.info(LINE_DELIMITER);

		// Joint example
		List<String> list = Stream.of("Monkey", "Lion", "Giraffe", "Lemur").filter(s -> s.startsWith("L")).map(
				String::toUpperCase).sorted().collect(Collectors.toList());
		logger.info("Joint example");
		logger.info(LINE_DELIMITER);
		list.forEach(logger::info);
		logger.info("");

		// Filter
		Stream<String> filterStream = Stream.of("Monkey", "Lion", "Giraffe", "Lemur").filter(s -> s.startsWith("L"));
		logger.info("Filter example");
		logger.info(LINE_DELIMITER);
		filterStream.forEach(logger::info);
		logger.info("");

		List<String> generatedNames = createSampleNameList(10);
		logger.info("Source list is: ");
		logger.info(LINE_DELIMITER);
		generatedNames.forEach(logger::info);
		logger.info("");

		// Limit
		Stream<String> limitStream = generatedNames.stream();
		logger.info("Limit example");
		logger.info(LINE_DELIMITER);
		limitStream.limit(5).forEach(logger::info);
		logger.info("");

		// Skip
		Stream<String> skipStream = generatedNames.stream();
		logger.info("Skip example");
		logger.info(LINE_DELIMITER);
		skipStream.skip(2).forEach(logger::info);
		logger.info("");

		// Distinct
		Stream<String> distinctStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Distinct example");
		logger.info(LINE_DELIMITER);
		distinctStream.distinct().forEach(logger::info);
		logger.info("");

		// Sorted
		Stream<String> sortedStream = generatedNames.stream();
		logger.info("Sorted example");
		logger.info(LINE_DELIMITER);
		sortedStream.sorted().forEach(logger::info);
		logger.info("");

		// Sorted with custom comparator
		Stream<String> sortedCustomStream = generatedNames.stream();
		logger.info("Sorted with custom comparator example");
		logger.info(LINE_DELIMITER);
		sortedCustomStream.sorted(Comparator.comparing(String::length).thenComparing(String::valueOf)).forEach(
				logger::info);
		logger.info("");

		// Map
		Stream<String> mapStream = generatedNames.stream();
		logger.info("Map example");
		logger.info(LINE_DELIMITER);
		mapStream.map(String::toLowerCase).forEach(logger::info);
		logger.info("");

		// Map to Integer, Double or Long
		Stream<String> mapToX = generatedNames.stream();
		logger.info("Map to Integer, Double or Long example");
		logger.info(LINE_DELIMITER);
		mapToX.mapToInt(String::length).forEach(s -> logger.info("{}", s));
		logger.info("");

		// FlatMap
		logger.info("Flattening a list of arrays example");
		logger.info(LINE_DELIMITER);
		List<String> list1 = Arrays.asList("One", "Two", "Three");
		List<String> list2 = Arrays.asList("Four", "Five", "Six");
		List<String> list3 = Arrays.asList("Seven", "Eight", "Nine");

		List<List<String>> listOfLists = Arrays.asList(list1, list2, list3);

		listOfLists.stream().flatMap(x -> x.stream()).forEach(s -> logger.info("{}", s));
		logger.info("");

		Stream<String> flatMapStream = generatedNames.stream();
		logger.info("Advanced FlatMap example");
		logger.info(LINE_DELIMITER);
		flatMapStream.flatMap(s -> s.chars().mapToObj(i -> (char) i)).forEach(s -> logger.info("{}", s));
		logger.info("");
	}

	public static void streamFinalActions() {
		logger.info("");
		logger.info("STREAM FINAL ACTIONS");
		logger.info(LINE_DELIMITER);

		// ForeachOrdered
		Stream<String> foreachOrderedStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("ForeachOrdered example");
		logger.info(LINE_DELIMITER);
		foreachOrderedStream.distinct().forEachOrdered(logger::info);
		logger.info("");

		// Collect to Set
		Stream<String> collectSetStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Collect to Set example");
		logger.info(LINE_DELIMITER);
		collectSetStream.collect(Collectors.toSet()).forEach(logger::info);
		logger.info("");

		// Collect to List
		Stream<String> collectListStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Collect to List example");
		logger.info(LINE_DELIMITER);
		collectListStream.collect(Collectors.toList()).forEach(logger::info);
		logger.info("");

		// Collect to general collections
		Stream<String> collectGeneralCollectionsStream = Stream.of("John", "Costas", "Costas", "Nick", "John",
																   "Costas");
		logger.info("Collect to general collections example");
		logger.info(LINE_DELIMITER);
		collectGeneralCollectionsStream.collect(Collectors.toCollection(LinkedList::new)).forEach(logger::info);
		logger.info("");

		// Collect to array
		Stream<String> collectArrayStream = Stream.of("John", "Costas", "Costas", "Nick", "John", "Costas");
		logger.info("Collect to array example");
		logger.info(LINE_DELIMITER);
		String[] namesArray = collectArrayStream.toArray(String[]::new);
		Arrays.stream(namesArray).forEach(logger::info);
		logger.info("");

		// Collect to Map
		Stream<String> collectMapStream = Stream.of("Costas", "John", "Mark", "Mary", "Kate", "Nick", "Costas", "Mary");
		logger.info("Collect to Map example");
		logger.info(LINE_DELIMITER);
		//@formatter:off
		collectMapStream
				.distinct()
				// Why use function identity? Answer:
				// https://stackoverflow.com/questions/28032827/java-8-lambdas-function-identity-or-t-t
				.collect(Collectors.toMap(Function.identity(), s -> (int) s.chars().distinct().count()))
				.forEach((k, v) -> logger.info("{}:{}", k, v));
		//@formatter:on
		logger.info("");

		// Collect grouping by
		Stream<String> collectGroupingByStream = Stream.of("John", "Costas", "Jacob", "Christine", "Catherine",
														   "Costas", "Nick", "John", "Costas");
		logger.info("Collect grouping by example");
		logger.info(LINE_DELIMITER);
		Map<Character, List<String>> groupingByMap = collectGroupingByStream.distinct().sorted().collect(
				Collectors.groupingBy(s -> s.charAt(0)));//
		logger.info("{}.", groupingByMap);
		logger.info("");

		// Collect grouping by downstream collector
		Stream<String> collectGroupingByDownstreamCollector = Stream.of("Costas", "John", "Mark", "Mary", "Kate",
																		"Nick", "Costas", "Mary");
		logger.info("Collect grouping by downstream collector example");
		logger.info(LINE_DELIMITER);
		Map<Character, Long> groupingByDownstreamMap = collectGroupingByDownstreamCollector.distinct().sorted().collect(
				Collectors.groupingBy(s -> s.charAt(0), Collectors.counting()));
		logger.info("{}.", groupingByDownstreamMap);
		logger.info("");

		// Collectors joining()
		Stream<String> collectJoining = Stream.of("Costas", "John", "Mark", "Mary", "Kate", "Nick", "Costas", "Mary");
		logger.info("Collect by joining()");
		logger.info(LINE_DELIMITER);
		String collectJoiningString = collectJoining.distinct().sorted().collect(
				Collectors.joining(" ", "Names: ", "."));
		logger.info("{}.", collectJoiningString);
		logger.info("");

		// Summarizing double example
		Stream<String> collectSummarizingDouble = Stream.of("Costas", "John", "Mark", "Mary", "Kate", "Nick", "Costas",
															"Mary");
		logger.info("Summarizing double");
		logger.info(LINE_DELIMITER);
		DoubleSummaryStatistics collectSummarizingDoubleResult = collectSummarizingDouble.distinct().sorted().collect(
				Collectors.summarizingDouble(s -> s.length()));
		logger.info("average:{}, sum:{}, count:{}, min:{}, max:{}.", collectSummarizingDoubleResult.getAverage(),
					collectSummarizingDoubleResult.getSum(), collectSummarizingDoubleResult.getCount(),
					collectSummarizingDoubleResult.getMin(), collectSummarizingDoubleResult.getMax());
		logger.info("");

		// Partitioning by example
		Stream<String> collectPartitioningBy = Stream.of("Costas", "John", "Mark", "Mary", "Kate", "Nick", "Costas",
														 "Mary");
		logger.info("Partitioning by");
		logger.info(LINE_DELIMITER);
		Map<Boolean, List<String>> collectPartitioningByMap = collectPartitioningBy.distinct().sorted().collect(
				Collectors.partitioningBy(s -> s.length() > 5));
		logger.info("{}.", collectPartitioningByMap);
		logger.info("");

		// Calculation
		logger.info(LINE_DELIMITER);
		OptionalDouble optionalDouble = IntStream.of(1, 2, 3, 4, 5, 6).average();
		logger.info("Average is {}.", optionalDouble);
		long optionalLong = LongStream.of(12344, 12112, 121212, 12121111).max().orElse(0);
		logger.info("Maximum value is {}.", optionalLong);
		logger.info(LINE_DELIMITER);
		logger.info("");

		// Reduce
		List<Invoice> invoices = Arrays.asList(new Invoice("A01", BigDecimal.valueOf(9.85), BigDecimal.valueOf(5)),
											   new Invoice("A02", BigDecimal.valueOf(24.99), BigDecimal.valueOf(3)),
											   new Invoice("A03", BigDecimal.valueOf(4.99), BigDecimal.valueOf(4)));

		logger.info("Reduce example");
		logger.info(LINE_DELIMITER);
		BigDecimal summary = invoices.stream().map(x -> x.getQuantity().multiply(x.getPrice())).reduce(BigDecimal.ZERO,
																									   BigDecimal::add);
		logger.info("Summary of invoices is {}.", summary);
		logger.info("");

		// Reduce with parallel
		List<Invoice> invoicesParallel = Arrays.asList(
				new Invoice("A01", BigDecimal.valueOf(10), BigDecimal.valueOf(10)),
				new Invoice("A02", BigDecimal.valueOf(10), BigDecimal.valueOf(10)),
				new Invoice("A03", BigDecimal.valueOf(10), BigDecimal.valueOf(10)));

		logger.info("Reduce example (parallel)");
		logger.info(LINE_DELIMITER);
		BigDecimal summaryParallel = invoicesParallel.parallelStream().map(x -> x.getQuantity().multiply(x.getPrice()))
													 .reduce(BigDecimal.ONE, BigDecimal::add);
		// when turning into parallel, it actually adds the BigDecimal.ONE on both threads, resulting in 303, instead of
		// the correct 300 total value
		logger.info("Summary of invoices is {}.", summaryParallel);
		logger.info("");

		// AnyMatch
		logger.info("AnyMatch example");
		logger.info(LINE_DELIMITER);
		Stream<String> anyMatchStream = Stream.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick",
												  "John", "Costas");
		boolean anyMatchFound = anyMatchStream.anyMatch(s -> s.startsWith("C"));
		logger.info("{} with character C.",
					anyMatchFound ? "Found at least one token starting" : "No token found " + "starting");
		logger.info("");

		// AllMatch
		logger.info("AllMatch example");
		logger.info(LINE_DELIMITER);
		Stream<String> allMatchStream = Stream.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick",
												  "John", "Costas");
		boolean allMatchFound = allMatchStream.allMatch(s -> s.startsWith("C"));
		logger.info("{} with character C.", allMatchFound ? "All tokens starting" : "Not all tokens starting");
		logger.info("");

		// NoneMatch
		logger.info("NoneMatch example");
		logger.info(LINE_DELIMITER);
		Stream<String> noneMatchStream = Stream.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas",
												   "Nick", "John", "Costas");
		boolean noneMatchFound = noneMatchStream.noneMatch(s -> s.startsWith("X"));
		logger.info("{} with character X.",
					noneMatchFound ? "There's no token found starting" : "Some tokens found starting");
		logger.info("");

		// FindAny
		logger.info("FindAny example");
		logger.info(LINE_DELIMITER);
		Stream<String> findAnyStream = Stream.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas", "Nick",
												 "John", "Costas");
		// When stream is parallel, result may vary
		Optional<String> findAny = findAnyStream.findAny();
		logger.info("{}.", findAny.isPresent() ? findAny.get() : findAny.orElseGet(() -> "None"));
		logger.info("");

		// FindFirst
		logger.info("FindFirst example");
		logger.info(LINE_DELIMITER);
		Stream<String> findFirstStream = Stream.of("John", "Costas", "Jacob", "Christine", "Catherine", "Costas",
												   "Nick", "John", "Costas");
		// When stream is parallel, result may vary
		Optional<String> findFirst = findFirstStream.filter(s -> s.length() > 5).sorted().findFirst();
		logger.info("{}.", findFirst.isPresent() ? findFirst.get() : findFirst.orElseGet(() -> "None"));
		logger.info("");
	}

	private static Stream<String> createSampleStringList() {
		String generatedWords = generator.getWords(30);
		return Pattern.compile(" ").splitAsStream(generatedWords);
		// return Arrays.stream(generatedWords.split(" "));
	}

	private static List<String> createSampleNameList(int howMany) {
		List<String> names = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			names.add(generator.getFirstName());
		}
		return names;
	}
}
