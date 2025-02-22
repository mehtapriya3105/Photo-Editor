package model.commands.commandexecutors;

import model.CompressCommand;
import model.IImageModel;
import model.commands.ModelCommand;

/**
 * Executes the compress command on an image by reducing its size based on a given percentage.
 * This class constructs CompressCommand with specified arguments and executes it using associated
 * image model.
 */
public class CompressCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a CompressCommandExecutor with specified model command, arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution
   * @param model The image model to be operated upon
   */
  public CompressCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the compress command based on the provided arguments.
   * The command is created with a percentage to reduce the image size, the image name,
   * and the destination name to save the compressed image.
   * The method then executes the constructed command and prints the execution status.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;
    m = CompressCommand.createBuilder()
            .setPercentage(args[0])
            .setImageName(args[1])
            .setDestinationName(args[2])
            .setModel(model)
            .build();
    status = m.execute();
    System.out.println("compress command executed " + status + "!");
  }
}
