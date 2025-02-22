package controller.loadsave;

/**
 * This class extends the abstractLoadSave class to provide specific implementations for handling
 * jpg image files. It implements the methods defined in the ILoad_Save interface for loading and
 * saving JPG images.
 */
public class Jpg extends AbstractLoadSave {

  /**
   * Returns the image type for this class, which is "jpg".
   *
   * @return A string representing the image type "jpg".
   */
  @Override
  public String getImgType() {
    return "jpg";
  }
}
