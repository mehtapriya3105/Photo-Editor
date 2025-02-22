package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.rgbimage.BlueComponentCommand;

/**
 * Executes the blue component extraction command on an image.
 * This class handles the construction of a BlueComponentCommand based on the provided arguments
 * and executes it using the associated image model.
 */
public class BlueComponentCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a BlueComponentCommandExecutor with specified model command, arguments, image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution
   * @param model The image model to be operated upon
   */
  public BlueComponentCommandExecutor(ModelCommand m, String[] args,
                                      IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the blue component extraction command based on the provided arguments.
   * The command's behavior varies depending on the number of arguments:
   * - If 4 arguments are provided, the command is created with source, destination, command,
   * and parameter.
   * - If 3 arguments are provided, the command is created with source, mask image, and destination.
   * - If 2 arguments are provided, the command is created with source and destination only.
   * The method then executes the constructed command and prints the execution status.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;
    if (args.length == 4) {
      m = BlueComponentCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setCommand(args[2])
              .setParam(args[3])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("blue component command executed " + status + "!");
    } else if (args.length == 3) {
      m = BlueComponentCommand.createBuilder()
              .setSourceFileName(args[0])
              .setMaskImageName(args[1])
              .setDestFileName(args[2])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("blue component command executed " + status + "!");
    } else {
      m = BlueComponentCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("blue component command executed " + status + "!");
    }
  }
}
