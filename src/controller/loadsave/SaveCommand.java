package controller.loadsave;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.ControllerCommand;
import model.IImageModel;

/**
 * SaveCommand is responsible for handling image saving operations. It implements the
 * ControllerCommand interface and uses a map to associate file extensions with their corresponding
 * image savers.
 */
public class SaveCommand implements ControllerCommand {

  private static final Map<String, ILoadSave> saverMap = new HashMap<>();

  // Static block to initialize supported file savers
  static {
    saverMap.put("jpg", new Jpg());
    saverMap.put("jpeg", new Jpeg());
    saverMap.put("png", new Png());
    saverMap.put("ppm", new Ppm());
  }

  private IImageModel imageModel;

  /**
   * Constructor that assigns the object's imageModel to interface type so that map can be accessed.
   *
   * @param imageModel imageModel of interface type IImageModel.
   */
  public SaveCommand(IImageModel imageModel) {
    this.imageModel = imageModel;
  }

  /**
   * Extracts the file extension from the given file path. This method searches for the last dot in
   * the file path and returns the substring that follows it, representing the file extension. If no
   * valid extension is found, it returns an empty string.
   *
   * @param filePath The file path from which to extract the extension.
   * @return The extracted file extension, or an empty string if none is found.
   */
  private static String getFileExtension(String filePath) {
    int lastDotIndex = filePath.lastIndexOf('.');
    if (lastDotIndex != -1 && lastDotIndex < filePath.length() - 1) {

      return filePath.substring(lastDotIndex + 1);

    }
    return "";
  }

  /**
   * Executes the save command using the provided arguments. The method checks for valid input,
   * retrieves the file path and image name, determines the file extension, and selects the
   * appropriate saver from the saverMap. If the saver for the file format is not found, an
   * exception is thrown. It calls the SaveImg method on the selected saver to save the image.
   *
   * @param args An array of arguments where args[0] is the file path and args[1] is the image name
   *             to be saved.
   * @throws IOException              If an error occurs during the saving of the image.
   * @throws IllegalArgumentException If insufficient arguments or file format is unsupported.
   */
  @Override
  public void execute(String[] args) throws IOException {
    if (args.length < 2) {
      throw new IllegalArgumentException("Wrong Command");
    }

    String filePath = args[0];
    String imageName = args[1];
    String fileExtension = getFileExtension(filePath);
    ILoadSave saver = saverMap.get(fileExtension);
    if (saver == null) {
      throw new IllegalArgumentException("No such file format");
    }
    saver.saveImg(filePath, imageName, imageModel);

  }
}
