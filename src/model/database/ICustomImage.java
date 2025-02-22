package model.database;

import java.io.FileNotFoundException;

/**
 * ICustomImage interface defines the contract for CustomImage objects, providing methods for image
 * manipulation, analysis, and pixel access.
 */
public interface ICustomImage {


  /**
   * Gets the width of the image.
   *
   * @return The width of the image in pixels.
   */
  int getWidth();

  /**
   * Gets height of the image.
   *
   * @return The height of the image in pixels.
   */
  int getHeight();

  /**
   * Gets the RGB color values of a pixel at specified (x, y) coordinates.
   *
   * @param x The x-coordinate of the pixel
   * @param y The y-coordinate of the pixel
   * @return An array containing the RGB values of the pixel
   */
  Pixel getPixel(int x, int y);


  /**
   * It is the method to split image into 3 channels R,G,B.
   *
   * @param image has the height and width.
   * @return list of 3 rgb images.
   */
  Image[] rgbsplit(Image image);


  /**
   * Combines separate red, green, and blue images into a new image.
   *
   * @param redImageName   The filename for the red component image
   * @param greenImageName The filename for the green component image
   * @param blueImageName  The filename for the blue component image
   * @throws FileNotFoundException if any of the input images do not exist
   */
  Image rgbcombine(Image redImageName, Image greenImageName,
                   Image blueImageName, String imgName) throws FileNotFoundException;


  /**
   * Flips the image horizontally.
   *
   * @return A new CustomImage object representing the flipped image
   */
  Image fliph(Image image);

  /**
   * Flips the image vertically.
   *
   * @return A new CustomImage object representing the flipped image
   */
  Image flipv(Image image);

  /**
   * Brightens the image by a specified increment.
   *
   * @param inc The amount to increase the brightness
   * @return A new CustomImage object representing the brightened image
   */

  Image brighten(int inc, Image image);

  /**
   * Applies a blur filter to the image.
   *
   * @return A new CustomImage object representing the blurred image
   */
  Image blur(Image image);

  /**
   * Applies a sharpen filter to the image.
   *
   * @return A new CustomImage object representing the sharpened image
   */
  Image sharpen(Image image);

  /**
   * Applies a sepia filter to the image.
   *
   * @return A new CustomImage object representing the sepia-toned image
   */
  Image sepia(Image image);

  /**
   * Calculates and returns the luma of the image.
   *
   * @return A new CustomImage object representing the image with luma values
   */
  Image calculateLuma(Image image);

  /**
   * Calculates and returns the intensity of the image.
   *
   * @return A new CustomImage object representing the image with intensity values
   */
  Image calculateIntensity(Image image);

  /**
   * Calculates and returns the value (brightness) of the image.
   *
   * @return A new CustomImage object representing the image with value calculations
   */
  Image calculateValue(Image image);

  /**
   * Get blue image of original image.
   *
   * @return blue image after filter.
   */
  Image getBlueImage(Image image);

  /**
   * Get red image of original image.
   *
   * @return red image after filter.
   */
  Image getRedImage(Image image);

  /**
   * Get green image of original image.
   *
   * @return green image after filter.
   */
  Image getGreenImage(Image image);


}
