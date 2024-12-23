package hust.soict.dsai.aims.screen.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookScreenController {

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
    private TextField tfTitle;

    public AddBookScreenController(Store store) {
        super();
        this.store = store;
    }

    @FXML
    void btnSavePressed(ActionEvent event) {
        String title = tfTitle.getText().trim();
        String category = tfCategory.getText().trim();

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

        if (title.isEmpty() || category.isEmpty()) {
            showAlert("Error", "All fields must be filled.", Alert.AlertType.ERROR);
            return;
        }

        Book book = new Book(title, category, cost);
        store.addMedia(book);

        clearFields();
        showAlert("Success", "Book has been added to the store!", Alert.AlertType.INFORMATION);
    }

    @FXML
    void initialize() {
        btnSave.setDisable(true);

        tfTitle.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfCategory.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        tfCost.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
    }

    private void validateFields() {
        boolean allFieldsFilled = !tfTitle.getText().trim().isEmpty() &&
                                  !tfCategory.getText().trim().isEmpty() &&
                                  !tfCost.getText().trim().isEmpty();
        btnSave.setDisable(!allFieldsFilled);
    }

    private void clearFields() {
        tfTitle.clear();
        tfCategory.clear();
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
