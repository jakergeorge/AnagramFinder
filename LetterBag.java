import java.util.ArrayList;

/**
 * This class contains letters supplied by the user that will be used to make 
 * anagrams. The class contains methods to recursively create new words from 
 * the supplied letters and check if they are in the dictionary. It also has a method
 * to sort the resulting list of anagrams.
 * @author Jacob George
 *
 */
public class LetterBag implements LetterBagInterface{
	private ArrayList<String> anagrams = new ArrayList<String>();
	private String letters;
	
	/**
	 * Constructor for LetterBag class
	 * @param letters A string that represent the letters to make
	 * anagrams from
	 */
	public LetterBag(String letters){
		this.letters = letters;
		
	}
	
	/**
	 * This method determines the list of words that can be created
	 * from a given LetterBag object that are present in the
	 * provided Dictionary object dict.
	 * @param dict the Dictionary object to be used
	 * @return a list of valid words in alphabetical order
	 */
	public ArrayList<String> getAllWords(Dictionary dict) {
		findAnagrams("", dict, letters);
		sortAnagramList(0);
		return anagrams;
	}
	
	/**
	 * Sorts the ArrayList, anagrams, by alphabetical order using
	 * a recursive selection sort algorithm
	 * @param firstIndex An int, the first index to compare other elements
	 * of the ArrayList to
	 */
	private void sortAnagramList(int firstIndex){
		if(firstIndex < anagrams.size() - 1){
			int curMinIndex = firstIndex;
			String curMin = anagrams.get(firstIndex);
			for(int i = firstIndex + 1; i < anagrams.size(); i++){
				if(anagrams.get(i).compareTo(curMin) < 0){
					curMinIndex = i;
					curMin = anagrams.get(i);
				}
			}
			
			if(curMinIndex != firstIndex){
				anagrams.set(curMinIndex, anagrams.get(firstIndex));
				anagrams.set(firstIndex, curMin);
			}
			
			sortAnagramList(firstIndex + 1);
		}
	}
	
	/**
	 * Finds anagrams using a recursive algorithm. On each recursive call
	 * if the current word under construction is not a prefix of any words
	 * in the dictionary, then the recursive calls end
	 * @param word A string, the current word being constructed
	 * @param dict the Dictionary object to find anagrams in
	 * @param remainingLetters A string representing the letters 
	 * that can still be used to make the anagram
	 */
	private void findAnagrams(String word, Dictionary dict, String remainingLetters){
		/*If all specified letters have been used and the constructed word is found in the
		dictionary, add it to the list of anagrams*/
		if((word.length() == this.letters.length()) && dict.findWord(word)){
			boolean alreadyFound = false;
			//Only add the word the list of anagrams if it has not already been added
			for(int i = 0; i < anagrams.size(); i++){
				if(anagrams.get(i).equals(word)){
					alreadyFound = true;
				}
			}
			if(!alreadyFound){
				anagrams.add(word);
			}
		}
		else{
			//Go through all of the remaining letters and make all possible words
			for(int i = 0; i < remainingLetters.length(); i++){
				//skip duplicate letters
				boolean duplicateLetter = false;
				for(int j = 0; j < i; j++){
					if(remainingLetters.charAt(i) == remainingLetters.charAt(j)){
						duplicateLetter = true;
					}
				}
				//If it is not a duplicate letter
				if(!duplicateLetter){
					//Make a new word
					String newWord = word + remainingLetters.charAt(i);
					String curRemainingLetters;
					//Delete the letter used from the string of remaining letters
					if(i < remainingLetters.length() - 1){
						curRemainingLetters = remainingLetters.substring(0, i) + remainingLetters.substring(i + 1);
					}
					else{
						curRemainingLetters = remainingLetters.substring(0, i);
					}
					//If the current word is a prefix of the word in a dictionary,
					//keep building anagrams from that word
					if(dict.findPrefix(newWord)){
						findAnagrams(newWord, dict, curRemainingLetters);
					}
				}
			}
		}
	}
}
