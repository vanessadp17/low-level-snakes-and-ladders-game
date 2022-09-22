/**
 * @author Vanessa DiPietrantonio
 * 
 * The BoardSpace class is responsible for holding the board tile's number.
 * If the tile is a special space, it is indicated T/F for a ladder's bottom or a snake's head.
 * The tile number for the appropriate special space is initialized to for each tile, if applicable. 
 * 
 * @param int board number that represents the number of the distinct tile on the board
 * @param boolean that represents if the tile is a bottom of a ladder
 * @param integer that represents the board number that locates the top of the ladder, 0 if false
 * @param boolean that represents if the tile is a snake's head
 * @param integer that represents the board number that locates the snake's tail, 0 if false
 */
public class BoardSpace implements Cloneable{
	//attributes
	private int boardNumber;
	private boolean ladderBottom;
	private int ladderTop;
	private int snakeBottom;
	private boolean snakeTop;

	//default constructor
	public BoardSpace() {
		boardNumber = 0;
		ladderBottom = false;
		ladderTop = 0;
		snakeBottom = 0;
		snakeTop = false;
		
	}
	
	//parametrized constructor
	public BoardSpace(int bn, boolean lb, boolean st, int lt, int sb) {
		boardNumber = bn;
		ladderBottom = lb;
		ladderTop = lt;
		snakeBottom = sb;
		snakeTop = st;
	}
	
	//accessor and mutator method
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int bn) {
		boardNumber = bn;
	}
	
	public boolean getLadderBottom() {
		return ladderBottom;
	}
	public void setLadderBottom(boolean lb) {
		ladderBottom = lb;
	}
	
	public int getLadderTop() {
		return ladderTop;
	}
	public void setLadderTop(int lt) {
		ladderTop = lt;
	}
	
	public int getSnakeBottom() {
		return snakeBottom;
	}
	public void setSnakeBottom(int sb) {
		snakeBottom = sb;
	}
	
	public boolean getSnakeTop() {
		return snakeTop;
	}
	public void setSnakeTop(boolean st) {
		snakeTop = st;
	}
	
	/**
	 * clone method
	 * @return cloned BoardSpace object or null
	 */
	public BoardSpace clone() {
		try {
			BoardSpace b = (BoardSpace)super.clone();
			return b;
		} 
		catch(CloneNotSupportedException e) {
			System.out.println("CloneNotSupportedException: Program has terminated.");
			return null;
		}
	}
	
	

}
