package cs3500.imageprocessing.controller.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cs3500.imageprocessing.controller.command.Blur;
import cs3500.imageprocessing.controller.command.Brighten;
import cs3500.imageprocessing.controller.command.Command;
import cs3500.imageprocessing.controller.command.GreenComponent;
import cs3500.imageprocessing.controller.command.HorizontalFlip;
import cs3500.imageprocessing.controller.command.LumaTransform;
import cs3500.imageprocessing.controller.command.Sepia;
import cs3500.imageprocessing.controller.command.Sharpen;
import cs3500.imageprocessing.controller.load.Load;
import cs3500.imageprocessing.controller.command.LumaComponent;
import cs3500.imageprocessing.controller.command.RedComponent;
import cs3500.imageprocessing.controller.command.Save;
import cs3500.imageprocessing.controller.command.ValueComponent;
import cs3500.imageprocessing.controller.command.VerticalFlip;
import cs3500.imageprocessing.model.ImageProcessingModel;
import cs3500.imageprocessing.controller.command.BlueComponent;
import cs3500.imageprocessing.controller.command.IntensityComponent;
import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.view.ImageView;

/**
 * The controller implementation for a PPMImage. This will connect the user with the
 * commands that can modify the loaded models.
 */
public class ImageControllerImpl implements ImageController {

  private final Map<String, ImageModel> modelMap;
  private final ImageView view;
  private final Readable in;

  /**
   * The constructor to add in the given view, readable.
   * @param view the view that will display the messages.
   * @param readable the readable that will send in the messages.
   * @throws IllegalArgumentException if any provided argument is null.
   */
  public ImageControllerImpl(ImageView view, Readable readable)
          throws IllegalArgumentException {
    if (view == null || readable == null) {
      throw new IllegalArgumentException("a provided parameter is null");
    }
    this.view = view;
    this.in = readable;
    this.modelMap = new HashMap<String, ImageModel>();
  }

  /**
   * The constructor to add in the given view, readable, and a mock for testing.
   * @param mock the mock model that is being tested on.
   * @param view the view that will display the messages.
   * @param readable the readable that will send in the messages.
   * @throws IllegalArgumentException if any provided argument is null.
   */
  public ImageControllerImpl(ImageModel mock, ImageView view, Readable readable)
          throws IllegalArgumentException {
    if (mock == null || view == null || readable == null) {
      throw new IllegalArgumentException("a provided parameter is null");
    }
    this.view = view;
    this.in = readable;
    this.modelMap = new HashMap<String, ImageModel>();
    this.modelMap.put("mock", mock);
  }


  @Override
  public void processImage() throws IllegalStateException {

    Scanner sc = new Scanner(this.in);
    //print the welcome message
    this.welcomeMessage();
    this.runFile(sc);
    this.farewellMessage();

  }


  /**
   * private method to assist the processImage method in scanner and dealing with user input.
   * @param userInstruction the users input that will be used.
   * @param sc the scanner that scans the input.
   */
  private void processCommand(String userInstruction, Scanner sc) {


    switch (userInstruction) {
      case "blur":
        this.executeCommand(new Blur(), sc);
        break;
      case "sharpen":
        this.executeCommand(new Sharpen(), sc);
        break;
      case "sepia-transform":
        this.executeCommand(new Sepia(), sc);
        break;
      case "luma-transform":
        this.executeCommand(new LumaTransform(), sc);
        break;
      case "brighten":
        int level = sc.nextInt();
        this.executeCommand(new Brighten(level), sc);
        break;
      case "vertical-flip":
        this.executeCommand(new VerticalFlip(), sc);
        break;
      case "horizontal-flip":
        this.executeCommand(new HorizontalFlip(), sc);
        break;
      case "red-component":
        this.executeCommand(new RedComponent(), sc);
        break;
      case "blue-component":
        this.executeCommand(new BlueComponent(), sc);
        break;
      case "green-component":
        this.executeCommand(new GreenComponent(), sc);
        break;
      case "value-component":
        this.executeCommand(new ValueComponent(), sc);
        break;
      case "luma-component":
        this.executeCommand(new LumaComponent(), sc);
        break;
      case "intensity-component":
        this.executeCommand(new IntensityComponent(), sc);
        break;
      case "file":
        String fileName = sc.next();
        StringReader read = new StringReader(this.getFileString(fileName));
        Scanner scan = new Scanner(read);
        this.runFile(scan);
        break;
      case "load":
        try {
          String srcFile = sc.next();
          String newName = sc.next();
          ImageModel mt = new ImageProcessingModel(0, 0, 0);
          new Load(srcFile, newName, this.modelMap).execute(mt);
          writeMessage("success!" + System.lineSeparator());
        } catch (Exception e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "save":
        try {
          String srcFile = sc.next();
          String name = sc.next();
          new Save(srcFile).execute(this.modelMap.get(name));
          writeMessage("success!" + System.lineSeparator());
        } catch (Exception e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "menu": //print the menu of supported instructions
        welcomeMessage();
        break;
      default: //error due to unrecognized instruction
        writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());
    }
  }


  /**
   * used to write a message to the console.
   * @param message the message that will be written.
   * @throws IllegalStateException if the view is sending an IO exception.
   */
  private void writeMessage(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }


  /**
   * print the welcome message of the instructions.
   * @throws IllegalStateException if the view is sending an IO exception.
   */
  private void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the Image program!" + System.lineSeparator());
    printMenu();
  }

  /**
   * print the farewell message.
   * @throws IllegalStateException if the view is sending an IO exception.
   */
  private void farewellMessage() throws IllegalStateException {
    writeMessage("Thank you for using this program!");
  }

  /**
   * print the instructions message.
   * @throws IllegalStateException if the view is sending an IO exception.
   */
  private void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("load 'srcFolder' 'newName' (load an image from the src folder and name it)"
            + System.lineSeparator());
    writeMessage("save 'fileDest' 'name' (save the file with the given 'name' in the fileDest)"
            + System.lineSeparator());
    writeMessage("brighten 'value' 'name' 'newName' (brighten the file 'name' by the value given)"
            + System.lineSeparator());
    writeMessage("red-component 'name' 'newName' (greyscale the file 'name' to the red value)"
            + System.lineSeparator());
    writeMessage("green-component 'name' 'newName' (greyscale the file 'name' to the green value)"
            + System.lineSeparator());
    writeMessage("blue-component 'name' 'newName' (greyscale the file 'name' to the blue value)"
            + System.lineSeparator());
    writeMessage("value-component 'name' 'newName' (greyscale the file 'name' to the value value)"
            + System.lineSeparator());
    writeMessage("luma-component 'name' 'newName' (greyscale the file 'name' to the luma value)"
            + System.lineSeparator());
    writeMessage("intensity-component 'name' 'newName' " +
            "(greyscale the file 'name' to the intensity value)"
            + System.lineSeparator());
    writeMessage("vertical-flip 'name' 'newName' (vertically flip the file 'name')"
            + System.lineSeparator());
    writeMessage("horizontal-flip 'name' 'newName' (horizontally flip the file 'name')"
            + System.lineSeparator());
    writeMessage("sepia-transform 'name' 'newName' (sepia color the file 'name')"
            + System.lineSeparator());
    writeMessage("luma-transform 'name' 'newName' (greyscale the file 'name' to the luma value)"
            + System.lineSeparator());
    writeMessage("blur 'name' 'newName' (blur the file 'name')"
            + System.lineSeparator());
    writeMessage("sharpen 'name' 'newName' (sharpen the file 'name')"
            + System.lineSeparator());
    writeMessage("file 'name' (process the commands in the file 'name')"
            + System.lineSeparator());
    writeMessage("menu (repeat this menu)"
            + System.lineSeparator());
  }

  /**
   * to change a model with the given command based on the used input via the scanner.
   * @param command the command that will be performed.
   * @param sc the scanner that takes in user input.
   */
  private void executeCommand(Command command, Scanner sc) {
    try {
      String s1 = sc.next();
      String s2 = sc.next();
      ImageModel modelCopy = this.modelMap.get(s1).deepCopy();
      command.execute(modelCopy);
      this.modelMap.put(s2, modelCopy);
      if (this.modelMap.containsKey(s2)) {
        this.modelMap.replace(s2, modelCopy);
      } else {
        this.modelMap.put(s2, modelCopy);
      }
      writeMessage("success!" + System.lineSeparator());
    } catch (Exception e) {
      writeMessage("Error: " + e.getMessage() + System.lineSeparator());
    }
  }

  /**
   * used to get the string of a given file.
   * @param fileName the file that will be used.
   * @return the file contents in the form of a string.
   */
  private String getFileString(String fileName) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File " + fileName + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      builder.append(s);
    }
    return builder.toString();
  }


  /**
   * used to run and get the contents of a scanner.
   * @param sc the scanner that has the user inputs.
   */
  private void runFile(Scanner sc) {
    boolean quit = false;
    while (!quit && sc.hasNext()) {
      //writeMessage("Type instruction: "); //prompt for the instruction name
      String userInstruction = sc.next(); //take an instruction name
      if (userInstruction.equals("quit") || userInstruction.equals("q")) {
        quit = true;
      } else {
        processCommand(userInstruction, sc);
      }
    }
  }

}
