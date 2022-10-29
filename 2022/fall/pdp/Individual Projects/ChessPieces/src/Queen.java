
/**
 * Implementation of Queen ,one of the ChessPieces.
 */
public class Queen extends AbstractChessPiece {

  /**
   * Constructs a Queen chessPiece with specified color
   * and a representation of its position in rows and columns.
   *
   * @param row   x-axis position of the chess piece.
   * @param col   y-axis position of the chess piece.
   * @param color specific color of the piece.
   * @throws IllegalArgumentException if any argument is invalid
   */
  public Queen(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
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
  @Override
  protected boolean canMoveFurther(int row, int col) {
    int diff1 = Math.abs(this.row - row);
    int diff2 = Math.abs(this.col - col);
    return (canMoveHelper(this.row, row) || canMoveHelper(this.col, col)
            || canMoveHelper(diff1, diff2));
  }
}
