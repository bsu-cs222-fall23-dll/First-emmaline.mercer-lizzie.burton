package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import java.lang.reflect.Array;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class GUIMain extends Application {
    Button searchButton;
    Stage window;
    Scene scene;
    ListView<String> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        GUIModel model = new GUIModel();
        window = primaryStage;
        searchButton = new Button("Search");
        searchButton.setAlignment(Pos.BASELINE_CENTER);

        //FORM
        Label label1 = new Label("Enter Wikipedia article name:");

        TextField textField = new TextField();
        textField.setPromptText("Enter Article Name");
        textField.setFocusTraversable(false);
        Text text = new Text("");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 10);
        text.setFont(font);
        text.setTranslateX(15);
        text.setTranslateY(125);
        text.setFill(Color.PURPLE);
        text.maxWidth(580);
        text.setWrappingWidth(580);

        listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

       // String[] revisionsList = new String[]{"cat", "dog"}; // MAYBE I CAN MAKE A TEST OUT OF THIS???

        //BUTTON ACTION
        searchButton.setOnAction(e -> {
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

//                            } else if (revisedStringArray[i + 1] == null && Integer.parseInt(revisedStringArray[i]) != 0 && Array.get(revisedStringArray, i) != Array.get(revisedStringArray, i - 1)) { // used to add to every i > 0
//                                revisedStringArray[i + 1] = ("Timestamp: " + revisionTimestamp + " User: " + revisionUserName);
//                                listView.getItems().add((String)revisedStringArray[i + 1]);
//                                i--;
                                //if (0 == Integer.parseInt(revisedStringArray[i])) {
//                                    break;
//                                if (Array.get(revisedStringArray, i) != Array.get(revisedStringArray, i - 1) && Integer.parseInt(revisedStringArray[i]) != 0) {
//                                    revisedStringArray[i + 1] = ("Timestamp: " + revisionTimestamp + " User: " + revisionUserName);
//                                    listView.getItems().add((String) revisedStringArray[i + 1]);
//                                    i--;
////                                break;
//                                } else {
//                                    break;
//                                }
                            } else {
                                break;
                            }
                        }
                    }
//                    revisedStringArray[(revisionTimestamp + revisionUserName);
//                    text.setText("Timestamp: " + revisionTimestamp + " User: " + revisionUserName);
//                    text.setText("Timestamp: " + revisionTimestamp + " User: " + revisionUserName);
//                    //listView.getItems().add((String)revision);
//                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        //LAYOUT
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(25, 5, 5, 50));
        layout.getChildren().addAll(label1, textField, listView);//revisionList, listView
        Group root = new Group(layout, text, searchButton);
        scene = new Scene(root, 595, 150, Color.IVORY);
        window.setTitle("Wikipedia Revision Query");
        window.setScene(scene);
        window.show();


//        searchButton = new Button("Search");

//        searchButton.setOnAction(e -> );
//                listView = new ListView<>();
//                listView.getItems().addAll("Cat", "Dog", "Mouse");
//                listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //{ System.out.println(textField.getText())
////            try {
////                searchArticle(textField.getText());
////            } catch (IOException ex) {
////                throw new RuntimeException(ex);
////            }
//        });

//        ListView<String> revisionList = new ListView<>();

    }
}

