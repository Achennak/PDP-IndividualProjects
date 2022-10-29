
/**
 * Abstract base class for implementations of {@link ChessPiece}. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link ChessPiece} interface. A new implementation of
 * the interface has the option of extending this class, or directly
 * implementing all the methods.
 */
abstract class AbstractChessPiece implements ChessPiece {

  protected int row;
  protected int col;
  protected Color color;

  /**
   * Constructs a chessPiece with specified color
   * and a representation of its position in rows and columns.
   *
   * @param row   x-axis position of the chess piece.
   * @param col   y-axis position of the chess piece.
   * @param color specific color of the piece.
   * @throws IllegalArgumentException for any invalid inputs
   */
  public AbstractChessPiece(int row, int col, Color color) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Illegal position");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.col;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return (this.getColor() != piece.getColor()) && canMove(
            piece.getRow(),
            piece.getColumn());
  }

  /**
   * Determines whether two integer values are the same.
   *
   * @param val1 value of first integer.
   * @param val2 value of second integer.
   * @return true if two values are same or false otherwise.
   */
  protected boolean canMoveHelper(int val1, int val2) {
    return (val1 == val2);
  }

  @Override
  public boolean canMove(int row, int col) {
    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    return canMoveFurther(row, col);
  }

  /**
   * Helper method that checks if this chess piece
   * be moved from its current location to the location
   * (row,col).
   *
   * @param row the row to which this chess piece can be moved
   * @param col the col to which this chess piece can be moved
   * @return true if it can be moved to this position, false otherwise.
   */

  abstract boolean canMoveFurther(int row, int col);


}
