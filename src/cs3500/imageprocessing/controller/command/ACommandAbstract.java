package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * abstract function object containing a method filterHelper,
 * which abstracts the handling of excpetions when making pixels.
 */
public abstract class ACommandAbstract implements Command {


  /**
   * this is a helper method used to try to grab surrounding pixels of the orignal one.
   * The pixel in the model at the x, y coords will try to be grabbed and if an exception is thrown,
   * it will return a default pixel with 0 as the rgb's.
   * @param model the model that the pixel will be grabbed from.
   * @param x the x of the pixel.
   * @param y the y of the pixel.
   * @return the pixel, or a default one if it is out of bounds.
   */
  protected Pixel filterHelper(ImageModel model, int x, int y) {
    try {
      return model.getPixelAt(x, y);
    } catch (IndexOutOfBoundsException e) {
      return new Pixel(0, 0, 0);
    }
  }

  @Override
  public abstract void execute(ImageModel model) throws IllegalStateException;

  /**
   * helper method used to transform every pixel in the model's rgb values using
   * matrix multiplication against the 3x3 2-D array list given.
   * @param model the model that will be mutated.
   * @param array the 3x3 2-D arrayList that is used to transform the model.
   */
  protected void colorTransformation(ImageModel model, double[][] array) {
    int red = 0;
    int green = 0;
    int blue = 0;
    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        Pixel pixelAt = model.getPixelAt(j, i);
        red = (int) Math.round((pixelAt.getRed() * array[0][0]) +
                (pixelAt.getGreen() * array[0][1]) +
                (pixelAt.getBlue() * array[0][2]));
        green = (int) Math.round((pixelAt.getRed() * array[1][0]) +
                (pixelAt.getGreen() * array[1][1]) +
                (pixelAt.getBlue() * array[1][2]));
        blue = (int) Math.round((pixelAt.getRed() * array[2][0]) +
                (pixelAt.getGreen() * array[2][1]) +
                (pixelAt.getBlue() * array[2][2]));

        if (red < 0) {
          red = 0;
        }
        if (red > 255) {
          red = 255;
        }
        if (green < 0) {
          green = 0;
        }
        if (green > 255) {
          green = 255;
        }
        if (blue < 0) {
          blue = 0;
        }
        if (blue > 255) {
          blue = 255;
        }
        Pixel newPixel = new Pixel(red, green, blue);
        model.setPixel(newPixel, j, i);
      }
    }
  }

  /**
   * Will check if the model is null.
   * @param model the model that will be checked.
   * @throws IllegalStateException if the model is null.
   */
  protected void modelNullCheck(ImageModel model) throws IllegalStateException {
    if (model == null) {
      throw new IllegalStateException("provided model is null");
    }
  }

  /**
   * will return the type of the file. (the last 3 characters)
   * @param fileName the file name that will be used.
   * @return the type of the filename.
   */
  protected String getType(String fileName) {
    String type = "";
    type = type + fileName.charAt(fileName.length() - 3);
    type = type + fileName.charAt(fileName.length() - 2);
    type = type + fileName.charAt(fileName.length() - 1);
    return type;
  }


}
