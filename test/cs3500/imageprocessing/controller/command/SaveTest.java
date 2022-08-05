package cs3500.imageprocessing.controller.command;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cs3500.imageprocessing.controller.load.Load;
import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.ImageProcessingModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * test class for save command.
 */
public class SaveTest {

  @Test
  public void execute() {
    File f = new File("test/testLoadFiles/testSave.ppm");
    File f1 = new File("test/testLoadFiles/testSave.png");
    File f2 = new File("test/testLoadFiles/testSave.jpg");
    File f3 = new File("test/testLoadFiles/testSave.bmp");

    assertTrue(f.delete());
    assertTrue(f1.delete());
    assertTrue(f2.delete());
    assertTrue(f3.delete());

    ImageModel mt = new ImageProcessingModel(0, 0, 0);
    Map<String, ImageModel> map = new HashMap<String, ImageModel>();
    new Load("test/testLoadFiles/valid2x2.ppm", "2x2new", map).execute(mt);
    ImageModel model = map.get("2x2new");

    new Save("test/testLoadFiles/testSave.ppm").execute(model);

    new Save("test/testLoadFiles/testSave.png").execute(model);

    new Save("test/testLoadFiles/testSave.jpg").execute(model);

    new Save("test/testLoadFiles/testSave.bmp").execute(model);


    Scanner sc = null;
    try {
      sc = new Scanner(new FileInputStream("test/testLoadFiles/testSave.ppm"));
    } catch (FileNotFoundException e) {
      fail();
    }
    //read the file line by line, and populate a string. This will throw away any comment lines
    String s = sc.nextLine();
    assertEquals(s, "P3");

    String s2 = sc.nextLine();
    assertEquals(s2, "2 2");

    String s3 = sc.nextLine();
    assertEquals(s3, "255");

    new Load("test/testLoadFiles/testSave.ppm", "testSave", map).execute(mt);

    assertEquals(2, map.get("testSave").getImageHeight());
    assertEquals(2, map.get("testSave").getImageWidth());
    assertEquals(255, map.get("testSave").getMaxColorValue());
    assertEquals(0, map.get("testSave").getPixelAt(0, 0).getBlue());
    assertEquals(255, map.get("testSave").getPixelAt(1, 0).getBlue());
    assertEquals(100, map.get("testSave").getPixelAt(0, 1).getBlue());
    assertEquals(200, map.get("testSave").getPixelAt(1, 1).getBlue());

    new Load("test/testLoadFiles/testSave.png", "png", map).execute(mt);

    assertEquals(2, map.get("png").getImageHeight());
    assertEquals(2, map.get("png").getImageWidth());
    assertEquals(255, map.get("png").getMaxColorValue());
    assertEquals(0, map.get("png").getPixelAt(0, 0).getBlue());
    assertEquals(255, map.get("png").getPixelAt(1, 0).getBlue());
    assertEquals(100, map.get("png").getPixelAt(0, 1).getBlue());
    assertEquals(200, map.get("png").getPixelAt(1, 1).getBlue());

    new Load("test/testLoadFiles/testSave.jpg", "jpg", map).execute(mt);

    assertEquals(2, map.get("jpg").getImageHeight());
    assertEquals(2, map.get("jpg").getImageWidth());
    assertEquals(255, map.get("jpg").getMaxColorValue());
    assertEquals(13, map.get("jpg").getPixelAt(0, 0).getBlue());
    assertEquals(234, map.get("jpg").getPixelAt(1, 0).getBlue());
    assertEquals(84, map.get("jpg").getPixelAt(0, 1).getBlue());
    assertEquals(209, map.get("jpg").getPixelAt(1, 1).getBlue());

    new Load("test/testLoadFiles/testSave.bmp", "bmp", map).execute(mt);

    assertEquals(2, map.get("bmp").getImageHeight());
    assertEquals(2, map.get("bmp").getImageWidth());
    assertEquals(255, map.get("bmp").getMaxColorValue());
    assertEquals(0, map.get("bmp").getPixelAt(0, 0).getBlue());
    assertEquals(255, map.get("bmp").getPixelAt(1, 0).getBlue());
    assertEquals(100, map.get("bmp").getPixelAt(0, 1).getBlue());
    assertEquals(200, map.get("bmp").getPixelAt(1, 1).getBlue());


  }


  @Test
  public void testInvalidSave() {
    ImageModel mt = new ImageProcessingModel(0, 0, 0);
    Map<String, ImageModel> map = new HashMap<String, ImageModel>();
    new Load("test/testLoadFiles/valid2x2.ppm", "2x2new", map).execute(mt);
    ImageModel model = map.get("2x2new");
    try {
      new Save("test/testLoadFiles/testSave.ppm").execute(model);
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("File already exist at location: "));
    }

    try {
      new Save("test/testLoadFiles/testSave.klp").execute(model);
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("file save type not supported, must be ppm, png," +
              " jpg, bmp"));
    }


  }

  @Test
  public void testInvalidConstructor() {
    try {
      new Save("test/testLoadFiles/valid2x2.ppm").execute(null);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("provided model is null", e.getMessage());
    }
  }

}