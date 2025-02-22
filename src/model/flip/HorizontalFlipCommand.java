package model.flip;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;
import model.filters.BlurCommand;

/**
 * HorizontalFlipCommand is a concrete implementation of AbstractFlipCommand, which provides the
 * functionality to flip an image horizontally.
 */
public class HorizontalFlipCommand extends AbstractImageCommand {

  /**
   * Constructs a HorizontalFlipCommand to perform a horizontal flip on an image.
   *
   * @param sourceFileName The name of the source file containing the image to be flipped.
   * @param destFileName   The name of the destination file to store the flipped image.
   * @param model          The image model that performs the flip operation.
   */
  private HorizontalFlipCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Creates and returns a new builder instance for constructing a HorizontalFlipCommand.
   *
   * @return A new instance of the HorizontalFlipCommandBuilder.
   */
  public static HorizontalFlipCommandBuilder createBuilder() {
    return new HorizontalFlipCommandBuilder();
  }

  /**
   * Executes the horizontal flip operation by invoking the appropriate method in the model. The
   * flipped image is stored in the specified destination file.
   *
   * @throws Exception If an error occurs during the horizontal flip operation, such as issues with
   *                   loading or saving the image.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.horizontalFlipMethod(sourceFileName, destFileName);
  }

  /**
   * The builder class for constructing a HorizontalFlipCommand instance.
   */
  public static class HorizontalFlipCommandBuilder extends
          AbstractCommandBuilder<BlurCommand> {

    /**
     * Constructs and returns a new HorizontalFlipCommand instance after validating the necessary
     * fields.
     *
     * @return A new instance of HorizontalFlipCommand.
     * @throws IllegalStateException If either the source file name or the destination file name is
     *                               missing or null.
     */
    @Override
    public HorizontalFlipCommand build() {
      // Validation for required fields
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }

      return new HorizontalFlipCommand(sourceFileName, destFileName, model);

    }

  }
}
