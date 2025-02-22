package controller;

import java.util.HashMap;
import java.util.Map;

import model.IImageModel;
import model.commands.ModelCommandHandler;


/**
 * CommandMapper routes commands based on their type. It directs load, save, run, and quit commands
 * to controllerCommandHandler, while all other commands are directed to ModelCommandHandler.
 */
public class CommandMappernew {

  private static Map<String, String> commandTypes;
  private final ControllerCommandHandler controllerHandler;
  private final ModelCommandHandler model;

  /**
   * Constructor for CommandMappernew. Initializes the controller and model handlers and registers
   * the command types.
   */
  public CommandMappernew(IImageModel model) {
    controllerHandler = new ControllerCommandHandler(model);
    this.model = new ModelCommandHandler(model);
    registerCommandTypes();
  }

  /**
   * Registers command types for routing. This method is private because it is only used internally
   * by the constructor to set up the mapping of command names to handler types.
   */
  private void registerCommandTypes() {
    commandTypes = new HashMap<>();
    // Commands that belong to the controller
    commandTypes.put("load", "controller");
    commandTypes.put("save", "controller");
    commandTypes.put("run", "controller");
    commandTypes.put("quit", "controller");

    commandTypes.put("red-component", "model");
    commandTypes.put("horizontal-flip", "model");
    commandTypes.put("vertical-flip", "model");
    commandTypes.put("brighten", "model");
    commandTypes.put("rgb-split", "model");
    commandTypes.put("blur", "model");
    commandTypes.put("sharpen", "model");
    commandTypes.put("sepia", "model");
    commandTypes.put("green-component", "model");
    commandTypes.put("blue-component", "model");
    commandTypes.put("value-component", "model");
    commandTypes.put("luma-component", "model");
    commandTypes.put("intensity-component", "model");
    commandTypes.put("rgb-combine", "model");
    commandTypes.put("histogram", "model");
    commandTypes.put("color-correct", "model");
    commandTypes.put("level-adjust", "model");
    commandTypes.put("compress", "model");
  }

  /**
   * Routes the command to the appropriate handler based on the command type. This is a public
   * method because it is intended to be called externally to route commands.
   *
   * @param commandName the name of the command to be routed.
   * @param args        the arguments to be passed to the handler.
   * @throws Exception if the command handler throws any exception during execution.
   */
  public void routeCommand(String commandName, String[] args) throws Exception {
    String type = commandTypes.get(commandName.toLowerCase());

    if (type == null) {
      throw new IllegalArgumentException("Unknown command: " + commandName);
    }

    if (type.equals("controller")) {

      controllerHandler.handle(commandName, args);
    } else if (type.equals("model")) {
      model.handle(commandName, args);
    }
  }
}
