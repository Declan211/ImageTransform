package cs3500.imageprocessing.model;

import org.junit.Test;


import java.io.File;
import java.io.StringReader;
import java.util.InputMismatchException;

import cs3500.imageprocessing.view.ImageView;
import cs3500.imageprocessing.view.ImageViewImpl;
import cs3500.imageprocessing.controller.command.BadAppendable;
import cs3500.imageprocessing.controller.control.ImageController;
import cs3500.imageprocessing.controller.control.ImageControllerImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * class to test controllerImpl class.
 */
public class PPMImageControllerImplTest {


  @Test
  public void testProcessImage() {
    ImageModel model1 = new ImageProcessingModel(2, 2, 255);
    Readable reader = new StringReader("load test/testLoadFiles/valid2x2.ppm valid2x2 brighten " +
                    "10 valid2x2 valid2x2-brighten " +
                    "red-component valid2x2-brighten valid2x2-red vertical-flip valid2x2-red " +
                    "valid2x2-vert " +
                    "horizontal-flip valid2x2-vert valid2x2-hor blue-component valid2x2-hor " +
                    "valid2x2-blue " +
                    "green-component valid2x2-blue valid2x2-green value-component valid2x2-green " +
                    "valid2x2-value " +
                    "intensity-component valid2x2-value valid2x2-intensity luma-component " +
                    "valid2x2-intensity " +
                    "valid2x2-luma save test/testLoadFiles/valid2x2-lum.ppm valid2x2-luma");
    StringBuilder log = new StringBuilder();
    ImageView view1 = new ImageViewImpl(log);
    ImageController controller = new ImageControllerImpl(view1, reader);
    File f = new File("test/testLoadFiles/valid2x2-lum.ppm");
    assertTrue(f.delete());
    controller.processImage();
    assertEquals("Welcome to the Image program!" + System.lineSeparator() +
            "Supported user instructions are: " + System.lineSeparator() +
            "load 'srcFolder' 'newName' (load an image from the src folder and name it)"
            + System.lineSeparator() +
            "save 'fileDest' 'name' (save the file with the given 'name' in the fileDest)"
            + System.lineSeparator() +
            "brighten 'value' 'name' 'newName' (brighten the file 'name' by the value given)"
            + System.lineSeparator() +
            "red-component 'name' 'newName' (greyscale the file 'name' to the red value)"
            + System.lineSeparator() +
            "green-component 'name' 'newName' (greyscale the file 'name' to the green value)"
            + System.lineSeparator() +
            "blue-component 'name' 'newName' (greyscale the file 'name' to the blue value)"
            + System.lineSeparator() +
            "value-component 'name' 'newName' (greyscale the file 'name' to the value value)" +
            "" + System.lineSeparator() +
            "luma-component 'name' 'newName' (greyscale the file 'name' to the luma value)"
            + System.lineSeparator() +
            "intensity-component 'name' 'newName' " +
            "(greyscale the file 'name' to the intensity value)"
            + System.lineSeparator() +
            "vertical-flip 'name' 'newName' (vertically flip the file 'name')"
            + System.lineSeparator() +
            "horizontal-flip 'name' 'newName' (horizontally flip the file 'name')"
            + System.lineSeparator() +
            "sepia-transform 'name' 'newName' (sepia color the file 'name')"
                    + System.lineSeparator() +
            "luma-transform 'name' 'newName' (greyscale the file 'name' to the luma value)"
            + System.lineSeparator() +
            "blur 'name' 'newName' (blur the file 'name')"
            + System.lineSeparator() +
            "sharpen 'name' 'newName' (sharpen the file 'name')"
            + System.lineSeparator() +
            "file 'name' (process the commands in the file 'name')"
            + System.lineSeparator() +
            "menu (repeat this menu)" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "Thank you for using this program!", log.toString());
  }

  @Test
  public void testInvalidInputs() {
    ImageModel model1 = new ImageProcessingModel(2, 2, 255);
    Readable reader = new StringReader("load testingmock/testinvalidinputs.txt " +
                    "brighten testinvalidinputs test-brighter " +
                    "brighten 10 testinvalidinputs test-brighter" +
                    " save testingmock/test-brighter.ppm test-brighter");
    StringBuilder log = new StringBuilder();
    ImageView view1 = new ImageViewImpl(log);
    ImageController controller = new ImageControllerImpl(view1, reader);
    try {
      controller.processImage();
    } catch (InputMismatchException e) {
      log.append("input mismatch");
    }
    assertEquals("Welcome to the Image program!" + System.lineSeparator() +
            "Supported user instructions are: " + System.lineSeparator() +
            "load 'srcFolder' 'newName' (load an image from the src folder and name it)"
            + System.lineSeparator() +
            "save 'fileDest' 'name' (save the file with the given 'name' in the fileDest)"
            + System.lineSeparator() +
            "brighten 'value' 'name' 'newName' (brighten the file 'name' by the value given)"
            + System.lineSeparator() +
            "red-component 'name' 'newName' (greyscale the file 'name' to the red value)"
            + System.lineSeparator() +
            "green-component 'name' 'newName' (greyscale the file 'name' to the green value)"
            + System.lineSeparator() +
            "blue-component 'name' 'newName' (greyscale the file 'name' to the blue value)"
            + System.lineSeparator() +
            "value-component 'name' 'newName' (greyscale the file 'name' to the value value)"
            + System.lineSeparator() +
            "luma-component 'name' 'newName' (greyscale the file 'name' to the luma value)"
            + System.lineSeparator() +
            "intensity-component 'name' 'newName' (greyscale the file 'name' to the " +
            "intensity value)" + System.lineSeparator() +
            "vertical-flip 'name' 'newName' (vertically flip the file 'name')"
            + System.lineSeparator() +
            "horizontal-flip 'name' 'newName' (horizontally flip the file 'name')"
            + System.lineSeparator() +
            "sepia-transform 'name' 'newName' (sepia color the file 'name')"
            + System.lineSeparator() +
            "luma-transform 'name' 'newName' (greyscale the file 'name' to the luma value)"
            + System.lineSeparator() +
            "blur 'name' 'newName' (blur the file 'name')"
            + System.lineSeparator() +
            "sharpen 'name' 'newName' (sharpen the file 'name')"
            + System.lineSeparator() +
            "file 'name' (process the commands in the file 'name')"
            + System.lineSeparator() +
            "menu (repeat this menu)" + System.lineSeparator() +
            "Error: file type must be ppm, png, jpg, or bmp" + System.lineSeparator() +
            "Undefined instruction: testinvalidinputs" + System.lineSeparator() +
            "Undefined instruction: test-brighter" + System.lineSeparator() +
            "Error: null" + System.lineSeparator() +
            "Error: provided model is null" + System.lineSeparator() +
            "Thank you for using this program!", log.toString());
  }

  @Test
  public void testBadAppendable() {
    ImageModel model1 = new ImageProcessingModel(2, 2, 255);
    Readable reader = new StringReader("load ");
    StringBuilder log = new StringBuilder();
    ImageView view1 = new ImageViewImpl(new BadAppendable());
    ImageController controller = new ImageControllerImpl(view1, reader);
    try {
      controller.processImage();
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), null);
    }
  }




  @Test
  public void testScriptFile() {
    ImageModel model1 = new ImageProcessingModel(2, 2, 255);
    Readable reader = new StringReader("file test/testloadfiles/testScript.txt");
    StringBuilder log = new StringBuilder();
    ImageView view1 = new ImageViewImpl(log);
    ImageController controller = new ImageControllerImpl(view1, reader);
    File f = new File("test/testLoadFiles/valid2x2-lum.ppm");
    assertTrue(f.delete());
    controller.processImage();
    assertEquals("Welcome to the Image program!" + System.lineSeparator() +
            "Supported user instructions are: " + System.lineSeparator() +
            "load 'srcFolder' 'newName' (load an image from the src folder and name it)"
            + System.lineSeparator() +
            "save 'fileDest' 'name' (save the file with the given 'name' in the fileDest)"
            + System.lineSeparator() +
            "brighten 'value' 'name' 'newName' (brighten the file 'name' by the value given)"
            + System.lineSeparator() +
            "red-component 'name' 'newName' (greyscale the file 'name' to the red value)"
            + System.lineSeparator() +
            "green-component 'name' 'newName' (greyscale the file 'name' to the green value)"
            + System.lineSeparator() +
            "blue-component 'name' 'newName' (greyscale the file 'name' to the blue value)"
            + System.lineSeparator() +
            "value-component 'name' 'newName' (greyscale the file 'name' to the value value)" +
            "" + System.lineSeparator() +
            "luma-component 'name' 'newName' (greyscale the file 'name' to the luma value)"
            + System.lineSeparator() +
            "intensity-component 'name' 'newName' " +
            "(greyscale the file 'name' to the intensity value)"
            + System.lineSeparator() +
            "vertical-flip 'name' 'newName' (vertically flip the file 'name')"
            + System.lineSeparator() +
            "horizontal-flip 'name' 'newName' (horizontally flip the file 'name')"
            + System.lineSeparator() +
            "sepia-transform 'name' 'newName' (sepia color the file 'name')"
            + System.lineSeparator() +
            "luma-transform 'name' 'newName' (greyscale the file 'name' to the luma value)"
            + System.lineSeparator() +
            "blur 'name' 'newName' (blur the file 'name')"
            + System.lineSeparator() +
            "sharpen 'name' 'newName' (sharpen the file 'name')"
            + System.lineSeparator() +
            "file 'name' (process the commands in the file 'name')"
            + System.lineSeparator() +
            "menu (repeat this menu)" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "success!" + System.lineSeparator() +
            "Thank you for using this program!", log.toString());
  }
}