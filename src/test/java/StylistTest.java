import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Henry", "Brazilian Blowouts");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_Henry() {
    Stylist testStylist = new Stylist("Henry", "Brazilian Blowouts");
    assertEquals("Henry", testStylist.getName());
  }

  @Test
  public void getExpertise_stylistInstantiatesWithExpertise_String() {
    Stylist testStylist = new Stylist("Henry", "Brazilian Blowouts");
    assertEquals("Brazilian Blowouts", testStylist.getExpertise());
  }

}
