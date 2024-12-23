package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<>();

    // Constructor
    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    // Phương thức thêm tác giả
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        } else {
            System.out.println("Author already exists: " + authorName);
        }
    }

    // Phương thức xóa tác giả
    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
        } else {
            System.out.println("Author not found: " + authorName);
        }
    }
    @Override
    public String toString() {
        return "Book title: " + getTitle() + ", Author: " + getAuthor();
    }
}
