package cs3500.imageprocessing.controller.command;

import cs3500.imageprocessing.model.ImageModel;

/**
 * The interface to represent any command that can be preformed on the model.
 * This interface has only one command as it is a part of the Command Design Process.
 */
public interface Command {

  /**
   * This method will preform the command on the given model.
   * @param model The model the command is going to mutate.
   * @throws IllegalStateException If the provided model is null, in some cases (read Java-Docs)
   *                               Other reasons will apply for the exception.
   */
  void execute(ImageModel model) throws IllegalStateException;
}

