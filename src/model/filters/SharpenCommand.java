package model.filters;

import java.io.FileNotFoundException;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * SharpenCommand is a concrete implementation of AbstractImageCommand, which sharpens an image and
 * stores the result. It supports both default sharpening and sharpening with a specified
 * parameter.
 */
public class SharpenCommand extends AbstractImageCommand {

  protected String mask = null;

  /**
   * Private constructor for creating a SharpenCommand with source and destination file names.
   *
   * @param sourceFileName The name of the source image file for sharpening.
   * @param destFileName   The name of the destination file to store the sharpened image.
   * @param model          The image model responsible for executing the sharpening filter.
   */
  private SharpenCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Private constructor for creating a SharpenCommand with a masked image.
   *
   * @param sourceFileName The name of the source image file for sharpening.
   * @param destFileName   The name of the destination file to store the sharpened image.
   * @param maskedImage    A string identifier for the masked image.
   * @param model          The image model responsible for executing the sharpening filter.
   */
  private SharpenCommand(String sourceFileName, String destFileName, String maskedImage,
                         IImageModel model) {
    super(sourceFileName, destFileName, maskedImage, model);
    this.mask = "mask";
  }

  /**
   * Private constructor for creating a SharpenCommand with additional parameters for the command
   * and a parameter.
   *
   * @param sourceFileName The name of the source image file for sharpening.
   * @param destFileName   The name of the destination file to store the sharpened image.
   * @param cmand          The command type (should be "split").
   * @param p              The parameter for sharpening the image.
   * @param model          The image model responsible for executing the sharpening filter.
   */
  private SharpenCommand(String sourceFileName, String destFileName, String cmand, String p,
                         IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates and returns a new instance of SharpenCommandBuilder to build a SharpenCommand.
   *
   * @return A new instance of SharpenCommandBuilder.
   */
  public static SharpenCommandBuilder createBuilder() {
    return new SharpenCommandBuilder();
  }

  /**
   * Executes the sharpening filter on the image. If no command is provided, it applies the default
   * sharpening filter. If the command "split" is provided, it applies the sharpening filter with a
   * specified parameter. Handles masked sharpening if the mask is set.
   *
   * @throws FileNotFoundException If the source image file is not found in the image manager.
   * @throws Exception             If an error occurs during the sharpening filter application.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.sharpenMethod(sourceFileName, destFileName, cmand, p, mask, maskedImage);
  }

  /**
   * The builder class for constructing a SharpenCommand instance. This allows for easy
   * configuration of the required parameters.
   */
  public static class SharpenCommandBuilder extends AbstractCommandBuilder<SharpenCommand> {

    /**
     * Builds and returns a new instance of SharpenCommand. Validates that both the source and
     * destination file names are provided.
     *
     * @return A new instance of SharpenCommand with the configured parameters.
     * @throws IllegalStateException    If any required field (sourceFileName or destFileName) is
     *                                  missing.
     * @throws IllegalArgumentException If the command is not "split" when additional parameters are
     *                                  provided.
     */
    @Override
    public SharpenCommand build() {
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }

      if (cmand != null && p != null) {
        return new SharpenCommand(sourceFileName, destFileName, cmand, p, model);
      } else if (mask != null) {
        return new SharpenCommand(sourceFileName, destFileName, maskedImage, model);
      } else {
        return new SharpenCommand(sourceFileName, destFileName, model);
      }
    }
  }
}
