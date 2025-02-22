import controller.GuiController;
import controller.ImageProcessorController;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import model.IImageModel;
import model.ImageModel;
import view.IView;
import view.JFrameView;

/**
 * The ImageProcessorApp class serves as the entry point for the image processing application. It
 * provides different modes for executing the application based on the command-line arguments: -
 * Scripted file mode (`-file`): Executes a series of commands from a script file. - Text-based mode
 * (`-text`): Runs in interactive text mode, accepting command from standard i/p. - GUI mode (no
 * arguments): Launches a graphical user interface for image processing.
 */
public class ImageProcessorApp {

  /**
   * The main method is entry point of application. Based on the provided command-line arguments, it
   * initializes the appropriate mode (scripted, text-based, or GUI) and starts the application.
   */
  public static void main(String[] args) throws Exception {

    IImageModel imageModel = new ImageModel();

    IView view;

    ImageProcessorController controller;

    // Mode selection based on command-line arguments
    if (args.length == 2 && "-file".equals(args[0])) {
      // Scripted file mode: Reads commands from a script file and executes them
      String scriptFilePath = "run " + args[1] + "\n" + "quit";
      Reader r = new StringReader(scriptFilePath);
      controller = new ImageProcessorController(r, imageModel);
      controller.start();

    } else if (args.length == 1 && "-text".equals(args[0])) {
      // Text-based mode: Accepts user commands interactively from standard input
      controller = new ImageProcessorController(new InputStreamReader(System.in), imageModel);
      controller.start();

    } else if (args.length == 0) {
      // GUI mode: Launches the graphical user interface
      GuiController guiController = new GuiController(imageModel);
      view = new JFrameView();
      guiController.setView(view);

    } else {
      System.err.println("Invalid arguments. Usage:");
      System.err.println("java -jar Program.jar -file path-of-script-file");
      System.err.println("java -jar Program.jar -text");
      System.err.println("java -jar Program.jar");
      System.exit(1);
    }
  }
}
