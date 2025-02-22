package model.rgbimage;

import java.io.FileNotFoundException;

import model.IImageModel;
import model.commands.ModelCommand;

/**
 * RgbSplitCommand is a concrete implementation of AbstractRGB2, which splits an image into its
 * individual red, green, and blue components.
 */
public class RgbSplitCommand implements ModelCommand {

  private String imageName;
  private String redImageName;
  private String greenImageName;
  private String blueImageName;
  private IImageModel model;

  /**
   * Constructs an RgbSplitCommand instance with the specified image names for splitting.
   *
   * @param imageName      the name of the image to be split
   * @param redImageName   the name for the red component image
   * @param greenImageName the name for the green component image
   * @param blueImageName  the name for the blue component image
   */
  private RgbSplitCommand(String imageName, String redImageName, String greenImageName,
                          String blueImageName, IImageModel model) {
    this.imageName = imageName;
    this.redImageName = redImageName;
    this.greenImageName = greenImageName;
    this.blueImageName = blueImageName;
    this.model = model;
  }

  /**
   * Creates a new builder instance for constructing RgbSplitCommand objects.
   *
   * @return an instance of RgbSplitCommandBuilder
   */
  public static RgbSplitCommandBuilder createBuilder() {
    return new RgbSplitCommandBuilder();
  }

  /**
   * Executes the RGB splitting operation by retrieving the original image and splitting it into
   * separate red, green, and blue components.
   *
   * @throws FileNotFoundException if the specified image cannot be found
   */
  @Override
  public boolean execute() throws FileNotFoundException {
    this.model.rgbSplitMethod(redImageName, greenImageName, blueImageName, imageName);
    return true;
  }

  /**
   * Builder class for constructing RgbSplitCommand instances.
   */
  public static class RgbSplitCommandBuilder {

    protected String imageName;
    protected String redImageName;
    protected String greenImageName;
    protected String blueImageName;
    protected IImageModel model;

    /**
     * Sets the name of the image to be split.
     *
     * @param imageName the name of the image to be split
     * @return the builder instance for method chaining
     */
    public RgbSplitCommandBuilder setImageName(String imageName) {
      this.imageName = imageName;
      return this;
    }

    /**
     * Sets the name for the image containing the red color component.
     *
     * @param redImageName the name for the red component image
     * @return the builder instance for method chaining
     */
    public RgbSplitCommandBuilder setRedImageName(String redImageName) {
      this.redImageName = redImageName;
      return this;
    }

    /**
     * Sets the name for the image containing the green color component.
     *
     * @param greenImageName the name for the green component image
     * @return the builder instance for method chaining
     */
    public RgbSplitCommandBuilder setGreenImageName(String greenImageName) {
      this.greenImageName = greenImageName;
      return this;
    }

    /**
     * Sets the name for the image containing the blue color component.
     *
     * @param blueImageName the name for the blue component image
     * @return the builder instance for method chaining
     */
    public RgbSplitCommandBuilder setBlueImageName(String blueImageName) {
      this.blueImageName = blueImageName;
      return this;
    }

    /**
     * Sets model for the command.
     *
     * @param model is the model.
     * @return the object.
     */
    public RgbSplitCommandBuilder setModel(IImageModel model) {
      this.model = model;
      return this;
    }

    /**
     * Builds and returns a new RgbSplitCommand instance with the specified properties.
     *
     * @return a new instance of RgbSplitCommand
     */
    public RgbSplitCommand build() {
      return new RgbSplitCommand(imageName, redImageName, greenImageName, blueImageName, model);
    }
  }
}
