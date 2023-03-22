# Setting up

**Prerequisites**

* JDK 11
* Recommended: IntelliJ IDE
* Fork this repo to your GitHub account and clone the fork to your computer

**Importing the project into IntelliJ**

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first).
1. Set up the correct JDK version.
    * Click `Configure` > `Structure for new Projects` (in older versions of Intellij:`Configure` > `Project Defaults` > `Project Structure`).
    * If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11.
    * Click `OK`.
1. Click `Import Project`.
1. Locate the project directory and click `OK`.
1. Select `Create project from existing sources` and click `Next`.
1. Rename the project if you want. Click `Next`.
1. Ensure that your src folder is checked. Keep clicking `Next`.
1. Click `Finish`.

## Setting up Java FX

### If you are not using Gradle

1. Download the [JavaFX 11 SDK](https://gluonhq.com/products/javafx/) and unzip it.

1. Import the `libs` folder from the SDK into your project (note: `JAVAFX_HOME` is the directory in which you have unzipped the JavaFX SDK).

   `File` > `Project Structure` > `Libraries` > `+` > `Java` > `{JAVAFX_HOME}/lib`

1. From `Run` > `Edit Configurations`, add the following line into your `VM options` for each of the `main` classes.

   `--module-path {JAVAFX_HOME}/lib --add-modules javafx.controls,javafx.fxml`<br>
   e.g., `--module-path C:/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml`

### If you are using Gradle

Update your `build.gradle` to include the following lines:
```groovy
plugins {
    id 'java'
    id 'org.openjfx.javafxplugin' version '0.0.10'
}

repositories {
    mavenCentral()
}

javafx {
    version = "11.0.2"
    modules = [ 'javafx.controls', 'javafx.fxml' ]
}

```
## Errors you may face
**classpath issues**
1. Ensure that During Installation, use default location of installation.
2. Right Click Resource folder for JavaFxml Docs to be Resource Root Folder.


