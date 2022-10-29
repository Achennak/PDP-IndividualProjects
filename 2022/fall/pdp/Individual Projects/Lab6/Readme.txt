From Recursion Towards Iteration

Objectives
The objectives of this lab are:

Learn strategies to convert recursive solutions to iterative solutions
You are allowed to work with another person on this lab. However both of you must submit individually to the server for it to count towards your grade.

0 Code Preparation
Before starting this lab, create a new package called solution in your source folder. Now copy all the code from the listadt and bst packages into this folder (in IntelliJ if you select these folders and copy, and then select the solution package and paste, it will copy the code and refactor the packages correctly in the copied code).

If you set up your code correctly, your src/ folder should contain the following folders: listadt , bst , solution . The solution folder should contain the following folders: listadt and bst . If your setup is correct, then the tests given to you should run. One of these tests will fail, as explained below.

1 Introduction
Recursive solutions, while elegant when used appropriately, suffer from some limitations. First, there is a danger of creating a stack overflow in many programming languages (like Java). Secondly, recursive solutions may have "hidden" inefficiencies in space (using more memory on the stack than needed) and time (repeatedly solving the same problem). We have written several recursive methods (that exploit the recursive definition of data) that may suffer from these limitations. As a simple example, try to run the testLongList test provided to you in the starter code. In this lab you will rewrite several of these implementations without recursion.

We will start with simpler methods and move to trickier methods, progressively adding to our strategy of converting a recursive solution to an iterative solution.

All changes suggested below should be made to the code in the solution package, so that you can compare and contrast the given and your implementation at any time. You can also switch which code is used by the tests by changing the line that imports various classes.

2 Lists
The code given in the listadt package contains the generic list implementation from class. Please review this design before proceeding.

To do: We begin by writing some "undesirable" methods to make the conversion easier. Write the following methods in the GenericElementNode<T> class: T getData() that will return the data in it, and GenericListADTNode<T> getRest() that will return rest.

2.1 Computing the size of a list: accumulator
The given code contains two versions of this method in the node classes: using "traditional recursion" and an "accumulator" (currently uncommented). The accumulator version ends with a single recursive call (tail recursion). One of the benefits of tail recursion is that it can be replaced by a loop. Therefore we will start with re-implementing the accumulator version of this method, without recursion. Here is the code:

//GenericElementNode:



  @Override
  public int countAcc(int acc) {
    return this.rest.countAcc(acc+1);
  }

//GenericEmptyNode:

  @Override
  public int countAcc(int acc) {
    return acc;
  }
2.1.1 General Recipe
The first method can be broken down into the following steps (mimicking how the code works):

Update the accumulator by adding 1 to it.
Move to this.rest .
We remove tail recursion as follows:

Make a list of all variables used in the method (including parameters).
In the new method, declare all of them as local variables.
Initialize them appropriately.
Enclose all the code right before the recursive call in a while loop (be sure to include the code that updates the parameters to the recursive call, as well as how the "calling object" changes between this and the next call).
Pay attention to how the recursion ends. Put that as the condition for the while loop to end.
Pay attention to which local variable(s) produce the final result, and return that result.
2.1.2 Implementation
This produces the final code, directly in the ListADTImpl class:

    //step 1: variables used: acc, this

    //steps 2, 3
    int acc = 0; //the initial value of the accumulator
    GenericListADTNode<T> current = head; //the initial value of this, the node on which count is called first

    //step 4
    while (current instanceof GenericElementNode) { //step 5: this is when recursion goes on
      acc +=1;
      current = ((GenericElementNode<T>)current).getRest();
    }
    //step 6
    return acc;
To do: Replace the implementation of the getSize method in ListADTImpl class with the above code. Verify the correctness of this implementation by running the given tests. The test that was failing earlier should not only pass, but run rather quickly!

2.1.3 Notes
Although the above code works, it has several "undesirable" features: the use of instanceof and the use of getRest . Both of them break the transparency of the nodes, and seem to not exploit dynamic dispatch. Towards the end of this lab we will discuss a strategy to remove these drawbacks.

2.2 Computing the size of a list: traditional recursion
The code below shows the implementation of this operation without using tail recursion:

  //GenericElementNode
  @Override
  public int count() { return 1+this.rest.count();}

  //GenericEmptyNode
  @Override
  public int count() { return 0;}
Methods like this have one or more recursive calls, with other code before, between, and after the calls. The code that must be executed after the recursive call requires that relevant data be saved, to be retrieved later. This saving and retrieving is done using the program stack.

A general-purpose recipe for making recursive methods non-recursive is to manage a stack ourselves in the code. Although this stack may get just as long as the program stack, it is much less of a problem because this stack is maintained on the heap (a much larger block of memory than the program stack can afford). Managing the stack ourselves also gives us the freedom to only save the data that we actually need post-recursion.

The above method can be broken down in the following way:

Call the function recursively for rest.
Use the result returned by the recursive call, along with information about this node (1) to compute the result for this node.
Thus there are two types of tasks: "recur" to the rest, and "process" this node. We maintain a stack to remember which tasks must be performed. Because this method returns results, we maintain another stack to store the results. We can use the Deque interface, and its LinkedList implementation in Java as a stack.

2.2.1 The "task" stack
Each entry on this stack must remember two things: the type of task ("recur", "process", etc.) and the data required to complete each task (e.g. a node).

To do: Create a new generic class called Pair<S,T> as an inner helper class in ListADTImpl . It stores two fields: one of type S and another of type T . Write a constructor that takes default values for these two fields.

For our count method, each item on the task stack will be a Pair<String,GenericListADTNode<T>> object. This will allow us to represent "recur on node X" and "process node Y" using the stack.

Every time we have to recur, we add an entry ("recur",n) where the n is the node we recur on. Every time we have to process, we add an entry ("process",n) where n is the node we need to process.

2.2.2 The "results" stack
The result of this method is a number. So an entry in this stack will be a single number. Every time we need a previously computed result, we pop from this stack. Every time we must return something, we add it to this stack.

2.2.3 The method
We can express the above count method as follows:

TS: task stack
RS: results stack

TS.push("recur",head) //start with the head
while (TS is not empty) {
    (command,n) = TS.pop()
    if (command is "recur") {
        if (n is GenericEmptyNode) {
            //simply process this node, no recursive call
            TS.push("process",n)
        }
        if (n is GenericElementNode) {
            //first make the recursive call, then process this node (add 1 to result)
            //added in reverse order so that they come out of stack in correct order
            TS.push("process",n)
            TS.push("recur",n.rest)
        }
    }
    if (command is "process") {
        if (n is GenericEmptyNode) {
            //an empty node simply returns 0, as per above implementation
            RS.push(0)
        }
        if (n is GenericElementNode) {
            //add 1 to the "last result", as per above implementation
            num = RS.pop()
            RS.push(1+num)
        }
    }
}
answer = RS.pop() //the last thing remaining is the final result
To do: Comment out the earlier getSize method in the ListADTImpl class, and implement the above in its place. Verify that all the tests still pass.

2.3 Questions to Ponder
Compare at the two non-recursive implementations of the count method. Which method appears easier? Which method appears more general-purpose? Is the first implementation motivating enough to solve problems using an accumulator approach? What about situations when there are several recursive calls (one of which is possibly tail-recursive)?

2.4 The Map method
To do: Reimplement the map method in the ListADTImpl class non-recursively. Follow the recipe for traditional recursion or accumulators , whichever applicable.

3 Binary Search Trees
The bst package contains the implementation of a binary search tree, based on a similar design. We now attempt to implement some of its methods non-recursively. The benefits of our general recipe above will becomes more apparent in this design.

To do: Once again, begin by writing "undesirable" methods in the BSTElementNode class: a method T getData() that returns the data at this node, BSTNode<T> getLeft() and BSTNode<T> getRight() that return the left and right child respectively.

3.1 Computing the size of the binary search tree
The getSize method in the BSTImpl class uses the count method implemented in the BSTNode classes. The count methods have been implemented as follows:

//BSTElementNode

  @Override
  public int count() {
    return 1+this.left.count()+this.right.count();
  }

//BSTEmptyNode

  @Override
  public int count() {
    return 0;
  }
Although there are similarities between this implementation and the one for the lists, the main difference is that there are two recursive calls, and results from both of them are added to 1.

3.1.1 The General Recipe
The implementation in the BSTElementNode can be summed up as follows:

Recur to the left.
Recur to the right.
Add 1 to the results of steps 1 and 2 and return the sum.
Notice that all of these steps can be expressed in terms of "tasks" in our recipe! We can start from our earlier recipe and change it to produce the following (changes marked):

TS: task stack
RS: results stack

TS.push("recur",*root*) //CHANGE 1: start with the root
while (TS is not empty) {
    (command,n) = TS.pop()
    if (command is "recur") {
        if (n is BSTEmptyNode) {
            //simply process this node, no recursive call
            TS.push("process",n)
        }
        if (n is BSTElementNode) {
            //first make the recursive call to left, then to right, and finally process this node (add 1 to the results)
            //added in reverse order so that they come out of stack in correct order
            //CHANGE 2
            TS.push("process",n)
            TS.push("recur",n.right)
            TS.push("recur",n.left)
        }
    }
    if (command is "process") {
        if (n is BSTEmptyNode) {
            //an empty node simply returns 0, as per above implementation
            RS.push(0)
        }
        if (n is BSTElementNode) {
            //add 1 to the previous two results, as per above implementation
            //CHANGE 3
            num1 = RS.pop()
            num2 = RS.pop()
            RS.push(1+num1+num2)
        }
    }
}
answer = RS.pop() //the last thing remaining is the final result
To do: Copy the Pair class from the list implementation to this implementation, and then implement the getSize method in BSTImpl to follow the above recipe. Verify that all tests pass.

3.2 Pre-order traversal
To do: Look at the implementation of the pre-order traversal operation in the BSTImpl and BSTNode classes. Modify the recipe so that it can be implemented in the BSTImpl class directly, without using recursion.

This method has been designed a bit differently. Instead of returning the result as a list, it modifies a list passed to it (i.e. it accumulates the result). This is done for efficiency reasons (so that merging lists repeatedly is not required). Due to this, you may find that a non-recursive implementation borrows some concepts from the accumulator recipe. Specifically, it is not necessary to maintain an entire "results" stack.

3.3 In-order and Post-order traversal
To do: Try to implement these methods non-recursively as well.

Traditionally these methods are harder to implement non-recursively than pre-order traversal. But thanks to our recipe you will find that the non-recursive versions are slight variations of each other, just like the recursive versions!

4 Submission
Fix the style, and submit on the submission server. To submit, select the src/ and test/ folders (in Finder/Explorer) and compress them. Submit this zip file to the server. When you view your submission, you should see src/ and test/ as the two top-level folders.

5 Optional: Insertion
Inserting into a binary search tree is a slighly tricky variant: we do not recur twice, but only once. However whatever the recursive call returns must be set to the appropriate child (left or right). This means, that we need to differentiate between the two children of the node when processing the node. Can you modify the recipe to implement insertion non-recursively?

Note: In a "traditional" non-recursive implementation of binary search tree insertion, we have to keep track of the "last node", so that we know where the algorithm fell off the tree. In the recursive implementation, this is handled more naturally. The iterative implementation from our recipe retains this natural handling, while removing recursion.

6 Optional: Removing the "Undesirables"
All of our implementations above have two undesirable features:

The use of instanceof to know what kind of a node we are dealing with. This breaks the transparency of our design.
Writing methods that get the data of specific nodes ( BSTElementNode and GenericElementNode ). Calling these methods requires ugly casting.
6.1 Fixing the Recipe for Accumulators
The accumulator recipe looks better, because it only requires access to data and a way to move forward in a list until we reach the end. In concept we can rewrite the accumulator version of the count method like this:

    int acc = 0;
    current = beginning-of-the-list
    while (we-are-not-at-end-of-list) {
      acc +=1;
      current = advance-to-next-node;
    }
    return acc;
What we need is an object that offers the operations written in hyphenated phrases in the above code snippet. It turns out that there is a design pattern that provides precisely such a general-purpose way to go through a list: the Iterator design pattern. You can read more about it, and how to design it here .

6.2 Fixing the Recipe for Traditional Recursion
We note that our recipes call for different actions depending on the type of nodes. That is, "recur" is implemented differently for each node, and "process" is implemented differently for each node. This hints that if we are able to somehow push these implementations to the nodes themselves, then dynamic dispatch will execute the appropriate action.

However how the tasks are implemented for each node depends on the operation we are implementing (e.g. "process" on a GenericElementNode returns 0 for the count, but a GenericElementNode for the map method). Therefore if we simply push the recur and process implementations into the nodes, then we will have to write many versions of them depending on the operation. We use one more level of indirection to avoid this.

Imagine an interface like this:

public interface NodeWorker<T> {
    void workOnElementNode(T data,GenericListElementNode<T> rest);
    void workOnEmptyNode();
}
Each operation that we wish to implement non-recursively would require two implementations of the above interface: one for "recur" and another one for "process". Looking at our recipes for the count method for lists or the count method for trees , the first implementation would be used in "recur" case, and the second implementation would be used in the "process" case. These implementations would receive both stacks as arguments to their constructors, so that the implementations of the above methods can manipulate them accordingly.

But the question remains: how would our recipes know which method to call, without using instanceof ? We let dynamic dispatch do that for us! If a NodeWorker<T> is somehow provided to a node, it can decide which methods to call. We add two methods in the GenericListADTNode interface as follows:

void workForRecur(NodeWorker<T> worker);
void workForProcess(NodeWorker<T> worker);
Now in our implementation, given the node it would call the first method in the node for "recur", or the second method in the node for "process". The implementations of these methods in the node classes would call the correct workOnXXX methods in the worker object.

This design is not new: it is in fact a design pattern! This is an example of the Visitor design pattern. You can read more about it, and how to design it here

7 A Party of Design Patterns! But is it required?
As we have seen, a complete implementation of all the above ideas uses the iterator and visitor design patterns. The idea of each operation doing different things for each tasks itself is a (simplistic) example of yet another design pattern: the command design pattern. You can read more about it, and how to design it here .

But is the above design too complicated? Aren't there examples of iterative implementations of recursive algorithms that are much simpler? Yes, there are and the Fibonacci numbers algorithm is probably the best example of this. However our design recipes above are quite general-purpose, and can be used to create an iterative implementation of a wide variety of recursive algorithms. Some of these algorithms can probably be reimagined to create simpler implementations, but not all.

As an exercise, try to design iterative solutions to two extreme examples: Fibonacci numbers (simple) and the Towers of Hanoi problem (difficult).