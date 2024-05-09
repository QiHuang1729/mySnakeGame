/**
 *	The snake board stores the characters in each position in the grid. 
 * 	The 
 *
 *	@author	Qi Huang
 *	@since	5/7/2024
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) {
		board = new char[height];
		for (int i = 0; i < board.length; i++) {
			board[i] = new char[width];
		}
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		Coordinate thisCoord = null;
		
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				thisCoord = new Coordinate(r, c);
				if (snake.indexOf(thisCoord) == 0) {
					board[r][c] = '@';
				} else if (snake.indexOf(thisCoord) != -1) {
					board[r][c] = '*';
				} else if (thisCoord.equals(target)) {
					board[r][c] = '+';
				}
			}
		}
		
		printBorder();
		for (int r = 0; r < board.length; r++) {
			System.out.print("|");
			for (int c = 0; c < board[r].length; c++) {
				System.out.print(board[r][c]);
			}
			System.out.println("|");
		}
		printBorder();
	}
	
	/* Helper methods go here	*/
	/** 
	 * Prints the top or bottom border of the board, and 
	 * goes to a new line
	 */
	private void printBorder() {
		System.out.print("+");
		for (int i = 0; i < board[0].length; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}
	
	
	/*	Accessor methods	*/
	public int getHeight() {
		return board.length;
	}
	
	public int getWidth() {
		return board[0].length;
	}
	
	/** Gets the character at a specific coordinate 
	 * 	
	 * 	Precondition: (row, col) is a valid location in the board
	 * */ 
	public char getCharAt(int row, int col) {
		return board[row][col];
	}
	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}
