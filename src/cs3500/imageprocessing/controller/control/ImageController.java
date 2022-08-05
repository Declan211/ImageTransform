package cs3500.imageprocessing.controller.control;

/**
 * This interface will control where the user input goes and how it is outputted.
 * This connects with the view and the model to display the program and allow the user to
 * put in commands.
 */
public interface ImageController {

  /**
   * This will take in user input to process an image which includes graphically changing it by
   * changing the pixel colors.
   * The program will stop if the user inputs 'q' or 'quit'
   * The menu will appear if the user inputs 'menu'
   * The list of commands are seen in the README file and when the program is started.
   * @throws IllegalStateException if the view cannot receive the messages sent. All other
   *                               exceptions are dealt with in this program to send the user an
   *                               error message but still continue on.
   */
  void processImage() throws IllegalStateException;
}
