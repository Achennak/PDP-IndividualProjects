import org.junit.Before;
import org.junit.Test;

import bignumber.BigNumber;
import bignumber.BigNumberImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class represents test class for BigNumber implementation.
 */

public class BigNumberImplTest {

  private BigNumber list;

  @Before
  public void setUp() {
    list = new BigNumberImpl();
  }

  @Test
  public void testInitialState() {
    assertEquals("0", list.toString());
  }

  @Test
  public void testInitialLength() {
    assertEquals(1, list.length());
  }

  @Test
  public void testInvalidNumericString() {
    String actual = "Invalid input";
    String expected = "";
    try {
      list = new BigNumberImpl("9.05");
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
    expected = "";
    try {
      list = new BigNumberImpl("ab05");
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
    expected = "";
    try {
      list = new BigNumberImpl("-05");
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
    expected = "";
    try {
      list = new BigNumberImpl("83648726472.5");
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
    expected = "";

    try {
      list = new BigNumberImpl("abcd");
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
    expected = "";
    try {
      list = new BigNumberImpl("@#$836487264725");
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
    expected = "";
    try {
      list = new BigNumberImpl("");
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);

    expected = "";
    try {
      list = new BigNumberImpl(null);
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);

  }


  @Test
  public void testInvalidString() {
    String actual = "Invalid input";
    String expected = "";
    try {
      list = new BigNumberImpl("0ab$23");
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
  }


  @Test
  public void testNumberConstructorWithLeadingZeros() {
    list = new BigNumberImpl("0023");
    assertEquals("23", list.toString());
    list = new BigNumberImpl("0");
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    list = new BigNumberImpl("00");
    assertEquals("0", list.toString());
    list = new BigNumberImpl("00000");
    assertEquals("0", list.toString());
  }

  @Test
  public void testConstructorWithBigNumber() {
    list = constructABigNumber(900);
    assertEquals(8101, list.length());
    list = new BigNumberImpl(list.toString());
    assertEquals(8101, list.length());
  }

  @Test
  public void testConstructBigNumberFromString() {
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    list = new BigNumberImpl("123456");
    assertEquals(6, list.length());
    assertEquals("123456", list.toString());
  }

  @Test
  public void testConstructorWithValidInput() {
    list = new BigNumberImpl("1234567890");
    assertEquals(10, list.length());
    assertEquals("1234567890", list.toString());
  }


  @Test
  public void testNumberCreationByShifting() {
    list = new BigNumberImpl();
    list.shiftLeft(1);
    list.addDigit(3);
    assertEquals("3", list.toString());
    list.shiftLeft(1);
    assertEquals("30", list.toString());
    list.addDigit(4);
    assertEquals("34", list.toString());
    list.addDigit(6);
    assertEquals("40", list.toString());
  }

  @Test
  public void testNumberCreationByShifting2() {
    list = new BigNumberImpl();
    assertEquals("0", list.toString());
    list.shiftLeft(1);
    list.addDigit(0);
    assertEquals("0", list.toString());
    list.shiftLeft(1);
    assertEquals("0", list.toString());
    list.addDigit(0);
    assertEquals("0", list.toString());
    list.addDigit(0);
    assertEquals("0", list.toString());
    list.addDigit(6);
    assertEquals("6", list.toString());
  }

  @Test
  public void testNumberCreationByShifting1() {
    list = new BigNumberImpl();
    list.shiftLeft(1);
    list.addDigit(6);
    assertEquals("6", list.toString());
    list.shiftLeft(1);
    assertEquals("60", list.toString());
    list.addDigit(5);
    assertEquals("65", list.toString());
    list.shiftLeft(1);
    list.addDigit(4);
    assertEquals("654", list.toString());
    list.shiftRight(1);
    assertEquals("65", list.toString());
    list.shiftRight(1);
    assertEquals("6", list.toString());
    list.shiftRight(1);
    assertEquals("0", list.toString());
  }


  @Test
  public void testInitialShiftLeftOnZero() {
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    list.shiftLeft(3);
    assertEquals("0", list.toString());
    list.shiftRight(-10);
    assertEquals("0", list.toString());
  }

  @Test
  public void testShiftLeftWithNegativeNumber() {
    list = constructList();
    assertEquals("324110", list.toString());
    list.addDigit(0);
    assertEquals("324110", list.toString());
    list.shiftLeft(-2);
    assertEquals("3241", list.toString());
    list.shiftLeft(-2);
    assertEquals("32", list.toString());
    list.shiftLeft(-2);
    assertEquals("0", list.toString());
    list.shiftLeft(-2);
    assertEquals("0", list.toString());
    list.shiftLeft(-2);
    assertEquals("0", list.toString());
  }

  @Test
  public void testShiftLeftWithPositiveNumber() {
    list = constructList();
    assertEquals("324110", list.toString());
    list.addDigit(0);
    assertEquals("324110", list.toString());
    list.shiftLeft(4);
    assertEquals("3241100000", list.toString());
    list.shiftLeft(0);
    assertEquals("3241100000", list.toString());
  }

  @Test
  public void testShiftLeftWithNumberGreaterThanLength() {
    list = constructList();
    assertEquals("324110", list.toString());
    assertEquals(6, list.length());
    list.shiftLeft(10);
    assertEquals("3241100000000000", list.toString());
  }

  @Test(timeout = 2000)
  public void testShiftLeftBigNumber() {
    list = constructABigNumber(500);
    list.shiftLeft(1000);
    assertEquals(5501, list.length());
    list.shiftLeft(-10);
    assertEquals(5491, list.length());
    list.shiftLeft(-500);
    assertEquals(4991, list.length());
    list.shiftLeft(5000);
    assertEquals(9991, list.length());
  }

  @Test
  public void testShiftRightWithNumberGreaterThanLength() {
    list = constructList();
    assertEquals("324110", list.toString());
    assertEquals(6, list.length());
    list.shiftRight(10);
    assertEquals("0", list.toString());
  }


  @Test
  public void testInitialShiftRightOnZero() {
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    list.shiftRight(3);
    assertEquals("0", list.toString());
    list.shiftLeft(-10);
    assertEquals("0", list.toString());
  }

  @Test
  public void testShiftRightWithNegativeNumber() {
    BigNumber list = constructList();
    assertEquals(6, list.length());
    assertEquals("324110", list.toString());
    list.shiftRight(-3);
    assertEquals("324110000", list.toString());
    list.shiftRight(0);
    assertEquals("324110000", list.toString());
  }

  @Test(timeout = 2000)
  public void testShiftRightBigNumber() {
    list = constructABigNumber(500);
    assertEquals(4501, list.length());
    list.shiftRight(1000);
    assertEquals(3501, list.length());
    list.shiftLeft(-10);
    assertEquals(3491, list.length());
    list.shiftLeft(-500);
    assertEquals(2991, list.length());
    list.shiftRight(5000);
    assertEquals(1, list.length());
  }

  @Test(timeout = 2000)
  public void testShiftRightOnZero() {
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    list.shiftRight(10000);
    assertEquals("0", list.toString());
    assertEquals(1, list.length());
  }

  @Test
  public void testShiftRight() {
    list.addDigit(1);
    assertEquals(1, list.length());
    assertEquals("1", list.toString());
    list.shiftRight(1);
    assertEquals("0", list.toString());
    assertEquals(1, list.length());

  }

  @Test
  public void testShiftRightWithPositiveNumber() {
    list = constructList();
    assertEquals("324110", list.toString());
    list.addDigit(0);
    assertEquals("324110", list.toString());
    list.shiftRight(2);
    assertEquals("3241", list.toString());
    list.shiftRight(2);
    assertEquals("32", list.toString());
    list.shiftRight(2);
    assertEquals("0", list.toString());
    list.shiftLeft(-2);
    assertEquals("0", list.toString());
    list.shiftLeft(-2);
    assertEquals("0", list.toString());
  }

  @Test(timeout = 2000)
  public void testBigNumberRightShift() {
    BigNumber list1 = constructABigNumber(500);
    assertEquals(4501, list1.length());
    list1 = helper2(list1);
    assertEquals(1, list1.length());
    assertEquals("0", list1.toString());
  }

  private BigNumber helper2(BigNumber list) {
    int len = list.length();
    for (int i = 1; i < len; i++) {
      list.shiftRight(i);
    }
    return list;
  }


  @Test(timeout = 2000)
  public void testBigNumberByShiftingLeft() {
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    list.addDigit(8);

    for (int j = 0; j < 800; j++) {
      for (int i = 1; i < 10; i++) {
        list.shiftLeft(1);
      }
    }
    assertEquals(7201, list.length());
  }




  @Test
  public void testNegativeShifts() {
    list = constructABigNumber(500);
    list.shiftRight(-200);
    assertEquals(4701, list.length());
  }

  @Test(timeout = 2000)
  public void testBigNumber() {
    BigNumber list1 = constructABigNumber(500);
    assertEquals(4501, list1.length());
  }


  @Test(timeout = 2000)
  public void testBigNumber2() {
    String num = String.valueOf(Integer.MAX_VALUE);
    BigNumber list = new BigNumberImpl(num);
    assertEquals(10, list.length());
    list.shiftLeft(10);
    assertEquals(20, list.length());
    assertEquals("21474836470000000000", list.toString());
    list.shiftLeft(100);
    assertEquals(120, list.length());
    list.shiftLeft(100);
    assertEquals(220, list.length());
    list.shiftRight(-10);
    assertEquals(230, list.length());
    list.shiftLeft(-100);
    assertEquals(130, list.length());
  }

  @Test
  public void testAddDigitWhenMultipleDigitsArePassed() {
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    String actual = "Invalid Input";
    String expected = "";
    try {
      list.addDigit(20);
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(expected, actual);
    list.addDigit(3);
    assertEquals("3", list.toString());
  }


  @Test
  public void testAddNegativeDigit() {
    BigNumber list = constructList();
    assertEquals("324110", list.toString());
    String actual = "Negative Numbers are not allowed.";
    String expected = "";
    try {
      list.addDigit(-6);
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
    assertEquals("324110", list.toString());
  }

  @Test
  public void testAddDigitAndShiftLeft() {
    BigNumber list = constructList();
    assertEquals("324110", list.toString());

  }


  @Test
  public void testAddDigitBasic() {
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    list.addDigit(2);
    assertEquals("2", list.toString());
    list.addDigit(3);
    assertEquals("5", list.toString());
  }

  @Test
  public void testAddWithCarry() {
    list.shiftLeft(1);
    list.addDigit(1);
    list.shiftLeft(1);
    list.addDigit(9);
    list.shiftLeft(1);
    list.addDigit(9);
    assertEquals("199", list.toString());
    list.addDigit(1);
    assertEquals("200", list.toString());

  }

  @Test
  public void testAddDigitWithCarry() {
    list = constructList9();
    assertEquals("999999", list.toString());
    list.addDigit(1);
    assertEquals("1000000", list.toString());
  }


  @Test(timeout = 5000)
  public void testAddDigitToBigNumber() {
    list = constructABigNumber(900);
    BigNumber list2 = list.copy();
    assertEquals(8101, list.length());
    list.shiftRight(1);
    assertEquals(8100, list.length());
    list.addDigit(9);
    assertEquals(8100, list.length());
    assertNotEquals(list, list2);
  }

  @Test(timeout = 3000)
  public void testAddDigitToBigNumberWithCarry() {
    list = constructABigNumber9(9000);
    assertEquals(9000, list.length());
    list.addDigit(1);
    assertEquals(9001, list.length());
  }


  @Test
  public void testGetDigitAtThrowsException() {
    String actual = "Invalid index";
    String expected = "";
    assertEquals("0", list.toString());
    try {
      assertEquals(0, list.getDigitAt(-1));
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
    assertEquals(0, list.getDigitAt(0));
    try {
      assertEquals(0, list.getDigitAt(1));
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
    list = constructList();
    assertEquals("324110", list.toString());
    assertEquals(0, list.getDigitAt(0));
    assertEquals(1, list.getDigitAt(1));
    assertEquals(1, list.getDigitAt(2));
    assertEquals(4, list.getDigitAt(3));
    assertEquals(2, list.getDigitAt(4));
    assertEquals(3, list.getDigitAt(5));

    //Index more than size of the list
    assertEquals(0, list.getDigitAt(list.length()));
    assertEquals(0, list.getDigitAt(10));

  }

  @Test
  public void testGetDigitAt() {
    list = constructList();
    assertEquals("324110", list.toString());
    assertEquals(6, list.length());
    assertEquals(0, list.getDigitAt(0));
    assertEquals(1, list.getDigitAt(1));
    assertEquals(1, list.getDigitAt(2));
    assertEquals(4, list.getDigitAt(3));
    assertEquals(2, list.getDigitAt(4));
    assertEquals(3, list.getDigitAt(5));
    //Index more than size of the list
    assertEquals(0, list.getDigitAt(list.length()));
    assertEquals(0, list.getDigitAt(10));
    assertEquals(0, list.getDigitAt(100000));
  }


  @Test(timeout = 5000)
  public void testGetDigitAtBigNumber() {
    list = constructABigNumber(800);
    assertEquals(7201, list.length());
    BigNumber list2 = list.copy();
    assertEquals(7201, list2.length());
    for (int i = 0; i < list2.length(); i++) {
      assertEquals(list.getDigitAt(i), list2.getDigitAt(i));
    }
  }


  @Test
  public void testCopy() {
    list = constructList();
    assertEquals("324110", list.toString());
    BigNumber list2 = list.copy();
    assertEquals("324110", list2.toString());
    assertFalse((list2 == list));
    assertEquals(list.toString(), list2.toString());
    assertEquals(list.length(), list2.length());
    for (int i = 0; i < list.length(); i++) {
      assertEquals(list.getDigitAt(i), list2.getDigitAt(i));
    }
    list.shiftRight(2);
    assertEquals("3241", list.toString());
    assertEquals("324110", list2.toString());
    assertNotEquals(list.toString(), list2.toString());

  }

  @Test(timeout = 5000)
  public void testBigNumberCopy() {
    list = constructABigNumber(900);
    BigNumber list2 = list.copy();
    assertEquals(list, list2);
    assertFalse((list2 == list));
    BigNumber list3 = list.add(list2);
    assertTrue(list3.compareTo(list) > 0);
  }

  @Test
  public void testCopyEmptyNumber() {
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    BigNumber list2 = list.copy();
    assertNotEquals((list == list2), true);
    assertEquals(1, list2.length());
    assertEquals("0", list2.toString());
    assertEquals(list2.toString(), list.toString());
  }

  @Test(timeout = 5000)
  public void testCopyEqualsCompareToOfBigNumber() {
    list = constructABigNumber(900);
    BigNumber list2 = list.copy();
    assertFalse((list2 == list));
    assertTrue(list.compareTo(list2) == 0);
    assertEquals(list, list2);
    assertEquals(list.hashCode(), list2.hashCode());
  }


  @Test
  public void testAddEmptyNumber() {
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    BigNumber list2 = list.copy();
    assertFalse((list == list2));
    assertNotEquals((list == list2), true);
    assertEquals(list2.toString(), list.toString());
    assertEquals(1, list2.length());
    assertEquals("0", list2.toString());
    BigNumber list3 = list.add(list2);
    assertEquals(1, list3.length());
    assertEquals("0", list3.toString());
    assertNotEquals((list == list3), true);
    assertNotEquals((list2 == list3), true);
  }


  @Test
  public void testAddTwoNumbers() {
    list = constructList();
    assertEquals("324110", list.toString());
    BigNumber list2 = constructList();
    assertEquals("324110", list2.toString());
    BigNumber list3 = list.add(list2);
    assertEquals(6, list3.length());
    assertEquals("648220", list3.toString());
    assertNotEquals(list, list3);
    assertNotEquals(list2, list3);
  }


  @Test
  public void testAddNumbersOfUnEqualLength() {
    list = constructList();
    assertEquals("324110", list.toString());
    BigNumber list2 = constructList();
    assertEquals("324110", list2.toString());
    list.shiftRight(2);
    assertEquals("3241", list.toString());
    BigNumber list3 = list.add(list2);
    assertEquals("327351", list3.toString());
    BigNumber list4 = list2.add(list);
    assertEquals("327351", list4.toString());
    assertNotEquals(list, list3);
    assertNotEquals(list2, list3);
    assertNotEquals(list, list4);
    assertNotEquals(list2, list4);

  }

  @Test
  public void testAddNumbersOfUnEqualLength1() {
    list = constructList();
    assertEquals("324110", list.toString());
    BigNumber list2 = constructList();
    assertEquals("324110", list2.toString());
    list.shiftRight(-2);
    assertEquals("32411000", list.toString());
    BigNumber list3 = list.add(list2);
    assertEquals("32735110", list3.toString());
    BigNumber list4 = list2.add(list);
    assertEquals("32735110", list4.toString());
    assertNotEquals(list, list3);
    assertNotEquals(list2, list3);
    assertNotEquals(list, list4);
    assertNotEquals(list2, list4);
  }


  @Test(timeout = 5000)
  public void testAddNumberWithABigNumber() {
    list = constructList();
    assertEquals("324110", list.toString());
    BigNumber second = constructABigNumber(900);
    assertEquals(8101, second.length());
    BigNumber list3 = list.add(second);
    assertEquals(8101, list3.length());
    assertNotEquals(list, list3);
    assertNotEquals(second, list3);
    assertTrue(list3.compareTo(list) > 0);
  }

  @Test(timeout = 5000)
  public void testAddTwoVeryBigNumbers() {
    list = constructABigNumber9(6000);
    assertEquals(6000, list.length());
    BigNumber second = constructABigNumber(500);
    assertEquals(4501, second.length());
    BigNumber list3 = list.add(second);
    assertEquals(6001, list3.length());
    assertNotEquals(list, list3);
    assertNotEquals(second, list3);
    assertTrue(list3.compareTo(list) > 0);
  }


  @Test(timeout = 5000)
  public void testAddTwoVeryBigNumber2() {
    list = constructABigNumber9(9000);
    assertEquals(9000, list.length());
    BigNumber second = constructABigNumber9(9000);
    assertEquals(9000, second.length());
    BigNumber list3 = list.add(second);
    assertEquals(9001, list3.length());
    assertNotEquals(list, list3);
    assertNotEquals(second, list3);
    assertTrue(list3.compareTo(list) > 0);
  }


  @Test
  public void testAddWithNull() {
    list = constructList();
    BigNumber list2 = null;
    String actual = "Invalid input";
    String expected = "";
    try {
      list.add(list2);
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
  }


  @Test
  public void testHashCode() {
    list = new BigNumberImpl("123456");
    BigNumber list2 = new BigNumberImpl("123456");
    assertEquals(list.hashCode(), list.hashCode());
    assertEquals(list2.toString().hashCode(), list2.hashCode());
    assertEquals(list.hashCode(), list2.hashCode());
    BigNumber list1 = new BigNumberImpl("1234567");
    assertNotEquals(list.hashCode(), list1.hashCode());
    String s = "123456";
    assertEquals(list.hashCode(), s.hashCode());
    list = new BigNumberImpl();
    list1 = new BigNumberImpl();
    assertEquals("0", list.toString());
    s = "0";
    assertEquals(list.hashCode(), s.hashCode());
    assertEquals(list1.hashCode(), s.hashCode());
    assertEquals(list1.toString().hashCode(), list.hashCode());

  }

  @Test(timeout = 5000)
  public void testHashCodeOfBigNumbers() {
    list = constructABigNumber(900);
    BigNumber copy = list.copy();
    BigNumber list2 = constructABigNumber9(9000);
    assertEquals(copy.hashCode(), list.hashCode());
    assertNotEquals(list.hashCode(), list2.hashCode());
  }

  @Test
  public void testEqualsThreeProperties() {
    equalsHelper("12345");
    list = new BigNumberImpl();
    //Reflexivity
    assertEquals(list, list);
    BigNumber list2 = new BigNumberImpl();

    //symmetry
    assertEquals(list, list2);
    assertEquals(list2, list);

    BigNumber list3 = new BigNumberImpl();
    assertEquals(list2, list3);
    assertEquals(list3, list);
    list = new BigNumberImpl();
    assertNotEquals(list, "");
    assertNotEquals(list, "0");
    assertNotEquals(list, null);

  }

  private void equalsHelper(String s) {

    list = new BigNumberImpl(s);
    assertEquals(list, list);
    BigNumber list2 = new BigNumberImpl(s);
    assertEquals(list, list2);
    assertEquals(list2, list);
    BigNumber list3 = new BigNumberImpl(s);
    assertEquals(list2, list3);
    assertEquals(list3, list);
  }


  @Test
  public void testEquals() {
    list = new BigNumberImpl("123456");
    assertEquals("123456", list.toString());
    assertEquals(list, list);
    BigNumber list2 = new BigNumberImpl("123456");
    assertEquals("123456", list2.toString());
    assertEquals(list, list2);
  }

  @Test
  public void testEqualsTwoUnEqualNodes() {
    list = new BigNumberImpl("123456");
    BigNumber list2 = new BigNumberImpl("1234567");
    assertEquals("1234567", list2.toString());
    assertNotEquals(list, list2);
    assertNotEquals(list.hashCode(), list2.hashCode());
  }

  @Test
  public void testEqualsTwoEmptyNodes() {
    list = new BigNumberImpl();
    BigNumber list2 = new BigNumberImpl();
    assertEquals("0", list2.toString());
    assertTrue(list.equals(list2));
  }

  @Test
  public void testEqualsTwoBigNumbers() {
    list = constructABigNumber(500);
    BigNumber list2 = constructABigNumber(500);
    assertEquals(list, list2);
  }

  @Test(timeout = 5000)
  public void testEqualsOnBigNumbers() {
    list = constructABigNumber(600);
    assertEquals(5401, list.length());
    assertEquals(list, list);
    BigNumber list2 = constructABigNumber(600);
    assertEquals(list, list2);
    assertEquals(list2, list);
    BigNumber list3 = list.copy();
    assertEquals(list, list3);
    assertEquals(list2, list3);
  }


  @Test
  public void testCompare() {
    list = new BigNumberImpl("45");
    BigNumber list2 = new BigNumberImpl("9");
    assertTrue(list2.compareTo(list) < 0);
  }

  @Test
  public void testCompareWithNull() {
    list = new BigNumberImpl("45");
    String actual = "Given Object is null";
    String expected = "";
    try {
      assertTrue(list.compareTo(null) < 0);
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }

    assertEquals(actual, expected);
    list = null;
    BigNumber list2 = new BigNumberImpl();
    try {
      assertTrue(list2.compareTo(list) < 0);
    } catch (IllegalArgumentException e) {
      expected = e.getMessage();
    }
    assertEquals(actual, expected);
  }

  @Test
  public void testCompareEqualNumbers() {
    list = new BigNumberImpl("123456");
    BigNumber list2 = new BigNumberImpl("123456");
    assertTrue(list2.compareTo(list) == 0);
  }

  @Test
  public void testCompare2() {
    list = new BigNumberImpl("123456");
    BigNumber list2 = new BigNumberImpl("23456");
    assertTrue(list2.compareTo(list) < 0);
  }

  @Test
  public void testCompare3() {
    list = new BigNumberImpl("123456");
    BigNumber list2 = new BigNumberImpl("8923456");
    assertTrue(list2.compareTo(list) > 0);
    assertNotEquals(list.hashCode(), list2.hashCode());
  }

  @Test(timeout = 2000)
  public void testCompareOneBigNumberWithOther() {

    BigNumber list1 = constructABigNumber(500);
    BigNumber list2 = new BigNumberImpl("8923456");
    assertTrue(list2.compareTo(list1) < 0);
  }

  @Test
  public void testCompareTwoBigNumbers() {

    BigNumber list1 = constructABigNumber(900);
    BigNumber list2 = constructABigNumber(900);
    assertEquals(0, list2.compareTo(list1));
  }


  @Test
  public void testEquals123() {
    list = new BigNumberImpl("9999");
    BigNumber list1 = new BigNumberImpl("99991");
    assertFalse(list.equals(list1));
    assertTrue(list.compareTo(list1) < 0);
    assertTrue(list1.compareTo(list) > 0);
    list1 = new BigNumberImpl("9991");
    assertTrue(list.compareTo(list1) > 0);
    assertFalse(list.equals(list1));
    String s = "9999";
    assertEquals(s.hashCode(), list.hashCode());
    list1 = list.copy();
    assertEquals(list1, list);
    assertEquals(0, list.compareTo(list1));
  }


  //Helper methods for testing

  private BigNumber constructABigNumber(int n) {
    BigNumber list = new BigNumberImpl();
    assertEquals(1, list.length());
    assertEquals("0", list.toString());

    String s = "";
    for (int j = 0; j < n; j++) {
      for (int i = 1; i < 10; i++) {
        list.addDigit(i % 10);
        s += String.valueOf(i % 10);
        assertEquals(s, list.toString());
        list.shiftLeft(1);

      }
    }
    return list;
  }

  private BigNumber constructABigNumber9(int n) {
    BigNumber list = new BigNumberImpl();
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    String s = "";
    for (int j = 0; j < n; j++) {
      list.addDigit(9);
      list.shiftLeft(1);
    }
    list.shiftRight(1);
    return list;
  }


  private BigNumber constructList9() {
    BigNumber list = new BigNumberImpl();
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    list.shiftLeft(1);
    assertEquals("0", list.toString());
    list.addDigit(9);
    assertEquals(1, list.length());
    assertEquals("9", list.toString());
    list.shiftLeft(1);
    assertEquals(2, list.length());
    assertEquals("90", list.toString());

    list.addDigit(9);
    assertEquals("99", list.toString());
    list.shiftLeft(1);
    assertEquals("990", list.toString());
    list.addDigit(9);
    assertEquals("999", list.toString());
    list.shiftLeft(1);
    assertEquals("9990", list.toString());
    list.addDigit(9);
    assertEquals("9999", list.toString());
    list.shiftLeft(1);
    assertEquals("99990", list.toString());
    list.addDigit(9);
    assertEquals("99999", list.toString());
    list.shiftLeft(1);
    assertEquals("999990", list.toString());
    list.addDigit(9);
    assertEquals("999999", list.toString());
    return list;
  }

  private BigNumber constructList() {
    BigNumber list = new BigNumberImpl();
    assertEquals(1, list.length());
    assertEquals("0", list.toString());
    list.shiftLeft(1);
    assertEquals("0", list.toString());
    list.addDigit(3);
    assertEquals(1, list.length());
    assertEquals("3", list.toString());
    list.shiftLeft(1);
    assertEquals(2, list.length());
    assertEquals("30", list.toString());

    list.addDigit(2);
    assertEquals("32", list.toString());
    list.shiftLeft(1);
    assertEquals("320", list.toString());
    list.addDigit(4);
    assertEquals("324", list.toString());
    list.shiftLeft(1);
    assertEquals("3240", list.toString());
    list.addDigit(1);
    assertEquals("3241", list.toString());
    list.shiftLeft(1);
    assertEquals("32410", list.toString());
    list.addDigit(1);
    assertEquals("32411", list.toString());
    list.shiftLeft(1);
    assertEquals("324110", list.toString());
    list.addDigit(0);
    assertEquals("324110", list.toString());
    return list;
  }


}
