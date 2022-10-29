/**
 * This interface represents all the operations to be supported by a list of
 * strings.
 */
public interface ListOfString {
  /**
   * Return the number of strings in this list.
   *
   * @return the size of this list.
   */
  int size();

  /**
   * Add the given string to the front of this list and return the resulting
   * list.
   *
   * @param s the string to be added.
   * @return the resulting list.
   */
  ListOfString addFront(String s);

  /**
   * Add the given string to the back of this list and return the resulting list.
   *
   * @param s the string to be added.
   * @return the resulting list.
   */
  ListOfString addBack(String s);

  /**
   * A method that adds the given string at the given index in this list
   * . If index is 0, it means this string should be added to the front of this
   * list.
   *
   * @param index the position to be occupied by this object, starting at 0.
   * @param s     the string to be added.
   * @return the resulting list.
   * @throws IllegalArgumentException if an invalid index is passed.
   */
  ListOfString add(int index, String s) throws IllegalArgumentException;

  /**
   * Get the string at the specified index, with 0 meaning the first object in
   * this list.
   *
   * @param index the specified index.
   * @return the string at the specified index.
   * @throws IllegalArgumentException if an invalid index is passed.
   */
  String get(int index) throws IllegalArgumentException;

  /**
   * Return an independent, but reversed version of this list.
   *
   * @return the reverse of this list.
   */
  ListOfString reverse();

  /**
   * Return a list of strings that interleaves the elements of this list and the other
   * beginning with this list.
   *
   * @param other second list to be interleaved.
   * @return an interleaved list of strings.
   */
  ListOfString interleave(ListOfString other);


  /**
   * Helper method for toString() which returns a string
   * that can be used to print the contents of the list.
   *
   * @param s accumulator which stores string value.
   * @return returns a sequence of strings in the list, separated by a comma.
   */
  String toStringHelper(StringBuilder s);

  /**
   * Helper method for reverse(),which returns the
   * reverse list of the given list of the strings.
   *
   * @param list accumulator list  which stores string values.
   * @return returns a list of strings which is reverse of the given list.
   */
  ListOfString reverseHelperMethod(ListOfString list);

  /**
   * Helper method for interleave(),which returns the result list.
   * the interleave operation interleaves the two lists, starting with the first list.
   * If one list ends then the remaining part of the other list is simply appended to the result.
   *
   * @param list1 first list to be interleaved.
   * @param list2 second list to be interleaved.
   * @return returns the result interleaved list.
   */
  ListOfString interleaveHelper(ListOfString list1, ListOfString list2);

  /**
   * Helper method for interleave(),for characterizing the remaining part of the resulting list.
   *
   * @param list1 returns the result after appending the remaining characters.
   * @return return the resulted interleaved list.
   */
  ListOfString append(ListOfString list1);

}
