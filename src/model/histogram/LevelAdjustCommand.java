package model.histogram;

import java.io.FileNotFoundException;

import model.IImageModel;
import model.commands.ModelCommand;

/**
 * LevelAdjustCommand is a command that adjusts the black, contrast, and white point of image. This
 * is done using the specified parameters for black (b), midpoint (m), and white point (w).
 */
public class LevelAdjustCommand implements ModelCommand {

  private String imageName;
  private Integer b;
  private Integer m;
  private Integer w;
  private String destinationName;
  private Integer p;
  private String cmand;
  private IImageModel model;

  /**
   * Constructs a LevelAdjustCommand with the specified parameters for image adjustment. This
   * constructor is used when the `cmand` and `p` parameters are not provided.
   *
   * @param imageName       the name of the image to be adjusted.
   * @param b               the black point adjustment value.
   * @param m               the midpoint adjustment value.
   * @param w               the white point adjustment value.
   * @param destinationName the name of the destination image after adjustment.
   * @param model           the image model to execute the command.
   */
  private LevelAdjustCommand(String imageName, Integer b, Integer m, Integer w,
                             String destinationName, IImageModel model) {
    this.p = 0;
    this.imageName = imageName;
    this.b = b;
    this.m = m;
    this.w = w;
    this.destinationName = destinationName;
    this.model = model;
  }

  /**
   * Constructs a LevelAdjustCommand with all specified parameters for image adjustment.
   *
   * @param imageName       the name of the image to be adjusted.
   * @param b               the black point adjustment value.
   * @param m               the midpoint adjustment value.
   * @param w               the white point adjustment value.
   * @param destinationName the name of the destination image after adjustment.
   * @param cmand           the command type (must be "split").
   * @param p               a percentage value (must be between 0 and 100).
   * @param model           the image model to execute the command.
   * @throws IllegalArgumentException if `b`, `m`, and `w` are not in ascending order, if `p` is out
   *                                  of range, or if `cmand` is invalid.
   */
  private LevelAdjustCommand(String imageName, Integer b, Integer m, Integer w,
                             String destinationName, String cmand, String p, IImageModel model) {
    this.imageName = imageName;
    if (b > m || m > w || w < b) {
      throw new IllegalArgumentException("b, m, and w must be in ascending order");
    }
    this.b = b;
    this.m = m;
    this.w = w;
    this.destinationName = destinationName;
    this.p = Integer.parseInt(p);
    if (this.p < 0 || this.p > 100) {
      throw new IllegalArgumentException("p must be between 0 and 100");
    }

    if (!"split".equals(cmand)) {  // Use "split".equals to prevent NullPointerException
      throw new IllegalArgumentException("cmand must be 'split'");
    }
    this.cmand = cmand;
    this.model = model;
  }

  /**
   * Creates and returns a new builder instance for constructing a LevelAdjustCommand.
   *
   * @return A new instance of LevelAdjustCommandBuilder.
   */
  public static LevelAdjustCommandBuilder createBuilder() {
    return new LevelAdjustCommandBuilder();
  }

  /**
   * Executes the level adjustment on the image by applying brightness, contrast, and white point
   * adjustments. The command checks if the input image exists, performs the adjustments, and stores
   * the result in the destination file.
   *
   * @throws FileNotFoundException    If the source image is not found.
   * @throws IllegalArgumentException If the command type is not "split".
   */
  @Override
  public boolean execute() throws FileNotFoundException {
    this.model.levelAdjustMethod(b, m, w, imageName, destinationName, p, cmand);
    return true;
  }

  /**
   * The builder class for constructing a LevelAdjustCommand instance. Allows for easy configuration
   * of the required parameters.
   */
  public static class LevelAdjustCommandBuilder {

    private String p;
    private String imageName;
    private Integer b;
    private Integer m;
    private Integer w;
    private String destinationName;
    private String cmand;
    private IImageModel model;

    /**
     * Sets the image name for the level adjustment command.
     *
     * @param imageName the name of the image to be adjusted.
     * @return the builder instance for chaining.
     */
    public LevelAdjustCommandBuilder setImageName(String imageName) {
      this.imageName = imageName;
      return this;
    }

    /**
     * Sets the black point adjustment value for the level adjustment command.
     *
     * @param b the black point adjustment value as a String.
     * @return the builder instance for chaining.
     */
    public LevelAdjustCommandBuilder setB(String b) {
      this.b = Integer.valueOf(b);
      return this;
    }

    /**
     * Sets the midpoint adjustment value for the level adjustment command.
     *
     * @param m the midpoint adjustment value as a String.
     * @return the builder instance for chaining.
     */
    public LevelAdjustCommandBuilder setM(String m) {
      this.m = Integer.valueOf(m);
      return this;
    }

    /**
     * Sets the white point adjustment value for the level adjustment command.
     *
     * @param w the white point adjustment value as a String.
     * @return the builder instance for chaining.
     */
    public LevelAdjustCommandBuilder setW(String w) {
      this.w = Integer.valueOf(w);
      return this;
    }

    /**
     * Sets the destination name for the adjusted image.
     *
     * @param destinationName the name of the destination image.
     * @return the builder instance for chaining.
     */
    public LevelAdjustCommandBuilder setDestinationName(String destinationName) {
      this.destinationName = destinationName;
      return this;
    }

    /**
     * Sets the command type (must be "split") for the level adjustment command.
     *
     * @param cmand the command type.
     * @return the builder instance for chaining.
     */
    public LevelAdjustCommandBuilder setCMand(String cmand) {
      this.cmand = cmand;
      return this;
    }

    /**
     * Sets the percentage value for the level adjustment command.
     *
     * @param p the percentage value as a String.
     * @return the builder instance for chaining.
     */
    public LevelAdjustCommandBuilder setP(String p) {
      this.p = p;
      return this;
    }

    /**
     * Sets the image model for the level adjustment command.
     *
     * @param model the image model to execute the command.
     * @return the builder instance for chaining.
     */
    public LevelAdjustCommandBuilder setModel(IImageModel model) {
      this.model = model;
      return this;
    }

    /**
     * Builds and returns a new LevelAdjustCommand instance using the configured parameters.
     *
     * @return a new LevelAdjustCommand instance.
     * @throws IllegalStateException if required fields are missing.
     */
    public LevelAdjustCommand build() {
      if (b == null || m == null || w == null || imageName == null || destinationName == null) {
        throw new IllegalStateException("All required fields must be set");
      }

      if (cmand == null && p == null) {
        return new LevelAdjustCommand(imageName, b, m, w, destinationName, model);
      } else {
        return new LevelAdjustCommand(imageName, b, m, w, destinationName, cmand, p, model);
      }
    }
  }
}
