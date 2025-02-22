package model.commands.commandexecutors;

import model.IImageModel;
import model.commands.ModelCommand;
import model.histogram.HistogramCommand;

/**
 * Executes the histogram command to generate a histogram for an image.
 * This class constructs a HistogramCommand using the provided arguments
 * and executes it using the associated image model.
 */
public class HistogramCommandExecutor extends AbstractCommandExecutor {

  /**
   * Constructs a HistogramCommandExecutor with the specified model command,
   * arguments, and image model.
   *
   * @param m     The model command to be executed
   * @param args  The arguments required for command execution (source & destination image names)
   * @param model The image model to be operated upon
   */
  public HistogramCommandExecutor(ModelCommand m, String[] args, IImageModel model) {
    super(m, args, model);
  }

  /**
   * Creates and executes the histogram command based on the provided arguments.
   * The command is created with the source image name and destination image name.
   * The method then executes the constructed command and prints the execution status.
   *
   * @throws Exception if any error occurs during the command creation or execution
   */
  @Override
  public void createCommands() throws Exception {
    boolean status;
    m = HistogramCommand.createBuilder()
            .setSourceFileName(args[0])
            .setDestFileName(args[1])
            .setModel(model)
            .build();
    status = m.execute();
    System.out.println("histogram command executed " + status + "!");
  }
}
