package controller;

import java.io.IOException;

/**
 * The QuitCommand class implements the ControllerCommand interface and is responsible for
 * terminating the application when executed. This command does not take any arguments.
 */
public class QuitCommand implements ControllerCommand {

  /**
   * Executes the quit command, which terminates the application.
   *
   * @param args An array of arguments, which are ignored for the quit command.
   * @throws IOException This method signature includes IOException, but it does not throw it in
   *                     this implementation. It's present to satisfy ControllerCommand interface.
   */
  @Override
  public void execute(String[] args) throws IOException {
    System.exit(0);
  }
}
