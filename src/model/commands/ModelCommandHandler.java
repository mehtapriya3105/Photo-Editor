package model.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import model.IImageModel;
import model.commands.commandexecutors.BlueComponentCommandExecutor;
import model.commands.commandexecutors.BlurCommandExecutor;
import model.commands.commandexecutors.BrightenCommandExecutor;
import model.commands.commandexecutors.ColorCorrectCommandExecutor;
import model.commands.commandexecutors.CompressCommandExecutor;
import model.commands.commandexecutors.GreenComponentCommandExecutor;
import model.commands.commandexecutors.HistogramCommandExecutor;
import model.commands.commandexecutors.HorizontalFlipCommandExecutor;
import model.commands.commandexecutors.IntensityComponentComamandExecutor;
import model.commands.commandexecutors.LevelAbjustCommandExecutor;
import model.commands.commandexecutors.LumaComponentCommandExecutor;
import model.commands.commandexecutors.RGBCombineCommandExecutor;
import model.commands.commandexecutors.RGBSplitCommandExecutor;
import model.commands.commandexecutors.RedComponentCommandExecutor;
import model.commands.commandexecutors.SepiaCommandExecutor;
import model.commands.commandexecutors.SharpenCommandExecutor;
import model.commands.commandexecutors.ValueComponentCommandExecutor;
import model.commands.commandexecutors.VerticalFlipCommandExecutor;

/**
 * Handles commands related to image manipulations or operations in the model. This class serves as
 * a command manager that registers and executes various image-related commands.
 */
public class ModelCommandHandler {

  private final IImageModel model;
  private final Map<String, BiConsumer<String[], IImageModel>> commandMap;

  /**
   * Constructs a ModelCommandHandler with the specified image model.
   * Initializes and registers all supported commands in the command map.
   *
   * @param model2 the image model to be used for command execution
   */
  public ModelCommandHandler(IImageModel model2) {
    this.model = model2;
    this.commandMap = new HashMap<>();

    commandMap.put("blur", (args, model) -> {
      try {
        new BlurCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("sharpen", (args, model) -> {
      try {
        new SharpenCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("intensity-component", (args, model) -> {
      try {
        new IntensityComponentComamandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("value-component", (args, model) -> {
      try {
        new ValueComponentCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("luma-component", (args, model) -> {
      try {
        new LumaComponentCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("sepia", (args, model) -> {
      try {
        new SepiaCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("horizontal-flip", (args, model) -> {
      try {
        new HorizontalFlipCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("vertical-flip", (args, model) -> {
      try {
        new VerticalFlipCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("red-component", (args, model) -> {
      try {
        new RedComponentCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("green-component", (args, model) -> {
      try {
        new GreenComponentCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("blue-component", (args, model) -> {
      try {
        new BlueComponentCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("rgb-split", (args, model) -> {
      try {
        new RGBSplitCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("rgb-combine", (args, model) -> {
      try {
        new RGBCombineCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("histogram", (args, model) -> {
      try {
        new HistogramCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("color-correct", (args, model) -> {
      try {
        new ColorCorrectCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("level-adjust", (args, model) -> {
      try {
        new LevelAbjustCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("brighten", (args, model) -> {
      try {
        new BrightenCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put("compress", (args, model) -> {
      try {
        new CompressCommandExecutor(null, args, model).createCommands();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  /**
   * Handles the execution of a command by its name and associated arguments.
   *
   * @param commandName The name of the command to execute
   * @param args        The arguments required for the command execution
   * @throws Exception if the command is unknown or fails during execution
   */
  public void handle(String commandName, String[] args) throws Exception {
    BiConsumer<String[], IImageModel> commandExecutor = commandMap.get(commandName);
    if (commandExecutor != null) {
      commandExecutor.accept(args, model);
    } else {
      throw new Exception("Command not recognized!");
    }
  }
}
