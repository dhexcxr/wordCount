//Author Name: M. Corey Glass
//Date: 5.19.22
//Program Name: WroneController
//Class Name: Glass_WordCount
//Purpose: count words in supplied file, display the words sorted from most frequent to least

package wordCount;

import java.io.File;
import java.io.FileNotFoundException;

public class Glass_WordCount {

	public static void main(String[] args) {

		getFile(args);
		countWords();
		displayResults();
		
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
	
	private static void countWords() {
		System.out.println("We're reading the file");
		
		// count word occurrences
		
	}
	
	private static void displayResults() {
		System.out.println("We're sorting the results");
		
		// sort results
		
		// display results
		
	}

}
