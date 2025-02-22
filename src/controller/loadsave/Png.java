package controller.loadsave;

/**
 * This class extends the abstractLoadSave class to provide specific implementations for handling
 * png image files. It implements the methods defined in the ILoad_Save interface for loading and
 * saving PNG images.
 */
public class Png extends AbstractLoadSave {

  /**
   * Returns the image type for this class, which is "png".
   *
   * @return A string representing the image type "png".
   */
  @Override
  public String getImgType() {
    return "png";
  }
}
