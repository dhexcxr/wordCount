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
		
		File input = new File("wordCount/Macbeth_ Entire_Play.html");
//		File dir = new File(".");
//		for (String filename : dir.list()) {
//			System.out.println(filename);
//		}
		
		return input;
	}
	
	private static Map<String, Integer> countWords(File input) {
		System.out.println("We're reading the file");
		
		Map<String, Integer> results = new HashMap<>();
		
		try {
			Scanner inputScanner = new Scanner(input);
			while (inputScanner.hasNext()) {
				String string = (inputScanner.next()).replaceAll("[^ a-zA-Z]", "");

				
				results.merge(string, 1, (k, v) -> v + 1);
				
				// SLOW WAY
//				if (results.containsKey(string)) {
//					results.put(string, results.get(string) + 1);
//				} else {
//					results.put(string, 1);
//				}
				
//				results.computeIfPresent(string, (k, v) -> v + 1);
//				results.computeIfAbsent(string, k -> 1);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// count word occurrences
		return results;
		
	}
	
	private static void displayResults(Map<String, Integer> results) {
		System.out.println("We're sorting the results");
		
		// sort results
		
		// display results
		
	}

}
