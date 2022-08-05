package cs3500.imageprocessing.controller.command;

import org.junit.Test;

import java.io.StringReader;

import cs3500.imageprocessing.view.ImageView;
import cs3500.imageprocessing.view.ImageViewImpl;
import cs3500.imageprocessing.controller.control.ImageController;
import cs3500.imageprocessing.controller.control.ImageControllerImpl;
import cs3500.imageprocessing.model.ImageModel;

import static org.junit.Assert.assertTrue;

/**
 * test class to test the mock inputs.
 */
public class TestMock {

  @Test
  public void testPlayGameMock() {
    Readable sr1 = new StringReader("brighten 100 mock mock2");
    StringBuilder sb1 = new StringBuilder();
    ImageModel m1 = new ModelMock(sb1);
    ImageView msv1 = new ImageViewImpl(sb1);
    ImageController msc1 = new ImageControllerImpl(m1, msv1, sr1);
    msc1.processImage();

    assertTrue(sb1.toString().contains("setPixel called"));
    assertTrue(sb1.toString().contains("getMaxColorValue called"));
    assertTrue(sb1.toString().contains("deepCopy called"));
    assertTrue(sb1.toString().contains("getImageWidth called"));
    assertTrue(sb1.toString().contains("getImageHeight called"));
    assertTrue(sb1.toString().contains("getPixelAt called"));

  }
}
