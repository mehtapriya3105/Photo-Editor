package model.filters;

import java.io.FileNotFoundException;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * IntensityCommand is a concrete implementation of AbstractImageCommand, which calculates the
 * intensity of an image and stores the result. The command operates in two modes: one with the
 * default intensity calculation and another with a parameterized intensity calculation if the
 * "split" command is provided along with a parameter.
 */
public class IntensityCommand extends AbstractImageCommand {

  protected String mask = null;

  /**
   * Constructs an IntensityCommand to calculate intensity on an image.
   *
   * @param sourceFileName The name of the source image file.
   * @param destFileName   The name of the destination file to store the calculated intensity.
   * @param model          The image model responsible for executing the intensity calculation.
   */
  private IntensityCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Constructs an IntensityCommand to calculate intensity with a mask applied to the image.
   *
   * @param sourceFileName The name of the source image file.
   * @param destFileName   The name of the destination file to store the calculated intensity.
   * @param maskedImage    A string identifier for the masked image.
   * @param model          The image model responsible for executing the intensity calculation.
   */
  private IntensityCommand(String sourceFileName, String destFileName, String maskedImage,
                           IImageModel model) {
    super(sourceFileName, destFileName, maskedImage, model);
    this.mask = "mask";
  }

  /**
   * Constructs an IntensityCommand with additional parameters for a more specific intensity
   * calculation, such as the "split" command with a parameter.
   *
   * @param sourceFileName The name of the source image file.
   * @param destFileName   The name of the destination file to store the calculated intensity.
   * @param cmand          The command type (e.g., "split").
   * @param p              The parameter to adjust the intensity calculation.
   * @param model          The image model responsible for executing the intensity calculation.
   */
  private IntensityCommand(String sourceFileName, String destFileName, String cmand, String p,
                           IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates and returns a new instance of IntensityCommandBuilder to build an IntensityCommand.
   *
   * @return A new instance of IntensityCommandBuilder.
   */
  public static IntensityCommandBuilder createBuilder() {
    return new IntensityCommandBuilder();
  }

  /**
   * Executes the intensity calculation on the image. If no command is provided, it calculates the
   * default intensity. If the command "split" is provided, it calculates the intensity using the
   * provided parameter.
   *
   * @throws FileNotFoundException    If the source image file is not found in the image manager.
   * @throws IllegalArgumentException If the command type is not "split" when additional parameters
   *                                  are provided.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.intensityMethod(sourceFileName, destFileName, cmand, p, mask, maskedImage);
  }


  /**
   * The builder class for constructing an IntensityCommand instance. This allows for easy
   * configuration of the required parameters.
   */
  public static class IntensityCommandBuilder extends
          AbstractCommandBuilder<IntensityCommandBuilder> {

    /**
     * Builds and returns a new instance of IntensityCommand. Ensures that both the source and
     * destination file names are provided and validates the parameters for the command.
     *
     * @return A new instance of IntensityCommand with the configured parameters.
     * @throws IllegalStateException If the sourceFileName or destFileName are missing.
     */
    @Override
    public IntensityCommand build() {
      // Validation for required fields
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }
      // Return a new instance of IntensityCommand with appropriate constructor
      if (cmand != null && p != null) {
        return new IntensityCommand(sourceFileName, destFileName, cmand, p, model);
      } else if (mask != null) {
        return new IntensityCommand(sourceFileName, destFileName, maskedImage, model);
      } else {
        return new IntensityCommand(sourceFileName, destFileName, model);
      }
    }
  }
}
