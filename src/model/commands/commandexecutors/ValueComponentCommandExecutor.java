package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.filters.ValueCommand;

/**
 * Executes the Value filter command to manipulate the value component of an image.
 * The Value command can be executed with different parameters, including a custom
 * command and parameters for the effect, or with a mask image.
 */
public class ValueComponentCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a ValueComponentCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution (file names and optional
   *              command parameters)
   * @param model The image model to be operated upon
   */
  public ValueComponentCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the Value command using the provided arguments. The command
   * is created with parameters like the source file, destination file, optional command
   * parameters, and optional mask image. After constructing the command, it is executed,
   * and the execution status is printed.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;

    // Create and execute the Value command with four arguments (command and parameters)
    if (args.length == 4) {
      m = ValueCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setCommand(args[2])
              .setParam(args[3])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("value command executed " + status + "!");
    }
    // Create and execute the Value command with three arguments (mask image)
    else if (args.length == 3) {
      m = ValueCommand.createBuilder()
              .setSourceFileName(args[0])
              .setMaskImageName(args[1])
              .setDestFileName(args[2])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("value command executed " + status + "!");
    }
    // Create and execute the Value command with two arguments (basic source and destination)
    else {
      m = ValueCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("value command executed " + status + "!");
    }
  }
}
