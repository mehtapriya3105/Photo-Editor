package model.database;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 * Interface for custom image processing with additional functionalities such as histogram creation,
 * color adjustments, and image compression.
 */
public interface ICustomImage2 extends ICustomImage {

  /**
   * Creates a histogram of the image, representing the frequency distribution of colors.
   *
   * @return a BufferedImage representing the histogram.
   * @throws FileNotFoundException if the image file is not found.
   */
  BufferedImage createHistogram(Image image) throws FileNotFoundException;


  /**
   * Displays the given image in a viewable format.
   *
   * @param image the BufferedImage to be displayed.
   */
  void displayImage(BufferedImage image);

  /**
   * Compresses the image, saving it to a specified destination.
   *
   * @param percentage the percentage by which image is to be compressed.
   */
  Image compress(Image image, int percentage);

  /**
   * Adjusts the color of given image based on specific brightness, midpoint, and whitepoint values.
   * This method calculates new RGB values for each pixel in image and updates image accordingly.
   *
   * @param b the brightness value used in the equation
   * @param m the midpoint value used in the equation
   * @param w the whitepoint value used in the equation
   * @return a new ICustomImage object with adjusted colors
   * @throws FileNotFoundException if the image is null or not found
   */
  Image adjustColorCommand(Image image, int b, int m, int w)
          throws FileNotFoundException;

  /**
   * Applies color correction to the image by adjusting each color channel (red, green, blue) based
   * on their respective average peak values. This operation balances the color channels to achieve
   * a more uniform distribution of color intensity.
   *
   * @return a new ICustomImage object with corrected colors
   * @throws FileNotFoundException if the image is null or not found
   */
  Image colorCorrectionCommand(Image image) throws FileNotFoundException;

  /**
   * Splits the current image horizontally based on a specified percentage. Pixels to the left of
   * the split (as determined by the percentage) are retained from the current image, and pixels to
   * the right are taken from the original image.
   *
   * @param originalImage The original image used for pixels beyond the split.
   * @param percentage    The percentage (0-100) of image's width that remains from current image.
   * @return A new image resulting from the split operation.
   * @throws IllegalArgumentException If percentage is null, not a number, or outside range 0-100.
   */
  Image splitImg(Image srcImage, Image originalImage, String percentage);

}
