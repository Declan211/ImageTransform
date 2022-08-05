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
public class HorizontalFlipTest {
  ImageModel model1;
  Pixel pixel1;
  Pixel pixel2;
  Pixel pixel3;
  Pixel pixel4;
  Pixel pixel5;
  Pixel pixel6;
  Pixel pixel7;
  Pixel pixel8;
  Pixel pixel9;

  @Before
  public void data() {
    model1 = new ImageProcessingModel(3,3,255);

    pixel1 = new Pixel(250, 123,250);
    pixel2 = new Pixel(90, 90,90);
    pixel3 = new Pixel(1, 1,2);
    pixel4 = new Pixel(100, 150,200);
    pixel5 = new Pixel(34, 45,200);
    pixel6 = new Pixel(90, 90,90);
    pixel7 = new Pixel(0, 109,57);
    pixel8 = new Pixel(100, 90,80);
    pixel9 = new Pixel(126, 240,0);

    model1.setPixel(pixel1, 0,0);
    model1.setPixel(pixel2, 0,1);
    model1.setPixel(pixel3, 0,2);
    model1.setPixel(pixel4, 1, 0);
    model1.setPixel(pixel5, 1,1);
    model1.setPixel(pixel6, 1, 2);
    model1.setPixel(pixel7, 2,0);
    model1.setPixel(pixel8, 2, 1);
    model1.setPixel(pixel9, 2, 2);

  }

  @Test
  public void execute() {
    new HorizontalFlip().execute(model1);

    // test it stops at 255
    assertEquals(model1.getPixelAt(0,0).getRed(), 0);
    assertEquals(model1.getPixelAt(0,0).getGreen(), 109);
    assertEquals(model1.getPixelAt(0,0).getBlue(), 57);

    assertEquals(model1.getPixelAt(0,1).getRed(), 100);
    assertEquals(model1.getPixelAt(0,1).getGreen(), 90);
    assertEquals(model1.getPixelAt(0,1).getBlue(), 80);

    assertEquals(model1.getPixelAt(0,2).getRed(), 126);
    assertEquals(model1.getPixelAt(0,2).getGreen(), 240);
    assertEquals(model1.getPixelAt(0,2).getBlue(), 0);

    assertEquals(model1.getPixelAt(1,0).getRed(), 100);
    assertEquals(model1.getPixelAt(1,0).getGreen(), 150);
    assertEquals(model1.getPixelAt(1,0).getBlue(), 200);

    assertEquals(model1.getPixelAt(1,1).getRed(), 34);
    assertEquals(model1.getPixelAt(1,1).getGreen(), 45);
    assertEquals(model1.getPixelAt(1,1).getBlue(), 200);

    assertEquals(model1.getPixelAt(1,2).getRed(), 90);
    assertEquals(model1.getPixelAt(1,2).getGreen(), 90);
    assertEquals(model1.getPixelAt(1,2).getBlue(), 90);

    assertEquals(model1.getPixelAt(2,0).getRed(), 250);
    assertEquals(model1.getPixelAt(2,0).getGreen(), 123);
    assertEquals(model1.getPixelAt(2,0).getBlue(), 250);

    assertEquals(model1.getPixelAt(2,1).getRed(), 90);
    assertEquals(model1.getPixelAt(2,1).getGreen(), 90);
    assertEquals(model1.getPixelAt(2,1).getBlue(), 90);

    assertEquals(model1.getPixelAt(2,2).getRed(), 1);
    assertEquals(model1.getPixelAt(2, 2).getGreen(), 1);
    assertEquals(model1.getPixelAt(2,2).getBlue(), 2);



  }

  @Test
  public void testInvalidConstructor() {
    try {

      new HorizontalFlip().execute(null);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("provided model is null", e.getMessage());
    }
  }

}