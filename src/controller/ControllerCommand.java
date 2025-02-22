package controller;

/**
 * ControllerCommand is an interface that represents commands related to controller operations, such
 * as loading, saving, running scripts, or quitting the application.
 */
public interface ControllerCommand {

  /**
   * Executes the controller command.
   *
   * @param args The arguments for the command.
   * @throws Exception if an error occurs during execution.
   */
  void execute(String[] args) throws Exception;
}
