package gr.codehub.j101.p01jvm.reference;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftReferences {
	public static void main(String[] args) throws Exception {
		softDemo();
		System.out.println("Finished!");
	}

	private static void softDemo() throws Exception {
		Reference<String> softString = new SoftReference<>("Hello world");
		System.out.println(softString.get().toUpperCase());

		byte[] bytes = new byte[1_000_000_000];
		Reference<byte[]> softBytes = new SoftReference<>(new byte[1_000_000_000]);


		List<byte[]> list = new ArrayList<>();
		while (true) {
			try {
				list.add(new byte[10_000_000]);
			} catch (Error e) {
				System.out.println("Could not allocate memory");
				break;
			}
			System.out.printf("Available heap memory: %,d\n", Runtime.getRuntime().freeMemory());
			System.out.println(softBytes.get() != null ? "softBytes is still here" : "softBytes is gone");
		}
		System.out.println(softBytes.get() != null ? "softBytes is still here" : "softBytes is gone");
	}

}
