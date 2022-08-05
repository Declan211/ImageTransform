package cs3500.imageprocessing.controller.command;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * This class will save a model to your computer in the format of ppm P3.
 */
public class Save extends ACommandAbstract {

  private final String fileDest;

  /**
   * this will construct a Save Command instance with the given file destination.
   * @param fileDest The destination you will have the file sent to on your computer.
   */
  public Save(String fileDest) {
    this.fileDest = fileDest;
  }

  /**
   * Execute will save the given model at the file destination.
   * @param model The model the command is going to save.
   * @throws IllegalStateException if the file destination is invalid, if the file already exists,
   *                               if the file cannot be created, if the provided model is null.
   */
  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);
    String type = this.getType(this.fileDest);

    if (type.equals("ppm")) {
      this.ppmSave(model);
    } else {
      this.otherSave(model, type);
    }


  }

  /**
   * will save specially a ppm file.
   * @param model the model that will be saved as a ppm.
   */
  private void ppmSave(ImageModel model) {

    File f = new File(this.fileDest);

    boolean result;
    try {
      result = f.createNewFile();  //creates a new file
      if (!result) {     // test if successfully created a new file
        throw new IllegalStateException("File already exist at location: " + f.getCanonicalPath());
      }
    } catch (IOException e) {
      throw new IllegalStateException("File was not created.");
    }

    StringBuilder sb = new StringBuilder();
    sb.append("P3\n");
    sb.append(Integer.toString(model.getImageWidth()) + " ");
    sb.append(Integer.toString(model.getImageHeight()) + "\n");
    sb.append(Integer.toString(model.getMaxColorValue()) + "\n");

    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        Pixel current = model.getPixelAt(j, i);
        sb.append(Integer.toString(current.getRed()) + "\n");
        sb.append(Integer.toString(current.getGreen()) + "\n");
        sb.append(Integer.toString(current.getBlue()) + "\n");
      }
    }

    FileOutputStream fos;
    try {
      fos = new FileOutputStream(this.fileDest, true);
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File not found");
    }
    byte[] b = sb.toString().getBytes();       //converts string into bytes

    try {
      fos.write(b);           //writes bytes into file
    } catch (IOException e) {
      throw new IllegalStateException("could not write to file");
    }

    try {
      fos.close();           //writes bytes into file
    } catch (IOException e) {
      throw new IllegalStateException("could not close file");
    }            //close the file
  }


  /**
   * will save a png, bmp, or jpg file.
   * @param model the model that will be saved.
   * @param type the type of file it is.
   */
  private void otherSave(ImageModel model, String type) {

    int width = model.getImageWidth();
    int height = model.getImageHeight();
    BufferedImage img;

    switch (type) {
      case "png":
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        break;
      case "jpg":
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        break;
      case "bmp":
        img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        break;
      default:
        throw new IllegalStateException("file save type not supported, must be ppm, png, jpg, bmp");
    }


    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        Pixel current = model.getPixelAt(j, i);
        int red = current.getRed();
        int green = current.getGreen();
        int blue = current.getBlue();
        int alpha = current.getAlpha();
        img.setRGB(j, i, new Color(red, green, blue, alpha).getRGB());
      }
    }

    File file = new File(this.fileDest);
    try {
      ImageIO.write(img, type, file);
    } catch (IOException e) {
      throw new IllegalStateException("could not save file");
    }

  }
}
