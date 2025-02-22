package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.filters.IntensityCommand;

/**
 * Executes the intensity command to adjust or extract the intensity component of an image.
 * This class constructs an IntensityCommand using the provided arguments
 * and executes it using the associated image model.
 */
public class IntensityComponentComamandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs an IntensityComponentCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for command execution (source and destination image names,
   *              optional mask image name and parameters)
   * @param model The image model to be operated upon
   */
  public IntensityComponentComamandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the intensity command based on the provided arguments.
   * The command is created with the source file name, destination file name, and other optional
   * parameters.
   * After constructing the command, it is executed, and the execution status is printed.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;

    // Command with 4 arguments (source, destination, command, and parameter)
    if (args.length == 4) {
      m = IntensityCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setCommand(args[2])
              .setParam(args[3])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("intensity command executed " + status + "!");
    }
    // Command with 3 arguments (source, mask image, and destination)
    else if (args.length == 3) {
      m = IntensityCommand.createBuilder()
              .setSourceFileName(args[0])
              .setMaskImageName(args[1])
              .setDestFileName(args[2])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("intensity command executed " + status + "!");
    }
    // Command with 2 arguments (source and destination)
    else {
      m = IntensityCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("intensity command executed " + status + "!");
    }
  }
}
