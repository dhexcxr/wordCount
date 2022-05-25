//Author Name: M. Corey Glass
//Date: 5.19.22
//Program Name: WroneController
//Class Name: Glass_WordCount
//Purpose: count words in supplied file, display the words sorted from most frequent to least

package wordCount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Glass_WordCount {
	
	static final String DEFAULT_FILE = "macbeth.txt";
	static Scanner keyboard;

	public static void main(String[] args) {
		keyboard = new Scanner(System.in);
		
		File input = getFile(args);
		Map<String, Integer> results = countWords(input);
		displayResults(results);
		
		keyboard.close();
	}

	private static File getFile(String[] args) {
		File inputFile;
		
		if (args.length != 0) {
			// read file from command line args
			inputFile = new File(args[0]);			
		} else {
			// if no file on command line, ask user for file name
			System.out.print("Enter file name to count words: ");
			
			String keyInput = keyboard.nextLine();
			inputFile = new File(keyInput);
			
			// if we cannot find the file the user entered use default file
			if (!inputFile.exists()) {
				System.out.println("Unable to find file: " + inputFile);
				System.out.println("Using default file: " + DEFAULT_FILE);
				inputFile = new File(DEFAULT_FILE);
			}
		}
		return inputFile;
	}
	
	private static Map<String, Integer> countWords(File input) {
		System.out.println("We're reading the file");
		
		Map<String, Integer> results = new HashMap<>();

		// count word occurrences
		try (Scanner inputScanner = new Scanner(input)) {
			while (inputScanner.hasNext()) {
				// TODO change regex to not remove ' in the middle of words, or something
//				String string = (inputScanner.next()).replaceAll("^(\')[^ a-zA-Z\']", "").toLowerCase();
				String string = (inputScanner.next()).replaceAll("^'|[^- a-zA-Z']|[-']$", "").toLowerCase();
//				String string = (inputScanner.next()).replaceAll("^\\'", "").replaceAll("[^ a-zA-Z\']", "").toLowerCase();

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
		List<Entry<String, Integer>> sortedResults = new ArrayList<>(results.entrySet());
		sortedResults.sort(Entry.comparingByValue());
		
		// display results
		for (int i = sortedResults.size() - 1; i >= sortedResults.size() - 20; i--) {
			System.out.println(sortedResults.get(i));
		}
		
	}

}
