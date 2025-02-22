package controller.loadsave;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import model.IImageModel;
import model.database.Image;
import model.database.Pixel;

/**
 * This class implements the ILoad_Save interface for handling PPM (Portable Pixmap) image files. It
 * provides methods to load PPM images from files and save CustomImage objects as PPM files.
 */
public class Ppm implements ILoadSave {

  /**
   * Loads a PPM image from the specified file path and stores it as a CustomImage object with the
   * given destination file name. The method reads the PPM file, ignoring comment lines, and parses
   * the image dimensions and pixel data. It then stores the pixel data in a CustomImage instance
   * and uses ImageManager to save it under the specified destination file name.
   *
   * @param filePath     The path to the source PPM image file to be loaded.
   * @param destFileName The name under which the loaded CustomImage will be stored.
   * @param imageModel   imageModel of interface type IImageModel.
   * @throws IOException If an error occurs while reading the PPM file or file format is invalid.
   */
  public void loadImg(String filePath, String destFileName, IImageModel imageModel)
          throws IOException {
    try (Scanner sc = new Scanner(new FileInputStream(filePath))) {
      StringBuilder builder = new StringBuilder();

      // Read the file line by line, discarding comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s).append(System.lineSeparator());
        }
      }

      try (Scanner ppmScanner = new Scanner(builder.toString())) {
        String token = ppmScanner.next();
        if (!token.equals("P3")) {
          throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
        }

        int width = ppmScanner.nextInt();
        int height = ppmScanner.nextInt();
        Image customImage = new Image(width, height);

        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            int r = ppmScanner.nextInt();
            int g = ppmScanner.nextInt();
            int b = ppmScanner.nextInt();
            customImage.setPixel(j, i, r, g, b);
          }
        }
        imageModel.storeImage(destFileName, customImage);

      }
    }
  }

  /**
   * Saves a CustomImage object as a PPM image to the specified path. The method retrieves the pixel
   * data from the CustomImage and writes it to a file in PPM format. It includes necessary headers
   * ("P3", dimensions, and max color value) and RGB values for each pixel.
   *
   * @param path         The destination path where PPM image will be saved. The path is prefixed
   *                     with "./".
   * @param srcImageName The name of the source CustomImage to be saved.
   * @param imageModel   imageModel of interface type IImageModel.
   * @throws IOException If an error occurs while writing the PPM file.
   */
  public void saveImg(String path, String srcImageName, IImageModel imageModel) throws IOException {
    Image customImage = imageModel.getImage(srcImageName);
    try (PrintWriter writer = new PrintWriter(new FileOutputStream("./" + path))) {
      writer.println("P3");
      writer.println(customImage.getWidth() + " " + customImage.getHeight());
      writer.println(255); // Max color value

      for (int y = 0; y < customImage.getHeight(); y++) {
        for (int x = 0; x < customImage.getWidth(); x++) {
          Pixel rgb = customImage.getPixel(x, y);
          writer.println(rgb.getRed() + " " + rgb.getGreen() + " " + rgb.getBlue());
        }
      }
    }
  }

  /**
   * Returns the image type for this class, which is "ppm".
   *
   * @return A string representing the image type "ppm".
   */
  public String getImgType() {
    return "ppm";
  }

}
