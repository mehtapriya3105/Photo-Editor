package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.histogram.ColorCorrectCommand;

/**
 * Executes the color correction command on an image.
 * This class constructs a ColorCorrectCommand based on the provided arguments
 * and executes it using the associated image model.
 */
public class ColorCorrectCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs ColorCorrectCommandExecutor with specified model command, arguments, & image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for the command execution
   * @param model The image model to be operated upon
   */
  public ColorCorrectCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the color correction command based on the provided arguments.
   * The command is created with source & destination file names, optional command, its parameters.
   * The method then executes the constructed command and prints the execution status.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;
    if (args.length == 4) {
      m = ColorCorrectCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setCommand(args[2])
              .setParam(args[3])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("color-correct command executed " + status + "!");
    } else {
      m = ColorCorrectCommand.createBuilder()
              .setSourceFileName(args[0])
              .setDestFileName(args[1])
              .setModel(model)
              .build();
      status = m.execute();
      System.out.println("color-correct command executed " + status + "!");
    }
  }
}
