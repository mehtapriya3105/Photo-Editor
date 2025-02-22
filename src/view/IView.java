package view;

import controller.Features;

import java.awt.image.BufferedImage;

/**
 * Interface representing the view in the MVC architecture for an image processing application. This
 * interface provides methods for user interaction and display functionalities.
 */
public interface IView {

  /**
   * Adds features to the view by linking it with the controller's functionality. This allows the
   * view to respond to user actions by invoking the appropriate controller methods.
   *
   * @param features the controller's feature set to be added to the view.
   */
  void addFeatures(Features features);

  /**
   * Prompts the user to upload an image file and returns the selected file's path. Displays a file
   * chooser dialog to allow the user to select an image file.
   *
   * @return the absolute path of the selected image file, or {@code null} if no file is selected.
   */
  String uploadImagePath();

  /**
   * Prompts the user to save the currently processed image and returns the chosen file path.
   * Displays a save dialog to allow the user to specify the save location and file name.
   *
   * @return the absolute path of file where image is to be saved, or null if no file is selected.
   */
  String saveImagePath();

  /**
   * Displays the specified image in the view along with its name. Updates the view to show the
   * image and clears any previous image display.
   *
   * @param imageName the name of the image to be displayed.
   * @param img       represents the image to be displayed.
   */
  void displayImg(String imageName, BufferedImage img);

  /**
   * Displays a histogram image in the view. Updates the histogram display panel with the given
   * image.
   *
   * @param img represents the histogram to be displayed.
   */
  void displayHisto(BufferedImage img);

  /**
   * Handles the split action for an image by updating the display with the modified image.
   *
   * @param img The updated image to be displayed in the popup.
   */
  void handleSplitAction(BufferedImage img);

  /**
   * Handles the level adjustment action for an image by updating the display with modified image.
   *
   * @param img The updated image to be displayed in the popup.
   */
  void handleSplitActionLevel(BufferedImage img);
}
