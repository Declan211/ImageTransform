package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * this class will make modify the model to be an "intensity-component" greyscale variant of itself.
 * This means that every pixel's r,g, and b will all get changed to be the pixels
 * the average of all the r, g, b values.
 * Since the average will likely create a double, the floor of the number was taken to allow it
 * to cast to an int.
 * This will not affect the color of the image since it is a small shift in values
 */
public class IntensityComponent extends ACommandAbstract {


  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);

    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        Pixel pixelAt = model.getPixelAt(j, i);
        int in;
        in = (int) Math.floor((pixelAt.getRed() + pixelAt.getGreen() + pixelAt.getBlue()) / 3.0);
        Pixel newPixel = new Pixel(in, in, in);
        model.setPixel(newPixel, j, i);
      }
    }
  }

}
