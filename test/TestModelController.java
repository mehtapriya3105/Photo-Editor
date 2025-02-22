import static org.junit.Assert.assertEquals;

import controller.ImageProcessorController;

import java.io.StringReader;

import mock.MockModel;
import model.IImageModel;

import org.junit.Test;


/**
 * These are Mock test for the Image Model. They check the following: simple command, split command,
 * mask commands. They also check the exception when the user enters command of wrong length. It
 * checks all following function: 1. Simple means normal basic commands - Commands For simple: Red,
 * Blue,Green,Blur,Sepia,Sharpen,Intensity,Luma,Value, Horizontal Flip, VerticalFlip,
 * Color-Correct,Histogram, Level-Adjust, RGB Split,RGB Combine, Brighten, Compress, Image
 * DownScaling 2. Commands for Split: Red,
 * Blue,Green,Blur,Sepia,Sharpen,Intensity,Luma,Value,Color-Correct,Level-Adjust
 * 3. Commands for Mask: Red,Blue,Green,Blur,Sepia,Sharpen,Intensity,Luma,Value
 */
public class TestModelController {

  //simple
  @Test
  public void testControllerBlueComponentSimple() {
    String inputData = "blue-component inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called BlueImage method!\nBlueImage Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }


  @Test(expected = RuntimeException.class)
  public void testControllerBlueCompoenentSimpleInvalidCommandLength() {
    String inputData = "blue-component inputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called BlueImage method!\nBlueImage Command Image Saved";
    assertEquals(expectedOutput, logOutput);

  }

  @Test(expected = RuntimeException.class)
  public void testControllerBlueCompoenentSimpleInvalidCommandSpell() {
    String inputData = "ble-component inputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called BlueImage method!\nBlueImage Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }

  @Test
  public void testControllerGreenComponentSimple() {
    String inputData = "green-component inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called GreenImage method!\nGreenImage Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }

  @Test(expected = RuntimeException.class)
  public void testControllerGreenComponentSimpleInvalidCommandLength() {
    String inputData = "green-component  outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called GreenImage method!\nGreenImage Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }

  @Test(expected = RuntimeException.class)
  public void testControllerGreenComponentSimpleInvalidCommandSpell() {
    String inputData = "grn-component  outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called GreenImage method!\nGreenImage Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }


  @Test
  public void testSepiaSimple() {
    String inputData = "sepia inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called Sepia method!\nSepia Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }

  @Test(expected = RuntimeException.class)
  public void testSepiaSimpleInvalidCommandLength() {
    String inputData = "sepia outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called Sepia method!\nSepia Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }

  @Test(expected = RuntimeException.class)
  public void testSepiaSimpleInvalidCommandSpell() {
    String inputData = "sia inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called Sepia method!\nSepia Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }


  @Test
  public void testSharpenSimple() {
    String inputData = "sharpen inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called Sharpen method!\nSharpen Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }

  @Test(expected = RuntimeException.class)
  public void testSharpenSimpleInvalidCommandLength() {
    String inputData = "sharpen inputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called Sharpen method!\nSharpen Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }

  @Test(expected = RuntimeException.class)
  public void testSharpenSimpleInvalidCommandSpell() {
    String inputData = "sarpen inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(
            new StringReader(inputData), mockModel);
    controller.start();
    String logOutput = ((MockModel) mockModel).getLog().trim();
    System.out.println(logOutput);
    String expectedOutput = "Called Sharpen method!\nSharpen Command Image Saved";
    assertEquals(expectedOutput, logOutput);
  }


  @Test
  public void testIntensitySimple() {
    String inputData = "intensity-component inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Intensity method!\n" +
                    "Intensity Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testIntensitySimpleInvalidCommandLength() {
    String inputData = "intensity-component  outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Intensity method!\n" +
                    "Intensity Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testIntensitySimpleInvalidCommandSpell() {
    String inputData = "inten-component inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Intensity method!\n" +
                    "Intensity Command Image Saved");
  }


  @Test
  public void testLumaSimple() {
    String inputData = "luma-component inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Luma method!\n" +
                    "Luma Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testLumaSimpleInvalidCommandLength() {
    String inputData = "luma-component outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Luma method!\n" +
                    "Luma Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testLumaSimpleInvalidCommandSpell() {
    String inputData = "luma inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Luma method!\n" +
                    "Luma Command Image Saved");
  }

  @Test
  public void testValueSimple() {
    String inputData = "value-component inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Value method!\n" +
                    "Value Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testValueSimpleInvalidCommandLength() {
    String inputData = "value-component  outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Value method!\n" +
                    "Value Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testValueSimpleInvalidCommandSpell() {
    String inputData = "value inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Value method!\n" +
                    "Value Command Image Saved");
  }

  @Test
  public void testHorizontalFlipSimple() {
    String inputData = "horizontal-flip inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called horizontalFlipMethod with sourceFileName: inputName, destFileName: outputName");
  }

  @Test(expected = RuntimeException.class)
  public void testHorizontalFlipSimpleInvalidCommandLength() {
    String inputData = "horizontal-flip outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called horizontalFlipMethod with sourceFileName: inputName, " +
                    "destFileName: outputName");
  }

  @Test(expected = RuntimeException.class)
  public void testHorizontalFlipSimpleInvalidCommandSpell() {
    String inputData = "horizontal-flp inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called horizontalFlipMethod with sourceFileName: inputName, " +
                    "destFileName: outputName");
  }

  @Test
  public void testVerticalFlipSimple() {
    String inputData = "vertical-flip inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called verticalFlipMethod with sourceFileName: inputName, " +
                    "destFileName: outputName");
  }

  @Test
  public void testColorCorrectSimple() {
    String inputData = "color-correct inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();

    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Color-Correct method!\n" +
                    "Color-Correct Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testColorCorrectSimpleInvalidCommandLength() {
    String inputData = "color-correct  outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();

    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Color-Correct method!\n" +
                    "Color-Correct Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testColorCorrectSimpleInvalidCommandSpell() {
    String inputData = "color-corect inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();

    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Color-Correct method!\n" +
                    "Color-Correct Command Image Saved");
  }

  @Test
  public void testHistogramSimple() {
    String inputData = "histogram inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called histogramMethod with sourceFileName: inputName, " +
                    "destFileName: outputName");
  }

  @Test(expected = RuntimeException.class)
  public void testHistogramSimpleInvalidCommandLength() {
    String inputData = "histogram outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called histogramMethod with sourceFileName: inputName, " +
                    "destFileName: outputName");
  }

  @Test(expected = RuntimeException.class)
  public void testHistogramSimpleInvalidCommandSpell() {
    String inputData = "hisogram inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called histogramMethod with sourceFileName: inputName, " +
                    "destFileName: outputName");
  }

  @Test
  public void testRGBSplitSimple() {
    String inputData = "rgb-split inputName red-image-name green-image-name blue-image-name";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called rgbSplitMethod with imageName: inputName");
  }

  @Test(expected = RuntimeException.class)
  public void testRGBSplitSimpleInvalidCommandLength() {
    String inputData = "rgb-split red-image-name green-image-name blue-image-name";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called rgbSplitMethod with imageName: inputName");
  }

  @Test(expected = RuntimeException.class)
  public void testRGBSplitSimpleInvalidCommandSpell() {
    String inputData = "rgb-slit inputName red-image-name green-image-name blue-image-name";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called rgbSplitMethod with imageName: inputName");
  }

  @Test
  public void testRGBCombineSimple() {
    String inputData = "rgb-combine inputName red-image-name green-image-name blue-image-name";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called rgbCombineMethod with redImageName: red-image-name, greenImageName: " +
                    "green-image-name, blueImageName: blue-image-name");
  }

  @Test(expected = RuntimeException.class)
  public void testRGBCombineSimpleInvalidCommandLength() {
    String inputData = "rgb-combine  red-image-name green-image-name blue-image-name";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called rgbCombineMethod with redImageName: red-image-name, greenImageName: " +
                    "green-image-name, blueImageName: blue-image-name");
  }

  @Test(expected = RuntimeException.class)
  public void testRGBCombineSimpleInvalidCommandSpell() {
    String inputData = "rgb-combie inputName red-image-name green-image-name blue-image-name";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called rgbCombineMethod with redImageName: red-image-name, greenImageName: " +
                    "green-image-name, blueImageName: blue-image-name");
  }

  @Test
  public void testBrightenSimple() {
    String inputData = "brighten 50 inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called brightenMethod with brightness: 50, imageName: inputName, " +
                    "destinationName: outputName");
  }

  @Test(expected = RuntimeException.class)
  public void testBrightenSimpleInvalidCommandLength() {
    String inputData = "brighten inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called brightenMethod with brightness: 50, imageName: inputName, " +
                    "destinationName: outputName");
  }

  @Test(expected = RuntimeException.class)
  public void testBrightenSimpleInvalidCommandSpell() {
    String inputData = "brihten 50 inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called brightenMethod with brightness: 50, imageName: inputName, " +
                    "destinationName: outputName");
  }

  @Test
  public void testLevelAdjustSimple() {
    String inputData = "level-adjust 100 200 255 mh mh-c-a";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called LevelAdjust method!\n" +
                    "LevelAdjust Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testLevelAdjustSimpleInvalidCommandLength() {
    String inputData = "level-adjust 100  255 mh mh-c-a";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called LevelAdjust method!\n" +
                    "LevelAdjust Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testLevelAdjustSimpleInvalidCommandSpell() {
    String inputData = "level-ajust 100 200 255 mh mh-c-a";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called LevelAdjust method!\n" +
                    "LevelAdjust Command Image Saved");
  }

  @Test
  public void testCompressSimple() {
    String inputData = "compress 95 inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called compressMethod with imageName: inputName, destinationName: " +
                    "outputName, percentage: 95");
  }

  @Test(expected = RuntimeException.class)
  public void testCompressSimpleInvalidCommandLength() {
    String inputData = "compress  inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called compressMethod with imageName: inputName, destinationName: " +
                    "outputName, percentage: 95");
  }

  @Test(expected = RuntimeException.class)
  public void testCompressSimpleInvalidCommandSpell() {
    String inputData = "comress 95 inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    System.out.println(((MockModel) mockModel).getLog().trim());
    assertEquals(((MockModel) mockModel).getLog().trim(),
            "Called compressMethod with imageName: inputName, destinationName: outputName, "
                    + "percentage: 95");
  }


  @Test
  public void testBlurSimple() {
    String inputData = "blur inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();

    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Blur method!\n" +
                    "Blur Command Image Saved");
  }

  @Test
  public void testBlurSimpleInvalidCommandLength() {
    String inputData = "blur outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();

    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "");
  }

  @Test(expected = RuntimeException.class)
  public void testBlurSimpleInvalidCommandSpell() {
    String inputData = "blu inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();

    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Blur method!\n" +
                    "Blur Command Image Saved");
  }

  @Test
  public void testControllerRedCompoenentSimple() {
    String inputData = "red-component inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called RedImage method!\n" +
                    "RedImage Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerRedCompoenentSimpleInvalidCommandLength() {
    String inputData = "red-component outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called RedImage method!\n" +
                    "RedImage Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerRedCompoenentSimpleInvalidCommandSpell() {
    String inputData = "rd-component inputName outputName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);

    controller.start();
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called RedImage method!\n" +
                    "RedImage Command Image Saved");
  }

  //split
  @Test
  public void testControllerBlueCompoenentSplit() {
    String inputData = "blue-component inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called BlueImage method!\n" +
                    "Called BlueImagemethod along with split!\n" +
                    "BlueImage Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerBlueCompoenentSplitMispell() {
    String inputData = "blue-component inputName outputName splt 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called BlueImage method!\n" +
                    "Called BlueImagemethod along with split!\n" +
                    "BlueImage Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerBlueCompoenentSplitReplaceSplit() {
    String inputData = "blue-component inputName outputName cut 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called BlueImage method!\n" +
                    "Called BlueImagemethod along with split!\n" +
                    "BlueImage Command Image Saved");
  }

  @Test
  public void testControllerRedCompoenentSplit() {
    String inputData = "red-component inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called RedImage method!\n" +
                    "Called RedImagemethod along with split!\n" +
                    "RedImage Command Image Saved");

  }

  @Test(expected = RuntimeException.class)
  public void testControllerRedCompoenentSplitReplaceSplit() {
    String inputData = "red-component inputName outputName cut 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called RedImage method!\n" +
                    "Called RedImagemethod along with split!\n" +
                    "RedImage Command Image Saved");

  }

  @Test(expected = RuntimeException.class)
  public void testControllerRedCompoenentSplitMispell() {
    String inputData = "red-component inputName outputName splt 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called RedImage method!\n" +
                    "Called RedImagemethod along with split!\n" +
                    "RedImage Command Image Saved");

  }

  @Test
  public void testControllerGreenCompoenentSplit() {
    String inputData = "green-component inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called GreenImage method!\n" +
                    "Called GreenImagemethod along with split!\n" +
                    "GreenImage Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerGreenCompoenentSplitMispell() {
    String inputData = "green-component inputName outputName slit 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called GreenImage method!\n" +
                    "Called GreenImagemethod along with split!\n" +
                    "GreenImage Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerGreenCompoenentSplitReplaceSplit() {
    String inputData = "green-component inputName outputName cut 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called GreenImage method!\n" +
                    "Called GreenImagemethod along with split!\n" +
                    "GreenImage Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerBlurSplitReplaceSplit() {
    String inputData = "blur inputName outputName cut 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Blur method!\n" +
                    "Called Blurmethod along with split!\n" +
                    "Blur Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerBlurSplitMispell() {
    String inputData = "blur inputName outputName spit 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Blur method!\n" +
                    "Called Blurmethod along with split!\n" +
                    "Blur Command Image Saved");
  }

  @Test
  public void testControllerBlurSplit() {
    String inputData = "blur inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Blur method!\n" +
                    "Called Blurmethod along with split!\n" +
                    "Blur Command Image Saved");
  }

  @Test
  public void testControllerSepiaSplit() {
    String inputData = "sepia inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Sepia method!\n" +
                    "Called Sepiamethod along with split!\n" +
                    "Sepia Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerSepiaSplitMispell() {
    String inputData = "sepia inputName outputName spit 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Sepia method!\n" +
                    "Called Sepiamethod along with split!\n" +
                    "Sepia Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerSepiaSplitReplaceSplit() {
    String inputData = "sepia inputName outputName cut 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Sepia method!\n" +
                    "Called Sepiamethod along with split!\n" +
                    "Sepia Command Image Saved");
  }

  @Test
  public void testControllerIntensitySplit() {
    String inputData = "intensity-component inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Intensity method!\n" +
                    "Called Intensitymethod along with split!\n" +
                    "Intensity Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerIntensitySplitMispell() {
    String inputData = "intensity-component inputName outputName spit 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Intensity method!\n" +
                    "Called Intensitymethod along with split!\n" +
                    "Intensity Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerIntensitySplitReplaceSplit() {
    String inputData = "intensity-component inputName outputName cut 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Intensity method!\n" +
                    "Called Intensitymethod along with split!\n" +
                    "Intensity Command Image Saved");
  }

  @Test
  public void testControllerSharpenSplit() {
    String inputData = "sharpen inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Sharpen method!\n" +
                    "Called Sharpenmethod along with split!\n" +
                    "Sharpen Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerSharpenSplitMispell() {
    String inputData = "sharpen inputName outputName splt 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Sharpen method!\n" +
                    "Called Sharpenmethod along with split!\n" +
                    "Sharpen Command Image Saved");
  }

  @Test(expected = RuntimeException.class)
  public void testControllerSharpenSplitReplaceSplit() {
    String inputData = "sharpen inputName outputName cut 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Sharpen method!\n" +
                    "Called Sharpenmethod along with split!\n" +
                    "Sharpen Command Image Saved");
  }

  @Test
  public void testControllerLumaSplit() {
    String inputData = "luma-component inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Luma method!\n" +
                    "Called Lumamethod along with split!\n" +
                    "Luma Command Image Saved");
  }

  @Test
  public void testControllerValueSplit() {
    String inputData = "value-component inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Value method!\n" +
                    "Called Valuemethod along with split!\n" +
                    "Value Command Image Saved");
  }

  @Test
  public void testControllerColorCorrectSplit() {
    String inputData = "color-correct inputName outputName split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Color-Correct method!\n" +
                    "Called Color-Correctmethod along with split!\n" +
                    "Color-Correct Command Image Saved");
  }

  @Test
  public void testControllerLevelAdjustSplit() {
    String inputData = "level-adjust 100 200 255 mh mh-c-a-split split 40";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called LevelAdjust method!\n" +
                    "Called LevelAdjustmethod along with split!\n" +
                    "LevelAdjust Command Image Saved");
  }

  //mask
  @Test
  public void testControllerBlueCompoenentMask() {
    String inputData = "blue-component inputName maskImageName DestinationImageName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    // Assert model interactions based on the processed commands
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called BlueImage method!\n" +
                    "Called BlueImage Command with masked Image\n" +
                    "BlueImage Command Image Saved");
  }

  @Test
  public void testControllerRedCompoenentMask() {
    String inputData = "red-component inputName maskImageName DestinationImageName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called RedImage method!\n" +
                    "Called RedImage Command with masked Image\n" +
                    "RedImage Command Image Saved");
  }

  @Test
  public void testControllerGreenCompoenentMask() {
    String inputData = "green-component inputName maskImageName DestinationImageName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called GreenImage method!\n" +
                    "Called GreenImage Command with masked Image\n" +
                    "GreenImage Command Image Saved");
  }

  @Test
  public void testControllerBlurComponentMask() {
    String inputData = "blur inputName maskImageName DestinationImageName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Blur method!\n" +
                    "Called Blur Command with masked Image\n" +
                    "Blur Command Image Saved");
  }

  @Test
  public void testControllerSepiaComponentMask() {
    String inputData = "sepia inputName maskImageName DestinationImageName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Sepia method!\n" +
                    "Called Sepia Command with masked Image\n" +
                    "Sepia Command Image Saved");
  }

  @Test
  public void testControllerIntensityCompoenentMask() {
    String inputData = "intensity-component inputName maskImageName DestinationImageName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Intensity method!\n" +
                    "Called Intensity Command with masked Image\n" +
                    "Intensity Command Image Saved");
  }

  @Test
  public void testControllerValueCompoenentMask() {
    String inputData = "value-component inputName maskImageName DestinationImageName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Value method!\n" +
                    "Called Value Command with masked Image\n" +
                    "Value Command Image Saved");
  }

  @Test
  public void testControllerLumaCompoenentMask() {
    String inputData = "luma-component inputName maskImageName DestinationImageName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Luma method!\n" +
                    "Called Luma Command with masked Image\n" +
                    "Luma Command Image Saved");
  }

  @Test
  public void testControllerSharpenMask() {
    String inputData = "sharpen inputName maskImageName DestinationImageName";
    IImageModel mockModel = new MockModel();
    ImageProcessorController controller = new ImageProcessorController(new StringReader(inputData),
            mockModel);
    controller.start(); // Test the start method
    String actualLog = ((MockModel) mockModel).getLog().trim();
    System.out.println(actualLog);
    assertEquals(actualLog,
            "Called Sharpen method!\n" +
                    "Called Sharpen Command with masked Image\n" +
                    "Sharpen Command Image Saved");
  }


}
