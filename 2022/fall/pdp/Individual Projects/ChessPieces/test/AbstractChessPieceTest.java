import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for {@link ChessPiece}s.
 */
abstract class AbstractChessPieceTest {
  private boolean[][] results;

  @Before
  public void setup() {
    results = new boolean[8][8];

  }

  /**
   * Helper methods for all the testcases below.
   */

  private void initializeResults() {

    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResultVal(row, col, false);
      }
    }
  }

  protected void initializeResultVal(int row, int col, boolean val) {
    results[row][col] = val;
  }

  private void verifyMoveResults(ChessPiece piece) {

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }
        assertEquals("Piece at :" + piece.getRow()
                        + "," + piece.getColumn()
                        + ", Unexpected canMove result "
                        + "for "
                        + "i="
                        + i
                        + " j="
                        + j
                        + "",
                results[i][j], piece.canMove(i, j));
      }
    }
  }


  private void verifyKillResults(ChessPiece piece) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {

        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }
        Color c = Color.values()[(piece.getColor().ordinal() + 1)
                % Color.values().length];
        ChessPiece another = initialiseChessPiece(i, j, c);
        assertEquals("Unexpected canKill result for "
                        + "i="
                        + i
                        + " j="
                        + j
                        + "",
                results[i][j], piece.canKill(another));
      }
    }
  }


  /**
   * Constructs an instance of the class under test representing the chessPiece
   * with Position(i,j) and color(c).
   *
   * @param i x-axis position of the chess piece.
   * @param j y-axis position of the chess piece.
   * @param c specific color of the piece.
   * @return an instance of the class under test.
   */
  protected abstract ChessPiece initialiseChessPiece(int i, int j, Color c);

  /**
   * Helper method to determine all the valid positions of the chessPieces.
   *
   * @param row     x-axis position of the chess piece.
   * @param col     y-axis position of the chess piece.
   * @param results matrix which contains all valid positions
   *                of the chessPieces.
   */
  protected abstract void setUpResults(int row, int col, boolean[][] results);

  //Below is the implementation of all concrete test class.

  public static final class KnightTest extends AbstractChessPieceTest {

    @Override
    protected ChessPiece initialiseChessPiece(int row, int col, Color c) {
      return new Knight(row, col, c);
    }

    @Override
    protected void setUpResults(int row, int col, boolean[][] results) {
      int[] xDir = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
      int[] yDir = new int[]{1, 2, 2, 1, -1, -2, -2, -1};

      for (int i = 0; i < 8; i++) {
        if (!(row + xDir[i] < 0 || row + xDir[i] >= 8) && !(col + yDir[i] < 0
                || col + yDir[i] >= 8)) {
          initializeResultVal(row + xDir[i], col + yDir[i], true);
        }
      }
    }
  }

  public static final class BishopTest extends AbstractChessPieceTest {

    @Override
    protected ChessPiece initialiseChessPiece(int row, int col, Color c) {
      return new Bishop(row, col, c);
    }


    @Override
    protected void setUpResults(int row, int col, boolean[][] results) {
      //check if canMove works
      for (int i = 0; i < 8; i++) {

        if ((row + i) < 8) {
          if ((col + i) < 8) {
            initializeResultVal(row + i, col + i, true);
          }
          if (col >= i) {
            initializeResultVal(row + i, col - i, true);
          }

        }
        if (row >= i) {
          if ((col + i) < 8) {
            initializeResultVal(row - i, col + i, true);
          }
          if (col >= i) {
            initializeResultVal(row - i, col - i, true);
          }
        }
      }
    }
  }

  public static final class RookTest extends AbstractChessPieceTest {

    @Override
    protected ChessPiece initialiseChessPiece(int row, int col, Color c) {
      return new Rook(row, col, c);
    }


    @Override
    protected void setUpResults(int row, int col, boolean[][] results) {
      //check if canMove works
      for (int i = 0; i < 8; i++) {
        initializeResultVal(i, col, true);
        initializeResultVal(row, i, true);
      }
    }
  }

  public static final class QueenTest extends AbstractChessPieceTest {

    @Override
    protected ChessPiece initialiseChessPiece(int row, int col, Color c) {
      return new Queen(row, col, c);
    }

    @Override
    protected void setUpResults(int row, int col, boolean[][] results) {


      for (int i = 0; i < 8; i++) {
        initializeResultVal(i, col, true);
        initializeResultVal(row, i, true);

        if ((row + i) < 8) {
          if ((col + i) < 8) {
            initializeResultVal(row + i, col + i, true);
          }
          if (col >= i) {
            initializeResultVal(row + i, col - i, true);
          }

        }

        if (row >= i) {
          if ((col + i) < 8) {
            initializeResultVal(row - i, col + i, true);
          }
          if (col >= i) {
            initializeResultVal(row - i, col - i, true);
          }
        }
      }
    }
  }


  /**
   * The tests for chessPiece begin here.
   * These tests are common for all implementations.
   */

  @Test(timeout = 500)
  public void testGetters() {
    ChessPiece piece;
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        for (Color c : Color.values()) {
          piece = initialiseChessPiece(row, col, c);
          assertEquals("Row number does not match what was initialized", row,
                  piece.getRow());
          assertEquals("Column number does not match what was initialized",
                  col, piece.getColumn());
          assertEquals("solution.Color does not match what was initialized",
                  c, piece.getColor());

        }
      }
    }
  }

  @Test(timeout = 500)
  public void testInvalidConstructions() {

    for (Color c : Color.values()) {
      for (int i = 0; i < 8; i++) {
        try {
          initialiseChessPiece(i, -1, c);
          fail("Did not throw an exception when rook is created with invalid "
                  + "row");
        } catch (IllegalArgumentException e) {
          //passes
        }
        try {
          initialiseChessPiece(-1, i, c);
          fail("Did not throw an exception when rook is created with invalid "
                  + "column");
        } catch (IllegalArgumentException e) {
          //passes
        }

      }
    }
  }


  @Test(timeout = 500)
  public void testChessPieceMoves() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = initialiseChessPiece(row, col, Color.BLACK);
        setUpResults(row, col, results);
        verifyMoveResults(piece);
      }
    }
  }

  @Test
  public void testChessPieceKills() {

    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = initialiseChessPiece(row, col, c);
          setUpResults(row, col, results);
          verifyKillResults(piece);
        }
      }
    }
  }
}
