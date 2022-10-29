Chess pieces


Objectives
The objectives of this lab are:

Use abstraction in code and tests as a way to minimize code duplication and improve design.

Use appropriate access modifiers and data restrictions for tighter design.

Use documentation to convey design intent.

2 Introduction
Duplicate code and redundant design is a common occurrence. Although sometimes it is the result of bad design, many times duplication and redundancy creeps into code that has been developed over a period of time. Abstraction is an effective way to tackle duplication in design and code.

Some code has been provided to you. Collectively, this code represents the design, implementation and testing of chess pieces. The ChessPiece interface represents some operations that all pieces should support. The Rook , Bishop and Queen classes represent specific chesspieces and implement the ChessPiece interface.

As the story goes, the Bishop class was implemented first, followed by Queen and then Rook . The first class was written, and then the developer wrote the second class by drawing inspiration from the first (note the similarity in the methods and tests). The third class was written by another developer, again drawing inspiration from the first two. As a result the provided code (including tests) have the following undesirable properties:

They seem to have redundant and/or duplicate code.

The fields are not appropriately guarded against side-effects or inappropriate access.

Although the interface is well-documented, the documentation of the implementations is rather scant.

Much like the second developer, if more chesspieces were to be implemented they would likely inherit the same undesirable properties as the above.

3 What to do
Your aim in this lab is to refactor (change the implementations without changing what they do, or how other classes view their public offerings) the provided code. Use abstraction mechanisms such as inheritance, abstract classes and methods, etc. as necessary to minimize duplication of code. Tighten the access and usage of fields as appropriate. Lastly write appropriate Javadoc-style documentation for all the implementations.

Note that the provided tests suffer from similar duplication as the code they test. The same ideas of abstraction apply to tests as well! For example, one may write an abstract test class and concrete classes that extend it. Only the concrete test classes can be run.

4 Questions to ponder/discuss
Discuss these questions with the person next to you, and report to the course staff.

Do you still see duplicate code after your abstraction? If so, why did you not remove it?

What limitations or shortcomings are you introducing into your code as a result of abstraction?

5 What to do: Part 2
Implement a new chesspiece: knight. As you may know, a knight can move in an L shape (e.g. two places to right and one on top, etc.). Try to fit the resulting Knight class into your improved design. Don't forget to write tests for it!