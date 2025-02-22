package model.commands.commandexecutors;

import model.BrightenCommand;
import model.IImageModel;
import model.commands.ModelCommand;

/**
 * Executes the brighten command on an image.
 * This class handles the construction of a BrightenCommand based on the provided arguments
 * and executes it using the associated image model.
 */
public class BrightenCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a BrightenCommandExecutor with specified model command, arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution
   * @param model The image model to be operated upon
   */
  public BrightenCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the brighten command based on the provided arguments.
   * The command is created with brightness value, source image name, and destination image name.
   * The method then executes the constructed command and prints the execution status.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;
    m = BrightenCommand.createBuilder()
            .setImageName(args[1])
            .setBrightness(args[0])
            .setDestinationName(args[2])
            .setModel(model)
            .build();
    status = m.execute();
    System.out.println("brighten command executed " + status + "!");
  }
}
