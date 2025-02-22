import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.database.CustomImage;
import model.database.ICustomImage;
import model.database.ICustomImage2;
import model.database.ICustomImage3;
import model.database.Image;
import model.database.CustomImage3;
import model.database.CustomImage2;

import org.junit.Before;
import org.junit.Test;

import utils.ImageComparisonUtils;

/**
 * This the test class on a dummy 5 x 5 and 3 x 3 matrix.
 */
public class TestModelLogic {

  private Image originalImage;
  private ICustomImage customImage;
  private ICustomImage2 customImage2;
  private ICustomImage3 customImage3;
  private Image bwImage;
  private Image bwImage2;

  /**
   * This is to setup the original matrix.
   *
   * @throws IOException if input or output exception occurs
   */
  @Before
  public void setUp() throws IOException {

    int[][][] pixelMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {10, 20, 30}, {40, 50, 60}, {70, 80, 90}},
            {{15, 25, 35}, {45, 55, 65}, {75, 85, 95}, {105, 115, 125}, {135, 145, 155}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}}
    };
    int[][][] greyPixelMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 0, 0}},
            {{1, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 0, 0}, {1, 1, 1}},
            {{0, 0, 0}, {1, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 0, 0}},
            {{1, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 0, 0}, {1, 1, 1}},
            {{0, 0, 0}, {1, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 0, 0}}
    };
    int[][][] invalidGreyPixelMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 0, 0}},
            {{1, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 0, 0}, {1, 1, 1}},
            {{0, 0, 0}, {1, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 0, 0}}
    };

    originalImage = new Image(5, 5);
    bwImage = new Image(5, 5);
    bwImage2 = new Image(3, 5);

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = pixelMatrix[row][col];
        int[] rgb1 = greyPixelMatrix[row][col];
        originalImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);
        bwImage.setPixel(row, col, rgb1[0], rgb1[1], rgb1[2]);
      }
    }

    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb2 = invalidGreyPixelMatrix[row][col];
        bwImage2.setPixel(row, col, rgb2[0], rgb2[1], rgb2[2]);
      }
    }

    customImage = new CustomImage(originalImage);
    customImage2 = new CustomImage2(originalImage);
    customImage3 = new CustomImage3(originalImage);
  }

  @Test
  public void testBrighten() throws IOException {

    Image brightenedImage = customImage.brighten(30, originalImage);

    int[][][] expectedPixelMatrix = {
            {{30, 30, 30}, {31, 31, 31}, {40, 50, 60}, {70, 80, 90}, {100, 110, 120}},
            {{45, 55, 65}, {75, 85, 95}, {105, 115, 125}, {135, 145, 155}, {165, 175, 185}},
            {{50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}, {170, 180, 190}},
            {{55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}, {175, 185, 195}},
            {{60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}, {180, 190, 200}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedPixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(brightenedImage, expectedImage));
  }

  @Test
  public void testBrightenDarker()
          throws IOException {
    Image brightenedImage = customImage.brighten(30, originalImage);
    Image brightenedImage1 = customImage.brighten(-30, brightenedImage);
    assertTrue(ImageComparisonUtils.equals(brightenedImage1, originalImage));
  }

  @Test
  public void testBrighten0() throws IOException {
    Image brightenedImage = customImage.brighten(0, originalImage);
    assertTrue(ImageComparisonUtils.equals(brightenedImage, originalImage));
  }

  @Test
  public void testDarker() throws IOException {

    Image darkenedImage = customImage.brighten(-30, originalImage);
    int[][][] expectedPixelMatrix = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {10, 20, 30}, {40, 50, 60}},
            {{0, 0, 5}, {15, 25, 35}, {45, 55, 65}, {75, 85, 95}, {105, 115, 125}},
            {{0, 0, 10}, {20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}},
            {{0, 5, 15}, {25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}},
            {{0, 10, 20}, {30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedPixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(darkenedImage, expectedImage));
  }

  @Test
  public void testRedComponent() throws IOException {
    // Step 3: Apply the operation for the red component
    Image redImage = customImage.getRedImage(originalImage); // Extracting the red component

    // Step 4: Create the expected red image based on the base pixel matrix
    int[][][] expectedRedPixelMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {10, 10, 10}, {40, 40, 40}, {70, 70, 70}},
            {{15, 15, 15}, {45, 45, 45}, {75, 75, 75}, {105, 105, 105}, {135, 135, 135}},
            {{20, 20, 20}, {50, 50, 50}, {80, 80, 80}, {110, 110, 110}, {140, 140, 140}},
            {{25, 25, 25}, {55, 55, 55}, {85, 85, 85}, {115, 115, 115}, {145, 145, 145}},
            {{30, 30, 30}, {60, 60, 60}, {90, 90, 90}, {120, 120, 120}, {150, 150, 150}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedRedPixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(redImage, expectedImage));
  }

  @Test
  public void testRedComponentMask() throws IOException {

    Image redImage = customImage.getRedImage(originalImage);

    int[][][] expectedRedPixelMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {10, 10, 10}, {40, 40, 40}, {70, 70, 70}},
            {{15, 15, 15}, {45, 45, 45}, {75, 75, 75}, {105, 105, 105}, {135, 135, 135}},
            {{20, 20, 20}, {50, 50, 50}, {80, 80, 80}, {110, 110, 110}, {140, 140, 140}},
            {{25, 25, 25}, {55, 55, 55}, {85, 85, 85}, {115, 115, 115}, {145, 145, 145}},
            {{30, 30, 30}, {60, 60, 60}, {90, 90, 90}, {120, 120, 120}, {150, 150, 150}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedRedPixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);
      }
    }

    assertTrue(ImageComparisonUtils.equals(redImage, expectedImage));
  }

  @Test
  public void testGreenComponent() throws IOException {
    // Step 3: Apply the operation for the red component
    Image greenImage = customImage.getGreenImage(originalImage); // Extracting the red component

    // Step 4: Create the expected red image based on the base pixel matrix
    int[][][] expectedGreenPixelMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {20, 20, 20}, {50, 50, 50}, {80, 80, 80}},
            {{25, 25, 25}, {55, 55, 55}, {85, 85, 85}, {115, 115, 115}, {145, 145, 145}},
            {{30, 30, 30}, {60, 60, 60}, {90, 90, 90}, {120, 120, 120}, {150, 150, 150}},
            {{35, 35, 35}, {65, 65, 65}, {95, 95, 95}, {125, 125, 125}, {155, 155, 155}},
            {{40, 40, 40}, {70, 70, 70}, {100, 100, 100}, {130, 130, 130}, {160, 160, 160}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedGreenPixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(
            ImageComparisonUtils.equals(greenImage, expectedImage));
  }


  @Test
  public void testBlueComponent() throws IOException {
    // Step 3: Apply the operation for the red component
    Image blueImg = customImage.getBlueImage(originalImage); // Extracting the red component

    // Step 4: Create the expected red image based on the base pixel matrix
    int[][][] expectedBluePixelMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {30, 30, 30}, {60, 60, 60}, {90, 90, 90}},
            {{35, 35, 35}, {65, 65, 65}, {95, 95, 95}, {125, 125, 125}, {155, 155, 155}},
            {{40, 40, 40}, {70, 70, 70}, {100, 100, 100}, {130, 130, 130}, {160, 160, 160}},
            {{45, 45, 45}, {75, 75, 75}, {105, 105, 105}, {135, 135, 135}, {165, 165, 165}},
            {{50, 50, 50}, {80, 80, 80}, {110, 110, 110}, {140, 140, 140}, {170, 170, 170}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedBluePixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(blueImg, expectedImage));
  }


  @Test
  public void testHorizontalFlip() throws IOException {
    // Step 3: Apply the operation for the red component
    Image horizontalImg = customImage.fliph(originalImage); // Extracting the red component

    int[][][] expectedorizontalFlipPixelMatrix = {
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{15, 25, 35}, {45, 55, 65}, {75, 85, 95}, {105, 115, 125}, {135, 145, 155}},
            {{0, 0, 0}, {1, 1, 1}, {10, 20, 30}, {40, 50, 60}, {70, 80, 90}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedorizontalFlipPixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedorizontalFlipPixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue("Red component brightened image does not match the expected output.",
            ImageComparisonUtils.equals(horizontalImg, expectedImage));
  }


  @Test
  public void testVerticalFlip() throws IOException {
    // Step 3: Apply the operation for the red component
    Image verticalImg = customImage.flipv(originalImage); // Extracting the red component

    int[][][] expectedVerticalFlipPixelMatrix = {

            {{70, 80, 90}, {40, 50, 60}, {10, 20, 30}, {1, 1, 1}, {0, 0, 0}},
            {{135, 145, 155}, {105, 115, 125}, {75, 85, 95}, {45, 55, 65}, {15, 25, 35}},
            {{140, 150, 160}, {110, 120, 130}, {80, 90, 100}, {50, 60, 70}, {20, 30, 40}},
            {{145, 155, 165}, {115, 125, 135}, {85, 95, 105}, {55, 65, 75}, {25, 35, 45}},
            {{150, 160, 170}, {120, 130, 140}, {90, 100, 110}, {60, 70, 80}, {30, 40, 50}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVerticalFlipPixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVerticalFlipPixelMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(verticalImg, expectedImage));
  }

  @Test
  public void testVerticalVerticalFlip() throws IOException {
    Image verticalImg = customImage.flipv(originalImage); // Extracting the red component
    Image verticalImg1 = customImage.flipv(verticalImg);
    assertTrue(ImageComparisonUtils.equals(verticalImg1, originalImage));
  }


  @Test
  public void testLuma() throws IOException {
    Image lumaImg = customImage.calculateLuma(originalImage); // Extracting the red component
    int[][][] expectedLumaMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {18, 18, 18}, {48, 48, 48}, {78, 78, 78}},
            {{23, 23, 23}, {53, 53, 53}, {83, 83, 83}, {113, 113, 113}, {143, 143, 143}},
            {{28, 28, 28}, {58, 58, 58}, {88, 88, 88}, {118, 118, 118}, {148, 148, 148}},
            {{33, 33, 33}, {63, 63, 63}, {93, 93, 93}, {123, 123, 123}, {153, 153, 153}},
            {{38, 38, 38}, {68, 68, 68}, {98, 98, 98}, {128, 128, 128}, {158, 158, 158}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedLumaMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedLumaMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);
      }
    }

    assertTrue(ImageComparisonUtils.equals(lumaImg, expectedImage));
  }

  @Test
  public void testLumaSplit40() throws IOException {
    Image lumaImg = customImage.calculateLuma(originalImage);
    Image finalLuma = customImage2.splitImg(lumaImg, originalImage, "40");
    int[][][] expectedLumaMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {18, 18, 18}, {48, 48, 48}, {78, 78, 78}},
            {{23, 23, 23}, {53, 53, 53}, {83, 83, 83}, {113, 113, 113}, {143, 143, 143}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}}
    };

    Image expectedImage = new Image(5, 5);

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedLumaMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(finalLuma, expectedImage));
  }


  @Test
  public void testIntensity() throws IOException {
    // Step 3: Apply the operation for the red component
    Image intensityImg = customImage.calculateIntensity(
            originalImage); // Extracting the red component

    int[][][] expectedIntensityMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {20, 20, 20}, {50, 50, 50}, {80, 80, 80}},
            {{25, 25, 25}, {55, 55, 55}, {85, 85, 85}, {115, 115, 115}, {145, 145, 145}},
            {{30, 30, 30}, {60, 60, 60}, {90, 90, 90}, {120, 120, 120}, {150, 150, 150}},
            {{35, 35, 35}, {65, 65, 65}, {95, 95, 95}, {125, 125, 125}, {155, 155, 155}},
            {{40, 40, 40}, {70, 70, 70}, {100, 100, 100}, {130, 130, 130}, {160, 160, 160}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedIntensityMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedIntensityMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(intensityImg, expectedImage));
  }

  @Test
  public void testIntensitySplit40() throws IOException {
    // Step 3: Apply the operation for the red component
    Image intensityImg = customImage.calculateIntensity(
            originalImage); // Extracting the red component
    Image finalIntensity = customImage2.splitImg(intensityImg, originalImage, "40");
    int[][][] expectedIntensityMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {20, 20, 20}, {50, 50, 50}, {80, 80, 80}},
            {{25, 25, 25}, {55, 55, 55}, {85, 85, 85}, {115, 115, 115}, {145, 145, 145}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedIntensityMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedIntensityMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(finalIntensity, expectedImage));
  }


  @Test
  public void testValue() throws IOException {

    Image valueImg = customImage.calculateValue(originalImage);
    int[][][] expectedValueMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {30, 30, 30}, {60, 60, 60}, {90, 90, 90}},
            {{35, 35, 35}, {65, 65, 65}, {95, 95, 95}, {125, 125, 125}, {155, 155, 155}},
            {{40, 40, 40}, {70, 70, 70}, {100, 100, 100}, {130, 130, 130}, {160, 160, 160}},
            {{45, 45, 45}, {75, 75, 75}, {105, 105, 105}, {135, 135, 135}, {165, 165, 165}},
            {{50, 50, 50}, {80, 80, 80}, {110, 110, 110}, {140, 140, 140}, {170, 170, 170}}};

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedValueMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedValueMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(valueImg, expectedImage));
  }

  @Test
  public void testValueSplit40() throws IOException {
    // Step 3: Apply the operation for the red component
    Image valueImg = customImage.calculateValue(originalImage); // Extracting the red component
    Image finalValueImg = customImage2.splitImg(valueImg, originalImage, "40");
    int[][][] expectedValueMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {30, 30, 30}, {60, 60, 60}, {90, 90, 90}},
            {{35, 35, 35}, {65, 65, 65}, {95, 95, 95}, {125, 125, 125}, {155, 155, 155}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedValueMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedValueMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(finalValueImg, expectedImage));
  }

  @Test
  public void testSharpen() throws IOException {
    // Step 3: Apply the operation for the red component
    Image sharpenImg = customImage.sharpen(originalImage); // Extracting the red component

    int[][][] expectedSharpenMatrix = {
            {{0, 0, 0}, {0, 0, 0}, {0, 9, 19}, {85, 101, 118}, {88, 99, 110}},
            {{2, 12, 22}, {40, 57, 73}, {84, 99, 114}, {208, 230, 253}, {187, 202, 217}},
            {{13, 27, 41}, {64, 83, 102}, {108, 121, 133}, {243, 255, 255}, {202, 213, 225}},
            {{30, 45, 60}, {91, 113, 134}, {144, 160, 176}, {255, 255, 255}, {232, 247, 255}},
            {{24, 35, 46}, {69, 84, 99}, {105, 116, 127}, {204, 219, 234}, {181, 193, 204}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedSharpenMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedSharpenMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(sharpenImg, expectedImage));
  }

  @Test
  public void testSharpenSplit40() throws IOException {
    // Step 3: Apply the operation for the red component
    Image sharpenImg = customImage.sharpen(originalImage); // Extracting the red component
    Image finalSharpenImg = customImage2.splitImg(sharpenImg, originalImage, "40");
    int[][][] expectedSharpenMatrix = {
            {{0, 0, 0}, {0, 0, 0}, {0, 9, 19}, {85, 101, 118}, {88, 99, 110}},
            {{2, 12, 22}, {40, 57, 73}, {84, 99, 114}, {208, 230, 253}, {187, 202, 217}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}},
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedSharpenMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedSharpenMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(finalSharpenImg, expectedImage));
  }

  @Test
  public void testBlur() throws IOException {
    // Step 3: Apply the operation for the red component
    Image blurImg = customImage.blur(originalImage); // Extracting the red component

    int[][][] expectedBlurMatrix = {
            {{4, 6, 8}, {12, 16, 20}, {26, 32, 38}, {46, 53, 61}, {45, 51, 57}},
            {{15, 20, 26}, {35, 43, 52}, {61, 70, 80}, {90, 100, 110}, {82, 90, 97}},
            {{22, 30, 37}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {97, 105, 112}},
            {{26, 33, 41}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {101, 108, 116}},
            {{21, 27, 32}, {43, 51, 58}, {66, 73, 81}, {88, 96, 103}, {77, 83, 89}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedBlurMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedBlurMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(blurImg, expectedImage));
  }

  @Test
  public void testBlurSplit40() throws IOException {
    // Step 3: Apply the operation for the red component
    Image blurImg = customImage.blur(originalImage); // Extracting the red component
    Image finalBlurImg = customImage2.splitImg(blurImg, originalImage, "40");
    int[][][] expectedBlurMatrix = {
            {{4, 6, 8}, {12, 16, 20}, {26, 32, 38}, {46, 53, 61}, {45, 51, 57}},
            {{15, 20, 26}, {35, 43, 52}, {61, 70, 80}, {90, 100, 110}, {82, 90, 97}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedBlurMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedBlurMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(finalBlurImg, expectedImage));
  }

  @Test
  public void testSepia() throws IOException {
    // Step 3: Apply the operation for the red component
    Image sepiaImg = customImage.sepia(originalImage); // Extracting the red component

    int[][][] expectedSepiaMatrix = {
            {{0, 0, 0}, {1, 1, 0}, {24, 22, 17}, {65, 58, 45}, {106, 94, 73}},
            {{31, 28, 22}, {72, 64, 50}, {112, 100, 78}, {153, 136, 106}, {193, 172, 134}},
            {{38, 34, 26}, {79, 70, 54}, {119, 106, 82}, {160, 142, 111}, {200, 178, 139}},
            {{45, 40, 31}, {85, 76, 59}, {126, 112, 87}, {166, 148, 115}, {207, 184, 143}},
            {{52, 46, 36}, {92, 82, 64}, {133, 118, 92}, {173, 154, 120}, {214, 190, 148}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedSepiaMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedSepiaMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Store the expected image in ImageManager for comparison

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(sepiaImg, expectedImage));
  }

  @Test
  public void testSepiaSplit40() throws IOException {
    // Step 3: Apply the operation for the red component
    Image sepiaImg = customImage.sepia(originalImage); // Extracting the red component
    Image finalSepiaImg = customImage2.splitImg(sepiaImg, originalImage, "40");
    int[][][] expectedSepiaMatrix = {
            {{0, 0, 0}, {1, 1, 0}, {24, 22, 17}, {65, 58, 45}, {106, 94, 73}},
            {{31, 28, 22}, {72, 64, 50}, {112, 100, 78}, {153, 136, 106}, {193, 172, 134}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}}

    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedSepiaMatrix[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    // Store the expected image in ImageManager for comparison

    // Step 6: Use the equals method to compare the actual and expected images
    assertTrue(ImageComparisonUtils.equals(finalSepiaImg, expectedImage));
  }

  @Test
  public void testRGBSplit() throws IOException {
    // Step 3: Apply the operation for the red component

    Image[] arr = customImage.rgbsplit(originalImage);
    Image redCalculated = arr[0];
    Image blueCalculated = arr[1];
    Image greenCalculated = arr[2];

    int[][][] expectedRedMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {10, 10, 10}, {40, 40, 40}, {70, 70, 70}},
            {{15, 15, 15}, {45, 45, 45}, {75, 75, 75}, {105, 105, 105}, {135, 135, 135}},
            {{20, 20, 20}, {50, 50, 50}, {80, 80, 80}, {110, 110, 110}, {140, 140, 140}},
            {{25, 25, 25}, {55, 55, 55}, {85, 85, 85}, {115, 115, 115}, {145, 145, 145}},
            {{30, 30, 30}, {60, 60, 60}, {90, 90, 90}, {120, 120, 120}, {150, 150, 150}}
    };

    int[][][] expectedBlueMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {20, 20, 20}, {50, 50, 50}, {80, 80, 80}},
            {{25, 25, 25}, {55, 55, 55}, {85, 85, 85}, {115, 115, 115}, {145, 145, 145}},
            {{30, 30, 30}, {60, 60, 60}, {90, 90, 90}, {120, 120, 120}, {150, 150, 150}},
            {{35, 35, 35}, {65, 65, 65}, {95, 95, 95}, {125, 125, 125}, {155, 155, 155}},
            {{40, 40, 40}, {70, 70, 70}, {100, 100, 100}, {130, 130, 130}, {160, 160, 160}}
    };

    int[][][] expectedGreenMatrix = {
            {{0, 0, 0}, {1, 1, 1}, {30, 30, 30}, {60, 60, 60}, {90, 90, 90}},
            {{35, 35, 35}, {65, 65, 65}, {95, 95, 95}, {125, 125, 125}, {155, 155, 155}},
            {{40, 40, 40}, {70, 70, 70}, {100, 100, 100}, {130, 130, 130}, {160, 160, 160}},
            {{45, 45, 45}, {75, 75, 75}, {105, 105, 105}, {135, 135, 135}, {165, 165, 165}},
            {{50, 50, 50}, {80, 80, 80}, {110, 110, 110}, {140, 140, 140}, {170, 170, 170}}

    };

    Image redExpImag = new Image(5, 5);
    Image greenExpImag = new Image(5, 5);
    Image blueExpImag = new Image(5, 5);

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedRedMatrix[row][col];
        redExpImag.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedBlueMatrix[row][col];
        blueExpImag.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedGreenMatrix[row][col];
        greenExpImag.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assert redCalculated != null;
    assertTrue(ImageComparisonUtils.equals(redCalculated, redExpImag));
    assert greenCalculated != null;
    assertTrue(ImageComparisonUtils.equals(greenCalculated, greenExpImag));
    assert blueCalculated != null;
    assertTrue(ImageComparisonUtils.equals(blueCalculated, blueExpImag));
  }


  @Test
  public void testSepia2() throws IOException {
    Image sepiaImg = customImage.sepia(originalImage);
    Image sepiaImg2 = customImage.sepia(sepiaImg);// Extracting the red component

    int[][][] expectedSepia = {
            {{0, 0, 0}, {1, 1, 0}, {29, 26, 20}, {78, 70, 54}, {127, 113, 88}},
            {{37, 33, 26}, {86, 77, 60}, {135, 120, 94}, {184, 164, 128}, {233, 207, 161}},
            {{45, 40, 31}, {95, 84, 65}, {143, 128, 99}, {193, 171, 133}, {241, 215, 167}},
            {{54, 48, 37}, {103, 91, 71}, {152, 135, 105}, {200, 178, 139}, {249, 222, 173}},
            {{62, 55, 43}, {111, 99, 77}, {160, 142, 111}, {209, 186, 145}, {255, 229, 179}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedSepia[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedSepia[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(sepiaImg2, expectedImage));
  }


  @Test
  public void testHflipVflip() throws IOException {
    Image hFlipImage = customImage.fliph(originalImage);
    Image vFlipImage = customImage.flipv(hFlipImage);// Extracting the red component

    int[][][] expectedHflVfl = {
            {{150, 160, 170}, {120, 130, 140}, {90, 100, 110}, {60, 70, 80}, {30, 40, 50}},
            {{145, 155, 165}, {115, 125, 135}, {85, 95, 105}, {55, 65, 75}, {25, 35, 45}},
            {{140, 150, 160}, {110, 120, 130}, {80, 90, 100}, {50, 60, 70}, {20, 30, 40}},
            {{135, 145, 155}, {105, 115, 125}, {75, 85, 95}, {45, 55, 65}, {15, 25, 35}},
            {{70, 80, 90}, {40, 50, 60}, {10, 20, 30}, {1, 1, 1}, {0, 0, 0}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedHflVfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedHflVfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(vFlipImage, expectedImage));
  }

  @Test
  public void testVflipHflip() throws IOException {

    Image vFlipImage = customImage.flipv(originalImage);
    Image hFlipImage = customImage.fliph(vFlipImage);

    int[][][] expectedVflHfl = {
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{15, 25, 35}, {45, 55, 65}, {75, 85, 95}, {105, 115, 125}, {135, 145, 155}},
            {{0, 0, 0}, {1, 1, 1}, {10, 20, 30}, {40, 50, 60}, {70, 80, 90}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }
    assertTrue(ImageComparisonUtils.equals(hFlipImage, expectedImage));
  }

  @Test
  public void testColorCorrectSplit40() throws IOException {

    Image ccImage = customImage2.colorCorrectionCommand(originalImage);
    Image ccFinalImage = customImage2.splitImg(ccImage, originalImage, "40");

    int[][][] expectedVflHfl = {
            {{10, 0, 0}, {11, 1, 0}, {20, 20, 20}, {50, 50, 50}, {80, 80, 80}},
            {{25, 25, 25}, {55, 55, 55}, {85, 85, 85}, {115, 115, 115}, {145, 145, 145}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}},
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);
      }
    }
    assertTrue(ImageComparisonUtils.equals(ccFinalImage, expectedImage));
  }

  @Test
  public void testColorCorrectSimple() throws IOException {
    Image ccImage = customImage2.colorCorrectionCommand(originalImage);

    int[][][] expectedVflHfl = {
            {{10, 0, 0}, {11, 1, 0}, {20, 20, 20}, {50, 50, 50}, {80, 80, 80}},
            {{25, 25, 25}, {55, 55, 55}, {85, 85, 85}, {115, 115, 115}, {145, 145, 145}},
            {{30, 30, 30}, {60, 60, 60}, {90, 90, 90}, {120, 120, 120}, {150, 150, 150}},
            {{35, 35, 35}, {65, 65, 65}, {95, 95, 95}, {125, 125, 125}, {155, 155, 155}},
            {{40, 40, 40}, {70, 70, 70}, {100, 100, 100}, {130, 130, 130}, {160, 160, 160}},
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }
    assertTrue(ImageComparisonUtils.equals(ccImage, expectedImage));
  }

  @Test
  public void testLevelAdjustSimple() throws IOException {
    // Step 3: Apply the operation for the red component
    Image adjustImage = customImage2.adjustColorCommand(originalImage, 20, 70, 100);

    int[][][] expectedVflHfl = {
            {{-21, -21, -21}, {-21, -21, -21}, {-13, 0, 17}, {38, 64, 94}, {127, 166, 208}},
            {{-7, 8, 27}, {50, 78, 110}, {146, 186, 231}, {279, 332, 389}, {450, 516, 585}},
            {{0, 17, 38}, {64, 94, 127}, {166, 208, 254}, {305, 360, 419}, {482, 550, 621}},
            {{8, 27, 50}, {78, 110, 146}, {186, 231, 279}, {332, 389, 450}, {516, 585, 659}},
            {{17, 38, 64}, {94, 127, 166}, {208, 254, 305}, {360, 419, 482}, {550, 621, 697}},
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }
    assertTrue(ImageComparisonUtils.equals(adjustImage, expectedImage));
  }

  @Test
  public void testLevelAdjustSplit40() throws IOException {
    Image adjustImage = customImage2.adjustColorCommand(originalImage, 20, 100, 200);
    Image adjustFinalImage = customImage2.splitImg(adjustImage, originalImage, "40");
    int[][][] expectedVflHfl = {
            {{-35, -35, -35}, {-33, -33, -33}, {-17, 0, 17}, {34, 50, 66}, {82, 98, 113}},
            {{-8, 8, 25}, {42, 58, 74}, {90, 105, 120}, {135, 149, 163}, {176, 189, 202}},
            {{20, 30, 40}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {140, 150, 160}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{30, 40, 50}, {60, 70, 80}, {90, 100, 110}, {120, 130, 140}, {150, 160, 170}},
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }
    assertTrue(ImageComparisonUtils.equals(adjustFinalImage, expectedImage));
  }

  @Test
  public void testCompress() throws IOException {

    Image adjustImage = customImage2.compress(originalImage, 30);

    int[][][] expectedVflHfl = {
            {{-11, -16, -12}, {3, -1, 2}, {20, 35, 35}, {38, 53, 65}, {74, 84, 94}},
            {{17, 35, 44}, {33, 50, 59}, {85, 87, 93}, {103, 106, 123}, {139, 149, 159}},
            {{34, 43, 51}, {49, 58, 66}, {83, 94, 100}, {102, 113, 130}, {137, 147, 157}},
            {{34, 43, 51}, {49, 58, 66}, {83, 94, 100}, {102, 113, 130}, {137, 147, 157}},
            {{37, 47, 57}, {52, 62, 72}, {95, 105, 121}, {114, 124, 128}, {149, 159, 169}},
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }
    assertTrue(ImageComparisonUtils.equals(adjustImage, expectedImage));
  }

  @Test(expected = Exception.class)
  public void testCompressErrorHighValue() throws IOException {

    Image adjustImage = customImage2.compress(originalImage, 110);

    int[][][] expectedVflHfl = {
            {{-11, -16, -12}, {3, -1, 2}, {20, 35, 35}, {38, 53, 65}, {74, 84, 94}},
            {{17, 35, 44}, {33, 50, 59}, {85, 87, 93}, {103, 106, 123}, {139, 149, 159}},
            {{34, 43, 51}, {49, 58, 66}, {83, 94, 100}, {102, 113, 130}, {137, 147, 157}},
            {{34, 43, 51}, {49, 58, 66}, {83, 94, 100}, {102, 113, 130}, {137, 147, 157}},
            {{37, 47, 57}, {52, 62, 72}, {95, 105, 121}, {114, 124, 128}, {149, 159, 169}},
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }
    assertTrue(ImageComparisonUtils.equals(adjustImage, expectedImage));
  }

  @Test(expected = Exception.class)
  public void testCompressErrorLowValue() throws IOException {

    Image adjustImage = customImage2.compress(originalImage, -100);

    int[][][] expectedVflHfl = {
            {{-11, -16, -12}, {3, -1, 2}, {20, 35, 35}, {38, 53, 65}, {74, 84, 94}},
            {{17, 35, 44}, {33, 50, 59}, {85, 87, 93}, {103, 106, 123}, {139, 149, 159}},
            {{34, 43, 51}, {49, 58, 66}, {83, 94, 100}, {102, 113, 130}, {137, 147, 157}},
            {{34, 43, 51}, {49, 58, 66}, {83, 94, 100}, {102, 113, 130}, {137, 147, 157}},
            {{37, 47, 57}, {52, 62, 72}, {95, 105, 121}, {114, 124, 128}, {149, 159, 169}},
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }
    assertTrue(ImageComparisonUtils.equals(adjustImage, expectedImage));
  }

  @Test
  public void testDownScalling() throws IOException {
    // Step 3: Apply the operation for the red component
    Image downScaleImage = customImage3.downScale(originalImage, 4, 2);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {3, 5, 8}, {25, 35, 45}, {62, 72, 82}},
            {{22, 32, 42}, {60, 70, 80}, {97, 107, 117}, {135, 145, 155}}
    };

    Image expectedImage = new Image(2, 4);
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 4; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(downScaleImage, expectedImage));
  }

  @Test
  public void testDownScallingSameHeightSameWidth() throws IOException {

    Image downScaleImage = customImage3.downScale(originalImage, 2, 2);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {25, 35, 45}},
            {{22, 32, 42}, {97, 107, 117}}
    };

    Image expectedImage = new Image(2, 2);
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 2; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(downScaleImage, expectedImage));
  }

  @Test(expected = Exception.class)
  public void testDownScallingInputNegative() throws IOException {
    // Step 3: Apply the operation for the red component
    Image downScaleImage = customImage3.downScale(originalImage, -1, 01);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {3, 5, 8}, {25, 35, 45}, {62, 72, 82}},
            {{22, 32, 42}, {60, 70, 80}, {97, 107, 117}, {135, 145, 155}}
    };

    Image expectedImage = new Image(2, 4);
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 4; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(downScaleImage, expectedImage));
  }


  @Test(expected = Exception.class)
  public void testDownScallingInput0() throws IOException {
    // Step 3: Apply the operation for the red component
    Image downScaleImage = customImage3.downScale(originalImage, 0, 0);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {3, 5, 8}, {25, 35, 45}, {62, 72, 82}},
            {{22, 32, 42}, {60, 70, 80}, {97, 107, 117}, {135, 145, 155}}
    };

    Image expectedImage = new Image(2, 4);
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 4; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(downScaleImage, expectedImage));
  }

  @Test
  public void maskBlurImage() throws IOException {
    Image blurOutput = customImage.blur(originalImage);
    Image maskOutput = customImage3.maskImg(blurOutput, bwImage, originalImage);

    int[][][] expectedVflHfl = {
            {{4, 6, 8}, {1, 1, 1}, {26, 32, 38}, {40, 50, 60}, {45, 51, 57}},
            {{15, 25, 35}, {35, 43, 52}, {75, 85, 95}, {90, 100, 110}, {135, 145, 155}},
            {{22, 30, 37}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {97, 105, 112}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{21, 27, 32}, {60, 70, 80}, {66, 73, 81}, {120, 130, 140}, {77, 83, 89}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));

  }

  @Test(expected = Exception.class)
  public void maskBlurImageInvalidMaskImage() throws IOException {
    Image blurOutput = customImage.blur(originalImage);
    Image maskOutput = customImage3.maskImg(blurOutput, bwImage2, originalImage);

    int[][][] expectedVflHfl = {
            {{4, 6, 8}, {1, 1, 1}, {26, 32, 38}, {40, 50, 60}, {45, 51, 57}},
            {{15, 25, 35}, {35, 43, 52}, {75, 85, 95}, {90, 100, 110}, {135, 145, 155}},
            {{22, 30, 37}, {50, 60, 70}, {80, 90, 100}, {110, 120, 130}, {97, 105, 112}},
            {{25, 35, 45}, {55, 65, 75}, {85, 95, 105}, {115, 125, 135}, {145, 155, 165}},
            {{21, 27, 32}, {60, 70, 80}, {66, 73, 81}, {120, 130, 140}, {77, 83, 89}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));

  }

  @Test
  public void maskSepiaImage() throws IOException {
    Image sepiaOutput = customImage.sepia(originalImage);
    Image maskOutput = customImage3.maskImg(sepiaOutput, bwImage, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {24, 22, 17,}, {40, 50, 60}, {106, 94, 73}},
            {{15, 25, 35}, {72, 64, 50}, {75, 85, 95}, {153, 136, 106}, {135, 145, 155}},
            {{38, 34, 26}, {50, 60, 70}, {119, 106, 82}, {110, 120, 130}, {200, 178, 139}},
            {{25, 35, 45}, {85, 76, 59}, {85, 95, 105}, {166, 148, 115}, {145, 155, 165}},
            {{52, 46, 36}, {60, 70, 80}, {133, 118, 92}, {120, 130, 140}, {214, 190, 148}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));

  }

  @Test(expected = Exception.class)
  public void maskSepiaImageInvalidImage() throws IOException {
    Image sepiaOutput = customImage.sepia(originalImage);
    Image maskOutput = customImage3.maskImg(sepiaOutput, bwImage2, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {24, 22, 17,}, {40, 50, 60}, {106, 94, 73}},
            {{15, 25, 35}, {72, 64, 50}, {75, 85, 95}, {153, 136, 106}, {135, 145, 155}},
            {{38, 34, 26}, {50, 60, 70}, {119, 106, 82}, {110, 120, 130}, {200, 178, 139}},
            {{25, 35, 45}, {85, 76, 59}, {85, 95, 105}, {166, 148, 115}, {145, 155, 165}},
            {{52, 46, 36}, {60, 70, 80}, {133, 118, 92}, {120, 130, 140}, {214, 190, 148}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));

  }

  @Test
  public void maskSharpenImage() throws IOException {
    Image sharpenOutput = customImage.sharpen(originalImage);
    Image maskOutput = customImage3.maskImg(sharpenOutput, bwImage, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {0, 9, 19}, {40, 50, 60}, {88, 99, 110}},
            {{15, 25, 35}, {40, 57, 73}, {75, 85, 95}, {208, 230, 253}, {135, 145, 155}},
            {{13, 27, 41}, {50, 60, 70}, {108, 121, 133}, {110, 120, 130}, {202, 213, 225}},
            {{25, 35, 45}, {91, 113, 134}, {85, 95, 105}, {255, 255, 255}, {145, 155, 165}},
            {{24, 35, 46}, {60, 70, 80}, {105, 116, 127}, {120, 130, 140}, {181, 193, 204}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }

  @Test(expected = Exception.class)
  public void maskSharpenImageInvalidImage() throws IOException {
    Image sharpenOutput = customImage.sharpen(originalImage);
    Image maskOutput = customImage3.maskImg(sharpenOutput, bwImage2, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {0, 9, 19}, {40, 50, 60}, {88, 99, 110}},
            {{15, 25, 35}, {40, 57, 73}, {75, 85, 95}, {208, 230, 253}, {135, 145, 155}},
            {{13, 27, 41}, {50, 60, 70}, {108, 121, 133}, {110, 120, 130}, {202, 213, 225}},
            {{25, 35, 45}, {91, 113, 134}, {85, 95, 105}, {255, 255, 255}, {145, 155, 165}},
            {{24, 35, 46}, {60, 70, 80}, {105, 116, 127}, {120, 130, 140}, {181, 193, 204}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }


  @Test
  public void maskRedImage() throws IOException {
    Image redImage = customImage.getRedImage(originalImage);
    Image maskOutput = customImage3.maskImg(redImage, bwImage, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {10, 10, 10}, {40, 50, 60}, {70, 70, 70}},
            {{15, 25, 35}, {45, 45, 45}, {75, 85, 95}, {105, 105, 105}, {135, 145, 155}},
            {{20, 20, 20}, {50, 60, 70}, {80, 80, 80}, {110, 120, 130}, {140, 140, 140}},
            {{25, 35, 45}, {55, 55, 55}, {85, 95, 105}, {115, 115, 115}, {145, 155, 165}},
            {{30, 30, 30}, {60, 70, 80}, {90, 90, 90}, {120, 130, 140}, {150, 150, 150}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }

  @Test(expected = Exception.class)
  public void maskRedImageInvalidImage() throws IOException {
    Image redImage = customImage.getRedImage(originalImage);
    Image maskOutput = customImage3.maskImg(redImage, bwImage2, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {10, 10, 10}, {40, 50, 60}, {70, 70, 70}},
            {{15, 25, 35}, {45, 45, 45}, {75, 85, 95}, {105, 105, 105}, {135, 145, 155}},
            {{20, 20, 20}, {50, 60, 70}, {80, 80, 80}, {110, 120, 130}, {140, 140, 140}},
            {{25, 35, 45}, {55, 55, 55}, {85, 95, 105}, {115, 115, 115}, {145, 155, 165}},
            {{30, 30, 30}, {60, 70, 80}, {90, 90, 90}, {120, 130, 140}, {150, 150, 150}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }

  @Test(expected = Exception.class)
  public void maskBlueImageInvalidImage() throws IOException {
    Image blueImage = customImage.getBlueImage(originalImage);
    Image maskOutput = customImage3.maskImg(blueImage, bwImage2, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {30, 30, 30}, {40, 50, 60}, {90, 90, 90}},
            {{15, 25, 35}, {65, 65, 65}, {75, 85, 95}, {125, 125, 125}, {135, 145, 155}},
            {{40, 40, 40}, {50, 60, 70}, {100, 100, 100}, {110, 120, 130}, {160, 160, 160}},
            {{25, 35, 45}, {75, 75, 75}, {85, 95, 105}, {135, 135, 135}, {145, 155, 165}},
            {{50, 50, 50}, {60, 70, 80}, {110, 110, 110}, {120, 130, 140}, {170, 170, 170}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }


  @Test
  public void maskGreenImage() throws IOException {
    Image greenImage = customImage.getGreenImage(originalImage);
    Image maskOutput = customImage3.maskImg(greenImage, bwImage, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {20, 20, 20}, {40, 50, 60}, {80, 80, 80}},
            {{15, 25, 35}, {55, 55, 55}, {75, 85, 95}, {115, 115, 115}, {135, 145, 155}},
            {{30, 30, 30}, {50, 60, 70}, {90, 90, 90}, {110, 120, 130}, {150, 150, 150}},
            {{25, 35, 45}, {65, 65, 65}, {85, 95, 105}, {125, 125, 125}, {145, 155, 165}},
            {{40, 40, 40}, {60, 70, 80}, {100, 100, 100}, {120, 130, 140}, {160, 160, 160}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }

  @Test(expected = Exception.class)
  public void maskGreenImageInvalidImage() throws IOException {
    Image greenImage = customImage.getGreenImage(originalImage);
    Image maskOutput = customImage3.maskImg(greenImage, bwImage2, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {20, 20, 20}, {40, 50, 60}, {80, 80, 80}},
            {{15, 25, 35}, {55, 55, 55}, {75, 85, 95}, {115, 115, 115}, {135, 145, 155}},
            {{30, 30, 30}, {50, 60, 70}, {90, 90, 90}, {110, 120, 130}, {150, 150, 150}},
            {{25, 35, 45}, {65, 65, 65}, {85, 95, 105}, {125, 125, 125}, {145, 155, 165}},
            {{40, 40, 40}, {60, 70, 80}, {100, 100, 100}, {120, 130, 140}, {160, 160, 160}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }

  @Test(expected = Exception.class)
  public void maskValueImageInvalidImage() throws IOException {
    Image valueImage = customImage.calculateValue(originalImage);
    Image maskOutput = customImage3.maskImg(valueImage, bwImage2, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {30, 30, 30}, {40, 50, 60}, {90, 90, 90}},
            {{15, 25, 35}, {65, 65, 65}, {75, 85, 95}, {125, 125, 125}, {135, 145, 155}},
            {{40, 40, 40}, {50, 60, 70}, {100, 100, 100}, {110, 120, 130}, {160, 160, 160}},
            {{25, 35, 45}, {75, 75, 75}, {85, 95, 105}, {135, 135, 135}, {145, 155, 165}},
            {{50, 50, 50}, {60, 70, 80}, {110, 110, 110}, {120, 130, 140}, {170, 170, 170}},
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }

  @Test
  public void maskIntensityImage() throws IOException {
    Image valueImage = customImage.calculateIntensity(originalImage);
    Image maskOutput = customImage3.maskImg(valueImage, bwImage, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {20, 20, 20}, {40, 50, 60}, {80, 80, 80}},
            {{15, 25, 35}, {55, 55, 55}, {75, 85, 95}, {115, 115, 115}, {135, 145, 155}},
            {{30, 30, 30}, {50, 60, 70}, {90, 90, 90}, {110, 120, 130}, {150, 150, 150}},
            {{25, 35, 45}, {65, 65, 65}, {85, 95, 105}, {125, 125, 125}, {145, 155, 165}},
            {{40, 40, 40}, {60, 70, 80}, {100, 100, 100}, {120, 130, 140}, {160, 160, 160}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }

  @Test(expected = Exception.class)
  public void maskIntensityImageInvalidImage() throws IOException {
    Image valueImage = customImage.calculateIntensity(originalImage);
    Image maskOutput = customImage3.maskImg(valueImage, bwImage2, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {20, 20, 20}, {40, 50, 60}, {80, 80, 80}},
            {{15, 25, 35}, {55, 55, 55}, {75, 85, 95}, {115, 115, 115}, {135, 145, 155}},
            {{30, 30, 30}, {50, 60, 70}, {90, 90, 90}, {110, 120, 130}, {150, 150, 150}},
            {{25, 35, 45}, {65, 65, 65}, {85, 95, 105}, {125, 125, 125}, {145, 155, 165}},
            {{40, 40, 40}, {60, 70, 80}, {100, 100, 100}, {120, 130, 140}, {160, 160, 160}}
    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }

  @Test
  public void maskLumaImage() throws IOException {
    Image valueImage = customImage.calculateLuma(originalImage);
    Image maskOutput = customImage3.maskImg(valueImage, bwImage, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {18, 18, 18}, {40, 50, 60}, {78, 78, 78}},
            {{15, 25, 35}, {53, 53, 53}, {75, 85, 95}, {113, 113, 113}, {135, 145, 155}},
            {{28, 28, 28}, {50, 60, 70}, {88, 88, 88}, {110, 120, 130}, {148, 148, 148}},
            {{25, 35, 45}, {63, 63, 63}, {85, 95, 105}, {123, 123, 123}, {145, 155, 165}},
            {{38, 38, 38}, {60, 70, 80}, {98, 98, 98}, {120, 130, 140}, {158, 158, 158}}

    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }

  @Test(expected = Exception.class)
  public void maskLumaImageInvalidImage() throws IOException {
    Image valueImage = customImage.calculateLuma(originalImage);
    Image maskOutput = customImage3.maskImg(valueImage, bwImage2, originalImage);

    int[][][] expectedVflHfl = {
            {{0, 0, 0}, {1, 1, 1}, {18, 18, 18}, {40, 50, 60}, {78, 78, 78}},
            {{15, 25, 35}, {53, 53, 53}, {75, 85, 95}, {113, 113, 113}, {135, 145, 155}},
            {{28, 28, 28}, {50, 60, 70}, {88, 88, 88}, {110, 120, 130}, {148, 148, 148}},
            {{25, 35, 45}, {63, 63, 63}, {85, 95, 105}, {123, 123, 123}, {145, 155, 165}},
            {{38, 38, 38}, {60, 70, 80}, {98, 98, 98}, {120, 130, 140}, {158, 158, 158}}

    };

    Image expectedImage = new Image(5, 5);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        int[] rgb = expectedVflHfl[row][col];
        expectedImage.setPixel(row, col, rgb[0], rgb[1], rgb[2]);  // Set expected pixel values
      }
    }

    assertTrue(ImageComparisonUtils.equals(maskOutput, expectedImage));
  }


  @Test
  public void testRGBCombine() throws FileNotFoundException {
    Image redImage = customImage.getRedImage(originalImage);
    Image blueImage = customImage.getBlueImage(originalImage);
    Image greenImage = customImage.getGreenImage(originalImage);

    Image finalImage = customImage.rgbcombine(redImage, greenImage, blueImage, "finalImg");

    assertTrue(ImageComparisonUtils.equals(finalImage, originalImage));
  }
}
