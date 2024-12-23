package hust.soict.dsai.aims.disc;

import hust.soict.dsai.aims.media.Disc;
import media.Playable;

public class DigitalVideoDisc extends Disc implements Playable {
    private static int nbDigitalVideoDiscs = 0; // Số lượng DVD đã tạo

    // Constructor chỉ có title
    public DigitalVideoDisc(String title) {
        super(++nbDigitalVideoDiscs, title, null, 0.0f, 0, null); // Gán id tự động, category, cost, length mặc định
    }

    // Constructor với category, title và cost
    public DigitalVideoDisc(String title, String category, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, null);
    }

    // Constructor với director, category, title và cost
    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, director);
    }

    // Constructor với director, category, title, length và cost
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
    }

    // Implement phương thức play() từ Playable
    @Override
    public void play() {
        if (getLength() > 0) {
            System.out.println("Playing DVD: " + getTitle());
            System.out.println("DVD length: " + getLength() + " minutes");
        } else {
            System.out.println("DVD " + getTitle() + " cannot be played due to invalid length.");
        }
    }
    @Override
    public String toString() {
        return "DVD title: " + getTitle() + ", Director: " + getDirector();
    }
}
