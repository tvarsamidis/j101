package gr.codehub.j101.p01jvm.reference;

import gr.codehub.j101.p01jvm.model.Person;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferences {
	private static ReferenceQueue<Person> referenceQueue = new ReferenceQueue<>();

	public static void main(String[] args) throws InterruptedException {
		phantomDemo();
	}

	public static void phantomDemo() throws InterruptedException {
		Person usefulPerson = new Person("Bob", 40);
		PhantomReference<Person> uneededPerson = new PhantomReference<>(usefulPerson, referenceQueue);
		System.out.println("Here is the unneeded person: " + uneededPerson.get());
		usefulPerson = null; // if we comment this out, the resource will not be cleaned up
		boolean personIsEnqueued = uneededPerson.enqueue(); // isEnqueued() is deprecated
		System.out.println("personIsEnqueued before GC: " + personIsEnqueued);
		System.gc();
		Thread.sleep(10000);
		personIsEnqueued = uneededPerson.enqueue(); // isEnqueued() is deprecated
		System.out.println("personIsEnqueued after GC: " + personIsEnqueued);
		Reference<?> queuedObject; // cannot be PhantomReference, cannot be <Person>
		int count = 0;
		while ((queuedObject = referenceQueue.poll()) != null) {
			count++;
		}
		if (count > 0) {
			// clean up the resource
			System.out.println("Resource is being cleaned up, count=" + count);
		}
		while ((queuedObject = referenceQueue.poll()) != null) {
			queuedObject.clear();
		}
		System.out.println("Now referenceQueue.poll() is " + referenceQueue.poll());
		System.out.println("Done!");
	}
}
