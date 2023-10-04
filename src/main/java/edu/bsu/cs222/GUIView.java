package edu.bsu.cs222;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.List;

public class GUIView {

    @FXML
    private TextField inputField;
    @FXML
    private Button fetchButton;
    @FXML
    private TextArea outputArea;

    private final GUIModel model = new GUIModel();

    @FXML
    public void initialize() {
        fetchButton.setOnAction(event -> fetchAndDisplayRevisions());
    }

    private void fetchAndDisplayRevisions() {
        String articleTitle = inputField.getText().trim();

        if (articleTitle.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide an article name.");
            alert.showAndWait();
            return;
        }

        fetchButton.setDisable(true);
        inputField.setEditable(false);

        new Thread(() -> {
            try {
                List<Revision> revisions = model.getRevisionsForArticle(articleTitle);
                String redirectedTitle = Redirect.getRedirectedTitle(String.valueOf(revisions));

                Platform.runLater(() -> {
                    if (redirectedTitle != null) {
                        outputArea.appendText("Note: You were redirected from " + articleTitle + " to " + redirectedTitle + "\n");
                    }

                    for (Revision rev : revisions) {
                        outputArea.appendText("User: " + rev.getUsername() + ", Timestamp: " + rev.getTimestamp() + "\n");
                    }
                });

            } catch (IOException e) {
                String errorMessage = e.getMessage();

                Platform.runLater(() -> {
                    if (errorMessage.contains("HTTP response code: 404")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "No Wikipedia page found for the given article name.");
                        alert.showAndWait();
                    } else if (errorMessage.contains("UnknownHostException")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please check your internet connection.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Did you check your internet? " + errorMessage);
                        alert.showAndWait();
                    }

                    fetchButton.setDisable(false);
                    inputField.setEditable(true);
                });
            }
        }).start();
    }



}
