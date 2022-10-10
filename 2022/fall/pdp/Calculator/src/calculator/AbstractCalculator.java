package calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for implementations of {@link Calculator}. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link Calculator} interface. A new implementation of
 * the interface has the option of extending this class, or directly
 * implementing all the methods.
 */

abstract class AbstractCalculator implements Calculator {

  protected final String res;
  protected final List<Character> operatorQueue;
  protected final List<Integer> operandQueue;

  protected final char prevOperator;

  /**
   * Constructs a basic calculator with empty result.
   */
  public AbstractCalculator() {
    this.res = "";
    this.prevOperator = ' ';
    this.operatorQueue = new ArrayList<>();
    this.operandQueue = new ArrayList<>();
  }

  /**
   * Constructs a calculator with result so far and operand,operators evaluating to that result.
   *
   * @param res              current result of calculator.
   * @param operandQueue     list of operands present in the expression.
   * @param operatorQueue    operator of the expression.
   * @param previousOperator previous operator used to get the current result.
   */
  protected AbstractCalculator(String res, List<Integer> operandQueue,
                               List<Character> operatorQueue, char previousOperator) {
    this.res = res;
    this.operatorQueue = operatorQueue;
    this.operandQueue = operandQueue;
    this.prevOperator = previousOperator;
  }

  @Override
  public String getResult() {
    return this.res;
  }

  @Override
  public Calculator input(char c) throws IllegalArgumentException {
    if (c == 'C') {
      return clearInput();
    } else if (c == '=' || c == '+' || c == '-' || c == '*') {
      validateResultAndUpdate(c);
      if (operandQueue.isEmpty()) {

        return checkForIllegalArgumentAndReturn(c);

      } else if (!operatorQueue.isEmpty()) {

        if (operandQueue.size() == 2) {

          if (c != '=') {
            //Add operator for inputs like 123+67- and dont add for inputs like 678+635=
            operatorQueue.add(c);
          }
          return performOperation(c);
        } else {
          //add abstract method
          return adjustOperators(c);
        }

      } else if (c == '=') {
        //add abstract method
        return checkEqualsOperatorAndReturn(c);
      } else if (c == '+' || c == '-' || c == '*') {
        operatorQueue.add(c);
        String result1 = getResultAfterOperation();
        return getNewInstance(result1, operandQueue, operatorQueue, c);
      }
    } else if ((int) c >= 48 && (int) c <= 57) {
      if (this.prevOperator == '=' && ((int) c >= 48 && (int) c <= 57)) {
        operatorQueue.clear();
        operandQueue.clear();
        operandQueue.add(0, c - '0');
        String result = getResultAfterOperation();
        return getNewInstance(result, operandQueue, operatorQueue, ' ');
      }
      updateOperands(c);
      String result1 = getResultAfterOperation();
      return getNewInstance(result1, operandQueue, operatorQueue, ' ');

    } else {
      throw new IllegalArgumentException("Invalid input provided. Valid operand characters "
              + "range from 0 to 9, and valid operators include +, -, and *.");
    }
    return null;
  }


  // All helper methods are defined below.

  private Calculator clearInput() {
    String result = "";
    return getNewInstance(result, new ArrayList<>(), new ArrayList<>(), ' ');
  }

  private Calculator performOperationFurther(char c, int a1, int a2) {
    int result = evaluateInput(a1, a2);
    String result1 = storePreviousValues(c, result);
    return getNewInstance(result1, operandQueue, operatorQueue, c);
  }

  private int add(int val1, int val2) {
    long result = (long) val1 + val2;
    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
      throw new IllegalArgumentException("Overflow!");
    }
    return (int) result;
  }

  private int subtract(int val1, int val2) {
    long result = (long) val1 - val2;
    System.out.println("long value is " + result);
    System.out.println(Integer.MIN_VALUE);
    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
      throw new IllegalArgumentException("Overflow!");
    }
    return (int) result;
  }

  private int multiply(int val1, int val2) {
    long result = (long) val1 * val2;
    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
      throw new IllegalArgumentException("Overflow!");
    }
    return (int) result;
  }


  private void updateOperands(char input) {
    if (operatorQueue.isEmpty()) {
      //update first operand
      System.out.println("First operand " + operandQueue.size());
      StringBuilder s1 = new StringBuilder();
      for (Integer integer : operandQueue) {
        s1.append(integer);
      }
      s1.append(input);
      long c = Long.parseLong(s1.toString());
      if (c <= Integer.MAX_VALUE) {
        System.out.println("current input " + (int) c);
        operandQueue.clear();
        operandQueue.add((int) c);
      } else {
        throw new IllegalArgumentException("Operand overflow occurred");
      }
    } else if (!operatorQueue.isEmpty()) {
      //update second operand
      int len = operandQueue.size();
      System.out.println("Second operand " + len);
      StringBuilder s1 = new StringBuilder();
      for (int i = 1; i < len; i++) {
        s1.append(operandQueue.get(i));
      }
      s1.append(input);
      System.out.println("Second operand string " + s1);
      long c = Long.parseLong(s1.toString());
      System.out.println("Second operand result in long " + c);
      if (c > Integer.MAX_VALUE) {
        //do nothing
        throw new IllegalArgumentException("Operand overflow occurred");
      } else if (c <= Integer.MAX_VALUE) {
        if (len > 1) {
          operandQueue.subList(1, len).clear();
        }
        operandQueue.add((int) c);
      }
    }
  }

  /**
   * Helper method to get the result after any operation.
   *
   * @return current input.
   */
  protected String getResultAfterOperation() {
    StringBuilder result = new StringBuilder();
    if (operandQueue != null && operandQueue.get(0) != null) {
      result.append(operandQueue.get(0));
    }
    if (operatorQueue != null && operatorQueue.size() > 0 && operatorQueue.get(0) != null) {
      result.append(operatorQueue.get(0));
    }
    if (operandQueue != null && operandQueue.size() > 1 && operandQueue.get(1) != null) {
      result.append(operandQueue.get(1));
    }
    return result.toString();
  }

  /**
   * Helper method to perform operations.
   *
   * @param c current input.
   * @return return the result of the operation.
   */

  protected Calculator performOperation(char c) {
    int a1 = operandQueue.get(0);
    int a2 = operandQueue.get(1);
    return performOperationFurther(c, a1, a2);
  }

  /**
   * Helper method to evaluate operations.
   *
   * @param a1 first operand of expression.
   * @param a2 second operand of expression.
   * @return result of the operation.
   */

  protected int evaluateInput(int a1, int a2) {
    char operator = operatorQueue.get(0);
    int result = 0;
    if (operator == '+') {
      try {
        result = add(a1, a2);
      } catch (IllegalArgumentException e) {
        result = 0;
        operandQueue.clear();
        operatorQueue.clear();
      }
    } else if (operator == '-') {
      try {
        result = subtract(a1, a2);
      } catch (IllegalArgumentException e) {
        result = 0;
        operandQueue.clear();
        operatorQueue.clear();
      }
    } else if (operator == '*') {
      try {
        result = multiply(a1, a2);
      } catch (IllegalArgumentException e) {
        result = 0;
        operandQueue.clear();
        operatorQueue.clear();
      }
    }
    return result;
  }

  //All Abstracts methods are defined below.

  /**
   * Helper method to get new instance of Calculator.
   *
   * @param res              current result of calculator.
   * @param operandQueue     list of operands present in the expression.
   * @param operatorQueue    operator of the expression.
   * @param previousOperator previous operator used to get the current result.
   * @return new Instance of Calculator.
   */

  protected abstract Calculator getNewInstance(String res,
                                               List<Integer> operandQueue,
                                               List<Character> operatorQueue,
                                               char previousOperator);

  /**
   * Helper method to update current result.
   *
   * @param c   current input of expression.
   * @param res current result.
   * @return new result as a String.
   */
  abstract String storePreviousValues(char c, int res);

  /**
   * Helper method to update the result.
   *
   * @param c current input of expression.
   */
  protected abstract void validateResultAndUpdate(char c);

  /**
   * Helper method to validate current operator and
   * throw IllegalArgument Exception.
   *
   * @param c current input.
   * @return new instance of Calculator with updated result.
   */
  protected abstract Calculator checkForIllegalArgumentAndReturn(char c);

  /**
   * Helper method to adjust operators and operands when input is "=".
   *
   * @param c current input of the expression.
   * @return new instance of Calculator with updated result.
   */
  protected abstract Calculator adjustOperators(char c);

  /**
   * Helper method to check if current operators is "=".
   *
   * @param c current input of the expression.
   * @return new instance of Calculator with updated result.
   */
  abstract Calculator checkEqualsOperatorAndReturn(char c);

}
