import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class represents book class test file.
 */
public class BookTest {

  private Book harryPotter;
  private Person john;

  @Before
  public void setUp() {

    john = new Person("John", "Doe", 1945);
    harryPotter = new Book("HarryPotter-Secret of the Chambers", john, 200);
  }

  @Test
  public void testTitleName() {
    assertEquals("HarryPotter-Secret of the Chambers", harryPotter.getTitle());

  }

  @Test
  public void testAuthor() {
    assertEquals(john, harryPotter.getAuthor());
  }

  @Test
  public void testAuthorFirstName() {
    assertEquals("John", harryPotter.getAuthor().getFirstName());
  }

  @Test
  public void testAuthorLastName() {
    assertEquals("Doe", harryPotter.getAuthor().getLastName());
  }

  @Test
  public void testAuthorYearOfBirth() {
    assertEquals(1945, harryPotter.getAuthor().getYearOfBirth());
  }

  @Test
  public void testPrice() {
    assertEquals(200, harryPotter.getPrice(), 0.001);
  }
}
