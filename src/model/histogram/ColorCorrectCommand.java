package model.histogram;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * ColorCorrectCommand is a command that performs color correction on an image. This command allows
 * for basic color correction or additional parameterized operations, such as "split" mode.
 */
public class ColorCorrectCommand extends AbstractImageCommand {

  /**
   * Constructs a ColorCorrectCommand with specified source and destination file names.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   */
  private ColorCorrectCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Constructs a ColorCorrectCommand with source and destination file names, command keyword, and a
   * parameter.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command keyword, specifying a special operation (e.g., "split")
   * @param p              a parameter for the command, used in specific modes (e.g., split mode
   *                       level)
   */
  private ColorCorrectCommand(String sourceFileName, String destFileName, String cmand, String p,
                              IImageModel model) {
    super(sourceFileName, destFileName, cmand, p, model);
  }

  /**
   * Creates and returns a new ColorCorrectCommandBuilder instance.
   *
   * @return a new instance of ColorCorrectCommandBuilder for building a ColorCorrectCommand
   */
  public static ColorCorrectCommandBuilder createBuilder() {
    return new ColorCorrectCommandBuilder();
  }

  /**
   * This method processes the image for color correction with split parameter.
   *
   * @throws Exception if exception occurs.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.colorCorrectMethod(sourceFileName, destFileName, cmand, p);
  }

  /**
   * Builder class for constructing instances of ColorCorrectCommand. This builder enforces required
   * fields and validates command and parameter settings.
   */
  public static class ColorCorrectCommandBuilder extends
          AbstractCommandBuilder<ColorCorrectCommand> {

    /**
     * Builds a ColorCorrectCommand instance with the specified attributes.
     *
     * @return a new instance of ColorCorrectCommand
     * @throws IllegalStateException if required attributes are not set
     */
    @Override
    public ColorCorrectCommand build() {
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }
      if (cmand != null && p != null) {
        return new ColorCorrectCommand(sourceFileName, destFileName, cmand, p, model);
      }
      return new ColorCorrectCommand(sourceFileName, destFileName, model);
    }
  }
}
