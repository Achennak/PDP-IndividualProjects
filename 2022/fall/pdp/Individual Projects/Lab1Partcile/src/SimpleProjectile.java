
/**
 * This class represents a SimpleProjectile. It has initialPosition(x,y) and initialVelocity(vx,vy).
 */
public class SimpleProjectile implements Particle {
  private float initHorizontalPos;
  private float initVerticalPos;
  private float initHorizontalVel;
  private float initVerticalVel;
  private final float acceleration = (float) -9.81;
  private final float hAcc = 0;

  /**
   * Constructs a SimpleProjectile obj and initializes to given initialPosition, initialVelocity.
   *
   * @param initHPos x-coordinate of the position of this particle initially
   * @param initVPos y-coordinate of the position of this particle initially
   * @param initHVel initial velocity of the particle along x-axis
   * @param initVVel initial velocity of the particle along y-axis
   */

  public SimpleProjectile(float initHPos, float initVPos, float initHVel, float initVVel) {
    this.initHorizontalPos = initHPos;
    this.initVerticalPos = initVPos;
    this.initHorizontalVel = initHVel;
    this.initVerticalVel = initVVel;
  }

  /**
   * Return the x-coordinate of the position of this particle initially.
   *
   * @return the x-coordinate of the position of this particle initially.
   */
  public float getInitialHorizontalPos() {
    return this.initHorizontalPos;
  }

  /**
   * Return the y-coordinate of the position of this particle initially.
   *
   * @return the y-coordinate of the position of this particle initially.
   */
  public float getInitialVerticalPos() {
    return this.initVerticalPos;
  }

  /**
   * Return the initial velocity of the particle along x-axis.
   *
   * @return the initial velocity of the particle along x-axis
   */
  public float getInitialHorizontalVel() {
    return this.initHorizontalVel;
  }

  /**
   * Return the initial velocity of the particle along y-axis.
   *
   * @return the initial velocity of the particle along y-axis
   */
  public float getInitialVerticalVel() {
    return this.initVerticalVel;
  }

  /**
   * Return the displacement of this particle at velocity v and time t.
   *
   * @param velocity velocity of this particle
   * @param time     At time t
   * @return the displacement of this particle at velocity v and time t.
   */
  private float getDisplacement(float velocity, float time, float acceleration) {
    return (float) ((velocity * time) + ((0.5) * acceleration * time * time));
  }

  /**
   * Return the time taken by this particle when it reaches to ground.
   *
   * @param velocity velocity of this particle
   * @return the displacement of this particle at velocity v and time t.
   */
  private float getTimeTakenByParticleToReachGround(float velocity) {
    return (-(2 * velocity) / acceleration);
  }


  /**
   * Return the state of the particle as a string formatted as below.
   *
   * @param time the time at which the state must be obtained.
   * @return the state of the particle as a string.
   */

  @Override
  public String getState(float time) {
    String format;
    float finalXDis;
    float finalYDis;
    if ((time < 0)) {
      finalXDis = getInitialHorizontalPos();
      finalYDis = getInitialVerticalPos();
    } else {
      float timeToReachGround = getTimeTakenByParticleToReachGround(getInitialVerticalVel());
      if (time < timeToReachGround) {
        float newXDis = getDisplacement(getInitialHorizontalVel(), time, hAcc);
        float newYDis = getDisplacement(getInitialVerticalVel(), time, acceleration);
        finalXDis = getInitialHorizontalPos() + newXDis;
        finalYDis = getInitialVerticalPos() + newYDis;
      } else {
        float disAlongX = getDisplacement(getInitialHorizontalVel(), timeToReachGround, hAcc);
        finalXDis = getInitialHorizontalPos() + disAlongX;
        finalYDis = getInitialVerticalPos();
      }
    }
    format = String.format("At time %.2f: position is (%.2f,%.2f)", time, finalXDis, finalYDis);
    return format;
  }
}
