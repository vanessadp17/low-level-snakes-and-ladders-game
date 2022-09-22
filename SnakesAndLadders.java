import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author Vanessa DiPietrantonio
 * 
 * Snakes and Ladders program initiates a snakes and ladders game.
 * This class is responsible for declaring the board, ordering the players, 
 * flipping the dice and playing the game until there is a winner.
 * 
 * 
 */
public class SnakesAndLadders {
	//attributes
	private int numPlayers;
	private BoardSpace[][] board = new BoardSpace[10][10];
	private Player[] players;
	
	//parametrized constructor
	public SnakesAndLadders(int np) {
		//declare the board
		BoardSpace b = new BoardSpace();
		for (int i=9; i>=0; i--) {
			System.out.println("\n");
			for (int j=0; j<10; j++) {
				// even row increases from left to right
				if (i%2 == 0) {
					board[i][j] = b.clone();
					board[i][j].setBoardNumber(10*i + j + 1);
					System.out.print("\t" + board[i][j].getBoardNumber());
				}
				// odd row decreases from left to right
				else {
					board[i][j] = b.clone();
					board[i][j].setBoardNumber(10*i - j + 10);
					System.out.print("\t" + board[i][j].getBoardNumber());
				}
			}
		}		
		System.out.println("\n");
		//declare the snakes spaces on the board
		board[1][4].setSnakeTop(true); board[1][4].setSnakeBottom(6);
		board[4][7].setSnakeTop(true); board[4][7].setSnakeBottom(30);
		board[6][1].setSnakeTop(true); board[6][1].setSnakeBottom(19);
		board[6][3].setSnakeTop(true); board[6][3].setSnakeBottom(60);
		board[9][7].setSnakeTop(true); board[9][7].setSnakeBottom(68);
		board[9][5].setSnakeTop(true); board[9][5].setSnakeBottom(25);
		board[9][3].setSnakeTop(true); board[9][3].setSnakeBottom(76);
		board[9][2].setSnakeTop(true); board[9][2].setSnakeBottom(78);
		
		//declare the ladder spaces on the board
		board[0][0].setLadderBottom(true); board[0][0].setLadderTop(38);
		board[0][3].setLadderBottom(true); board[0][3].setLadderTop(14);
		board[0][8].setLadderBottom(true); board[0][8].setLadderTop(31);
		board[2][0].setLadderBottom(true); board[2][0].setLadderTop(42);
		board[2][7].setLadderBottom(true); board[2][7].setLadderTop(84);
		board[3][4].setLadderBottom(true); board[3][4].setLadderTop(44);
		board[5][9].setLadderBottom(true); board[5][9].setLadderTop(67);
		board[7][9].setLadderBottom(true); board[7][9].setLadderTop(91);
		board[7][0].setLadderBottom(true); board[7][0].setLadderTop(25);
		
		//declare players
		numPlayers = np;
		players = new Player[numPlayers];
		for (int i=0; i<np; i++) {
			players[i] = new Player(i+1,0,0);
		}
	}
	
	//accessor and mutator methods
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public void setNumPlayers(int np) {
		numPlayers = np;
	}
	
	/**
	 * This method generates a random number from 1 to 6 inclusive.
	 * @return integer value that represents the random number.
	 */
	public int flipDice() {
		return (int)(6*Math.random()+1);
	}
	
	/**
	 * This method determines the order the players will play the game. The method iterates through the array and checks how many players 
	 * obtain the same dice value for the highest dice value of each roll. 
	 * If there was no tie, the players will be sorted an into another array, which contains the ordered list of players.
	 * If a tie did occur, the tied players be added into a new array and will run through this method again until they achieve distinct dice values. 
	 * This method will be repeated called on until a final order is determined. 
	 * @param an array of object Player.
	 * @param start index (inclusive)
	 * @param end index (exclusive)
	*/
	public void orderPlayers(Player[] players, int start, int end) {
		// Roll dice and display value for players between players[start] (inclusive) and
		// players[end] (exclusive) 
		for (int i = start; i < end; ++i) {
		    players[i].setDiceRoll(flipDice());
		    System.out.println(players[i]);
		}
		// Sort the array by descending order
		Arrays.sort(players, start, end, new Comparator<Player>() {
			public int compare(Player a, Player b) {
				return Integer.compare(b.getDiceRoll(), a.getDiceRoll());
			}
		});

		// Find tied players who rolled the same number
		int i = start;
		while (i < end) {
		    // Try to find a "run" of players with the same number.
			int runStart = i;
		    int diceNumberRolled = players[runStart].getDiceRoll();
		    ++i;
		    while (i < end && players[i].getDiceRoll() == diceNumberRolled) {
		    	++i;
		    }

		    if (i - runStart > 1) {
		      // We have found more than one player with the same dice number.
		      // Get all of the players with that dice number to roll again.
		    System.out.print("\nA tie was achieved between ");
		    for (int j = runStart; j < i; j++) {
		    	System.out.print("Player "+ players[j].getName());
		    	if(j != i-1)
		    		System.out.print(" and ");
		    }
		    System.out.println();
		    orderPlayers(players, runStart, i);
		    }
		}
	}
	
	/**
	 * Checks if a player lands on a snake's hear or the bottom of a ladder. If yes, the player's new position is stored.
	 * @param b current board space
	 * @param p current player
	 */
	public void specialSpace(BoardSpace b, Player p) {
		if (b.getSnakeTop()) {
			System.out.print(" then down to square " + b.getSnakeBottom());
			p.setPosition(b.getSnakeBottom());
		}
		if (b.getLadderBottom()) {
			System.out.print(" then up to square " + b.getLadderTop());
			p.setPosition(b.getLadderTop());
		}
	}
	
	/**
	 * This method simulates the running of the snake and ladder game. Players are ordered based on their first dice roll
	 * and take turns rolling the dice and moving to the appropriate spot on the board until one makes it exactly to 
	 * the 100th space on the board. Once a player has won, this marks the end of the game.
	*/
	public void play() {
		System.out.println("The game is played by " + numPlayers + " players\n\nNow deciding which player will start playing:");
	    //Order the players 
	    orderPlayers(players, 0, numPlayers);
	    
	    System.out.print("\nReached the final decision on order of playing: ");
	    for (int i=0; i<numPlayers;i++) {
	    	System.out.print("Player "+ players[i].getName());
	    	if (i != numPlayers-1)
	    		System.out.print(", ");
	    }
	    
	    System.out.println("\n\nNow let the game begin...");
	    // this loop continues until the game is over and there's a winner
		while (true) 
		{
			for (int i=0; i<numPlayers;i++)
			{
				// roll the dice
				players[i].setDiceRoll(flipDice());
				// move the player
				players[i].move();
				// display the player's new position
				System.out.print("\n" + players[i] + "; now in square " + players[i].getPosition());
				// check if the player is on a snake's head or bottom of a ladder
				for (int j=0; j<10; j++) {
					for (int k=0; k<10; k++) {
						if (players[i].getPosition() == board[j][k].getBoardNumber()) {
							specialSpace(board[j][k], players[i]);	
							}
						}	
					}
				// check if there is a winner 
				if (players[i].getPosition() == board[9][0].getBoardNumber()) {
					System.out.print("\n------------------------------------------------------------"
							+ "\nThe game is now over. We have a winner!\nCongratulations to Player " 
							+ players[i].getName() + " for winning the game!\nThank you everyone for playing."
							+ "\n------------------------------------------------------------");
					System.exit(0);
				}	
			}
			System.out.print("\nGame not over; flipping again");
		}  
	}
}


