package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * this class will make modify the model to be a "value-component" greyscale variant of itself.
 * This means that every pixel's r,g, and b will all get changed to be the pixels
 * maximum value of all the r, g, b values.
 */
public class ValueComponent extends ACommandAbstract {

  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);

    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        Pixel pixelAt = model.getPixelAt(j, i);
        int value = Math.max(Math.max(pixelAt.getRed(), pixelAt.getBlue()), pixelAt.getGreen());
        Pixel newPixel = new Pixel(value, value, value);
        model.setPixel(newPixel, j, i);
      }
    }
  }
}

