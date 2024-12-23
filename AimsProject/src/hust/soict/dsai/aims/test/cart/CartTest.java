package hust.soict.dsai.test.cart;

public class CartTest {
    public static void main(String[] args) {
        // Tạo đối tượng Cart
        Cart cart = new Cart();

        // Tạo các DVD
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Aladdin", "Animation", "John Musker", 90, 18.99f);

        // Thêm DVD vào giỏ hàng
        cart.addDigitalVideoDisc(dvd1);
        cart.addDigitalVideoDisc(dvd2);

        // Thêm nhiều DVD một lúc
        cart.addDigitalVideoDisc(new DigitalVideoDisc[] { dvd1, dvd2, dvd3 });

        // Thêm hai DVD cùng lúc
        cart.addDigitalVideoDisc(dvd2, dvd3);

        // In chi tiết giỏ hàng
        cart.printCartDetails();

        // In danh sách ID
        cart.printAllIds();

        // In danh sách tiêu đề
        cart.printAllTitles();

        // Xóa DVD khỏi giỏ hàng
        cart.removeDigitalVideoDisc(dvd2);

        // In chi tiết giỏ hàng sau khi xóa
        cart.printCartDetails();
    }
}

=======

