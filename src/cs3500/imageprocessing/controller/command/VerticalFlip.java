package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;
import cs3500.imageprocessing.model.Pixel;

/**
 * this class will make modify the model to be a vertically flipped variant of itself.
 * This means that every pixel's y positions will flip in accordance to the width.
 * The x position however will remain the same.
 */
public class VerticalFlip extends ACommandAbstract {

  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);

    ImageModel modelCopy = model.deepCopy();
    for (int i = 0; i < model.getImageHeight(); i++) {
      for (int j = 0; j < model.getImageWidth(); j++) {
        Pixel pixelAt = modelCopy.getPixelAt(j, i);
        Pixel newPixel = new Pixel(pixelAt.getRed(), pixelAt.getGreen(), pixelAt.getBlue());
        model.setPixel(newPixel, j, model.getImageHeight() - i - 1);
      }
    }
  }
}