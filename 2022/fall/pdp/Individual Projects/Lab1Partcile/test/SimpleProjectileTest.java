import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class represents SimpleProjectile class test file.
 */
public class SimpleProjectileTest {


  private SimpleProjectile sProjectile;

  @Before
  public void setUp() {

    sProjectile = new SimpleProjectile(0, 0, 10, 10);
  }

  @Test
  public void testInitialHorizontalPosition() {
    assertEquals(0, sProjectile.getInitialHorizontalPos(), 0.001);

  }

  @Test
  public void testInitialVerticalPosition() {
    assertEquals(0, sProjectile.getInitialVerticalPos(), 0.001);

  }

  @Test
  public void testInitialHorizontalVelocity() {
    assertEquals(10, sProjectile.getInitialHorizontalVel(), 0.001);

  }

  @Test
  public void testInitialVerticalVelocity() {
    assertEquals(10, sProjectile.getInitialVerticalVel(), 0.001);

  }

  /**
   * When particle doesn't move at all.
   */
  @Test
  public void testGetStateWhenNoMovement() {
    sProjectile = new SimpleProjectile(0, 0, 0, 0);
    assertEquals("At time 2.57: position is (0.00,0.00)", sProjectile.getState(2.567f));

  }

  /**
   * When particle starts from position(0,0) and with velocity(0,10).
   */
  @Test
  public void testGetStateFromOriginNoHorizontalVelocity() {
    sProjectile = new SimpleProjectile(0, 0, 0, 10);
    assertEquals("At time 2.46: position is (0.00,0.00)", sProjectile.getState(2.4567f));

  }

  /**
   * When particle starts from position(4,4) and with velocity(10,0).
   */
  @Test
  public void testGetStateNoVerticalVelocity() {
    sProjectile = new SimpleProjectile(4, 4, 10, 0);
    assertEquals("At time 2.46: position is (4.00,4.00)", sProjectile.getState(2.4567f));

  }


  /**
   * When particle starts from position(0,0) and with velocity(10,10).
   */
  @Test
  public void testGetStateFromOriginDiagonalVelocity() {
    sProjectile = new SimpleProjectile(0, 0, 10, 10);
    assertEquals("At time 2.46: position is (20.39,0.00)", sProjectile.getState(2.4597f));

  }


  /**
   * When particle starts from position(3,0) and with velocity(10,10).
   */

  @Test
  public void testGetStateFromNonOriginWithHorizontalVelocity() {
    sProjectile = new SimpleProjectile(3, 0, 10, 10);
    assertEquals("At time 2.04: position is (23.39,0.00)", sProjectile.getState(2.0387f));

  }


  /**
   * When particle starts from position(3,3) and with velocity(10,10) and time>2.0387S.
   */

  @Test
  public void testGetStateFromDiagonalPositions() {
    sProjectile = new SimpleProjectile(3, 3, 10, 10);
    assertEquals("At time 2.46: position is (23.39,3.00)", sProjectile.getState(2.456f));
  }


  /**
   * When particle starts from position(3,3) and with velocity(10,10) and time<2.0387S.
   */

  @Test
  public void testFromDiagonalPositions() {
    sProjectile = new SimpleProjectile(3, 3, 10, 10);
    assertEquals("At time 1.46: position is (17.56,7.16)", sProjectile.getState(1.456f));
  }

  /**
   * For any negative time, it should stay at its initial position.
   */
  @Test
  public void testGetStateWithTimeIsNegative() {
    assertEquals("At time -2.49: position is (0.00,0.00)", sProjectile.getState(-2.49f));

  }
}
