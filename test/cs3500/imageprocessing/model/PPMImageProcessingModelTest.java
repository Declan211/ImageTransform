package cs3500.imageprocessing.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * test class for the Image Processing Model.
 */
public class PPMImageProcessingModelTest {

  @Test
  public void testValidInitialization() {
    ImageModel model = new ImageProcessingModel(2, 2, 255);
    assertEquals(2, model.getImageWidth());
    assertEquals(2, model.getImageHeight());
    assertEquals(255, model.getMaxColorValue());
  }

  @Test
  public void testInvalidInitialization() {
    try {
      ImageModel model = new ImageProcessingModel(-2, 2, 255);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given model value is less then 0.")) {
        fail("Wrong exception thrown");
      }
    }
    try {
      ImageModel model = new ImageProcessingModel(2, -2, 255);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given model value is less then 0.")) {
        fail("Wrong exception thrown");
      }
    }
    try {
      ImageModel model = new ImageProcessingModel(2, 2, -255);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Given model value is less then 0.")) {
        fail("Wrong exception thrown");
      }
    }
  }

  @Test
  public void testMethods() {
    ImageModel model = new ImageProcessingModel(2, 2, 255);
    Pixel pixel1 = new Pixel(1, 2, 3);
    Pixel pixel2 = new Pixel(255, 200, 1);
    Pixel pixel3 = new Pixel(45, 234, 100);
    Pixel pixel4 = new Pixel(255, 154, 100);
    model.setPixel(pixel1, 0, 0);
    model.setPixel(pixel2, 0, 1);
    model.setPixel(pixel3, 1, 0);
    model.setPixel(pixel4, 1, 1);
    assertEquals(2, model.getImageWidth());
    assertEquals(2, model.getImageHeight());
    assertEquals(255, model.getMaxColorValue());
    assertEquals(pixel1.getBlue(), 3);
    assertEquals(pixel1.getGreen(), 2);
    assertEquals(pixel1.getRed(), 1);

    assertEquals(pixel3.getBlue(), 100);
    assertEquals(pixel3.getGreen(), 234);
    assertEquals(pixel3.getRed(), 45);


    ImageModel model2 = model.deepCopy();
    assertEquals(2, model2.getImageWidth());
    assertEquals(2, model2.getImageHeight());
    assertEquals(255, model2.getMaxColorValue());

  }



}