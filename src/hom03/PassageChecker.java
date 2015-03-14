package hom03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Jaden Young
 */
public class PassageChecker {
	private File inputFile;
	Scanner scanFile;
	private CircularQueue<LinkedStack<String>> passageQueue;
	
	/**
	 * Only constructor
	 * @param pathToFile Name of the file to search through. If the file 
	 * is stored in the NetBeans project folder, no path is needed, simply
	 * the name of the file
	 *  Ex: "test1.txt"
	 * @throws FileNotFoundException Thrown if the file cannot be found
	 */
	public PassageChecker(String pathToFile) throws FileNotFoundException {
		inputFile = new File(pathToFile);
		scanFile = new Scanner(inputFile);
		LinkedStack<String> currentPassage = new LinkedStack<>();
		while(scanFile.hasNextLine()) {
			String currentLine = scanFile.nextLine();
			if(hasEnd(currentLine)) {
				//passages stored in passage queue will be upside down
				passageQueue.enqueue(currentPassage);
				currentPassage = new LinkedStack<>();
			} else {
				currentPassage.push(currentLine);
			}
		}
	}
	
	//returns true when a string contains the word "end" with no other letters
	private boolean hasEnd(String input) {
		String onlyLetters = "";
		for(int i = 0; i < input.length(); i++) {
			if(Character.isLetter(input.charAt(i))) {
				onlyLetters += input.charAt(i);
			}
			if(onlyLetters.length() > 3)
				return false;
		}
		return onlyLetters.equalsIgnoreCase("end");
	}
	
	//returns a string that has all the words seperated only by single spaces
	//condenses multiple delimiters like ", " into one single space
	private String formatString(String input) {
		String formattedString = "";
		boolean isDelimiter = false;
		for(int i = 0; i < input.length(); i++) {
			if(Character.isLetter(input.charAt(i))) {
				formattedString += input.charAt(i);
				isDelimiter = false;
			} else if(isDelimiter) {
				//do nothing
			} else {
				formattedString += " ";
				isDelimiter = true;
			}
		}
		return formattedString;
	}
	
	//calls the format method to ensure both strings are formatted correctly
	// "word word word word"
	//then compares word by word for equality ignoring case
	//returns true if all words are equal, false if not
	private boolean compareStrings(String a, String b) {
		String formA = formatString(a);
		String formB = formatString(b);
		Scanner scanA = new Scanner(formA);
		Scanner scanB = new Scanner(formB);
		while(scanA.hasNext()) {
			if(!scanA.next().equalsIgnoreCase(scanB.next()))
				return false;
		}
		return true;
	}
	
	//returns true if the the stack reads the same from top down as it does
	//from bottom up
	//the reverse method is called so many times simply to produce copies 
	//to refrain from modifying the original array
	private boolean isSame(LinkedStack<String> inputStack) {
		LinkedStack<String> rightSideUp = inputStack.reverse();
		LinkedStack<String> upSideDown = rightSideUp.reverse();
		while(rightSideUp.getSize() > 0) {
			if(!compareStrings(rightSideUp.pop(), upSideDown.pop()))
				return false;
		}
		return true;
	}
	
	/**
	 * This is the bread and butter. The only public method besides the 
	 * constructor in the class. Will return a string containing the full text
	 * of each passage (minus "end"), along with stating whether or not the 
	 * passage reads the same top to bottom as it does bottom to top
	 * @return String containing formatted output
	 */
	@Override
	public String toString() {
		String output = "";
		for(int i = 0; i < passageQueue.size(); i++) {
			LinkedStack<String> tempPassage = passageQueue.first().reverse();
			boolean readsSame = isSame(tempPassage);
			for(int j = 0; j < passageQueue.first().getSize(); j++) {
				output += tempPassage.pop();
			}
			output += "\n ----- The passage reads the same both ways : "
					+ readsSame + " -----\n";
			passageQueue.rotate();
		}
		return output;
	}
}
