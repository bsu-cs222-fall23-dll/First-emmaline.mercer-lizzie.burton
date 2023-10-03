package edu.bsu.cs222;

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

    private final GUIModel model = new GUIModel(); // Assuming you have a GUIModel for data fetching

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

        fetchButton.setDisable(true); // Disable the button while processing.
        inputField.setEditable(false); // Disable the input field.

        new Thread(() -> {
            try {
                List<Revision> revisions = model.getRevisionsForArticle(articleTitle);
                String redirectedTitle = Redirect.getRedirectedTitle(String.valueOf(revisions));
                if (redirectedTitle != null) {
                    outputArea.appendText("Note: You were redirected from " + articleTitle + " to " + redirectedTitle + "\n");
                }

                for (Revision rev : revisions) {
                    outputArea.appendText("User: " + rev.getUsername() + ", Timestamp: " + rev.getTimestamp() + "\n");
                }
            } catch (IOException e) {
                String errorMessage = e.getMessage();
                if (errorMessage.contains("HTTP response code: 404")) {
                    outputArea.appendText("No Wikipedia page found for the given article name.\n");
                } else {
                    // Use JavaFX alert instead of JOptionPane
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Network Error: " + errorMessage);
                    alert.showAndWait();
                }


            } finally {
                fetchButton.setDisable(false);
                inputField.setEditable(true); // Re-enable the input field.
            }
        }).start();
    }

}
