package bignumber;

/**
 * This interface represents the List of numbers which is a very big Number.
 * It defines a few operations on BigNumbers, specifically with the following method signatures.
 */
public interface BigNumber extends Comparable<BigNumber> {

  /**
   * A functionality to get the length of this numberList.
   *
   * @return returns the number of digits in this bigNumber.
   */
  int length();

  /**
   * This method takes the number of shifts as an argument and
   * shifts this number to the left by that number,
   * which is simply multiplying this bigNumber by 10.
   *
   * @param numOfShifts no of digits to be shifted.
   */
  void shiftLeft(int numOfShifts);

  /**
   * This method takes the number of shifts as an argument and
   * shifts this number to the right by that number,
   * which is Simply dividing this bigNumber by 2.
   *
   * @param numOfShifts no of digits to be shifted.
   */
  void shiftRight(int numOfShifts);

  /**
   * This method takes a single digit as an argument and adds it to this bigNumber.
   *
   * @param digit digit to be added.
   * @throws IllegalArgumentException throws an IllegalArgumentException
   *                                  if the digit passed to it is not a single non-negative digit.
   */
  void addDigit(int digit) throws IllegalArgumentException;

  /**
   * This method that takes a position as an argument and
   * returns the digit at that position from bigNumberList.
   *
   * @param index position of the digit in the bigNumber list.
   * @return returns the digit at that index given.
   * @throws IllegalArgumentException throws an IllegalArgumentException
   *                                  if an invalid index is passed.
   */
  int getDigitAt(int index) throws IllegalArgumentException;

  /**
   * A functionality to get clone the given bingNumber.
   *
   * @return returns an identical and independent copy of this bigNumber.
   */
  BigNumber copy();

  /**
   * A functionality to add two BigNumbers and returns the sum of these two numbers.
   *
   * @param other second bigNumber to be added.
   * @return returns the sum of these two bigNumbers.
   * @throws IllegalArgumentException throws IllegalArgumentException when second number is null.
   */
  BigNumber add(BigNumber other) throws IllegalArgumentException;

}
