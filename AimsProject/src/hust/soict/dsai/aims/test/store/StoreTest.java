package hust.soict.dsai.test.store;


import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;


public class StoreTest {
    public static void main(String[] args) {
        // Tạo cửa hàng
        Store store = new Store();

        // Tạo các DVD mới
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);

        // Thêm DVD vào cửa hàng
        store.addDVD(dvd1);
        store.addDVD(dvd2);
        store.addDVD(dvd3);

        // In danh sách DVD trong cửa hàng
        store.printStoreDetails();

        // Xóa DVD khỏi cửa hàng
        store.removeDVD(dvd2);

        // In danh sách DVD sau khi xóa
        store.printStoreDetails();
    }
}
