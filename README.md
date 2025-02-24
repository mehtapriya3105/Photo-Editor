<<<<<<< HEAD
# Image Processing Application

## Project Description:

This is an image processing application designed to facilitate various image manipulation tasks,
including flipping, filtering, and combining images. The application allows users to perform
operations through a user-friendly command-line interface, enabling efficient processing of image
files in formats like JPEG, JPG, PNG, and PPM. With a focus on modular design, the application
supports multiple image manipulation techniques, enhancing user capabilities in image editing and
analysis.
---
Here’s the **Table of Contents** in a proper README format:

# Table of Contents

1. [Project Description](#project-description)
2. [Introduction](#introduction)
    - [Objectives](#objectives)
    - [Use Cases](#use-cases)
3. [Features](#features)
    - [Image Manipulation](#image-manipulation)
    - [Color Manipulation](#color-manipulation)
    - [File Handling](#file-handling)
    - [Command-Line Interface](#command-line-interface)
4. [Technology Used](#technology-used)
    - [Programming Language](#programming-language)
    - [Design Patterns](#design-patterns)
    - [SOLID Principles](#solid-principles)
    - [Development Tools](#development-tools)
5. [Command Reference](#command-reference)
    - [Commands](#commands)
    - [Filter Commands](#filter-commands)
6. [Code Structure](#code-structure)
7. [Architecture](#architecture)
    - [Model Package](#model-package)
    - [Controller Package](#controller-package)
    - [Utils Package](#utils-package)
    - [View Package](#view-package)
    - [Main Class](#imageprocessorapp-class)
8. [Command Script](#command-script)
9. [References](#references)

---

## Introduction:

This image processing application is designed to streamline the manipulation of image files by
offering a wide range of operations such as flipping, filtering, and combining images. With support
for popular image formats including JPEG, JPG, PNG, and PPM, the application provides users with
powerful tools to efficiently handle various image editing tasks through a simple and intuitive
command-line interface. The modular design allows for flexibility and extensibility, making the
application suitable for both casual users and developers interested in adding custom features or
using it in larger workflows.

#### Objectives:

- Efficient Image Manipulation: Enable users to quickly apply common image transformations like
  flipping, rotating, and filtering to images without the need for complex software.

- User-Friendly CLI: Provide an easy-to-use command-line interface, allowing users to interact with
  the application seamlessly, while reducing the complexity typically associated with image editing.

- Format Flexibility: Support multiple image formats such as JPEG, PNG, JPG, and PPM to ensure
  compatibility with a wide range of images.

- Modular Design: Implement a modular structure that allows for easy extension of functionalities,
  enabling future development of additional image manipulation techniques.

- High Performance: Ensure that the application is optimized for processing large image files and
  multiple images simultaneously, maintaining high performance and speed.

- Extensibility: Offer a codebase that is easy to extend for developers interested in adding new
  features like additional filters or custom manipulations.

#### Use Cases:

- Basic Image Flipping: Users can flip images vertically or horizontally for quick transformations
  or corrections, such as correcting mirror image effects.

- Filtering Operations: Apply various filters like grayscale, sepia, or sharpening to enhance the
  visual appearance of images for creative or analytical purposes.

- Image Combination: Merge multiple images into one, either for aesthetic purposes or creating
  composite images for research, presentations, or artistic work.

- Batch Processing: Process multiple images at once through command-line scripting, making the
  application useful for photographers, graphic designers, or developers handling large collections
  of images.

- Educational and Research Projects: Students or researchers can use the application to apply image
  manipulation techniques for academic purposes, such as in projects focused on computer vision or
  image analysis.

- Customizable Filters: Developers can extend the application by adding custom filters or effects,
  allowing for specialized image processing workflows.

## Features:

The Image Processing Application offers a comprehensive set of tools for efficient image enhancement
and manipulation.

#### Image Manipulation:

- Flipping: Effortlessly flip images horizontally or vertically to create mirror effects or adjust
  orientations.
- Brightening: Modify the brightness of images to improve visibility or achieve creative effects.
- Blurring: Apply blur to soften image details, ideal for background effects or artistic purposes.
- Sharpening: Increase clarity and detail with sharpening techniques to improve image quality.
- Sepia: Add a sepia-tone effect for vintage-style image manipulation.

#### Color Manipulation:

- RGB Splitting: Split images into their red, green, and blue channels for detailed editing and
  analysis.
- RGB Combining: Reassemble the RGB components to form the final image, allowing customized color
  adjustments and corrections.
- Component Operations: Convert images to grayscale or apply thresholding techniques on color
  components (e.g., red, green, blue, luma, value, intensity) for enhanced color control.

#### Image Compression:

- Compress images: Compresses images to reduce file size while maintaining quality.

#### Image Histograms:

- Create histograms: Generates histograms to visualize the color distribution in images.
- Color corrections: Adjusts the colors in an image to improve its appearance.
- Level adjustments: Modifies the image's brightness and contrast by adjusting the color levels.

#### Downscale:

- Image downscaling reduces the dimensions of an image to make it smaller while preserving its
  visual content.

#### Mask:

- Masking an image (Manipulation of image only where the other image is bright/white): The following
  operations can support it:
  red-component, blue-component, green-component, value, luma, intensity, sepia, sharpen, blur.

#### Split Preview:

- Split preview (manipulate certain portion of an image): The following operations can support this:
  blur, sharpen, sepia, greyscale, color correction and levels adjustment.

#### File Handling:

- Multi-format support: Open, modify, and save images in various formats like JPEG, PNG, JPG, and
  PPM, ensuring broad compatibility with digital image files.
- Batch Processing: Load and edit multiple images simultaneously, then save them based on user
  preferences for efficient batch processing.

#### Command-Line Interface:

- Interactive Commands: A user-friendly command-line interface allows users to execute
  image-processing commands with real-time feedback.
- Error Handling: Robust error handling informs users of command failures or incorrect inputs
  through clear messages.
- Scripting Capability: Automate repetitive or complex tasks by executing a series of commands from
  a script file.
  Together, these features make the Image Processing Application a versatile tool for anyone looking
  to efficiently edit, enhance, and process digital images.

These features collectively make the Image Processing Application a powerful and flexible tool for
anyone looking to edit, enhance, and manipulate digital images efficiently.

## Technology Used:

This application is built using modern programming languages, libraries, and design patterns that
contribute to its performance, usability, and scalability.

#### Programming Language:

- Java: The application is primarily written in Java, leveraging its powerful object-oriented
  capabilities and comprehensive standard libraries to create a scalable and maintainable image
  processing system.

#### Design Patterns:

- Factory Pattern: Used for the command creation process, allowing commands to be dynamically
  generated, enhancing scalability, and simplifying command management.

- MVC Model-View-Controller: The application adheres to the MVC architecture, separating the
  business logic (Model), the user interface (View), and input handling (Controller) to improve
  modularity and maintainability.

- Command Pattern: Implements the factory pattern to efficiently generate command objects based on
  user inputs, improving code readability and maintainability.

- Template Method Pattern: Used in the `AbstractFlipCommand`, allowing subclasses to override
  specific steps (like flipping direction) while keeping the overall algorithm intact.

- Builder Pattern: It allows for the step-by-step construction of complex objects by separating the
  construction process from the final object representation.

- General Command Callbacks: This design is used to design our view. It decouples the view from
  specific controller implementations, enabling flexible and reusable interactions through a
  standardized mechanism for invoking commands.

#### SOLID Principles:

- Single Responsibility Principle: Each class has a specific purpose, such as loading images,
  processing commands, or managing image data.

- Open/Closed Principle: The system can be extended with new commands and functionalities without
  modifying existing code, thanks to the abstract command structures.

- Liskov Substitution Principle: Subclasses can be used interchangeably with their base classes,
  maintaining the expected behavior.

- Interface Segregation Principle: Interfaces like `ImageMapper` ensure that classes only implement
  the methods they need, promoting a cleaner design.

- Dependency Inversion Principle: High-level modules depend on abstractions rather than concrete
  implementations, enabling flexibility and easier testing.

#### Development Tools:

- IDE: The application was developed using IDEs like IntelliJ IDEA, providing advanced features for
  coding, debugging, and project management.
- Version Control: Git was utilized for version control, enabling collaborative development and
  streamlined code change management.
- JUnit: Unit testing was conducted using JUnit to ensure that all application components function
  as expected, maintaining high code quality throughout the development process.
  By combining these technologies, the application delivers a powerful, user-friendly
  image-processing experience that caters to both beginner and advanced users.
- Swing: Swing is a Java GUI toolkit that provides a rich set of lightweight, platform-independent
  components for building desktop applications. It is built on top of AWT.

## Command Reference:

This section provides a list of available commands and their descriptions for the Image Processing
application.

#### Commands:

Load Command: The command below represents load command which includes path and
image name to refer in future manipulations.

```bash 

load <image_path> <reference_name>

```

Save Command: Saves the manipulated image to the specified path using the name of the previously
loaded image reference.

```bash

save <output_path> <reference_name>

```

Run command: This command is used to run a ".txt" file where the commands are written in it to
execute line by line.

```bash

run <script_file_path>
```

Exit Command: This command is used to terminate the program. By just entering "exit" and pressing
enter, the program will be terminated.

#### Filter Commands:

Brighten Command: Brightens the referenced image by the specified value. A positive value for
intesity_value increases
brightness, while a negative value decreases it, image_value indicates the image that
has to be manipulated and the reference_name is the image's name after manipulation.

```bash

brighten <intensity_value> <image_name> <reference_name>

```

Compress command: It is simply a reduction in the amount of data that is stored when the image is
saved to a file.

```bash

compress <percentage_value> <image_name> <reference_name>

```

Blur Command: Blur command blurs the image with a predefined filter that runs over the pixels of the
image in order to blur it.

Sharpen Command: Sharpen command sharpens the image with a predefined filter that runs over the
pixels of the image in order to sharpen it.

Sepia Command: Sepia command applies a sepia tone to the image that manipulates the pixels by matrix
multiplication method with the sepia equation.

Red-component Command: This converts the image into its red component where the RGB channel gets
converted to RRR channel.

Green-component Command: This converts the image into its green component where the RGB channel gets
converted to GGG channel.

Blue-component: This converts the image into its blue component where the RGB channel gets converted
to BBB channel.

Luma-component : This converts the image into its red component where the RGB channel gets
multiplied with the luma equation to manipulate the image.

Value-component: This converts the image into its red component where the RGB channel is changed
to maximum value of pixel and that gets replicated into all the three channel.

Intensity-component: This converts the image into its red component where the RGB channel is
changed to the average value of pixel and that gets replicated into all the three channel.

Horizontal-flip command: This converts the image into its horizontally flipped form.

Vertical-flip command: This converts the image into its vertically flipped form.

Color-Correct command: The process of aligning the histogram peaks of individual channels.

Histogram command: It is drawn as a line graph joining the peaks of the frequencies of consecutive
values.

Command syntax for above all filters:

```bash

<filter_name> <image_name> <reference_name>

```

Command syntax for split preview for blur, intensity, luma, sharpen, sepia, value,
greyscale, color correction and levels adjustment:

```bash

<filter_name> <image_name> <reference_name> <split> <percentage_value>

```

RGB-split command: This command makes 3 separate images which are the red, green and blue components
of the images.

```bash

rgb-split  <image_name> <red_reference_name> <green_reference_name> <blue_reference_name>

```

RGB-combine command: This command combines the 3 RGB components into a single image.

```bash 

rgb-combine <reference_name> <red_image_name> <green_image_name> <blue_image_name>

```

Level-Adjust command: In an image, the "dark" regions would contribute to the values on the left of
the histogram, the "bright" regions would contribute to the values on the right of the histogram.
These are often called "shadows" and "highlights". In addition, a "mid" region is also helpful.

```bash 

level-adjust <black_value> <mid_value> <white_value> <image_name> <reference_name>

```

Downscale: This command takes in new height and new width from the user along with the source image
name and destination image name and downscales it to new width and height.
The height and width are non-zero positive integers and smaller than width and height of source
image.

```bash 
downscale <image_name> <reference_name> <new_height> <new_width>
```

Mask: This command takes in image name from user and assumes that the image used for masking is
already
loaded in the memory, the masked image must be of same dimensions as the image to be masked. It
saves the resultant image in the reference image, this operation is performed for
value, luma, intensity, sepia, blur, sharpen, red-component, green-component and blue-component.

```bash 
mask <image_name> <mask_image_name> <reference_name>
```

## Code Structure:

The code structure for the project is displayed below:

```bash

ImageProcessingApp/

│

├── src/

│   ├── controller/                    # Handles execution of commands and interaction with model.

│   │	  ├── loadsave/                  # Contains the files for load and save.

|    |   │   ├── ILoadSave.java			 # Interface for load and save.

|    |   │   ├── AbstractLoadSave.java

|    |   │   ├── Jpeg.java

|    |   │   ├── Jpg.java

|    |   │   ├── Png.java

|    |   │   ├── Ppm.java

|    |   │   ├── LoadCommand.java

|    |   │   └── SaveCommand.java

│   │   |

│   │   ├── ControllerCommand.java 	  # Interface for controller.

│   │   ├── Features.java # Interface for guicontroller

│   │   ├── CommandMapperNew.java

│   │   ├── CommandParser.java

│   │   ├── ControllerCommandHandler.java

│   │   ├── GuiController.java 

│   │   ├── ImageProcessorController.java

│   │   ├── QuitCommand.java

│   │   └── RunScriptCommand.java

│   │

│   ├── model/                           # Contains the core logic for image manipulation and operations.

│   │   ├── commands/

│   │   │   ├── commandexecutors/

│   │   │   │   ├── ICommandExecutor.java     # Interface for command executors

│   │   │   │   ├── AbstractCommandExecutor.java 

│   │   │   │   ├── BlueComponentCommandExecutor.java 

│   │   │   │   ├── BlurCommandExecutor.java 

│   │   │   │   ├── BrightenCommandExecutor.java     

│   │   │   │   ├── ColorCorrectCommandExecutor.java 

│   │   │   │   ├── CompressCommandExecutor.java  

│   │   │   │   ├── DownscaleCommandExecutor.java 

│   │   │   │   ├── GreenComponentCommandExecutor.java  

│   │   │   │   ├── HistogramCommandExecutor.java  

│   │   │   │   ├── HorizontalFlipCommandExecutor.java  

│   │   │   │   ├── IntensityComponentComamandExecutor.java 

│   │   │   │   ├── LevelAdjustCommandExecutor.java  

│   │   │   │   ├── LumaComponentCommandExecutor.java 

│   │   │   │   ├── RedComponentCommandExecutor.java  

│   │   │   │   ├── RGBCombineCommandExecutor.java 

│   │   │   │   ├── RGBSplitCommandExecutor.java 

│   │   │   │   ├── SepiaCommandExecutor.java  

│   │   │   │   ├── SharpenCommandExecutor.java  

│   │   │   │   ├── ValueComponentCommandExecutor.java    

│   │   │   │   └── VerticalFlipCommandExecutor.java

│   │   │   │

│   │   │   ├── ModelCommand.java        # Model interface for execute method.

│   │   │   └── ModelCommandHandler.java

│   │   │

│   │   ├── database/

|   │   │   ├── ICustomImage.java         # Interface for old functionalities.

|   │   │   ├── ICustomImage2.java        # Interface with old and second version functionalities.

|   │   │   ├── ICustomImage3.java        # Interface with old and third version functionalities 

|   │   │   ├── IPixel.java       # Interface with pixel class.

|   │   │   ├── Pixel.java

|   │   │   ├── Image.java

|   │   │   ├── CustomImage.java

|   │   │   ├── CustomImage2.java

│   │   │   └── CustomImage3.java

|   │   │    

│   │   ├── filters/

|   │   │   ├── BlurCommand.java

|   │   │   ├── IntensityCommand.java

|   │   │   ├── LumaCommand.java

|   │   │   ├── SepiaCommand.java

|   │   │   ├── SharpenCommand.java

|   │   │   └── ValueCommand.java

│   │   |

│   │   ├── flip/

|   │   │   ├── HorizontalFlipCommand.java

|   │   │   └── VerticalFlipCommand.java

│   │   |

│   │   ├── histogram/

|   │   │   ├── ColorCorrectCommand.java

|   │   │   ├── LevelAdjustCommand.java

|   │   │   └── HistogramCommand.java

│   │   |

│   │   ├── rgbimage/

|   │   │   ├── BlueComponentCommand.java

|   │   │   ├── GreenComponentCommand.java

|   │   │   ├── RedComponentCommand.java

|   │   │   ├── RgbCombine.java

|   │   │   └── RgbSplitCommand.java

│   │   |

│   │   ├── IImageModel.java      # Interface for model

│   │   ├── AbstractImageCommand.java

│   │   ├── AbstractCommandBuilder.java

│   │   ├── BrightenCommand.java

│   │   ├── CompressCommand.java	

│   │   ├── DownScaling.java

│   │   └── ImageModel.java	        

│   │

│   ├── utils/                               # Contains the file for matrix comparison.

│   │   └── ImageComparisonUtils.java

│   │

│   ├── view/                               # Contains the file for matrix comparison.

│   │   ├── IView.java

│   │   └── JFrameView.java

│   │

│   └── ImageProcessorApp.java               # Entry point for the application.

│

├── test/

│   ├── mock/ 

│   │   ├── MockModel.java

│   │   └── MockView.java

│   ├── MockViewTest.java

│   ├── TestCommandController.java

│   ├── TestModelController.java

│   └── TestModelLogic.java                            

│

└── res/                                    # Resources directory.

│   ├── jar file and class diagram

│   ├── test.txt and check.txt

│   └── (Images for loading, saving, and processing)

```

This structure ensures a clean separation of concerns, allowing easy modifications, testing, and
future extension of functionalities.

## Changes and Design Updates:

JFrameView represents the view for the MVC. It implements IView and extends JFrame. Swing libraries
which is built upon AWT are used.
General command callbacks design is used to design the gui as it decouples the view from specific
controller implementations, enabling flexibility and reusability.
GuiController implements features. It is the controller for the gui view. It handles interaction
with the JFrameView and model.
CustomImage3 implements ICustomImage3 and contains all new functionalities and all old
functionalities as it extends CustomImage2.
ICustomImage3 extends ICustomImage3 hence, following SOLID principles.
Image class was created to seperate out the object from its functionalities/operations.

**New Files:**

Features- It defines all image processing functionalities.  
GuiController- It implements all image processing functionalities from Features. View and model are
connected via controller.
IView- It is the interface for main frame with all functionalities.
JFrameView- This class represents the JFrame-based GUI view for the image processing application.
This class provides the user interface to interact with the application, including loading, saving,
and applying image transformations and effects.
DownScaling- This class processes commands to downscale the image. It further calls the image model
for the downscale method.
Image- The image is decoupled from its operations.
CustomImage3- It is the new model class which implements ICustomImage3 and extends CustomImage2
hence, it has all the previous functionalities as well as the new one.
ICustomImage3- This is an interface extends ICustomImage2.java and that defines all the getters,
setters, and new functionalities related to image manipulation.

## Architecture:

The Image Processing Application is built using a clean architecture that separates concerns across
five key packages: model, controller, utils, view and main (ImageProcessorApp). This section
explains the
role of each package and
provides a detailed explanation of the core classes within them.

#### Model Package:

- commands Package : It contains all the classes that create objects for respective functionalities.

1. commandexecutors Package: It contains classes for all command executors.

a. ICommandExecutor: It is the method that is executed by all the command executor classes to create
commands based on arguments provided by user.

b. AbstractCommandExecutor: This is the abstract class with abstract constructor that takes in the
input of model command handler, image model, and arguments by the user. This input is further used
by create command function.

c. BlueComponentCommandExecutor.java, BlurCommandExecutor.java, BrightenCommandExecutor.java,
ColorCorrectCommandExecutor.java, CompressCommandExecutor.java, DownscaleCommandExecutor.java,
GreenComponentCommandExecutor.java, HistogramCommandExecutor.java,
HorizontalFlipCommandExecutor.java,
IntensityComponentComamandExecutor.java, LevelAdjustCommandExecutor.java,
LumaComponentCommandExecutor.java,
RedComponentCommandExecutor.java, RGBCombineCommandExecutor.java, RGBSplitCommandExecutor.java,
SepiaCommandExecutor.java, SharpenCommandExecutor.java, ValueComponentCommandExecutor.java,
VerticalFlipCommandExecutor.java: It initializes constructors for all the model methods using the
string builder and executes them.

2. ModelCommand.java:
   It is interface that is used for creating objects of all command classes.

3. ModelCommandHandler.java:
   This class handles the execution of commands related to the model’s functionalities by invoking
   the appropriate methods. Here, we switched to switch statements from command map as the length of
   the arguments increased from 2 to 4, and has a possibility to increase in future which can lead
   to incorrect arguments being set up by the tester or user.Hence, command builder class is better.

- database Package : This package primarily deals with managing images and consists of the following
  components:

1. ICustomImage.java:
   This is an interface that defines all the getters, setters, and functionalities related to image
   manipulation.

2. ICustomImage2.java:
   This is an interface extends ICustomImage.java and that defines all the getters, setters, and
   new functionalities related to image manipulation.

3. ICustomImage3.java:
   This is an interface extends ICustomImage2.java and that defines all the getters, setters, and
   new functionalities related to image manipulation.

4. IPixel.java:
   This is interface for pixel class, it shows the gets and sets red, green, blue components.

5. CustomImage.java:
   This class holds the actual image object and implements all the methods related to image
   processing.

6. CustomImage2.java:
   It is the new model class which implements ICustomImage2 and extends CustomImage hence,
   it has all the previous functionalities as well as the new one.

7. CustomImage3.java:
   It is the new model class which implements ICustomImage3 and extends CustomImage2 hence,
   it has all the previous functionalities as well as the new one.

8. Image.java:
   This is the representation of an image. It has setters and getters for height, width and pixel.

9. Pixel.java:
   This is the implementation of one pixel for an image, it gives user flexibility to change the
   type of image being stored.


- filters Package : This package contains files related to applying filters to images. The classes
  act as wrappers for methods in the CustomImage class. The classes have been changed to adjust
  builder class. Also, the classes handle the functionality for split.

1. BlurCommand.java:
   This class calls the blur method from the CustomImage class, acting as a wrapper for it.

2. IntensityCommand.java:
   It invokes the intensity function of the CustomImage class and stores the result using a name
   provided by the user in the ImageManager.

3. LumaCommand.java:
   This class calls the luma functionality of the CustomImage class and stores the result in the
   ImageManager.

4. SepiaCommand.java:
   It calls the sepia functionality of the CustomImage class and stores the resulting image with the
   user-provided name in the ImageManager.

5. SharpenCommand.java:
   This class invokes the sharpen function from the CustomImage class and stores the generated image
   in the ImageManager using a user-defined name.

6. ValueCommand.java:
   It calls the value function of the CustomImage class and stores the generated image in the
   ImageManager.


- flip Package : This package contains files related to flipping images. The classes have been
  changed to adjust builder class.

1. AbstractFlipCommand.java:
   This abstract class saves the flipped image in the ImageManager. It also contains abstract
   methods for flipping the image, which are implemented by horizontal and vertical flip classes.

2. HorizontalFlipCommand.java:
   It invokes the fliph (horizontal flip) method from the CustomImage class.

3. VerticalFlipCommand.java:
   This class calls the flipv (vertical flip) method from the CustomImage class.

- histogram Package : This package contains new functionalities of color correction, histogram,
  level-adjustment.

1. ColorCorrectCommand.java:
   This class adjusts the colors in an image to improve its appearance.

2. HistogramCommand.java:
   This class generates histograms to visualize the color distribution in images.

3. LevelAdjustCommand.java:
   This class modifies brightness and contrast of image by redistributing pixel intensity levels.

- rgbimage Package : This package handles functionalities related to the RGB components of images.
  The classes have been changed to adjust builder class.

1. BlueComponentCommand.java:
   It calls the getblueImage method from the CustomImage class to extract the blue component of the
   image.

2. GreenComponentCommand.java:
   This class invokes the getgreenImage method from the CustomImage class to extract the green
   component.

3. RedComponentCommand.java:
   It calls the getredImage method from the CustomImage class to extract the red component.

4. RgbCombine.java:
   This class calls the rgbcombine function from the CustomImage class to combine the red, green,
   and blue components into one image.

5. RgbSplitCommand.java:
   It invokes the splitrgb function from the CustomImage class to split the image into red, green,
   and blue channels.

- AbstractCommandBuilder.java:
  Generic command builder class for building builder objects for other functionalities.

- AbstractImageCommand.java:
  This abstract class handles commands in the
  format <command> <src file name> <destination file name>. It serves as a base for all
  image-related commands. Creates builder class so removed execute method and added constructor.

- BrightenCommand.java:
  This class processes commands to brighten or darken an image. It calls the brighten method from
  the CustomImage class and stores the newly generated image in the ImageManager.

- CompressCommand.java:
  This class processes commands to compress images to reduce file size while maintaining quality.

- DownScaling.java: This class processes commands to downscale the image. It further calls the image
  model for the downscale method.

- IImageModel.java: It is the interface that shows all the operations performed by the model.

- ImageModel.java: It is the class that performs all the image operations and also validates the
  user input.

#### Controller Package:

- loadsave Package : This package manages the loading and saving of image files.

1. ILoadSave.java:
   An interface that defines the load and save functionalities for file formats like JPEG, PNG, and
   JPG.

2. AbstractLoadSave.java:
   This class contains the core logic for loading and saving files that can be handled by
   BufferedImage, specifically formats like JPEG, JPG, and PNG.

3. Jpeg.java:
   A class that specifies saving the file as a JPEG.

4. Jpg.java:
   A class that specifies saving the file as a JPG.

5. Png.java:
   A class that specifies saving the file as a PNG.

6. Ppm.java:
   This class handles loading and saving PPM files.

7. LoadCommand.java:
   Contains the logic for loading files. It maps file extensions to the corresponding class that
   handles that file type.

8. SaveCommand.java:
   Handles saving files. It maps the file extension to the respective class for saving the image.

- ControllerCommand.java:
  The main controller class that maps commands like Load, Save, Quit, and Run to their respective
  classes.

- CommandMapperNew.java:
  The primary mapper that links incoming commands to either ControllerCommand or ModelCommand.

- CommandParser.java:
  A utility class that parses user input, splitting it into command names and arguments. This is
  useful for processing both user input and script files, reducing code duplication.

- ControllerCommandHandler.java:
  Manages controller commands such as load, save, run, and quit. It registers these commands and
  executes them based on the provided command name.

- ImageProcessorController.java:
  The main controller for the program. It reads the user input or a script file line by line and
  processes commands.

- QuitCommand.java:
  Handles the command to quit the program.

- RunScriptCommand.java:
  Processes the <run> command and executes a script file containing a list of commands.

- Features.java:
  It defines all image processing functionalities.

- GuiController.java:
  It implements all image processing functionalities from Features. View and model are connected via
  controller.

#### Utils Package:

- ImageComparisonUtils.java:
  This class compares two CustomImage objects pixel by pixel, checking whether two pixels are
  identical. It includes methods used in test cases to verify image matrices and has overloaded
  methods to handle tolerance levels for intensity arguments.

#### View Package:

- IView.java:
  It is the interface for main frame with all functionalities.

- JFrameView.java:
  This class represents the JFrame-based GUI view for the image processing application.
  This class provides the user interface to interact with the application,
  including loading, saving, and applying image transformations and effects.

#### Main Class:

- ImageProcessorApp.java:
  To run the application, execute the `ImageProcessorApp` class. Follow the prompts to load images,
  apply various commands, and save the results.

## Command Script:

The program allows users to run both command-line interface (CLI) commands and script commands
simultaneously.
Users can load an image using the CLI, then execute a run <text file.txt> command to perform
multiple image manipulations defined in a script file.
After the script finishes executing, the program doesn't terminate; it waits for further input from
the user, allowing continued interaction via the CLI.
Users can also manipulate images stored in the program's memory during the run command, directly
from the CLI afterward.
To quit the program, either stop it from the IDE or type "quit" in the CLI.

How To Run application:

First run ImageProcessorApp.java which is main starting point of system.
Then type in CLI: run check.txt
Contents of check.txt script:

````
load res/manhattan-small.png mh

load res/triangle_mask.png mh-mask

red-component mh mhr
save F/mhr.png mhr

red-component mh mhr-split-40 split 40
save F/mhr-split-40.png mhr-split-40

red-component mh mh-mask mhr-mask
save F/mhr-mask.png mhr-mask

blue-component mh mhb
save F/mhb.png mhb

blue-component mh mhb-split split 40
save F/mhb-split.png mhb-split

blue-component mh mh-mask mhb-mask
save F/mhb-mask.png mhb-mask

green-component mh mhg
save F/mhg.png mhg

green-component mh mhg-split split 40
save F/mhg-split.png mhg-split

green-component mh mh-mask mhg-mask
save F/mhg-mask.png mhg-mask

luma-component mh mh-luma
save F/mh-luma.png mhg

luma-component mh mh-luma-40 split 40
save F/mh-luma-40.png mh-luma-40

luma-component mh mh-mask mh-luma-mask
save F/mh-luma-mask.png mh-luma-mask

value-component mh mh-value
save F/mh-value.png mh-value

value-component mh mh-mask mh-mask-luma
save F/mh-mask-luma.png mh-mask-luma

value-component mh mh-value-40 split 40
save F/mh-value-40.png mh-value-40

intensity-component mh mh-intensity
save F/mh-intensity.png mh-intensity

intensity-component mh mh-mask mh-intensity-mask
save F/mh-intensity-mask.png mh-intensity-mask

intensity-component mh mh-intensity-40 split 40
save F/mh-intensity-40.png mh-intensity-40

blur mh mh-blur
save F/mh-blur.png mh-blur

blur mh mh-mask mh-blur-mask
save F/mh-blur-mask.png mh-blur-mask

blur mh mh-blur-split split 50
save F/mh-blur-split-50.png mh-blur-split

sharpen mh mh-sharpen
save F/mh-sharpen.png mh-sharpen

sharpen mh mh-mask mh-sharpen-mask
save F/mh-sharpen-mask.png mh-sharpen-mask

sharpen mh mh-sharpen-split split 50
save F/mh-sharpen-split.png mh-sharpen-split

sepia mh mh-sepia
save F/mh-sepia.png mh-sepia

sepia mh mh-mask mh-sepia-mask
save F/mh-sepia-mask.png mh-sepia-mask

sepia mh mh-sepia-split split 40
save F/mh-sepia-split.png mh-sepia-split

histogram mh mh-histo
save F/mh-histogram.png mh-histo

color-correct mh mh-correct
save F/mh-correct.png mh-correct

color-correct mh mh-correct-split split 40
save F/mh-correct-split.png mh-correct-split

level-adjust 100 200 255 mh mh-c-a
save F/mh-level-adjusted.png mh-c-a

level-adjust 100 200 255 mh mh-c-a-split split 40
save F/mh-level-adjusted-split.png mh-c-a-split

horizontal-flip mh mh-fliph
save F/mh-fliph.png mh-fliph

vertical-flip mh mh-flipv
save F/mh-flipv.png mh-flipv

rgb-combine mh-combine mhr mhg mhb
save F/mh-combine.png mh-combine

rgb-split mh mh-redsplit mh-greensplit mh-bluesplit
save F/mh-redsplit.png mh-redsplit
save F/mh-greensplit.png mh-greensplit
save F/mh-bluesplit.png mh-bluesplit

compress 50 mh mh-compress
save F/mh-compress.png mh-compress

quit

````

## References:

Image used for script testing and application testing:

- https://pngtree.com/freepng/cute-cartoon-hand-drawn-playing-koala_5326858.html
- galaxy.png and manhattan-small.png were provided in the assignment question.
- https://upload.wikimedia.org/wikipedia/commons/4/41/Lower_Manhattan_from_Jersey_City_November_2014_panorama_2.jpg (
  for manhattan-small image)

=======
# Photo-Editor
>>>>>>> a6f0c533075c81ad2f269ad9ccb7e2353fa23cca
