import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * This class represents test class for ListOfString
 * Created by ashesh on 1/30/2017.
 */
public class ListOfStringTest {

  @Test
  public void testCounts() {
    ListOfString emptyListOfStrings = new EmptyList();

    ListOfString partialListOfStrings = new NonEmptyList("capital",
            new NonEmptyList("of",
                    new NonEmptyList("Massachusetts", new
                            EmptyList())));

    ListOfString listOfStrings = new NonEmptyList("Boston",
            new NonEmptyList("is",
                    new NonEmptyList("the",
                            partialListOfStrings
                    )));
    assertEquals(0, emptyListOfStrings.size());
    assertEquals(3, partialListOfStrings.size());
    assertEquals(6, listOfStrings.size());
  }

  @Test
  public void testEasyStringListAssembly() {
    ListOfString sentence = new EmptyList();
    sentence = sentence.addFront("Massachusetts");
    sentence = sentence.addFront("Boston");
    sentence = sentence.add(1, "is");
    sentence = sentence.add(2, "of");
    sentence = sentence.add(2, "the");
    sentence = sentence.add(3, "capital");

    String[] words = "Boston is the capital of Massachusetts".split("\\s+");
    assertEquals(words.length, sentence.size());

    for (int i = 0; i < words.length; i = i + 1) {
      assertEquals(words[i], sentence.get(i));
    }

  }

  @Test
  public void testToString() {
    ListOfString sentence = makeList("Boston is the capital of Massachusetts");
    String answer = "Boston,is,the,capital,of,Massachusetts";
    assertEquals(answer, sentence.toString());
  }

  @Test
  public void testToStringNonEmptyList() {
    ListOfString sentence = makeList("Boston is the capital of "
            + "Massachusetts aaba sahbdhas ashbd 76y7 52362 @!@! ^T&gyugh");
    String answer = "Boston,is,the,capital,of,Massachusetts,"
            + "aaba,sahbdhas,ashbd,76y7,52362,@!@!,^T&gyugh";
    assertEquals(answer, sentence.toString());
  }

  @Test
  public void testToStringEmptyList() {
    ListOfString sentence = new EmptyList();
    String answer = "";
    assertEquals(answer, sentence.toString());
  }

  @Test
  public void testReverse() {
    ListOfString sentence = makeList("Boston is the capital of Massachusetts");
    ListOfString reversed = sentence.reverse();
    assertEquals(sentence.size(), reversed.size());
    for (int i = 0; i < sentence.size(); i = i + 1) {
      assertEquals(sentence.get(i), reversed.get(sentence.size() - 1 - i));
    }

  }

  @Test
  public void testReverse1() {
    ListOfString sentence = makeList("Boston is the capital of Massachusetts");
    ListOfString reversed = sentence.reverse();
    assertEquals(sentence.size(), reversed.size());
    for (int i = 0; i < sentence.size(); i = i + 1) {
      assertEquals(sentence.get(i), reversed.get(sentence.size() - 1 - i));
    }
    ListOfString reversed1 = reversed.reverse();
    assertEquals(reversed1.size(), reversed.size());
    for (int i = 0; i < reversed.size(); i = i + 1) {
      assertEquals(reversed.get(i), reversed1.get(reversed.size() - 1 - i));
    }
  }

  @Test
  public void testInterLeave2() {
    ListOfString part1 = makeList("Boston the of state in USA");
    ListOfString part2 = new EmptyList();
    ListOfString part1PlusPart2 = part1.interleave(part2);
    System.out.println(part1PlusPart2);
    for (int i = 0; i < 6; i = i + 1) {
      assertEquals(part1.get(i), part1PlusPart2.get(i));
    }
    ListOfString part2PlusPart1 = part2.interleave(part1);
    System.out.println(part2PlusPart1);
    for (int i = 0; i < 6; i = i + 1) {
      assertEquals(part1.get(i), part2PlusPart1.get(i));
    }
    ListOfString emptyListInterleave = part2.interleave(part2);
    System.out.println(emptyListInterleave);
    assertEquals("", emptyListInterleave.toString());
  }

  @Test
  public void testInterLeave() {
    ListOfString part1 = makeList("Boston the of state in USA");
    ListOfString part2 = makeList("is capital Massachusetts");
    ListOfString part1PlusPart2 = part1.interleave(part2);
    ListOfString part2PlusPart1 = part2.interleave(part1);

    int j = 0;
    int k = 0;
    for (int i = 0; i < 7; i = i + 1) {
      if (i % 2 == 0 && j < part1.size()) {
        assertEquals(part1.get(j), part1PlusPart2.get(i));
        j += 1;
      } else {
        assertEquals(part2.get(k), part1PlusPart2.get(i));
        k += 1;
      }
    }

    j = 0;
    k = 0;
    for (int i = 0; i < 7; i = i + 1) {
      if (i % 2 == 0 && k < part2.size()) {
        assertEquals(part2.get(k), part2PlusPart1.get(i));
        k += 1;
      } else {
        assertEquals(part1.get(j), part2PlusPart1.get(i));
        j += 1;
      }
    }


  }

  @Test
  public void testAddFront() {
    ListOfString part1 = new EmptyList();
    ListOfString part2 = part1.addFront("Akshaya");
    assertEquals("Akshaya", part2.toString());
    part2 = part2.addFront("Sai");
    assertEquals("Sai,Akshaya", part2.toString());
  }


  @Test
  public void testAddBAck() {
    ListOfString part1 = new EmptyList();
    ListOfString part2 = part1.addBack("Akshaya");
    assertEquals("Akshaya", part2.toString());
    part2 = part2.addBack("Chennakeshava");
    assertEquals("Akshaya,Chennakeshava", part2.toString());
  }

  @Test
  public void testGetSize() {
    ListOfString part1 = makeList("Boston the of state in USA");
    assertEquals(6, part1.size());
  }

  @Test
  public void testGet() {
    ListOfString part1 = makeList("Boston the of state in USA");
    assertEquals("Boston", part1.get(0));
  }

  @Test
  public void testGetThrowsException() {
    ListOfString part1 = new EmptyList();
    String expected = "Wrong index";
    String actual = null;
    try {
      part1.get(-1);
    } catch (IllegalArgumentException e) {
      actual = e.getMessage();
    }
    assertEquals(actual, expected);
  }


  @Test
  public void testAdd() {
    ListOfString part1 = makeList("Boston the of state in USA");
    assertEquals("Boston", part1.get(0));
    part1 = part1.add(0, "xyz");
    assertEquals("xyz", part1.get(0));
  }

  @Test
  public void testAddThrowsException() {
    ListOfString part1 = new EmptyList();
    String expected = "Invalid index to add an element";
    String actual = null;
    try {
      part1.add(2, "xyz");
    } catch (IllegalArgumentException e) {
      actual = e.getMessage();
    }
    assertEquals(actual, expected);
  }

  private ListOfString makeList(String sentence) {
    String[] words = sentence.split("\\s+");
    ListOfString list = new EmptyList();
    for (int i = words.length - 1; i >= 0; i -= 1) {
      list = list.addFront(words[i]);
    }
    return list;
  }


}