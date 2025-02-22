import controller.CommandMappernew;
import model.ImageModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.ImageComparisonUtils;

/**
 * This class tests the controller.
 */
public class TestCommandController {

  private CommandMappernew cm;
  private ImageModel model;

  @Before
  public void setUp() {
    String srcImageName;
    srcImageName = "res/manhattan-small.png";
    model = new ImageModel();
    cm = new CommandMappernew(model);
  }

  @Test
  public void testLoadJpg() throws Exception {
    String[] args;
    args = new String[]{"res/koala.jpg", "kl"};
    cm.routeCommand("load", args);
    Assert.assertTrue(model.getImage("kl") != null);
  }

  @Test
  public void testLoadPNG() throws Exception {
    String[] args;
    args = new String[]{"res/manhattan-small.png", "mh"};
    cm.routeCommand("load", args);
    Assert.assertTrue(model.getImage("mh") != null);
  }

  @Test
  public void testLoadPPM() throws Exception {
    String[] args;
    args = new String[]{"res/koala-jpg-flipv.ppm", "kl-ppm"};
    cm.routeCommand("load", args);
    Assert.assertTrue(model.getImage("kl-ppm") != null);
  }

  @Test
  public void testSaveJpg() throws Exception {
    String[] args;
    args = new String[]{"res/koala.jpg", "kl"};
    cm.routeCommand("load", args);
    args = new String[]{"F/koala-new.jpg", "kl"};
    cm.routeCommand("save", args);
    args = new String[]{"F/koala-new.jpg", "kl-new"};
    cm.routeCommand("load", args);
    Assert.assertTrue(
            ImageComparisonUtils.equals(model.getImage("kl"), model.getImage("kl-new"), 15));
  }

  @Test
  public void testSavePNG() throws Exception {
    String[] args;
    args = new String[]{"res/manhattan-small.png", "mh"};
    cm.routeCommand("load", args);
    args = new String[]{"F/mh-new.png", "mh"};
    cm.routeCommand("save", args);
    args = new String[]{"F/mh-new.png", "mh-new"};
    cm.routeCommand("load", args);
    Assert.assertTrue(
            ImageComparisonUtils.equals(model.getImage("mh"), model.getImage("mh-new"), 15));
  }

  @Test
  public void testRunScript() throws Exception {
    String[] args;
    args = new String[]{"/Users/priyamehta/Desktop/trialnewdesignlatest/res/testcase.txt"};
    cm.routeCommand("run", args);
    Assert.assertTrue(model.getImage("koala-jpg") != null);
    Assert.assertTrue(model.getImage("koala-jpg-brighter") != null);
    Assert.assertTrue(model.getImage("koala-jpg-blur") != null);
    Assert.assertTrue(model.getImage("koala-jpg-blue") != null);
  }


}
