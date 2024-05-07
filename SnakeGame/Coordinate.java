/**
 *	A coordinate on a grid. Integer (row, column) values.
 *
 *	@author Mr Greenstein
 */
public class Coordinate implements Comparable<Coordinate>
{
	private int row, column;
		
	public Coordinate(int myRow, int myColumn)
	{
		row = myRow;
		column = myColumn;
	}
	
	/* Accessor methods */
	public int getRow() { return row; }
	public int getCol() { return column; }
	
	@Override
	public boolean equals(Object other)
	{
		return compareTo((Coordinate)other) == 0;
	}
	
	/**
	 *	Coordinate is greater when:
	 *	1. row is greater or
	 *	2. row is equal and column is greater
	 *	3. otherwise Coordinates are equal
	 *	@return		negative if less than, 0 if equal, positive if greater than
	 */
	@Override
	public int compareTo(Coordinate other) {
		if (! (other instanceof Coordinate)) {
			throw new IllegalArgumentException("compareTo not "
				+ "Coordinate object");
		}
		
		if (row > ((Coordinate)other).row || row < 
												((Coordinate)other).row) {
			return row - ((Coordinate)other).row;
		}
		
		if (column > ((Coordinate)other).column || column < 
											((Coordinate)other).column) {
			return column - ((Coordinate)other).column;
		}
		
		return 0;
	}
	
	public String toString()
	{	return "[ " + row + ", " + column + "]";  }
	
}
