package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Store {
    // Maximum number of items allowed in the store
    private static final int MAX_ITEMS_IN_STORE = 100;

    // List to store Media items
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    // Add a Media item to the store
    public void addMedia(Media media) {
        if (itemsInStore.size() < MAX_ITEMS_IN_STORE) {
            itemsInStore.add(media);
            System.out.println("Media \"" + media.getTitle() + "\" has been added to the store.");
        } else {
            System.out.println("The store is full. Cannot add \"" + media.getTitle() + "\".");
        }
    }

    // Remove a Media item from the store
    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("Media \"" + media.getTitle() + "\" has been removed from the store.");
        } else {
            System.out.println("Media \"" + media.getTitle() + "\" does not exist in the store.");
        }
    }

    // Print details of Media items in the store
    public void printStoreDetails() {
        System.out.println("***********************STORE***********************");
        System.out.println("Available Items in Store:");
        for (int i = 0; i < itemsInStore.size(); i++) {
            Media media = itemsInStore.get(i);
            System.out.println((i + 1) + ". " + media.getTitle() + " - " + media.getCategory() + ": " + media.getCost() + " $");
        }
        System.out.println("**************************************************");
    }
}
