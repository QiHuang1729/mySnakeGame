/**
 *	A SinglyLinkedList of Coordinate objects representing
 *	a snake on a two-dimensional grid.
 *
 *	@author	
 *	@since	
 */
public class Snake extends SinglyLinkedList<Coordinate> {
	
	private final int START_LENGTH = 5;
	
	/**	Constructor for making a Snake that is 5 grids high facing north.
	 *	Places the snake head at Coordinate location and the tail below.
	 *	Precondition: To place the Snake, the board must have at
	 *				least location.getRow() + 4 more rows.
	 */
	public Snake(Coordinate location) { 
		ListNode<Coordinate> snakeBody = 
									new ListNode<Coordinate>(location);
		add(snakeBody);
		
		for (int i = 1; i < START_LENGTH; i++) {
			int nextRow = location.getRow() - 1;
			int nextCol = location.getCol() - 1;
			Coordinate nextCoord = new Coordinate(nextRow, nextCol);
			snakeBody = new ListNode<Coordinate>(location);
			add(snakeBody);
		}
		
		tail = snakeBody;
	}
}
