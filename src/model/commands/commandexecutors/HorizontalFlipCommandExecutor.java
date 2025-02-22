package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.flip.HorizontalFlipCommand;

/**
 * Executes the horizontal flip command to flip an image horizontally.
 * This class constructs a HorizontalFlipCommand using the provided arguments
 * and executes it using the associated image model.
 */
public class HorizontalFlipCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a HorizontalFlipCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for command execution (source & destination image names)
   * @param model The image model to be operated upon
   */
  public HorizontalFlipCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the horizontal flip command based on the provided arguments.
   * The command is created with the source image name and destination image name.
   * The method then executes the constructed command and prints the execution status.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;
    m = HorizontalFlipCommand.createBuilder()
            .setSourceFileName(args[0])
            .setDestFileName(args[1])
            .setModel(model)
            .build();
    status = m.execute();
    System.out.println("horizontal-flip command executed " + status + "!");
  }
}
