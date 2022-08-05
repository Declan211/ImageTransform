package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;

/**
 * function object for converting an image to sepia.x
 */
public class Sepia extends ACommandAbstract {

  @Override
  public void execute(ImageModel model) throws IllegalStateException {
    this.modelNullCheck(model);


    double[][] array = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };

    this.colorTransformation(model, array);
  }
}

