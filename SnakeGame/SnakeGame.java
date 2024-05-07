/**
 *	Snake Game - <Description goes here>
 *	
 *	@author	
 *	@since	
 */
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game

	/*	Constructor	*/
	public SnakeGame() { 
		int row, col = -1;
		
		board = new SnakeBoard(20, 25);
		score = 0;
		
		row = (int)(Math.random() * (board.getHeight() - 4));
		col = (int)(Math.random() * board.getWidth());
		Coordinate headLocation = new Coordinate(row, col);
		snake = new Snake(headLocation);
		
		do {
			row = (int)(Math.random() * board.getHeight());
			col = (int)(Math.random() * board.getWidth());
			target = new Coordinate(row, col);
		}
		while (snake.contains(target));
	}
	
	/*	Main method	*/
	public static void main(String[] args) {
		boolean isEnded = false;
		String input = null;
		String acceptable = "wsdahfrq";
		printIntroduction();
		
		do {
			board.printBoard();
			do {
				input = Prompt.getChar("[ASK USER SOMETHING]");
			} while (acceptable.indexOf(input) == -1);
			
			if (input.equals("w")) {
				//
			}
		} while(!isEnded);
	}
	
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
	
}
