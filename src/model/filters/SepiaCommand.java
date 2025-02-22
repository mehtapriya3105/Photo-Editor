package model.filters;

import java.io.FileNotFoundException;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * SepiaCommand is a concrete implementation of AbstractImageCommand, which applies a sepia filter
 * to an image and stores the result. It supports both default sepia application and sepia
 * application with a parameter.
 */
public class SepiaCommand extends AbstractImageCommand {

  protected String mask = null;

  /**
   * Private constructor for creating a SepiaCommand with source and destination file names.
   *
   * @param sourceFileName The name of the source image file for sepia filter application.
   * @param destFileName   The name of the destination file to store the result of the sepia filter
   *                       application.
   * @param model          The image model responsible for executing the sepia filter application.
   */
  private SepiaCommand(String sourceFileName, String destFileName, IImageModel model) {

    super(sourceFileName, destFileName, model);
  }

  /**
   * Private constructor for creating a SepiaCommand with a masked image.
   *
   * @param sourceFileName The name of the source image file for sepia filter application.
   * @param destFileName   The name of the destination file to store the result of the sepia filter
   *                       application.
   * @param maskedImage    A string identifier for the masked image.
   * @param model          The image model responsible for executing the sepia filter application.
   */
  private SepiaCommand(String sourceFileName, String destFileName, String maskedImage,
                       IImageModel model) {
    super(sourceFileName, destFileName, maskedImage, model);
    this.mask = "mask";
  }

  /**
   * Private constructor for creating a SepiaCommand with additional parameters for the command and
   * a parameter.
   *
   * @param sourceFileName The name of the source image file for sepia filter application.
   * @param destFileName   The name of the destination file to store the result of the sepia filter
   *                       application.
   * @param cmand          The command type (should be "split").
   * @param p              The parameter for sepia filter application.
   * @param model          The image model responsible for executing the sepia filter application.
   */
  private SepiaCommand(String sourceFileName, String destFileName, String cmand, String p,
                       IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates and returns a new instance of SepiaCommandBuilder to build a SepiaCommand.
   *
   * @return A new instance of SepiaCommandBuilder.
   */
  public static SepiaCommandBuilder createBuilder() {
    return new SepiaCommandBuilder();
  }

  /**
   * Executes the sepia filter application on the image. If no command is provided, it applies the
   * default sepia filter. If the command "split" is provided, it applies the sepia filter with a
   * specified parameter.
   *
   * @throws FileNotFoundException If the source image file is not found in the image manager.
   * @throws Exception             If there are errors during the sepia filter application.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.sepiaMethod(sourceFileName, destFileName, cmand, p, mask, maskedImage);
  }

  /**
   * The builder class for constructing a SepiaCommand instance. This allows for easy configuration
   * of the required parameters for sepia filter application.
   */
  public static class SepiaCommandBuilder extends AbstractCommandBuilder<SepiaCommand> {

    /**
     * Builds and returns a new instance of SepiaCommand. Validates that both the source and
     * destination file names are provided.
     *
     * @return A new instance of SepiaCommand with the configured parameters.
     * @throws IllegalStateException    If any required field (sourceFileName or destFileName) is
     *                                  missing.
     * @throws IllegalArgumentException If the command is not "split" when additional parameters are
     *                                  provided.
     */
    @Override
    public SepiaCommand build() {
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }

      if (cmand != null && p != null) {
        return new SepiaCommand(sourceFileName, destFileName, cmand, p, model);
      } else if (mask != null) {
        return new SepiaCommand(sourceFileName, destFileName, maskedImage, model);
      } else {
        return new SepiaCommand(sourceFileName, destFileName, model);
      }
    }
  }
}
