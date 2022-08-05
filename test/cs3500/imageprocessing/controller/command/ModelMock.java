package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * mock model for testing.
 */
public class ModelMock implements ImageModel {

  StringBuilder append;

  /**
   * create the mock model.
   * @param append the log.
   */
  public ModelMock(StringBuilder append) {
    this.append = append;
  }

  @Override
  public void setPixel(Pixel ppmPixel, int x, int y) {

    append.append("setPixel called");
  }

  @Override
  public int getMaxColorValue() {
    append.append("getMaxColorValue called");
    return 255;

  }

  @Override
  public ImageModel deepCopy() {
    append.append("deepCopy called");
    return this;

  }

  @Override
  public int getImageWidth() {
    append.append("getImageWidth called");
    return 2;

  }

  @Override
  public int getImageHeight() {
    append.append("getImageHeight called");
    return 2;

  }

  @Override
  public Pixel getPixelAt(int x, int y) {
    append.append("getPixelAt called");
    return new Pixel(0,0,0);
  }

  @Override
  public Integer[][] getHistogramValues() {
    return new Integer[0][];
  }


}
