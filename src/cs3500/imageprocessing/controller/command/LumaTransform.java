package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;

/**
 * function object to preform the luma-greyscale operation on an image.
 */
public class LumaTransform extends ACommandAbstract {
  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);


    double[][] array = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };

    this.colorTransformation(model, array);
  }
}
