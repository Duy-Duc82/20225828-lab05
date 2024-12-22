package hust.soict.dsai.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartController {

    @FXML
    private TableView<CartItem> cartTable;

    @FXML
    private TableColumn<CartItem, String> nameColumn;

    @FXML
    private TableColumn<CartItem, Double> priceColumn;

    @FXML
    private TableColumn<CartItem, Integer> quantityColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    private ObservableList<CartItem> cartItems;

    @FXML
    public void initialize() {
        // Khởi tạo danh sách ObservableList
        cartItems = FXCollections.observableArrayList();

        // Gán giá trị cho các cột trong TableView
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Gắn ObservableList vào TableView
        cartTable.setItems(cartItems);
    }

    @FXML
    private void handleAdd() {
        try {
            // Lấy dữ liệu từ các trường nhập
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            // Thêm sản phẩm mới vào danh sách
            cartItems.add(new CartItem(name, price, quantity));

            // Xóa nội dung trong các trường nhập sau khi thêm
            nameField.clear();
            priceField.clear();
            quantityField.clear();
        } catch (NumberFormatException e) {
            // Hiển thị thông báo nếu nhập dữ liệu không hợp lệ
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid data format");
            alert.setContentText("Please ensure price and quantity are valid numbers.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDelete() {
        // Lấy mục được chọn trong TableView
        CartItem selectedItem = cartTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Xóa mục được chọn khỏi danh sách
            cartItems.remove(selectedItem);
        } else {
            // Hiển thị cảnh báo nếu không có mục nào được chọn
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No item selected");
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCalculateTotal() {
        // Tính tổng giá trị giỏ hàng
        double total = cartItems.stream()
                                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                                .sum();

        // Hiển thị tổng giá trị trong một thông báo
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Total Cost");
        alert.setHeaderText("Total Cost of Cart");
        alert.setContentText("Total: $" + total);
        alert.showAndWait();
    }
}
