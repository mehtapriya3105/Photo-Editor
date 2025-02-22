package view;

import controller.Features;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BoxLayout;

/**
 * This class represents the JFrame-based GUI view for the image processing application. This class
 * provides the user interface to interact with the application, including loading, saving, and
 * applying image transformations and effects.
 */
public class JFrameView extends JFrame implements IView {

  private JTextField percentageTextField;
  private JFrame newViewFrame;
  private JLabel imageDisplayLabel;
  private JLabel histogramDisplayLabel;
  private JLabel imageLabel;
  private String imgName;
  private String var;
  private JPanel imageDisplayPanel;
  private JPanel histogramDisplayPanel;
  private JButton loadButton;
  private JButton saveButton;
  private JButton blurButton;
  private JButton lumaButton;
  private JButton sepiaButton;
  private JButton sharpenButton;
  private JButton hFlipButton;
  private JButton vFlipButton;
  private JButton correctButton;
  private JButton levelButton;
  private JButton redButton;
  private JButton greenButton;
  private JButton blueButton;
  private JButton darkButton;
  private JButton brightenButton;
  private JButton compressButton;
  private JScrollPane scrollPane;
  private Dimension buttonSize;
  private JButton downscaleButton;
  private JFrame levelAdjustFrame;

  /**
   * Constructs the JFrameView, initializing all GUI components and layout.
   */
  public JFrameView() {
    JPanel topPanel;
    JPanel leftPanel;

    buttonSize = new Dimension(120, 50);

    setTitle("Image Processor GUI");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setSize(800, 600);
    setLayout(new BorderLayout());

    // Add a window listener to handle the close operation
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int choice = JOptionPane.showConfirmDialog(
                JFrameView.this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
          // Exit the program
          System.exit(0);
        }
      }
    });

    leftPanel = new JPanel();

    leftPanel.setLayout(new GridLayout(15, 1));
    leftPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Functions"
    ));
    blurButton = createButton("Blur", buttonSize);
    lumaButton = createButton("Luma", buttonSize);
    sepiaButton = createButton("Sepia", buttonSize);
    sharpenButton = createButton("Sharpen", buttonSize);
    hFlipButton = createButton("Horizontal Flip", buttonSize);
    vFlipButton = createButton("Vertical Flip", buttonSize);
    correctButton = createButton("Color Correct", buttonSize);
    levelButton = createButton("Level Adjustment", buttonSize);
    redButton = createButton("Red Component", buttonSize);
    blueButton = createButton("Blue Component", buttonSize);
    greenButton = createButton("Green Component", buttonSize);
    downscaleButton = createButton("Down Scale", buttonSize);
    brightenButton = createButton("Brighten", buttonSize);
    darkButton = createButton("Darken", buttonSize);
    compressButton = createButton("Compress", buttonSize);
    leftPanel.add(blurButton);
    leftPanel.add(lumaButton);
    leftPanel.add(sepiaButton);
    leftPanel.add(sharpenButton);
    leftPanel.add(hFlipButton);
    leftPanel.add(vFlipButton);
    leftPanel.add(correctButton);
    leftPanel.add(levelButton);
    leftPanel.add(redButton);
    leftPanel.add(greenButton);
    leftPanel.add(blueButton);
    leftPanel.add(compressButton);
    leftPanel.add(downscaleButton);
    leftPanel.add(brightenButton);
    leftPanel.add(darkButton);

    topPanel = new JPanel();
    Dimension buttonSize1 = new Dimension(20, 50);
    topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    loadButton = createButton("Load", buttonSize1);
    saveButton = createButton("Save", buttonSize1);
    topPanel.add(loadButton);
    topPanel.add(saveButton);

    imageDisplayLabel = new JLabel("Image Display", SwingConstants.CENTER);
    histogramDisplayLabel = new JLabel("Histogram Display", SwingConstants.CENTER);

    imageDisplayPanel = new JPanel(new BorderLayout());
    imageDisplayLabel.setForeground(Color.BLACK);
    scrollPane = new JScrollPane(imageDisplayLabel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imageDisplayPanel.add(scrollPane, BorderLayout.CENTER);

    histogramDisplayPanel = new JPanel(new BorderLayout());
    histogramDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    histogramDisplayPanel.add(histogramDisplayLabel, BorderLayout.CENTER);

    // Create and configure the split pane to include both image and histogram panels
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, imageDisplayPanel,
            histogramDisplayPanel);
    splitPane.setDividerLocation(400);
    splitPane.setResizeWeight(0.7);

    add(leftPanel, BorderLayout.WEST);
    add(topPanel, BorderLayout.NORTH);
    add(splitPane, BorderLayout.CENTER);

    setVisible(true);
  }

  /**
   * Prompts the user for a single input value through a dialog with a text field.
   *
   * @param title          the title of the input dialog.
   * @param message        the message displayed in the dialog.
   * @param tooltipMessage the tooltip text shown in the input field.
   * @return the user's input as a string, or null if the dialog is canceled.
   */
  private static String promptForSingleInput(String title, String message, String tooltipMessage) {
    // Create a custom panel with a label and text field
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());

    JLabel label = new JLabel(message);
    JTextField textField = new JTextField(5);
    textField.setToolTipText(tooltipMessage);

    panel.add(label);
    panel.add(textField);

    // Show the custom panel in a JOptionPane
    int option = JOptionPane.showConfirmDialog(
            null,
            panel,
            title,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    // If the user clicks OK, retrieve the input
    if (option == JOptionPane.OK_OPTION) {
      return textField.getText();
    } else {
      return null; // User clicked Cancel
    }
  }

  /**
   * Creates a JButton with a specified text and predefined size.
   *
   * @param text the text to display on the button.
   * @return the created JButton with a specified text and size.
   */
  private JButton createButton(String text, Dimension buttonsize) {
    JButton button = new JButton(text);
    button.setPreferredSize(buttonSize);
    return button;
  }

  /**
   * Displays a success message in a popup dialog.
   *
   * @param action the action that was successfully performed, used in the message.
   */
  private void successMessage(String action) {
    JOptionPane.showMessageDialog(
            this,
            action + " performed successfully.",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
    );
  }

  /**
   * Opens a file chooser dialog to allow the user to select an image file to upload. Validates the
   * file type and displays the selected image in the GUI.
   *
   * @return the absolute path of the selected image file, or null if no file is selected.
   */
  @Override
  public String uploadImagePath() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image Files (JPG, JPEG, PNG, PPM)", "jpg", "jpeg", "png", "ppm");
    fileChooser.setFileFilter(filter);
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      try {
        BufferedImage image = ImageIO.read(selectedFile);
        displayImg(selectedFile.getName(), image);
        return selectedFile.getAbsolutePath();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error loading image!",
                "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    return null;
  }

  /**
   * Opens a file chooser dialog to save the current image to a file.
   *
   * @return absolute path where image should be saved, or null if the user cancels operation.
   */
  @Override
  public String saveImagePath() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Image");

    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image Files (JPG, JPEG, PNG, PPM)", "jpg", "jpeg", "png", "ppm");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();

      String filePath = fileToSave.getAbsolutePath();
      if (!filePath.matches(".*\\.(jpg|jpeg|png|ppm)$")) {
        filePath += ".png";
      }

      return filePath;
    }

    return null; // Return null if user cancels
  }

  /**
   * Displays the provided image in the GUI.
   *
   * @param imageName the name of the image file being displayed.
   * @param img       the BufferedImage to display.
   */
  @Override
  public void displayImg(String imageName, BufferedImage img) {
    this.imgName = imageName;
    ImageIcon imageIcon = new ImageIcon(img);
    imageDisplayLabel.setIcon(imageIcon);
    imageDisplayLabel.setText("");
    scrollPane.setViewportView(imageDisplayLabel);
    imageDisplayPanel.revalidate();
    imageDisplayPanel.repaint();
  }

  /**
   * Displays a histogram of the provided image in the GUI.
   *
   * @param image the BufferedImage representing the histogram.
   */
  @Override
  public void displayHisto(BufferedImage image) {
    JLabel histogram = new JLabel(new ImageIcon(image));
    histogramDisplayPanel.remove(histogramDisplayLabel);
    histogramDisplayPanel.removeAll();
    histogramDisplayPanel.add(histogram, BorderLayout.CENTER);
    histogramDisplayPanel.revalidate();
    histogramDisplayPanel.repaint();
  }

  /**
   * Adds feature handlers to the GUI buttons, linking them to the provided features.
   *
   * @param features the Features object that defines the operations to be performed.
   */
  @Override
  public void addFeatures(Features features) {

    loadButton.addActionListener(evt -> {
      try {
        features.loadImage();
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });

    addFeatureWithPopup(blurButton, features, "blur");

    addFeatureWithPopup(lumaButton, features, "luma");

    addFeatureWithPopup(sepiaButton, features, "sepia");

    addFeatureWithPopup(sharpenButton, features, "sharpen");

    addFeatureWithPopup(correctButton, features, "color-correct");

    addFeatureWithPopup(redButton, features, "red-component");

    addFeatureWithPopup(greenButton, features, "green-component");

    addFeatureWithPopup(blueButton, features, "blue-component");

    levelButton.addActionListener(evt -> {
      if (!checkIfImagePresent()) {
        return;
      }
      try {
        createLevelAdjustPopUp(features);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    hFlipButton.addActionListener(evt -> {
      if (!checkIfImagePresent()) {
        return;
      }
      try {
        features.getFlipHorizontal();
        successMessage("Horizontal Flip");
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });

    vFlipButton.addActionListener(evt -> {
      if (!checkIfImagePresent()) {
        return;
      }
      try {
        features.getFlipVertical();
        successMessage("Vertical Flip");
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });

    compressButton.addActionListener(evt -> {
      if (!checkIfImagePresent()) {
        return;
      }

      try {
        String compressionValueStr;

        while (true) {
          compressionValueStr = promptForSingleInput("Compression Value",
                  "Enter compression value (0-100):",
                  "Enter a value between 0 and 100.");

          // If the user cancels or closes the dialog, exit the loop
          if (compressionValueStr == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Compression operation canceled.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
          }

          try {
            int compressionValue = Integer.parseInt(compressionValueStr);

            if (compressionValue < 0 || compressionValue > 100) {
              JOptionPane.showMessageDialog(
                      this,
                      "Please enter a value between 0 and 100.",
                      "Error",
                      JOptionPane.ERROR_MESSAGE
              );
            } else {
              // Valid input, perform compression and exit the loop
              features.getCompress(compressionValue);
              successMessage("Compress");
              break;
            }
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid integer.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
          }
        }
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });

    saveButton.addActionListener(evt -> {
      if (!checkIfImagePresent()) {
        return;
      }
      JCheckBox confirmSaveCheckBox = new JCheckBox("I confirm I want to save the image.");
      Object[] message = {"Do you want to save the image?", confirmSaveCheckBox};
      int option = JOptionPane.showConfirmDialog(
              null,
              message,
              "Save Confirmation",
              JOptionPane.OK_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE
      );
      if (option == JOptionPane.OK_OPTION) {
        if (confirmSaveCheckBox.isSelected()) {
          try {
            var = features.saveImage();
            if (var == null) {
              showInfoDialog("Image save canceled.", "Save Canceled");
            } else {
              successMessage("Save");
            }
          } catch (Exception e) {
            showErrorDialog("Error saving image!", "Error");
            throw new RuntimeException(e);
          }
        } else {
          showErrorDialog("You must check the confirmation box to save the image.",
                  "Save Error");
        }
      } else {
        showInfoDialog("Image save canceled.", "Save Canceled");
      }
    });

    downscaleButton.addActionListener(evt -> {
      if (checkIfImagePresent()) {
        try {
          int newWidth = -1;
          int newHeight = -1;

          // Loop to get valid width
          while (true) {
            String widthInput = JOptionPane.showInputDialog(this,
                    "Enter new width (positive integer):");
            if (widthInput == null) {
              JOptionPane.showMessageDialog(
                      this,
                      "Downscale operation canceled.",
                      "Info",
                      JOptionPane.INFORMATION_MESSAGE
              );
              return; // User canceled or closed the dialog
            }
            try {
              newWidth = Integer.parseInt(widthInput);
              if (newWidth > 0) {
                break; // Valid input
              } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Width must be a non-zero positive integer.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
              }
            } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(
                      this,
                      "Please enter a valid integer for width.",
                      "Error",
                      JOptionPane.ERROR_MESSAGE
              );
            }
          }

          // Loop to get valid height
          while (true) {
            String heightInput = JOptionPane.showInputDialog(this,
                    "Enter new height (positive integer):");
            if (heightInput == null) {
              JOptionPane.showMessageDialog(
                      this,
                      "Downscale operation canceled.",
                      "Info",
                      JOptionPane.INFORMATION_MESSAGE
              );
              return; // User canceled or closed the dialog
            }
            try {
              newHeight = Integer.parseInt(heightInput);
              if (newHeight > 0) {
                break; // Valid input
              } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Height must be a non-zero positive integer.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
              }
            } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(
                      this,
                      "Please enter a valid integer for height.",
                      "Error",
                      JOptionPane.ERROR_MESSAGE
              );
            }
          }

          features.downScale(Integer.toString(newWidth), Integer.toString(newHeight));
          successMessage("DownScale");

        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
      }
    });

    brightenButton.addActionListener(evt -> {
      if (checkIfImagePresent()) {
        try {
          String brightValue = null;
          while (true) {
            brightValue = promptForSingleInput(
                    "Brightness Value",
                    "Enter brightness value (positive integer):",
                    "Value entered must be greater than or equal to 0."
            );

            if (brightValue == null) {
              JOptionPane.showMessageDialog(
                      this,
                      "Brighten operation canceled.",
                      "Info",
                      JOptionPane.INFORMATION_MESSAGE
              );
              return; // User canceled or exited the dialog
            }

            try {
              int brightness = Integer.parseInt(brightValue);

              if (brightness < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Value must be a positive integer.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
              } else {
                features.getBright(brightValue);
                successMessage("Brighten");
                break; // Exit the loop on successful operation
              }
            } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(
                      this,
                      "Please enter a valid integer value.",
                      "Error",
                      JOptionPane.ERROR_MESSAGE
              );
            }
          }
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
      }
    });

    darkButton.addActionListener(evt -> {
      if (checkIfImagePresent()) {
        try {
          String brightValue = null;
          while (true) {
            brightValue = promptForSingleInput(
                    "Darkness Value",
                    "Enter darkness value (positive integer):",
                    "Value entered must be greater than or equal to 0."
            );

            if (brightValue == null) {
              JOptionPane.showMessageDialog(
                      this,
                      "Darken operation canceled.",
                      "Info",
                      JOptionPane.INFORMATION_MESSAGE
              );
              return; // User canceled or exited the dialog
            }

            try {
              int darknessValue = Integer.parseInt(brightValue);

              if (darknessValue < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Value must be a positive integer.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
              } else {
                features.getBright("-" + brightValue);
                successMessage("Darken");
                break; // Exit the loop on successful operation
              }
            } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(
                      this,
                      "Please enter a valid integer value.",
                      "Error",
                      JOptionPane.ERROR_MESSAGE
              );
            }
          }
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
      }
    });

  }

  /**
   * Checks whether an image is currently loaded in the GUI.
   *
   * @return true if an image is loaded, false otherwise.
   */
  private boolean checkIfImagePresent() {
    if (this.imgName == null) {
      // Show error popup
      JOptionPane.showMessageDialog(
              this, // Parent component - JFrame or JPanel
              "You need to load an image before applying effects.",
              "Error",
              JOptionPane.ERROR_MESSAGE
      );
      return false; // Exit the method early
    } else {
      return true;
    }
  }

  /**
   * Adds a feature handler to a button, linking it to a specific feature that opens a pop-up
   * window.
   *
   * @param button      the button to which the feature is added.
   * @param features    the Features object defining the operation.
   * @param featureName the name of the feature being linked to the button.
   */
  private void addFeatureWithPopup(JButton button, Features features, String featureName) {
    button.addActionListener(evt -> {
      if (!checkIfImagePresent()) {
        return;
      }
      createPoPuP(featureName, features);

    });
  }

  /**
   * Displays an information dialog with the given message and title.
   *
   * @param message The message to be displayed in the dialog.
   * @param title   The title of the dialog window.
   */
  private void showInfoDialog(String message, String title) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Displays an error dialog with the given message and title.
   *
   * @param message The error message to be displayed in the dialog.
   * @param title   The title of the dialog window.
   */
  private void showErrorDialog(String message, String title) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Creates and displays a popup window for split preview with various action buttons.
   *
   * @param actionType The type of action to be performed (e.g., "blur", "red-component").
   * @param features   The Features object used to apply the corresponding effect.
   */
  private void createPoPuP(String actionType, Features features) {

    JTextField bTextFeild;
    JTextField mTextFeild;
    JTextField wTextFeild;
    JPanel inputBMWPanel;

    newViewFrame = new JFrame("Split Preview Screen Window");
    newViewFrame.setSize(800, 500);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    imageLabel = new JLabel("Image will appear here", JLabel.CENTER);
    imageLabel.setPreferredSize(new Dimension(400, 300));
    panel.add(imageLabel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    JButton applyButton = new JButton("Apply");
    JButton cancelButton = new JButton("Cancel");
    JButton splitPreviewButton = new JButton("Split");

    buttonPanel.add(applyButton);
    buttonPanel.add(splitPreviewButton);
    buttonPanel.add(cancelButton);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    JPanel inputPanel = new JPanel();
    percentageTextField = new JTextField(5);
    percentageTextField.setToolTipText("Enter percentage between 0 and 100");
    inputPanel.add(new JLabel("Enter percentage for split preview:"), BorderLayout.NORTH);
    inputPanel.add(percentageTextField, BorderLayout.NORTH);

    inputBMWPanel = new JPanel();

    bTextFeild = new JTextField(5);
    mTextFeild = new JTextField(5);
    wTextFeild = new JTextField(5);

    bTextFeild.setToolTipText("Enter B between 0 and 100");
    mTextFeild.setToolTipText("Enter M between 0 and 100");
    wTextFeild.setToolTipText("Enter W between 0 and 100");

    inputBMWPanel.add(bTextFeild, BorderLayout.NORTH);
    inputBMWPanel.add(mTextFeild, BorderLayout.SOUTH);
    inputBMWPanel.add(wTextFeild, BorderLayout.WEST);

    newViewFrame.add(inputPanel, BorderLayout.NORTH);
    newViewFrame.add(panel, BorderLayout.SOUTH);

    addFeaturestoPopUp(features, actionType, applyButton, cancelButton, splitPreviewButton);

    newViewFrame.setVisible(true);
  }

  /**
   * Handles the split action for an image by updating the display with the modified image.
   *
   * @param img The updated image to be displayed in the popup.
   */
  @Override
  public void handleSplitAction(BufferedImage img) {
    try {
      if (newViewFrame != null && newViewFrame.isVisible()) {
        BufferedImage updatedImage = img;
        if (updatedImage != null) {
          imageLabel.setIcon(new ImageIcon(updatedImage));
          imageLabel.setText(null); // Clear placeholder text if present
          newViewFrame.revalidate(); // Refresh layout
          newViewFrame.repaint(); // Redraw components
          newViewFrame.setVisible(true);
        } else {
          imageLabel.setText("No image to display.");
        }
      } else {
        System.out.println("No open popup frame to update.");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      imageLabel.setText("Error updating image.");
    }
  }

  /**
   * Adds event listeners to the popup buttons and handles their actions.
   *
   * @param features           The Features object used to apply the corresponding effect.
   * @param actionType         The type of action to be performed.
   * @param applyButton        The "Apply" button in the popup window.
   * @param cancelButton       The "Cancel" button in the popup window.
   * @param splitPreviewButton The "Split" button in the popup window.
   */
  private void addFeaturestoPopUp(Features features, String actionType, JButton applyButton,
                                  JButton cancelButton, JButton splitPreviewButton) {
    cancelButton.addActionListener(evt -> {
      newViewFrame.dispose();

    });
    applyButton.addActionListener(e -> {
      try {
        switch (actionType) {
          case "red-component":
            features.getRedComponent(100, false);
            newViewFrame.dispose();
            successMessage("Red-component");
            break;
          case "blue-component":
            features.getBlueComponent(100, false);
            newViewFrame.dispose();
            successMessage("Blue-component");
            break;
          case "green-component":
            features.getGreenComponent(100, false);
            newViewFrame.dispose();
            successMessage("Green-component");
            break;
          case "blur":
            features.getBlur(100, false);
            newViewFrame.dispose();
            successMessage("Blur");
            break;
          case "sharpen":
            features.getSharpen(100, false);
            newViewFrame.dispose();
            successMessage("Sharpen");
            break;
          case "luma":
            features.getLuma(100, false);
            newViewFrame.dispose();
            successMessage("Luma");
            break;
          case "sepia":
            features.getSepia(100, false);
            newViewFrame.dispose();
            successMessage("Sepia");
            break;
          case "color-correct":
            features.getColorCorrect(100, false);
            newViewFrame.dispose();
            successMessage("Color-correct");
            break;
          default:
            throw new IllegalStateException("Unexpected value: " + actionType);
        }
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });

    splitPreviewButton.addActionListener(e -> {
      try {
        String input = percentageTextField.getText().toString().trim();

        // Check if input is null or empty
        if (input == null || input.isEmpty()) {
          JOptionPane.showMessageDialog(newViewFrame, "Please enter a valid percentage.",
                  "Input Error",
                  JOptionPane.ERROR_MESSAGE);
          return; // Do not proceed with the action or close the window
        }

        int percentage = Integer.parseInt(input);
        if (percentage >= 0 && percentage <= 100) {
          switch (actionType) {
            case "blur":
              features.getBlur(percentage, true);
              break;
            case "luma":
              features.getLuma(percentage, true);
              break;
            case "sepia":
              features.getSepia(percentage, true);
              break;
            case "red-component":
              features.getRedComponent(percentage,
                      true);
              break;
            case "green-component":
              features.getGreenComponent(percentage,
                      true);
              break;
            case "blue-component":
              features.getBlueComponent(percentage,
                      true);
              break;
            case "sharpen":
              features.getSharpen(percentage, true);
              break;
            case "color-correct":
              features.getColorCorrect(percentage,
                      true);
              break;

            default:
              JOptionPane.showMessageDialog(newViewFrame, "Invalid action type specified: "
                              + actionType,
                      "Error", JOptionPane.ERROR_MESSAGE);
              break;
          }
        } else {
          JOptionPane.showMessageDialog(newViewFrame, "Split percentage should be "
                          + "between 0 to 100.",
                  "Error", JOptionPane.ERROR_MESSAGE);
        }
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(newViewFrame, "An error occurred: "
                        + ex.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  /**
   * Creates and displays a popup window for level adjustments with user input fields and buttons.
   *
   * @param features The Features object used to apply the level adjustment effect.
   */
  private void createLevelAdjustPopUp(Features features) {
    levelAdjustFrame = new JFrame("Level Adjust");
    levelAdjustFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    levelAdjustFrame.setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

    JPanel bmwPanel = new JPanel();
    bmwPanel.setLayout(new BoxLayout(bmwPanel, BoxLayout.X_AXIS));

    JLabel labelB = new JLabel("B (0-255):");
    JTextField textFieldB = new JTextField(5);
    textFieldB.setMaximumSize(textFieldB.getPreferredSize());

    JLabel labelM = new JLabel("M (0-255):");
    JTextField textFieldM = new JTextField(5);
    textFieldM.setMaximumSize(textFieldM.getPreferredSize());

    JLabel labelW = new JLabel("W (0-255):");
    JTextField textFieldW = new JTextField(5);
    textFieldW.setMaximumSize(textFieldW.getPreferredSize());

    bmwPanel.add(labelB);
    bmwPanel.add(textFieldB);
    bmwPanel.add(labelM);
    bmwPanel.add(textFieldM);
    bmwPanel.add(labelW);
    bmwPanel.add(textFieldW);

    JLabel percentageInput = new JLabel("Split Percentage (0-100):");
    JTextField textFieldInput = new JTextField("50", 5);
    textFieldInput.setMaximumSize(textFieldInput.getPreferredSize());

    inputPanel.add(bmwPanel);
    inputPanel.add(percentageInput);
    inputPanel.add(textFieldInput);

    levelAdjustFrame.add(inputPanel, BorderLayout.NORTH);

    imageLabel = new JLabel();
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JScrollPane scrollPane = new JScrollPane(imageLabel);
    levelAdjustFrame.add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    JButton applyButton = new JButton("Apply");
    buttonPanel.add(applyButton);

    JButton cancelButton = new JButton("Cancel");
    buttonPanel.add(cancelButton);

    JButton submitButton = new JButton("Split");
    buttonPanel.add(submitButton);

    levelAdjustFrame.add(buttonPanel, BorderLayout.SOUTH);

    levelAdjustFrame.setSize(800, 500);
    levelAdjustFrame.setLocationRelativeTo(null);
    levelAdjustFrame.setVisible(true);

    applyButton.addActionListener(e -> {
      try {
        int b = Integer.parseInt(textFieldB.getText().trim());
        int m = Integer.parseInt(textFieldM.getText().trim());
        int w = Integer.parseInt(textFieldW.getText().trim());

        if (b >= m || m >= w) {
          JOptionPane.showMessageDialog(levelAdjustFrame, "B, M, and W values must"
                  + " be in ascending order.", "Input Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        if (b < 0 || b > 255 || m < 0 || m > 255 || w < 0 || w > 255) {
          JOptionPane.showMessageDialog(levelAdjustFrame, "B, M, and W values must be"
                  + " between 0 to 255", "Input Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        features.getLevelAdjust(b, m, w, 50, false);
        levelAdjustFrame.dispose();
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(levelAdjustFrame, "Please enter valid "
                + "numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });

    cancelButton.addActionListener(e -> levelAdjustFrame.dispose());

    submitButton.addActionListener(e -> {
      try {
        int b = Integer.parseInt(textFieldB.getText().trim());
        int m = Integer.parseInt(textFieldM.getText().trim());
        int w = Integer.parseInt(textFieldW.getText().trim());
        int p = Integer.parseInt(textFieldInput.getText().trim());

        if (p < 0 || p > 100) {
          JOptionPane.showMessageDialog(levelAdjustFrame, "Please enter a percentage"
                  + " between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
          return;
        }

        if (b >= m || m >= w) {
          JOptionPane.showMessageDialog(levelAdjustFrame, "B, M, and W values must be "
                  + "in ascending order.", "Input Error", JOptionPane.ERROR_MESSAGE);
          return;
        }

        if (b < 0 || b > 255 || m < 0 || m > 255 || w < 0 || w > 255) {
          JOptionPane.showMessageDialog(levelAdjustFrame, "B, M, and W values must be "
                  + "between 0 to 255", "Input Error", JOptionPane.ERROR_MESSAGE);
          return;
        }


        features.getLevelAdjust(b, m, w, p, true);
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(levelAdjustFrame, "Please enter valid numeric"
                + " values.", "Input Error", JOptionPane.ERROR_MESSAGE);
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });
  }

  /**
   * Handles the level adjustment action for an image by updating the display with modified image.
   *
   * @param img The updated image to be displayed in the popup.
   */
  @Override
  public void handleSplitActionLevel(BufferedImage img) {
    try {
      if (levelAdjustFrame != null && levelAdjustFrame.isVisible()) {
        BufferedImage updatedImage = img;
        if (updatedImage != null) {
          imageLabel.setIcon(new ImageIcon(updatedImage));
          imageLabel.setText(null); // Clear placeholder text if present
          levelAdjustFrame.revalidate(); // Refresh layout
          levelAdjustFrame.repaint(); // Redraw components
          levelAdjustFrame.setVisible(true);
        } else {
          imageLabel.setText("No image to display.");
        }
      } else {
        System.out.println("No open popup frame to update.");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      imageLabel.setText("Error updating image.");
    }
  }


}

