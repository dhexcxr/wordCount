package wordCount;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordCountTest {

	private static final String DEFAULT_TEST_FILE = "testFile.txt";
	private static final String DEFAULT_FILE = "macbeth.txt";
	private static final String TEST_FILE_NOT_FOUND = "notTheRightFile.txt";
	private static Scanner testInputScanner = new Scanner(System.in);
	
	private static final Map<String, Integer> UNSORTED_MAP;
	static {
		UNSORTED_MAP = new HashMap<>();
		UNSORTED_MAP.put("a", 5);
		UNSORTED_MAP.put("d", 1);
		UNSORTED_MAP.put("f", 2);
		UNSORTED_MAP.put("g", 1);
		UNSORTED_MAP.put("ten", 3);
		UNSORTED_MAP.put("words", 1);
		UNSORTED_MAP.put("here", 1);
	}
	
	private static ByteArrayOutputStream byteArrayOutputStream;
	private static PrintStream ps;
	private static PrintStream stdout;
	
	@BeforeEach
	void setupOutputCapture() {
		byteArrayOutputStream = new ByteArrayOutputStream();
		ps = new PrintStream(byteArrayOutputStream);
		stdout = System.out;
		System.setOut(ps);
	}

	@AfterEach
	void resetOutputToDefault() {
		System.setOut(stdout);
	}
	
	@Test
	void getFileFromCmdArgsTest() {
		// test file name input from command line argument 
		String[] testArgs = {DEFAULT_TEST_FILE};
		File testInputFile = new File(DEFAULT_TEST_FILE);
		
		File returnedFile = Glass_WordCount.getFile(testArgs, testInputScanner);
		assertTrue(testInputFile.equals(returnedFile));
	}
	
	@Test
	void getFileNotFoundFromCmdArgsTest() {
		// test file name input from command line argument where the user's input is not found
		// and we load the default file
		String[] testArgs = {TEST_FILE_NOT_FOUND};
		File testInputFile = new File(DEFAULT_FILE);
		
		File returnedFile = Glass_WordCount.getFile(testArgs, testInputScanner);
		assertTrue(testInputFile.equals(returnedFile));
	}
	
	@Test
	void getFileFromInputFileFoundTest() {
		// test file name input via user keyboard input
		String[] testArgs = new String[0];
		File inputFile = new File(DEFAULT_TEST_FILE);
		
		// simulate user input
		testInputScanner = new Scanner(new ByteArrayInputStream((DEFAULT_TEST_FILE + "\n").getBytes()));
		
		File returnedFile = Glass_WordCount.getFile(testArgs, testInputScanner);
		assertTrue(inputFile.equals(returnedFile));
	}
	
	@Test
	void getFileFromInputFileNotFoundTest() {
		// test file name input via user keyboard input where the user's input is not found
		// and we load the default file
		String[] testArgs = new String[0];
		File inputFile = new File(DEFAULT_FILE);

		// simulate user input
		testInputScanner = new Scanner(new ByteArrayInputStream((TEST_FILE_NOT_FOUND + "\n").getBytes()));
		
		File returnedFile = Glass_WordCount.getFile(testArgs, testInputScanner);
		assertTrue(inputFile.equals(returnedFile));
	}
	
	@Test
	void getFileFromInputChooseDefaultTest() {
		// test file name input via user keyboard input where the user's input is not found
		// and we load the default file
		String[] testArgs = new String[0];
		File inputFile = new File(DEFAULT_FILE);

		// simulate user input
		testInputScanner = new Scanner(new ByteArrayInputStream(("\n").getBytes()));
		
		File returnedFile = Glass_WordCount.getFile(testArgs, testInputScanner);
		assertTrue(inputFile.equals(returnedFile));
	}
	
	@Test
	void countWordsTest() {
		File inputFile = new File(DEFAULT_TEST_FILE);
		Map<String, Integer> expectedResults = new HashMap<>();
		expectedResults.put("a", 3);
		expectedResults.put("d", 1);
		expectedResults.put("f", 2);
		expectedResults.put("g", 1);
		expectedResults.put("ten", 1);
		expectedResults.put("words", 1);
		expectedResults.put("here", 1);
		
		Map<String, Integer> testResults = Glass_WordCount.countWords(inputFile);
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	void displayResultsTest() {
		fail("Not yet implemented");
	}

}
