package gr.codehub.j101.p01jvm;

import java.util.ArrayList;
import java.util.List;

public class MemoryError {
	public static void main(String[] args) {
		if (false) {
			stackErrors();
		} else {
			heapErrors();
		}
	}

	// for dumping, enable VM options
	// -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./data_jvm/mem.dump
	// -XX:OnOutOfMemoryError=".\data_jvm\oome_message.bat"
	// for XX:OnOutOfMemoryError use quotes and \ in Windows, not /
	private static void heapErrors() {
		try {
			int blockSize = 10_000_000;
			long blockCount = 0;
			List<byte[]> list = new ArrayList<>();
			while (true) {
				list.add(new byte[blockSize]);
				blockCount++;
				System.out.println("List size=" + String.format("%,d", blockCount * blockSize));
				System.out.printf("Available heap memory: %,d\n", Runtime.getRuntime().freeMemory());
			}
		} catch (Error e) {
			System.out.println("There was an error of: " + e);
			System.out.println("Saved it!");
		}
	}

	private static void stackErrors() {
		try {
			doSomething(0);
		} catch (Error e) {
			System.out.println("There was an error of: " + e);
			System.out.println("Saved it!");
		}
	}

	private static void doSomething(int depth) throws OutOfMemoryError {
		if (depth % 1000 == 0) {
			System.out.println("We are at stack depth " + depth);
		}
		doSomething(depth + 1);
	}
}
