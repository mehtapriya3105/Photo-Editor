package model;

import java.io.FileNotFoundException;

import model.commands.ModelCommand;

/**
 * The CompressCommand class is responsible for compressing an image to a specified percentage and
 * saving the compressed image under a new name.
 */
public class CompressCommand implements ModelCommand {

  private String imageName;
  private Integer percentage;
  private String destinationName;
  private IImageModel model;

  /**
   * Constructs a CompressCommand with the specified image name, compression percentage, and
   * destination name.
   *
   * @param percentage      the percentage to compress the image by
   * @param imageName       the name of the image to be compressed
   * @param destinationName the name under which the compressed image will be saved
   * @param model           the image model containing the compressing logic
   */
  public CompressCommand(Integer percentage, String imageName, String destinationName,
                         IImageModel model) {
    this.imageName = imageName;
    this.percentage = percentage;
    this.destinationName = destinationName;
    this.model = model;
  }

  /**
   * Creates and returns a builder for constructing a CompressCommand instance.
   *
   * @return a new instance of CompressBuilderClass for building a CompressCommand
   */
  public static CompressBuilderClass createBuilder() {
    return new CompressBuilderClass();
  }

  /**
   * Executes the compression command by compressing the specified image to the given percentage and
   * storing the result under the destination name.
   *
   * @return true if the compression is successful
   * @throws FileNotFoundException if the image to be compressed cannot be found
   */
  @Override
  public boolean execute() throws FileNotFoundException {
    this.model.compressMethod(imageName, destinationName, percentage);
    return true;
  }

  /**
   * Builder class for constructing instances of CompressCommand.
   */
  public static class CompressBuilderClass {

    private String imageName;
    private Integer percentage;
    private String destinationName;
    private IImageModel model;

    /**
     * Sets the name of the image to be compressed.
     *
     * @param imageName the name of the image
     * @return the current builder instance for chaining
     */
    public CompressBuilderClass setImageName(String imageName) {
      this.imageName = imageName;
      return this;
    }

    /**
     * Sets the compression percentage.
     *
     * @param percentage the percentage to compress the image by (as a string)
     * @return the current builder instance for chaining
     * @throws IllegalArgumentException if the percentage is not between 0 and 100
     */
    public CompressBuilderClass setPercentage(String percentage) {
      int percentValue = Integer.valueOf(percentage);
      if (percentValue < 0 || percentValue > 100) {
        throw new IllegalArgumentException("Percentage must be between 0 and 100");
      }
      this.percentage = percentValue;
      return this;
    }

    /**
     * Sets the destination name for the compressed image.
     *
     * @param destinationName the name under which the compressed image will be saved
     * @return the current builder instance for chaining
     */
    public CompressBuilderClass setDestinationName(String destinationName) {
      this.destinationName = destinationName;
      return this;
    }

    /**
     * Sets the image model containing the logic for image compression.
     *
     * @param model the image model instance
     * @return the current builder instance for chaining
     */
    public CompressBuilderClass setModel(IImageModel model) {
      this.model = model;
      return this;
    }

    /**
     * Builds and returns a CompressCommand instance.
     *
     * @return a new CompressCommand instance
     * @throws IllegalStateException if any required field is missing
     */
    public CompressCommand build() {
      if (imageName == null || percentage == null || destinationName == null || model == null) {
        throw new IllegalStateException("Missing required fields for CompressCommand");
      }
      return new CompressCommand(percentage, imageName, destinationName, model);
    }
  }
}