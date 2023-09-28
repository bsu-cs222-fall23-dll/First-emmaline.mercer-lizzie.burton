package edu.bsu.cs222;

import javafx.stage.Stage;

public abstract class CLIManager {

    public static void main(String[] args) {
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        reader.run();
    }


    public abstract void start(Stage primaryStage);
}
