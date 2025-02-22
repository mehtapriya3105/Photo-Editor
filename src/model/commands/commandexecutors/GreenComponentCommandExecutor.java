package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.rgbimage.GreenComponentCommand;

/**
 * Executes the green component extraction command to process an image and extract
 * its green color component. This class constructs a GreenComponentCommand using the
 * provided arguments and executes it using the associated image model.
 */
public class GreenComponentCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a GreenComponentCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution (image names, command, parameter)
   * @param model The image model to be operated upon
   */
  public GreenComponentCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the green component command based on the provided arguments.
   * The command is created with the source image name, destination image name, and
   * additional parameters (command and parameter, or mask image) as needed.
   * The method then executes the constructed command and prints the execution status.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;
    if (args.length == 4) {
      m = GreenComponentCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setCommand(args[2])
              .setParam(args[3])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("green component command executed " + status + "!");
    } else if (args.length == 3) {
      m = GreenComponentCommand.createBuilder()
              .setSourceFileName(args[0])
              .setMaskImageName(args[1])
              .setDestFileName(args[2])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("green component command executed " + status + "!");
    } else {
      m = GreenComponentCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("green component command executed " + status + "!");
    }
  }
}
