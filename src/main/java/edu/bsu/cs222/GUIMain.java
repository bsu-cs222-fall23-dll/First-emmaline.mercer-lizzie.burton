package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class GUIMain extends Application {
    Button searchButton;
    Stage window;
    ListView<String> listView;

    @Override
    public void start(Stage primaryStage){
        GUIModel model = new GUIModel();
        window = primaryStage;
        window.setTitle("Wikipedia Revision Query");
        searchButton = new Button("Search");

        //FORM
        TextField input = new TextField();
        String nameInput = input.getText(); //.setPromptText("Enter Wikipedia article name: ");
        listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        JSONArray revisionsList = model.searchArticle(String.valueOf(nameInput));
//        String[] revisionsList = new String[]{"cat", "dog"};
        searchButton.setOnAction(e -> {
            for (Object section : revisionsList) {
                listView.getItems().add((String) section);
            }
        });

        //LAYOUT
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(listView, searchButton);//revisionList
        window.setScene(new Scene(layout, 300, 250));
        window.show();


//        searchButton = new Button("Search");

//        searchButton.setOnAction(e -> );
//                listView = new ListView<>();
//                listView.getItems().addAll("Cat", "Dog", "Mouse");
//                listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //{ System.out.println(nameInput.getText())
////            try {
////                searchArticle(nameInput.getText());
////            } catch (IOException ex) {
////                throw new RuntimeException(ex);
////            }
//        });

//        ListView<String> revisionList = new ListView<>();

    }
    public static void main(String[] args) {
        launch(args);
    }
}

