import java.util.ArrayList;

/**
 * This Class represents a Dictionary. All of the words in the dictionary are
 * stored in an ArrayList of strings. There are methods to add and get words from
 * the dictionary, find if a word or prefix is contained in the dictionary, and to
 * get the size of the dictionary.
 * @author Jacob George
 *
 */
public class Dictionary implements DictionaryInterface{
	private ArrayList<String> dictionary;
	
	/**
	 * Constructor for a  dictionary object
	 */
	public Dictionary(){
		dictionary = new ArrayList<String>();
	}
	
	/**
	 * Adds a word to the ArrayList of Strings dictionary
	 * @param word A string representing the word to be added
	 */
	public void addWord(String word){
		this.dictionary.add(word);
	}
	
	/**
	 * Gets a word from the dictionary
	 * @param index An integer representing the index of the desired word
	 * @return A string representing the desired word.
	 */
	public String getWord (int index){
		if(index >= 0 && index < dictionary.size()){
			return dictionary.get(index);
		}
		
		return "Invalid Index";
	}
	
	/**
	 * Returns the size of the dictionary
	 * @return An integer, the size of the dictionary ArrayList
	 */
	public int size(){
		return dictionary.size();
	}

	
	/**
	 * This method determines if a given word
	 * is in the Dictionary. (Public wrapper method)
	 * @param word the word to be checked
	 * @return true if the word is in this Dictionary,
	 * false otherwise
	 */
	public boolean findWord(String word){
		int low = 0;
		int high = this.dictionary.size() - 1; 
		//first call
		return findWord(word, low, high);
	}
	
	/**
	 * This method determines if a given word is
	 * in the Dictionary using a recursive binary search
	 * Preconditions: The dictionary is sorted alphabetically
	 * @param word the word to be checked
	 * @param low An integer, the lowest possible index
	 * that the could contain the desired word
	 * @param high An integer, the highest possible index that could
	 * contain the word
	 * @return True if the word is found, false otherwise
	 */
	private boolean findWord(String word, int low, int high){
		//keep checking for word while low <= high
		if(low <= high){
			int mid = (high+low)/2;
			//If the word is found return true
			if(this.dictionary.get(mid).compareTo(word) == 0){
				return true;
			}
			//if the word with index mid is "larger" than word, call again with high as mid-1
			else if(this.dictionary.get(mid).compareTo(word) > 0){
				return findWord(word, low, mid - 1);
			}
			//if the word with index mid is "smaller" than word, call again with low as mid+1
			else if(this.dictionary.get(mid).compareTo(word) < 0){
				return findWord(word, mid + 1, high);
			}
		}
		//Return false if the word is not found
		return false;
	}
	
	/**
	 * This method determines if a prefix is in the dictionary
	 * (Public wrapper method)
	 * @param prefix A string, the prefix to be searched for
	 * @return True if the prefix is found, false otherwise
	 */
	public boolean findPrefix(String prefix){
		int low = 0;
		int high = this.dictionary.size() - 1;
		return findPrefix(prefix, low, high);
	}
	
	/**
	 * This method determines if a prefix is in the dictionary using
	 * a recursive binary search algorithm
	 * @param prefix A string, the prefix to be searched for
	 * @param low An int, the lowest possible index that may contain prefix
	 * @param high An int, the highest possible index hat may contain the prefix
	 * @return True if the prefix is found, false otherwise
	 */
	private boolean findPrefix(String prefix, int low, int high){
		//Keep going if low<=high
		if(low <= high){
			int mid = (high + low)/2;
			//Get the word at mid
			String curWord = dictionary.get(mid);
			//If the word is longer than the prefix, trim it to the length of prefix
			if(curWord.length() >= prefix.length()){
				String dictPrefix = curWord.substring(0, prefix.length());
				//Return true if the trimmed word is equal to prefix
				if(dictPrefix.compareTo(prefix) == 0){
					return true;
				}
				//if the word with index mid is "larger" than prefix, call again with high as mid-1
				else if(dictPrefix.compareTo(prefix) > 0){
					return findPrefix(prefix, low, mid - 1);
				}
				//if the word with index mid is "smaller" than prefix, call again with low as mid+1
				else{
					return findPrefix(prefix, mid + 1, high);
				}
			}
			//if the word is not as long as the prefix, compare them
			//if the word with index mid is "larger" than prefix, call again with high as mid-1
			if(curWord.compareTo(prefix) > 0){
				return findPrefix(prefix, low, mid - 1);
			}
			//if the word with index mid is "smaller" than prefix, call again with low as mid+1
			else{
				return findPrefix(prefix, mid + 1, high);
			}
				
		}
		//If the prefix is not found, return false
		return false;
	}

}
