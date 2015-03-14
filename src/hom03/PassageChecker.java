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
	
	public PassageChecker(String pathToFile) throws FileNotFoundException {
		inputFile = new File(pathToFile);
		scanFile = new Scanner(inputFile);
		readFile(inputFile);
	}
	
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
	
	
	private void readFile(File input) {
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
	
	private boolean isSame(LinkedStack<String> inputStack) {
		LinkedStack<String> rightSideUp = inputStack.reverse();
		LinkedStack<String> upSideDown = rightSideUp.reverse();
		while(rightSideUp.getSize() > 0) {
			if(!compareStrings(rightSideUp.pop(), upSideDown.pop()))
				return false;
		}
		return true;
	}
	
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
