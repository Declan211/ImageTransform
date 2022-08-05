package cs3500.imageprocessing.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import cs3500.imageprocessing.model.ImageModelState;

import cs3500.imageprocessing.model.Pixel;

public class GraphicalView extends JFrame implements IGUIView {

  private Histo histogram;
  private ImageModelState model;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;
  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private JButton brightenButton;
  private JButton darkenButton;
  private JButton redButton;
  private JButton greenButton;
  private JButton blueButton;
  private JButton lumaButton;
  private JButton sepiaButton;
  private JButton intensityButton;
  private JButton valueButton;
  private JButton horFlipButton;
  private JButton vertFlipButton;
  private JButton blurButton;
  private JButton sharpenButton;

  private BufferedImage img;
  private JScrollPane lastScrollPane;
  private JPanel lastHistogram;
  private JPanel picturePanel;



  public GraphicalView() throws IOException {
    super();
    setTitle("Swing features");
    setSize(1280, 720);


    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Buttons"));
    dialogBoxesPanel.setLayout(new GridLayout(0, 4, 0, 0));
    mainPanel.add(dialogBoxesPanel);


    //file open
    JPanel fileopenPanel = new JPanel();
    dialogBoxesPanel.add(fileopenPanel);
    this.fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);


    //file save
    JPanel filesavePanel = new JPanel();
    dialogBoxesPanel.add(filesavePanel);
    this.fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    //model brighten
    JPanel brighten = new JPanel();
    dialogBoxesPanel.add(brighten);
    this.brightenButton = new JButton("brighten");
    brightenButton.setActionCommand("brighten");
    brighten.add(brightenButton);

    //model darken
    JPanel darken = new JPanel();
    dialogBoxesPanel.add(darken);
    this.darkenButton = new JButton("darken");
    darkenButton.setActionCommand("darken");
    darken.add(darkenButton);

    //model blur
    JPanel blur = new JPanel();
    dialogBoxesPanel.add(blur);
    this.blurButton = new JButton("blur");
    blurButton.setActionCommand("blur");
    blur.add(blurButton);

    //model sharpen
    JPanel sharpen = new JPanel();
    dialogBoxesPanel.add(sharpen);
    this.sharpenButton = new JButton("sharpen");
    sharpenButton.setActionCommand("sharpen");
    sharpen.add(sharpenButton);

    //model sepia
    JPanel sepia = new JPanel();
    dialogBoxesPanel.add(sepia);
    this.sepiaButton = new JButton("sepia");
    sepiaButton.setActionCommand("sepia");
    sepia.add(sepiaButton);

    //model luma
    JPanel luma = new JPanel();
    dialogBoxesPanel.add(luma);
    this.lumaButton = new JButton("luma-greyscale");
    lumaButton.setActionCommand("luma-component");
    luma.add(lumaButton);

    //model intensity
    JPanel intensity = new JPanel();
    dialogBoxesPanel.add(intensity);
    this.intensityButton = new JButton("intensity-greyscale");
    intensityButton.setActionCommand("intensity-component");
    intensity.add(intensityButton);

    //model value-greyscale
    JPanel value = new JPanel();
    dialogBoxesPanel.add(value);
    this.valueButton = new JButton("value-greyscale");
    valueButton.setActionCommand("value-component");
    value.add(valueButton);

    //model vertFlip
    JPanel vertFlip = new JPanel();
    dialogBoxesPanel.add(vertFlip);
    this.vertFlipButton = new JButton("vertical-flip");
    vertFlipButton.setActionCommand("vertical-flip");
    vertFlip.add(vertFlipButton);

    //model horizontal-flip
    JPanel horizontalFlip = new JPanel();
    dialogBoxesPanel.add(horizontalFlip);
    this.horFlipButton = new JButton("horizontal-flip");
    horFlipButton.setActionCommand("horizontal-flip");
    horizontalFlip.add(horFlipButton);

    //model red-greyscale
    JPanel red = new JPanel();
    dialogBoxesPanel.add(red);
    this.redButton = new JButton("red-greyscale");
    redButton.setActionCommand("red-component");
    red.add(redButton);

    //model green-greyscale
    JPanel green = new JPanel();
    dialogBoxesPanel.add(green);
    this.greenButton = new JButton("green-greyscale");
    greenButton.setActionCommand("green-component");
    green.add(greenButton);

    //model blue-greyscale
    JPanel blue = new JPanel();
    dialogBoxesPanel.add(blue);
    this.blueButton = new JButton("blue-greyscale");
    blueButton.setActionCommand("blue-component");
    blue.add(blueButton);

    this.lastScrollPane = new JScrollPane(new JLabel(new ImageIcon()));
    this.lastHistogram = new JPanel();
    picturePanel = new JPanel();


    this.pack();
  }

  public void setListener(ActionListener listener) {
    fileOpenButton.addActionListener(listener);
    fileSaveButton.addActionListener(listener);
    brightenButton.addActionListener(listener);
    darkenButton.addActionListener(listener);
    blurButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    horFlipButton.addActionListener(listener);
    vertFlipButton.addActionListener(listener);
    redButton.addActionListener(listener);
    greenButton.addActionListener(listener);
    blueButton.addActionListener(listener);
    lumaButton.addActionListener(listener);
    intensityButton.addActionListener(listener);
    valueButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);


  }


  @Override
  public File openFile() {

    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(GraphicalView.this);
    File f = null;
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      f = fchooser.getSelectedFile();
      fileOpenDisplay.setText(f.getAbsolutePath());
    }
    return f;
  }

  public File saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(GraphicalView.this);
    File f = null;
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      f = fchooser.getSelectedFile();
      fileSaveDisplay.setText(f.getAbsolutePath());
    }
    return f;
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(GraphicalView.this, message, "Something went wrong", JOptionPane.WARNING_MESSAGE);
  }



  public void setModel(ImageModelState model) {
    this.model = model;
    this.setImg();
    JLabel picLabel = new JLabel(new ImageIcon(this.img));
    JScrollPane scrollPane = new JScrollPane(picLabel);
    picturePanel.remove(this.lastScrollPane);
//    mainPanel.add(scrollPane);
    this.revalidate();
    this.lastScrollPane = scrollPane;

    this.histogram = new Histo(this.model);
    JPanel histogramPanel = new JPanel();
    histogramPanel.add(this.histogram);
    histogramPanel.repaint();
    picturePanel.remove(this.lastHistogram);
//    mainPanel.add(histogramPanel, BorderLayout.EAST);
    this.revalidate();
    this.lastHistogram = histogramPanel;

    picturePanel.setLayout(new BoxLayout(picturePanel, BoxLayout.X_AXIS));
    picturePanel.add(scrollPane);
    picturePanel.add(histogramPanel);
    mainPanel.add(picturePanel);
    setVisible(true);
  }


  public void setImg() {
    BufferedImage img = new BufferedImage(model.getImageWidth(), model.getImageHeight(), BufferedImage.TYPE_INT_RGB);

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
    this.img = img;
  }


}
