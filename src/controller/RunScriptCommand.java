package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.IImageModel;

/**
 * RunScriptCommand reads a script file containing commands and executes them using CommandMappernew
 * to route them to the appropriate handler.
 */
public class RunScriptCommand implements ControllerCommand {

  private final IImageModel model;

  /**
   * Constructor for RunScriptCommand. Initializes the ImageModel instance to be used in commands.
   *
   * @param model The ImageModel instance required by ModelCommandHandler.
   */
  public RunScriptCommand(IImageModel model) {
    this.model = model;
  }

  /**
   * Executes the run script command. This method takes a script file as an argument, reads each
   * line of the script, and attempts to parse and execute the command found in each line. The
   * commands are routed to the appropriate handler using CommandMappernew. If any errors occur
   * while executing commands, they are caught and logged.
   *
   * @param args The arguments required to run the script. The first argument should be the name of
   *             the script file to be read.
   * @throws IOException If there is an issue reading the script file, an IOException is thrown.
   */
  @Override
  public void execute(String[] args) throws IOException {
    if (args.length < 1) {
      throw new IllegalArgumentException("No script file specified.");
    }

    String commandFile = args[0];

    try (BufferedReader reader = new BufferedReader(new FileReader(commandFile))) {
      String line;

      CommandMappernew commandMapper = new CommandMappernew(model);

      while ((line = reader.readLine()) != null) {
        CommandParser.ParsedCommand parsedCommand = CommandParser.parse(line.trim());

        if (parsedCommand != null) {
          String commandName = parsedCommand.getCommandName();
          String[] commandArgs = parsedCommand.getArguments();

          try {
            commandMapper.routeCommand(commandName, commandArgs);
          } catch (Exception e) {
            System.err.println("Error executing command: " + e.getMessage());
          }
        } else {
          if (line.length() == 0) {
            continue;
          } else {
            System.out.println("Failed to parse command: " + line.trim());
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading script file: " + e.getMessage());
      throw e;
    }
  }
}
