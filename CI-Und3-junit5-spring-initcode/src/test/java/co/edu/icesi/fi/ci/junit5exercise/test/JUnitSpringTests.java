package co.edu.icesi.fi.ci.junit5exercise.test;

public class JUnitSpringTests {
	
	
	
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}

	public void testSampleServiceGetAccountDescription() {
		// Check if the return description has a 'Description:' string.
	}

	public void testSampleServiceGetAccountCode() {
		// Check if the return description has a 'Code:' string.
	}

	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}
