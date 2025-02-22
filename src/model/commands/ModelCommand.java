package model.commands;

/**
 * ModelCommand is an interface that represents commands related to image manipulations or
 * operations in the model, such as blur, red-component, green-component, etc.
 */
public interface ModelCommand {

  /**
   * Executes the model command.
   *
   * @throws Exception if an error occurs during execution.
   */
  boolean execute() throws Exception;
}
