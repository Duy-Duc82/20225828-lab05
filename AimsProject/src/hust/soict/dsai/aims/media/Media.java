package hust.soict.dsai.aims.media;
import java.util.Comparator;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;
    private String artist;
    private String author;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    // Constructor
    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getters and Setters
    public String getAuthor(){
        return author;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    public String getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
    if (obj == null || !(obj instanceof Media)) {
         return false;
        }
        Media other = (Media) obj;
        return this.title != null && this.title.equals(other.title);
    }
        
    @Override
    public int hashCode() {
    return (title != null) ? title.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "Media title: " + getTitle();
    }
}

