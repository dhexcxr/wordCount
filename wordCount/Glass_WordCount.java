//Author Name: M. Corey Glass
//Date: 5.19.22
//Program Name: WroneController
//Class Name: Glass_WordCount
//Purpose: count words in supplied file, display the words sorted from most frequent to least

package wordCount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Glass_WordCount {

	public static void main(String[] args) {

		File input = getFile(args);
		Map<String, Integer> results = countWords(input);
		displayResults(results);
		
	}

	private static File getFile(String[] args) {
		System.out.println("We're getting the file");
		
		// read file from command line arg

		// if no file on command line, ask user for file name

		// ask again if file not found
		
		File input = new File("wordCount/macbeth.txt");
		
		return input;
	}
	
	private static Map<String, Integer> countWords(File input) {
		System.out.println("We're reading the file");
		
		Map<String, Integer> results = new HashMap<>();

		// count word occurrences
		try (Scanner inputScanner = new Scanner(input)) {
			while (inputScanner.hasNext()) {
				// TODO change regex to not remove ' in the middle of words, or something
				String string = (inputScanner.next()).replaceAll("[^ a-zA-Z]", "").toLowerCase();

				results.merge(string, 1, (k, v) -> k + v);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return results;	
	}
	
	private static void displayResults(Map<String, Integer> results) {
		System.out.println("We're sorting the results");
		
		// sort results
		
		// display results
		
	}

}
