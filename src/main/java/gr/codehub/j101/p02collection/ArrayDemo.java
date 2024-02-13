package gr.codehub.j101.p02collection;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayDemo {
	private final static String LINE_DELIMITER = "------------------------------------------------------------";
	private static final Logger logger = LoggerFactory.getLogger(ArrayDemo.class);
	private static final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) {
		// Default values
		arrayDefaultValues();
		// Integer array actions
		integerArrayActions();
		// String array actions
		stringArrayActions();
		// Some other array related actions
		otherArrayActions();
	}

	private static void arrayDefaultValues() {
		logger.info("### ARRAY DEFAULT VALUES ###");

		logger.info("String array default values:");
		var stringBuilder = new StringBuilder();
		var stringArray = new String[3];
		for (String val : stringArray) {
			stringBuilder.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder);
		logger.info(LINE_DELIMITER);

		logger.info("Integer array default values:");
		var stringBuilder2 = new StringBuilder();
		int[] integerArray = new int[3];
		for (int val : integerArray) {
			stringBuilder2.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder2);
		logger.info(LINE_DELIMITER);

		logger.info("Double array default values:");
		double[] doubleArray = new double[3];
		var stringBuilder3 = new StringBuilder();
		for (double val : doubleArray) {
			stringBuilder3.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder3);
		logger.info(LINE_DELIMITER);

		logger.info("Boolean array default values:");
		var booleanArray = new boolean[3];
		var stringBuilder4 = new StringBuilder();
		for (boolean val : booleanArray) {
			stringBuilder4.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder4);
		logger.info(LINE_DELIMITER);

		logger.info("Reference Array default values:");
		var myArray = new ArrayDemo[3];
		var stringBuilder5 = new StringBuilder();
		for (ArrayDemo val : myArray) {
			stringBuilder5.append(val).append(" ");
		}
		logger.info("{} ", stringBuilder5);
		logger.info(LINE_DELIMITER);
	}

	private static void integerArrayActions() {
		logger.info("");
		logger.info("### INTEGER ARRAY ACTIONS ###");
		int[] intArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] intArrayTheNewWay = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		var loggingContent = new StringBuilder();
		for (int i = 0; i < intArrayTheNewWay.length; i++) {
			loggingContent.append(intArrayTheNewWay[i]).append(", ");
		}
		logger.info(loggingContent.substring(0, loggingContent.length() - 2));
		int[][] int2DArray = {{1, 3, 5, 7, 9}, {2, 4, 6, 8, 10}, {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}};

		var logging2DContent = new StringBuilder();
		for (int i = 0; i < int2DArray.length; i++) {
			logging2DContent.append("[");
			for (int j = 0; j < int2DArray[i].length; j++) {
				logging2DContent.append(int2DArray[i][j]).append(", ");
			}
			logging2DContent.append("]");
		}
		logger.info(logging2DContent.substring(0, logging2DContent.length()));

		int[] clonedArray = intArray.clone();
		logger.info("Arrays generated with clone are {} and {}.", intArray == clonedArray ? "indentical" : "different",
					Arrays.equals(intArray, clonedArray) ? "equal" : "not equal");
	}

	private static void stringArrayActions() {
		logger.info("");
		logger.info("### STRING ARRAY ACTIONS ###");

		var stringNames = generateRandomStrings(20);
		printArrayContents(stringNames);
		var copiedStringNames = Arrays.copyOf(stringNames, 10);
		printArrayContents(copiedStringNames);
		var rangeCopiedStringNames = Arrays.copyOfRange(stringNames, 15, 18);
		printArrayContents(rangeCopiedStringNames);

		Arrays.sort(stringNames);
		printArrayContents(stringNames);

		Arrays.sort(stringNames, new Comparator<>() {
			@Override
			public int compare(final String o1, final String o2) {
				return o2.compareTo(o1);
			}
		});
		printArrayContents(stringNames);
	}

	private static void otherArrayActions() {
		logger.info("");
		logger.info("### OTHER ARRAY ACTIONS ###");

		var fillableArray = new int[10];
		Arrays.fill(fillableArray, 1);
		printArrayContents(fillableArray);

		int[] intArray = {1, 3, 5, 7, 9, 11};
		logger.info("7 was found at position {}.", Arrays.binarySearch(intArray, 7));
		logger.info("2 was found at position {}.", Arrays.binarySearch(intArray, 2));
		logger.info("8 was found at position {}.", Arrays.binarySearch(intArray, 8));
		logger.info("-2 was found at position {}.", Arrays.binarySearch(intArray, -2));
		logger.info("13 was found at position {}.", Arrays.binarySearch(intArray, 13));
	}

	private static void printArrayContents(int[] array) {
		for (int i = 0; i < array.length; i++) {
			logger.info("{}. {}", i + 1, array[i]);
		}
		logger.info(LINE_DELIMITER);
	}

	private static void printArrayContents(String[] array) {
		for (int i = 0; i < array.length; i++) {
			logger.info("{}. {}", i + 1, array[i]);
		}
		logger.info(LINE_DELIMITER);
	}

	private static String[] generateRandomStrings(int howMany) {
		String[] names = new String[howMany];
		for (int i = 0; i < howMany; i++) {
			names[i] = generator.getFirstName();
		}
		return names;
	}
}
