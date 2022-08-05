package cs3500.imageprocessing.controller.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cs3500.imageprocessing.controller.command.BlueComponent;
import cs3500.imageprocessing.controller.command.Blur;
import cs3500.imageprocessing.controller.command.Brighten;
import cs3500.imageprocessing.controller.command.Command;
import cs3500.imageprocessing.controller.command.GreenComponent;
import cs3500.imageprocessing.controller.command.HorizontalFlip;
import cs3500.imageprocessing.controller.command.IntensityComponent;
import cs3500.imageprocessing.controller.command.LumaTransform;
import cs3500.imageprocessing.controller.command.RedComponent;
import cs3500.imageprocessing.controller.command.Save;
import cs3500.imageprocessing.controller.command.Sepia;
import cs3500.imageprocessing.controller.command.Sharpen;
import cs3500.imageprocessing.controller.command.ValueComponent;
import cs3500.imageprocessing.controller.command.VerticalFlip;
import cs3500.imageprocessing.controller.load.Load;
import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.ImageProcessingModel;
import cs3500.imageprocessing.view.IGUIView;

public class ImageGUIController implements ImageController, ActionListener {

  ImageModel model;
  private final IGUIView view;

  public ImageGUIController(IGUIView view) {
    this.view = view;
    this.model = new ImageProcessingModel(0,0,0);

    view.setListener(this);
  }
  public ImageGUIController(IGUIView view, ImageModel model) {
    this.view = view;
    this.model = model;
    view.setListener(this);
  }

  @Override
  public void processImage() throws IllegalStateException {

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {
      case "blur":
        this.executeCommand(new Blur());
        break;
      case "sharpen":
        this.executeCommand(new Sharpen());
        break;
      case "sepia":
        this.executeCommand(new Sepia());
        break;
      case "brighten":
        this.executeCommand(new Brighten(10));
        break;
      case "darken":
        this.executeCommand(new Brighten(-10));
        break;
      case "vertical-flip":
        this.executeCommand(new VerticalFlip());
        break;
      case "horizontal-flip":
        this.executeCommand(new HorizontalFlip());
        break;
      case "red-component":
        this.executeCommand(new RedComponent());
        break;
      case "blue-component":
        this.executeCommand(new BlueComponent());
        break;
      case "green-component":
        this.executeCommand(new GreenComponent());
        break;
      case "value-component":
        this.executeCommand(new ValueComponent());
        break;
      case "luma-component":
        this.executeCommand(new LumaTransform());
        break;
      case "intensity-component":
        this.executeCommand(new IntensityComponent());
        break;
      case "Open file": {
        File f = view.openFile();
        try {
          String srcFile = f.getPath();
          String newName = "";
          Map<String, ImageModel> map = new HashMap<String, ImageModel>();
          ImageModel mt = new ImageProcessingModel(0, 0, 0);
          new Load(srcFile, newName, map).execute(mt);
          this.model = map.get(newName);
          this.view.setModel(model);
        } catch (Exception a) {
          writeMessage("Error: " + a.getMessage() + System.lineSeparator());
        }
        break;
      }

      case "Save file": {
        File f = view.saveFile();
        try {
          String srcFile = f.getPath();
          new Save(srcFile).execute(this.model);
        } catch (Exception a) {
          this.writeMessage(a.getMessage());
        }
        break;
      }
      }
    }
  private void writeMessage(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }



  /**
   * to change a model with the given command based on the used input via the scanner.
   * @param command the command that will be performed.
   */
  private void executeCommand(Command command) {
    try {
      command.execute(this.model);
      this.view.setModel(model);
    } catch (Exception e) {
      writeMessage("Error: " + e.getMessage() + System.lineSeparator());
    }
  }

  }

