package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * this class will make modify the model to be a "luma-component" greyscale variant of itself.
 * This means that every pixel's r,g, and b will all get changed to be the pixels
 * luma (the weighted sum .212r + .7152g + .0722b) of all the r, g, b values.
 * Since the luma will likely create a double, the floor of the number was taken to allow it
 * to cast to an int.
 * This will not affect the color of the image since it is a small shift in values
 */
public class LumaComponent extends ACommandAbstract {

  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);

    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        Pixel pixelAt = model.getPixelAt(j, i);
        int luma = (int) Math.floor(0.2126 * pixelAt.getRed() + 0.7152 * pixelAt.getGreen() +
                0.0722 * pixelAt.getBlue());
        Pixel newPixel = new Pixel(luma, luma, luma);
        model.setPixel(newPixel, j, i);
      }
    }
  }

}
