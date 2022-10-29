
/**
 * This represents a non-empty node of the list. It contains a piece of data
 * along with the rest of the list
 */
public class NonEmptyList implements ListOfString {
  private final String str;
  private ListOfString rest;

  public NonEmptyList(String s, ListOfString rest) {
    this.str = s;
    this.rest = rest;
  }

  @Override
  public int size() {
    return 1 + this.rest.size();
  }

  @Override
  public ListOfString addFront(String s) {

    return new NonEmptyList(s, this);
  }

  @Override
  public ListOfString addBack(String s) {
    this.rest = this.rest.addBack(s);
    return this;
  }

  @Override
  public ListOfString add(int index, String s) {
    if (index == 0) {
      return addFront(s);
    } else {
      return new NonEmptyList(this.str, this.rest.add(index - 1, s));
    }
  }


  @Override
  public String get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.str;
    }
    return this.rest.get(index - 1);
  }

  @Override
  public ListOfString reverse() {
    return reverseHelperMethod(new EmptyList());
  }

  @Override
  public ListOfString reverseHelperMethod(ListOfString list) {
    System.out.println(list.toString());
    return this.rest.reverseHelperMethod(list.addFront(this.str));
  }


  @Override
  public ListOfString interleave(ListOfString other) {
    return interleaveHelper(new EmptyList(), other);
  }

  @Override
  public ListOfString interleaveHelper(ListOfString acc, ListOfString other) {
    return other.interleaveHelper(acc.addBack(this.str), this.rest);
  }

  /**
   * functionality to return contents of the list seperated
   * by comma's as a string.
   *
   * @return returns sequence of strings in the list, separated by a comma.
   */
  @Override
  public String toString() {
    return toStringHelper(new StringBuilder());
  }

  @Override
  public String toStringHelper(StringBuilder s) {
    return this.rest.toStringHelper(s.append(this.str + ",")).toString();
  }

  @Override
  public ListOfString append(ListOfString acc) {
    return this.rest.append(acc.addBack(this.str));
  }


}
