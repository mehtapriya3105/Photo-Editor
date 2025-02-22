package controller.loadsave;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.ControllerCommand;
import model.IImageModel;

/**
 * LoadCommand is responsible for handling image loading operations. It implements the
 * ControllerCommand interface and uses a map to associate file extensions with their corresponding
 * image loaders.
 */
public class LoadCommand implements ControllerCommand {

  private static final Map<String, ILoadSave> loaderMap = new HashMap<>();

  // Static block to initialize supported file loaders
  static {
    loaderMap.put("jpg", new Jpg());
    loaderMap.put("png", new Png());
    loaderMap.put("ppm", new Ppm());
    loaderMap.put("jpeg", new Jpeg());
  }

  private IImageModel imageModel;

  /**
   * Constructor that assigns the imageModel to interface type.
   *
   * @param model model of interface type IImageModel.
   */
  public LoadCommand(IImageModel model) {
    imageModel = model;
  }

  /**
   * Extracts the file extension from the given file path. The method searches for the last dot in
   * the file path and returns substring that follows it, representing the file extension. If no
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
   * Executes the load command using the provided arguments. The method checks for valid input,
   * retrieves the file path and image name, determines the file extension, and selects the
   * appropriate loader from the loaderMap. If the loader for the file format is not found, an
   * exception is thrown. It calls the LoadImg method on the selected loader to load the image.
   *
   * @param args An array of arguments where args[0] is the file path and args[1] is the image name
   *             to be used for storage.
   * @throws IOException              If an error occurs during the loading of the image.
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
    ILoadSave loader = loaderMap.get(fileExtension);
    if (loader == null) {
      throw new IllegalArgumentException("No such file format");
    }
    loader.loadImg(filePath, imageName, imageModel);

  }

}

