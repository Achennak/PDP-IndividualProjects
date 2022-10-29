List Operations

Objectives
The objectives of this lab are:

Practice writing recursive methods on recursive unions, such as lists.

Write JUnit tests to verify that implementation matches the specification.

You are allowed to work with another person on this lab. However both of you must submit individually to the server for it to count towards your grade.

1 The List Design
The starter code contains an interface ListOfString that represents a list of strings. This interface has been implemented in EmptyList (representing an empty list of strings) and NonEmptyList (representing a non-empty list of strings). They follow the design of recursive union types from lecture. Lastly a test class has been provided to you. These tests are exactly the tests on the server for this lab, although you are expected to add more tests to verify your implementation.

2 What to do
The interface has three methods that have not been meaningfully implemented. You are supposed to implement them, so that they pass the given tests.

2.1 toString
The toString method is a default method that exists in all Java classes. By default this method returns a string that is dependent on the reference of the object. You must override this method for the given classes, so that it returns a string that can be used to print the contents of the list. Specifically, it should return a sequence of strings in the list, separated by a comma. It should return an empty string if the list is empty. Look at the provided test to see the expected output.

Implement the toString method in the EmptyNode and NonEmptyList classes. You are allowed to add more methods in the ListOfString interface that will act as helper methods for this operation.

Remember: if you have to write code that finds the specific type of an object, then you are not using dynamic dispatch to your advantage!

Write more tests for this method before you implement it.

Do not submit to the submission server yet!

2.2 Questions to ponder and discuss
Discuss with the person next to you (or the student you chose to work with) the following questions:

Could you formulate this problem in the traditional recursive way?

Could you formulate this problem using an accumulator?

Which approach did you choose to implement and why? Is one solution more efficient than the other?

You may find the existing StringBuilder class helpful to discuss the last point.

2.3 Tricky: Reverse a list
Read the documentation of the reverse method in the ListOfString interface, and implement it accordingly in the two classes. Again, you are allowed to add helper methods to the interface if you wish.

Remember: if you have to write code that finds the specific type of an object, then you are not using dynamic dispatch to your advantage!
Do not submit to the submission server yet!

2.4 Questions to ponder and discuss
Discuss with the person next to you (or the student you chose to work with) the same questions as above, but for the reverse method:

Could you formulate this problem in the traditional recursive way?

Could you formulate this problem using an accumulator?

Which approach did you choose to implement and why? Is one solution more efficient than the other?

2.5 Trickier: List interleaving
Suppose you are given two lists of numbers A=(1,3,5) and B=(2,4,6,8,10). Then the interleave of A and B is (1,2,3,4,5,6,8,10), whereas the interleave of B and A is (2,1,4,3,6,5,8,10). In other words, the interleave operation interleaves the two lists, starting with the first list. If one list ends then the remaining part of the other list is simply appended to the result.

You must implement the interleave method in the two classes, so that it obeys this definition (and passes the provided test).

Remember: if you have to write code that finds the specific type of an object, then you are not using dynamic dispatch to your advantage!
Hint 1: Consider a similar operation: interleave two lists A and B, starting with the first element of A . Look carefully at the example lists given above: the resulting list begins with the first element of A, but can you characterize the remaining part of the resulting list?

Hint 2: You may find it useful to review double dispatch from lecture. Specifically, how we designed the second dispatch.

Fix the style, and submit on the submission server. To submit, select the src/ and test/ folders (in Finder/Explorer) and compress them. Submit this zip file to the server. When you view your submission, you should see src/ and test/ as the two top-level folders.