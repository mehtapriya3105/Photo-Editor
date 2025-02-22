package mock;

import controller.Features;

import java.awt.image.BufferedImage;

import view.IView;

/**
 * A mock implementation of the IView interface for testing purposes. Simulates user interactions
 * and display behaviors.
 */
public class MockView implements IView {

  private final StringBuilder log;
  private String uploadPath;
  private String savePath;

  /**
   * Constructor for MockView.
   *
   * @param log is the log to be shown.
   */
  public MockView(StringBuilder log) {
    this.log = log;
  }

  /**
   * Adds features to the view by linking it with the controller's functionality. This allows the
   * view to respond to user actions by invoking the appropriate controller methods.
   *
   * @param features the controller's feature set to be added to the view.
   */
  @Override
  public void addFeatures(Features features) {
    log.append("addFeatures called.\n");
  }

  /**
   * Prompts the user to upload an image file and returns the selected file's path. Displays a file
   * chooser dialog to allow the user to select an image file.
   *
   * @return the absolute path of the selected image file, or null if no file is selected.
   */
  @Override
  public String uploadImagePath() {
    log.append("uploadImagePath called.\n");
    return uploadPath; // Simulate user-provided upload path
  }

  /**
   * Prompts the user to save the currently processed image and returns the chosen file path.
   * Displays a save dialog to allow the user to specify the save location and file name.
   *
   * @return the absolute path of file where image is to be saved, or null if no file is selected.
   */
  @Override
  public String saveImagePath() {
    log.append("saveImagePath called.\n");
    return savePath; // Simulate user-provided save path
  }

  /**
   * Displays the specified image in the view along with its name. Updates the view to show the
   * image and clears any previous image display.
   *
   * @param imageName the name of the image to be displayed.
   * @param img       represents the image to be displayed.
   */
  @Override
  public void displayImg(String imageName, BufferedImage img) {
    log.append("displayImg called with imageName: ").append(imageName).append("\n");
    if (img != null) {
      log.append("Image dimensions: ").append(img.getWidth()).append("x").append(img.getHeight())
              .append("\n");
    } else {
      log.append("Image is null.\n");
    }
  }

  /**
   * Displays a histogram image in the view. Updates the histogram display panel with the given
   * image.
   *
   * @param img represents the histogram to be displayed.
   */
  @Override
  public void displayHisto(BufferedImage img) {
    log.append("displayHisto called.\n");
    if (img != null) {
      log.append("Histogram dimensions: ").append(img.getWidth()).append("x")
              .append(img.getHeight()).append("\n");
    } else {
      log.append("Histogram is null.\n");
    }
  }

  /**
   * Handles the action of splitting an image. Simulates user interaction by logging the call
   * and displaying the dimensions of the provided image or indicating if the image is null.
   *
   * @param img represents the image to be split.
   */
  @Override
  public void handleSplitAction(BufferedImage img) {
    System.out.println("handleSplitAction called.\n");
    log.append("handleSplitAction called.\n");
    if (img != null) {
      log.append("Split image dimensions: ").append(img.getWidth()).append("x")
              .append(img.getHeight()).append("\n");
    } else {
      log.append("Split image is null.\n");
    }
  }

  /**
   * Handles the level adjustment action for an image by updating the display with modified image.
   *
   * @param img The updated image to be displayed in the popup.
   */
  @Override
  public void handleSplitActionLevel(BufferedImage img) {
    log.append("handleSplitActionLevel called.\n");
    if (img != null) {
      log.append("Split level image dimensions: ").append(img.getWidth()).append("x")
              .append(img.getHeight()).append("\n");
    } else {
      log.append("Split level image is null.\n");
    }
  }

  /**
   * Sets the simulated upload path for testing.
   *
   * @param uploadPath the path to simulate user-selected upload file.
   */
  public void setUploadPath(String uploadPath) {
    this.uploadPath = uploadPath;
  }

  /**
   * Sets the simulated save path for testing.
   *
   * @param savePath the path to simulate user-specified save file.
   */
  public void setSavePath(String savePath) {
    this.savePath = savePath;
  }

  /**
   * Retrieves the log of actions performed or simulated by the mock view.
   *
   * @return StringBuilder containing the log of method calls and parameters.
   */
  public StringBuilder getlog() {
    return log;
  }
}
