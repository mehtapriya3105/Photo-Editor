package controller;

import java.io.FileNotFoundException;

/**
 * Interface defining features for image processing functionalities, including loading,
 * saving, applying filters, and transformations. These methods are to be implemented
 * by classes handling image processing tasks.
 */
public interface Features {
  /**
   * Loads an image file specified by the user and prepares it for processing.
   *
   * @throws FileNotFoundException if the file path is invalid or the file cannot be located.
   */
  void loadImage() throws FileNotFoundException;

  /**
   * Saves the currently loaded image to a specified file path.
   */
  String saveImage();

  /**
   * Generates a split preview of a filter or transformation by applying a percentage of the effect.
   *
   * @param command    the command string representing the operation.
   * @param percentage the percentage of the effect to apply.
   * @param action     the specific action to append to the image name.
   * @throws FileNotFoundException if the operation fails or resources are unavailable.
   */
  void splitPreview(String command, Integer percentage, String action)
          throws FileNotFoundException;

  /**
   * Applies a blur effect to the image.
   *
   * @param percentage the strength of the blur effect.
   * @param splitBool  whether to generate a split preview.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getBlur(int percentage, boolean splitBool) throws FileNotFoundException;

  /**
   * Extracts the luma (brightness) component of the image.
   *
   * @param percentage the intensity of the luma extraction.
   * @param splitBool  whether to generate a split preview.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getLuma(int percentage, boolean splitBool) throws FileNotFoundException;

  /**
   * Applies a sepia tone effect to the image.
   *
   * @param percentage the intensity of the sepia effect.
   * @param splitBool  whether to generate a split preview.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getSepia(int percentage, boolean splitBool) throws FileNotFoundException;

  /**
   * Sharpens the image to enhance details.
   *
   * @param percentage the intensity of the sharpening effect.
   * @param splitBool  whether to generate a split preview.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getSharpen(int percentage, boolean splitBool)
          throws FileNotFoundException;

  /**
   * Flips the image horizontally.
   *
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getFlipHorizontal() throws FileNotFoundException;

  /**
   * Flips the image vertically.
   *
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getFlipVertical() throws FileNotFoundException;

  /**
   * Applies color correction to the image.
   *
   * @param percentage the intensity of the color correction.
   * @param splitBool  whether to generate a split preview.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getColorCorrect(int percentage, boolean splitBool)
          throws FileNotFoundException;

  /**
   * Adjusts the image levels using specified values for red, green, and blue channels.
   *
   * @param v1         the value for the red channel.
   * @param v2         the value for the green channel.
   * @param v3         the value for the blue channel.
   * @param percentage the intensity of the adjustment.
   * @param splitBool  whether to generate a split preview.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getLevelAdjust(int v1, int v2, int v3, int percentage, boolean splitBool)
          throws FileNotFoundException; //different command

  /**
   * Extracts the red component of the image.
   *
   * @param percentage the intensity of the extraction.
   * @param splitBool  whether to generate a split preview.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getRedComponent(int percentage, boolean splitBool)
          throws FileNotFoundException;

  /**
   * Extracts the green component of the image.
   *
   * @param percentage the intensity of the extraction.
   * @param splitBool  whether to generate a split preview.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getGreenComponent(int percentage, boolean splitBool)
          throws FileNotFoundException;

  /**
   * Extracts the blue component of the image.
   *
   * @param percentage the intensity of the extraction.
   * @param splitBool  whether to generate a split preview.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getBlueComponent(int percentage, boolean splitBool)
          throws FileNotFoundException;

  /**
   * Adjusts the brightness of the image.
   *
   * @param bright the brightness adjustment value.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getBright(String bright) throws FileNotFoundException; //different command

  /**
   * Compresses the image with a specified compression value.
   *
   * @param compressVal the value determining the compression ratio.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void getCompress(int compressVal) throws FileNotFoundException; //different command

  /**
   * Downscales the image to specified dimensions.
   *
   * @param newWidth  the desired width of the downscaled image.
   * @param newHeight the desired height of the downscaled image.
   * @throws FileNotFoundException if resources are unavailable or the operation fails.
   */
  void downScale(String newWidth, String newHeight) throws FileNotFoundException;

}
