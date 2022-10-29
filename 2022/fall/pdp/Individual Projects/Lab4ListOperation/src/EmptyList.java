/**
 * This represents an empty node in the list.
 */
public class EmptyList implements ListOfString {

  @Override
  public int size() {
    return 0;
  }

  @Override
  public ListOfString addFront(String s) {
    return new NonEmptyList(s, this);
  }

  @Override
  public ListOfString addBack(String s) {
    return addFront(s);
  }

  @Override
  public ListOfString add(int index, String s) throws
          IllegalArgumentException {
    if (index == 0) {
      return addFront(s);
    }
    throw new IllegalArgumentException("Invalid index to add an element");
  }

  @Override
  public String get(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("Wrong index");
  }

  @Override
  public ListOfString reverse() {
    return reverseHelperMethod(new EmptyList());
  }

  public ListOfString reverseHelperMethod(ListOfString list1) {
    return list1;
  }

  @Override
  public ListOfString interleave(ListOfString other) {
    if (other instanceof NonEmptyList) {
      return other;
    } else {
      return new EmptyList();
    }
  }


  @Override
  public ListOfString interleaveHelper(ListOfString acc, ListOfString other) {
    if (other instanceof NonEmptyList) {
      NonEmptyList list = (NonEmptyList) other;
      return list.append(acc);
    } else {
      return acc;
    }
  }

  @Override
  public ListOfString append(ListOfString acc) {
    return acc;
  }

  /**
   * A functionality to return contents of the list as a string.
   *
   * @return returns an empty since the current list is empty.
   */
  @Override
  public String toString() {
    return "";
  }

  @Override
  public String toStringHelper(StringBuilder s) {
    return s.replace(s.length() - 1, s.length(), "").toString();
  }


}