package controller.loadsave;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import model.IImageModel;
import model.database.Image;
import model.database.Pixel;

/**
 * Abstract base class for loading and saving image files. Implements the ILoad_Save interface and
 * provides common methods for image handling. Specific image formats (e.g., PNG, etc.) should
 * extend this class and implement abstract methods.
 */
public abstract class AbstractLoadSave implements ILoadSave {

  /**
   * Loads an image from the specified file path and converts it into a CustomImage object. The
   * processed image is then stored using the specified destination file name. This method reads the
   * image file, extracts pixel RGB values, and populates a CustomImage instance with this data. It
   * saves CustomImage using ImageManager.
   *
   * @param path         The relative path to the source image file.
   * @param destFileName The name for the stored CustomImage.
   * @param imageModel   imageModel of interface type IImageModel.
   * @throws IOException If an error occurs while reading the image file or storing the
   *                     CustomImage.
   */
  public void loadImg(String path, String destFileName, IImageModel imageModel) throws IOException {

    BufferedImage bf = ImageIO.read(new File(path));
    Image cm = new Image(bf.getWidth(), bf.getHeight());
    System.out.println("width: " + bf.getWidth());
    System.out.println("height: " + bf.getHeight());
    if (bf == null) {
      throw new FileNotFoundException("Image not found");
    }

    for (int i = 0; i < bf.getWidth(); i++) {
      for (int j = 0; j < bf.getHeight(); j++) {
        int rgb = bf.getRGB(i, j);
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        cm.setPixel(i, j, r, g, b);
      }
    }

    System.out.println("load command executed successfully!");
    imageModel.storeImage(destFileName, cm);

  }

  /**
   * Saves a CustomImage object to a specified path in the format determined by the subclass. This
   * method retrieves the pixel data from the CustomImage, converts it to a BufferedImage, and
   * writes it to the file system. The output image format will be based on the result of the
   * getImgType() method.
   *
   * @param path         The destination file path for the image
   * @param srcImageName The name of the CustomImage to be saved
   * @param imageModel   imageModel of interface type IImageModel.
   * @throws IOException If an error occurs while retrieving the CustomImage or writing to the
   *                     file.
   */
  public void saveImg(String path, String srcImageName, IImageModel imageModel) throws IOException {

    Image cm = imageModel.getImage(srcImageName);
    if (cm == null) {
      throw new FileNotFoundException("Image not found");
    }
    BufferedImage bf = new BufferedImage(cm.getWidth(), cm.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < cm.getWidth(); i++) {
      for (int j = 0; j < cm.getHeight(); j++) {
        Pixel rgb = cm.getPixel(i, j);
        bf.setRGB(i, j,
                ((int) rgb.getRed() << 16) | ((int) rgb.getGreen() << 8) | (int) rgb.getBlue());
      }
    }

    Path outputPath = Paths.get(path);
    ImageIO.write(bf, getImgType(), outputPath.toFile());
    System.out.println("save command executed successfully!");
  }

  /**
   * Returns the image type specific to the subclass. Subclasses must provide their implementation
   * of this method.
   *
   * @return A string representing the image type (e.g., "jpg", etc.).
   */
  public abstract String getImgType();


}
