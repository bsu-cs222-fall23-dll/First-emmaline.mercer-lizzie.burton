package edu.bsu.cs222;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/edu/bsu/cs222/resources/MYFXML.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/MYFXML.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Wikipedia Search");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
