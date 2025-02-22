package model.filters;

import java.io.FileNotFoundException;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * ValueCommand is a concrete implementation of AbstractImageCommand, which calculates the value
 * (brightness) of an image and stores the result.
 */
public class ValueCommand extends AbstractImageCommand {

  protected String mask = null;

  /**
   * Private constructor for creating a ValueCommand with source and destination file names.
   *
   * @param sourceFileName The name of the source image file for calculating the value
   *                       (brightness).
   * @param destFileName   The name of the destination file to store the result.
   * @param model          The image model responsible for executing the value calculation.
   */
  private ValueCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Private constructor for creating a ValueCommand with a masked image.
   *
   * @param sourceFileName The name of the source image file for calculating the value.
   * @param destFileName   The name of the destination file to store the result.
   * @param maskImage      A string identifier for the masked image.
   * @param model          The image model responsible for executing the value calculation.
   */
  private ValueCommand(String sourceFileName, String destFileName, String maskImage,
                       IImageModel model) {
    super(sourceFileName, destFileName, maskImage, model);
    this.mask = "mask";
  }

  /**
   * Private constructor for creating a ValueCommand with additional parameters for the command and
   * a parameter.
   *
   * @param sourceFileName The name of the source image file for calculating the value
   *                       (brightness).
   * @param destFileName   The name of the destination file to store the result.
   * @param cmand          The command type (should be "split").
   * @param p              The parameter for calculating the value (brightness) of the image.
   */
  private ValueCommand(String sourceFileName, String destFileName, String cmand, String p,
                       IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates and returns a new instance of ValueCommandBuilder to build a ValueCommand.
   *
   * @return A new instance of ValueCommandBuilder.
   */
  public static ValueCommandBuilder createBuilder() {
    return new ValueCommandBuilder();
  }

  /**
   * Executes the value calculation on the image. If no command is provided, it calculates the
   * default value (brightness). If the "split" command is provided, it calculates the value with a
   * specified parameter. Handles masked value calculation if a mask is provided.
   *
   * @throws FileNotFoundException If the source image file is not found in the image manager.
   * @throws Exception             If an error occurs during the value calculation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.valueMethod(sourceFileName, destFileName, cmand, p, mask, maskedImage);
  }

  /**
   * The builder class for constructing a ValueCommand instance. This allows for easy configuration
   * of the required parameters.
   */
  public static class ValueCommandBuilder extends AbstractCommandBuilder<ValueCommand> {

    /**
     * Builds and returns a new instance of ValueCommand. Validates that both the source and
     * destination file names are provided. Depending on the input configuration, it creates the
     * appropriate ValueCommand instance for default value calculation, parameterized calculation,
     * or masked calculation.
     *
     * @return A new instance of ValueCommand with the configured parameters.
     * @throws IllegalStateException    If any required field (sourceFileName or destFileName) is
     *                                  missing.
     * @throws IllegalArgumentException If the command is not "split" when additional parameters are
     *                                  provided.
     */
    @Override
    public ValueCommand build() {
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }

      if (cmand != null && p != null) {
        return new ValueCommand(sourceFileName, destFileName, cmand, p, model);
      } else if (mask != null) {
        return new ValueCommand(sourceFileName, destFileName, maskedImage, model);
      } else {
        return new ValueCommand(sourceFileName, destFileName, model);
      }
    }
  }
}
