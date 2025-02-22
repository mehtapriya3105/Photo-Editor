package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import model.IImageModel;

/**
 * ImageProcessorController class is responsible for managing the input and interaction with user.
 * It continuously listens for user input, parses commands, and routes them to appropriate handlers
 * through CommandMapper. This controller is designed to run until manually stopped or terminated.
 */
public class ImageProcessorController {

  private final CommandMappernew factory;
  private Readable in;

  /**
   * This method starts the controller and listens for user input in an infinite loop. It reads
   * input from the console using a BufferedReader, trims any leading/trailing whitespace, and
   * checks if the input is valid. Blank lines and comment lines (starting with "#") are ignored. If
   * the input is a valid command, it is parsed and routed to the appropriate command handler
   * (controller or model). Any input/output errors during reading are caught and reported. Other
   * exceptions that occur during command execution are thrown as runtime exceptions.
   */
  public ImageProcessorController(Readable inputStreamReader, IImageModel model) {
    this.in = inputStreamReader;
    this.factory = new CommandMappernew(model);
  }

  /**
   * This method starts the controller.
   */
  public void start() {
    BufferedReader reader = new BufferedReader((Reader) this.in);
    String input;
    try {
      while (true) {
        input = reader.readLine(); // Read the next line from input
        if (input == null) { // Handle end of input
          break; // Exit the loop if no more input
        }
        input = input.trim();
        if (input.isBlank() || input.startsWith("#")) {
          continue;
        }
        CommandParser.ParsedCommand parsedCommand = CommandParser.parse(input);

        if (parsedCommand != null) {
          factory.routeCommand(parsedCommand.getCommandName(), parsedCommand.getArguments());
        }

      }
    } catch (IOException e) {
      System.err.println("Error reading input: " + e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
