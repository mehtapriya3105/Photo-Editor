package utils;

import model.database.Image;
import model.database.Pixel;

/**
 * This class is used to test two matrix. The two matrices are compared pixel by pixel. Method
 * overloading is used for comparison.
 */
public class ImageComparisonUtils {

  /**
   * Compares two CustomImage objects pixel by pixel.
   *
   * @param image1 the first image
   * @param image2 the second image
   * @return true if the images are identical, false otherwise
   */
  public static boolean equals(Image image1, Image image2) {

    printTranspose(image1);
    if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
      return false;
    }

    for (int i = 0; i < image1.getHeight(); ++i) {
      for (int j = 0; j < image1.getWidth(); ++j) {
        Pixel pixel1 = image1.getPixel(j, i);
        Pixel pixel2 = image2.getPixel(j, i);

        if (!arePixelsEqual(pixel1, pixel2)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Compares two CustomImage objects pixel by pixel. Method overloading with one more parameter.
   *
   * @param image1    the first image
   * @param image2    the second image
   * @param tolerance similar like delta
   * @return true if the images are identical, false otherwise
   */
  public static boolean equals(Image image1, Image image2, int tolerance) {
    // Check if the dimensions are the same
    if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
      return false;
    }

    for (int i = 0; i < image1.getHeight(); ++i) {
      for (int j = 0; j < image1.getWidth(); ++j) {
        Pixel pixel1 = image1.getPixel(j, i);
        Pixel pixel2 = image2.getPixel(j, i);

        if (!arePixelsEqual(pixel1, pixel2, tolerance)) {
          System.out.println(pixel1.getRed() + " " + pixel1.getGreen() + " " + pixel1.getBlue());
          System.out.println(pixel2.getRed() + " " + pixel2.getGreen() + " " + pixel2.getBlue());

          return false;  // Return false if any pixel is different
        }
      }
    }
    return true;  // Return true if all pixels match
  }

  /**
   * Compares two pixels to see if they are equal.
   *
   * @param pixel1 the first pixel as a double array [R, G, B]
   * @param pixel2 the second pixel as a double array [R, G, B]
   * @return true if the two pixels are identical, false otherwise
   */
  private static boolean arePixelsEqual(Pixel pixel1, Pixel pixel2) {
    return pixel1.getRed() == pixel2.getRed()
            && pixel1.getGreen() == pixel2.getGreen()
            && pixel1.getBlue() == pixel2.getBlue();
  }

  /**
   * Compares two pixels to see if they are equal. Method overloading with one more parameter.
   *
   * @param pixel1    the first pixel as a double array [R, G, B]
   * @param pixel2    the second pixel as a double array [R, G, B]
   * @param tolerance similar to delta
   * @return true if the two pixels are identical, false otherwise
   */
  private static boolean arePixelsEqual(Pixel pixel1, Pixel pixel2, int tolerance) {
    return Math.abs(pixel1.getRed() - pixel2.getRed()) <= tolerance
            && Math.abs(pixel1.getGreen() - pixel2.getGreen()) <= tolerance
            && Math.abs(pixel1.getBlue() - pixel2.getBlue()) <= tolerance;
  }

  private static void printTranspose(Image image) {
    int originalWidth = image.getWidth();
    int originalHeight = image.getHeight();

    System.out.println("Transposed Image: ");
    for (int x = 0; x < originalWidth; x++) {
      System.out.print("{");
      for (int y = 0; y < originalHeight; y++) {
        Pixel pixel = image.getPixel(x, y);
        System.out.print(
                "{" + pixel.getRed() + "," + pixel.getGreen() + "," + pixel.getBlue() + "},");
      }
      System.out.println("},");

    }
  }

}
