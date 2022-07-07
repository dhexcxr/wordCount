package wordCount;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

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
		fail("Not yet implemented");
	}
	
	@Test
	void displayResultsTest() {
		fail("Not yet implemented");
	}

}
