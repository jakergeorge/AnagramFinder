import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class that runs the program. Reads a dictionary from a specified file and 
 * calls methods to construct anagrams from user input. Prints the anagrams
 * that are found in the dictionary	
 * @author Jacob George
 *
 */
public class FindWords {

	/**
	 * Main method with reads dictionary file, asks for user input,
	 * calls methods to create anagrams and calls method to print output
	 * @param args A string array of arguments, contains the name of the dictionary file
	 * @throws FileNotFoundException if the dictionary file cannot be read
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//Check to see that a filename is given
		if(args.length < 1){
			System.err.println("Error: Please supply the dictionary filename.");
			System.exit(0);
		}
		
		//Make file object
		File dictionaryFile = new File(args[0]);
		
		//Check to see if the file is readable
		if(!dictionaryFile.canRead()){
			System.err.printf("Error: Cannot read the file %s%n", dictionaryFile.getAbsolutePath());
			System.exit(0);
		}
		
		Scanner fileInput = new Scanner(dictionaryFile);
		
		Dictionary dict = new Dictionary();
		
		//Add every line of the file to the dictionary
		while(fileInput.hasNextLine()){
			dict.addWord(fileInput.nextLine());
		}
		
		//Ask user for input
		System.out.println("Please enter a string of characters (LETTERS ONLY):");
		//Verify letter
		Scanner userInput = new Scanner(System.in);
		String letterSeq = userInput.nextLine().toLowerCase();
		
		//Check that input only consists of letters
		boolean onlyLetters = true;
		
		for(int i = 0; i < letterSeq.length(); i++){
			if(!Character.isLetter(letterSeq.charAt(i))){
				onlyLetters = false;
			}
		}
		
		//Print an error if the input contains characters that are not letters
		if(!onlyLetters){
			System.err.println("Error: The string may only contain letters.");
			System.exit(0);
		}
		
		LetterBag letters = new LetterBag(letterSeq);
		
		//Find all of the anagrams
		ArrayList<String> anagrams = letters.getAllWords(dict);
		
		//Print the anagrams
		printWords(anagrams);
		
		fileInput.close();
		userInput.close();
	}
	
	/**
	 * Prints all of the anagrams found
	 * @param anagrams An arraylist of strings that contain
	 * all of the anagrams found in the dictionary
	 */
	public static void printWords(ArrayList<String> anagrams){
		//If anagrams were found, print them
		if(anagrams.size() > 0){
			System.out.println("Found " + anagrams.size() + " words");
		
			for(int i = 0; i < anagrams.size(); i++){
				System.out.println(anagrams.get(i));
			}
		}
		else{
			System.out.println("No words found");
		}
	}

}
