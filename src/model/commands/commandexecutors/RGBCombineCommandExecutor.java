package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.rgbimage.RgbCombine;

/**
 * Executes the RGB combine command to merge red, green, and blue image components
 * into a single RGB image. This class constructs an RgbCombine command using
 * the provided arguments and executes it using the associated image model.
 */
public class RGBCombineCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs an RGBCombineCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution (image file names for components)
   * @param model The image model to be operated upon
   */
  public RGBCombineCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the RGB combine command using the provided arguments.
   * The command is created with parameters like the red, green, and blue image names
   * to combine them into a single RGB image. After constructing the command,
   * it is executed, and the execution status is printed.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;

    // Create and execute the RGB combine command
    m = RgbCombine.createBuilder()
            .setImageName(args[0])
            .setRedImageName(args[1])
            .setGreenImageName(args[2])
            .setBlueImageName(args[3])
            .setModel(model)
            .build();

    // Execute the command and print status
    status = m.execute();
    System.out.println("rgb-combine command executed " + status + "!");
  }
}
