package calculator;


/**
 * This interface represents a single calculator.
 * This calculator works only with whole numbers.
 * It supports only three arithmetic operations: addition, subtraction and multiplication.
 */
public interface Calculator {

  /**
   * A functionality to perform addition, subtraction and multiplication
   * operations between given input numbers.
   *
   * @param c takes a single character as its only argument of whole
   *          numbers and one of operator from +,-,*
   *          and  if The input is 'C' then it will clear the calculator inputs.
   * @return return a Calculator object as a result of processing the input.
   * @throws IllegalArgumentException will throw an IllegalArgumentException for any invalid inputs.
   */

  Calculator input(char c);

  /**
   * A functionality to returns the current result of the calculator.
   * The result at any point should show either what was entered thus far, or the result.
   *
   * @return returns the current result as a String.
   */

  String getResult();

}
