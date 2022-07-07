package wordCount;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class WordCountTest {
	
	static final String DEFAULT_TEST_FILE = "testFile.txt";

	@Test
	void getFileFromCmdArgsTest() {
		String[] testArgs = {"testFile.txt"};
		File inputFile = new File(DEFAULT_TEST_FILE);
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
