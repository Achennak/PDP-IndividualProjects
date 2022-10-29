package bignumber;

/**
 * This class represents an element(non-empty)  node in the list adt implementation of BigNumber.
 */
class BigNumberListADTElementNode implements BigNumberListADTNode {
  private int data;
  private BigNumberListADTNode rest;


  /**
   * Default constructor of BigNumberNode class with no parameters
   * and initializes this node to 0 and rest to empty node.
   */
  public BigNumberListADTElementNode() {
    this.data = 0;
    this.rest = new BigNumberListADTEmptyNode();
  }

  /**
   * Creates a new bigNumberNode whose node value is @data and rest is @rest.
   *
   * @param data value in the current node.
   * @param rest rest of the node.
   */

  public BigNumberListADTElementNode(int data, BigNumberListADTNode rest) {
    this.data = data;
    this.rest = rest;
  }


  public BigNumberListADTElementNode(String number) {

    BigNumberListADTElementNode node =
            new BigNumberListADTElementNode(0, constructFromString(number));
    node = (BigNumberListADTElementNode) node.rest;
    this.data = node.data;
    this.rest = node.rest;
  }


  //helper method for constructing bigNumber from String.
  private BigNumberListADTNode constructFromString(String number) {
    int n = number.length();
    BigNumberListADTNode node = new BigNumberListADTElementNode();
    for (int i = 0; i < n; i++) {
      int digit = Character.getNumericValue(number.charAt(i));
      node = node.addDigit(digit);
      if (i != n - 1) {
        node = node.shiftLeft(1);
      }
    }
    return node;
  }


  @Override
  public int length() {
    return helperCount(0);
  }

  public int helperCount(int acc) {
    return this.rest.helperCount(1 + acc);
  }

  @Override
  public BigNumberListADTNode shiftLeft(int numShifts) {
    if (numShifts < 0) {
      return this.shiftRight(-numShifts);
    }
    if (numShifts == 0) {
      return this;
    }

    if (this.rest instanceof BigNumberListADTEmptyNode) {
      if (this.data == 0) {
        return this;
      }
    }

    BigNumberListADTElementNode node = this;


    for (int i = 0; i < numShifts; i++) {
      node = new BigNumberListADTElementNode(0, node);
    }

    return node;
  }


  @Override
  public BigNumberListADTNode shiftRight(int numShifts) {
    if (numShifts < 0) {
      return this.shiftLeft(-numShifts);
    }
    if (numShifts == 0) {
      return this;
    }

    return shiftRightHelper(this, numShifts);

  }

  @Override
  public BigNumberListADTNode shiftRightHelper(BigNumberListADTNode node, int shifts) {
    if (shifts == 0) {
      return node;
    }
    return this.rest.shiftRightHelper(this.rest, shifts - 1);

  }

  @Override
  public BigNumberListADTNode addDigit(int digit) {
    return addDigitHelper(new BigNumberListADTEmptyNode(), digit);
  }


  @Override
  public BigNumberListADTNode addDigitHelper(BigNumberListADTNode head, int digit) {
    int sum = this.data + digit;
    int carry = sum / 10;
    sum = sum % 10;
    this.data = sum;
    head = new BigNumberListADTElementNode(sum, this.rest);
    if (carry > 0) {
      return new BigNumberListADTElementNode(this.data, this.rest.addDigit(carry));
    }
    return head;
  }


  @Override
  public int getDigitAt(int index) {
    if (index == 0) {
      return this.data;
    }
    return this.rest.getDigitAt(index - 1);
  }


  @Override
  public BigNumberListADTNode add(BigNumberListADTNode other) {
    if (other instanceof BigNumberListADTEmptyNode) {
      return this;
    } else {
      BigNumberListADTElementNode second = new BigNumberListADTElementNode(other.toString());
      return addTwoNumbersHelper(second);
    }
  }

  private BigNumberListADTNode addTwoNumbersHelper(BigNumberListADTNode second) {
    BigNumberListADTElementNode first = new BigNumberListADTElementNode(this.toString());
    return first.addHelper(second, 0);
  }

  @Override
  public BigNumberListADTNode addHelper(BigNumberListADTNode second, int carry) {
    if (second instanceof BigNumberListADTEmptyNode) {
      return this.addRestHelper(carry);
    }
    int sum = this.data + second.getDigitAt(0) + carry;
    int c = sum / 10;
    sum = sum % 10;
    second = second.shiftRight(1);
    return new BigNumberListADTElementNode(sum, second.addHelper(this.rest, c));
  }

  @Override
  public BigNumberListADTNode addRestHelper(int digit) {
    int sum = this.data + digit;
    int carry = sum / 10;
    sum = sum % 10;
    return new BigNumberListADTElementNode(sum, this.rest.addRestHelper(carry));
  }


  @Override
  public String toString() {
    return toStringHelper(new StringBuilder());
  }

  @Override
  public String toStringHelper(StringBuilder s) {
    StringBuilder s1 = new StringBuilder(String.valueOf(this.data));
    return this.rest.toStringHelper(s1.append(s)).toString();
  }

}
