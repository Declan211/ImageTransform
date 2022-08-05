package cs3500.imageprocessing.view;

import java.awt.*;

import javax.swing.*;

import cs3500.imageprocessing.model.ImageModelState;

public class Histo extends JLabel {

  ImageModelState model;

  Histo(ImageModelState model) {
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D graphics2D = (Graphics2D) g.create();

    Integer[][] values = model.getHistogramValues();
    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 4; j++) {

        int y = (int) Math.floor(values[i][j] / 100.0);
        y = Math.max(200 - y, 0);
        switch(j) {
          case 0:
            graphics2D.setColor(new Color(255, 0, 0, 100));
            break;
          case 1:
            graphics2D.setColor(new Color(0, 255, 0, 100));
            break;
          case 2:
            graphics2D.setColor(new Color(0, 0, 255, 100));
            break;
          case 3:
            graphics2D.setColor(new Color(150, 150, 150, 100));
            break;
        }
        graphics2D.drawLine(i, 200, i, y);

      }
    }

    // setpaint = set the color
    // draw lines
    // alpha composite
    //set composite
  }

  public Dimension getPreferredSize() {
    return new Dimension(500, 200);
  }
}
