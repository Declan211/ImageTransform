package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * this class will make modify the model to be a "red-component" greyscale variant of itself.
 * This means that every pixel's r,g, and b will all get changed to be the pixels red value.
 */
public class RedComponent extends ACommandAbstract {

  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);

    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        Pixel pixelAt = model.getPixelAt(j, i);
        int redValue = pixelAt.getRed();
        Pixel newPixel = new Pixel(redValue, redValue, redValue);
        model.setPixel(newPixel, j, i);
      }
    }
  }
}
