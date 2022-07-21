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


/**
 * Counts words from a text file. User can input filename on command line or interactively.
 * Words are stripped of leading and trailing punctuation, counted and stored in a Map,
 * then sorted. The user is asked how many of the top appearing words they would like displayed.
 * Those are then printed to System.out.
 *  
 */
public class Glass_WordCount {
	
	/**
	 * Default filename if user's inputted filename is not found.
	 */
	static final String DEFAULT_FILE = "macbeth.txt";

	/**
	 * Used to setup file and keyboard input, and coordinate the counting functions
	 * 
	 * @param args - String[], program command line arguments, user can specify filename of text file to count words from 
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		File input = getFile(args, keyboard);
		Map<String, Integer> results = countWords(input);
		displayResults(results, keyboard);
		
		keyboard.close();
	}

	/**
	 * Opens the file the user wants to count words from.
	 * Gets the file from the command line arguments if the user supplied them there.
	 * If not, we then ask the user to type in a filename. If we cannot find the filename
	 * provided on the command line or that the user types in we use a default file.
	 * 
	 * @param args - String[], program command line arguments, might include the filename the user provided on the command line
	 * @param keyboard - Scanner, for keyboard input
	 * 
	 * @return return the file we found in a File object
	 */
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
	
	/**
	 * Counts words from text File input.
	 * Opens Scanner on File input. While there is still more input to Scanner, we strip leading and trailing punctuation,
	 * set the string to lower case, and add the string to the results Map. If the string is already in the map,
	 * we increase the Map key value by 1.
	 * 
	 * @param input - File, contains file to count words from
	 * 
	 * @return return a Map containing each word found as the key and the count of that word as the value
	 */
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
	
	/**
	 * Display word counts on screen.
	 * Puts the result Map's EntrySet into an array, then sorts on EntrySet value.
	 * Allows the user to specify how many of the top words they would like to see.
	 * 
	 * @param results - Map<String, Integer>, contains found words and word count
	 * @param keyboard - Scanner, for keyboard input
	 */
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
