package cs3500.imageprocessing.controller.command;

import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.ImageProcessingModel;
import cs3500.imageprocessing.model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * class for testing the LumaTransform function object.
 */
public class LumaTransformTest {
  ImageModel model1;
  Pixel pixel1;
  Pixel pixel2;
  Pixel pixel3;
  Pixel pixel4;


  @Before
  public void data() {
    model1 = new ImageProcessingModel(2,2,255);

    pixel1 = new Pixel(250, 123,250);
    pixel2 = new Pixel(90, 90,90);
    pixel3 = new Pixel(1, 1,2);
    pixel4 = new Pixel(100, 150,200);

    model1.setPixel(pixel1, 0,0);
    model1.setPixel(pixel2, 0,1);
    model1.setPixel(pixel3, 1,0);
    model1.setPixel(pixel4, 1, 1);
  }

  @Test
  public void execute() {
    new LumaTransform().execute(model1);


    assertEquals(model1.getPixelAt(0,0).getRed(), 159);
    assertEquals(model1.getPixelAt(0,0).getGreen(), 159);
    assertEquals(model1.getPixelAt(0,0).getBlue(), 159);

    assertEquals(model1.getPixelAt(0,1).getRed(), 90);
    assertEquals(model1.getPixelAt(0,1).getGreen(), 90);
    assertEquals(model1.getPixelAt(0,1).getBlue(), 90);

    assertEquals(model1.getPixelAt(1,0).getRed(), 1);
    assertEquals(model1.getPixelAt(1,0).getGreen(), 1);
    assertEquals(model1.getPixelAt(1,0).getBlue(), 1);

    // test different values of colors
    assertEquals(model1.getPixelAt(1,1).getRed(), 143);
    assertEquals(model1.getPixelAt(1,1).getGreen(), 143);
    assertEquals(model1.getPixelAt(1,1).getBlue(), 143);




  }


  @Test
  public void testInvalidConstructor() {
    try {

      new LumaTransform().execute(null);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("provided model is null", e.getMessage());
    }
  }
}