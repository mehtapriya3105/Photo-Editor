package model;

import java.io.FileNotFoundException;

import model.commands.ModelCommand;

/**
 * Represents a command to downscale an image to a new width and height. This class follows the
 * Builder design pattern to facilitate the creation of DownScaling objects.
 */
public class DownScaling implements ModelCommand {

  private String originalImageName;
  private String destinationImageName;
  private int newWidth;
  private int newHeight;
  private IImageModel model;

  /**
   * Private constructor to enforce the use of the Builder pattern for creating instances.
   *
   * @param originalImageName    the name of the original image to be downscaled
   * @param destinationImageName the name of the new image after downscaling
   * @param newWidth             the new width of the image
   * @param newHeight            the new height of the image
   * @param model                the {@link ImageModel} used to perform the operation
   * @throws FileNotFoundException    if the original image is not found
   * @throws IllegalArgumentException if the width or height is invalid
   */
  private DownScaling(String originalImageName, String destinationImageName, String newWidth,
                      String newHeight, IImageModel model) throws FileNotFoundException {
    this.originalImageName = originalImageName;
    this.destinationImageName = destinationImageName;
    this.newWidth = Integer.parseInt(newWidth);
    if (this.newWidth < 0 || this.newWidth > model.getImage(originalImageName).getWidth()) {
      throw new IllegalArgumentException("Width must be a positive integer");
    }
    this.newHeight = Integer.parseInt(newHeight);
    if (this.newHeight < 0 || this.newHeight > model.getImage(originalImageName).getHeight()) {
      throw new IllegalArgumentException("Height must be a positive integer");
    }
    this.model = model;
  }

  /**
   * Creates a new instance of {@link DownScalingBuilder} to construct a DownScaling object.
   *
   * @return a new {@link DownScalingBuilder}
   */
  public static DownScalingBuilder createBuilder() {
    return new DownScalingBuilder();
  }

  /**
   * Executes the downscaling operation by invoking the image downscaling method in the
   * {@link ImageModel}.
   *
   * @return true if the operation was successful
   * @throws FileNotFoundException if the original image is not found
   */
  @Override
  public boolean execute() throws FileNotFoundException {
    model.imageDownScalling(originalImageName, destinationImageName, newHeight, newWidth);
    return true;
  }

  /**
   * Builder class for constructing {@link DownScaling} objects.
   */
  public static class DownScalingBuilder {

    private String originalImageName;
    private String destinationImageName;
    private String newWidth;
    private String newHeight;
    private IImageModel model;

    /**
     * Sets the name of the original image to be downscaled.
     *
     * @param originalImageName the name of the original image
     * @return the current {@link DownScalingBuilder} instance
     */
    public DownScalingBuilder setOriginalImageName(String originalImageName) {
      this.originalImageName = originalImageName;
      return this;
    }

    /**
     * Sets the name of the destination image after downscaling.
     *
     * @param destinationImageName the name of the destination image
     * @return the current {@link DownScalingBuilder} instance
     */
    public DownScalingBuilder setDestinationImageName(String destinationImageName) {
      this.destinationImageName = destinationImageName;
      return this;
    }

    /**
     * Sets the new width for the downscaled image.
     *
     * @param newWidth the new width as a string
     * @return the current {@link DownScalingBuilder} instance
     */
    public DownScalingBuilder setNewWidth(String newWidth) {
      this.newWidth = newWidth;
      return this;
    }

    /**
     * Sets the new height for the downscaled image.
     *
     * @param newHeight the new height as a string
     * @return the current {@link DownScalingBuilder} instance
     */
    public DownScalingBuilder setNewHeight(String newHeight) {
      this.newHeight = newHeight;
      return this;
    }

    /**
     * Sets the {@link ImageModel} to be used for the downscaling operation.
     *
     * @param model the {@link ImageModel}
     * @return the current {@link DownScalingBuilder} instance
     */
    public DownScalingBuilder setModel(IImageModel model) {
      this.model = model;
      return this;
    }

    /**
     * Builds and returns a {@link DownScaling} object with the specified parameters.
     *
     * @return a new {@link DownScaling} object
     * @throws FileNotFoundException if the original image is not found
     */
    public DownScaling build() throws FileNotFoundException {
      return new DownScaling(originalImageName, destinationImageName, newWidth, newHeight, model);
    }
  }
}
