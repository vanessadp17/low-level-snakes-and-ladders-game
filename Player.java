/**
 * @author Vanessa DiPietrantonio
 * 
 * The Player class is responsible for holding the player's name, position and dice value. 
 * The position and dice roll variables will be updated and modified with each turn of the player.
 * The player's position is adjusted after each move the player makes and the player's dice value is displayed.  
 * 
 * @param int value that represents the identity of the distinct players in the game
 * @param integer that represents the dice value
 * @param integer that represents the board position that locates the players
 */

public class Player {
	// Player attributes
	private int name;
	private int position;
	private int diceRoll;
	
	// parameterized constructor
	public Player(int name, int diceRoll, int position) {
		this.name = name;
		this.diceRoll = diceRoll;
		this.position = position;
	}

	//getters and setters
	public void setName(int name) {
		this.name = name;
	}
	public int getName() {
		return name;
	}
	
	public void setDiceRoll(int diceRoll) {
		this.diceRoll = diceRoll;
	}
	public int getDiceRoll() {
		return diceRoll;
	}
  
	public void setPosition(int position) {
		this.position = position;
	}
	public int getPosition() {
		return position;
	}
	

	/**
	 * This method adds the dice value to the player's position to get the player's new position.
	 * If the new position is greater than 100, then the player moves the difference backwards on the board.
	*/
	public void move() {
		if ((position + diceRoll) < 100)
			position += diceRoll;
		else	
			position =  100 - ((position + diceRoll) - 100);
	}
	
	// displays the player's dice value at each roll
	public String toString() {
		return "Player " + name + " got a dice value of " + diceRoll;
	}
  
}