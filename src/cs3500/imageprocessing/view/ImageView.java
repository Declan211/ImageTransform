package cs3500.imageprocessing.view;

import java.io.IOException;

/**
 * interface for the PPM Image View.
 */
public interface ImageView {

  /**
   * renders the given message to the Appendable.
   * @param message String to be appended.
   * @throws IOException if transmission to view fails.
   */
  void renderMessage(String message) throws IOException;
}
