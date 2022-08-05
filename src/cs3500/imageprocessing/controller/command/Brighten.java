package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * this class will make modify the model brighter or darker variant of itself.
 * This means that every pixel's r,g, and b will all shifted by the given value.
 * Negative values will indicate a darker variant.
 * Positive values will indicate a brighter variant.
 * The value given cannot be 0, as that would not change the model.
 */
public class Brighten extends ACommandAbstract {
  private final int value;

  /**
   * construct the class with the given value.
   * @param value the value that will modify the model pixel colors by.
   */
  public Brighten(int value) {
    this.value = value;
  }

  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);

    if (value == 0) {
      throw new IllegalStateException("value for brightness cannot be 0");
    }
    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        Pixel pixelAt = model.getPixelAt(j, i);
        int newRed = pixelAt.getRed() + value;
        int newGreen = pixelAt.getGreen() + value;
        int newBlue = pixelAt.getBlue() + value;

        if (newRed > model.getMaxColorValue()) {
          newRed = model.getMaxColorValue();
        }
        if (newRed < 0) {
          newRed = 0;
        }
        if (newGreen > model.getMaxColorValue()) {
          newGreen = model.getMaxColorValue();
        }
        if (newGreen < 0) {
          newGreen = 0;
        }
        if (newBlue > model.getMaxColorValue()) {
          newBlue = model.getMaxColorValue();
        }
        if (newBlue < 0) {
          newBlue = 0;
        }

        Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
        model.setPixel(newPixel, j, i);
      }
    }
  }
}
