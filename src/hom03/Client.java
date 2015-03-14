package hom03;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author jaden
 */
public class Client {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String response = "";
		final int PIGS = 0;
		final int FLY = 1;
		System.out.println("Enter the name of a file to check > ");
		while(PIGS != FLY) {
			try {
				PassageChecker passageCheck  = new PassageChecker(scan.next());
			} catch (FileNotFoundException e) {
				System.out.println("Sorry, couldn't find that file. Try again"
						+ " > ");
			}
		}
		
	}
}
