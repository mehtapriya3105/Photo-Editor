package model;

import java.io.FileNotFoundException;

import model.commands.ModelCommand;

/**
 * BrightenCommand is responsible for adjusting the brightness of an image. It implements the
 * ModelCommand interface and provides the functionality to brighten an image by a specified amount
 * and store the result.
 */
public class BrightenCommand implements ModelCommand {

  private String imageName;
  private Integer brightness;
  private String destinationName;
  private IImageModel model;

  /**
   * Constructs a BrightenCommand instance with the specified parameters.
   *
   * @param imageName       the name of the image to be brightened
   * @param brightness      the amount to adjust the brightness, can be positive or negative
   * @param destinationName the name under which the brightened image will be stored
   * @param model           the image model that provides the brighten functionality
   */
  private BrightenCommand(String imageName, Integer brightness, String destinationName,
                          IImageModel model) {
    this.imageName = imageName;
    this.brightness = brightness;
    this.destinationName = destinationName;
    this.model = model;
  }

  /**
   * Creates a builder instance for constructing a BrightenCommand.
   *
   * @return a new instance of BrightenCommandBuilder
   */
  public static BrightenCommandBuilder createBuilder() {
    return new BrightenCommandBuilder();
  }

  /**
   * Executes the brighten command by adjusting the brightness of the specified image. This method
   * retrieves the image using the image name, applies the specified brightness adjustment, and
   * stores the resulting image under the destination name. If the image is not found, it returns
   * without performing any operations.
   *
   * @throws FileNotFoundException if the specified image file is not found in the ImageManager
   */
  @Override
  public boolean execute() throws FileNotFoundException {
    this.model.brightenMethod(brightness, imageName, destinationName);
    return true;
  }

  /**
   * Builder class for constructing instances of BrightenCommand with a fluent interface.
   */
  public static class BrightenCommandBuilder {

    private String imageName;
    private Integer brightness;
    private String destinationName;
    private IImageModel model;

    /**
     * Sets the name of the image to be brightened.
     *
     * @param imageName the name of the image
     * @return the current instance of BrightenCommandBuilder
     */
    public BrightenCommandBuilder setImageName(String imageName) {
      this.imageName = imageName;
      return this;
    }

    /**
     * Sets the brightness adjustment value.
     *
     * @param brightness the amount to adjust the brightness, as a string
     * @return the current instance of BrightenCommandBuilder
     */
    public BrightenCommandBuilder setBrightness(String brightness) {
      this.brightness = Integer.valueOf(brightness);
      return this;
    }

    /**
     * Sets the name under which the brightened image will be stored.
     *
     * @param destinationName the name of the destination image
     * @return the current instance of BrightenCommandBuilder
     */
    public BrightenCommandBuilder setDestinationName(String destinationName) {
      this.destinationName = destinationName;
      return this;
    }

    /**
     * Sets the image model that provides the brighten functionality.
     *
     * @param model the image model
     * @return the current instance of BrightenCommandBuilder
     */
    public BrightenCommandBuilder setModel(IImageModel model) {
      this.model = model;
      return this;
    }

    /**
     * Builds and returns an instance of BrightenCommand. Ensures all required fields are set before
     * constructing the command.
     *
     * @return a new instance of BrightenCommand
     * @throws IllegalStateException if any required field is missing
     */
    public BrightenCommand build() {
      if (imageName == null || brightness == null || destinationName == null || model == null) {
        throw new IllegalStateException("Missing required fields for BrightenCommand");
      }
      return new BrightenCommand(imageName, brightness, destinationName, model);
    }
  }
}