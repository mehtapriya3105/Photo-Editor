package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.histogram.LevelAdjustCommand;

/**
 * Executes the level adjust command to adjust the image's brightness, contrast, or other color
 * properties.
 * This class constructs a LevelAdjustCommand using the provided arguments and executes it using
 * the associated image model.
 */
public class LevelAbjustCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a LevelAdjustCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution (adjustment parameters,
   *              image name, destination name)
   * @param model The image model to be operated upon
   */
  public LevelAbjustCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the level adjust command based on the provided arguments.
   * The command is created with various parameters like brightness (B), middle tone (M),
   * white point (W), image name, destination name, and additional optional parameters.
   * After constructing the command, it is executed, and the execution status is printed.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;

    // Command with 7 arguments (brightness, middle tone, white point, image name, destination,
    // command, and parameter)
    if (args.length == 7) {
      m = LevelAdjustCommand.createBuilder()
              .setB(args[0])
              .setM(args[1])
              .setW(args[2])
              .setImageName(args[3])
              .setDestinationName(args[4])
              .setCMand(args[5])
              .setP(args[6])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("level-adjust command executed " + status + "!");
    }
    // Command with 5 arguments (brightness, middle tone, white point, image name, and destination)
    else {
      m = LevelAdjustCommand.createBuilder()
              .setB(args[0])
              .setM(args[1])
              .setW(args[2])
              .setImageName(args[3])
              .setDestinationName(args[4])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("level-adjust command executed " + status + "!");
    }
  }
}
