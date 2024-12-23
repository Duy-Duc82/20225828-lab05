package hust.soict.dsai.aims.screen.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.screen.AddTrack;
import hust.soict.dsai.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCDScreenController {

    private Store store;
    private CompactDisc cd;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddTrack;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnAddCD;

    @FXML
    private TextField tfCategory;

    @FXML
    private TextField tfCost;

    @FXML
    private TextField tfArtist;

    @FXML
    private TextField tfTitle;

    public AddCDScreenController(Store store) {
        super();
        this.store = store;
    }

    @FXML
    void btnAddCDPressed(ActionEvent event) {
        if (cd != null) {
            store.addMedia(cd);
            clearFields();
            btnSave.setDisable(true);
            btnAddCD.setDisable(true);
            btnAddTrack.setDisable(true);
            showAlert("Success", "CD has been added to the store!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "No CD to add!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void btnAddTrackPressed(ActionEvent event) {
        if (cd != null) {
            new AddTrack(cd);
        } else {
            showAlert("Error", "No CD available to add tracks!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void btnSavePressed(ActionEvent event) {
        String title = tfTitle.getText().trim();
        String category = tfCategory.getText().trim();
        String artist = tfArtist.getText().trim();

        float cost;
        try {
            cost = Float.parseFloat(tfCost.getText().trim());
            if (cost <= 0) {
                throw new NumberFormatException("Cost must be positive.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input: Cost must be a positive number.", Alert.AlertType.ERROR);
            return;
        }

        if (title.isEmpty() || category.isEmpty() || artist.isEmpty()) {
            showAlert("Error", "All fields must be filled.", Alert.AlertType.ERROR);
            return;
        }

        cd = new CompactDisc(title, category, artist, cost);
        btnAddCD.setDisable(false);
        btnAddTrack.setDisable(false);
        btnSave.setDisable(true);
        showAlert("Success", "CD information saved. Add tracks or confirm addition to store.", Alert.AlertType.INFORMATION);
    }

    @FXML
    void initialize() {
        btnSave.setDisable(true);
        btnAddCD.setDisable(true);
        btnAddTrack.setDisable(true);

        tfTitle.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfCategory.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfArtist.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfCost.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
    }

    private void validateFields() {
        boolean allFieldsFilled = !tfTitle.getText().trim().isEmpty() &&
                                  !tfCategory.getText().trim().isEmpty() &&
                                  !tfArtist.getText().trim().isEmpty() &&
                                  !tfCost.getText().trim().isEmpty();
        btnSave.setDisable(!allFieldsFilled);
    }

    private void clearFields() {
        tfTitle.clear();
        tfCategory.clear();
        tfArtist.clear();
        tfCost.clear();
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
