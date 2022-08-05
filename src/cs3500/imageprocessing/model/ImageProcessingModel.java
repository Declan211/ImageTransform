package cs3500.imageprocessing.model;

/**
 * This is the model class to represent a PPM P3 image through PPMPixels in a 2-D array list.
 */
public class ImageProcessingModel implements ImageModel {

  private final int width;
  private final int height;
  private final int maxColorValue;
  private final Pixel[][] board;

  /**
   * constructor - intializes the width, height, and max color value as the given parameters, and
   * the board as a null 2D array. Throws an IllegalArgumentException if any of the inputs are
   * less than 0.
   *
   * @param width         image width.
   * @param height        image height.
   * @param maxColorValue maximum color value.
   * @throws IllegalArgumentException if any of the inputs are less than 0.
   */
  public ImageProcessingModel(int width, int height, int maxColorValue)
          throws IllegalArgumentException {
    if (width < 0 || height < 0 || maxColorValue < 0) {
      throw new IllegalArgumentException("Given model value is less then 0.");
    }
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
    this.board = new Pixel[width][height];
  }


  @Override
  public int getImageWidth() {
    return this.width;
  }

  @Override
  public int getImageHeight() {
    return this.height;
  }

  @Override
  public Pixel getPixelAt(int xPixel, int yPixel) {
    Pixel p = this.board[xPixel][yPixel];
    return new Pixel(p.getRed(), p.getGreen(), p.getBlue());
  }

  @Override
  public int getMaxColorValue() {
    return this.maxColorValue;
  }

  @Override
  public ImageModel deepCopy() {
    ImageModel model = new ImageProcessingModel(this.width, this.height, this.maxColorValue);
    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        Pixel cur = this.getPixelAt(i, j);
        int r = cur.getRed();
        int g = cur.getGreen();
        int b = cur.getBlue();

        Pixel pixel = new Pixel(r, g, b);
        model.setPixel(pixel, i, j);

      }
    }
    return model;
  }

  @Override
  public void setPixel(Pixel ppmPixel, int x, int y) {
    this.board[x][y] = ppmPixel;
  }


  public Integer[][] getHistogramValues() {
    Integer[][] values = new Integer[256][4];

    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 4; j++) {
        values[i][j] = 0;
      }
    }

    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        Pixel cur = this.getPixelAt(i, j);
        int r = cur.getRed();
        int g = cur.getGreen();
        int b = cur.getBlue();
        int in = (int) Math.floor((r + g + b) / 3.0);


        values[r][0] = values[r][0] + 1;
        values[g][1] = values[g][1] + 1;
        values[b][2] = values[b][2] + 1;
        values[in][3] = values[in][3] + 1;
      }
    }
    return values;
  }
}
