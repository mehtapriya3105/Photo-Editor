package controller.loadsave;

import java.io.IOException;

import model.IImageModel;

/**
 * This interface defines the contract for loading and saving image files. Implementing classes must
 * provide concrete implementations for the specified methods.
 */
public interface ILoadSave {

  /**
   * Loads an image from the specified path and saves it with the given destination file name.
   *
   * @param path         The file path from which the image will be loaded.
   * @param destFileName The name of the file where the loaded image will be saved.
   * @param imageModel   imageModel of interface type IImageModel.
   * @throws IOException If an I/O error occurs while loading the image or saving it.
   */
  void loadImg(String path, String destFileName, IImageModel imageModel) throws IOException;

  /**
   * Saves an image from the specified source image name to the given path.
   *
   * @param path         The destination path where the image will be saved.
   * @param srcImageName The name of the source image to be saved.
   * @param imageModel   imageModel of interface type IImageModel.
   * @throws IOException If an I/O error occurs while saving the image.
   */
  void saveImg(String path, String srcImageName, IImageModel imageModel) throws IOException;

  /**
   * Gets the type of the image (e.g., PNG, JPEG).
   *
   * @return A string representing the image type.
   */
  String getImgType();

}
