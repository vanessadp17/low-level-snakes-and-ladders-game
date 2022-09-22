/**
 * @author Vanessa DiPietrantonio
 * 
 * The program prompts the user to enter the number of players for the Snakes and Ladders Game 
 * between 2 and 4 people inclusively. If the user fails to fulfill this requirement within four tries, 
 * the program terminates. Otherwise, the program creates an object for the game, and the game begins.
 */

import java.util.Scanner;

public class PlaySnakesAndLadders {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		// display welcome message
		System.out.println("------------------------------------------------------\n\tWelcome to the Snakes and Ladders Game\n------------------------------------------------------");	
		System.out.print("\nEnter the # of players for your game. Number must be between 2 and 4 inclusively: ");
	    int numPlayers = keyboard.nextInt();
	    
	    // permit the user at most four tries to enter the number of players for that game between 2 and 4 inclusively
	    int count = 1; 
	    while (count < 4) {
	      if (numPlayers < 2 || numPlayers > 4) {
	          System.out.print("Bad Attempt "+ count + "- Invalid # of players. Please enter a number between 2 and 4 inclusively: ");
	          numPlayers = keyboard.nextInt();
	          count++;
	          }
	      else
	    	  break;
	    }
	    // terminate the program
	    if (count == 4) {
	    	System.out.println("Bad Attempt 4! You have exhausted all your chances. Program will terminate!");
	    	System.exit(0);
	    }
	    
	    // create the game object and call on the play method
	    SnakesAndLadders s = new SnakesAndLadders(numPlayers);
	    s.play();
	    
	    keyboard.close();
	}	 
	

}
