package bignumber;

/**
 * This interface, implemented as an ADT,
 * represents all operations on a node in a list of numbers.
 * The advantage of the ADT design is that this interface may now represent
 * operations on a single node.
 */
public interface BigNumberListADTNode {

  /**
   * A functionality to get the length of this bigNumber List.
   *
   * @return returns the number of nodes in the list.
   */
  int length();

  /**
   * Helper method for fetching the length.
   *
   * @param acc accumulator
   * @return returns the number of digits in this BigNumber.
   */
  int helperCount(int acc);

  /**
   * This method takes the number of shifts as an argument and shifts this
   * number to the left by that number,
   * which is simply adding a node with value 0 at the back of the list.
   *
   * @param numShifts no of places to be shifted.
   * @return returns BigNumberListNode after shifting.
   */
  BigNumberListADTNode shiftLeft(int numShifts);

  /**
   * This method takes the number of shifts as an argument and shifts
   * this number to the right by that number,
   * which is simply removing the last node at the back of the list.
   *
   * @param numShifts no of places to be shifted.
   * @return returns BigNumberListNode after shifting.
   */
  BigNumberListADTNode shiftRight(int numShifts);

  /**
   * Helper method used in shiftRight method.
   *
   * @param node BigNumberListNode which acts as accumulator.
   * @param num  no of places to be shifted.
   * @return returns BigNumberListNode after shifting.
   */
  BigNumberListADTNode shiftRightHelper(BigNumberListADTNode node, int num);


  /**
   * This method takes a single digit as an argument and adds it to this number.
   *
   * @param digit digit to be added.
   * @return returns BigNumberListNode after adding this digit.
   */
  BigNumberListADTNode addDigit(int digit);

  /**
   * This method that takes a position as an argument and returns the digit at that position.
   *
   * @param index position of the digit in the list node.
   * @return returns the digit at the index given.
   */
  int getDigitAt(int index);


  /**
   * A functionality to add two BigNumbers and returns the sum of these two numbers.
   *
   * @param other second number to be added.
   * @return returns the sum of two bigNumbers.
   */
  BigNumberListADTNode add(BigNumberListADTNode other);

  /**
   * Helper method for toString method.
   *
   * @param s string accumulator.
   * @return returns the string representation of this number, as simply the number itself.
   */
  String toStringHelper(StringBuilder s);

  /**
   * Helper method for addDigit function.
   *
   * @param node  accumulator node.
   * @param digit digit to be added.
   * @return returns the list after adding this digit.
   */
  BigNumberListADTNode addDigitHelper(BigNumberListADTNode node, int digit);

  /**
   * A functionality that returns string representation of this number.
   *
   * @return returns the number itself as a string.
   */
  String toString();

  /**
   * Helper method used in adding twoBigNumbers method,when one of the bigNumber becomes null.
   *
   * @param carry carry to be forwarded to next node.
   * @return returns a new BigNumber node with current result.
   */
  BigNumberListADTNode addRestHelper(int carry);

  /**
   * Helper method used in adding twoBigNumbers method.
   *
   * @param second second bigNumber to be added.
   * @param carry  carry to be forwarded to next node.
   * @return returns a new BigNumber node with current result.
   */
  BigNumberListADTNode addHelper(BigNumberListADTNode second, int carry);
}
