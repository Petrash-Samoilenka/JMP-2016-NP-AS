package com.epam.jmp.classloader;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class StatelessClassLoaderTest {
	
	private static ClassLoader classloader;
	
	@BeforeClass
	public static void initClassloader() {
		classloader = new StatelessClassLoader("src/test/resources/stateless-classloader-test-data-0.1.0.jar");
	}
	
	@Test
	public void testLoadsMoneyClass() throws ClassNotFoundException {
		assertNotNull(classloader.loadClass("com.epam.jmp.classloader.test.Money"));
	}
	
	@Test
	public void testLoadsStaticCounterClass() throws ClassNotFoundException {
		assertNotNull(classloader.loadClass("com.epam.jmp.classloader.test.StaticCounter"));
	}
	
	@Test
	public void testLoadsMathConstantsClass() throws ClassNotFoundException {
		assertNotNull(classloader.loadClass("com.epam.jmp.classloader.test.MathConstants"));
	}
	
	@Test(expected = ClassNotFoundException.class)
	public void testDoesNotLoadMutableIntegerClass() throws ClassNotFoundException {
		assertNotNull(classloader.loadClass("com.epam.jmp.classloader.test.MutableInteger"));
	}
	
}
