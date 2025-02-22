package model.rgbimage;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * GreenComponentCommand is a concrete implementation of AbstractRGB, which extracts the green color
 * component from an image.
 */
public class GreenComponentCommand extends AbstractImageCommand {

  protected String mask = null;

  /**
   * Constructs a GreenComponentCommand with a mask to extract the green color component from
   * image.
   *
   * @param sourceFileName the name of the source image file.
   * @param destFileName   the name of the destination file to store the resulting image.
   * @param maskedImage    the mask image file name to apply during processing.
   * @param model          the image model to execute the command.
   */
  private GreenComponentCommand(String sourceFileName, String destFileName,
                                String maskedImage, IImageModel model) {
    super(sourceFileName, destFileName, maskedImage, model);
    this.mask = "mask";
  }

  /**
   * Constructs a GreenComponentCommand to extract the green color component from an image. This
   * constructor is used when no mask is provided.
   *
   * @param sourceFileName the name of the source image file.
   * @param destFileName   the name of the destination file to store the resulting image.
   * @param model          the image model to execute the command.
   */
  private GreenComponentCommand(String sourceFileName, String destFileName,
                                IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Constructs a GreenComponentCommand with a specific command and percentage.
   *
   * @param sourceFileName the name of the source image file.
   * @param destFileName   the name of the destination file to store the resulting image.
   * @param cmand          the command type.
   * @param p              the percentage to be applied during processing.
   * @param model          the image model to execute the command.
   */
  private GreenComponentCommand(String sourceFileName, String destFileName, String cmand, String p,
                                IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates a new builder instance for the GreenComponentCommand.
   *
   * @return a GreenComponentCommandBuilder instance to build the command.
   */
  public static GreenComponentCommandBuilder createBuilder() {
    return new GreenComponentCommandBuilder();
  }

  /**
   * Processes the source image to extract the green color component and stores the result.
   *
   * @throws Exception if an error occurs during image processing.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.getGreenComponentImage(sourceFileName, destFileName, cmand, p, mask, maskedImage);
  }

  /**
   * Builder class for constructing GreenComponentCommand instances.
   */
  public static class GreenComponentCommandBuilder extends
          AbstractCommandBuilder<GreenComponentCommand> {

    /**
     * Builds a GreenComponentCommand instance after validating the required fields. Depending on
     * the provided parameters, it constructs the appropriate version of the command.
     *
     * @return a new instance of GreenComponentCommand.
     * @throws IllegalStateException if source or destination file names are not set.
     */
    @Override
    public GreenComponentCommand build() {
      // Validation for required fields
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }
      if (cmand != null && p != null) {
        return new GreenComponentCommand(sourceFileName, destFileName, cmand, p, model);
      } else if (mask != null) {
        return new GreenComponentCommand(sourceFileName, destFileName, maskedImage, model);
      } else {
        return new GreenComponentCommand(sourceFileName, destFileName, model);
      }

    }

  }
}
