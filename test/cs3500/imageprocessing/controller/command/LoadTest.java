package cs3500.imageprocessing.controller.command;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import cs3500.imageprocessing.controller.load.Load;
import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.ImageProcessingModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * test class for load command.
 */
public class LoadTest {


  @Test
  public void execute() {

    Map<String, ImageModel> map = new HashMap<String, ImageModel>();
    ImageModel mtModel = new ImageProcessingModel(0, 0, 0);

    new Load("test/testLoadFiles/valid2x2.ppm", "2x2", map).execute(mtModel);

    assertEquals(2, map.get("2x2").getImageHeight());
    assertEquals(2, map.get("2x2").getImageWidth());
    assertEquals(255, map.get("2x2").getMaxColorValue());
    assertEquals(0, map.get("2x2").getPixelAt(0,0).getBlue());
    assertEquals(255, map.get("2x2").getPixelAt(1,0).getBlue());
    assertEquals(100, map.get("2x2").getPixelAt(0,1).getBlue());
    assertEquals(200, map.get("2x2").getPixelAt(1,1).getBlue());

    new Load("test/testLoadFiles/download.jpg", "down", map).execute(mtModel);
    assertEquals(183, map.get("down").getImageHeight());
    assertEquals(275, map.get("down").getImageWidth());
    assertEquals(255, map.get("down").getMaxColorValue());
    assertEquals(78, map.get("down").getPixelAt(1,0).getBlue());
    assertEquals(74, map.get("down").getPixelAt(0,1).getBlue());
    assertEquals(45, map.get("down").getPixelAt(1,1).getBlue());

    new Load("test/testLoadFiles/Capture.png", "cap", map).execute(mtModel);
    assertEquals(374, map.get("cap").getImageHeight());
    assertEquals(934, map.get("cap").getImageWidth());
    assertEquals(255, map.get("cap").getMaxColorValue());
    assertEquals(212, map.get("cap").getPixelAt(1,0).getBlue());
    assertEquals(212, map.get("cap").getPixelAt(0,1).getBlue());
    assertEquals(212, map.get("cap").getPixelAt(1,1).getBlue());

    new Load("test/testLoadFiles/download.bmp", "load", map).execute(mtModel);
    assertEquals(183, map.get("load").getImageHeight());
    assertEquals(275, map.get("load").getImageWidth());
    assertEquals(255, map.get("load").getMaxColorValue());
    assertEquals(78, map.get("load").getPixelAt(1,0).getBlue());
    assertEquals(74, map.get("load").getPixelAt(0,1).getBlue());
    assertEquals(45, map.get("load").getPixelAt(1,1).getBlue());
  }

  @Test
  public void testInvalidConstructor() {
    try {
      Map<String, ImageModel> map = new HashMap<String, ImageModel>();

      new Load("test/testLoadFiles/valid2x2.ppm", "2x2", map).execute(null);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("provided model is null", e.getMessage());
    }
  }


  @Test
  public void testInvalidLoads() {
    Map<String, ImageModel> map = new HashMap<String, ImageModel>();
    ImageModel mtModel = new ImageProcessingModel(0, 0, 0);
    try {
      new Load("test/testLoadFiles/noFile.ppm", "noFile", map).execute(mtModel);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("File test/testLoadFiles/noFile.ppm not found!", e.getMessage());
    }

    try {
      new Load("test/testLoadFiles/mt.ppm", "mt", map).execute(mtModel);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("ran out of inputs, bad file.", e.getMessage());
    }

    try {
      new Load("test/testLoadFiles/noP3.ppm", "mt", map).execute(mtModel);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("File must start with P3", e.getMessage());
    }

    try {
      new Load("test/testLoadFiles/invalid2x2.ppm", "2x2", map).execute(mtModel);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("ran out of inputs, bad file.", e.getMessage());
    }
    try {
      new Load("test/testLoadFiles/badColorValues2x2.ppm", "2x2", map).execute(mtModel);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("pixel color values cannot be greater than the max value", e.getMessage());
    }


  }
}