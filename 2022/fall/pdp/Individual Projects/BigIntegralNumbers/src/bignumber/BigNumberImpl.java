package bignumber;

import java.util.regex.Pattern;

/**
 * This class implements the List of Numbers(BigNumber) interface.
 * This implementation represents non-negative numbers of arbitrary lengths.
 */
public class BigNumberImpl implements BigNumber {

  private BigNumberListADTNode head;
  private static final Pattern pattern = Pattern.compile("^\\d+$");

  /**
   * Default constructor of BigNumberImpl class with no parameters
   * and initializes this number to 0 .
   */
  public BigNumberImpl() {

    head = new BigNumberListADTElementNode();
  }

  private boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    return pattern.matcher(strNum).matches();
  }

  /**
   * This constructs takes a number as a string and represents it as BigNumber.
   *
   * @param number number to be represented as bigNumber in String format.
   * @throws IllegalArgumentException throws IllegalArgumentException
   *                                  if string passed to it does not represent a valid number.
   */
  public BigNumberImpl(String number) throws IllegalArgumentException {
    if (number == null || number.isEmpty()) {
      throw new IllegalArgumentException("Invalid input");
    } else if (!isNumeric(number)) {
      throw new IllegalArgumentException("Invalid input");
    } else {
      int i = 0;
      int len = number.length();
      while (i < len && number.charAt(i) == '0') {
        i++;
      }
      if (len > 1) {
        number = number.substring(i);
      }
      if (number != null && !number.isEmpty()) {

        head = new BigNumberListADTElementNode(number);

      } else {
        head = new BigNumberListADTElementNode();
      }
    }
  }

  @Override
  public int length() {
    return head.length();
  }

  @Override
  public void shiftLeft(int numOfShifts) {
    head = head.shiftLeft(numOfShifts);
  }

  @Override
  public void shiftRight(int numOfShifts) {
    head = head.shiftRight(numOfShifts);
  }

  @Override
  public void addDigit(int digit) throws IllegalArgumentException {
    if (digit < 0) {
      throw new IllegalArgumentException("Negative Numbers are not allowed.");
    }
    if (digit > 9) {
      throw new IllegalArgumentException("Invalid Input");
    }
    head = head.addDigit(digit);
  }

  @Override
  public int getDigitAt(int index) throws IllegalArgumentException {
    if (index < 0) {
      throw new IllegalArgumentException("Invalid index");
    }
    return head.getDigitAt(index);

  }

  @Override
  public BigNumber copy() {
    return new BigNumberImpl(this.toString());
  }

  @Override
  public BigNumber add(BigNumber other) throws IllegalArgumentException {
    if (other != null) {
      BigNumberListADTNode otherCopy = new BigNumberListADTElementNode(other.toString());
      return new BigNumberImpl(head.add(otherCopy).toString());
    } else {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  /**
   * A functionality to check if two BigNumbers are equal or not.
   *
   * @param o second bigNumber to be checked.
   * @return returns true if both bigNumbers have equal contents else return false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BigNumber)) {
      return false;
    }
    return head.toString().equals(o.toString());
  }

  /**
   * A functionality to get the hashcode of the given bigNumber.
   *
   * @return hashcode as int.
   */

  @Override
  public int hashCode() {
    String s = head.toString();
    return s.hashCode();
  }


  /**
   * A functionality that returns string representation of this bigNumber.
   *
   * @return returns a string representation of this number,
   *          which is simply the number itself.
   */
  @Override
  public String toString() {
    return head.toString();
  }


  /**
   * A functionality to compare two bigNumbers b1,b2.
   *
   * @param o second number to be compared(b2).
   * @return returns 0 when b1 is equals to b2,returns positive number
   *          when b1 is greater than b2 and returns negative number when b1 is less than b2.
   * @throws IllegalArgumentException when one of the numbers is null.
   */

  @Override
  public int compareTo(BigNumber o) throws IllegalArgumentException {
    if (o == null) {
      throw new IllegalArgumentException("Given Object is null");
    }
    if (this.length() > o.length()) {
      return 1;
    } else if (this.length() < o.length()) {
      return -1;
    } else {
      return this.toString().compareTo(o.toString());
    }
  }
}
