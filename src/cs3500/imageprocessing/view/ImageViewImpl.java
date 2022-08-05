package cs3500.imageprocessing.view;

import java.io.IOException;

/**
 * class representing the view for the PPM Image.
 */
public class ImageViewImpl implements ImageView {

  private final Appendable destination;


  /**
   * constructor - initializes the Appendable to the given destination.
   * @param destination given appendable.
   * @throws IllegalArgumentException if destination is null.
   */
  public ImageViewImpl(Appendable destination)
          throws IllegalArgumentException {
    if (destination == null) {
      throw new IllegalArgumentException("at least one parameter is null");
    }
    this.destination = destination;
  }

  public ImageViewImpl() {
    this.destination = System.out;
  }


  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }
}
