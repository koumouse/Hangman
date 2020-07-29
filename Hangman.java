import java.util.*;
import java.io.*;

public class Hangman {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Welcome to Hangman!");
		
		final String myWord = getWord();
		String guessWord = "";
		String guessedLetters = "";
		int numSpaces = myWord.length();
		Scanner sc = new Scanner(System.in);
		int tryCounter = 0;
		int correctLetters = 0;
		boolean correct;
		
		//Creates word full of blanks corresponding to the hidden word
		for(int a=0; a<numSpaces; a++) {
			guessWord += "_ ";
		}
		
		//Main function of the game
		while (true) {
			System.out.println(guessWord);
			correct = false;
			System.out.print("Enter your guess: ");
			char guess = sc.next().charAt(0);
			
			//Checks to see if the input is a letter
			if (Character.toString(guess).matches("^[a-zA-z]*$")) {
				
				//Checks to see if the input has already been guessed
				if (guessedLetters.indexOf(guess) == -1) { 
					guessedLetters = guessedLetters + guess;
				
					//Iterates through word and replaces blanks with correct letters
					for (int a=0; a<numSpaces; a++) {
						if (myWord.charAt(a) == guess) {
							guessWord = guessWord.substring(0, a*2) + guess + guessWord.substring(a*2+1);
							correct = true;
							correctLetters++;
						}
					}
					
					//If no correct letters you lose a life
					if (!correct) {
						tryCounter++;
					}
					
					//If all letters are filled in you win
					if (correctLetters == numSpaces) {
						System.out.println("Congratulations you won! The word was: " + myWord);
						break;
					}
					
					//If you lose all lives game over
					if (tryCounter == 6) {
						System.out.println("Sorry. The word was: " + myWord);
						break;
					}
		
					System.out.println(6-tryCounter + " incorrect guess(es) left!");
				} else {
					System.out.println("Letter already guessed. Please try again.");
				}
			} else {
				System.out.println("Error. Please enter a letter.");
			}
		}
		
		sc.close();
	}

	// Generates a random word from the word dictionary file
	private static String getWord() throws FileNotFoundException {
		Random rand = new Random();
		int i = rand.nextInt(370104);
		
		File file = new File("words.txt");
		Scanner scan = new Scanner(file);
		
		for (int a=0; a<i; a++) {
			scan.next();
		}
		
		String word = scan.next();
		scan.close();
		return word;
	}
}
