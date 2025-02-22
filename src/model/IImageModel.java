package model;

import java.io.FileNotFoundException;

import model.database.Image;

/**
 * Interface representing the operations for image processing.
 */
public interface IImageModel {

  /**
   * Stores an image in the model with a specified name.
   *
   * @param name  the name to associate with the image.
   * @param image the image to be stored.
   */
  void storeImage(String name, Image image);

  /**
   * Retrieves an image stored in the model by name.
   *
   * @param name the name of the image to retrieve.
   * @return the image associated with the given name.
   * @throws FileNotFoundException if the image is not found.
   */
  Image getImage(String name) throws FileNotFoundException;

  /**
   * Applies a blur effect to an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the blurred image.
   * @param cmand          additional command parameters.
   * @param p              parameter for processing.
   * @param mask           the mask to apply (optional).
   * @param maskImage      the image to use as a mask (optional).
   * @throws FileNotFoundException if the source file is not found.
   */
  void blurMethod(String sourceFileName, String destFileName, String cmand, String p,
                  String mask, String maskImage) throws FileNotFoundException;

  /**
   * Adjusts the intensity of an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the processed image.
   * @param cmand          additional command parameters.
   * @param p              parameter for processing.
   * @param mask           the mask to apply (optional).
   * @param maskImage      the image to use as a mask (optional).
   * @throws FileNotFoundException if the source file is not found.
   */
  void intensityMethod(String sourceFileName, String destFileName, String cmand, String p,
                       String mask, String maskImage)
          throws FileNotFoundException;

  /**
   * Converts an image to a luma representation and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the luma image.
   * @param cmand          additional command parameters.
   * @param p              parameter for processing.
   * @param mask           the mask to apply (optional).
   * @param maskImage      the image to use as a mask (optional).
   * @throws FileNotFoundException if the source file is not found.
   */
  void lumaMethod(String sourceFileName, String destFileName, String cmand, String p,
                  String mask, String maskImage)
          throws FileNotFoundException;

  /**
   * Applies a sepia effect to an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the sepia-toned image.
   * @param cmand          additional command parameters (if any).
   * @param p              parameter for processing.
   * @param mask           the mask to apply (optional).
   * @param maskImage      the image to use as a mask (optional).
   * @throws FileNotFoundException if the source file is not found.
   */
  void sepiaMethod(String sourceFileName, String destFileName, String cmand, String p,
                   String mask, String maskImage)
          throws FileNotFoundException;

  /**
   * Sharpens an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the sharpened image.
   * @param cmand          additional command parameters (if any).
   * @param p              parameter for processing.
   * @param mask           the mask to apply (optional).
   * @param maskImage      the image to use as a mask (optional).
   * @throws FileNotFoundException if the source file is not found.
   */
  void sharpenMethod(String sourceFileName, String destFileName, String cmand, String p,
                     String mask, String maskImage)
          throws FileNotFoundException;

  /**
   * Adjusts the value (brightness or darkness) of an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the value-adjusted image.
   * @param cmand          additional command parameters (if any).
   * @param p              parameter for processing.
   * @param mask           the mask to apply (optional).
   * @param maskImage      the image to use as a mask (optional).
   * @throws FileNotFoundException if the source file is not found.
   */
  void valueMethod(String sourceFileName, String destFileName, String cmand, String p,
                   String mask, String maskImage)
          throws FileNotFoundException;

  /**
   * Flips an image horizontally and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the horizontally flipped image.
   * @throws Exception if the source file cannot be processed.
   */
  void horizontalFlipMethod(String sourceFileName, String destFileName) throws Exception;

  /**
   * Flips an image vertically and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the vertically flipped image.
   * @throws Exception if the source file cannot be processed.
   */
  void verticalFlipMethod(String sourceFileName, String destFileName) throws Exception;

  /**
   * Performs color correction on an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the color-corrected image.
   * @param cmand          additional command parameters (if any).
   * @param p              parameter for processing.
   * @throws FileNotFoundException if the source file is not found.
   */
  void colorCorrectMethod(String sourceFileName, String destFileName, String cmand, String p)
          throws FileNotFoundException;

  /**
   * Generates a histogram for an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the histogram image.
   * @throws FileNotFoundException if the source file is not found.
   */
  void histogramMethod(String sourceFileName, String destFileName)
          throws FileNotFoundException;

  /**
   * Extracts the blue color component from an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the blue component image.
   * @param cmand          additional command parameters (if any).
   * @param p              parameter for processing.
   * @param mask           the mask to apply (optional).
   * @param maskImage      the image to use as a mask (optional).
   * @throws FileNotFoundException if the source file is not found.
   */
  void getBlueImageMethod(String sourceFileName, String destFileName, String cmand, String p,
                          String mask,
                          String maskImage)
          throws FileNotFoundException;

  /**
   * Extracts the red color component from an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the red component image.
   * @param cmand          additional command parameters (if any).
   * @param p              parameter for processing.
   * @param mask           the mask to apply (optional).
   * @param maskImage      the image to use as a mask (optional).
   * @throws FileNotFoundException if the source file is not found.
   */
  void getRedComponentMethod(String sourceFileName, String destFileName, String cmand, String p,
                             String mask,
                             String maskImage)
          throws FileNotFoundException;

  /**
   * Extracts the green color component from an image and stores the result.
   *
   * @param sourceFileName the name of the source image.
   * @param destFileName   the name for the green component image.
   * @param cmand          additional command parameters (if any).
   * @param p              parameter for processing.
   * @param mask           the mask to apply (optional).
   * @param maskImage      the image to use as a mask (optional).
   * @throws FileNotFoundException if the source file is not found.
   */
  void getGreenComponentImage(String sourceFileName, String destFileName, String cmand, String p,
                              String mask,
                              String maskImage)
          throws FileNotFoundException;

  /**
   * Combines separate red, green, and blue images into a single RGB image.
   *
   * @param redImageName   the name of the red component image.
   * @param greenImageName the name of the green component image.
   * @param blueImageName  the name of the blue component image.
   * @param imageName      the name for the combined RGB image.
   * @throws FileNotFoundException if any of the component images are not found.
   */
  void rgbCombineMethod(String redImageName, String greenImageName, String blueImageName,
                        String imageName)
          throws FileNotFoundException;

  /**
   * Splits an image into its red, green, and blue components and stores each.
   *
   * @param redImageName   the name for the red component image.
   * @param greenImageName the name for the green component image.
   * @param blueImageName  the name for the blue component image.
   * @param imageName      the name of the source image.
   * @throws FileNotFoundException if the source image is not found.
   */
  void rgbSplitMethod(String redImageName, String greenImageName, String blueImageName,
                      String imageName)
          throws FileNotFoundException;

  /**
   * Brightens an image by a specified value and stores the result.
   *
   * @param brightness      brightness adjustment value (positive to brighten, negative to darken).
   * @param imageName       the name of the source image.
   * @param destinationName the name for the brightened image.
   * @throws FileNotFoundException if the source image is not found.
   */


  void brightenMethod(Integer brightness, String imageName, String destinationName)
          throws FileNotFoundException;

  /**
   * Adjusts the levels (black, midtones, white) of an image and stores the result.
   *
   * @param b               the black level adjustment value.
   * @param m               the midtone level adjustment value.
   * @param w               the white level adjustment value.
   * @param imageName       the name of the source image.
   * @param destinationName the name for the adjusted image.
   * @param p               parameter for processing.
   * @param cmand           additional command parameters (if any).
   * @throws FileNotFoundException if the source image is not found.
   */
  void levelAdjustMethod(int b, int m, int w, String imageName, String destinationName,
                         int p, String cmand)
          throws FileNotFoundException;

  /**
   * Compresses an image to a specified percentage of its original size and stores the result.
   *
   * @param imageName       the name of the source image.
   * @param destinationName the name for the compressed image.
   * @param percentage      the compression percentage (e.g., 50 for 50% size reduction).
   * @throws FileNotFoundException if the source image is not found.
   */
  void compressMethod(String imageName, String destinationName, Integer percentage)
          throws FileNotFoundException;

  /**
   * Downscales an image to a specified height and width and stores the result.
   *
   * @param imageName       the name of the source image.
   * @param destinationName the name for the downscaled image.
   * @param newHeight       the desired height of the downscaled image.
   * @param newWidth        the desired width of the downscaled image.
   * @throws FileNotFoundException if the source image is not found.
   */
  void imageDownScalling(String imageName, String destinationName, int newHeight,
                         int newWidth)
          throws FileNotFoundException;


}
