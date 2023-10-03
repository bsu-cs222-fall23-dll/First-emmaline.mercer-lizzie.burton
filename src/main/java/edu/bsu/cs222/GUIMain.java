package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import net.minidev.json.JSONArray;
import java.io.IOException;


import static javafx.application.Application.launch;

public class GUIMain extends Application {
    Button searchButton, goBackButton, closeButton;
    Stage window;
    Scene scene, scene2;
    ListView<String> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        GUIModel model = new GUIModel();
        AlertBox alertBox = new AlertBox();
        window = primaryStage;

        searchButton = new Button("Search");
        searchButton.setLayoutX(50);
        searchButton.setLayoutY(85);

        goBackButton = new Button("Go Back");
        goBackButton.setLayoutX(100);
        goBackButton.setLayoutY(100);

        closeButton = new Button(("click me"));
        closeButton.setLayoutX(50);
        closeButton.setLayoutY(100);
        closeButton.setOnAction(e -> alertBox.displayAlertBox("Title", "Message"));

        //FORM
        Text text = new Text("");
        text.setText("Enter Wikipedia Article Name:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 20);
        text.setFont(font);
        text.setTranslateX(15);
        text.setTranslateY(30);
        text.setFill(Color.WHITE);
        text.maxWidth(580);
        text.setWrappingWidth(580);

        TextField textField = new TextField();
        textField.setPromptText("Enter Article Name");
        textField.setFocusTraversable(false);

        listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

       // String[] revisionsList = new String[]{"cat", "dog"}; // MAYBE I CAN MAKE A TEST OUT OF THIS???

        //SEARCHBUTTON ACTION
        searchButton.setOnAction(e -> {
            window.setScene(scene2);
//            text.setText("The article you searched was: " + textField.getText());
            JSONArray revisedArray = null;
            try {
                revisedArray = model.searchArticle(textField.getText());
                String[] revisedStringArray = new String[13];
                    int j = -1;
                    for (Object revision : revisedArray) {
                        String revisionUserName = JsonPath.read(revision, "$.user");
                        String revisionTimestamp = JsonPath.read(revision, "$.timestamp");
                        j++;
                        for (int i = j; i <= revisedStringArray.length; i++ ) {
                            if (revisedStringArray[i] == null) {
                                revisedStringArray[i] = ("Timestamp: " + revisionTimestamp + " User: " + revisionUserName);
                                listView.getItems().add((String)revisedStringArray[i]);
                                i--;
                            } else {
                                break;
                            }
                        }
                    }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //GOBACKBUTTON ACTION
        goBackButton.setOnAction(e -> {
            window.setScene(scene);
            listView.getItems().clear();
        });

        //LAYOUT OF SCENE1
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(50, 50, 50, 50));
        layout.getChildren().addAll(textField);
        Group root = new Group(layout, text, searchButton);
        scene = new Scene(root, 500, 500, Color.CORNFLOWERBLUE);
        window.setTitle("Wikipedia Revision Query");
        window.setScene(scene);
        window.show();

        //LAYOUT OF SCENE2
        VBox layout2 = new VBox(20);
        layout2.setPadding(new Insets(50, 50, 50, 50));
        layout2.getChildren().addAll(listView, goBackButton);
        Group root2 = new Group(layout2);
        scene2 = new Scene(root2, 500, 500, Color.CORNFLOWERBLUE);
    }
}

