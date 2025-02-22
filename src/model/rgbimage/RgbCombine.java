package model.rgbimage;

import java.io.FileNotFoundException;

import model.IImageModel;
import model.commands.ModelCommand;

/**
 * RgbCombine is a concrete implementation of AbstractRGB2, which combines individual RGB color
 * components into a single image.
 */
public class RgbCombine implements ModelCommand {

  protected String imageName;
  protected String redImageName;
  protected String greenImageName;
  protected String blueImageName;
  protected IImageModel model;

  /**
   * Constructs a RgbCombine instance to combine RGB components into a new image.
   *
   * @param imageName      the name of the resulting combined image
   * @param redImageName   the name of the image containing the red component
   * @param greenImageName the name of the image containing the green component
   * @param blueImageName  the name of the image containing the blue component
   */
  private RgbCombine(String imageName, String redImageName, String greenImageName,
                     String blueImageName, IImageModel model) {
    this.imageName = imageName;
    this.redImageName = redImageName;
    this.greenImageName = greenImageName;
    this.blueImageName = blueImageName;
    this.model = model;
  }

  /**
   * Creates a new builder instance for constructing RgbCombine objects.
   *
   * @return an instance of RgbCombineBuilder
   */
  public static RgbCombineBuilder createBuilder() {
    return new RgbCombineBuilder();
  }

  /**
   * Executes the RGB combination process by merging the red, green, and blue components into a
   * single image.
   *
   * @throws FileNotFoundException if any of the input image files cannot be found
   */
  @Override
  public boolean execute() throws FileNotFoundException {
    this.model.rgbCombineMethod(redImageName, greenImageName, blueImageName, imageName);
    return true;
  }

  /**
   * Builder class for constructing RgbCombine instances.
   */
  public static class RgbCombineBuilder {

    protected String imageName;
    protected String redImageName;
    protected String greenImageName;
    protected String blueImageName;
    protected IImageModel model;

    /**
     * Sets the name of the resulting combined image.
     *
     * @param imageName the name of the combined image
     * @return the builder instance for method chaining
     */
    public RgbCombineBuilder setImageName(String imageName) {
      this.imageName = imageName;
      return this;
    }

    /**
     * Sets the name of the image containing the red color component.
     *
     * @param redImageName the name of the red component image
     * @return the builder instance for method chaining
     */
    public RgbCombineBuilder setRedImageName(String redImageName) {
      this.redImageName = redImageName;
      return this;
    }

    /**
     * Sets the name of the image containing the green color component.
     *
     * @param greenImageName the name of the green component image
     * @return the builder instance for method chaining
     */
    public RgbCombineBuilder setGreenImageName(String greenImageName) {
      this.greenImageName = greenImageName;
      return this;
    }

    /**
     * Sets the name of the image containing the blue color component.
     *
     * @param blueImageName the name of the blue component image
     * @return the builder instance for method chaining
     */
    public RgbCombineBuilder setBlueImageName(String blueImageName) {
      this.blueImageName = blueImageName;
      return this;
    }

    /**
     * Sets model for the command.
     *
     * @param model is the model.
     * @return the object.
     */
    public RgbCombineBuilder setModel(IImageModel model) {
      this.model = model;
      return this;
    }

    /**
     * Builds and returns a new RgbCombine instance with the specified properties.
     *
     * @return a new instance of RgbCombine
     */
    public RgbCombine build() {
      return new RgbCombine(imageName, redImageName, greenImageName, blueImageName, model);
    }
  }
}
