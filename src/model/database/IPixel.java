package model.database;

/**
 * The IPixel interface defines the structure and behavior of a pixel in an image. It includes
 * methods to retrieve the red, green, and blue color components of the pixel, as well as a method
 * to obtain a new instance of the pixel with the same color values. This interface is intended to
 * be implemented by classes that represent individual pixels in image processing applications.
 */
public interface IPixel {

  /**
   * Retrieves the red component of the pixel's color.
   *
   * @return the red color value (0-255)
   */
  int getRed();

  /**
   * Retrieves the green component of the pixel's color.
   *
   * @return the green color value (0-255)
   */
  int getGreen();

  /**
   * Retrieves the blue component of the pixel's color.
   *
   * @return the blue color value (0-255)
   */
  int getBlue();

  /**
   * Creates and returns a new instance of the pixel with the same red, green, and blue color
   * components.
   *
   * @return a new Pixel object with identical color values
   */
  Pixel getPixel();
}

