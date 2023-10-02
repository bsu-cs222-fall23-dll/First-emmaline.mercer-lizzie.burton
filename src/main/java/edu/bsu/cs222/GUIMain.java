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
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
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

        //FORM
        Label label1 = new Label("Enter Wikipedia article name:");
        TextField textField = new TextField();
        textField.setPromptText("Enter Article Name");
        textField.setFocusTraversable(false);
//        String  articleTitle = textField.getText();


        Text text = new Text("");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 10);
        text.setFont(font);
        text.setTranslateX(15);
        text.setTranslateY(125);
        text.setFill(Color.PURPLE);
        text.maxWidth(580);
        text.setWrappingWidth(580);


//        String textField = input.getText().formatted("Enter Wikipedia article name: "); //.setPromptText("Enter Wikipedia article name: ");

//        listView = new ListView<>();
//        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

       // String[] revisionsList = new String[]{"cat", "dog"};
        searchButton.setOnAction(e -> {
            text.setText("The article you searched was: " + textField.getText());
            System.out.println(textField.getText());

            ArrayList<String> revisionsList = null;
            try {
                revisionsList = model.searchArticle(String.valueOf(textField.getText()));
                for (Object element : revisionsList) {
                    String revisionUserName = JsonPath.read(element, "$.user");
                    String revisionTimestamp = JsonPath.read(element, "$.timestamp");
                    text.setText(revisionTimestamp + revisionUserName);
//                    listView.getItems().add((String) element);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        //LAYOUT
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(25, 5, 5, 50));
        layout.getChildren().addAll(label1, textField);//revisionList, listView
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

