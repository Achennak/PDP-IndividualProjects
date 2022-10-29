package bignumber;

/**
 * This class represents an empty node in the list adt implementation of BigNumber.
 */
class BigNumberListADTEmptyNode implements BigNumberListADTNode {


  @Override
  public int length() {
    return 0;
  }

  @Override
  public int helperCount(int acc) {
    return acc;
  }

  @Override
  public BigNumberListADTNode shiftLeft(int numShifts) {
    return this;
  }


  @Override
  public BigNumberListADTNode shiftRight(int numShifts) {
    return this;
  }

  @Override
  public BigNumberListADTNode shiftRightHelper(BigNumberListADTNode node, int num) {
    return new BigNumberListADTElementNode(0, node);
  }

  @Override
  public BigNumberListADTNode addDigit(int digit) {
    return addDigitHelper(new BigNumberListADTElementNode(), digit);
  }

  @Override
  public BigNumberListADTNode addDigitHelper(BigNumberListADTNode head, int digit) {
    return new BigNumberListADTElementNode(digit, this);
  }

  @Override
  public int getDigitAt(int index) {
    return 0;
  }


  @Override
  public BigNumberListADTNode add(BigNumberListADTNode other) {
    return other;
  }

  @Override
  public String toString() {
    return "0";
  }


  @Override
  public String toStringHelper(StringBuilder s) {
    return s.toString();
  }


  @Override
  public BigNumberListADTNode addRestHelper(int carry) {
    if (carry == 0) {
      return this;
    }
    return new BigNumberListADTElementNode(carry, this);
  }

  @Override
  public BigNumberListADTNode addHelper(BigNumberListADTNode second, int carry) {
    if (second instanceof BigNumberListADTEmptyNode) {
      return this;
    }
    return second.addRestHelper(carry);
  }


}
