package edu.bsu.cs222;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends CLIManager {

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10); // 10px spacing between components

        TextField articleNameInput = new TextField();
        articleNameInput.setPromptText("Enter Wikipedia article name");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchArticle(articleNameInput.getText()));

        ListView<String> revisionList = new ListView<>();

        layout.getChildren().addAll(articleNameInput, searchButton, revisionList);

        primaryStage.setScene(new Scene(layout, 400, 600));
        primaryStage.setTitle("Wikipedia Revision Viewer");
        primaryStage.show();
    }

    private void searchArticle(String articleName) {
        // Your logic to search for the article and display revisions.
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}

