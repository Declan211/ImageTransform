package cs3500.imageprocessing.model;


/**
 * This is a pixel of a PPM P3 image. It will have the red, green, and blue values of the pixel.
 */
public class Pixel {

  private final int red;
  private final int green;
  private final int blue;
  private final int alpha;

  private int value;
  private int intensity;
  private int luma;

  /**
   * constructor - initializes each color as the given integer for that color, and sets the alpha
   * or transparency value to 255.
   * @param red amount of red.
   * @param green amount of green.
   * @param blue amount of blue.
   * @throws IllegalArgumentException if any inputs are less than 0.
   */
  public Pixel(int red, int green, int blue)
          throws IllegalArgumentException {
    if (red < 0 || blue < 0 || green < 0) {
      throw new IllegalArgumentException("Pixel values cannot be less than 0");
    }
    this.red = red;
    this.blue = blue;
    this.green = green;
    this.alpha = 255;
  }

  /**
   * constructor - initializes each color as the given integer for that color.
   * @param red amount of red.
   * @param green amount of green.
   * @param blue amount of blue.
   * @throws IllegalArgumentException if any inputs are less than 0.
   */
  public Pixel(int red, int green, int blue, int alpha)
          throws IllegalArgumentException {
    if (red < 0 || blue < 0 || green < 0) {
      throw new IllegalArgumentException("Pixel values cannot be less than 0");
    }
    this.red = red;
    this.blue = blue;
    this.green = green;
    this.alpha = alpha;
  }

  /**
   * This will return the red color value of the pixel.
   * @return the red value of the pixel.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * This will return the green color value of the pixel.
   * @return the green value of the pixel.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * This will return the blue color value of the pixel.
   * @return the blue value of the pixel.
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * This will return the alpha color value of the pixel.
   * @return the alpha value of the pixel.
   */
  public int getAlpha() {
    return this.alpha;
  }

}
