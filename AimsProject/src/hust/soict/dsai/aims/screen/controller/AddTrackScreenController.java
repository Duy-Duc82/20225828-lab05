package hust.soict.dsai.aims.screen.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddTrackScreenController {
    private CompactDisc cd;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSaveTrack;

    @FXML
    private TextField tfLength;

    @FXML
    private TextField tfTitle;

    public AddTrackScreenController(CompactDisc cd) {
        super();
        this.cd = cd;
    }

    @FXML
    void btnSaveTrackPressed(ActionEvent event) {
        String title = tfTitle.getText().trim();
        int length;

        try {
            length = Integer.parseInt(tfLength.getText().trim());
            if (length <= 0) {
                throw new NumberFormatException("Length must be positive.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input", "Length must be a positive integer.", Alert.AlertType.ERROR);
            return;
        }

        if (title.isEmpty()) {
            showAlert("Error", "Invalid input", "Title cannot be empty.", Alert.AlertType.ERROR);
            return;
        }

        Track track = new Track(title, length);

        if (cd.getTracks().contains(track)) {
            showAlert("Warning", "Duplicate Track", "This track already exists in the CD.", Alert.AlertType.WARNING);
        } else {
            cd.addTrack(track);
            showAlert("Success", "Track Added", "Track has been added successfully.", Alert.AlertType.INFORMATION);
        }

        tfTitle.clear();
        tfLength.clear();
    }

    @FXML
    void initialize() {
        btnSaveTrack.setDisable(true);

        tfTitle.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfLength.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
    }

    private void validateFields() {
        btnSaveTrack.setDisable(tfTitle.getText().trim().isEmpty() || tfLength.getText().trim().isEmpty());
    }

    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
