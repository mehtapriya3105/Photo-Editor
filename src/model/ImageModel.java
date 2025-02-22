package model;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import model.database.CustomImage;
import model.database.CustomImage2;
import model.database.CustomImage3;
import model.database.ICustomImage;
import model.database.ICustomImage2;
import model.database.ICustomImage3;
import model.database.Image;

/**
 * This is the image model class that has all functionalities for image manipulation.
 */
public class ImageModel implements IImageModel {

  private static Map<String, Image> ImageManger = new HashMap<>();

  private ICustomImage customImage;
  private ICustomImage2 customImage2;
  private ICustomImage3 customImage3;

  /**
   * Constructor that assigns ImageManger of object as hashmap.
   */
  public ImageModel() {
    this.ImageManger = new HashMap<>();
  }

  /**
   * Stores a CustomImage object in the image storage under a specified name.
   *
   * @param name  The name to associate with the image
   * @param image The CustomImage object to store
   */
  public void storeImage(String name, Image image) {
    ImageManger.put(name, image);
  }

  /**
   * Retrieves a CustomImage object from the storage by its name.
   *
   * @param name The name of the image to retrieve
   * @return The CustomImage associated with the specified name, or null if not found
   * @throws FileNotFoundException if the image does not exist in storage
   */
  public Image getImage(String name) throws FileNotFoundException {
    if (!ImageManger.containsKey(name)) {
      return null;
    }
    return ImageManger.get(name);
  }

  /**
   * Applies a blur effect to an image and stores the result.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void blurMethod(String sourceFileName, String destFileName, String cmand, String p,
                         String mask, String maskImage) throws FileNotFoundException {

    Image originalImage = getImage(sourceFileName);
    customImage = new CustomImage(originalImage);

    Image processedImage = customImage.blur(originalImage);
    customImage2 = new CustomImage2(processedImage);

    if ("split".equals(cmand)) {
      processedImage = customImage2.splitImg(processedImage, originalImage, p);
    } else if (cmand != null) {
      throw new IllegalArgumentException("Invalid command: " + cmand);
    }

    if (mask != null) {
      Image maskOfImage = getImage(maskImage);
      customImage3 = new CustomImage3(processedImage);
      processedImage = customImage3.maskImg(processedImage, maskOfImage, originalImage);
    }

    storeImage(destFileName, processedImage);
  }

  /**
   * Calculates the intensity of an image and applies optional transformations.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void intensityMethod(String sourceFileName, String destFileName, String cmand, String p,
                              String mask, String maskImage)
          throws FileNotFoundException {
    Image originalImage = getImage(sourceFileName);
    customImage = new CustomImage(originalImage);
    Image processedImage = customImage.calculateIntensity(originalImage);

    if ("split".equals(cmand)) {
      customImage2 = new CustomImage2(processedImage);
      processedImage = customImage2.splitImg(processedImage, originalImage, p);
    } else if (cmand != null) {
      throw new IllegalArgumentException("Invalid command: " + cmand);
    }
    if (mask != null) {
      Image maskOfImage = getImage(maskImage);
      customImage3 = new CustomImage3(processedImage);
      processedImage = customImage3.maskImg(processedImage, maskOfImage, originalImage);
    }

    storeImage(destFileName, processedImage);
  }

  /**
   * Calculates the luma of an image and applies optional transformations.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void lumaMethod(String sourceFileName, String destFileName, String cmand, String p,
                         String mask, String maskImage)
          throws FileNotFoundException {

    Image originalImage = getImage(sourceFileName);
    customImage = new CustomImage(originalImage);
    Image processedImage = customImage.calculateLuma(originalImage);

    if ("split".equals(cmand)) {
      customImage2 = new CustomImage2(processedImage);
      processedImage = customImage2.splitImg(processedImage, originalImage, p);
    } else if (cmand != null) {
      throw new IllegalArgumentException("Invalid command: " + cmand);
    }

    if (mask != null) {
      Image maskOfImage = getImage(maskImage);
      customImage3 = new CustomImage3(processedImage);
      processedImage = customImage3.maskImg(processedImage, maskOfImage, originalImage);
    }

    storeImage(destFileName, processedImage);
  }

  /**
   * Applies a sepia tone effect to an image and stores the result.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void sepiaMethod(String sourceFileName, String destFileName, String cmand, String p,
                          String mask, String maskImage)
          throws FileNotFoundException {
    Image originalImage = getImage(sourceFileName);
    customImage = new CustomImage(originalImage);
    Image processedImage = customImage.sepia(originalImage);

    if ("split".equals(cmand)) {
      customImage2 = new CustomImage2(processedImage);
      processedImage = customImage2.splitImg(processedImage, originalImage, p);

    } else if (cmand != null) {
      throw new IllegalArgumentException("Invalid command: " + cmand);
    }

    if (mask != null) {
      Image maskOfImage = getImage(maskImage);
      customImage3 = new CustomImage3(processedImage);
      processedImage = customImage3.maskImg(processedImage, maskOfImage, originalImage);

    }

    storeImage(destFileName, processedImage);
  }

  /**
   * Applies a sharpening filter to an image and stores the result.
   *
   * @param sourceFileName The name of the source image.
   * @param destFileName   The name to save the processed image.
   * @param cmand          Optional command (e.g., "split").
   * @param p              Parameter for the command (if applicable).
   * @param mask           Optional mask parameter.
   * @param maskImage      Name of the mask image to apply.
   * @throws FileNotFoundException if the source image or mask is not found.
   */
  @Override
  public void sharpenMethod(String sourceFileName, String destFileName, String cmand, String p,
                            String mask, String maskImage)
          throws FileNotFoundException {
    Image originalImage = getImage(sourceFileName);
    customImage = new CustomImage(originalImage);
    Image processedImage = customImage.sharpen(originalImage);

    if ("split".equals(cmand)) {
      customImage2 = new CustomImage2(processedImage);
      processedImage = customImage2.splitImg(processedImage, originalImage, p);

    } else if (cmand != null) {
      throw new IllegalArgumentException("Invalid command: " + cmand);
    }

    if (mask != null) {
      Image maskOfImage = getImage(maskImage);
      customImage3 = new CustomImage3(processedImage);
      processedImage = customImage3.maskImg(processedImage, maskOfImage, originalImage);
    }

    storeImage(destFileName, processedImage);
  }

  /**
   * Processes an image to calculate its value and optionally applies split and mask commands.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @param mask           indicates if a mask should be applied
   * @param maskImage      the name of the mask image file
   * @throws FileNotFoundException if the source or mask image file is not found
   */
  @Override
  public void valueMethod(String sourceFileName, String destFileName, String cmand, String p,
                          String mask, String maskImage)
          throws FileNotFoundException {
    Image originalImage = getImage(sourceFileName);
    customImage = new CustomImage(originalImage);

    Image processedImage = customImage.calculateValue(originalImage);

    if ("split".equals(cmand)) {
      customImage2 = new CustomImage2(processedImage);
      processedImage = customImage2.splitImg(processedImage, originalImage, p);

    } else if (cmand != null) {
      throw new IllegalArgumentException("Invalid command: " + cmand);
    }

    if (mask != null) {
      Image maskOfImage = getImage(maskImage);
      customImage3 = new CustomImage3(processedImage);
      processedImage = customImage3.maskImg(processedImage, maskOfImage, originalImage);

    }

    storeImage(destFileName, processedImage);
  }

  /**
   * Horizontally flips an image and stores the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @throws Exception if the source file is not found or flipping fails
   */
  @Override
  public void horizontalFlipMethod(String sourceFileName, String destFileName) throws Exception {
    Image img = getImage(sourceFileName);
    if (img == null) {
      return;
    }
    customImage = new CustomImage(img);
    Image flippedImage = customImage.fliph(img);
    storeImage(destFileName, flippedImage);
  }

  /**
   * Vertically flips an image and stores the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @throws Exception if the source file is not found or flipping fails
   */
  @Override
  public void verticalFlipMethod(String sourceFileName, String destFileName) throws Exception {
    Image img = getImage(sourceFileName);

    if (img == null) {
      return;
    }

    customImage = new CustomImage(img);
    Image flippedImage = customImage.flipv(img);

    storeImage(destFileName, flippedImage);
  }

  /**
   * Applies color correction to an image and optionally splits it.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @throws FileNotFoundException if the source file is not found
   */
  @Override
  public void colorCorrectMethod(String sourceFileName, String destFileName, String cmand, String p)
          throws FileNotFoundException {
    Image originalImage = getImage(sourceFileName);
    customImage2 = new CustomImage2(originalImage);
    Image proccessedImage = customImage2.colorCorrectionCommand(originalImage);
    if (cmand == null) {
      storeImage(destFileName, proccessedImage);
    } else {
      if (!cmand.equals("split")) {
        throw new IllegalArgumentException("Wrong command");
      }

      storeImage(destFileName, customImage2.splitImg(proccessedImage, originalImage, p));
    }
  }

  /**
   * Creates a histogram representation of an image and stores it in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @throws FileNotFoundException if the source file is not found
   */
  @Override
  public void histogramMethod(String sourceFileName, String destFileName)
          throws FileNotFoundException {
    Image originalImage = getImage(sourceFileName);
    customImage2 = new CustomImage2(originalImage);

    if (originalImage == null) {
      throw new FileNotFoundException("File not found");
    }
    BufferedImage bfImage = customImage2.createHistogram(originalImage);

    Image cm = new Image(256, 256);

    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        int rgb = bfImage.getRGB(i, j);
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        cm.setPixel(i, j, r, g, b);
      }
    }
    storeImage(destFileName, cm);
  }

  /**
   * Extracts the blue component of an image, optionally applies split and mask commands, and stores
   * the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @param mask           indicates if a mask should be applied
   * @param maskImage      the name of the mask image file
   * @throws FileNotFoundException if the source or mask image file is not found
   */
  @Override
  public void getBlueImageMethod(String sourceFileName, String destFileName, String cmand, String p,
                                 String mask,
                                 String maskImage)
          throws FileNotFoundException {

    Image originalImage = getImage(sourceFileName);
    customImage = new CustomImage(originalImage);
    Image processedImage = customImage.getBlueImage(originalImage);

    if ("split".equals(cmand)) {
      customImage2 = new CustomImage2(processedImage);
      processedImage = customImage2.splitImg(processedImage, originalImage, p);

    } else if (cmand != null) {
      throw new IllegalArgumentException("Invalid command: " + cmand);
    }

    if (mask != null) {
      Image maskOfImage = getImage(maskImage);
      customImage3 = new CustomImage3(processedImage);
      processedImage = customImage3.maskImg(processedImage, maskOfImage, originalImage);
    }

    storeImage(destFileName, processedImage);
  }

  /**
   * Extracts the red component of an image, optionally applies split and mask commands, and stores
   * the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @param mask           indicates if a mask should be applied
   * @param maskImage      the name of the mask image file
   * @throws FileNotFoundException if the source or mask image file is not found
   */
  @Override
  public void getRedComponentMethod(String sourceFileName, String destFileName, String cmand,
                                    String p, String mask,
                                    String maskImage)
          throws FileNotFoundException {

    Image originalImage = getImage(sourceFileName);
    customImage = new CustomImage(originalImage);
    Image processedImage = customImage.getRedImage(originalImage);

    if ("split".equals(cmand)) {
      customImage2 = new CustomImage2(processedImage);
      processedImage = customImage2.splitImg(processedImage, originalImage, p);
    } else if (cmand != null) {
      throw new IllegalArgumentException("Invalid command: " + cmand);
    }

    if (mask != null) {
      Image maskOfImage = getImage(maskImage);
      customImage3 = new CustomImage3(processedImage);
      processedImage = customImage3.maskImg(processedImage, maskOfImage, originalImage);
    }

    storeImage(destFileName, processedImage);
  }

  /**
   * Extracts the green component of an image, optionally applies split and mask commands, and
   * stores the result in a destination file.
   *
   * @param sourceFileName the name of the source image file
   * @param destFileName   the name of the destination image file
   * @param cmand          the command to execute (e.g., "split")
   * @param p              parameter used for the "split" command
   * @param mask           indicates if a mask should be applied
   * @param maskImage      the name of the mask image file
   * @throws FileNotFoundException if the source or mask image file is not found
   */
  @Override
  public void getGreenComponentImage(String sourceFileName, String destFileName, String cmand,
                                     String p, String mask,
                                     String maskImage)
          throws FileNotFoundException {

    Image originalImage = getImage(sourceFileName);
    customImage = new CustomImage(originalImage);
    Image processedImage = customImage.getGreenImage(originalImage);

    if ("split".equals(cmand)) {
      customImage2 = new CustomImage2(processedImage);
      processedImage = customImage2.splitImg(processedImage, originalImage, p);
    } else if (cmand != null) {
      throw new IllegalArgumentException("Invalid command: " + cmand);
    }

    if (mask != null) {
      Image maskOfImage = getImage(maskImage);
      customImage3 = new CustomImage3(processedImage);
      processedImage = customImage3.maskImg(processedImage, maskOfImage, originalImage);
    }

    storeImage(destFileName, processedImage);
  }

  /**
   * Combines red, green, and blue component images into a single RGB image.
   *
   * @param redImageName   the name of the red component image file
   * @param greenImageName the name of the green component image file
   * @param blueImageName  the name of the blue component image file
   * @param imageName      the name of the resulting RGB image file
   * @throws FileNotFoundException if any of the component image files are not found
   */
  @Override
  public void rgbCombineMethod(String redImageName, String greenImageName, String blueImageName,
                               String imageName)
          throws FileNotFoundException {
    Image newImage = new Image(0, 0);
    Image redImage = getImage(redImageName);
    Image greenImage = getImage(greenImageName);
    Image blueImage = getImage(blueImageName);
    customImage = new CustomImage(newImage);
    storeImage(imageName, customImage.rgbcombine(redImage, greenImage, blueImage, imageName));
  }

  /**
   * Splits an RGB image into separate red, green, and blue component images.
   *
   * @param redImageName   the name of the red component image file
   * @param greenImageName the name of the green component image file
   * @param blueImageName  the name of the blue component image file
   * @param imageName      the name of the RGB image file
   * @throws FileNotFoundException if the RGB image file is not found
   */
  @Override
  public void rgbSplitMethod(String redImageName, String greenImageName, String blueImageName,
                             String imageName)
          throws FileNotFoundException {
    Image newImage = getImage(imageName);
    Image newImageDummy = new Image(0, 0);
    customImage = new CustomImage(newImage);
    Image[] newSplitimages = customImage.rgbsplit(newImage);
    storeImage(redImageName, newSplitimages[0]);
    storeImage(greenImageName, newSplitimages[1]);
    storeImage(blueImageName, newSplitimages[2]);
  }

  /**
   * Brightens an image by a specified value and stores the result in a destination file.
   *
   * @param brightness      the value to adjust brightness
   * @param imageName       the name of the source image file
   * @param destinationName the name of the destination image file
   * @throws FileNotFoundException if the source image file is not found
   */
  @Override
  public void brightenMethod(Integer brightness, String imageName, String destinationName)
          throws FileNotFoundException {
    Image img;
    try {
      img = getImage(imageName);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    // Check if the image exists; if not, exit the method
    if (img == null) {
      return;
    }

    customImage = new CustomImage(img);
    Image image = customImage.brighten(brightness, img);
    storeImage(destinationName, image);
  }

  /**
   * Adjusts the levels of an image (black, mid, and white) and optionally applies split command.
   *
   * @param b               the black level adjustment value
   * @param m               the mid-tone level adjustment value
   * @param w               the white level adjustment value
   * @param imageName       the name of the source image file
   * @param destinationName the name of the destination image file
   * @param p               parameter used for the "split" command
   * @param cmand           the command to execute (e.g., "split")
   * @throws FileNotFoundException if the source image file is not found
   */
  @Override
  public void levelAdjustMethod(int b, int m, int w, String imageName, String destinationName,
                                int p, String cmand)
          throws FileNotFoundException {
    Image originalImage = getImage(imageName);
    if (originalImage == null) {
      throw new FileNotFoundException("Image not found: " + imageName);
    }

    customImage2 = new CustomImage2(originalImage);

    Image proccesssedImage = customImage2.adjustColorCommand(originalImage, b, m, w);
    if ("split".equals(cmand)) {
      customImage2 = new CustomImage2(proccesssedImage);
      storeImage(destinationName,
              customImage2.splitImg(proccesssedImage, originalImage, Integer.toString(p)));
    } else {
      storeImage(destinationName, proccesssedImage);
    }
  }

  /**
   * Compresses an image by a specified percentage and stores the result in a destination file.
   *
   * @param imageName       the name of the source image file
   * @param destinationName the name of the destination image file
   * @param percentage      the compression percentage
   * @throws FileNotFoundException if the source image file is not found
   */
  @Override
  public void compressMethod(String imageName, String destinationName, Integer percentage)
          throws FileNotFoundException {
    Image originalImage = getImage(imageName);
    customImage2 = new CustomImage2(originalImage);

    if (originalImage == null) {
      return;
    }

    Image compressImage = customImage2.compress(originalImage, percentage);

    storeImage(destinationName, compressImage);
  }

  /**
   * Downscales an image to a new width and height and stores the result in a destination file.
   *
   * @param imageName       the name of the source image file
   * @param destinationName the name of the destination image file
   * @param newHeight       the new height of the image
   * @param newWidth        the new width of the image
   * @throws FileNotFoundException if the source image file is not found
   */
  @Override
  public void imageDownScalling(String imageName, String destinationName, int newHeight,
                                int newWidth)
          throws FileNotFoundException {
    Image originalImage = getImage(imageName);

    if (originalImage == null) {
      return;
    }

    customImage3 = new CustomImage3(originalImage);
    Image downScalledImage = customImage3.downScale(originalImage, newHeight, newWidth);

    storeImage(destinationName, downScalledImage);
  }
}
