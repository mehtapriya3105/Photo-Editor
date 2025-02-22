package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.rgbimage.RgbSplitCommand;

/**
 * Executes the RGB split command to separate a single RGB image into its individual
 * red, green, and blue components. This class constructs an RgbSplit command using
 * the provided arguments and executes it using the associated image model.
 */
public class RGBSplitCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs an RGBSplitCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for command execution (image file names for RGB components)
   * @param model The image model to be operated upon
   */
  public RGBSplitCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the RGB split command using the provided arguments.
   * The command is created with parameters like the image name and the names
   * for the red, green, and blue components to store the split results. After
   * constructing the command, it is executed, and the execution status is printed.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;

    // Create and execute the RGB split command
    m = RgbSplitCommand.createBuilder()
            .setImageName(args[0])
            .setRedImageName(args[1])
            .setGreenImageName(args[2])
            .setBlueImageName(args[3])
            .setModel(model)
            .build();

    // Execute the command and print status
    status = m.execute();
    System.out.println("rgb-split command executed " + status + "!");
  }
}
