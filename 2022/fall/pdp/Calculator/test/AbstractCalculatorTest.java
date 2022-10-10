import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;
import calculator.SmartCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link Calculator}s.
 */

abstract class AbstractCalculatorTest {

  protected Calculator calculator;

  protected abstract void initialiseCalculator();


  public static final class SimpleCalculatorTest extends AbstractCalculatorTest {

    @Override
    @Before
    public void initialiseCalculator() {
      calculator = new SimpleCalculator();
    }

    @Test
    public void testWhenFirstInputIsPlus() {
      String actualException = null;
      try {
        calculator.input('+');
      } catch (IllegalArgumentException e) {
        actualException = e.getMessage();
      }
      String expectedException = "Invalid input";
      assertEquals(actualException, expectedException);
    }

    //input is 326-=
    @Test
    public void testWhenInputHasWithMissingInputs() {
      String actualException = null;
      try {
        calculator = calculator.input('3');
        assertEquals("3", calculator.getResult());
        calculator = calculator.input('2');
        assertEquals("32", calculator.getResult());
        calculator = calculator.input('6');
        assertEquals("326", calculator.getResult());
        calculator = calculator.input('-');
        assertEquals("326-", calculator.getResult());
        calculator = calculator.input('=');
      } catch (IllegalArgumentException e) {
        actualException = e.getMessage();
      }
      String expectedException = "Invalid input";
      assertEquals(actualException, expectedException);
    }


    //input is 326+67-*
    @Test
    public void testWhenInputAreInvalidWithMissingInputs2() {
      String actualException = null;
      try {
        calculator = calculator.input('3');
        calculator = calculator.input('2');
        calculator = calculator.input('6');
        calculator = calculator.input('+');
        calculator = calculator.input('8');
        calculator = calculator.input('7');
        calculator = calculator.input('-');
        calculator = calculator.input('*');
      } catch (IllegalArgumentException e) {
        actualException = e.getMessage();
      }
      String expectedException = "Invalid input";
      assertEquals(actualException, expectedException);
    }

    //Input is 32-+
    @Test
    public void testWhenInputAreInvalidWithMissingInputs3() {
      String actualException = null;
      try {
        calculator = calculator.input('3');
        calculator = calculator.input('2');
        calculator = calculator.input('-');
        calculator = calculator.input('+');
      } catch (IllegalArgumentException e) {
        actualException = e.getMessage();
      }
      String expectedException = "Invalid input";
      assertEquals(actualException, expectedException);
    }

    //When Input is 32+-7
    @Test
    public void testWhenInputAreInvalidMissingInputs4() {
      String actualException = null;
      try {
        calculator = calculator.input('3');
        calculator = calculator.input('2');
        calculator = calculator.input('-');
        calculator = calculator.input('+');
        calculator = calculator.input('5');
      } catch (IllegalArgumentException e) {
        actualException = e.getMessage();
      }
      String expectedException = "Invalid input";
      assertEquals(actualException, expectedException);
    }

    //Input is 32+456+9=== and is valid input
    @Test
    public void testWhenInputAreValidWithMultipleEquals() {
      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("32+", calculator.getResult());
      calculator = calculator.input('4');
      assertEquals("32+4", calculator.getResult());
      calculator = calculator.input('5');
      assertEquals("32+45", calculator.getResult());
      calculator = calculator.input('6');
      assertEquals("32+456", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("488+", calculator.getResult());
      calculator = calculator.input('9');
      assertEquals("488+9", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("497", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("497", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("497", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("497", calculator.getResult());
    }

  }

  public static final class SmartCalculatorTest extends AbstractCalculatorTest {

    @Override
    @Before
    public void initialiseCalculator() {
      calculator = new SmartCalculator();
    }


    //Input is 32+=9 and is valid input
    @Test
    public void testWhenInputAreValidWithMissingInputs() {
      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("32+", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("64", calculator.getResult());
      calculator = calculator.input('9');
      assertEquals("9", calculator.getResult());
    }


    //Input is 32+456+9=== and is valid input
    @Test
    public void testWhenInputIsValidWithMultipleEquals() {
      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("32+", calculator.getResult());
      calculator = calculator.input('4');
      assertEquals("32+4", calculator.getResult());
      calculator = calculator.input('5');
      assertEquals("32+45", calculator.getResult());
      calculator = calculator.input('6');
      assertEquals("32+456", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("488+", calculator.getResult());
      calculator = calculator.input('9');
      assertEquals("488+9", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("497", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("506", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("515", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("524", calculator.getResult());
    }


    @Test
    public void testWhenFirstInputIsPlusOperator() {
      calculator = calculator.input('+');
      assertEquals("", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("2", calculator.getResult());
    }

    //Input is 32-+*
    @Test
    public void testWhenInputAreValidMultipleOperators() {
      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('-');
      assertEquals("32-", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("32+", calculator.getResult());
      calculator = calculator.input('*');
      assertEquals("32*", calculator.getResult());
    }

    //input is 326+=
    @Test
    public void testWhenInputHasWithMissingInputs() {

      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('6');
      assertEquals("326", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("326+", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("652", calculator.getResult());
    }


    //When Input is 32+-7
    @Test
    public void testWhenInputIsValidAndIgnoreFirstOperator() {

      calculator = calculator.input('3');
      calculator = calculator.input('2');
      calculator = calculator.input('-');
      calculator = calculator.input('+');
      calculator = calculator.input('5');
      assertEquals("32+5", calculator.getResult());
    }

    //Test inputs like +43+-====
    @Test
    public void testWhenInputIsValidSequence() {

      calculator = calculator.input('+');
      assertEquals("", calculator.getResult());
      calculator = calculator.input('4');
      assertEquals("4", calculator.getResult());
      calculator = calculator.input('3');
      assertEquals("43", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("43+", calculator.getResult());
      calculator = calculator.input('-');
      assertEquals("43-", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("0", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("-43", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("-86", calculator.getResult());
    }

    //Test inputs like +43+====
    @Test
    public void testWhenInputIsValidSequence1() {
      calculator = calculator.input('+');
      calculator = calculator.input('4');
      assertEquals("4", calculator.getResult());
      calculator = calculator.input('3');
      assertEquals("43", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("43+", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("86", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("129", calculator.getResult());
    }

    //input is 326+67-*
    @Test
    public void testWhenInputAreValidSequence() {
      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('6');
      assertEquals("326", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("326+", calculator.getResult());
      calculator = calculator.input('8');
      assertEquals("326+8", calculator.getResult());
      calculator = calculator.input('7');
      assertEquals("326+87", calculator.getResult());
      calculator = calculator.input('-');
      assertEquals("413-", calculator.getResult());
      calculator = calculator.input('*');
      assertEquals("413*", calculator.getResult());
      calculator = calculator.input('-');
      assertEquals("413-", calculator.getResult());

    }

    //Input is 32-+*= , should return 1024
    @Test
    public void testWhenInputIsValidSequence4() {
      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('-');
      assertEquals("32-", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("32+", calculator.getResult());
      calculator = calculator.input('*');
      assertEquals("32*", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("1024", calculator.getResult());
    }


    @Test
    public void testInputs1() {
      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("32+", calculator.getResult());
      calculator = calculator.input('-');
      assertEquals("32-", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("32+", calculator.getResult());
      calculator = calculator.input('1');
      assertEquals("32+1", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32+12", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("44", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("56", calculator.getResult());
    }


    @Test
    public void testInputs2() {
      calculator = calculator.input('+');
      assertEquals("", calculator.getResult());
      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("32+", calculator.getResult());
      calculator = calculator.input('-');
      assertEquals("32-", calculator.getResult());
      calculator = calculator.input('1');
      assertEquals("32-1", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32-12", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("20", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("8", calculator.getResult());
    }


    @Test
    public void testInputs3() {
      calculator = calculator.input('3');
      assertEquals("3", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("32+", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("32+2", calculator.getResult());
      calculator = calculator.input('5');
      assertEquals("32+25", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("57", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("82", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("82+", calculator.getResult());
      calculator = calculator.input('2');
      assertEquals("82+2", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("84", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("86", calculator.getResult());
    }

    @Test
    public void testInput4() {
      calculator = calculator.input('0');
      calculator = calculator.input('+');
      calculator = calculator.input('0');
      calculator = calculator.input('=');
      calculator = calculator.input('-');
      calculator = calculator.input('4');
      calculator = calculator.input('*');
      calculator = calculator.input('2');
      assertEquals("-4*2", calculator.getResult());
      calculator = calculator.input('1');
      assertEquals("-4*21", calculator.getResult());
      calculator = calculator.input('4');
      assertEquals("-4*214", calculator.getResult());
      calculator = calculator.input('7');
      assertEquals("-4*2147", calculator.getResult());
      calculator = calculator.input('4');
      assertEquals("-4*21474", calculator.getResult());
      calculator = calculator.input('8');
      assertEquals("-4*214748", calculator.getResult());
      calculator = calculator.input('3');
      assertEquals("-4*2147483", calculator.getResult());
      calculator = calculator.input('6');
      assertEquals("-4*21474836", calculator.getResult());
      calculator = calculator.input('4');
      assertEquals("-4*214748364", calculator.getResult());
      calculator = calculator.input('7');
      assertEquals("-4*2147483647", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("0", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("0", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("0+", calculator.getResult());
      calculator = calculator.input('5');
      assertEquals("0+5", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("5", calculator.getResult());
    }

    @Test
    public void testInput5() {
      calculator = calculator.input('0');
      calculator = calculator.input('+');
      calculator = calculator.input('4');
      calculator = calculator.input('5');
      calculator = calculator.input('-');
      calculator = calculator.input('-');
      assertEquals("45-", calculator.getResult());
      calculator = calculator.input('6');
      calculator = calculator.input('7');
      assertEquals("45-67", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("-22", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("-89", calculator.getResult());
      calculator = calculator.input('+');
      assertEquals("-89+", calculator.getResult());
      calculator = calculator.input('5');
      assertEquals("-89+5", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("-84", calculator.getResult());
    }

    @Test
    public void testInput6() {
      calculator = calculator.input('9');
      calculator = calculator.input('0');
      calculator = calculator.input('-');
      calculator = calculator.input('-');
      calculator = calculator.input('2');
      calculator = calculator.input('8');
      calculator = calculator.input('*');
      calculator = calculator.input('3');
      calculator = calculator.input('0');
      assertEquals("62*30", calculator.getResult());
      calculator = calculator.input('+');
      calculator = calculator.input('*');
      calculator = calculator.input('6');
      calculator = calculator.input('9');
      calculator = calculator.input('-');
      calculator = calculator.input('+');
      calculator = calculator.input('4');
      calculator = calculator.input('0');
      assertEquals("128340+40", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("128380", calculator.getResult());
      calculator = calculator.input('=');
      assertEquals("128420", calculator.getResult());
      calculator = calculator.input('9');
      assertEquals("9", calculator.getResult());
    }

  }

  /**
   * The tests for Calculator begin here.
   * These tests are common for all implementations.
   */


  @Test
  public void testInitialResult() {
    assertEquals("", calculator.getResult());
  }

  @Test
  public void testWhenInputsAreAlphabets() {
    String actualException = null;
    try {
      calculator.input('c');
    } catch (IllegalArgumentException e) {
      actualException = e.getMessage();
    }
    String expectedException = "Invalid input provided. Valid operand characters range "
            + "from 0 to 9, and valid operators include +, -, and *.";
    assertEquals(actualException, expectedException);
  }

  @Test
  public void testWhenFirstInputIsEquals() {
    String actualException = null;
    try {
      calculator.input('=');
    } catch (IllegalArgumentException e) {
      actualException = e.getMessage();
    }
    String expectedException = "Invalid input";
    assertEquals(actualException, expectedException);
  }

  @Test
  public void testWhenFirstInputIsOperator() {
    String actualException = null;
    try {
      calculator.input('*');
    } catch (IllegalArgumentException e) {
      actualException = e.getMessage();
    }
    String expectedException = "Invalid input";
    assertEquals(actualException, expectedException);
  }


  //Input is 32=== and is valid input
  @Test
  public void testWhenInputHasMultipleEquals() {
    calculator = calculator.input('3');
    assertEquals("3", calculator.getResult());
    calculator = calculator.input('2');
    assertEquals("32", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("32", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("32", calculator.getResult());
  }

  @Test
  public void testWhenInputIsNegative() {
    String actualException = null;
    try {
      calculator.input('-');
    } catch (IllegalArgumentException e) {
      actualException = e.getMessage();
    }
    String expectedException = "Negative inputs are not allowed.";
    assertEquals(actualException, expectedException);
  }

  //32+24=6=
  @Test
  public void testWhenInputsAreValid() {
    calculator = calculator.input('3');
    calculator = calculator.input('2');
    calculator = calculator.input('+');
    calculator = calculator.input('2');
    calculator = calculator.input('4');
    calculator = calculator.input('=');
    calculator = calculator.input('6');
    assertEquals("6", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("6", calculator.getResult());
  }

  //Input is 32= and is valid input
  @Test
  public void testWhenInputAreValid2() {
    calculator = calculator.input('3');
    assertEquals("3", calculator.getResult());
    calculator = calculator.input('2');
    assertEquals("32", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("32", calculator.getResult());
  }

  //Input is 03+03= and is valid input
  @Test
  public void testWhenInputAreValid4() {
    calculator = calculator.input('0');
    assertEquals("0", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("3", calculator.getResult());
    calculator = calculator.input('+');
    assertEquals("3+", calculator.getResult());
    calculator = calculator.input('0');
    assertEquals("3+0", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("3+3", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("6", calculator.getResult());
  }

  @Test
  public void testClearInput() {
    calculator = calculator.input('3');
    calculator = calculator.input('2');
    assertEquals("32", calculator.getResult());
    calculator = calculator.input('C');
    assertEquals("", calculator.getResult());
  }

  @Test
  public void testClearInputFirst() {
    calculator = calculator.input('C');
    assertEquals("", calculator.getResult());
    calculator = calculator.input('C');
    assertEquals("", calculator.getResult());
  }

  @Test
  public void testGetResultAfterInput() {
    calculator = calculator.input('3');
    assertEquals("3", calculator.getResult());
  }

  //Input is 1+001 and is valid input
  @Test
  public void testWhenInputAreValid3() {
    calculator = calculator.input('1');
    assertEquals("1", calculator.getResult());
    calculator = calculator.input('+');
    assertEquals("1+", calculator.getResult());
    calculator = calculator.input('0');
    assertEquals("1+0", calculator.getResult());
    calculator = calculator.input('0');
    assertEquals("1+0", calculator.getResult());
    calculator = calculator.input('1');
    assertEquals("1+1", calculator.getResult());
    calculator = calculator.input('+');
    assertEquals("2+", calculator.getResult());

  }

  //Input is 10001+ and is valid input
  @Test
  public void testWhenInputAreValid5() {
    calculator = calculator.input('1');
    calculator = calculator.input('0');
    assertEquals("10", calculator.getResult());
    calculator = calculator.input('0');
    assertEquals("100", calculator.getResult());
    calculator = calculator.input('1');
    assertEquals("1001", calculator.getResult());
    calculator = calculator.input('+');
    assertEquals("1001+", calculator.getResult());
    calculator = calculator.input('6');
    assertEquals("1001+6", calculator.getResult());
    calculator = calculator.input('-');
    assertEquals("1007-", calculator.getResult());
  }

  //Input is 0001+ and is valid input
  @Test
  public void testWhenInputAreValid6() {
    calculator = calculator.input('0');
    assertEquals("0", calculator.getResult());
    calculator = calculator.input('0');
    assertEquals("0", calculator.getResult());
    calculator = calculator.input('1');
    assertEquals("1", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("1", calculator.getResult());
  }

  @Test
  public void testGetResultAfterClear() {
    calculator = calculator.input('3');
    assertEquals("3", calculator.getResult());
    calculator = calculator.input('C');
    assertEquals("", calculator.getResult());
  }

  @Test
  public void testGetResultAfterException() {
    String actualException = null;
    try {
      calculator = calculator.input('3');
      calculator = calculator.input('a');
    } catch (IllegalArgumentException e) {
      actualException = e.getMessage();
    }
    String expectedException = "Invalid input provided. Valid operand characters "
            + "range from 0 to 9, and valid operators include +, -, and *.";
    assertEquals(actualException, expectedException);
    assertEquals("3", calculator.getResult());
  }

  //Input is 32=9 and is valid input
  @Test
  public void testWhenInputAreValid7() {
    calculator = calculator.input('3');
    assertEquals("3", calculator.getResult());
    calculator = calculator.input('2');
    assertEquals("32", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("32", calculator.getResult());
    calculator = calculator.input('9');
    assertEquals("9", calculator.getResult());
  }

  //Input is 3+2=9 and is valid input
  @Test
  public void testWhenInputAreValidOperation() {
    calculator = calculator.input('3');
    assertEquals("3", calculator.getResult());
    calculator = calculator.input('+');
    calculator = calculator.input('2');
    assertEquals("3+2", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("5", calculator.getResult());
    calculator = calculator.input('9');
    assertEquals("9", calculator.getResult());
  }


  @Test
  public void testWhenOperandOverflows() {
    calculator = calculator.input('1');
    assertEquals("1", calculator.getResult());
    calculator = calculator.input('+');
    assertEquals("1+", calculator.getResult());
    calculator = calculator.input('1');
    assertEquals("1+1", calculator.getResult());
    calculator = calculator.input('2');
    assertEquals("1+12", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1+123", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("1+1234", calculator.getResult());
    calculator = calculator.input('5');
    assertEquals("1+12345", calculator.getResult());
    calculator = calculator.input('6');
    assertEquals("1+123456", calculator.getResult());
    calculator = calculator.input('7');
    assertEquals("1+1234567", calculator.getResult());
    calculator = calculator.input('8');
    assertEquals("1+12345678", calculator.getResult());
    calculator = calculator.input('9');
    assertEquals("1+123456789", calculator.getResult());
    calculator = calculator.input('0');
    assertEquals("1+1234567890", calculator.getResult());
    String message = "Operand overflow occurred";
    String actual = "";
    try {
      //excepted operand overflow
      calculator = calculator.input('3');
    } catch (IllegalArgumentException e) {
      actual = e.getMessage();
    }
    assertEquals(message, actual);
    assertEquals("1+1234567890", calculator.getResult());
    try {
      calculator = calculator.input('3');
    } catch (IllegalArgumentException e) {
      actual = e.getMessage();
    }
    assertEquals(message, actual);
    assertEquals("1+1234567890", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("1234567891", calculator.getResult());
  }


  @Test
  public void testWhenAddOperationOverflow() {
    calculator = calculator.input('2');
    assertEquals("2", calculator.getResult());
    calculator = calculator.input('1');
    assertEquals("21", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("214", calculator.getResult());
    calculator = calculator.input('7');
    assertEquals("2147", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("21474", calculator.getResult());
    calculator = calculator.input('8');
    assertEquals("214748", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("2147483", calculator.getResult());
    calculator = calculator.input('6');
    assertEquals("21474836", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("214748364", calculator.getResult());
    calculator = calculator.input('7');
    assertEquals("2147483647", calculator.getResult());
    //It should retain same input as bfr after operand overflow
    String message = "Operand overflow occurred";
    String actual = "";
    try {
      calculator = calculator.input('9');
    } catch (IllegalArgumentException e) {
      actual = e.getMessage();
    }
    assertEquals(actual, message);
    assertEquals("2147483647", calculator.getResult());
    calculator = calculator.input('+');
    assertEquals("2147483647+", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("2147483647+3", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("0", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("0", calculator.getResult());

    calculator = calculator.input('+');
    assertEquals("0+", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("0+3", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("3", calculator.getResult());
  }

  @Test
  public void testWhenSubtractOperationOverflow() {
    calculator = calculator.input('1');
    assertEquals("1", calculator.getResult());
    calculator = calculator.input('+');
    assertEquals("1+", calculator.getResult());
    calculator = calculator.input('1');
    assertEquals("1+1", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("2", calculator.getResult());
    calculator = calculator.input('-');
    assertEquals("2-", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("2-4", calculator.getResult());
    calculator = calculator.input('-');
    assertEquals("-2-", calculator.getResult());
    calculator = calculator.input('2');
    assertEquals("-2-2", calculator.getResult());
    calculator = calculator.input('1');
    assertEquals("-2-21", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("-2-214", calculator.getResult());
    calculator = calculator.input('7');
    assertEquals("-2-2147", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("-2-21474", calculator.getResult());
    calculator = calculator.input('8');
    assertEquals("-2-214748", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("-2-2147483", calculator.getResult());
    calculator = calculator.input('6');
    assertEquals("-2-21474836", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("-2-214748364", calculator.getResult());
    calculator = calculator.input('7');
    assertEquals("-2-2147483647", calculator.getResult());
    //It should retain same input as bfr after operand overflow
    String message = "Operand overflow occurred";
    String actual = "";
    try {
      calculator = calculator.input('9');
    } catch (IllegalArgumentException e) {
      actual = e.getMessage();
    }
    assertEquals(message, actual);
    assertEquals("-2-2147483647", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("0", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("0", calculator.getResult());
    calculator = calculator.input('-');
    assertEquals("0-", calculator.getResult());
    calculator = calculator.input('1');
    assertEquals("0-1", calculator.getResult());
    calculator = calculator.input('0');
    assertEquals("0-10", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("-10", calculator.getResult());
  }

  @Test
  public void testWhenMultiplyOperationOverflow() {
    calculator = calculator.input('2');
    assertEquals("2", calculator.getResult());
    calculator = calculator.input('1');
    assertEquals("21", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("214", calculator.getResult());
    calculator = calculator.input('7');
    assertEquals("2147", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("21474", calculator.getResult());
    calculator = calculator.input('8');
    assertEquals("214748", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("2147483", calculator.getResult());
    calculator = calculator.input('6');
    assertEquals("21474836", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("214748364", calculator.getResult());
    calculator = calculator.input('7');
    assertEquals("2147483647", calculator.getResult());
    //It should retain same input as bfr after operand overflow
    String message = "Operand overflow occurred";
    String actual = "";
    try {
      calculator = calculator.input('9');
    } catch (IllegalArgumentException e) {
      actual = e.getMessage();
    }
    assertEquals(message, actual);
    assertEquals("2147483647", calculator.getResult());
    calculator = calculator.input('*');
    assertEquals("2147483647*", calculator.getResult());
    calculator = calculator.input('9');
    assertEquals("2147483647*9", calculator.getResult());

    calculator = calculator.input('=');
    assertEquals("0", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("0", calculator.getResult());

    calculator = calculator.input('+');
    assertEquals("0+", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("0+3", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("3", calculator.getResult());
    calculator = calculator.input('*');
    assertEquals("3*", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("3*4", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("12", calculator.getResult());
  }

  //Input is 1234567890+33333333= and is valid input

  @Test
  public void testWhenOperandOverflow2() {
    calculator = calculator.input('1');
    assertEquals("1", calculator.getResult());
    calculator = calculator.input('2');
    assertEquals("12", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("123", calculator.getResult());
    calculator = calculator.input('4');
    assertEquals("1234", calculator.getResult());
    calculator = calculator.input('5');
    assertEquals("12345", calculator.getResult());
    calculator = calculator.input('6');
    assertEquals("123456", calculator.getResult());
    calculator = calculator.input('7');
    assertEquals("1234567", calculator.getResult());
    calculator = calculator.input('8');
    assertEquals("12345678", calculator.getResult());
    calculator = calculator.input('9');
    assertEquals("123456789", calculator.getResult());
    calculator = calculator.input('0');
    assertEquals("1234567890", calculator.getResult());
    //It should retain same input as bfr after operand overflow
    String message = "Operand overflow occurred";
    String actual = "";
    try {
      calculator = calculator.input('9');
    } catch (IllegalArgumentException e) {
      actual = e.getMessage();
    }
    assertEquals(message, actual);
    assertEquals("1234567890", calculator.getResult());
    calculator = calculator.input('+');
    assertEquals("1234567890+", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1234567890+3", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1234567890+33", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1234567890+333", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1234567890+3333", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1234567890+33333", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1234567890+333333", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1234567890+3333333", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1234567890+33333333", calculator.getResult());
    calculator = calculator.input('3');
    assertEquals("1234567890+333333333", calculator.getResult());
    try {
      calculator = calculator.input('8');
    } catch (IllegalArgumentException e) {
      actual = e.getMessage();
    }
    assertEquals(message, actual);
    //It should retain same input as bfr after operand overflow
    assertEquals("1234567890+333333333", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("1567901223", calculator.getResult());
  }

  //Input is 3+2=-9 and is valid input
  @Test
  public void testWhenInputIsValid() {
    calculator = calculator.input('3');
    assertEquals("3", calculator.getResult());
    calculator = calculator.input('+');
    assertEquals("3+", calculator.getResult());
    calculator = calculator.input('2');
    assertEquals("3+2", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("5", calculator.getResult());
    calculator = calculator.input('-');
    assertEquals("5-", calculator.getResult());
    calculator = calculator.input('9');
    assertEquals("5-9", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("-4", calculator.getResult());
  }

  //Input is 32==9 and is valid input
  @Test
  public void testWhenInputIsValidWithOperandAfterEquals() {
    calculator = calculator.input('3');
    assertEquals("3", calculator.getResult());
    calculator = calculator.input('2');
    assertEquals("32", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("32", calculator.getResult());
    calculator = calculator.input('=');
    assertEquals("32", calculator.getResult());
    calculator = calculator.input('9');
    assertEquals("9", calculator.getResult());
  }


}
