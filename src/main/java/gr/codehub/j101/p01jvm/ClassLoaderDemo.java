package gr.codehub.j101.p01jvm;

import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.util.ArrayList;

public class ClassLoaderDemo {

	public static void main(String[] args) throws ClassNotFoundException {
		customLoadersDemo();
		systemClassLoaderDemo();
		//loadNamedClassDemo();
	}

	private static void customLoadersDemo() {
		System.out.println("\n** customLoadersDemo **********************************************************");
		ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
		System.out.println("Class loader 1 toString: " + classLoader.toString());
		System.out.println("Class loader 1 name: " + classLoader.getName());
		System.out.println("Parent class loader 1: " + classLoader.getParent());
		ClassLoader classLoader2 = new CustomClassLoader(classLoader);
		System.out.println("Class loader 2: " + classLoader2);
		System.out.println("Class loader 2 name: " + classLoader2.getName());
		System.out.println("Parent class loader 2: " + classLoader2.getParent());
		System.out.println("Parent class loader 2 name: " + classLoader2.getParent().getName());
		System.out.println("Grandparent class loader 2: " + classLoader2.getParent().getParent());
		System.out.println("Grandparent class loader 2 name: " + classLoader2.getParent().getParent().getName());
	}

	private static void systemClassLoaderDemo() {
		System.out.println("\n** systemClassLoaderDemo **********************************************************");
		showLoaderInfo(ClassLoaderDemo.class);
		showLoaderInfo(DriverManager.class);
		showLoaderInfo(ArrayList.class);
		showLoaderInfo(System.class);
		showLoaderInfo(Logger.class);
		showLoaderInfo(java.util.logging.Logger.class);
		showLoaderInfo(System.Logger.class);
	}

	private static void showLoaderInfo(Class clazz) {
		ClassLoader loader = clazz.getClassLoader();
		System.out.println("Name="+clazz.getName() + "   Loader=" + (loader == null? "Bootstrap":loader));
	}

	private static void loadNamedClassDemo() throws ClassNotFoundException {
		System.out.println("\n** loadNamedClassDemo **********************************************************");
		Class<?> myClass = ClassLoader.getSystemClassLoader().loadClass("java.time.Month");
		for (Method m : myClass.getDeclaredMethods()) {
			System.out.println(m.getName());
		}
	}

}

class CustomClassLoader extends ClassLoader {
	public CustomClassLoader(ClassLoader parent) {
		super(parent);
	}
}
