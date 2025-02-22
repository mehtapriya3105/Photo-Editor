package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.flip.VerticalFlipCommand;

/**
 * Executes the Vertical Flip command on an image.
 * The VerticalFlipCommand will flip the image vertically and save it to the specified
 * destination file.
 */
public class VerticalFlipCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a VerticalFlipCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution (file names)
   * @param model The image model to be operated upon
   */
  public VerticalFlipCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the Vertical Flip command using the provided arguments.
   * The command flips the source image vertically and saves the result to the destination file.
   * After constructing the command, it is executed, and the execution status is printed.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;

    // Create and execute the VerticalFlip command with the provided arguments
    m = VerticalFlipCommand.createBuilder()
            .setSourceFileName(args[0])
            .setDestFileName(args[1])
            .setModel(model)
            .build();

    status = m.execute();
    System.out.println("vertical-flip command executed " + status + "!");
  }
}
