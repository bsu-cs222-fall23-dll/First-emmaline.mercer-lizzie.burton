package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class GUIMain extends Application {
    Button searchButton;
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Wikipedia Revision Query");

        //FORM
        TextField articleNameInput = new TextField();
        articleNameInput.setPromptText("Enter Wikipedia article name:");
        searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            try {
                searchArticle(articleNameInput.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        ListView<String> revisionList = new ListView<>();

        //LAYOUT
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(articleNameInput, searchButton, revisionList);
        window.setScene(new Scene(layout, 400, 600));
        window.show();
    }

    private String searchArticle(String articleName) throws IOException {
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        String revisedList = String.valueOf(reader.readParsedData(articleName));
        return revisedList;
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}

