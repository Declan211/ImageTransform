package cs3500.imageprocessing.model;

/**
 * interface for the PPM Image Model.
 */
public interface ImageModel extends ImageModelState {

  /**
   * In the model, sets the given pixel to the given x and y coordinates in the image.
   * @param ppmPixel given pixel.
   * @param x given x-coordinate.
   * @param y given y-coordinate.
   */
  void setPixel(Pixel ppmPixel, int x, int y);

  /**
   * gives the maximum color value of all the pixels in the model.
   * @return the maximum color value.
   */
  int getMaxColorValue();

  /**
   * provides a deep copy of this model. Copies all the pixels into a new model.
   * @return a deep copy of the model.
   */
  ImageModel deepCopy();
}
