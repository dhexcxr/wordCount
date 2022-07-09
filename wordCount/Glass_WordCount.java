//Author Name: M. Corey Glass
//Date: 5.19.22
//Modified: 7.9.22
//Program Name: wordCount
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

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		File input = getFile(args, keyboard);
		Map<String, Integer> results = countWords(input);
		displayResults(results, keyboard);
		
		keyboard.close();
	}

	protected static File getFile(String[] args, Scanner keyboard) {
		File inputFile;
		
		if (args.length != 0) {
			// read file from command line args
			inputFile = new File(args[0]);			
		} else {
			// if no file on command line, ask user for file name
			System.out.print("Enter file name to count words ([Enter] for default macbeth.txt): ");
			
			String keyInput = keyboard.nextLine();
			inputFile = new File(keyInput);
			
			// if we cannot find the file the user entered use default file
			if (inputFile.length() == 0) {
				System.out.println("Using default file: " + DEFAULT_FILE);
				inputFile = new File(DEFAULT_FILE);
			}
		}
		
		// make sure file exists
		if (!inputFile.exists()) {
			System.out.println("Unable to find file: " + inputFile);
			System.out.println("Using default file: " + DEFAULT_FILE);
			inputFile = new File(DEFAULT_FILE);
		}
		
		return inputFile;
	}
	
	protected static Map<String, Integer> countWords(File input) {
		Map<String, Integer> results = new HashMap<>();

		// count word occurrences
		try (Scanner inputScanner = new Scanner(input)) {
			while (inputScanner.hasNext()) {
											// strip punctuation from the strings we scan from the file
				String string = (inputScanner.next()).replaceAll("(?:^')|[^- a-zA-Z']|(?:[-']$)", "").toLowerCase();
				results.merge(string, 1, (k, v) -> k + v);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return results;	
	}
	
	protected static void displayResults(Map<String, Integer> results, Scanner keyboard) {
				
		// sort results
		List<Entry<String, Integer>> sortedResults = new ArrayList<>(results.entrySet());
		sortedResults.sort(Entry.comparingByValue());
		
		// get the number of words the user wants to display
		int count;
		System.out.print("How many of the top words would you like to display ([Enter] for all)? ");
		String keyInput = keyboard.nextLine();
		if (keyInput.length() == 0) {
			System.out.println("Showing all words.");
			count = sortedResults.size();
		} else {
			String message;
			
			try {
				count = Integer.parseInt(keyInput);
				message = "Showing " + count + " words.";
			} catch (Exception e) {
				message = "Input error. Showing 20 words.";
				count = 20;
			}
			
			// if count is larger than
			
			if (count > sortedResults.size()) {
				message = "Input error. Showing all words.";
				count = sortedResults.size();
			}
			
			System.out.println(message);
		}
		
		// display results
		for (int i = sortedResults.size() - 1; i >= sortedResults.size() - count; i--) {
			System.out.println(sortedResults.get(i));
		}	
	}
}
