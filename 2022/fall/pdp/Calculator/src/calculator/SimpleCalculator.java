package calculator;

import java.util.List;

/**
 * Implementation of SimpleCalculator ,one of the Calculator model.
 */
public class SimpleCalculator extends AbstractCalculator {

  /**
   * Constructs a basic calculator with empty result.
   */
  public SimpleCalculator() {
    super();
  }

  /**
   * Constructs a simpleCalculator with result so far and operand,
   * operators evaluating to that result.
   *
   * @param res           current result of calculator.
   * @param operandQueue  list of operands present in the expression.
   * @param operatorQueue operator of the expression.
   * @param prevOperator  previous operator used to get the current result.
   */
  private SimpleCalculator(String res, List<Integer> operandQueue,
                           List<Character> operatorQueue, char prevOperator) {
    super(res, operandQueue, operatorQueue, prevOperator);
  }

  @Override
  protected void validateResultAndUpdate(char c) {
    //do nothing
  }

  /**
   * Helper method to check if current operators is "=".
   *
   * @param c current input of the expression.
   * @return new instance of Calculator with updated result.
   */
  @Override
  protected Calculator checkEqualsOperatorAndReturn(char c) {
    return new SimpleCalculator(res, operandQueue, operatorQueue, '=');
  }

  @Override
  protected Calculator adjustOperators(char c) {
    throw new IllegalArgumentException("Invalid input");
  }

  @Override
  protected Calculator checkForIllegalArgumentAndReturn(char c) {
    if (c == '=' && this.res != null && !this.res.isEmpty()) {
      return new SimpleCalculator(this.res, operandQueue, operatorQueue, '=');
    } else if (c == '-') {
      throw new IllegalArgumentException("Negative inputs are not allowed.");
    } else {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  /**
   * Helper method to update current result.
   *
   * @param c   current input of expression.
   * @param res current result.
   * @return new result as a String.
   */
  @Override
  protected String storePreviousValues(char c, int res) {
    String result;
    if (c == '=') {
      if (!operandQueue.isEmpty()) {
        System.out.println(operandQueue.get(0) + " " + operandQueue.get(1) + " result" + res);
        operandQueue.set(0, res);
        operandQueue.subList(1, operandQueue.size()).clear();
      } else {
        operandQueue.add(0, res);
      }
      operatorQueue.clear();

      return String.valueOf(res);

    } else {
      operandQueue.clear();
      operatorQueue.remove(0);
      operandQueue.add(res);
      result = getResultAfterOperation();
    }
    return result;
  }

  @Override
  protected Calculator getNewInstance(String res, List<Integer> operandQueue,
                                      List<Character> operatorQueue, char previousOperator) {
    return new SimpleCalculator(res, operandQueue, operatorQueue, previousOperator);
  }


}
