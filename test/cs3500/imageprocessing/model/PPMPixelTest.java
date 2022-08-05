package cs3500.imageprocessing.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * class to test the pixel class.
 */
public class PPMPixelTest {

  @Test
  public void getRed() {
    Pixel p = new Pixel(1,2,3);
    assertEquals(p.getRed(), 1);
  }

  @Test
  public void getGreen() {
    Pixel p = new Pixel(1,2,3);
    assertEquals(p.getGreen(), 2);

  }

  @Test
  public void getBlue() {
    Pixel p = new Pixel(1,2,3);
    assertEquals(p.getBlue(), 3);

  }

  @Test
  public void testInvalidInitialization() {
    try {
      Pixel p = new Pixel(-1, 1, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Pixel values cannot be less than 0");
    }
    try {
      Pixel p = new Pixel(1, -1, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Pixel values cannot be less than 0");
    }
    try {
      Pixel p = new Pixel(1, 1, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Pixel values cannot be less than 0");
    }
    try {
      Pixel p = new Pixel(-1, -1, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Pixel values cannot be less than 0");
    }
  }
}