package controller;

/**
 * CommandParser is responsible for parsing input strings and extracting command names along with
 * their arguments.
 */
public class CommandParser {


  /**
   * Parses the input string into a command name and its arguments. Returns a ParsedCommand object
   * containing the extracted command and arguments. If the input is empty or a comment (starting
   * with "#"-comments), it returns null.
   *
   * @param input The input string to parse.
   * @return A ParsedCommand object containing the command name and arguments, or null if invalid.
   */
  public static ParsedCommand parse(String input) {
    if (input == null || input.trim().isEmpty() || input.trim().startsWith("#")) {
      return null;
    }

    String[] commandArgs = input.trim().split("\\s+");

    String commandName = commandArgs[0];
    String[] commandToPass = new String[commandArgs.length - 1];

    if (commandArgs.length > 1) {
      System.arraycopy(commandArgs, 1, commandToPass, 0, commandArgs.length - 1);
    }

    return new ParsedCommand(commandName, commandToPass);
  }


  /**
   * ParsedCommand is a static nested class that encapsulates the parsed command name and the
   * associated arguments.
   */
  public static class ParsedCommand {

    private final String commandName;
    private final String[] arguments;

    /**
     * Constructs a ParsedCommand object with the provided command name and arguments.
     *
     * @param commandName The name of the parsed command.
     * @param arguments   The arguments associated with the parsed command.
     */
    public ParsedCommand(String commandName, String[] arguments) {
      this.commandName = commandName;
      this.arguments = arguments;
    }

    /**
     * Returns the parsed command name.
     *
     * @return The command name as a String.
     */
    public String getCommandName() {
      return commandName;
    }

    /**
     * Returns the parsed arguments.
     *
     * @return An array of Strings representing the arguments.
     */
    public String[] getArguments() {
      return arguments;
    }
  }
}
