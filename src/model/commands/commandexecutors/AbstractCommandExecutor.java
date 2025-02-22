package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;

/**
 * Serves as an abstract base class for command executors.
 * Provides common structure and fields for handling image-related commands.
 * Subclasses should implement specific command execution logic.
 */
public abstract class AbstractCommandExecutor implements ICommandExecutor {

  protected ModelCommand m;
  protected String[] args;
  protected IImageModel model;

  /**
   * Constructs an AbstractCommandExecutor with the specified parameters.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution
   * @param model The image model to be operated upon
   */
  public AbstractCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    this.m = m;
    this.args = args;
    this.model = model;
  }

  /**
   * Creates and executes the command(s) as per the implementation in subclasses.
   *
   * @throws Exception if any error occurs during the creation or execution of commands
   */
  public abstract void createCommands() throws Exception;
}
