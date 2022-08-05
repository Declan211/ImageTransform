package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * function object for sharpening an image when called.
 */
public class Sharpen extends ACommandAbstract {
  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);

    ImageModel copy = model.deepCopy();
    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        double redSum = 0.0;
        double greenSum = 0.0;
        double blueSum = 0.0;


        // if i = 2 j = 2
        // m = 0, k = 0
        // m < 4
        for (int m = i - 2; m <= i + 2; m++) {
          for (int k = j - 2; k <= j + 2; k++) {
            if (2 == Math.abs(i - m) || 2 == Math.abs(j - k)) {
              Pixel pixel = this.filterHelper(copy, k, m);
              redSum = redSum + (-0.125 * pixel.getRed());
              greenSum = greenSum + (-0.125 * pixel.getGreen());
              blueSum = blueSum + (-0.125 * pixel.getBlue());

            } else if (1 == Math.abs(i - m) || 1 == Math.abs(j - k)) {
              Pixel pixel = this.filterHelper(copy, k, m);
              redSum = redSum + (0.25 * pixel.getRed());
              greenSum = greenSum + (0.25 * pixel.getGreen());
              blueSum = blueSum + (0.25 * pixel.getBlue());

            } else {
              Pixel pixel = this.filterHelper(copy, k, m);
              redSum = redSum + pixel.getRed();
              greenSum = greenSum + pixel.getGreen();
              blueSum = blueSum + pixel.getBlue();

            }
          }
        }
        int newRedSum = (int) Math.round(redSum);
        int newGreenSum = (int) Math.round(greenSum);
        int newBlueSum = (int) Math.round(blueSum);

        if (newRedSum < 0) {
          newRedSum = 0;
        }
        if (newRedSum > 255) {
          newRedSum = 255;
        }
        if (newGreenSum < 0) {
          newGreenSum = 0;
        }
        if (newGreenSum > 255) {
          newGreenSum = 255;
        }
        if (newBlueSum < 0) {
          newBlueSum = 0;
        }
        if (newBlueSum > 255) {
          newBlueSum = 255;
        }
        Pixel newPixel = new Pixel(newRedSum, newGreenSum, newBlueSum);
        model.setPixel(newPixel, j, i);

      }
    }
  }
}
