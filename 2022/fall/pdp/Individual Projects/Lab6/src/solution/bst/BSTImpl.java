package solution.bst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a binary search tree. It implements the BSTADT
 * interface.
 */
public class BSTImpl<T extends Comparable<T>>
        implements BST<T> {

  class Pair<S, T> {
    public T value;
    public S key;

    Pair(S s, T node) {
      key = s;
      value = node;
    }

    public S getKey() {
      return this.key;
    }

    public T getValue() {
      return this.value;
    }
  }

  private BSTNode<T> root;

  /**
   * Default constructor with no arguments.
   */
  public BSTImpl() {
    root = new BSTEmptyNode<T>(); //no tree
  }

  @Override
  public int size() {

    Deque<Pair<String, BSTNode<T>>> tS = new LinkedList<>();
    Deque<Integer> rS = new LinkedList<>();
    Pair<String, BSTNode<T>> pair = new Pair<>("recur", root);
    tS.push(pair);
    //CHANGE 1: start with the root
    while (!tS.isEmpty()) {
      pair = tS.pop();
      String command = pair.getKey();
      BSTNode<T> n = pair.getValue();
      if (command.equals("recur")) {
        if (n instanceof BSTEmptyNode) {
          //simply process this node, no recursive call
          pair = new Pair<>("process", n);
          tS.push(pair);
        }
        if (n instanceof BSTElementNode) {
          //first make the recursive call to left, then to right,
          // and finally process this node (add 1 to the results)
          //added in reverse order so that they come out of stack in correct order
          //CHANGE 2
          pair = new Pair<>("process", n);
          tS.push(pair);
          pair = new Pair<>("recur", ((BSTElementNode<T>) n).getRight());
          tS.push(pair);
          pair = new Pair<>("recur", ((BSTElementNode<T>) n).getLeft());
          tS.push(pair);
        }
      }
      if (command.equals("process")) {
        if (n instanceof BSTEmptyNode) {
          //an empty node simply returns 0, as per above implementation
          rS.push(0);
        }
        if (n instanceof BSTElementNode) {
          //add 1 to the previous two results, as per above implementation
          //CHANGE 3
          int num1 = rS.pop();
          int num2 = rS.pop();
          rS.push(1 + num1 + num2);
        }
      }
    }
    return rS.pop();
  }

  @Override
  public void insert(T data) {
    root = root.insert(data);
  }

  @Override
  public boolean present(T data) {
    return root.contains(data);
  }

  @Override
  public T minimum() {
    return root.minimum();
  }

  @Override
  public T maximum() {
    return root.maximum();
  }

  public String toString() {
    return "[" + root.toString() + "]";
  }

  @Override
  public List<T> preorder() {
    Deque<Pair<String, BSTNode>> ts = new LinkedList<>();
    List<T> result = new ArrayList<>();

    ts.push(new Pair<>("recur", root));
    while (!ts.isEmpty()) {
      Pair<String, BSTNode> p = ts.pop();
      if (p.getKey().equals("recur")) {
        if (p.getValue() instanceof BSTEmptyNode) {
          ts.push(new Pair<>("process", p.getValue()));
        }
        if (p.getValue() instanceof BSTElementNode) {
          ts.push(new Pair<>("process", p.getValue()));
          ts.push(new Pair<>("recur", ((BSTElementNode) p.getValue()).getLeft()));
          ts.push(new Pair<>("recur", ((BSTElementNode) p.getValue()).getRight()));
        }
      }
      if (p.getKey().equals("process")) {
        if (p.getValue() instanceof BSTElementNode) {
          result.add(0, ((BSTElementNode<T>) p.getValue()).getData());
        }
      }
    }
    return result;
  }

  @Override
  public List<T> inorder() {
    Deque<Pair<String, BSTNode>> ts = new LinkedList<>();
    List<T> result = new ArrayList<>();

    ts.push(new Pair<>("recur", root));
    while (!ts.isEmpty()) {
      Pair<String, BSTNode> p = ts.pop();
      if (p.getKey().equals("recur")) {
        if (p.getValue() instanceof BSTEmptyNode) {
          ts.push(new Pair<>("process", p.getValue()));
        }
        if (p.getValue() instanceof BSTElementNode) {
          ts.push(new Pair<>("recur", ((BSTElementNode) p.getValue()).getLeft()));
          ts.push(new Pair<>("process", p.getValue()));
          ts.push(new Pair<>("recur", ((BSTElementNode) p.getValue()).getRight()));

        }
      }
      if (p.getKey().equals("process")) {
        if (p.getValue() instanceof BSTElementNode) {
          result.add(0, ((BSTElementNode<T>) p.getValue()).getData());
        }
      }
    }
    return result;
  }


  @Override
  public List<T> postorder() {
    Deque<Pair<String, BSTNode>> ts = new LinkedList<>();
    List<T> result = new ArrayList<>();

    ts.push(new Pair<>("recur", root));
    while (!ts.isEmpty()) {
      Pair<String, BSTNode> p = ts.pop();
      if (p.getKey().equals("recur")) {
        if (p.getValue() instanceof BSTEmptyNode) {
          ts.push(new Pair<>("process", p.getValue()));
        }
        if (p.getValue() instanceof BSTElementNode) {
          ts.push(new Pair<>("recur", ((BSTElementNode) p.getValue()).getLeft()));
          ts.push(new Pair<>("recur", ((BSTElementNode) p.getValue()).getRight()));
          ts.push(new Pair<>("process", p.getValue()));
        }
      }
      if (p.getKey().equals("process")) {
        if (p.getValue() instanceof BSTElementNode) {
          result.add(0, ((BSTElementNode<T>) p.getValue()).getData());
        }
      }
    }
    return result;
  }
}
