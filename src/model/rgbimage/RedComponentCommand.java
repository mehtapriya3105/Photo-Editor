package model.rgbimage;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * RedComponentCommand is a concrete implementation of AbstractRGB, which extracts the red color
 * component from an image.
 */
public class RedComponentCommand extends AbstractImageCommand {

  protected String mask = null;

  /**
   * Constructs a RedComponentCommand with a mask to extract the red color component from an image.
   *
   * @param sourceFileName the name of the source image file.
   * @param destFileName   the name of the destination file to store the resulting image.
   * @param maskedImage    the mask image file name to apply during processing.
   * @param model          the image model to execute the command.
   */
  private RedComponentCommand(String sourceFileName, String destFileName, String maskedImage,
                              IImageModel model) {
    super(sourceFileName, destFileName, maskedImage, model);
    this.mask = "mask";
  }

  /**
   * Constructs a RedComponentCommand to extract the red color component without a mask.
   *
   * @param sourceFileName the name of the source image file.
   * @param destFileName   the name of the destination file to store the resulting image.
   * @param model          the image model to execute the command.
   */
  private RedComponentCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Constructs a RedComponentCommand with a specific command and percentage.
   *
   * @param sourceFileName the name of the source image file.
   * @param destFileName   the name of the destination file to store the resulting image.
   * @param cmand          the command type to execute.
   * @param p              the percentage to apply during processing.
   * @param model          the image model to execute the command.
   */
  private RedComponentCommand(String sourceFileName, String destFileName, String cmand, String p,
                              IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates a new builder instance for constructing RedComponentCommand instances.
   *
   * @return a RedComponentCommandBuilder instance to build the command.
   */
  public static RedComponentCommandBuilder createBuilder() {
    return new RedComponentCommandBuilder();
  }

  /**
   * Processes the source image to extract the red color component and stores the result.
   *
   * @throws Exception if an error occurs during image processing.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.getRedComponentMethod(sourceFileName, destFileName, cmand, p, mask, maskedImage);
  }

  /**
   * Builder class for constructing RedComponentCommand instances.
   */
  public static class RedComponentCommandBuilder extends
          AbstractCommandBuilder<RedComponentCommand> {

    /**
     * Builds a RedComponentCommand instance after validating required fields. Depending on the
     * provided parameters, it constructs the appropriate version of the command.
     *
     * @return a new instance of RedComponentCommand.
     * @throws IllegalStateException if source or destination file names are not set.
     */
    @Override
    public RedComponentCommand build() {

      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }
      if (cmand != null && p != null) {
        return new RedComponentCommand(sourceFileName, destFileName, cmand, p, model);
      } else if (mask != null) {
        return new RedComponentCommand(sourceFileName, destFileName, maskedImage, model);
      } else {
        return new RedComponentCommand(sourceFileName, destFileName, model);
      }

    }

  }
}
