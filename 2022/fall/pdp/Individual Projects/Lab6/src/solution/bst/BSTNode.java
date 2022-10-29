package solution.bst;


/**
 * This represents the operations on all nodes of a binary search tree.
 */
public interface BSTNode<T extends Comparable<T>> {

  /**
   * Inserts new data into the tree rooted at this node, and return the
   * resulting tree.
   *
   * @param data new data to be inserted.
   * @return returns the resulting tree.
   */
  BSTNode<T> insert(T data);

  /**
   * Determine and return the minimum element in the tree rooted at this node.
   *
   * @return return the minimum element in the tree.
   */
  T minimum();

  /**
   * Determine and return the maximum element in the tree rooted at this node.
   *
   * @return returns the maximum element in the tree.
   */
  T maximum();

  /**
   * Search to see if the specific data is present in the tree rooted at this
   * node.
   *
   * @param data data to be searched.
   * @return true if data is present in the tree, false otherwise.
   */
  boolean contains(T data);

  /**
   * Returns a string containing all the data in the tree rooted at this node.
   * The string is formatted as d1 d2 ... dn.
   *
   * @return returns the BST tree as a string.
   */
  String toString();
}
