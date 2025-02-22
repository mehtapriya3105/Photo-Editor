package model.flip;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * VerticalFlipCommand is a concrete implementation of AbstractFlipCommand, which provides the
 * functionality to flip an image vertically.
 */
public class VerticalFlipCommand extends AbstractImageCommand {

  /**
   * Constructs a VerticalFlipCommand to perform a vertical flip on an image.
   *
   * @param sourceFileName The name of the source file containing the image to be flipped.
   * @param destFileName   The name of the destination file to store the flipped image.
   * @param model          The image model that performs the flip operation.
   */
  public VerticalFlipCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Creates and returns a new builder instance for constructing a VerticalFlipCommand.
   *
   * @return A new instance of the VerticalFlipCommandBuilder.
   */
  public static VerticalFlipCommandBuilder createBuilder() {
    return new VerticalFlipCommandBuilder();
  }

  /**
   * Executes the vertical flip operation by invoking the appropriate method in the model. The
   * flipped image is stored in the specified destination file.
   *
   * @throws Exception If an error occurs during the vertical flip operation, such as issues with
   *                   loading or saving the image.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.verticalFlipMethod(sourceFileName, destFileName);
  }

  /**
   * The builder class for constructing a VerticalFlipCommand instance.
   */
  public static class VerticalFlipCommandBuilder extends
          AbstractCommandBuilder<VerticalFlipCommand> {

    /**
     * Constructs and returns a new VerticalFlipCommand instance after validating the necessary
     * fields.
     *
     * @return A new instance of VerticalFlipCommand.
     * @throws IllegalStateException If either the source file name or the destination file name is
     *                               missing or null.
     */
    @Override
    public VerticalFlipCommand build() {
      // Validation for required fields
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }

      return new VerticalFlipCommand(sourceFileName, destFileName, model);

    }

  }
}
