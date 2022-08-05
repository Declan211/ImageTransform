package cs3500.imageprocessing.view;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

import cs3500.imageprocessing.controller.control.ImageController;
import cs3500.imageprocessing.controller.control.ImageControllerImpl;
import cs3500.imageprocessing.controller.control.ImageGUIController;
import cs3500.imageprocessing.view.GraphicalView;
import cs3500.imageprocessing.view.ImageView;
import cs3500.imageprocessing.view.ImageViewImpl;

/**
 * class used to start the process image program.
 */
public class Main {

  /**
   * method to start the process image program in the System.out console.
   * @param args the user inputted strings from the system.in.
   */
  public static void main(String[] args) throws IOException {
    Readable reader = new InputStreamReader(System.in);

    GraphicalView.setDefaultLookAndFeelDecorated(false);
    GraphicalView view = new GraphicalView();
    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setVisible(true);
    ImageController control = new ImageGUIController(view);
    control.processImage();

  }
}