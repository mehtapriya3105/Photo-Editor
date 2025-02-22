package model.database;

/**
 * Interface for CustomImage3 that has methods for masking and downscaling. This interface
 * extends ICustomImage2.
 */
public interface ICustomImage3 extends ICustomImage2 {

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
  Image downScale(Image image, int newHeight, int newWidth);

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
   * @throws IllegalArgumentException If dimensions of masked image and original image do not
   *                                  match.
   */
  Image maskImg(Image proccessedImage, Image maskedImage, Image originalImage);
}
