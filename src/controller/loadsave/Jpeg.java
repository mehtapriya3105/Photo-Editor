package controller.loadsave;

/**
 * This class extends the abstractLoadSave class to provide specific implementations for handling
 * jpeg image files. It implements the methods defined in the ILoad_Save interface for loading and
 * saving JPEG images.
 */
public class Jpeg extends AbstractLoadSave {

  /**
   * Returns the image type for this class, which is "jpeg".
   *
   * @return A string representing the image type "jpeg".
   */
  @Override
  public String getImgType() {
    return "jpeg";
  }
}
