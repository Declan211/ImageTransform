package cs3500.imageprocessing.controller.load;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import cs3500.imageprocessing.controller.command.ACommandAbstract;
import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.ImageProcessingModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * this class will find a ppm file from the fileName field and load that file as a PPMImageModel.
 * This is done by reading the values from the file to extract the width, height, maxValue,
 * and pixel colors.
 * The model that is created will be put into the given map with the destName as the key.
 * The execute command will take in a PPMImageModel as the signature of the method but does not
 * use this model in the actual method.
 */
public class Load extends ACommandAbstract {

  private final String fileName;
  private final String destName;
  private final Map<String, ImageModel> map;


  /**
   * Create a new instance of the Load command to load a file into a model.
   *
   * @param fileName the location of where the file is located.
   * @param destName the name you will attach to this model.
   * @param map      the existing map that will have the destName, model put into.
   */
  public Load(String fileName, String destName, Map<String, ImageModel> map) {
    this.destName = destName;
    this.fileName = fileName;
    this.map = map;
  }

  /**
   * Execute will find the fileName file and load it into a model and then put it into the map.
   *
   * @param model A model that is a part of the method signature but not actually used in Load.
   * @throws IllegalStateException if the provided model is null, if the file cannot be found,
   *                               if the ppm file is bad and does not accurately fulfill ppm P3
   *                               requirements.
   *                               PPM P3 requirements: Must start with P3, Comments are started
   *                               with a '#' char, second input is width, third is height, fourth
   *                               is max value for pixel colors, then finally there are 3 pixel
   *                               color values for each pixel.
   */
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);
    String type = this.getType(this.fileName);

    ImageModel newModel;
    if (type.equals("ppm")) {
      newModel = this.ppmLoad();
    } else if (type.equals("png") || type.equals("jpg") || type.equals("bmp")) {
      newModel = this.otherLoad();
    } else {
      throw new IllegalStateException("file type must be ppm, png, jpg, or bmp");
    }

    map.put(this.destName, newModel);

  }


  private ImageModel ppmLoad() {

    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(this.fileName));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File " + this.fileName + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    try {
      token = sc.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("ran out of inputs, bad file.");
    }

    if (!token.equals("P3")) {
      throw new IllegalStateException("File must start with P3");
    }
    int width;
    int height;
    int maxValue;
    try {
      width = sc.nextInt();
      height = sc.nextInt();
      maxValue = sc.nextInt();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("ran out of inputs, bad file.");
    }


    ImageModel newModel = new ImageProcessingModel(width, height, maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r;
        int g;
        int b;
        try {
          r = sc.nextInt();
          g = sc.nextInt();
          b = sc.nextInt();
        } catch (NoSuchElementException e) {
          throw new IllegalStateException("ran out of inputs, bad file.");
        }
        if (r > maxValue || g > maxValue || b > maxValue) {
          throw new IllegalStateException("pixel color values cannot " +
                  "be greater than the max value");
        }
        Pixel pixel = new Pixel(r, g, b);
        newModel.setPixel(pixel, j, i);

      }
    }
    return newModel;
  }


  private ImageModel otherLoad() {

    BufferedImage img;
    try {
      img = ImageIO.read(new File(this.fileName));
    } catch (IOException e) {
      throw new IllegalStateException("file not found");
    }

    ImageModel imageModel = new ImageProcessingModel(img.getWidth(), img.getHeight(), 255);

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {

        int color = img.getRGB(j, i);

        int blue = color & 0xff;
        int green = (color & 0xff00) >> 8;
        int red = (color & 0xff0000) >> 16;
        int alpha = (color & 0xff000000) >>> 24;
        Pixel pixel = new Pixel(red, green, blue, alpha);
        imageModel.setPixel(pixel, j, i);
      }
    }
    return imageModel;
  }

}
