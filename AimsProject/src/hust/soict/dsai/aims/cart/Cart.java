package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Cart {
    // Maximum number of items allowed in the cart
    public static final int MAX_NUMBERS_ORDERED = 20;

    // List to store Media objects in the cart
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    // Add a Media item to the cart
    public void addMedia(Media media) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            itemsOrdered.add(media);
            System.out.println("Media \"" + media.getTitle() + "\" has been added to the cart.");
        } else {
            System.out.println("The cart is full. Could not add \"" + media.getTitle() + "\".");
        }
    }

    // Remove a Media item from the cart
    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("Media \"" + media.getTitle() + "\" has been removed from the cart.");
        } else {
            System.out.println("Media \"" + media.getTitle() + "\" does not exist in the cart.");
        }
    }

    // Calculate the total cost of items in the cart
    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    // Print information of Media items in the cart
    public void printCartDetails() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            Media media = itemsOrdered.get(i);
            System.out.println((i + 1) + ". " + media.getTitle() + " - " + media.getCategory() + ": " + media.getCost() + " $");
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("*****************************************************");
    }

    // Print all IDs of Media items
    public void printAllIds() {
        System.out.println("List of all Media IDs with full details:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            Media media = itemsOrdered.get(i);
            System.out.println((i + 1) + ". ID: " + media.getId() +
                               ", Title: " + media.getTitle() +
                               ", Category: " + media.getCategory() +
                               ", Cost: " + media.getCost());
        }
    }

    // Print all titles of Media items
    public void printAllTitles() {
        System.out.println("List of all Media Titles with full details:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            Media media = itemsOrdered.get(i);
            System.out.println((i + 1) + ". Title: " + media.getTitle() +
                               ", Category: " + media.getCategory() +
                               ", Cost: " + media.getCost() +
                               ", ID: " + media.getId());
        }
    }
}
