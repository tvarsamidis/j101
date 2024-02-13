package gr.codehub.j101.p01jvm.loader;

public class CustomClassLoaderExample {
	public static void main(String[] args) {
		// Get the class loader for this class
		ClassLoader customClassLoader = CustomClassLoaderExample.class.getClassLoader();

		// Print information about the class loader
		System.out.println("CustomClassLoaderExample class loader: " + customClassLoader);
		System.out.println("Parent class loader: " + customClassLoader.getParent());

		// Create a new class loader with a specific parent
		ClassLoader customParentClassLoader = new CustomClassLoader(customClassLoader);

		// Print information about the custom parent class loader
		System.out.println("Custom parent class loader: " + customParentClassLoader);
		System.out.println("Grandparent class loader: " + customParentClassLoader.getParent());
	}
}


