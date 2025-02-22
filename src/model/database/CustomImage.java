package model.database;


import java.io.FileNotFoundException;

/**
 * This is the public class. It implements ICustomImage.
 */
public class CustomImage implements ICustomImage {


  private Image image;
  private int width;
  private int height;

  /**
   * Initializes a new CustomImage with the specified width and height, creating pixel arrays for
   * the red, green, and blue channels.
   */
  public CustomImage(Image image) {
    this.image = image;
    this.width = image.getWidth();
    this.height = image.getHeight();
  }


  /**
   * Returns the width of the image.
   */
  public int getWidth() {
    return image.getWidth();
  }

  /**
   * Returns the height of the image.
   */
  public int getHeight() {
    return image.getWidth();
  }

  /**
   * Gets Pixel of image.
   */
  public Pixel getPixel(int x, int y) {
    return image.getPixel(x, y);
  }

  /**
   * Returns a new CustomImage containing only the blue component of the original image by setting
   * red and green values to 0.
   */
  public Image getBlueImage(Image image) {
    Image newImageName = new Image(image.getWidth(), image.getHeight());
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel temp = this.getPixel(x, y);
        newImageName.setPixel(x, y, temp.getBlue(), temp.getBlue(), temp.getBlue());
      }
    }
    return newImageName;
  }


  /**
   * Returns a new CustomImage containing only the green component of the original image by setting
   * red and blue values to 0.
   */
  public Image getGreenImage(Image image) {
    Image newImageName = new Image(image.getWidth(), image.getHeight());
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel temp = this.getPixel(x, y);
        newImageName.setPixel(x, y, temp.getGreen(), temp.getGreen(), temp.getGreen());
      }
    }
    return newImageName;
  }

  /**
   * Returns a new CustomImage containing only the red component of the original image by setting
   * green and blue values to 0.
   */
  public Image getRedImage(Image image) {
    Image newImageName = new Image(image.getWidth(), image.getHeight());
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel temp = this.getPixel(x, y);
        newImageName.setPixel(x, y, temp.getRed(), temp.getRed(), temp.getRed());
      }
    }
    return newImageName;
  }


  /**
   * Splits the original image into separate red, green, and blue images and stores them using the
   * provided names.
   */
  @Override
  public Image[] rgbsplit(Image image) {
    Image redImage = this.getRedImage(image);
    Image greenImage = this.getGreenImage(image);
    Image blueImage = this.getBlueImage(image);
    return new Image[]{redImage, greenImage, blueImage};
  }

  /**
   * Combines the separate red, green, and blue images into a new image and stores it using the
   * specified name. Throws FileNotFoundException if any of the images cannot be found.
   */
  @Override
  public Image rgbcombine(Image redImageName, Image greenImageName,
                          Image blueImageName, String imgName)
          throws FileNotFoundException {
    Image red_image = redImageName;
    Image blue_image = blueImageName;
    Image green_image = greenImageName;

    if (red_image == null || blue_image == null || green_image == null) {
      throw new FileNotFoundException();
    }

    if (red_image.getHeight() != blue_image.getHeight()
            || red_image.getHeight() != green_image.getHeight()) {
      throw new IllegalArgumentException("height  does not match all rgb heights");
    }

    if (red_image.getWidth() != blue_image.getWidth()
            || red_image.getWidth() != green_image.getWidth()) {
      throw new IllegalArgumentException("width does not match all rgb widths");
    }
    Image newImage = new Image(red_image.getWidth(), red_image.getHeight());
    for (int y = 0; y < red_image.getHeight(); y++) {
      for (int x = 0; x < red_image.getWidth(); x++) {
        newImage.setPixel(x, y, red_image.getPixel(x, y).getRed(),
                green_image.getPixel(x, y).getGreen(),
                blue_image.getPixel(x, y).getBlue());
      }
    }
    return newImage;
  }

  /**
   * Flips the image horizontally and returns the new flipped image.
   *
   * @return a new CustomImage that is horizontally flipped.
   */
  @Override
  public Image fliph(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    Image flippedImage = new Image(width, height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel temp = this.getPixel(x, y);

        // Calculate the flipped x-coordinate
        int flippedX = width - 1 - x;

        // Set the pixel in the flipped position
        flippedImage.setPixel(flippedX, y, temp.getRed(), temp.getGreen(), temp.getBlue());

      }
    }
    return flippedImage;
  }


  /**
   * Flips the image vertically and returns the new flipped image.
   */
  @Override
  public Image flipv(Image image) {
    Image flippedImage = new Image(image.getWidth(), image.getHeight());
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel temp = image.getPixel(x, y);
        flippedImage.setPixel(x, image.getHeight() - 1 - y, temp.getRed(), temp.getGreen(),
                temp.getBlue());
      }
    }
    return flippedImage;
  }

  /**
   * Brightens the image by the specified increment and returns the brightened image.
   */
  @Override
  public Image brighten(int inc, Image image) {
    Image brightenedImage = new Image(image.getWidth(), image.getHeight());
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {

        Pixel temp = image.getPixel(x, y);

        int red = clamp((temp.getRed() + inc));
        int green = clamp((temp.getGreen() + inc));
        int blue = clamp((temp.getBlue() + inc));

        brightenedImage.setPixel(x, y, red, green, blue);
      }
    }
    return brightenedImage;
  }

  /**
   * Applies the specified convolution kernel to the image, filtering it and returning the resulting
   * image.
   */
  private Image applyKernel(double[][] kernel, Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    Image newImage = new Image(width, height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {

        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;
        int kernelRadius = kernel.length / 2;

        for (int i = -kernelRadius; i <= kernelRadius; i++) {
          for (int j = -kernelRadius; j <= kernelRadius; j++) {
            int kernelX = i + kernelRadius;
            int kernelY = j + kernelRadius;

            if (x + i >= 0 && x + i < width && y + j >= 0 && y + j < height) {
              Pixel neighboringPixel = image.getPixel(x + i, y + j);
              redSum += neighboringPixel.getRed() * kernel[kernelY][kernelX];
              greenSum += neighboringPixel.getGreen() * kernel[kernelY][kernelX];
              blueSum += neighboringPixel.getBlue() * kernel[kernelY][kernelX];
            }
          }
        }

        int newRed = (int) Math.max(0, Math.min(redSum, 255));
        int newGreen = (int) Math.max(0, Math.min(greenSum, 255));
        int newBlue = (int) Math.max(0, Math.min(blueSum, 255));
        newImage.setPixel(x, y, newRed, newGreen, newBlue);
      }
    }

    return newImage;
  }


  /**
   * Applies a Gaussian blur filter to the image and returns the blurred image.
   */
  public Image blur(Image image) {
    final double[][] kernel = {
            {1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}
    };
    return this.applyKernel(kernel, image);
  }

  /**
   * Applies a sharpening filter to the image and returns the sharpened image.
   */
  public Image sharpen(Image image) {
    final double[][] kernel = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };
    return this.applyKernel(kernel, image);
  }

  /**
   * Applies a sepia tone effect to the image and returns the modified image.
   */
  public Image sepia(Image image) {
    Image newImage = new Image(image.getWidth(), image.getHeight());

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel temp = image.getPixel(x, y);

        int newRed = (int) (0.393 * temp.getRed() + 0.769 * temp.getGreen()
                + 0.189 * temp.getBlue());
        int newGreen = (int) (0.349 * temp.getRed() + 0.686 * temp.getGreen()
                + 0.168 * temp.getBlue());
        int newBlue = (int) (0.272 * temp.getRed() + 0.534 * temp.getGreen()
                + 0.131 * temp.getBlue());

        newRed = clamp(newRed);
        newGreen = clamp(newGreen);
        newBlue = clamp(newBlue);

        newImage.setPixel(x, y, newRed, newGreen, newBlue);
      }
    }
    return newImage;
  }

  private int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }


  /**
   * Calculates the luma value for each pixel in the image and returns a new image where each
   * pixel's RGB values are set to the corresponding luma value (grayscale).
   */
  public Image calculateLuma(Image image) {
    Image lumaImage = new Image(image.getWidth(), image.getHeight());

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel temp = image.getPixel(x, y);

        int lumaValue = (int) (0.2126 * temp.getRed() + 0.7152 * temp.getGreen()
                + 0.0722 * temp.getBlue());

        lumaImage.setPixel(x, y, lumaValue, lumaValue, lumaValue);

      }
    }
    return lumaImage;
  }

  /**
   * Calculates the intensity (average of RGB values) for each pixel in the image and returns a new
   * intensity image where each pixel's RGB values are set to the corresponding intensity value
   * (grayscale).
   */
  public Image calculateIntensity(Image image) {
    Image intensityImage = new Image(image.getWidth(), image.getHeight());
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel temp = image.getPixel(x, y);

        int intensityValue = ((temp.getRed() + temp.getGreen() + temp.getBlue()) / 3);
        intensityImage.setPixel(x, y, intensityValue, intensityValue, intensityValue);
      }
    }
    return intensityImage;
  }


  /**
   * Calculates the value (maximum of R, G, B values) for each pixel in the image and returns a new
   * image where each pixel's RGB values are set to the corresponding value (grayscale).
   *
   * @return a new CustomImage representing the value values.
   */
  @Override
  public Image calculateValue(Image image) {
    Image valueImage = new Image(image.getWidth(), image.getHeight());
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel temp = image.getPixel(x, y);

        int value = Math.max(temp.getRed(), Math.max(temp.getGreen(), temp.getBlue()));

        valueImage.setPixel(x, y, value, value, value);
      }
    }

    return valueImage;
  }


}

