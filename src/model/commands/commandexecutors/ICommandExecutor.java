package model.commands.commandexecutors;

/**
 * The ICommandExecutor interface serves as a contract for all command executor classes in the
 * application. Any class implementing this interface must define the behavior for creating
 * commands.
 */
public interface ICommandExecutor {

  /**
   * Creates the commands managed by the implementing command executor.
   *
   * @throws Exception if an error occurs during the creation of commands.
   */
  void createCommands() throws Exception;
}
