package model.database;

/**
 * The Pixel class represents a single pixel in an image, characterized by its red, green, and blue
 * color components. Each color component is an integer value ranging from 0 to 255. This class
 * provides methods to retrieve the individual color components of the pixel and create a new
 * instance of a pixel with the same color values. The Pixel class implements the IPixel interface,
 * ensuring compatibility with various image processing operations.
 */

public class Pixel implements IPixel {

  private int red;
  private int green;
  private int blue;

  /**
   * Constructs a Pixel object with the specified red, green, and blue color values.
   *
   * @param red   the red color component (0-255)
   * @param green the green color component (0-255)
   * @param blue  the blue color component (0-255)
   */
  public Pixel(int red, int green, int blue) {
    if (red > 255) {
      red = 255;
    }
    if (green > 255) {
      green = 255;
    }
    if (blue > 255) {
      blue = 255;
    }
    if (red < 0) {
      red = 0;
    }
    if (green < 0) {
      green = 0;
    }
    if (blue < 0) {
      blue = 0;
    }
    this.red = red;
    this.green = green;
    this.blue = blue;

  }

  /**
   * Returns the red color component of the pixel.
   *
   * @return the red color component (0-255)
   */
  @Override
  public int getRed() {
    return red;
  }

  /**
   * Returns the green color component of the pixel.
   *
   * @return the green color component (0-255)
   */
  @Override
  public int getGreen() {
    return green;
  }

  /**
   * Returns the blue color component of the pixel.
   *
   * @return the blue color component (0-255)
   */
  @Override
  public int getBlue() {
    return blue;
  }

  /**
   * Creates and returns a new Pixel object with the same red, green, and blue values as the current
   * pixel.
   *
   * @return a new Pixel object with identical color values
   */
  @Override
  public Pixel getPixel() {
    return new Pixel(red, green, blue);
  }
}
