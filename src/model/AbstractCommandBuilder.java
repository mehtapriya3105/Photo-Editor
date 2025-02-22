package model;

import java.io.FileNotFoundException;

/**
 * A generic `AbstractCommandBuilder` class designed to handle the creation of command objects with
 * flexible configurations. This builder supports commands requiring either two arguments (source
 * and destination file names) or four arguments (source file, destination file, command, and
 * parameter).
 */
public abstract class AbstractCommandBuilder<T> {

  protected String sourceFileName;

  protected String destFileName;

  protected String cmand;

  protected String p;

  protected String mask;

  protected String maskedImage;

  protected IImageModel model;

  /**
   * Default constructor that initializes all fields to null. This allows for a flexible setup where
   * each attribute can be set individually.
   */
  public AbstractCommandBuilder() {
    this.sourceFileName = null;
    this.destFileName = null;
    this.cmand = null;
    this.p = null;
    this.model = null;
    this.mask = null;
    this.maskedImage = null;
  }

  /**
   * Sets the source file name for the command.
   *
   * @param sourceFileName The name of the source file.
   * @return The builder instance for method chaining.
   */
  public AbstractCommandBuilder<T> setSourceFileName(String sourceFileName) {
    this.sourceFileName = sourceFileName;
    return this;
  }

  /**
   * Sets the destination file name for the command.
   *
   * @param destFileName The name of the destination file.
   * @return The builder instance for method chaining.
   */
  public AbstractCommandBuilder<T> setDestFileName(String destFileName) {
    this.destFileName = destFileName;
    return this;
  }

  /**
   * Sets the command to be executed.
   *
   * @param cmand The command identifier.
   * @return The builder instance for method chaining.
   */
  public AbstractCommandBuilder<T> setCommand(String cmand) {
    this.cmand = cmand;
    return this;
  }

  /**
   * Sets an additional parameter for the command.
   *
   * @param p The parameter value.
   * @return The builder instance for method chaining.
   */
  public AbstractCommandBuilder<T> setParam(String p) {
    this.p = p;
    return this;
  }

  /**
   * Sets model for the command.
   *
   * @param model The model.
   * @return The builder instance for method chaining.
   */
  public AbstractCommandBuilder<T> setModel(IImageModel model) {
    this.model = model;
    return this;
  }

  /**
   * Sets mask image name for the command.
   *
   * @param maskImageName The string name.
   * @return The builder instance for method chaining.
   */
  public AbstractCommandBuilder<T> setMaskImageName(String maskImageName) {
    this.mask = "mask";
    this.maskedImage = maskImageName;
    return this;
  }

  /**
   * Builds an instance of the specified command class with the given configuration. The method
   * supports commands with either two or four parameters.
   *
   * @param commandClass The class of the command to instantiate.
   * @return An instance of the specified `AbstractImageCommand` class.
   * @throws IllegalStateException    if either the source or destination file name is missing.
   * @throws IllegalArgumentException if instantiation of the command class fails.
   */
  public AbstractImageCommand build(Class<? extends AbstractImageCommand> commandClass) {
    if (sourceFileName == null || destFileName == null) {
      throw new IllegalStateException("Source and destination file names are required.");
    }

    try {
      if (cmand != null && p != null) {
        return (AbstractImageCommand) commandClass.getConstructor(String.class, String.class,
                        String.class, String.class)
                .newInstance(sourceFileName, destFileName, cmand, p, model);
      } else if (maskedImage != null && mask != null) {
        return (AbstractImageCommand) commandClass.getConstructor(String.class, String.class)
                .newInstance(sourceFileName, destFileName, maskedImage, model);
      } else {
        return (AbstractImageCommand) commandClass.getConstructor(String.class, String.class)
                .newInstance(sourceFileName, destFileName, model);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to create command instance: " + e.getMessage());
    }
  }

  /**
   * An abstract method to be implemented by subclasses, defining how a specific command will be
   * built. This allows further customization for different command types.
   *
   * @return An instance of `AbstractImageCommand`.
   */
  public abstract AbstractImageCommand build() throws FileNotFoundException;
}
