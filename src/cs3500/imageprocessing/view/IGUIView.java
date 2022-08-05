package cs3500.imageprocessing.view;

import java.awt.event.ActionListener;
import java.io.File;

import cs3500.imageprocessing.model.ImageModelState;

public interface IGUIView extends ImageView {
  void setListener(ActionListener listener);

  File openFile();

  void setModel(ImageModelState model);
  File saveFile();

  }
