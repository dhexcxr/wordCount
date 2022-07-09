package wordCount;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class WordCountTest {
	
	// TODO see if we can turn these into new File and not Strings
	static final String DEFAULT_TEST_FILE = "testFile.txt";
	static final String DEFAULT_FILE = "macbeth.txt";

	@Test
	void getFileFromCmdArgsTest() {
		// test file name input from command line argument 
		String[] testArgs = {DEFAULT_TEST_FILE};
		File testInputFile = new File(DEFAULT_TEST_FILE);
		File returnedFile = Glass_WordCount.getFile(testArgs);
		assertTrue(testInputFile.equals(returnedFile));
	}
	
	@Test
	void getFileFromInputFileFoundTest() {
		// test file name input via user keyboard input
		String[] testArgs = new String[0];
		File inputFile = new File(DEFAULT_TEST_FILE);
		
		// TODO setup keyboard inputs via InputStream
		
		File returnedFile = Glass_WordCount.getFile(testArgs);
		assertTrue(inputFile.equals(returnedFile));
	}
	
	@Test
	void getFileFromInputFileNotFoundTest() {
		// test file name input via user keyboard input where the user's input is not found
		// and we load the default file
		String[] testArgs = new String[0];
		File inputFile = new File(DEFAULT_FILE);
		
		// TODO setup keyboard inputs via InputStream
		
		File returnedFile = Glass_WordCount.getFile(testArgs);
		assertTrue(inputFile.equals(returnedFile));
	}
	
	@Test
	void countWordsTest() {
		File inputFile = new File(DEFAULT_TEST_FILE);
		Map<String, Integer> expectedResults = new HashMap<>();
		expectedResults.put("a", 2);
		expectedResults.put("b", 1);
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
