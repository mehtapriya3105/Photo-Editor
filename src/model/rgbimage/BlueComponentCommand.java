package model.rgbimage;

import java.io.FileNotFoundException;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * BlueComponentCommand is a concrete implementation of AbstractRGB, which extracts the blue color
 * component from an image.
 */
public class BlueComponentCommand extends AbstractImageCommand {

  protected String mask = null;

  /**
   * Constructs a BlueComponentCommand to extract the blue color component from an image. This
   * constructor is used when no mask is provided.
   *
   * @param sourceFileName the name of the source image file.
   * @param destFileName   the name of the destination file to store the resulting image.
   * @param model          the image model to execute the command.
   */
  private BlueComponentCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Constructs a BlueComponentCommand with a mask to extract the blue color component from image.
   *
   * @param sourceFileName the name of the source image file.
   * @param destFileName   the name of the destination file to store the resulting image.
   * @param maskedImage    the mask image file name to apply during processing.
   * @param model          the image model to execute the command.
   */
  private BlueComponentCommand(String sourceFileName, String destFileName,
                               String maskedImage, IImageModel model) {
    super(sourceFileName, destFileName, maskedImage, model);
    this.mask = "mask";
  }

  /**
   * Constructs a BlueComponentCommand with a specific command and percentage.
   *
   * @param sourceFileName the name of the source image file.
   * @param destFileName   the name of the destination file to store the resulting image.
   * @param cmand          the command type.
   * @param p              the percentage to be applied during processing.
   * @param model          the image model to execute the command.
   */
  private BlueComponentCommand(String sourceFileName, String destFileName, String cmand, String p,
                               IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates a new builder instance for the BlueComponentCommand.
   *
   * @return a BlueComponentCommandBuilder instance to build the command.
   */
  public static BlueComponentCommandBuilder createBuilder() {
    return new BlueComponentCommandBuilder();
  }

  /**
   * Processes the source image to extract the blue color component and stores the result.
   *
   * @throws Exception if an error occurs during image processing.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.getBlueImageMethod(sourceFileName, destFileName, cmand, p, mask, maskedImage);
  }

  /**
   * Builder class for constructing BlueComponentCommand instances.
   */
  public static class BlueComponentCommandBuilder extends
          AbstractCommandBuilder<BlueComponentCommand> {

    /**
     * Builds a BlueComponentCommand instance after validating the required fields. Depending on the
     * provided parameters, it constructs the appropriate version of the command.
     *
     * @return a new instance of BlueComponentCommand.
     * @throws FileNotFoundException if the model requires a file that is not found.
     * @throws IllegalStateException if source or destination file names are not set.
     */
    @Override
    public BlueComponentCommand build() throws FileNotFoundException {
      // Validation for required fields
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }
      if (cmand != null && p != null) {
        return new BlueComponentCommand(sourceFileName, destFileName, cmand, p, model);
      } else if (mask == null) {
        return new BlueComponentCommand(sourceFileName, destFileName, model);
      }
      return new BlueComponentCommand(sourceFileName, destFileName, maskedImage, model);
    }

  }
}
