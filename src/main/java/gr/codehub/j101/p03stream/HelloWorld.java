package gr.codehub.j101.p03stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

	public static void main(String[] args) {
		logger.info("Hello world from the logger!");
		System.out.println("Hello world from the console!");
	}
}
