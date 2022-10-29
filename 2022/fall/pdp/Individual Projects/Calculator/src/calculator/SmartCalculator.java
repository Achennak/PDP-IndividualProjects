package calculator;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of SmartCalculator ,one of the Calculator model.
 */
public class SmartCalculator extends AbstractCalculator {

  /**
   * Constructs a basic calculator with empty result.
   */
  public SmartCalculator() {
    super();
  }


  /**
   * Constructs a smartCalculator with result so far and operand,
   * operators evaluating to that result.
   *
   * @param res           current result of calculator.
   * @param operandQueue  list of operands present in the expression.
   * @param operatorQueue operator of the expression.
   * @param prevOp        previous operator used to get the current result.
   */
  private SmartCalculator(String res, List<Integer> operandQueue,
                          List<Character> operatorQueue, char prevOp) {
    super(res, operandQueue, operatorQueue, prevOp);
  }

  /**
   * Helper method to check if current operators is "=".
   *
   * @param c current input of the expression.
   * @return new instance of Calculator with updated result.
   */
  @Override
  protected Calculator checkEqualsOperatorAndReturn(char c) {
    if (operandQueue.isEmpty() && operatorQueue.isEmpty()) {
      return new SmartCalculator(" ", new ArrayList<>(), new ArrayList<>(), ' ');
    } else if (operatorQueue.isEmpty()) {
      //should be valid for inputs like here Input is 32====
      //For Case like 32==9
      return new SmartCalculator(res, operandQueue, operatorQueue, c);
    } else {
      //for inputs like 3 2 + = =
      return performOperation(c);
    }
  }


  @Override
  protected void validateResultAndUpdate(char c) {
    String result = this.res;

    if (result != null && result.length() != 0 && result.charAt(0) == '-') {
      result = result.substring(1);
    }
    if ((result.contains("+") || result.contains("-") || result.contains("*"))) {
      //do nothing
    } else {
      //if result doesn't match with current result
      String current = getCurrentResult();
      if (c != '=' && !result.equals(current)) {
        if (c != '+' && c != '-' && c != '*') {
          //For inputs like 3+2==9
          operatorQueue.clear();
          operandQueue.clear();
        } else if (c == '-' || c == '+' || c == '*') {
          //for inputs like 3+2==-9
          operatorQueue.clear();
          if (!operandQueue.isEmpty() && operandQueue.size() > 1) {
            operandQueue.remove(1);
          }
        }
      }
    }
  }

  @Override
  protected Calculator getNewInstance(String res, List<Integer> operandQueue,
                                      List<Character> operatorQueue, char previousOperator) {
    return new SmartCalculator(res, operandQueue, operatorQueue, previousOperator);
  }

  @Override
  protected Calculator checkForIllegalArgumentAndReturn(char c) {
    if (c != '+') {
      //if first input it not '+' , then throw illegal argument exception.
      //Validate for negative inputs -12+3 and inputs like *12-7,
      if (c == '-') {
        throw new IllegalArgumentException("Negative inputs are not allowed.");
      } else {
        throw new IllegalArgumentException("Invalid input");
      }
    } else {
      return new SmartCalculator("", new ArrayList<>(), new ArrayList<>(), ' ');
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
        operandQueue.set(0, res);
      } else {
        operandQueue.add(0, res);
      }
      result = operandQueue.get(0).toString();
    } else {
      result = storePreviousValues2(res);
    }
    return result;
  }


  @Override
  protected Calculator adjustOperators(char c) {
    if (c != '=') {
      operatorQueue.remove(0);
      operatorQueue.add(c);
      String result1 = getCurrentResult();
      return new SmartCalculator(result1, operandQueue, operatorQueue, c);
    } else {
      //for inputs like 32+= this logic works

      int a1 = operandQueue.get(0);
      int a2 = operandQueue.get(0);
      return adjustOperatorsFurther(c, a1, a2);
    }

  }

  //Helper methods for smartCalculator.
  private Calculator adjustOperatorsFurther(char c, int a1, int a2) {
    int result = evaluateInput(a1, a2);
    String result1 = storePreviousValues1(c, result);
    return new SmartCalculator(result1, operandQueue, operatorQueue, c);
  }

  private String storePreviousValues1(char c, int res) {
    String result;
    if (c == '=') {
      int l = operandQueue.get(0);
      operandQueue.set(0, res);
      operandQueue.add(1, l);
      result = operandQueue.get(0).toString();
    } else {
      result = storePreviousValues2(res);
    }
    return result;
  }

  private String storePreviousValues2(int res) {
    String result;
    operandQueue.clear();
    operatorQueue.remove(0);
    operandQueue.add(res);
    result = getCurrentResult();
    return result;
  }


  private String getCurrentResult() {
    return getOperandStringFromList(operandQueue) + getOperatorStringFromList(operatorQueue);
  }

  /**
   * Helper method to fetch the string.
   *
   * @param list list of operands.
   * @return string from list.
   */
  private String getOperatorStringFromList(List<Character> list) {
    return list.toString().replace("[", "").replace("]", "");
  }

  /**
   * Helper method to fetch the string.
   *
   * @param list list of operators.
   * @return string from list.
   */
  private String getOperandStringFromList(List<Integer> list) {
    return list.toString().replace("[", "").replace("]", "");
  }
}
