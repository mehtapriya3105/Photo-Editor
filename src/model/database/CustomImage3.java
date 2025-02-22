package model.database;

/**
 * The CustomImage3 class extends CustomImage2 and implements the ICustomImage3 interface. It
 * contains old as well as new functionalities for downscaling and masking.
 */
public class CustomImage3 extends CustomImage2 implements ICustomImage3 {

  /**
   * Constructs an CustomImage3 with the specified width and height.
   *
   * @param image is the image with width and height.
   */
  public CustomImage3(Image image) {
    super(image);
  }

  /**
   * Downscales the current image to a specified new width and height using bilinear interpolation.
   * The new image is calculated by mapping each pixel in the downscaled image to the corresponding
   * coordinates in the original image, and then interpolating the color values based on the nearest
   * pixels.
   *
   * @param newHeight The height of the new downscaled image.
   * @param newWidth  The width of the new downscaled image.
   * @return A new downscaled image with the specified dimensions.
   * @throws IllegalArgumentException If the new dimensions are non-positive.
   */
  @Override
  public Image downScale(Image image, int newHeight, int newWidth) {
    if (newHeight <= 0 || newWidth <= 0) {
      throw new IllegalArgumentException("invalid input");
    }
    double xScale = (double) image.getWidth() / newWidth;
    double yScale = (double) image.getHeight() / newHeight;

    Image newDownScaledImage = new Image(newWidth, newHeight);

    for (int j = 0; j < newHeight; j++) {
      for (int i = 0; i < newWidth; i++) {

        double originalImageX = i * xScale;
        double originalImageY = j * yScale;

        int x1 = Math.max(0, (int) Math.floor(originalImageX));
        int x2 = Math.min(this.getWidth() - 1, (int) Math.ceil(originalImageX));
        int y1 = Math.max(0, (int) Math.floor(originalImageY));
        int y2 = Math.min(this.getHeight() - 1, (int) Math.ceil(originalImageY));

        Pixel p = calculateColor(image, x1, x2, originalImageX, y1, y2, originalImageY);
        newDownScaledImage.setPixel(i, j, p.getRed(), p.getGreen(), p.getBlue());
      }
    }
    return newDownScaledImage;
  }

  /**
   * Calculates the interpolated color for a specific pixel in the downscaled image based on
   * bilinear interpolation. The method uses the surrounding four pixels in the original image and
   * the fractional distance to compute the interpolated RGB values.
   *
   * @param x1             The x-coordinate of the left neighboring pixel in the original image.
   * @param x2             The x-coordinate of the right neighboring pixel in the original image.
   * @param originalImageX The exact x-coordinate in the original image corresponding to the
   *                       downscaled pixel.
   * @param y1             The y-coordinate of the top neighboring pixel in the original image.
   * @param y2             The y-coordinate of the bottom neighboring pixel in the original image.
   * @param originalImageY The exact y-coordinate in the original image corresponding to the
   *                       downscaled pixel.
   * @return The interpolated {@link Pixel} object containing the calculated RGB values.
   */
  private Pixel calculateColor(Image image, int x1, int x2, double originalImageX, int y1, int y2,
                               double originalImageY) {
    Pixel x1y1 = image.getPixel(x1, y1);
    Pixel x1y2 = image.getPixel(x1, y2);
    Pixel x2y1 = image.getPixel(x2, y1);
    Pixel x2y2 = image.getPixel(x2, y2);

    // Avoid division by zero for edge cases
    double xWeight = (x2 > x1) ? (originalImageX - x1) / (x2 - x1) : 0;
    double yWeight = (y2 > y1) ? (originalImageY - y1) / (y2 - y1) : 0;

    // Interpolate Red channel
    double mRed = x1y1.getRed() * (1 - xWeight) + x2y1.getRed() * xWeight;
    double nRed = x1y2.getRed() * (1 - xWeight) + x2y2.getRed() * xWeight;
    int cRed = (int) (mRed * (1 - yWeight) + nRed * yWeight);

    // Interpolate Green channel
    double mGreen = x1y1.getGreen() * (1 - xWeight) + x2y1.getGreen() * xWeight;
    double nGreen = x1y2.getGreen() * (1 - xWeight) + x2y2.getGreen() * xWeight;
    int cGreen = (int) (mGreen * (1 - yWeight) + nGreen * yWeight);

    // Interpolate Blue channel
    double mBlue = x1y1.getBlue() * (1 - xWeight) + x2y1.getBlue() * xWeight;
    double nBlue = x1y2.getBlue() * (1 - xWeight) + x2y2.getBlue() * xWeight;
    int cBlue = (int) (mBlue * (1 - yWeight) + nBlue * yWeight);

    return new Pixel(cRed, cGreen, cBlue);
  }


  /**
   * Applies a masking operation on the current image by combining it with a masked image and an
   * original image. The masking logic sets pixels from the original image to the resulting image if
   * the corresponding pixel in the masked image is non-black (i.e., all RGB values are non-zero).
   * Otherwise, it retains the pixels from the current image.
   *
   * @param maskedImage   The image used as the mask. Must have same dimensions as current image.
   * @param originalImage The original image whose pixels will be used when mask conditions are
   *                      met.
   * @return A new image resulting from the masking operation.
   * @throws IllegalArgumentException If dimensions of masked image & original image do not match.
   */
  @Override
  public Image maskImg(Image proccessedImage, Image maskedImage, Image originalImage) {
    if (maskedImage.getHeight() != originalImage.getHeight()
            || maskedImage.getWidth() != originalImage.getWidth()) {
      throw new IllegalArgumentException("Masked and masked image must have the same size.");
    }
    Image maskedMaskedImage = new Image(proccessedImage.getWidth(), proccessedImage.getHeight());
    for (int y = 0; y < proccessedImage.getHeight(); y++) {
      for (int x = 0; x < proccessedImage.getWidth(); x++) {
        Pixel temp = proccessedImage.getPixel(x, y);
        Pixel maskedTemp = maskedImage.getPixel(x, y);
        Pixel originalTemp = originalImage.getPixel(x, y);
        if (maskedTemp.getBlue() != 0 && maskedTemp.getRed() != 0 && maskedTemp.getGreen() != 0) {
          maskedMaskedImage.setPixel(x, y, originalImage.getPixel(x, y).getRed(),
                  originalTemp.getGreen(), originalTemp.getBlue());
        } else {
          maskedMaskedImage.setPixel(x, y, temp.getRed(), temp.getGreen(), temp.getBlue());
        }
      }
    }
    return maskedMaskedImage;
  }
}
