package model;

import model.commands.ModelCommand;

/**
 * AbstractImageCommand is an abstract class that implements the ModelCommand interface. This class
 * provides a base structure for commands that operate on image files, handling common attributes
 * such as source and destination file names, as well as command validation.
 */
public abstract class AbstractImageCommand implements ModelCommand {

  protected String sourceFileName;

  protected String destFileName;

  protected String cmand;

  protected String p;

  protected IImageModel model;

  protected String mask;

  protected String maskedImage;

  /**
   * Constructs an AbstractImageCommand with specified source and destination file names. This
   * constructor is intended for commands that do not require additional parameters.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   */
  public AbstractImageCommand(String sourceFileName, String destFileName, IImageModel model) {
    this.sourceFileName = sourceFileName;
    this.destFileName = destFileName;
    this.model = model;
  }

  /**
   * Constructs an AbstractImageCommand with specified source, destination, masked image and model.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param maskedImage    the name of masked image
   * @param model          the model name
   */
  public AbstractImageCommand(String sourceFileName, String destFileName, String maskedImage,
                              IImageModel model) {
    this.sourceFileName = sourceFileName;
    this.destFileName = destFileName;
    this.maskedImage = maskedImage;
    this.mask = "mask";
    this.model = model;
  }

  /**
   * Constructs an AbstractImageCommand with specified source and destination file names, a command
   * keyword, and a parameter. This constructor validates the command keyword to ensure it is
   * recognized.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command keyword specifying the operation (e.g., "split")
   * @param p              a parameter associated with the command, for additional options or
   *                       values
   * @param model          the model name
   * @throws IllegalArgumentException if the command keyword is not recognized
   */
  public AbstractImageCommand(String sourceFileName, String destFileName, String cmand, String p,
                              IImageModel model) {
    this.sourceFileName = sourceFileName;
    this.destFileName = destFileName;
    if ("split".equals(cmand)) {
      this.cmand = cmand;
    } else {
      throw new IllegalArgumentException("Command value must be 'split'.");
    }
    if (p == null || Integer.parseInt(p) < 0 || Integer.parseInt(p) > 100) {
      throw new IllegalArgumentException("Percent must be between 0 and 100 and cannot be null.");
    }
    this.p = p;
    this.model = model;
  }

  /**
   * Executes the model command.
   *
   * @throws Exception if an error occurs during execution.
   */
  public boolean execute() throws Exception {
    boolean success = true;
    try {
      processImage();
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    return success;
  }

  /**
   * This method is called to manipulate the image according to the command.
   *
   * @throws Exception if exception occurs.
   */
  protected abstract void processImage() throws Exception;

}
