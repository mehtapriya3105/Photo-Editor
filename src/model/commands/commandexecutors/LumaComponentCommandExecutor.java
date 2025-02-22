package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.filters.LumaCommand;

/**
 * Executes the luma component command to modify the luma (brightness) component of an image.
 * This class constructs a LumaCommand using the provided arguments and executes it using the
 * associated image model.
 */
public class LumaComponentCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a LumaComponentCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution (image file names, command,
   *              and parameter)
   * @param model The image model to be operated upon
   */
  public LumaComponentCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the luma command based on the provided arguments.
   * The command is created with various parameters like source file name, destination file name,
   * an optional mask image name, and a command with additional parameters.
   * After constructing the command, it is executed, and the execution status is printed.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;

    // Command with 4 arguments (source file, destination file, command, and parameter)
    if (args.length == 4) {
      m = LumaCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setCommand(args[2])
              .setParam(args[3])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("luma command executed " + status + "!");
    }
    // Command with 3 arguments (source file, mask image, and destination file)
    else if (args.length == 3) {
      m = LumaCommand.createBuilder()
              .setSourceFileName(args[0])
              .setMaskImageName(args[1])
              .setDestFileName(args[2])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("luma command executed " + status + "!");
    }
    // Command with 2 arguments (source file and destination file)
    else {
      m = LumaCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("luma command executed " + status + "!");
    }
  }
}
