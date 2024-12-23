package hust.soict.cybersec.aims.screen.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {
    private Cart cart;
    
    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;
    
    @FXML
    private Label costLabel;

    @FXML
    private TextField tfFilter;

    @FXML
    private Button placeOrder;

    @FXML
    void placeOrderPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully.");
        alert.setTitle("Order created");
        alert.setHeaderText(null);
        alert.showAndWait();
        cart.clearCart(); // Clear the cart after placing order
        updateCostLabel();
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            updateCostLabel();
        }
    }

    public CartScreenController(Cart cart) {
        super();
        this.cart = cart;
    }

    @FXML
    void initialize() {
        colMediaTitle.setCellValueFactory(
            new PropertyValueFactory<Media, String>("title")
        );
        colMediacategory.setCellValueFactory(
            new PropertyValueFactory<Media, String>("category")
        );
        colMediaCost.setCellValueFactory(
            new PropertyValueFactory<Media, Float>("cost")
        );
        tblMedia.setItems(this.cart.getItemsOrdered());

        updateCostLabel();

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Media>() {

                @Override
                public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                    if (newValue != null) {
                        updateButtonBar(newValue);
                    }
                }

                private void updateButtonBar(Media media) {
                    btnRemove.setVisible(true);
                    btnPlay.setVisible(media instanceof Playable);
                }
            }
        );

        tfFilter.textProperty().addListener(
            new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }

            private void showFilteredMedia(String keyword) {
                FilteredList<Media> filteredList = new FilteredList<>(cart.getItemsOrdered());

                if (!keyword.isEmpty() && radioBtnFilterId.isSelected()) {
                    filteredList.setPredicate(media -> {
                        String idString = String.valueOf(media.getId());
                        return idString.equals(keyword);
                    });
                } else if (!keyword.isEmpty() &&  radioBtnFilterTitle.isSelected()) {
                    filteredList.setPredicate(media -> {
                        String title = media.getTitle().toLowerCase();
                        return title.contains(keyword.toLowerCase());
                    });
                } else {
                    filteredList.setPredicate(null);
                }
                tblMedia.setItems(filteredList);
            }
        });

    }

    private void updateCostLabel() {
        costLabel.setText(String.format("%.2f $", cart.totalCost()));
    }
}
