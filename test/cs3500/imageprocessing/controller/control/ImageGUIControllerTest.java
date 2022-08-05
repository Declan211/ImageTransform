package cs3500.imageprocessing.controller.control;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.io.IOException;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.ImageProcessingModel;
import cs3500.imageprocessing.model.Pixel;
import cs3500.imageprocessing.view.GraphicalView;
import cs3500.imageprocessing.view.IGUIView;

import static org.junit.Assert.assertEquals;

public class ImageGUIControllerTest {
  ImageModel model1;
  IGUIView view1;
  Pixel pixel1;
  Pixel pixel2;
  Pixel pixel3;
  Pixel pixel4;


  @Before
  public void data() throws IOException {
    model1 = new ImageProcessingModel(2, 2, 255);

    pixel1 = new Pixel(123, 250, 250);
    pixel2 = new Pixel(90, 90, 90);
    pixel3 = new Pixel(1, 1, 2);
    pixel4 = new Pixel(150, 100, 200);

    model1.setPixel(pixel1, 0, 0);
    model1.setPixel(pixel2, 0, 1);
    model1.setPixel(pixel3, 1, 0);
    model1.setPixel(pixel4, 1, 1);

    view1 = new GraphicalView();
    view1.setModel(model1);
  }

  @Test
  public void testActionPerformed() {
    ImageGUIController controller = new ImageGUIController(view1, model1);
    ActionEvent event1 = new ActionEvent(view1, 2, "Open file");
    ActionEvent event2 = new ActionEvent(view1, 3, "brighten");
    controller.actionPerformed(event1);
    assertEquals(123, model1.getPixelAt(0, 0).getRed());
    assertEquals(250, model1.getPixelAt(0, 0).getGreen());
    assertEquals(250, model1.getPixelAt(0, 0).getBlue());

    assertEquals(90, model1.getPixelAt(0, 1).getRed());
    assertEquals(90, model1.getPixelAt(0, 1).getGreen());
    assertEquals(90, model1.getPixelAt(0, 1).getBlue());

    assertEquals(1, model1.getPixelAt(1, 0).getRed());
    assertEquals(1, model1.getPixelAt(1, 0).getGreen());
    assertEquals(2, model1.getPixelAt(1, 0).getBlue());

    assertEquals(150, model1.getPixelAt(1, 1).getRed());
    assertEquals(100, model1.getPixelAt(1, 1).getGreen());
    assertEquals(200, model1.getPixelAt(1, 1).getBlue());

    controller.actionPerformed(event2);
    assertEquals(133, model1.getPixelAt(0, 0).getRed());
    assertEquals(255, model1.getPixelAt(0, 0).getGreen());
    assertEquals(255, model1.getPixelAt(0, 0).getBlue());

    assertEquals(100, model1.getPixelAt(0, 1).getRed());
    assertEquals(100, model1.getPixelAt(0, 1).getGreen());
    assertEquals(100, model1.getPixelAt(0, 1).getBlue());

    assertEquals(11, model1.getPixelAt(1, 0).getRed());
    assertEquals(11, model1.getPixelAt(1, 0).getGreen());
    assertEquals(12, model1.getPixelAt(1, 0).getBlue());

    assertEquals(160, model1.getPixelAt(1, 1).getRed());
    assertEquals(110, model1.getPixelAt(1, 1).getGreen());
    assertEquals(210, model1.getPixelAt(1, 1).getBlue());

  }


}