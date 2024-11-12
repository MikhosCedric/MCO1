## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Requirements

- You will need to have javafx-sdk-23.0.1 in you device
- You will need mysql_connector-j-8.0.33 to be configured in your device

## Changes to be made:

- In mysqlconnect.java, replace the database directory with your own path, username, and password
- In .vscode -> launch.json, replace the "vmArgs": "--module-path \"C:/Program Files (x86)/Java/javafx-sdk-23.0.1/lib\" --add-modules javafx.controls,javafx.fxml" to the module path where you stored you javafx lib folder
- Do the same adjustents to the settings.json folder and its components
- If you are using VSCode: In the JAVA PROJECTS tab in the explorer panel, add the jar files inside your javafx lib to the referenced libraries and also add the jar file of your mysql_connector.
