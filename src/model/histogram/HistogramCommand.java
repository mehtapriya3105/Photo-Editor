package model.histogram;

import model.AbstractCommandBuilder;
import model.AbstractImageCommand;
import model.IImageModel;

/**
 * HistogramCommand is a command class that generates and displays a histogram for a specified
 * image. This command fetches an image from the source, creates a histogram, and displays the
 * resulting histogram image.
 */
public class HistogramCommand extends AbstractImageCommand {

  /**
   * Constructs a HistogramCommand with the specified source and destination file names.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   */
  private HistogramCommand(String sourceFileName, String destFileName, IImageModel model) {
    super(sourceFileName, destFileName, model);
  }

  /**
   * Creates and returns a new HistogramCommandBuilder instance.
   *
   * @return a new instance of HistogramCommandBuilder for building a HistogramCommand
   */
  public static HistogramCommandBuilder createBuilder() {
    return new HistogramCommandBuilder();
  }

  /**
   * This method processes the image to generate histogram using file name and dest file name.
   *
   * @throws Exception if exception occurs.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.histogramMethod(sourceFileName, destFileName);
  }

  /**
   * Builder class for constructing instances of HistogramCommand. This builder enforces that
   * required fields, such as source and destination file names, are set before building the
   * command.
   */
  public static class HistogramCommandBuilder extends AbstractCommandBuilder<HistogramCommand> {

    /**
     * Builds a HistogramCommand instance with the specified attributes.
     *
     * @return a new instance of HistogramCommand
     * @throws IllegalStateException if required attributes are not set
     */
    @Override
    public HistogramCommand build() {
      if (sourceFileName == null || destFileName == null) {
        throw new IllegalStateException("Source and destination file names are required.");
      }
      return new HistogramCommand(sourceFileName, destFileName, model);
    }
  }
}
