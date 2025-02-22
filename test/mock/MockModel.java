package mock;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import model.IImageModel;
import model.database.Image;

/**
 * This test class implements mock tests for the model. It implements IImageModel class.
 */
public class MockModel implements IImageModel {

  private static Map<String, Image> ImageManager = new HashMap<>();
  private final StringBuilder log;

  public MockModel() {
    this.log = new StringBuilder();
  }

  /**
   * Appends a message to the log.
   *
   * @param message The message to append to the log
   */
  private void logAction(String message) {
    log.append(message).append("\n");
  }

  /**
   * Stores a CustomImage object in the image storage under a specified name.
   *
   * @param name  The name to associate with the image
   * @param image The CustomImage object to store
   */
  @Override
  public void storeImage(String name, Image image) {
    ImageManager.put(name, image);
    logAction("Stored image: " + name);
  }

  /**
   * Retrieves an image by its name from the mock storage.
   *
   * @param name The name of the image
   * @return The image object
   * @throws FileNotFoundException if the image is not found
   */
  @Override
  public Image getImage(String name) throws FileNotFoundException {
    if (!ImageManager.containsKey(name)) {
      logAction("Attempted to retrieve non-existent image: " + name);
      throw new FileNotFoundException("Image not found: " + name);
    }
    logAction("Retrieved image: " + name);
    return ImageManager.get(name);
  }


  /**
   * Applies a blur effect to an image and stores the result.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void blurMethod(String sourceFileName, String destFileName, String cmand, String p,
                         String mask, String maskImage)
          throws FileNotFoundException {
    logWritter("Blur", cmand, mask);
  }

  /**
   * Calculates the intensity of an image and applies optional transformations.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void intensityMethod(String sourceFileName, String destFileName, String cmand, String p,
                              String mask, String maskImage)
          throws FileNotFoundException {
    logWritter("Intensity", cmand, mask);

  }

  /**
   * Calculates the luma of an image and applies optional transformations.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void lumaMethod(String sourceFileName, String destFileName, String cmand, String p,
                         String mask, String maskImage)
          throws FileNotFoundException {
    logWritter("Luma", cmand, mask);
  }

  /**
   * Applies a sepia tone effect to an image and stores the result.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void sepiaMethod(String sourceFileName, String destFileName, String cmand, String p,
                          String mask, String maskImage)
          throws FileNotFoundException {
    logWritter("Sepia", cmand, mask);
  }

  /**
   * Applies a sharpening filter to an image and stores the result.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void sharpenMethod(String sourceFileName, String destFileName, String cmand, String p,
                            String mask, String maskImage)
          throws FileNotFoundException {
    logWritter("Sharpen", cmand, mask);
  }

  /**
   * Processes an image to calculate its value and optionally applies split and mask commands.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @param mask           indicates if a mask should be applied
   * @param maskImage      the name of the mask image file
   * @throws FileNotFoundException if the source or mask image file is not found
   */
  @Override
  public void valueMethod(String sourceFileName, String destFileName, String cmand, String p,
                          String mask, String maskImage)
          throws FileNotFoundException {
    logWritter("Value", cmand, mask);
  }

  /**
   * Horizontally flips an image and stores the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @throws Exception if the source file is not found or flipping fails
   */
  @Override
  public void horizontalFlipMethod(String sourceFileName, String destFileName) throws Exception {
    logAction(
            "Called horizontalFlipMethod with sourceFileName: " + sourceFileName
                    + ", destFileName: " + destFileName);
  }

  /**
   * Vertically flips an image and stores the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @throws Exception if the source file is not found or flipping fails
   */
  @Override
  public void verticalFlipMethod(String sourceFileName, String destFileName) throws Exception {
    logAction(
            "Called verticalFlipMethod with sourceFileName: " + sourceFileName + ", destFileName: "
                    + destFileName);
  }


  /**
   * Applies color correction to an image and optionally splits it.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @throws FileNotFoundException if the source file is not found
   */
  @Override
  public void colorCorrectMethod(String sourceFileName, String destFileName, String cmand, String p)
          throws FileNotFoundException {
    logWritter("Color-Correct", cmand);
  }

  /**
   * Creates a histogram representation of an image and stores it in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @throws FileNotFoundException if the source file is not found
   */
  @Override
  public void histogramMethod(String sourceFileName, String destFileName)
          throws FileNotFoundException {
    logAction("Called histogramMethod with sourceFileName: " + sourceFileName + ", destFileName: "
            + destFileName);
  }

  /**
   * Extracts the blue component of an image, optionally applies split and mask commands, and stores
   * the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @param mask           indicates if a mask should be applied
   * @param maskImage      the name of the mask image file
   * @throws FileNotFoundException if the source or mask image file is not found
   */
  @Override
  public void getBlueImageMethod(String sourceFileName, String destFileName, String cmand, String p,
                                 String mask, String maskImage)
          throws FileNotFoundException {
    logWritter("BlueImage", cmand, mask);
  }

  /**
   * Extracts the red component of an image, optionally applies split and mask commands, and stores
   * the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @param mask           indicates if a mask should be applied
   * @param maskImage      the name of the mask image file
   * @throws FileNotFoundException if the source or mask image file is not found
   */
  @Override
  public void getRedComponentMethod(String sourceFileName, String destFileName, String cmand,
                                    String p, String mask, String maskImage)
          throws FileNotFoundException {
    logWritter("RedImage", cmand, mask);
  }

  /**
   * Extracts the green component of an image, optionally applies split and mask commands, and
   * stores the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @param mask           indicates if a mask should be applied
   * @param maskImage      the name of the mask image file
   * @throws FileNotFoundException if the source or mask image file is not found
   */
  @Override
  public void getGreenComponentImage(String sourceFileName, String destFileName, String cmand,
                                     String p, String mask, String maskImage)
          throws FileNotFoundException {
    logWritter("GreenImage", cmand, mask);
  }

  /**
   * Combines red, green, and blue component images into a single RGB image.
   *
   * @param redImageName   the name of the red component image file
   * @param greenImageName the name of the green component image file
   * @param blueImageName  the name of the blue component image file
   * @param imageName      the name of the resulting RGB image file
   * @throws FileNotFoundException if any of the component image files are not found
   */
  @Override
  public void rgbCombineMethod(String redImageName, String greenImageName, String blueImageName,
                               String imageName)
          throws FileNotFoundException {
    logAction("Called rgbCombineMethod with redImageName: " + redImageName + ", greenImageName: "
            + greenImageName + ", blueImageName: "
            + blueImageName);
  }

  /**
   * Splits an RGB image into separate red, green, and blue component images.
   *
   * @param redImageName   the name of the red component image file
   * @param greenImageName the name of the green component image file
   * @param blueImageName  the name of the blue component image file
   * @param imageName      the name of the RGB image file
   * @throws FileNotFoundException if the RGB image file is not found
   */
  @Override
  public void rgbSplitMethod(String redImageName, String greenImageName, String blueImageName,
                             String imageName)
          throws FileNotFoundException {
    logAction("Called rgbSplitMethod with imageName: " + imageName);
  }

  /**
   * Brightens an image by a specified value and stores the result in a destination file.
   *
   * @param brightness      the value to adjust brightness
   * @param imageName       the name of the source image file
   * @param destinationName the name of the destination image file
   * @throws FileNotFoundException if the source image file is not found
   */
  @Override
  public void brightenMethod(Integer brightness, String imageName, String destinationName)
          throws FileNotFoundException {
    logAction("Called brightenMethod with brightness: " + brightness + ", imageName: " + imageName
            + ", destinationName: "
            + destinationName);
  }

  /**
   * Adjusts the levels of an image (black, mid, and white) and optionally applies split command.
   *
   * @param b               the black level adjustment value
   * @param m               the mid-tone level adjustment value
   * @param w               the white level adjustment value
   * @param imageName       the name of the source image file
   * @param destinationName the name of the destination image file
   * @param p               parameter used for the "split" command
   * @param cmand           the command to execute (e.g., "split")
   * @throws FileNotFoundException if the source image file is not found
   */
  @Override
  public void levelAdjustMethod(int b, int m, int w, String imageName, String destinationName,
                                int p, String cmand)
          throws FileNotFoundException {
    logWritter("LevelAdjust", cmand);
  }

  /**
   * Compresses an image by a specified percentage and stores the result in a destination file.
   *
   * @param imageName       the name of the source image file
   * @param destinationName the name of the destination image file
   * @param percentage      the compression percentage
   * @throws FileNotFoundException if the source image file is not found
   */
  @Override
  public void compressMethod(String imageName, String destinationName, Integer percentage)
          throws FileNotFoundException {
    logAction("Called compressMethod with imageName: " + imageName + ", destinationName: "
            + destinationName + ", percentage: "
            + percentage);
  }

  /**
   * Downscales an image to a new width and height and stores the result in a destination file.
   *
   * @param imageName       the name of the source image file
   * @param destinationName the name of the destination image file
   * @param newHeight       the new height of the image
   * @param newWidth        the new width of the image
   * @throws FileNotFoundException if the source image file is not found
   */
  @Override
  public void imageDownScalling(String imageName, String destinationName, int newHeight,
                                int newWidth) throws FileNotFoundException {
    logAction("Called imageDownScalling with imageName: " + imageName + ", destinationName: "
            + destinationName);
  }

  /**
   * Retrieves the log content.
   *
   * @return The log as a string
   */
  public String getLog() {
    return log.toString();
  }

  /**
   * Logs the details of an image processing action, including the type of action
   * and command used. Overloaded method without mask information.
   *
   * @param action the name of the action being performed (e.g., "blur", "sharpen").
   * @param cmand  the specific command related to the action (e.g., "split" or null for default).
   */
  private void logWritter(String action, String cmand) {
    logAction("Called " + action + " method!");

    if ("split".equals(cmand)) {
      logAction("Called " + action + "method along with split!");
    } else if (cmand != null) {
      logAction("Invalid " + action + " Command!");
    }

    logAction(action + " Command Image Saved");
  }

  /**
   * Logs the details of an image processing action, including the type of action,
   * command used, and whether a mask was applied. Overloaded method with mask information.
   *
   * @param action the name of the action being performed (e.g., "blur", "sharpen").
   * @param cmand  the specific command related to the action (e.g., "split" or null for default).
   * @param mask   indicates whether a masked image was used (can be null if no mask is applied).
   */
  private void logWritter(String action, String cmand, String mask) {
    logAction("Called " + action + " method!");
    //    System.out.println("cmand: " + cmand);
    //    System.out.println("mask: " + mask);
    if ("split".equals(cmand)) {
      logAction("Called " + action + "method along with split!");
    } else if (cmand != null) {
      logAction("Invalid " + action + " Command!");
    }

    if (mask != null) {
      logAction("Called " + action + " Command with masked Image");
    }

    logAction(action + " Command Image Saved");
  }
}
