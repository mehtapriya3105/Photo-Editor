package controller;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.IImageModel;
import model.commands.ModelCommandHandler;
import model.database.Image;
import model.database.Pixel;
import view.IView;

/**
 * A GUI controller for handling user interactions in an image processing application.
 * Implements the Features interface to define various image processing actions.
 */
public class GuiController implements Features {

  private final CommandMappernew factory;
  private ModelCommandHandler model;
  private IView view;
  private String imageName;
  private String originalFilePath;
  private IImageModel imageModel;

  /**
   * Constructor to initialize the GUI controller with an image model.
   *
   * @param m to use for image operations.
   */
  public GuiController(IImageModel m) {
    this.factory = new CommandMappernew(m);
    this.imageModel = m;
    imageName = "";
  }

  /**
   * Maps a given command string to a specific image processing operation.
   *
   * @param command the command string to be processed and executed.
   */
  protected void commandMap(String command) {
    try {
      CommandParser.ParsedCommand parsedCommand = CommandParser.parse(command);

      if (parsedCommand != null) {
        factory.routeCommand(parsedCommand.getCommandName(), parsedCommand.getArguments());
      }
    } catch (IOException e) {
      System.err.println("Error reading input: " + e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * Converts an image identified by its name to a {@link BufferedImage}.
   *
   * @param image the name of the image to be converted.
   * @return the corresponding {@link BufferedImage}.
   * @throws FileNotFoundException if the specified image is not found.
   */
  private BufferedImage convertToBufferImage(String image) throws FileNotFoundException {
    Image cm = imageModel.getImage(image);
    if (cm == null) {
      throw new FileNotFoundException("Image not found");
    }
    BufferedImage bf = new BufferedImage(cm.getWidth(), cm.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < cm.getWidth(); i++) {
      for (int j = 0; j < cm.getHeight(); j++) {
        Pixel rgb = cm.getPixel(i, j);
        bf.setRGB(i, j,
                ((int) rgb.getRed() << 16) | ((int) rgb.getGreen() << 8)
                        | (int) rgb.getBlue());
      }
    }
    return bf;
  }

  /**
   * Sets the view for this controller and links it to the features.
   *
   * @param v the {@link IView} to be linked to this controller.
   */
  public void setView(IView v) {
    view = v;
    view.addFeatures(this);
  }

  /**
   * Generates and returns a histogram for the current image.
   *
   * @return the histogram as a {@link BufferedImage}.
   * @throws FileNotFoundException if the image is not found.
   */
  private BufferedImage getHistogram() throws FileNotFoundException {
    String command = "histogram " + imageName + " " + imageName + "histogram";
    this.commandMap(command);
    return this.convertToBufferImage(imageName + "histogram");
  }

  /**
   * Displays the image in a popup window by converting the given image name to a BufferedImage
   * and then passing it to the view to handle the split action.
   *
   * @param tempImageName The name of the image file to be displayed in the popup.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  private void showImageInPopup(String tempImageName) throws FileNotFoundException {
    BufferedImage img = this.convertToBufferImage(tempImageName);
    view.handleSplitAction(img);
  }

  /**
   * Displays the image in a popup window for level adjustments by converting the given image name
   * to a BufferedImage and then passing it to the view to handle the level adjustment action.
   *
   * @param tempImageName The name of the image file to be displayed in popup for level adjustments.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  private void showImageInPopupLevel(String tempImageName) throws FileNotFoundException {
    BufferedImage img = this.convertToBufferImage(tempImageName);
    view.handleSplitActionLevel(img);
  }

  /**
   * Displays the current image and its histogram in the main view.
   *
   * @throws FileNotFoundException if the image is not found.
   */
  private void showImage() throws FileNotFoundException {
    BufferedImage img = this.convertToBufferImage(imageName);
    view.displayImg(imageName, img);

    BufferedImage histo = this.getHistogram();
    view.displayHisto(histo);
  }

  /**
   * Loads an image into the application by retrieving the file path from the view.
   * The image is stored with a unique name derived from the file name.
   * If this is the first image loaded, it sets the original file path.
   *
   * @throws FileNotFoundException if the specified file cannot be found.
   */
  @Override
  public void loadImage() throws FileNotFoundException {
    String filepath = view.uploadImagePath();
    if (filepath == null) {
      return;
    }
    this.imageName = filepath.substring(0, filepath.lastIndexOf('.'));
    String command = "load " + filepath + " " + imageName;
    this.commandMap(command);
    this.showImage();

    if (originalFilePath == null) {
      this.originalFilePath = imageName;
    }
  }

  /**
   * Saves the currently loaded image to a user-specified file path.
   * Prints a confirmation message upon successful save. Returns null or filepath.
   */
  @Override
  public String saveImage() {
    String filepath = view.saveImagePath();
    if (filepath == null) {
      return " ";
    }
    String command = "save " + filepath + " " + imageName;
    this.commandMap(command);
    System.out.println("Image saved successfully to " + filepath);
    return filepath;
  }


  /**
   * Displays a preview of an image transformation (e.g., blur, luma, etc.) with split-screen view
   * applying a specified percentage of the effect.
   *
   * @param command    the command to execute the transformation.
   * @param percentage the intensity of the transformation effect.
   * @param action     a string identifier for the transformation action.
   * @throws FileNotFoundException if the image cannot be found.
   */
  @Override
  public void splitPreview(String command, Integer percentage, String action)
          throws FileNotFoundException {
    String newCommand = command + "split" + " " + "split" + " " + percentage;
    if (percentage < 0 || percentage > 100) {
      System.out.println("Invalid percentage");
      return;
    }
    this.commandMap(newCommand);
    this.imageName = imageName + action + "split";
    if (action.equals("leveladjust")) {
      this.showImageInPopupLevel(imageName);
    } else {
      this.showImageInPopup(imageName);
    }
  }

  /**
   * Applies a blur effect to the image. If `splitBool` is true, previews effect with a split view.
   *
   * @param percentage the intensity of the blur effect.
   * @param splitBool  whether to show a split view of the transformation.
   * @throws FileNotFoundException if the image cannot be found.
   */
  @Override
  public void getBlur(int percentage, boolean splitBool)
          throws FileNotFoundException {
    String command = "blur " + imageName + " " + imageName + "blur";
    if (splitBool) {
      String img = imageName;
      this.splitPreview(command, percentage, "blur");
      this.imageName = img;
    } else {
      this.commandMap(command);
      this.imageName = imageName + "blur";
      this.showImage();
    }
  }

  /**
   * Applies luma component effect to image.If `splitBool` is true, previews effect with split view.
   *
   * @param percentage the intensity of the luma effect.
   * @param splitBool  whether to show a split view of the transformation.
   * @throws FileNotFoundException if the image cannot be found.
   */
  @Override
  public void getLuma(int percentage, boolean splitBool)
          throws FileNotFoundException {
    String command = "luma-component " + imageName + " " + imageName + "luma";
    if (splitBool) {
      String img = imageName;
      this.splitPreview(command, percentage, "luma");
      this.imageName = img;
    } else {
      this.commandMap(command);
      this.imageName = imageName + "luma";
      this.showImage();
    }
  }

  /**
   * Applies a sepia filter to the image with the given percentage and displays it.
   * If splitBool is true, it shows a preview of the sepia effect; otherwise, it applies and
   * shows the effect.
   *
   * @param percentage The strength of the sepia effect (0 to 100).
   * @param splitBool  If true, shows a preview before applying the effect; false applies the
   *                   effect directly.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getSepia(int percentage, boolean splitBool) throws FileNotFoundException {
    String command = "sepia " + imageName + " " + imageName + "sepia";
    if (splitBool) {
      String img = imageName;
      this.splitPreview(command, percentage, "sepia");
      this.imageName = img;
    } else {
      this.commandMap(command);
      this.imageName = imageName + "sepia";
      this.showImage();
    }
  }

  /**
   * Applies a sharpen effect to the image with the given percentage and displays it.
   * If splitBool is true, it shows a preview of the sharpen effect; otherwise, it applies and
   * shows the effect.
   *
   * @param percentage The strength of the sharpen effect (0 to 100).
   * @param splitBool  If true, shows a preview before applying the effect; false applies the
   *                   effect directly.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getSharpen(int percentage, boolean splitBool) throws FileNotFoundException {
    String command = "sharpen " + imageName + " " + imageName + "sharpen";
    if (splitBool) {
      String img = imageName;
      this.splitPreview(command, percentage, "sharpen");
      this.imageName = img;
    } else {
      this.commandMap(command);
      this.imageName = imageName + "sharpen";
      this.showImage();
    }
  }

  /**
   * Flips the image horizontally and displays the result.
   *
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getFlipHorizontal() throws FileNotFoundException {
    String command = "horizontal-flip " + imageName + " " + imageName + "hflip";
    this.commandMap(command);
    this.imageName = imageName + "hflip";
    this.showImage();
  }

  /**
   * Flips the image vertically and displays the result.
   *
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getFlipVertical() throws FileNotFoundException {
    String command = "vertical-flip " + imageName + " " + imageName + "vflip";
    this.commandMap(command);
    this.imageName = imageName + "vflip";
    this.showImage();
  }

  /**
   * Adjusts the color of the image based on the given percentage and displays it.
   * If splitBool is true, it shows a preview of the color correction; otherwise, it applies and
   * shows the effect.
   *
   * @param percentage The strength of the color correction effect (0 to 100).
   * @param splitBool  If true, shows a preview before applying the effect; false applies the
   *                   effect directly.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getColorCorrect(int percentage, boolean splitBool) throws FileNotFoundException {
    String command = "color-correct " + imageName + " " + imageName + "color-correct";
    if (splitBool) {
      String img = imageName;
      this.splitPreview(command, percentage, "color-correct");
      this.imageName = img;
    } else {
      this.commandMap(command);
      this.imageName = imageName + "color-correct";
      this.showImage();
    }
  }

  /**
   * Adjusts the levels of the image based on the provided values for brightness, midtone, and
   * white balance, and displays it.
   * If splitBool is true, it shows a preview of the level adjustment; otherwise, it applies
   * and shows the effect.
   *
   * @param v1         The value for the black point (should be less than or equal to v2 and v3).
   * @param v2         The value for the midtone (should be between v1 and v3).
   * @param v3         The value for the white point (should be greater than or equal to v2).
   * @param percentage The strength of the level adjustment effect (0 to 100).
   * @param splitBool  If true, shows a preview before applying the effect; false applies the
   *                   effect directly.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getLevelAdjust(int v1, int v2, int v3, int percentage, boolean splitBool)
          throws FileNotFoundException {
    String command = "level-adjust " + v1 + " " + v2 + " " + v3 + " " + imageName + " " + imageName
            + "leveladjust";
    if (v1 > v2 || v1 > v3 || v2 > v3) {
      System.out.println("Invalid B,M,W values");
      return;
    }
    if (splitBool) {
      String img = imageName;
      this.splitPreview(command, percentage, "leveladjust");
      this.imageName = img;
    } else {
      this.commandMap(command);
      this.imageName = imageName + "leveladjust";
      this.showImage();
    }
  }

  /**
   * Extracts the red component of the image and displays it.
   * If splitBool is true, it shows a preview of the red component; otherwise, it applies and shows
   * the effect.
   *
   * @param percentage The strength of the red component effect (0 to 100).
   * @param splitBool  If true, shows a preview before applying the effect; false applies the effect
   *                   directly.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getRedComponent(int percentage, boolean splitBool) throws FileNotFoundException {
    String command = "red-component " + imageName + " " + imageName + "red";
    if (splitBool) {
      String img = imageName;
      this.splitPreview(command, percentage, "red");
      this.imageName = img;
    } else {
      this.commandMap(command);
      this.imageName = imageName + "red";
      this.showImage();
    }
  }

  /**
   * Extracts the green component of the image and displays it.
   * If splitBool is true, it shows a preview of the green component; otherwise, it applies and
   * shows the effect.
   *
   * @param percentage The strength of the green component effect (0 to 100).
   * @param splitBool  If true, shows a preview before applying the effect; false applies the
   *                   effect directly.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getGreenComponent(int percentage, boolean splitBool) throws FileNotFoundException {
    String command = "green-component " + imageName + " " + imageName + "green";
    if (splitBool) {
      String img = imageName;
      this.splitPreview(command, percentage, "green");
      this.imageName = img;
    } else {
      this.commandMap(command);
      this.imageName = imageName + "green";
      this.showImage();
    }
  }

  /**
   * Extracts the blue component of the image and displays it.
   * If splitBool is true, it shows a preview of the blue component; otherwise, it applies and shows
   * the effect.
   *
   * @param percentage The strength of the blue component effect (0 to 100).
   * @param splitBool  If true, shows a preview before applying the effect; false applies the effect
   *                   directly.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getBlueComponent(int percentage, boolean splitBool) throws FileNotFoundException {
    String command = "blue-component " + imageName + " " + imageName + "blue";
    if (splitBool) {
      String img = imageName;
      this.splitPreview(command, percentage, "blue");
      this.imageName = img;
    } else {
      this.commandMap(command);
      this.imageName = imageName + "blue";
      this.showImage();
    }
  }

  /**
   * Compresses the image to the specified compression value and displays the result.
   *
   * @param compressVal The compression value (positive integer).
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getCompress(int compressVal) throws FileNotFoundException {
    if (compressVal < 0) {
      System.out.println("Invalid compress value");
      return;
    }
    String command = "compress " + compressVal + " " + imageName + " " + imageName + "compress";
    this.commandMap(command);
    this.imageName = imageName + "compress";
    this.showImage();
  }

  /**
   * Downscales the image to the specified width and height and displays the result.
   *
   * @param newWidth  The new width of the image.
   * @param newHeight The new height of the image.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void downScale(String newWidth, String newHeight) throws FileNotFoundException {
    if (Integer.parseInt(newHeight) < 0) {
      System.out.println("Invalid height value");
      return;
    }
    if (Integer.parseInt(newWidth) < 0) {
      System.out.println("Invalid width value");
      return;
    }
    imageModel.imageDownScalling(imageName, imageName
                    + "downscale", Integer.parseInt(newHeight),
            Integer.parseInt(newWidth));
    this.imageName = imageName + "downscale";
    this.showImage();
  }

  /**
   * Adjusts the brightness of the image and displays the result.
   *
   * @param bright The brightness level to be applied.
   * @throws FileNotFoundException If the image file cannot be found or read.
   */
  @Override
  public void getBright(String bright) throws FileNotFoundException {
    String command = "brighten " + bright + " " + imageName + " " + imageName + "bright";
    this.commandMap(command);
    this.imageName = imageName + "bright";
    this.showImage();
  }


}

