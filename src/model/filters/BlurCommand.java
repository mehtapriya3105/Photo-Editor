package model.filters;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;


/**
 * BlurCommand is a concrete implementation of AbstractImageCommand, which applies a blur filter to
 * an image. This command can be executed to apply a default blur or a parameterized blur based on
 * the command passed.
 */
public class BlurCommand extends AbstractImageCommand {

  protected String mask = null;

  /**
   * Constructs a BlurCommand to apply a blur filter to an image.
   *
   * @param sourceFileName The name of the source image file.
   * @param destFileName   The name of the destination file where the blurred image will be stored.
   * @param model          The image model responsible for executing the blur operation.
   */
  private BlurCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Constructs a BlurCommand to apply a blur filter with a mask to an image.
   *
   * @param sourceFileName The name of the source image file.
   * @param destFileName   The name of the destination file where the blurred image will be stored.
   * @param maskedImage    A string identifier for the masked image.
   * @param model          The image model responsible for executing the blur operation.
   */
  private BlurCommand(String sourceFileName, String destFileName, String maskedImage,
                      IImageModel model) {
    super(sourceFileName, destFileName, maskedImage, model);
    this.mask = "mask";
  }

  /**
   * Constructs a BlurCommand with additional parameters for advanced configuration.
   *
   * @param sourceFileName The name of the source image file.
   * @param destFileName   The name of the destination file where the blurred image will be stored.
   * @param cmand          The command type (e.g., "split").
   * @param p              A parameter to adjust the blur filter.
   * @param model          The image model responsible for executing the blur operation.
   */
  private BlurCommand(String sourceFileName, String destFileName, String cmand, String p,
                      IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates and returns a new instance of BlurCommandBuilder to build a BlurCommand.
   *
   * @return A new instance of BlurCommandBuilder.
   */
  public static BlurCommandBuilder createBuilder() {
    return new BlurCommandBuilder();
  }

  /**
   * Executes the blur operation by invoking the appropriate method in the image model.
   *
   * @throws Exception If an error occurs during the blur operation, such as issues with loading,
   *                   processing, or saving the image.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.blurMethod(sourceFileName, destFileName, cmand, p, mask, maskedImage);
  }

  /**
   * The builder class for constructing a BlurCommand instance. This allows for easy configuration
   * of the required parameters.
   */
  public static class BlurCommandBuilder extends AbstractCommandBuilder<BlurCommandBuilder> {

    /**
     * Builds and returns a new BlurCommand instance after validating required parameters.
     *
     * @return A new instance of BlurCommand configured with the specified parameters.
     * @throws IllegalStateException If any required fields (sourceFileName or destFileName) are
     *                               missing or not set.
     */
    @Override
    public BlurCommand build() {
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }

      if (cmand != null && p != null) {
        return new BlurCommand(sourceFileName, destFileName, cmand, p, model);
      } else if (mask != null) {
        return new BlurCommand(sourceFileName, destFileName, maskedImage, model);
      } else {
        return new BlurCommand(sourceFileName, destFileName, model);
      }
    }
  }
}
