package cs3500.imageprocessing.controller.command;

import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.ImageProcessingModel;
import cs3500.imageprocessing.model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * test class for command.
 */
public class BrightenTest {

  ImageModel model1;
  Pixel pixel1;
  Pixel pixel2;
  Pixel pixel3;
  Pixel pixel4;


  @Before
  public void data() {
    model1 = new ImageProcessingModel(2,2,255);

    pixel1 = new Pixel(250, 250,250);
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
    new Brighten(10).execute(model1);

    // test it stops at 255
    assertEquals(model1.getPixelAt(0,0).getRed(), 255);
    assertEquals(model1.getPixelAt(0,0).getGreen(), 255);
    assertEquals(model1.getPixelAt(0,0).getBlue(), 255);

    assertEquals(model1.getPixelAt(0,1).getRed(), 100);
    assertEquals(model1.getPixelAt(0,1).getGreen(), 100);
    assertEquals(model1.getPixelAt(0,1).getBlue(), 100);

    assertEquals(model1.getPixelAt(1,0).getRed(), 11);
    assertEquals(model1.getPixelAt(1,0).getGreen(), 11);
    assertEquals(model1.getPixelAt(1,0).getBlue(), 12);

    // test different values of colors
    assertEquals(model1.getPixelAt(1,1).getRed(), 110);
    assertEquals(model1.getPixelAt(1,1).getGreen(), 160);
    assertEquals(model1.getPixelAt(1,1).getBlue(), 210);


    // test negative brightness
    new Brighten(-20).execute(model1);

    assertEquals(model1.getPixelAt(0,0).getRed(), 235);
    assertEquals(model1.getPixelAt(0,0).getGreen(), 235);
    assertEquals(model1.getPixelAt(0,0).getBlue(), 235);

    assertEquals(model1.getPixelAt(0,1).getRed(), 80);
    assertEquals(model1.getPixelAt(0,1).getGreen(), 80);
    assertEquals(model1.getPixelAt(0,1).getBlue(), 80);

    // test it does not go below 0
    assertEquals(model1.getPixelAt(1,0).getRed(), 0);
    assertEquals(model1.getPixelAt(1,0).getGreen(), 0);
    assertEquals(model1.getPixelAt(1,0).getBlue(), 0);

    assertEquals(model1.getPixelAt(1,1).getRed(), 90);
    assertEquals(model1.getPixelAt(1,1).getGreen(), 140);
    assertEquals(model1.getPixelAt(1,1).getBlue(), 190);

  }

  @Test
  public void testInvalidConstructor() {
    try {

      new Brighten(10).execute(null);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("provided model is null", e.getMessage());
    }

    try {

      new Brighten(0).execute(new ImageProcessingModel(0,0,0));
      fail();
    } catch (IllegalStateException e) {
      assertEquals("value for brightness cannot be 0", e.getMessage());
    }
  }
}