package model.database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * The CustomImage2 class extends CustomImage and implements the ICustomImage2 interface. This
 * class provides functionality for adjusting the color of an image based on a custom mathematical
 * equation and allows access to individual pixel color adjustments.
 */
public class CustomImage2 extends CustomImage implements ICustomImage2 {

  /**
   * Constructs an CustomImage2 with the specified width and height.
   *
   * @param image contains width and height of image.
   */
  public CustomImage2(Image image) {
    super(image);
  }

  /**
   * Adjusts the color of the given image based on specific brightness, midpoint, and whitepoint
   * values. This method calculates new RGB values for each pixel in the image and updates the image
   * accordingly.
   *
   * @param b the brightness value used in the equation
   * @param m the midpoint value used in the equation
   * @param w the whitepoint value used in the equation
   * @return a new ICustomImage object with adjusted colors
   * @throws FileNotFoundException if the image is null or not found
   */
  @Override
  public Image adjustColorCommand(Image image, int b, int m, int w)
          throws FileNotFoundException {
    if (image == null) {
      throw new FileNotFoundException("Image not found");
    }

    Image newImage = new Image(image.getWidth(), image.getHeight());

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel temp = image.getPixel(x, y);
        int newRed = calculateEquation(temp.getRed(), b, m, w);
        int newGreen = calculateEquation(temp.getGreen(), b, m, w);
        int newBlue = calculateEquation(temp.getBlue(), b, m, w);
        Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
        newImage.setPixel(x, y, newPixel.getRed(), newPixel.getGreen(), newPixel.getBlue());
      }
    }
    return newImage;
  }

  /**
   * Calculates a new color value for a given color component (red, green, or blue) using quadratic
   * equation. The equation is derived from the brightness, midpoint, and whitepoint values.
   *
   * @param x the original color component value (0-255)
   * @param b the brightness value
   * @param m the midpoint value
   * @param w the whitepoint value
   * @return the adjusted color component value
   */
  private int calculateEquation(int x, double b, double m, double w) {
    double[] equationVariables = getEquationVariables(b, m, w);
    double a = equationVariables[0];
    double bb = equationVariables[1];
    double c = equationVariables[2];
    int ans = ((int) ((a * Math.pow(x, 2)) + (bb * x) + c));
    return ans;
  }

  /**
   * Computes the coefficients for the quadratic equation used in color adjustment. These
   * coefficients are determined based on the brightness, midpoint, and whitepoint values.
   *
   * @param black the brightness value
   * @param mid   the midpoint value
   * @param white the whitepoint value
   * @return an array containing the coefficients for the quadratic equation
   */
  private double[] getEquationVariables(double black, double mid, double white) {
    double a = (
            (Math.pow(black, 2) * (mid - white)) - (black * (Math.pow(mid, 2)
                    - Math.pow(white, 2))) + (white * Math.pow(mid, 2))
                    - (mid * Math.pow(white, 2)));

    double aA = (-1 * black * (128 - 255)) + 128 * white - 255 * mid;

    double aB = ((Math.pow(black, 2) * (128 - 255)) + (255 * Math.pow(mid, 2)) - (128 * Math.pow(
            white, 2)));

    double aC = ((Math.pow(black, 2) * (255 * mid - 128 * white)) - (black * (
            (255 * Math.pow(mid, 2)) - (128 * Math.pow(white, 2)))));
    return new double[]{aA / a, aB / a, aC / a};

  }

  /**
   * Finds the maximum value in an array of integers.
   *
   * @param ints an array of integers
   * @return the maximum value in the array
   */
  private int getMaxValue(int[] ints) {
    int maxvalue = 0;

    for (int i = 0; i < ints.length; i++) {
      if (ints[i] > maxvalue) {
        maxvalue = ints[i];

      }
    }
    return maxvalue;
  }

  /**
   * Identifies the index of the peak (maximum) value in an array of integers.
   *
   * @param ints an array of integers
   * @return the index of the peak value in the array
   */
  private int getPeaks(int[] ints) {
    int maxvalue = 0;
    int peak = 0;
    for (int i = 0; i < ints.length; i++) {
      if (ints[i] > maxvalue) {
        maxvalue = ints[i];
        peak = i;
      }
    }
    return peak;
  }

  /**
   * Applies color correction to the image by adjusting each color channel (red, green, blue) based
   * on their respective average peak values. This operation balances the color channels to achieve
   * a more uniform distribution of color intensity.
   *
   * @return a new ICustomImage object with corrected colors
   * @throws FileNotFoundException if the image is null or not found
   */
  @Override
  public Image colorCorrectionCommand(Image image) throws FileNotFoundException {
    if (image == null) {
      throw new FileNotFoundException("Image not found");
    }

    Image newImage = new Image(image.getWidth(), image.getHeight());

    List<int[]> freqCount = frequencyCount(image);

    int redPeak = getPeaks(freqCount.get(0));
    int greenPeak = getPeaks(freqCount.get(1));
    int bluePeak = getPeaks(freqCount.get(2));

    int avgPeak = (bluePeak + greenPeak + redPeak) / 3;

    int redOffset = avgPeak - redPeak;
    int blueOffset = avgPeak - bluePeak;
    int greenOffset = avgPeak - greenPeak;

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel temp = image.getPixel(x, y);
        int newRed = clamp(temp.getRed() + redOffset);
        int newGreen = clamp(temp.getGreen() + greenOffset);
        int newBlue = clamp(temp.getBlue() + blueOffset);
        Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
        newImage.setPixel(x, y, newPixel.getRed(), newPixel.getGreen(), newPixel.getBlue());
      }
    }
    return newImage;
  }


  /**
   * Draws a line graph for a specific color channel's frequency distribution. The graph visually
   * represents the intensity distribution of the color channel.
   *
   * @param g         the Graphics2D object used for drawing
   * @param frequency an array containing the frequency of each intensity level
   * @param maxVal    the maximum frequency value for scaling
   * @param color     the color of the line graph
   * @param height    the height of the graph area
   */
  private void drawChannelLineGraph(Graphics2D g, int[] frequency, int maxVal, Color color,
                                    int height) {
    g.setColor(color);
    int previousY = height - (int) ((frequency[0] / (double) maxVal) * height);

    for (int i = 1; i < 256; i++) {
      int currentY = height - (int) ((frequency[i] / (double) maxVal) * height);
      g.drawLine(i - 1, previousY, i, currentY);
      previousY = currentY;
    }
  }

  /**
   * Creates a histogram image that represents the frequency distribution of red, green, and blue
   * color intensities in the current image. Each color channel is visualized as a line graph on the
   * histogram.
   *
   * @return a BufferedImage containing the histogram
   * @throws FileNotFoundException if the image is null or not found
   */
  @Override
  public BufferedImage createHistogram(Image image) throws FileNotFoundException {
    if (image == null) {
      throw new FileNotFoundException("Image not found");
    }

    List<int[]> freqCount = frequencyCount(image);

    // Create a BufferedImage to hold the histogram
    BufferedImage histogramImage =
            new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = histogramImage.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 256, 256);

    // Calculate maximum values for scaling each color channel
    int maxRed = getMaxValue(freqCount.get(0));
    int maxGreen = getMaxValue(freqCount.get(1));
    int maxBlue = getMaxValue(freqCount.get(2));

    // Draw Red, Green, and Blue line graphs
    drawChannelLineGraph(g, freqCount.get(0), maxRed, Color.RED, 150);
    drawChannelLineGraph(g, freqCount.get(1), maxGreen, Color.GREEN, 150);
    drawChannelLineGraph(g, freqCount.get(2), maxBlue, Color.BLUE, 150);

    g.dispose();
    return histogramImage;
  }


  /**
   * Computes the frequency of each intensity level (0-255) for red, green, and blue color channels
   * in the given image. This frequency data is useful for creating histograms or analyzing color
   * distribution in the image.
   *
   * @param image the ICustomImage object to analyze
   * @return a list of arrays, where each array contains frequency of intensities for color channel.
   */
  private List<int[]> frequencyCount(Image image) {
    List l = new ArrayList<>();
    int[] freqr = new int[256];
    int[] freqb = new int[256];
    int[] freqg = new int[256];
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel temp = image.getPixel(x, y);
        int redValue = temp.getRed();
        int greenValue = temp.getGreen();
        int blueValue = temp.getBlue();

        if ((redValue >= 10) && (redValue <= 246)) {
          freqr[redValue]++;
        }

        if (greenValue > 10 && greenValue < 246) {
          freqb[greenValue]++;
        }

        if (blueValue > 10 && blueValue < 246) {
          freqg[blueValue]++;
        }
      }
    }
    l.add(freqr);
    l.add(freqb);
    l.add(freqg);

    return l;

  }

  /**
   * Displays the provided image in a new JFrame window. The image is rendered as a JLabel in
   * frame.
   *
   * @param image the BufferedImage to display
   */
  public void displayImage(BufferedImage image) {
    JFrame frame = new JFrame("Image Display");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(image.getWidth(), image.getHeight());

    // Create a JLabel and set the image icon
    JLabel label = new JLabel(new ImageIcon(image));
    frame.getContentPane().add(label, BorderLayout.CENTER);

    // Display the frame
    frame.pack();
    frame.setVisible(true);
  }


  /**
   * Clamps an integer value to the range [0, 255]. If the value is greater than 255, it returns
   * 255; if less than 0, it returns 0.
   *
   * @param value The integer value to be clamped.
   * @return The clamped value within the range [0, 255].
   */
  private int clamp(int value) {
    if (value > 255) {
      return 255;
    }
    if (value < 0) {
      return 0;
    }
    return value;
  }

  /**
   * Clamps a double value to the range [0, 255]. If the value is greater than 255, it returns 255;
   * if less than 0, it returns 0.
   *
   * @param value The double value to be clamped.
   * @return The clamped value within the range [0, 255].
   */
  private double clamp(double value) {
    if (value > 255) {
      return 255;
    }
    if (value < 0) {
      return 0;
    }
    return value;
  }


  /**
   * Compresses the image by applying the Haar wavelet transform, compression thresholding, and
   * inverse Haar transform to reduce the image data size.
   *
   * @param percentage Compression level in percentage to determine threshold for data elimination.
   * @return A compressed ICustomImage instance with reduced data size.
   */
  @Override
  public Image compress(Image image, int percentage) {

    int height = image.getHeight();
    int width = image.getWidth();
    int size = this.paddingSize(Math.max(height, width));

    double[][] redpixel = new double[size][size];
    double[][] greenpixel = new double[size][size];
    double[][] bluepixel = new double[size][size];

    // Copy original pixels to padded matrices
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redpixel[i][j] = image.getPixel(j, i).getRed();
        greenpixel[i][j] = image.getPixel(j, i).getGreen();
        bluepixel[i][j] = image.getPixel(j, i).getBlue();
      }
    }

    // Apply compression pipeline on each color channel
    redpixel = this.pipeline(redpixel, size, percentage);
    greenpixel = this.pipeline(greenpixel, size, percentage);
    bluepixel = this.pipeline(bluepixel, size, percentage);

    Image compressed = new Image(width, height);  // Set compressed image dimensions

    // Set pixels back to compressed image
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        compressed.setPixel(j, i, (int) redpixel[i][j], (int) greenpixel[i][j],
                (int) bluepixel[i][j]);
      }
    }
    return compressed;
  }


  /**
   * Pads the matrix size to the nearest power of 2 for optimal Haar transformation.
   *
   * @param max Maximum dimension (width or height) of the matrix.
   * @return Padded matrix size as a power of 2.
   */
  private int paddingSize(int max) {
    int c = 1;
    while (c < max) {
      c = c * 2;
    }
    return c;
  }

  /**
   * Applies Haar wavelet transform, compression, and inverse Haar transform in sequence.
   *
   * @param matrix     The matrix representing a color channel.
   * @param size       The padded size of the matrix.
   * @param percentage Compression percentage to determine threshold.
   * @return Compressed matrix after inverse Haar transformation.
   */
  protected double[][] pipeline(double[][] matrix, int size, int percentage) {
    matrix = this.harr(matrix, size);
    matrix = this.comp(matrix, percentage);
    matrix = this.inverseHarr(matrix, size);
    return matrix;
  }

  /**
   * Performs the Haar wavelet transform on the matrix.
   *
   * @param matrix The matrix to be transformed.
   * @param size   The size of the matrix.
   * @return The matrix after applying Haar wavelet transform.
   */
  protected double[][] harr(double[][] matrix, int size) {
    int c = size;
    while (c > 1) {
      matrix = this.transformR(matrix, c);
      matrix = this.transformC(matrix, c);
      c = c / 2;
    }
    return matrix;
  }

  /**
   * Transforms rows of the matrix using Haar transform.
   *
   * @param matrix The matrix to transform.
   * @param c      Current size of the sub-matrix being transformed.
   * @return Matrix with transformed rows.
   */
  protected double[][] transformR(double[][] matrix, int c) {
    int row = matrix.length;
    for (int i = 0; i < row; i++) {
      List<Double> avg = new ArrayList<>();
      List<Double> diff = new ArrayList<>();
      for (int j = 0; j < c; j = j + 2) {
        avg.add((matrix[i][j] + matrix[i][j + 1]) / Math.sqrt(2.0));
        diff.add((matrix[i][j] - matrix[i][j + 1]) / Math.sqrt(2.0));
      }
      for (int j = 0; j < avg.size(); j++) {
        matrix[i][j] = avg.get(j);
        matrix[i][avg.size() + j] = diff.get(j);
      }
    }
    return matrix;
  }

  /**
   * Transforms columns of the matrix using Haar transform.
   *
   * @param matrix The matrix to transform.
   * @param c      Current size of the sub-matrix being transformed.
   * @return Matrix with transformed columns.
   */
  protected double[][] transformC(double[][] matrix, int c) {
    for (int i = 0; i < matrix.length; i++) {
      List<Double> avg = new ArrayList<>();
      List<Double> diff = new ArrayList<>();
      for (int j = 0; j < c; j = j + 2) {
        avg.add((matrix[j][i] + matrix[j + 1][i]) / Math.sqrt(2.0));
        diff.add((matrix[j][i] - matrix[j + 1][i]) / Math.sqrt(2.0));
      }
      for (int j = 0; j < avg.size(); j++) {
        matrix[j][i] = avg.get(j);
        matrix[j + avg.size()][i] = diff.get(j);
      }
    }
    return matrix;
  }

  /**
   * Compresses the matrix by setting values below the threshold to zero.
   *
   * @param matrix     The matrix to be compressed.
   * @param percentage Compression percentage determining the threshold.
   * @return Compressed matrix.
   */
  protected double[][] comp(double[][] matrix, int percentage) {
    Double threshold = Double.MAX_VALUE;
    if (percentage == 100.00) {
      return this.apply(matrix, threshold);
    }
    List<Double> value = new ArrayList<>();
    for (double[] doubles : matrix) {
      for (int j = 0; j < matrix.length; j++) {
        double temp = Math.round(doubles[j] * 1000.0) / 1000.0;
        value.add(Math.abs(temp));
      }
    }
    List<Double> unique = new ArrayList<>(new HashSet<>(value));
    Collections.sort(unique);
    int i = (int) Math.round((unique.size()) * (percentage / 100.0));
    threshold = unique.get(i);
    return this.apply(matrix, threshold);
  }

  /**
   * Applies the threshold to set values below it to zero.
   *
   * @param matrix    The matrix to modify.
   * @param threshold Values below this threshold will be set to zero.
   * @return Matrix with values below threshold set to zero.
   */
  protected double[][] apply(double[][] matrix, double threshold) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (Math.abs(matrix[i][j]) < threshold) {
          matrix[i][j] = 0.0;
        }
      }
    }
    return matrix;
  }

  /**
   * Applies inverse Haar wavelet transform on the matrix.
   *
   * @param matrix The matrix to be transformed.
   * @param size   The size of the matrix.
   * @return Matrix after inverse Haar transform.
   */
  protected double[][] inverseHarr(double[][] matrix, int size) {
    int c = 2;
    while (c <= size) {
      matrix = this.inverseHarrC(matrix, c);
      matrix = this.inverseHarrR(matrix, c);
      c = c * 2;
    }
    return matrix;
  }

  /**
   * Inversely transforms rows using inverse Haar transform.
   *
   * @param matrix The matrix to transform.
   * @param c      Current size of the sub-matrix being transformed.
   * @return Matrix with inversely transformed rows.
   */
  protected double[][] inverseHarrR(double[][] matrix, int c) {
    for (int i = 0; i < matrix.length; i++) {
      List<Double> avg = new ArrayList<>();
      List<Double> diff = new ArrayList<>();
      for (int j = 0; j < c / 2; j++) {
        avg.add((matrix[i][j] + matrix[i][j + c / 2]) / Math.sqrt(2.0));
        diff.add((matrix[i][j] - matrix[i][j + c / 2]) / Math.sqrt(2.0));
      }
      for (int j = 0; j < c; j = j + 2) {
        matrix[i][j] = avg.get(j / 2);
        matrix[i][j + 1] = diff.get(j / 2);
      }
    }
    return matrix;
  }

  /**
   * Inversely transforms columns using inverse Haar transform.
   *
   * @param matrix The matrix to transform.
   * @param c      Current size of the sub-matrix being transformed.
   * @return Matrix with inversely transformed columns.
   */
  protected double[][] inverseHarrC(double[][] matrix, int c) {
    for (int i = 0; i < matrix.length; i++) {
      List<Double> avg = new ArrayList<>();
      List<Double> diff = new ArrayList<>();
      for (int j = 0; j < c / 2; j++) {
        avg.add((matrix[j][i] + matrix[j + (c / 2)][i]) / Math.sqrt(2.0));
        diff.add((matrix[j][i] - matrix[j + (c / 2)][i]) / Math.sqrt(2.0));
      }
      for (int j = 0; j < c; j = j + 2) {
        matrix[j][i] = avg.get(j / 2);
        matrix[j + 1][i] = diff.get(j / 2);
      }
    }
    return matrix;
  }

  /**
   * Splits the current image horizontally based on a specified percentage. Pixels to the left of
   * the split (as determined by the percentage) are retained from the current image, and pixels to
   * the right are taken from the original image.
   *
   * @param originalImage The original image used for pixels beyond the split.
   * @param percentage    The percentage (0-100) of image's width that remains from current image.
   * @return A new image resulting from the split operation.
   * @throws IllegalArgumentException If the percentage is null, not number, or outside range
   *                                  0-100.
   */
  @Override
  public Image splitImg(Image proccessedImage, Image originalImage, String percentage) {
    Image newSplitImage = new Image(proccessedImage.getWidth(), proccessedImage.getHeight());
    if (percentage == null) {
      throw new IllegalArgumentException("Percentage cannot be null");
    }
    int percent = Integer.parseInt(percentage);
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("Percentage must be between 0 and 100");
    }
    int widthLimit = (originalImage.getWidth() * percent) / 100;

    for (int y = 0; y < proccessedImage.getHeight(); y++) {
      for (int x = 0; x < proccessedImage.getWidth(); x++) {

        Pixel temp = proccessedImage.getPixel(x, y);
        Pixel originalTemp = originalImage.getPixel(x, y);
        if (x < widthLimit) {
          newSplitImage.setPixel(x, y, temp.getRed(), temp.getGreen(), temp.getBlue());
        } else {
          newSplitImage.setPixel(x, y, originalTemp.getRed(), originalTemp.getGreen(),
                  originalTemp.getBlue());
        }
      }
    }
    return newSplitImage;
  }


}
