import static org.junit.Assert.assertTrue;

import controller.Features;
import controller.GuiController;

import java.awt.image.BufferedImage;

import mock.MockModel;
import mock.MockView;

import org.junit.Before;
import org.junit.Test;

import view.IView;

/**
 * This class tests the view by mock tests.
 */
public class MockViewTest {

  private IView mockView;
  private StringBuilder log;
  private BufferedImage testImage;
  private MockModel model;

  @Before
  public void setUp() {
    Features mockFeatures;
    log = new StringBuilder();
    mockView = new MockView(log);
    mockFeatures = new GuiController(model);
    testImage = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < testImage.getWidth(); i++) {
      for (int j = 0; j < testImage.getHeight(); j++) {
        testImage.setRGB(i, j, 80);
      }
    }
  }

  @Test
  public void testAddFeatures() {
    mockView.addFeatures(null); // Passing null as we are only testing the log behavior
    assertTrue(log.toString().contains("addFeatures called."));
  }

  @Test
  public void testDisplayImg() {
    String imageName = "TestImage";
    mockView.displayImg(imageName, testImage);
    assertTrue(log.toString().contains("displayImg called with imageName: " + imageName));
    assertTrue(log.toString().contains("Image dimensions: 100x50"));
  }

  @Test
  public void testDisplayImgWithNull() {
    mockView.displayImg("NullImage", null);
    assertTrue(log.toString().contains("displayImg called with imageName: NullImage"));
    assertTrue(log.toString().contains("Image is null."));
  }

  @Test
  public void testDisplayHisto() {
    mockView.displayHisto(testImage);

    assertTrue(log.toString().contains("displayHisto called."));
    assertTrue(log.toString().contains("Histogram dimensions: 100x50"));
  }

  @Test
  public void testDisplayHistoWithNull() {
    mockView.displayHisto(null);
    assertTrue(log.toString().contains("displayHisto called."));
    assertTrue(log.toString().contains("Histogram is null."));
  }

  @Test
  public void testDisplayHistoWithImage() {
    mockView.displayHisto(testImage);
    assertTrue(log.toString().contains("displayHisto called."));
    assertTrue(log.toString().contains("Histogram dimensions: 100x50"));
  }

  @Test
  public void testHandleSplitAction() {
    mockView.handleSplitAction(testImage);
    assertTrue(log.toString().contains("handleSplitAction called."));
    assertTrue(log.toString().contains("Split image dimensions: 100x50"));
  }

  @Test
  public void testHandleSplitActionWithNull() {
    mockView.handleSplitAction(null);
    assertTrue(log.toString().contains("handleSplitAction called."));
    assertTrue(log.toString().contains("Split image is null."));
  }

  @Test
  public void testHandleSplitActionWithImage() {
    mockView.handleSplitAction(testImage);
    assertTrue(log.toString().contains("handleSplitAction called."));
    assertTrue(log.toString().contains("Split image dimensions: 100x50"));
  }

  @Test
  public void testHandleSplitActionLeveImage() {
    mockView.handleSplitActionLevel(testImage);
    assertTrue(log.toString().contains("handleSplitActionLevel called."));
    assertTrue(log.toString().contains("Split level image dimensions: 100x50"));
  }

  @Test
  public void testHandleSplitActionLevelNullImage() {
    mockView.handleSplitActionLevel(null);
    assertTrue(log.toString().contains("handleSplitActionLevel called."));
    assertTrue(log.toString().contains("Split level image is null."));
  }

}
