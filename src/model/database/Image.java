package model.database;

/**
 * Image class represents an image with width and height. It contains setters and getters for pixel.
 * It also contains getters for width and height.
 */
public class Image {

  protected int width;
  protected int height;
  protected Pixel[][] pixels;

  /**
   * Constructs an Image object with the specified width and height.
   * Initializes a 2D array of Pixel objects to store pixel data.
   *
   * @param width  The width of the image.
   * @param height The height of the image.
   */
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    pixels = new Pixel[width][height];
  }

  /**
   * Returns the width of the image.
   *
   * @return The width of the image.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of the image.
   *
   * @return The height of the image.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets the pixel at the specified (x, y) coordinates with the given RGB values.
   * Throws an exception if the coordinates are out of bounds.
   *
   * @param x          The x-coordinate of the pixel to set.
   * @param y          The y-coordinate of the pixel to set.
   * @param redValue   The red value (0-255) of the pixel.
   * @param greenValue The green value (0-255) of the pixel.
   * @param blueValue  The blue value (0-255) of the pixel.
   * @throws IndexOutOfBoundsException If the coordinates are out of bounds of the image.
   */
  public void setPixel(int x, int y, int redValue, int greenValue, int blueValue) {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      Pixel temp = new Pixel(redValue, greenValue, blueValue);
      pixels[x][y] = temp;
    } else {
      throw new IndexOutOfBoundsException("Pixel coordinates out of bounds.");
    }
  }

  /**
   * Retrieves the pixel at the specified (x, y) coordinates.
   * Throws an exception if the coordinates are out of bounds.
   *
   * @param x The x-coordinate of the pixel to retrieve.
   * @param y The y-coordinate of the pixel to retrieve.
   * @return The Pixel object at the specified coordinates.
   * @throws IndexOutOfBoundsException If the coordinates are out of bounds of the image.
   */
  public Pixel getPixel(int x, int y) {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      Pixel temp = pixels[x][y].getPixel();
      return temp;
    } else {
      throw new IndexOutOfBoundsException("Pixel coordinates out of bounds.");
    }
  }
}
