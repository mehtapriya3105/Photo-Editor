package model.commands.commandexecutors;

import model.DownScaling;
import model.IImageModel;
import model.commands.ModelCommand;

/**
 * Executes the downscale command to resize an image to the specified dimensions.
 * This class constructs a DownScaling command using the provided arguments and executes it
 * using the associated image model.
 */
public class DownscaleCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a DownscaleCommandExecutor with specified model command, arguments, & image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution (image names and new dimensions)
   * @param model The image model to be operated upon
   */
  public DownscaleCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the downscale command based on the provided arguments.
   * The command is created with the original image name, the destination image name,
   * and the new height and width for the image.
   * The method then executes the constructed command and prints the execution status.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;
    m = DownScaling.createBuilder()
            .setOriginalImageName(args[0])
            .setDestinationImageName(args[1])
            .setNewHeight(args[2])
            .setNewWidth(args[3])
            .setModel(model)
            .build();
    status = m.execute();
    System.out.println("DownScale command executed " + status + "!");
  }
}
