package hust.soict.dsai.aims.screen.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddDVDScreenController {

    private Store store;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSave;

    @FXML
    private TextField tfCategory;

    @FXML
    private TextField tfCost;

    @FXML
    private TextField tfDirector;

    @FXML
    private TextField tfLength;

    @FXML
    private TextField tfTitle;

    public AddDVDScreenController(Store store) {
        super();
        this.store = store;
    }

    @FXML
    void btnSavePressed(ActionEvent event) {
        String title = tfTitle.getText().trim();
        String category = tfCategory.getText().trim();
        String director = tfDirector.getText().trim();

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

        float cost;
        try {
            cost = Float.parseFloat(tfCost.getText().trim());
            if (cost <= 0) {
                throw new NumberFormatException("Cost must be positive.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input", "Cost must be a positive number.", Alert.AlertType.ERROR);
            return;
        }

        if (title.isEmpty() || category.isEmpty() || director.isEmpty()) {
            showAlert("Error", "Invalid input", "Title, category, and director cannot be empty.", Alert.AlertType.ERROR);
            return;
        }

        DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
        store.addMedia(dvd);

        tfTitle.clear();
        tfCategory.clear();
        tfDirector.clear();
        tfLength.clear();
        tfCost.clear();

        showAlert("Success", "DVD Added", "The DVD has been added to the store.", Alert.AlertType.INFORMATION);
    }

    @FXML
    void initialize() {
        btnSave.setDisable(true);

        tfTitle.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfCategory.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfDirector.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfLength.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfCost.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
    }

    private void validateFields() {
        btnSave.setDisable(tfTitle.getText().trim().isEmpty() ||
                           tfCategory.getText().trim().isEmpty() ||
                           tfDirector.getText().trim().isEmpty() ||
                           tfLength.getText().trim().isEmpty() ||
                           tfCost.getText().trim().isEmpty());
    }

    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
