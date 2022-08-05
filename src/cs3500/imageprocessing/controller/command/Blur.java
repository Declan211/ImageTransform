package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * Function object which blurs an image when called on it.
 */
public class Blur extends ACommandAbstract {

  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);

    ImageModel copy = model.deepCopy();
    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        int redSum;
        int greenSum;
        int blueSum;

        Pixel topLeft = this.filterHelper(copy, j - 1, i - 1);
        Pixel top = this.filterHelper(copy, j, i - 1);
        Pixel topRight = this.filterHelper(copy, j + 1, i - 1);
        Pixel left = this.filterHelper(copy, j - 1, i);
        Pixel middle = this.filterHelper(copy, j, i);
        Pixel right = this.filterHelper(copy, j + 1, i);
        Pixel botLeft = this.filterHelper(copy, j - 1, i + 1);
        Pixel bot = this.filterHelper(copy, j, i + 1);
        Pixel botRight = this.filterHelper(copy, j + 1, i + 1);


        redSum = (int) Math.round(((topLeft.getRed() + topRight.getRed() + botLeft.getRed() +
                botRight.getRed()) * (1.0 / 16.0)) +
                ((top.getRed() + left.getRed() + right.getRed() + bot.getRed()) * (1.0 / 8.0)) +
                (middle.getRed() * (1.0 / 4.0)));
        greenSum = (int) Math.round(((topLeft.getGreen() + topRight.getGreen() + botLeft.getGreen()
                + botRight.getGreen()) * (1.0 / 16.0)) +
                ((top.getGreen() + left.getGreen() + right.getGreen() + bot.getGreen()) *
                        (1.0 / 8.0)) +
                (middle.getGreen() * (1.0 / 4.0)));
        blueSum = (int) Math.round(((topLeft.getBlue() + topRight.getBlue() + botLeft.getBlue() +
                botRight.getBlue()) * (1.0 / 16.0)) +
                ((top.getBlue() + left.getBlue() + right.getBlue() + bot.getBlue()) * (1.0 / 8.0)) +
                (middle.getRed() * (1.0 / 4.0)));

        if (redSum < 0) {
          redSum = 0;
        }
        if (redSum > 255) {
          redSum = 255;
        }
        if (blueSum < 0) {
          blueSum = 0;
        }
        if (blueSum > 255) {
          blueSum = 255;
        }
        if (greenSum < 0) {
          greenSum = 0;
        }
        if (greenSum > 255) {
          greenSum = 255;
        }

        Pixel newPixel = new Pixel(redSum, greenSum, blueSum);
        model.setPixel(newPixel, j, i);
      }
    }
  }


}
