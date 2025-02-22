package controller;

import java.util.HashMap;
import java.util.Map;

import controller.loadsave.LoadCommand;
import controller.loadsave.SaveCommand;
import model.IImageModel;

/**
 * Handles commands related to the controller, such as load, save, run, and quit. It maintains a
 * registry of commands and executes them based on the provided command name.
 */
public class ControllerCommandHandler {

  private final Map<String, ControllerCommand> controllerCommands;
  private final IImageModel model;

  /**
   * Constructor for ControllerCommandHandler. It initializes the map that holds the available
   * commands and calls the method to register them. This setup ensures that when a command is
   * received, it can be executed by looking it up in the map.
   *
   * @param model The ImageModel instance required by commands that need a model reference.
   */
  public ControllerCommandHandler(IImageModel model) {
    this.model = model;
    controllerCommands = new HashMap<>();
    registerCommands();
  }

  /**
   * This method registers the commands specific to the controller, associating each command name
   * with its corresponding command object.
   */
  private void registerCommands() {
    controllerCommands.put("load", new LoadCommand(model));
    controllerCommands.put("save", new SaveCommand(model));
    controllerCommands.put("run", new RunScriptCommand(model));
    controllerCommands.put("quit", new QuitCommand());
  }

  /**
   * Handles the execution of a command based on the command name, with error handling.
   *
   * @param commandName The name of the command to execute.
   * @param args        The arguments required by the command.
   * @throws Exception If the command execution fails or is not recognized.
   */
  public void handle(String commandName, String[] args) {
    ControllerCommand command = controllerCommands.get(commandName.toLowerCase());

    if (command == null) {
      System.err.println("Error: Unknown controller command - " + commandName);
      return;
    }

    try {
      command.execute(args);
    } catch (IllegalArgumentException e) {
      System.err.println("Error executing command '" + commandName + "': Invalid arguments - "
              + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error executing command '" + commandName + "': " + e.getMessage());
      e.printStackTrace(); // Optional: Print stack trace for deeper debugging
    }
  }
}
