package model.filters;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * LumaCommand is a concrete implementation of AbstractImageCommand, which calculates the luma of an
 * image and stores the result. It supports both default luma calculation and luma calculation with
 * a parameter.
 */
public class LumaCommand extends AbstractImageCommand {

  protected String mask = null;

  /**
   * Constructs a LumaCommand to calculate luma on an image.
   *
   * @param sourceFileName The name of the source image file.
   * @param destFileName   The name of the destination file to store the calculated luma.
   * @param model          The image model responsible for executing the luma calculation.
   */
  private LumaCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Constructs a LumaCommand to calculate luma with a mask applied to the image.
   *
   * @param sourceFileName The name of the source image file.
   * @param destFileName   The name of the destination file to store the calculated luma.
   * @param maskedImage    A string identifier for the masked image.
   * @param model          The image model responsible for executing the luma calculation.
   */
  private LumaCommand(String sourceFileName, String destFileName, String maskedImage,
                      IImageModel model) {
    super(sourceFileName, destFileName, maskedImage, model);
    this.mask = "mask";
  }

  /**
   * Constructs a LumaCommand with additional parameters for a more specific luma calculation, such
   * as the "split" command with a parameter.
   *
   * @param sourceFileName The name of the source image file.
   * @param destFileName   The name of the destination file to store the calculated luma.
   * @param cmand          The command type (e.g., "split").
   * @param p              The parameter to adjust the luma calculation.
   * @param model          The image model responsible for executing the luma calculation.
   */
  private LumaCommand(String sourceFileName, String destFileName, String cmand, String p,
                      IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates and returns a new instance of LumaCommandBuilder to build a LumaCommand.
   *
   * @return A new instance of LumaCommandBuilder.
   */
  public static LumaCommandBuilder createBuilder() {
    return new LumaCommandBuilder();
  }

  /**
   * Executes the luma calculation on the image. If no command is provided, it calculates the
   * default luma. If the command "split" is provided, it calculates the luma using the provided
   * parameter.
   *
   * @throws Exception If there are errors during the luma calculation process.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.lumaMethod(sourceFileName, destFileName, cmand, p, mask, maskedImage);
  }


  /**
   * The builder class for constructing a LumaCommand instance. This allows for easy configuration
   * of the required parameters.
   */
  public static class LumaCommandBuilder extends
          AbstractCommandBuilder<LumaCommand.LumaCommandBuilder> {

    /**
     * Builds and returns a new instance of LumaCommand. Ensures that both the source and
     * destination file names are provided and validates the parameters for the command.
     *
     * @return A new instance of LumaCommand with the configured parameters.
     * @throws IllegalStateException    If the sourceFileName or destFileName are missing.
     * @throws IllegalArgumentException If the command is not "split" when additional parameters are
     *                                  provided.
     */
    @Override
    public LumaCommand build() {
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }

      if (cmand != null && p != null) {
        if (!cmand.equals("split")) {
          throw new IllegalArgumentException("Wrong command");
        }
        return new LumaCommand(sourceFileName, destFileName, cmand, p, model);
      } else if (mask != null) {
        return new LumaCommand(sourceFileName, destFileName, maskedImage, model);
      } else {
        return new LumaCommand(sourceFileName, destFileName, model);
      }
    }
  }
}
