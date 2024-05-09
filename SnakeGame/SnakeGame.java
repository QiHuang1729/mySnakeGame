/**
 *	Snake Game - The game is played by moving a snake in a rectangular grid.
 *  Each turn, the user is prompted to move the snake, and the goal of the 
 * 	user is to reach as many targets as possible without moving the snake
 * 	into itself or moving it into an edge.
 * 	
 *	This class prints the introduction, prompts the user using a do-while
 * 	loop for the action, and changes the appropriate conditions in the 
 * 	board. It ends the game when appropriate, and prints an end message
 * 	to the user.  
 * 
 * 	The Snake Game class makes sure the Snake moves up, down, left, or 
 * 	right according to the user's input. It also grows the snake when 
 * 	it reaches the target.
 * 
 * 	The class also puts a new target randomly on the board after the 
 * 	user reaches the original target. 
 * 
 * 	Additionally, the user can choose the "help" option to bring up a help
 * 	menu containing instructions for the game. The user can also save the 
 * 	game to a file, restore the game to a file, or quit. 
 * 
 *	@author	Qi Huang
 *	@since	5/9/2024
 */
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game
	private final String[] VALID_INPUTS = {"w", "s", "d", "a", "h", "f", 
															"r", "q"};

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
		SnakeGame sg = new SnakeGame();
		sg.run();
	}
	
	/** Runs the main game loop, keeps track of whether the program is
	 * over or not */
	public void run() {
		boolean gameIsDone = false;
		String input = null;
		printIntroduction();
		
		do {
			board.printBoard();
			input = getInput();
			processInput(input);
			gameIsDone = checkState();
			
		} while(!gameIsDone);
	}
	
	/** Gets the input in the main loop of the game 
	 * 	
	 * 	@return a valid user input 
	 */
	private String getInput() {
		boolean inputIsValid = false;
		String input = null;
		
		do {
			input = Prompt.getString("Score: " + score + 
				+ " (w - North, s - South, d - East, a - West, h - Help)");
			
			for (int i = 0; i < validInputs.length; i++) {
				if (validInputs[i].equals(input)) {
					inputIsValid = true;
				}
			}
		} while (!inputIsValid);
		
		return input;
	}
	
	/** Takes actions which depend on the user input */
	private void processInput(String input) {
		if (input.equals("w") || input.equals("s") || input.equals("d") 
				|| input.equals("a")) {
			moveSnake(input);
		} else if (input.equals("h")) {
			helpMenu();
		} else if (input.equals("q")) {
			confirmQuit();
		}
	}
	
	/** Moves the snake to the next location */
	private void moveSnake() {
		ListNode<Coordinate> nextHead = null;
		Coordinate nextLoc = null;
		int dx = -1;
		int dy = -1;
		Coordinate headLoc = snake.get(0).getValue();
		int row = -1;
		int col = -1;
		
		if (input.equals("w")) {
				dx = 0;
				dy = -1;
		} else if (input.equals("s")) {
			dx = 0;
			dy = 1;
		} else if (input.equals("d")) {
			dx = 1;
			dy = 0;
		} else {
			dx = -1;
			dy = 0;
		}
		
		row = headLoc.getRow();
		col = headLoc.getCol();
		nextLoc = new Coordinate(row + dy, col + dx);
		nextHead = new ListNode<Coordinate>(nextLoc);
		snake.add(nextHead);
		snake.remove(snake.size() - 1);
	}
	
	/** Gets the user input when they try to quit */
	private String confirmQuit() {
		boolean inputIsValid = false;
		String input = null;
		String[] validYesOrNo = {"y", "n"};
		
		do {
			input = Prompt.getString("Do you really want to quit? "
				+ "(y or n)");
			
			for (int i = 0; i < validYesOrNo.length; i++) {
				if (validYesOrNo[i].equals(input)) {
					inputIsValid = true;
				}
			}
		} while (!inputIsValid);
		
		return input;
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
