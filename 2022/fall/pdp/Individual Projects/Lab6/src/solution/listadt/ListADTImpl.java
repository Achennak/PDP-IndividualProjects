package solution.listadt;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements
 * the ListADT interface.
 */
public class ListADTImpl<T> implements ListADT<T> {

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

  private GenericListADTNode<T> head;

  /**
   * Default Constructor with no arguments.
   */
  public ListADTImpl() {
    head = new GenericEmptyNode();
  }

  //a private constructor that is used internally (see map)
  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  @Override
  public int getSize() {

    //--------Using stack Approach------------------
    Deque<Pair<String, GenericListADTNode<T>>> ts = new LinkedList<>();
    Deque<Integer> rS = new LinkedList<>();
    Pair<String, GenericListADTNode<T>> pair = new Pair<>("recur", head);
    ts.push(pair); //start with the head
    while (!ts.isEmpty()) {
      pair = ts.pop();
      String command = pair.getKey();
      GenericListADTNode<T> n = pair.getValue();
      if (command.equals("recur")) {
        if (n instanceof GenericEmptyNode) {
          //simply process this node, no recursive call
          pair = new Pair<>("process", n);
          ts.push(pair);
        }
        if (n instanceof GenericElementNode) {
          //first make the recursive call, then process this node (add 1 to result)
          //added in reverse order so that they come out of stack in correct order
          pair = new Pair<>("process", n);
          ts.push(pair);
          pair = new Pair<>("recur", ((GenericElementNode<T>) n).getRest());
          ts.push(pair);
        }
      }
      if (command.equals("process")) {
        if (n instanceof GenericEmptyNode) {
          //an empty node simply returns 0, as per above implementation
          rS.push(0);
        }
        if (n instanceof GenericElementNode) {
          //add 1 to the "last result", as per above implementation
          int num = rS.pop();
          rS.push(1 + num);
        }
      }
    }
    return rS.pop();
  }

  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    } else {
      throw new IllegalArgumentException("Invalid index");
    }

  }

  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {


    //--------Using stack Approach------------------
    Deque<Pair<String, GenericListADTNode<T>>> ts = new LinkedList<>();
    Deque<GenericListADTNode<R>> rS = new LinkedList<>();
    Pair<String, GenericListADTNode<T>> pair = new Pair<>("recur", head);
    ts.push(pair); //start with the head
    while (!ts.isEmpty()) {
      pair = ts.pop();
      String command = pair.getKey();
      GenericListADTNode<T> n = pair.getValue();
      if (command.equals("recur")) {
        if (n instanceof GenericEmptyNode) {
          //simply process this node, no recursive call
          pair = new Pair<>("process", n);
          ts.push(pair);
        }
        if (n instanceof GenericElementNode) {
          pair = new Pair<>("process", n);
          ts.push(pair);
          pair = new Pair<>("recur", ((GenericElementNode<T>) n).getRest());
          ts.push(pair);
        }
      }
      if (command.equals("process")) {
        if (n instanceof GenericEmptyNode) {
          rS.push(new GenericEmptyNode<R>());
        }
        if (n instanceof GenericElementNode) {
          rS.push(new GenericElementNode(
                  converter.apply(((GenericElementNode<T>) n).getData()),
                  rS.pop()));
        }
      }
    }
    return new ListADTImpl<>(rS.pop());
  }

  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}
