package cs3500.imageprocessing.model;

/**
 * interface for the PPM Image Model State. Does not contain any mutating methods - this is to
 * be given to the view/controller.
 */
public interface ImageModelState {


  /**
   * gets the image width.
   * @return the image width.
   */
  int getImageWidth();

  /**
   * gets the image height.
   * @return the image height.
   */
  int getImageHeight();

  /**
   * gets the pixel at the given coordinates.
   * @param x x coordinate
   * @param y y coordinate
   * @return the pixel at the given coordinates.
   */
  Pixel getPixelAt(int x, int y);

  Integer[][] getHistogramValues();




  }
